package me.nlighten.backend.common.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class PropertiesUtil {

  public static String returnValueFromPropertyFile(String fileClasspath, String key)
      throws Exception {
    if (fileClasspath == null || fileClasspath.trim().length() == 0 || key == null
        || key.trim().length() == 0) {
      throw new Exception("No property file or key defined");
    }
    BufferedReader br;
    try {
      URL resource = ClassLoader.getSystemClassLoader().getResource(fileClasspath);
      br = new BufferedReader(new FileReader(resource.getFile()));
    } catch (NullPointerException e) {
      throw new Exception("No resource found for: " + fileClasspath, e);
    }
    try {
      for (String line; (line = br.readLine()) != null;) {
        String[] keyValue = line.split("=", 2);
        if (keyValue.length > 0 && keyValue[0].equals(key)) {
          return keyValue.length > 1 ? keyValue[1] : "";
        }
      }
    } catch (IOException e) {
      throw new Exception("Cannot read line from: " + fileClasspath, e);
    }
    throw new Exception("No key found for: " + key);
  }
}
