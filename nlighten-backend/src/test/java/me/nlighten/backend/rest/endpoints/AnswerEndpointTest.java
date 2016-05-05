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

import me.nlighten.backend.db.model.Answer;
import me.nlighten.backend.rest.util.JaxRsActivator;
import me.nlighten.backend.test.AbstractTest;

/**
 * The Class AnswerEndpointTest.
 * 
 * @author Lubo
 */
@RunWith(Arquillian.class)
public class AnswerEndpointTest extends AbstractTest {

  /** The deployment url. */
  @ArquillianResource
  URL deploymentUrl;

  /** The endpoint url prefix. */
  private static final String RESOURCE_PREFIX =
      JaxRsActivator.class.getAnnotation(ApplicationPath.class).value().substring(1);

  /**
   * Save test.
   *
   * @throws Exception the exception
   */
  @Test
  @InSequence(1)
  @RunAsClient
  public void createTest() throws Exception {
    try {
      Answer answer = new Answer();
      answer.setApproved(true);
      answer.setAuthor("author_test");
      answer.setText("text_test");

      ResteasyClient client = new ResteasyClientBuilder().build();
      ResteasyWebTarget target =
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/answers");
      Response response = target.request().post(Entity.entity(answer, MediaType.APPLICATION_JSON));

      Assert.assertEquals(200, response.getStatus());
      System.out.println("POST /answers\n" + response.readEntity(String.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Merge test.
   *
   * @throws Exception the exception
   */
  @Test
  @InSequence(2)
  @RunAsClient
  public void updateTest() throws Exception {
    try {
      Answer answer = new Answer();
      answer.setId(1L);
      answer.setApproved(false);
      answer.setAuthor("changed_author_test");
      answer.setText("changed_text_test");

      ResteasyClient client = new ResteasyClientBuilder().build();
      ResteasyWebTarget target =
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/answers/1");
      Response response = target.request().put(Entity.entity(answer, MediaType.APPLICATION_JSON));

      Assert.assertEquals(200, response.getStatus());
      System.out.println("PUT /answers\n" + response.readEntity(String.class));
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
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/answers");
      Response response = target.request().get();

      Assert.assertEquals(200, response.getStatus());
      System.out.println("GET /answers\n" + response.readEntity(String.class));
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
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/answers/1");
      Response response = target.request().get();

      Assert.assertEquals(200, response.getStatus());
      System.out.println("GET /answers/1\n" + response.readEntity(String.class));
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
          client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/answers/1");
      Response response = target.request().delete();

      Assert.assertEquals(200, response.getStatus());
      System.out.println("DELETE /answers/1\n" + response.readEntity(String.class));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
