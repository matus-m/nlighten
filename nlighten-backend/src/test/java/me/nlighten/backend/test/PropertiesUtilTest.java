package me.nlighten.backend.test;

import org.junit.Assert;
import org.junit.Test;

import me.nlighten.backend.common.util.PropertiesUtil;

public class PropertiesUtilTest {
  @Test
  public void findValuerTest() throws Exception {
    Assert.assertEquals("Regular translation",
        PropertiesUtil.returnValueFromPropertyFile(
            "translations/me.nlighten.backend.test.resource.FQNFileNameTranslations_en.properties",
            "REGULAR_TRANSLATION"));
  }

  @Test(expected = Exception.class)
  public void noValueTest() throws Exception {
    PropertiesUtil.returnValueFromPropertyFile(
        "translations/me.nlighten.backend.test.resource.FQNFileNameTranslations_en.properties",
        "NON_EXISTANT_VALUE");
  }

  @Test(expected = Exception.class)
  public void noFileTest() throws Exception {
    PropertiesUtil.returnValueFromPropertyFile("non-existant_file.properties",
        "NON_EXISTANT_VALUE");
  }

}
