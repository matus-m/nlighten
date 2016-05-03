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

import me.nlighten.backend.db.model.Lesson;
import me.nlighten.backend.rest.util.JaxRsActivator;
import me.nlighten.backend.test.AbstractTest;

/***
 * The Class LessonEndpointTest.**
 * 
 * @author Lubo
 */
@RunWith(Arquillian.class)
public class LessonEndpointTest extends AbstractTest {

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
      Lesson lesson = new Lesson();
      lesson.setTitle("title_test");
      lesson.setDescription("description_test");
      lesson.setOrder(1);

      ResteasyClient client = new ResteasyClientBuilder().build();
      ResteasyWebTarget target =
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/lessons");
      Response response = target.request().post(Entity.entity(lesson, MediaType.APPLICATION_JSON));

      Assert.assertEquals(200, response.getStatus());
      System.out.println("POST /lessons\n" + response.readEntity(String.class));
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
      Lesson lesson = new Lesson();
      lesson.setId(1L);
      lesson.setTitle("changed_title_test");
      lesson.setDescription("changed_description_test");
      lesson.setOrder(2);

      ResteasyClient client = new ResteasyClientBuilder().build();
      ResteasyWebTarget target =
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/lessons/1");
      Response response = target.request().put(Entity.entity(lesson, MediaType.APPLICATION_JSON));

      Assert.assertEquals(200, response.getStatus());
      System.out.println("PUT /lessons\n" + response.readEntity(String.class));
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
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/lessons");
      Response response = target.request().get();

      Assert.assertEquals(200, response.getStatus());
      System.out.println("GET /lessons\n" + response.readEntity(String.class));
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
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/lessons/1");
      Response response = target.request().get();

      Assert.assertEquals(200, response.getStatus());
      System.out.println("GET /lessons/1\n" + response.readEntity(String.class));
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
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/lessons/1");
      Response response = target.request().delete();

      Assert.assertEquals(200, response.getStatus());
      System.out.println("DELETE /lessons/1\n" + response.readEntity(String.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
