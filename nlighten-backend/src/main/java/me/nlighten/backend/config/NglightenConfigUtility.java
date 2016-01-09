package me.nlighten.backend.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Startup
@Singleton
public class NglightenConfigUtility implements Serializable {

  private static final long serialVersionUID = -4394127830768605021L;

  private static final String CONFIGURATION_PATH = System
      .getProperty("NGLIGHTEN_CONFIGURATION_FILE_PATH");

  @Inject
  private Logger logger;

  // private Logger logger =
  // LoggerFactory.getLogger(NglightenConfigUtility.class);

  /**
   * Gets the configuration pojo class filled with data from configuration file.
   *
   * @return the NglightenConfig pojo class
   */
  public <T> T getConfig(Class<T> clazz) {
    T result = null;
    InputStream inputStream = clazz.getResourceAsStream(clazz.getSimpleName() + ".json");
    ObjectMapper mapper = new ObjectMapper();
    try {
      if (CONFIGURATION_PATH != null) {
        result = mapper.readValue(new File(CONFIGURATION_PATH), clazz);
      } else {
        result = mapper.readValue(inputStream, clazz);
      }
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
