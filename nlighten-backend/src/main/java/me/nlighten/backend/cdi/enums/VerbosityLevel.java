package me.nlighten.backend.cdi.enums;

import me.nlighten.backend.cdi.TracedLoggingInterceptor;

/**
 * Verbosity level for TracedLoggingInterceptor.
 * Values:
 *  <li>{@link #BASIC}</li>
 *  <li>{@link #VERBOSE}</li>
 * @see TracedLoggingInterceptor
 * @author Ronald Kriek
 *
 */
public enum VerbosityLevel {
  /** Log: class containing method, method name, return type, execution time and success of execution */
  BASIC,
  /** Log: BASIC verbosity, size of returned collections */
  VERBOSE,
  ;
}
