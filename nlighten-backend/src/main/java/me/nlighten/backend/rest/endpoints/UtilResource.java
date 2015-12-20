package me.nlighten.backend.rest.endpoints;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import me.nlighten.backend.rest.model.ApiStatusDTO;
import me.nlighten.backend.rest.model.enums.ServerStatusEnum;

@Path("/")
public class UtilResource {

	@GET
	@POST
	@Produces("application/json")
	@Path("/apistatus")
	public ApiStatusDTO getClichedMessage() {
		ApiStatusDTO status = new ApiStatusDTO();
		status.setStatus(ServerStatusEnum.OK);
		status.setServerTime(new Date());
		return status;
	}

}
