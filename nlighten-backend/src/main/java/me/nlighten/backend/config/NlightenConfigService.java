package me.nlighten.backend.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class NlightenConfigService is for loading configuration data from JSON file located in
 * corresponding POJOs classpath, or user defined path by "NGLIGHTEN_CONFIGURATION_FILE_PATH" system
 * variable.
 * 
 * @author Lubo3
 */
@Startup
@Singleton
public class NlightenConfigService implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -4394127830768605021L;

  /** system variable constant */
  private static final String CONFIGURATION_PATH =
      System.getProperty("NGLIGHTEN_CONFIGURATION_FILE_PATH");

  /** The config cache is holding POJOs with configuration data. */
  private Map<String, Object> configCache = new HashMap<String, Object>();

  /** The logger. */
  @Inject
  private Logger logger;

  /**
   * getConfig if possible gets the configuration POJO class from cache, otherwise load them from
   * CONFIGURATION_PATH or classpath.
   *
   * @param <T> the generic type
   * @param clazz the clazz
   * @return the config
   */
  public <T> T getConfig(Class<T> clazz) {
    T result = null;
    if (configCache.containsKey(clazz.getSimpleName())) {
      result = (T) configCache.get(clazz.getSimpleName());
    } else {
      result = loadJsonToPojo(clazz);
    }
    return result;
  }

  /**
   * reloadAllConfigs reloads all POJOS in cache map.
   */
  public void reloadAllConfigs() {
    for (Object object : configCache.values()) {
      loadJsonToPojo(object.getClass());
    }
  }
  
  /**
   * reloadConfig reloads config POJO for provided class. 
   *
   * @param clazz the clazz
   */
  public void reloadConfig(Object clazz){
    loadJsonToPojo((Class) clazz);
  }

  /**
   * loadJsonToPojo loads JSON configuration file to POJO class and put POJO to cache map.
   *
   * @param <T> the generic type
   * @param clazz the clazz
   * @return the t
   */
  private <T> T loadJsonToPojo(Class<T> clazz) {
    T result = null;
    InputStream inputStream = clazz.getResourceAsStream(clazz.getSimpleName() + ".json");
    ObjectMapper mapper = new ObjectMapper();
    try {
      result = clazz.newInstance();
      if (CONFIGURATION_PATH != null) {
        result = mapper.readValue(new File(CONFIGURATION_PATH), clazz);
      } else {
        result = mapper.readValue(inputStream, clazz);
      }
      configCache.put(clazz.getSimpleName(), result);
    } catch (JsonParseException e) {
      logger.error("Error durring parsing config file: " + e.getMessage());
    } catch (JsonMappingException e) {
      logger.error("Error durring mapping config file: " + e.getMessage());
    } catch (IOException e) {
      logger.error("Error durring reading config file: " + e.getMessage());
    } catch (Exception e) {
      logger.error("Error durring reading config file: " + e.getMessage());
    }
    return result;
  }

}
