package me.nlighten.backend.test;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import me.nlighten.backend.common.util.I18nUtil;
import me.nlighten.backend.test.resource.FQNFileNameTranslations;
import me.nlighten.backend.test.resource.NoTranslationsEnum;
import me.nlighten.backend.test.resource.TestTranslations;


/**
 * Translation utility testing
 * 
 * @author Martin
 *
 */
public class TranslationUtilTest {

  @Test
  public void simpleTranslationTest() throws Exception {
    // SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS = true
    System.setProperty("SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS", "true");
    Assert.assertEquals("Übergut koolaine Buchstaben",
        I18nUtil.translate(TestTranslations.SOME_COOL_TEXT, new Locale("de")));
    Assert.assertEquals("Some cool text",
        I18nUtil.translate(TestTranslations.SOME_COOL_TEXT, new Locale("en")));
    // SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS = false
    System.setProperty("SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS", "false");
    Assert.assertEquals("Regular translation",
        I18nUtil.translate(FQNFileNameTranslations.REGULAR_TRANSLATION, new Locale("en")));
    Assert.assertEquals("There = are = multiple = equals = signs = ",
        I18nUtil.translate(FQNFileNameTranslations.TRANSLATION_WITH_EQUALS_SIGN, new Locale("en")));

  }

  @Test
  public void translationWithArgumentsTest() throws Exception {
    // SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS = true
    System.setProperty("SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS", "true");
    Assert.assertEquals("Nummer ?Streit? 1: 22 String ?Streit? 2: mein Auto", I18nUtil
        .translate(TestTranslations.TEXT_WITH_ARGUMENTS, new Locale("de"), 22, "mein Auto"));
    Assert.assertEquals("Number Argument 1: 341 String Argument 2: Rapture's comming!",
        I18nUtil.translate(TestTranslations.TEXT_WITH_ARGUMENTS, new Locale("en"), 341,
            "Rapture's comming!"));
    // SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS = false
    System.setProperty("SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS", "false");
    Assert.assertEquals("This is translation with this argument: first argument",
        I18nUtil.translate(FQNFileNameTranslations.ARGUMENT_TRANSLATION, new Locale("en"),
            "first argument"));
  }

  @Test
  public void noTranslationsTest() throws Exception {
    // SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS = true
    System.setProperty("SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS", "true");
    Assert.assertEquals(NoTranslationsEnum.TRANSLATIONS.name(),
        I18nUtil.translate(NoTranslationsEnum.TRANSLATIONS, new Locale("de")));
    // SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS = false
    System.setProperty("SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS", "false");
    Assert.assertEquals(FQNFileNameTranslations.MISSING_TRANSLATION.name(),
        I18nUtil.translate(FQNFileNameTranslations.MISSING_TRANSLATION, new Locale("en")));
  }

}
