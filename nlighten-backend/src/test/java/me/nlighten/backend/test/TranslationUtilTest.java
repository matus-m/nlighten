package me.nlighten.backend.test;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import me.nlighten.backend.common.util.I18nUtil;
import me.nlighten.backend.test.resource.TestTranslations;


/**
 * Translation utility testing
 * 
 * @author Martin
 *
 */
public class TranslationUtilTest {

  @Test
  public void simpleTranslationTest() {
    Assert.assertEquals("Übergut koolaine Buchstaben",
        I18nUtil.translate(TestTranslations.SOME_COOL_TEXT, new Locale("de")));
    Assert.assertEquals("Some cool text",
        I18nUtil.translate(TestTranslations.SOME_COOL_TEXT, new Locale("en")));
  }

  @Test
  public void translationWithArgumentsTest() {
    Assert.assertEquals("Nummer ?Streit? 1: 22 String ?Streit? 2: mein Auto", I18nUtil
        .translate(TestTranslations.TEXT_WITH_ARGUMENTS, new Locale("de"), 22, "mein Auto"));
    Assert.assertEquals("Number Argument 1: 341 String Argument 2: Rapture's comming!",
        I18nUtil.translate(TestTranslations.TEXT_WITH_ARGUMENTS, new Locale("en"), 341,
            "Rapture's comming!"));
  }
}
