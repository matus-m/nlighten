package me.nlighten.backend.config;

import java.io.File;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;

/**
 * The Class TemporaryTest is just temporary test.
 */
@Startup
@Singleton
public class TemporaryTest implements Serializable {

  private static final long serialVersionUID = -6349735599054247302L;

  @Inject
  private NlightenConfigService nglightenConfigService;

  private static final String PATH =
      "\\deployments\\nglighten-backend.war\\WEB-INF\\classes\\me\\nlighten\\backend\\config\\";

  private static final String JSON = "{\"cdiDebugProperty\":\"changed json property\"}";

  @PostConstruct
  public void init() {
    // check if service can load properties from json file
    System.out.println("Loaded property is: "
        + nglightenConfigService.getConfig(NlightenConfig.class).getCdiDebugProperty());

    // check if service can load properties from cache
    System.out.println("Loaded property shoud be same because is loaded from cache: "
        + nglightenConfigService.getConfig(NlightenConfig.class).getCdiDebugProperty());

    // check if service can reload fresh properties from json file
    changeJson();
    nglightenConfigService.reloadAllConfigs();
    System.out.println("Loaded property is different: "
        + nglightenConfigService.getConfig(NlightenConfig.class).getCdiDebugProperty());
  }

  public void changeJson() {
    try {
      FileUtils.writeStringToFile(
          new File(System.getProperty("jboss.server.base.dir") + PATH + "NlightenConfig.json"),
          JSON);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
