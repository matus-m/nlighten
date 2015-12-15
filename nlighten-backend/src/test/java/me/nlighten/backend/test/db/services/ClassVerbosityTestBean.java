package me.nlighten.backend.test.db.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import me.nlighten.backend.cdi.annotations.Traced;
import me.nlighten.backend.cdi.annotations.TracedVerbosity;
import me.nlighten.backend.cdi.enums.VerbosityLevel;

/**
 * Test verbosity annotation on class
 * 
 * @author Ronald Kriek
 *
 */
@Named
@Traced
@TracedVerbosity(VerbosityLevel.VERBOSE)
public class ClassVerbosityTestBean {
  public List<String> interceptorArraySize() {
    List<String> myList = new ArrayList<>();
    myList.add("Foo");
    myList.add("Bar");
    return myList;
  }
}
