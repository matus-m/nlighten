package me.nlighten.backend.rest.endpoints;

import java.net.URL;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.nlighten.backend.db.enums.CourseType;
import me.nlighten.backend.db.enums.Difficulty;
import me.nlighten.backend.db.model.Course;
import me.nlighten.backend.rest.util.JaxRsActivator;
import me.nlighten.backend.test.AbstractTest;

/**
 * The Class CourseEndpointTest.
 * 
 * @author Lubo
 */
@RunWith(Arquillian.class)
public class CourseEndpointTest extends AbstractTest {

  /** The deployment url. */
  @ArquillianResource
  URL deploymentUrl;

  /** The endpoint url prefix. */
  private static final String RESOURCE_PREFIX =
      JaxRsActivator.class.getAnnotation(ApplicationPath.class).value().substring(1);

  /**
   * Creates the test.
   *
   * @throws Exception the exception
   */
  @Test
  @InSequence(1)
  @RunAsClient
  public void createTest() throws Exception {
    try {
      Course course = new Course();
      course.setTitle("title_test");
      course.setDescription("description_test");
      course.setTags("tags_test");
      course.setCourseType(CourseType.CLASS);
      course.setLanguage("language_test");
      course.setDifficulty(Difficulty.INTERMEDIATE);
      course.setRating(10);
      course.setResources("resources_test");

      ResteasyClient client = new ResteasyClientBuilder().build();
      ResteasyWebTarget target =
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/courses");
      Response response = target.request().post(Entity.entity(course, MediaType.APPLICATION_JSON));

      Assert.assertEquals(200, response.getStatus());
      System.out.println("POST /courses\n" + response.readEntity(String.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Update test.
   *
   * @throws Exception the exception
   */
  @Test
  @InSequence(2)
  @RunAsClient
  public void updateTest() throws Exception {
    try {
      Course course = new Course();
      course.setId(1L);
      course.setTitle("changed_title_test");
      course.setDescription("changed_description_test");
      course.setTags("changed_tags_test");
      course.setCourseType(CourseType.LECTURE);
      course.setLanguage("changed_language_test");
      course.setDifficulty(Difficulty.ADVANCED);
      course.setRating(1);
      course.setResources("changed_resources_test");

      ResteasyClient client = new ResteasyClientBuilder().build();
      ResteasyWebTarget target =
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/courses/1");
      Response response = target.request().put(Entity.entity(course, MediaType.APPLICATION_JSON));

      Assert.assertEquals(200, response.getStatus());
      System.out.println("PUT /courses\n" + response.readEntity(String.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Find all test.
   *
   * @throws Exception the exception
   */
  @Test
  @InSequence(3)
  @RunAsClient
  public void findAllTest() throws Exception {
    try {
      ResteasyClient client = new ResteasyClientBuilder().build();
      ResteasyWebTarget target =
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/courses");
      Response response = target.request().get();

      Assert.assertEquals(200, response.getStatus());
      System.out.println("GET /courses\n" + response.readEntity(String.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Find by id test.
   *
   * @throws Exception the exception
   */
  @Test
  @InSequence(4)
  @RunAsClient
  public void findByIdTest() throws Exception {
    try {
      ResteasyClient client = new ResteasyClientBuilder().build();
      ResteasyWebTarget target =
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/courses/1");
      Response response = target.request().get();

      Assert.assertEquals(200, response.getStatus());
      System.out.println("GET /courses/1\n" + response.readEntity(String.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Delete test.
   *
   * @throws Exception the exception
   */
  @Test
  @InSequence(5)
  @RunAsClient
  public void deleteTest() throws Exception {
    try {
      ResteasyClient client = new ResteasyClientBuilder().build();
      ResteasyWebTarget target =
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/courses/1");
      Response response = target.request().delete();

      Assert.assertEquals(200, response.getStatus());
      System.out.println("DELETE /courses/1\n" + response.readEntity(String.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
