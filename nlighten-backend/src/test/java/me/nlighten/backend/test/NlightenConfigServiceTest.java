package me.nlighten.backend.test;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import me.nlighten.backend.config.NlightenConfig;
import me.nlighten.backend.config.NlightenConfigService;

/**
 * The Class NlightenConfigServiceTest, for testing NlightenConfigService.
 * 
 * @author Lubo3
 */
@RunWith(Arquillian.class)
public class NlightenConfigServiceTest extends AbstractTest {

  /** The Constant JSON_RELOAD_CONFIG. */
  private static final String JSON_RELOAD_CONFIG = "{\"cdiDebugProperty\":\"reloadConfig\"}";

  /** The Constant JSON_RELOAD_ALL_CONFIGS. */
  private static final String JSON_RELOAD_ALL_CONFIGS =
      "{\"cdiDebugProperty\":\"reloadAllConfigs\"}";

  /** The system variable constant CONFIGURATION_PATH. */
  private static final String CONFIGURATION_PATH = "NGLIGHTEN_CONFIGURATION_FILE_PATH";

  /** The Constant JSON_PATH, where JSON has to be saved. */
  private static final String JSON_PATH = System.getProperty("jboss.server.temp.dir")
      + System.getProperty("file.separatorr") + "NlightenConfig.json";

  /** The logger. */
  @Inject
  private Logger logger;

  /** The nlighten config service. */
  @Inject
  private NlightenConfigService nlightenConfigService;

  /**
   * Gets the config test.
   */
  @Test
  @InSequence(1)
  public void getConfigTest() {
    try {
      NlightenConfig nlightenConfig = nlightenConfigService.getConfig(NlightenConfig.class);
      Assert.assertEquals("this is json property", nlightenConfig.getCdiDebugProperty());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Reload config.
   */
  @Test()
  @InSequence(2)
  public void reloadConfig() {
    try {
      saveJsonFileAndSetSystemProperty(JSON_RELOAD_CONFIG);
      nlightenConfigService.reloadConfig(NlightenConfig.class);

      NlightenConfig nlightenConfig = nlightenConfigService.getConfig(NlightenConfig.class);
      Assert.assertEquals("reloadConfig", nlightenConfig.getCdiDebugProperty());
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  /**
   * Reload all configs test.
   */
  @Test
  @InSequence(3)
  public void reloadAllConfigsTest() {
    try {
      saveJsonFileAndSetSystemProperty(JSON_RELOAD_ALL_CONFIGS);
      nlightenConfigService.reloadAllConfigs();

      NlightenConfig nlightenConfig = nlightenConfigService.getConfig(NlightenConfig.class);
      Assert.assertEquals("reloadAllConfigs", nlightenConfig.getCdiDebugProperty());
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  /**
   * Save json file and set system property.
   *
   * @param json the json
   */
  private void saveJsonFileAndSetSystemProperty(String json) {
    try {
      System.setProperty(CONFIGURATION_PATH, JSON_PATH);
      FileUtils.writeStringToFile(new File(JSON_PATH), json);
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }

}
