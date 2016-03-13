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
import me.nlighten.backend.db.dao.AnswerDAO;
import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.model.Answer;
import me.nlighten.backend.rest.model.AnswerDTO;
import me.nlighten.backend.rest.util.AnswerMapper;

/**
 * The Class AnswerEndpoint.
 * 
 * @author Lubo
 */
@Path("/answers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AnswerEndpoint {

  /** The answer dao. */
  @EJB
  private AnswerDAO answerDAO;

  /** The dto answer mapper. */
  AnswerMapper answerMapper = Selma.mapper(AnswerMapper.class);

  /**
   * Creates the.
   *
   * @param answerDTO the answer dto
   * @return the answer dto
   * @throws DAOException the DAO exception
   */
  @POST
  @Path("/")
  public AnswerDTO create(AnswerDTO answerDTO) throws DAOException {
    Answer answer = answerMapper.toAnswer(answerDTO);
    answer = answerDAO.save(answer);
    return answerMapper.toAnswerDTO(answer);
  }

  /**
   * Update.
   *
   * @param id the id
   * @param answerDTO the answer dto
   * @return the answer dto
   * @throws DAOException the DAO exception
   */
  @PUT
  @Path("/{id}")
  public AnswerDTO update(@PathParam("id") long id, AnswerDTO answerDTO) throws DAOException {
    Answer foundAnswer = answerDAO.findById(Answer.class, id);
    Answer answer = answerMapper.toAnswer(answerDTO, foundAnswer);

    answer = answerDAO.merge(answer);
    return answerMapper.toAnswerDTO(answer);
  }

  /**
   * Find by id endpoint.
   *
   * @param id the id
   * @return the answer dto
   * @throws DAOException the DAO exception
   */
  @GET
  @Path("/{id}")
  public AnswerDTO findById(@PathParam("id") long id) throws DAOException {
    Answer answer = answerDAO.findById(Answer.class, id);
    return answerMapper.toAnswerDTO(answer);
  }

  /**
   * Find all endpoint.
   *
   * @return the list
   * @throws DAOException the DAO exception
   */
  @GET
  @Path("/")
  public List<AnswerDTO> findAll() throws DAOException {
    List<Answer> answers = answerDAO.findAll(Answer.class);
    return answerMapper.toAnswersDTO(answers);
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
    return answerDAO.delete(Answer.class, id);
  }
}
