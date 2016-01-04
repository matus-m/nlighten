package me.nlighten.backend.config;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class Test {

  @Inject
  private NglightenConfigUtility nglightenConfigUtility;

  @PostConstruct
  public void init() {
    System.out.println(nglightenConfigUtility.getConfig(NglightenConfig.class).getCdiDebugProperty());
  }
}
