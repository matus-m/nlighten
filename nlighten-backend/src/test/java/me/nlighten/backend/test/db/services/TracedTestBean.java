package me.nlighten.backend.test.db.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import me.nlighten.backend.cdi.annotations.Traced;

/**
 * Bean for testing traced interceptor 
 * 
 * @author Ronald Kriek
 *
 */
@Named
@Traced
public class TracedTestBean {
  public String successfulMessage() {
    return "OK";
  }

  public List<String> interceptorArraySize() {
    List<String> myList = new ArrayList<>();
    myList.add("Foo");
    myList.add("Bar");
    return myList;
  }
}
