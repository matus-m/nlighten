package me.nlighten.backend.cdi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.nlighten.backend.cdi.enums.VerbosityLevel;

/**
 * Specify verbosity level. Default verbosity is set to BASIC.
 * 
 * @see VerbosityLevel
 * @author Ronald Kriek
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TracedVerbosity {
  VerbosityLevel value() default VerbosityLevel.BASIC;
}
