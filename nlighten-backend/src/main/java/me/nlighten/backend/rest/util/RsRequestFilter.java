package me.nlighten.backend.rest.util;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;

@Provider
public class RsRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println(requestContext.getMethod() + " " + requestContext.getUriInfo().getRequestUri());
		if ("true".equals(System.getProperty("NLIGHTEN_REST_DEBUG_REQUESTS"))) {
			for (String key : requestContext.getHeaders().keySet())
				System.out.println(requestContext.getHeaders().get(key));
		}
	}

}
