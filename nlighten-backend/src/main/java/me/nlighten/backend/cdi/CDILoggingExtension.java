package me.nlighten.backend.cdi;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CDILoggingExtension list all available beans during application startup and log them to console.
 * 
 * @author Lubo3
 */
public class CDILoggingExtension implements Extension {

  final Logger logger = LoggerFactory.getLogger(CDILoggingExtension.class);

  /** system variable constant */
  private static final String ENABLE_NLIGHTEN_CDI_DEBUG_PROPERTY = "NLIGHTEN_ENABLE_CDI_DEBUG";

  /** boolean value to determine if scanning feature should be enabled/disabled */
  private boolean NLIGHTEN_CDI_DEBUG =
      Boolean.valueOf(System.getProperty(ENABLE_NLIGHTEN_CDI_DEBUG_PROPERTY, "false"));

  /**
   * beforeBeanDiscovery is triggered before scanning process. Its purpose is to notify user that
   * scanning process has started.
   */
  void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
    if (NLIGHTEN_CDI_DEBUG) {
      logger.info("Beginning the scanning process for available beans...");
    }
  }

  /** processAnnotatedType scan container for available beans and list them in console */
  void processAnnotatedType(@Observes ProcessAnnotatedType<?> pat) {
    if (NLIGHTEN_CDI_DEBUG) {
      logger.info("- scanned bean type: " + pat.getAnnotatedType().getJavaClass().getName());
    }
  }

  /**
   * afterBeanDiscovery is triggered after scanning process. Its purpose is to notify user that
   * scanning process has finished.
   */
  void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {
    if (NLIGHTEN_CDI_DEBUG) {
      logger.info("Finished the scanning process.");
    }
  }

}
