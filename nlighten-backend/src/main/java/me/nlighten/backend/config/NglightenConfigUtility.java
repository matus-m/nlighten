package me.nlighten.backend.config;

import java.io.IOException;
import java.io.InputStream;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Startup
@Singleton
public class NglightenConfigUtility {

  /** The logger. */
  private Logger logger = LoggerFactory.getLogger(NglightenConfigUtility.class);

  /** name of configuration file **/
  private static String configFile = "NglightenConfig.json";

  /**
   * Gets the configuration pojo class filled with data from configuration file.
   *
   * @return the NglightenConfig pojo class
   */
  public <T> T getConfig(Class<T> clazz) {
    T result = null;
    InputStream inputStream = clazz.getResourceAsStream(configFile);
    ObjectMapper mapper = new ObjectMapper();
    try {
       result = mapper.readValue(inputStream, clazz);
      /*
       * String name = System.getProperty("jboss.server.base.dir") +
       * "/deployments/nglighten-backend.war/WEB-INF/classes/" +
       * "/me/nlighten/backend/cdi/NglightenConfig.json"; result = mapper.readValue(new File(name),
       * clazz);
       */
    } catch (JsonParseException e) {
      logger.error("Error durring parsing config file: " + e.getMessage());
    } catch (JsonMappingException e) {
      logger.error("Error durring mapping config file: " + e.getMessage());
    } catch (IOException e) {
      logger.error("Error durring reading config file: " + e.getMessage());
    }
    return result;
  }

}
