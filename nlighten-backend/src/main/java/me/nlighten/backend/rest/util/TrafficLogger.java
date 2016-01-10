package me.nlighten.backend.rest.util;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter used for RS requests/response logging
 * 
 * @author Martin
 *
 */
@Provider
public class TrafficLogger implements ContainerRequestFilter, ContainerResponseFilter {

  final Logger logger = LoggerFactory.getLogger(TrafficLogger.class);

  @Context
  ResourceInfo resourceInfo;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    logger.info("Processing request: " + requestContext.getMethod() + " "
        + requestContext.getUriInfo().getRequestUri());
    Class<?> resourceClass = resourceInfo.getResourceClass();
    Method resourceMethod = resourceInfo.getResourceMethod();
    logger
        .info("Invoked method: " + resourceClass.getName() + "." + resourceMethod.getName() + "()");
    if ("true".equals(System.getProperty("NLIGHTEN_REST_DEBUG_REQUESTS"))) {
      for (String key : requestContext.getHeaders().keySet())
        logger.info(key + ": " + requestContext.getHeaders().getFirst(key));
    }
  }

  @Override
  public void filter(ContainerRequestContext requestContext,
      ContainerResponseContext responseContext) {
    logger.info("Response status: " + Integer.toString(responseContext.getStatus()));

  }

}
