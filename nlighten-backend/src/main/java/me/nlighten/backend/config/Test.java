package me.nlighten.backend.config;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class Test implements Serializable {

	private static final long serialVersionUID = -6349735599054247302L;

	@Inject
	private NglightenConfigUtility nglightenConfigUtility;

	@PostConstruct
	public void init() {
		System.out.println("Loaded property is: " + nglightenConfigUtility.getConfig(NglightenConfig.class).getCdiDebugProperty());
	}
}
