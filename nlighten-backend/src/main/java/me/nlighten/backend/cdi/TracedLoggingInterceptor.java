package me.nlighten.backend.cdi;

import java.lang.reflect.Method;
import java.util.Collection;

import javax.annotation.Priority;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

import me.nlighten.backend.cdi.annotations.Traced;
import me.nlighten.backend.cdi.enums.VerbosityLevel;

/**
 * Interceptor for logging the beginning and end of service method executions.
 * 
 * @author Ronald Kriek
 *
 */
@Traced
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Default
public class TracedLoggingInterceptor {

  @Inject
  private Logger logger;

  @AroundInvoke
  public Object aroundInvoke(InvocationContext ic) throws Exception {
    Object result = null;
    
    Class<?> clazz = ic.getMethod().getDeclaringClass();
    String clazzStr = clazz.getName();
    Method method = ic.getMethod();
    String methodStr = method.getName();
    Class<?> resultClazz = method.getReturnType();
    String resultClazzStr = resultClazz.getSimpleName();
    
    Traced traced = clazz.getAnnotation(Traced.class);
    if(method.isAnnotationPresent(Traced.class)){
      traced = method.getAnnotation(Traced.class);
    }
    VerbosityLevel verbosityLevel = (traced == null) ? VerbosityLevel.BASIC : traced.value(); 
    
    long startTime = System.currentTimeMillis();
    logger.info("[{}] {} [{}]: Started.", clazzStr, methodStr, resultClazzStr);
    try {
      result = ic.proceed();
      
      String totalTime = Long.toString(System.currentTimeMillis() - startTime);
      String resultStr = (result != null) ? resultClazzStr : "null";
      
      if (VerbosityLevel.VERBOSE.equals(verbosityLevel) && isCollection(resultClazz) && result != null) {
        String collectionSize = Integer.toString(((Collection<?>) result).size());
        logger.info("[{}] {} [{}:{}]: Succeeded ({} ms)", clazzStr, methodStr, resultStr,
            collectionSize, totalTime);
      } else {
        logger.info("[{}] {} [{}]: Succeeded ({} ms)", clazzStr, methodStr, resultStr, totalTime);
      }
    } catch (Exception e) {
      String totalTime = Long.toString(System.currentTimeMillis() - startTime);
      logger.info("[{}] {} [{}]: Failed ({} ms)", clazzStr, methodStr, resultClazzStr, totalTime);
      throw e;
    }
    return result;
  }

  public boolean isCollection(Class<?> clazz) {
    return (clazz != null) && Collection.class.isAssignableFrom(clazz);
  }

 
  
}
