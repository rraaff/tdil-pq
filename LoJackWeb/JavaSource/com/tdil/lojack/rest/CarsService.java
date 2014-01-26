package com.tdil.lojack.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.model.VLUData;
import com.tdil.lojack.rest.model.AlarmCollection;
import com.tdil.lojack.rest.model.VLUMessagesCollection;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.lojack.vlu.VLUUtils;
import com.tdil.lojack.vlu.model.VLUDataDTO;

@Path("/cars")
public class CarsService extends AbstractRESTService {
	
	@Context
    HttpServletRequest request;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(CarsService.class);
	
	public CarsService() {
	}
	
	public HttpSession getSession() {
		return request.getSession(false);
	}

	@GET
	@Path("/vluMessagesNoPrevent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response vluMessagesNoPrevent() {
		validateLogged();
		final WebsiteUser user = getUser();
		try {
			List<VLUData> vluDataMessages = VLUUtils.getVLUData(user.getDni());
			List<VLUDataDTO> intermediate = new ArrayList<VLUDataDTO>();
			for (VLUData vluData : vluDataMessages) {
				intermediate.add(new VLUDataDTO(vluData));
			}
			return createResponse(201, new VLUMessagesCollection(intermediate));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return failResponse();
		}
	}


}