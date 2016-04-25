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
import me.nlighten.backend.db.dao.QuestionDAO;
import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.model.Question;
import me.nlighten.backend.rest.model.QuestionDTO;
import me.nlighten.backend.rest.util.QuestionMapper;

/**
 * The Class QuestionEndpoint.
 * 
 * @author Lubo
 */
@Path("/questions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class QuestionEndpoint {

  /** The question dao. */
  @EJB
  private QuestionDAO questionDAO;

  /** The question mapper. */
  QuestionMapper questionMapper = Selma.mapper(QuestionMapper.class);

  /**
   * Creates the.
   *
   * @param QuestionDTO the question dto
   * @return the question dto
   * @throws DAOException the DAO exception
   */
  @POST
  @Path("/")
  public QuestionDTO create(QuestionDTO QuestionDTO) throws DAOException {
    Question question = questionMapper.toQuestion(QuestionDTO);
    question = questionDAO.save(question);
    return questionMapper.toQuestionDTO(question);
  }

  /**
   * Update.
   *
   * @param id the id
   * @param questionDTO the question dto
   * @return the question dto
   * @throws DAOException the DAO exception
   */
  @PUT
  @Path("/{id}")
  public QuestionDTO update(@PathParam("id") long id, QuestionDTO questionDTO) throws DAOException {
    Question foundQuestion = questionDAO.findById(id);
    Question question = questionMapper.toQuestion(questionDTO, foundQuestion);

    question = questionDAO.merge(question);
    return questionMapper.toQuestionDTO(question);
  }

  /**
   * Find by id.
   *
   * @param id the id
   * @param graphName the graph name
   * @return the question dto
   * @throws DAOException the DAO exception
   */
  @GET
  @Path("/{id}")
  public QuestionDTO findById(@PathParam("id") long id, @QueryParam("graphName") String graphName)
      throws DAOException {
    if (graphName != null && !graphName.isEmpty()) {
      Question question = questionDAO.loadById(graphName, id);
      return questionMapper.toQuestionDTOWithCollections(question);
    } else {
      Question question = questionDAO.findById(id);
      return questionMapper.toQuestionDTO(question);
    }
  }

  /**
   * Find all endpoint.
   *
   * @return the list
   * @throws DAOException the DAO exception
   */
  @GET
  @Path("/")
  public List<QuestionDTO> findAll() throws DAOException {
    List<Question> questions = questionDAO.findAll();
    return questionMapper.toQuestionsDTO(questions);
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
    return questionDAO.deleteById(id);
  }
}
