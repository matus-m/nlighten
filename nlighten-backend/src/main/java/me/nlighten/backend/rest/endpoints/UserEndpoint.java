package me.nlighten.backend.rest.endpoints;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.xebia.extras.selma.Selma;
import me.nlighten.backend.db.dao.UserDAO;
import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.model.User;
import me.nlighten.backend.rest.model.UserDTO;
import me.nlighten.backend.rest.util.UserMapper;

/**
 * The User Endpoint.
 * 
 * @author Dorin Gheorghe Brage
 */

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class UserEndpoint {

  /** The user dao */
  @EJB
  private UserDAO userDAO;

  /** The dto user mapper */
  UserMapper userDtoMapper = Selma.mapper(UserMapper.class);

  /**
   * Create the user
   * 
   * @param userDTO
   * @return
   * @throws DAOException
   */
  @PUT
  @Path("/")
  public UserDTO create(UserDTO userDTO) throws DAOException {
    User user = userDtoMapper.toUser(userDTO);
    user = userDAO.save(user);
    return userDtoMapper.toUserDTO(user);
  }

  /**
   * Update the user
   * 
   * @param id
   * @param userDTO
   * @return
   * @throws DAOException
   */
  @POST
  @Path("/{id}")
  public UserDTO update(@PathParam("id") long id, UserDTO userDTO) throws DAOException {
    User user = userDAO.findById(id);
    user = userDtoMapper.toUser(userDTO, user);
    user = userDAO.merge(user);

    return userDtoMapper.toUserDTO(user);
  }

  /**
   * Find or Load by id
   * 
   * @param id the given id
   * @param loaded flag to load or not with all the collections.
   * @return the {@link UserDTO}
   * @throws DAOException
   */
  @GET
  @Path("/{id}")
  public UserDTO findById(@PathParam("id") long id, @QueryParam("loaded") boolean loaded)
      throws DAOException {
    User user = null;
    if (loaded) {
      user = userDAO.loadById(id);
      return userDtoMapper.toUserDTOWithoutCollections(user);
    } else {
      user = userDAO.findById(id);
      return userDtoMapper.toUserDTO(user);
    }

  }

  /**
   * Find all the users
   * 
   * @return the users
   * @throws DAOException
   */
  @GET
  @Path("/")
  public List<UserDTO> findAll() throws DAOException {
    List<User> users = userDAO.findAll();
    return userDtoMapper.toUsersDTO(users);
  }

  /**
   * Delete the user
   * 
   * @param id the given id
   * @return {@code TRUE} is successful, otherwise {@code FALSE}
   * @throws DAOException
   */
  @DELETE
  @Path("/{id}")
  public boolean delete(@PathParam("id") long id) throws DAOException {
    return userDAO.deleteById(id);
  }
}
