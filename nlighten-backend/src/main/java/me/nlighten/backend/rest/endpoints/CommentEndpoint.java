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
import javax.ws.rs.core.MediaType;

import fr.xebia.extras.selma.Selma;
import me.nlighten.backend.db.dao.CommentDAO;
import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.model.Comment;
import me.nlighten.backend.rest.model.CommentDTO;
import me.nlighten.backend.rest.util.CommentMapper;

/**
 * The Class CommentEndpoint.
 * 
 * @author Lubo
 */
@Path("/comments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CommentEndpoint {

  /** The comment dao. */
  @EJB
  private CommentDAO commentDAO;

  /** The comment mapper. */
  CommentMapper commentMapper = Selma.mapper(CommentMapper.class);

  /**
   * Creates the.
   *
   * @param commentDTO the comment dto
   * @return the comment dto
   * @throws DAOException the DAO exception
   */
  @POST
  @Path("/")
  public CommentDTO create(CommentDTO commentDTO) throws DAOException {
    Comment comment = commentMapper.toComment(commentDTO);
    comment = commentDAO.save(comment);
    return commentMapper.toCommentDTO(comment);
  }

  /**
   * Update.
   *
   * @param id the id
   * @param commentDTO the comment dto
   * @return the comment dto
   * @throws DAOException the DAO exception
   */
  @PUT
  @Path("/{id}")
  public CommentDTO update(@PathParam("id") long id, CommentDTO commentDTO) throws DAOException {
    Comment foundComment = commentDAO.findById(id);
    Comment comment = commentMapper.toComment(commentDTO, foundComment);

    comment = commentDAO.merge(comment);
    return commentMapper.toCommentDTO(comment);
  }

  /**
   * Find by id endpoint.
   *
   * @param id the id
   * @return the comment dto
   * @throws DAOException the DAO exception
   */
  @GET
  @Path("/{id}")
  public CommentDTO findById(@PathParam("id") long id) throws DAOException {
    Comment comment = commentDAO.findById(id);
    return commentMapper.toCommentDTO(comment);
  }

  /**
   * Find all endpoint.
   *
   * @return the list
   * @throws DAOException the DAO exception
   */
  @GET
  @Path("/")
  public List<CommentDTO> findAll() throws DAOException {
    List<Comment> comments = commentDAO.findAll();
    return commentMapper.toCommentsDTO(comments);
  }

  /**
   * Delete.
   *
   * @param id the id
   * @return true, if successful
   * @throws DAOException the DAO exception
   */
  @DELETE
  @Path("/{id}")
  public boolean delete(@PathParam("id") long id) throws DAOException {
    return commentDAO.deleteById(id);
  }
}
