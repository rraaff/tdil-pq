package com.tdil.lojack.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import com.tdil.lojack.utils.WebsiteUser;

@Path("/sayHello")
public class DisplayDetails {
	
	@Context
    HttpServletRequest request;
	
	/*@Context
    HttpSession session;*/
	
	
	public DisplayDetails() {
	}
	
	public HttpSession getSession() {
		return request.getSession(false);
	}
	
	public void validateLogged() {
		HttpSession session = getSession();
		if (session == null) {
			throw new WebApplicationException(401);
		}
		WebsiteUser user = (WebsiteUser)session.getAttribute("user");
		if (user == null) {
			throw new WebApplicationException(401);
		}
	}
/*
	@GET
	public String getHtml(@QueryParam("empname") String empName, @QueryParam("empnumber") int empNumber,
			@QueryParam("empunit") String empUnit, @QueryParam("empmail") String empMail) {
		validateLogged();
		StringBuilder str = new StringBuilder(" Hello, Mr./Ms.  " + empName + "  Welcome to the world of JAX-RS");
		str = str.append("Your Employee Number is:  " + empNumber + "");
		str = str.append("Your Unit is:  " + empUnit + "");
		str = str.append("Your Email ID is:  " + empMail + "");
		return str.toString();
	}
	
	@GET
	@Path("/details")
	public String getHtml1(@QueryParam("empname") String empName) {
		StringBuilder str = new StringBuilder(" Hello, Mr./Ms.  " + empName + "  Details");
		return str.toString();
	}
	
	@GET
	@Path("/email/{userid}")
	public String getHtml2(@PathParam("userid") String id) {
		StringBuilder str = new StringBuilder(" Hello, Mr./Ms.  " + id + "  email XXXX");
		return str.toString();
	}
	
	@GET
	 @Produces( { "application/x-javascript", MediaType.APPLICATION_JSON,
	      MediaType.APPLICATION_XML })
	@Path("/jsonp/orders")
	public JSONWithPadding getOrders(@QueryParam("callback") @DefaultValue("fn") String callback) {
		Alarm a = new Alarm();
		a.setIdEntidad(1);
		a.setDescription("test");
		List<Alarm> result = new ArrayList<Alarm>();
		result.add(a);
	    return new JSONWithPadding(new AlarmCollection(result), callback);
	}

	@GET
	 @Produces( { "application/x-javascript", MediaType.APPLICATION_JSON,
	      MediaType.APPLICATION_XML })
	@Path("/jsonp/login")
	public JSONWithPadding login(@QueryParam("callback") @DefaultValue("fn") String callback, 
			@QueryParam("username") String username, @QueryParam("password") String password) {
		if ("marcos".equals(username)) {
			return new JSONWithPadding(new LoginResult("OK"), callback);
		} else {
			return new JSONWithPadding(new LoginResult("ERR"), callback);
		}
	}
	
	@XmlRootElement
	public static class LoginResult {
		
		private String result;
		
		public LoginResult() {
			// TODO Auto-generated constructor stub
		}
		
		public LoginResult(String result) {
			super();
			this.result = result;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}
		

	}
*/
	
}