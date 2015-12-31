package me.nlighten.backend.db.model;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import me.nlighten.backend.db.services.NglightenConfigUtility;

@Startup
@Singleton
public class Test {

  @PostConstruct
  public void init() {
    NglightenConfigUtility.getNglightenConfigFile();
  }
}
