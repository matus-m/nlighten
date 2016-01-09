package me.nlighten.backend.test;

import static org.junit.Assert.assertEquals;

import java.io.FileWriter;

import javax.inject.Inject;

import me.nlighten.backend.config.NglightenConfig;
import me.nlighten.backend.config.NglightenConfigUtility;

import org.jboss.arquillian.junit.Arquillian;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class NglightenConfigUtilityTest {

  private static final String CONFIGURATION_PATH = "NGLIGHTEN_CONFIGURATION_FILE_PATH";

  @Inject
  private NglightenConfigUtility nglightenConfigUtility;

  @Test
  public void getConfigTest() {
    try {
      String fileSeparator = System.getProperty("file.separator");
      JSONObject nglightenConfiguration = new JSONObject();
      nglightenConfiguration.put("cdiDebugProperty", "property-value");
      FileWriter file =
          new FileWriter(System.getProperty("user.home") + fileSeparator + "NglightenConfig.json");
      file.write(nglightenConfiguration.toJSONString());
      file.flush();
      file.close();
      System.setProperty(CONFIGURATION_PATH, System.getProperty("user.home") + fileSeparator
          + "NglightenConfig.json");

      NglightenConfig loadedPojo = nglightenConfigUtility.getConfig(NglightenConfig.class);
      String cdiDebugProperty = loadedPojo.getCdiDebugProperty();
      assertEquals("property-value", cdiDebugProperty);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
