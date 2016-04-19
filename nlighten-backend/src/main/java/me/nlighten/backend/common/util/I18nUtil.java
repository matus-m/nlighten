package me.nlighten.backend.common.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility providing translation capabilities.
 * 
 * @author Martin
 *
 */
public class I18nUtil {

  private static final Logger log = LoggerFactory.getLogger(I18nUtil.class);
  private static Locale defaultLocale = new Locale("en");
  private static MessageFormat formatter = new MessageFormat("");

  /**
   * {@code translate} defaults to {@link I18nUtil#translate(Enum, Locale, Object...)}.
   *
   * @see I18nUtil#translate(Enum, Locale, Object...)
   */
  public static String translate(Enum key, Locale locale) {
    return translate(key, locale, null);
  }

  /**
   * For a translation to be successful, there needs to be a match between Enum class with name
   * [className] and property file in the source folder under the path
   * 'translations/[className]/[className]_[locale].properties'. Keys must be defined exactly like
   * their respective enum values.
   * 
   * @param key enum key
   * @param locale uses 'en' locale if null
   * @param args list of arguments to be applied to required message. Can be empty || null
   * @return translated message. <i>null</i> if key is not specified
   */
  public static String translate(Enum key, Locale locale, Object... args) {
    try {
      if (key == null) {
        return null;
      }
      if (locale == null) {
        locale = defaultLocale;
      }
      String enumClassFQN = key.getClass().getName();
      String value = "";
      if ("true"
          .equals(System.getProperty("SEPARATE_FOLDERS_FOR_NLIGHTEN_TRANSLATIONS").toLowerCase())) {
        ResourceBundle messages = ResourceBundle.getBundle("translations." + enumClassFQN, locale);
        value = messages.getString(key.name());
      } else {
        String propertyFileClasspath = "translations/" + enumClassFQN
            + (locale == null ? ".properties" : "_" + locale.getLanguage() + ".properties");
        value = PropertiesUtil.returnValueFromPropertyFile(propertyFileClasspath, key.name());
      }
      if (args == null || args.length == 0) {
        return value;
      } else {
        formatter.setLocale(locale);
        formatter.applyPattern(value);
        return formatter.format(args);
      }
    } catch (Exception e) {
      log.warn("Resource missing for: " + key.getClass().getName() + "." + key.name() + " Locale: "
          + locale.getLanguage());
      return key.name();
    }
  }


}
