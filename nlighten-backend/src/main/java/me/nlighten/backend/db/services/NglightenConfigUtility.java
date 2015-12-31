package me.nlighten.backend.db.services;

import java.io.File;
import java.io.IOException;

import me.nlighten.backend.db.model.NglightenConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class NglightenConfigUtility {

  /**
   * Private constructor.
   */
  private NglightenConfigUtility() {}

  /** The NglightenConfigUtility logger. */
  private final static Logger logger = LoggerFactory.getLogger(NglightenConfigUtility.class);
  
  /** path where config file is located **/
  private static String pathToConfigFile;

  /**
   * Gets the NglightenConfig pojo class filled with data from config file.
   *
   * @return the NglightenConfig pojo class
   */
  public static NglightenConfig getNglightenConfigFile() {
    NglightenConfig result = null;
    ObjectMapper mapper = new ObjectMapper();
    try {
      NglightenConfig nglightenConfig =
          mapper.readValue(new File(pathToConfigFile), NglightenConfig.class);
      result = nglightenConfig;
    } catch (JsonParseException e) {
      logger.error("Error durring parsing config file: " + e.getMessage());
    } catch (JsonMappingException e) {
      logger.error("Error durring mapping config file: " + e.getMessage());
    } catch (IOException e) {
      /**
       * here we could read properties from system variable if config file is not available
       */
      logger.error("Error durring reading config file: " + e.getMessage());
    }
    return result;
  }

}
