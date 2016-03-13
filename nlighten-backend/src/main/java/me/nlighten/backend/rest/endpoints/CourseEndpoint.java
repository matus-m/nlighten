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
import me.nlighten.backend.db.dao.CourseDAO;
import me.nlighten.backend.db.dao.exception.DAOException;
import me.nlighten.backend.db.model.Course;
import me.nlighten.backend.rest.model.CourseDTO;
import me.nlighten.backend.rest.util.CourseMapper;

/**
 * The Class CourseEndpoint.
 * 
 * @author Lubo
 */
@Path("/courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CourseEndpoint {

  /** The course dao. */
  @EJB
  private CourseDAO courseDAO;

  /** The dto mapper. */
  CourseMapper courseMapper = Selma.mapper(CourseMapper.class);

  /**
   * Creates the.
   *
   * @param courseDTO the course dto
   * @return the course dto
   * @throws DAOException the DAO exception
   */
  @POST
  @Path("/")
  public CourseDTO create(CourseDTO courseDTO) throws DAOException {
    Course course = courseMapper.toCourse(courseDTO);
    course = courseDAO.save(course);
    return courseMapper.toCourseDTO(course);
  }

  /**
   * Update.
   *
   * @param id the id
   * @param courseDTO the course dto
   * @return the course dto
   * @throws DAOException the DAO exception
   */
  @PUT
  @Path("/{id}")
  public CourseDTO update(@PathParam("id") long id, CourseDTO courseDTO) throws DAOException {
    Course foundCourse = courseDAO.findById(Course.class, id);
    Course course = courseMapper.toCourse(courseDTO, foundCourse);
    course = courseDAO.merge(course);
    return courseMapper.toCourseDTO(course);
  }

  /**
   * Find by id.
   *
   * @param id the id
   * @param graphName the graph name
   * @return the course dto
   * @throws DAOException the DAO exception
   */
  @GET
  @Path("/{id}")
  public CourseDTO findById(@PathParam("id") long id, @QueryParam("graphName") String graphName)
      throws DAOException {
    Course course = null;
    if (graphName != null && !graphName.isEmpty()) {
      course = courseDAO.loadById(graphName, id);
      return courseMapper.toCourseDTOWithCollections(course);
    } else {
      course = courseDAO.findById(Course.class, id);
      return courseMapper.toCourseDTO(course);
    }
  }

  /**
   * Find all.
   *
   * @return the list
   * @throws DAOException the DAO exception
   */
  @GET
  @Path("/")
  public List<CourseDTO> findAll() throws DAOException {
    List<Course> courses = courseDAO.findAll(Course.class);
    return courseMapper.toCoursesDTO(courses);
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
    return courseDAO.delete(Course.class, id);
  }
}
