package me.nlighten.backend.cdi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

import me.nlighten.backend.cdi.enums.VerbosityLevel;

/**
 * Log bean message execution. Default verbosity is set to BASIC.
 * 
 * @see VerbosityLevel
 * @author Ronald Kriek
 *
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Traced {
  VerbosityLevel value() default VerbosityLevel.BASIC;
}
