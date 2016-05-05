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
import me.nlighten.backend.db.dao.LessonDAO;
import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.model.Lesson;
import me.nlighten.backend.rest.model.LessonDTO;
import me.nlighten.backend.rest.util.LessonMapper;

/**
 * The Class LessonEndpoint.
 * 
 * @author Lubo
 */
@Path("/lessons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class LessonEndpoint {

  /** The lesson dao. */
  @EJB
  private LessonDAO lessonDAO;

  /** The dto lesson mapper. */
  LessonMapper lessonDtoMapper = Selma.mapper(LessonMapper.class);

  /**
   * Creates the.
   *
   * @param lessonDTO the lesson dto
   * @return the lesson dto
   * @throws DAOException the DAO exception
   */
  @POST

  @Path("/")
  public LessonDTO create(LessonDTO lessonDTO) throws DAOException {
    Lesson lesson = lessonDtoMapper.toLesson(lessonDTO);
    lesson = lessonDAO.save(lesson);
    return lessonDtoMapper.toLessonDTO(lesson);
  }

  /**
   * Update.
   *
   * @param id the id
   * @param lessonDTO the lesson dto
   * @return the lesson dto
   * @throws DAOException the DAO exception
   */
  @PUT
  @Path("/{id}")
  public LessonDTO update(@PathParam("id") long id, LessonDTO lessonDTO) throws DAOException {
    Lesson foundlesson = lessonDAO.findById(id);
    Lesson lesson = lessonDtoMapper.toLesson(lessonDTO, foundlesson);

    lesson = lessonDAO.merge(lesson);
    return lessonDtoMapper.toLessonDTO(lesson);
  }

  /**
   * Find by id.
   *
   * @param id the id
   * @param graphName the graph name
   * @return the lesson dto
   * @throws DAOException the DAO exception
   */
  @GET
  @Path("/{id}")
  public LessonDTO findById(@PathParam("id") long id, @QueryParam("graphName") String graphName)
      throws DAOException {
    if (graphName != null && !graphName.isEmpty()) {
      Lesson lesson = lessonDAO.loadById(graphName, id);
      return lessonDtoMapper.toLessonDTOWithCollections(lesson);
    } else {
      Lesson lesson = lessonDAO.findById(id);
      return lessonDtoMapper.toLessonDTO(lesson);
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
  public List<LessonDTO> findAll() throws DAOException {
    List<Lesson> lessons = lessonDAO.findAll();
    return lessonDtoMapper.toLessonsDTO(lessons);
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
    return lessonDAO.deleteById(id);
  }
}
