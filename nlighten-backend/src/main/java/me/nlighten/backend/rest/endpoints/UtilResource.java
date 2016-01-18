package me.nlighten.backend.rest.endpoints;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import me.nlighten.backend.rest.model.ApiStatusDTO;
import me.nlighten.backend.rest.model.enums.ServerStatusEnum;

/**
 * RS end-point for utility services/information
 * 
 * @author Martin
 *
 */
@Path("/util")
public class UtilResource {


  @GET
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/apistatus")
  public ApiStatusDTO getApiStatus() {
    ApiStatusDTO status = new ApiStatusDTO();
    status.setStatus(ServerStatusEnum.OK);
    status.setServerTime(new Date());
    return status;
  }

}
