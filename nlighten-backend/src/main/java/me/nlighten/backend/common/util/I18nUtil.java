package me.nlighten.backend.common.util;

import java.io.File;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility providing translation capabilities.
 * 
 * @author Martin
 *
 */
public class I18nUtil {

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
    if (key == null) {
      return null;
    }
    if (locale == null) {
      locale = new Locale("en");
    }
    String enumClassName = key.getClass().getSimpleName();
    ResourceBundle messages = ResourceBundle.getBundle(
        "translations" + File.separator + enumClassName + File.separator + enumClassName, locale);
    if (args == null || args.length == 0) {
      return messages.getString(key.name());
    } else {
      MessageFormat formatter = new MessageFormat("");
      formatter.setLocale(locale);
      formatter.applyPattern(messages.getString(key.name()));
      return formatter.format(args);
    }
  }
}
