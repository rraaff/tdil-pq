package com.tdil.struts.actions;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.users.Role;

public abstract class AjaxAction extends Action {


	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		String contentType = this.getContentType();
		if (contentType != null) {
			response.setContentType(contentType);
		}
	    response.setHeader("cache-control", "no-cache");
		if (!Role.isValid(request, AbstractAction.getPermissions(mapping))) {
			HashMap<String, String> result = new HashMap<String, String>();
			result.put("error", "notLogged");
			writeJsonResponse(result, response);
		}
		return this.basicExecute(mapping, form, request, response);
	}
	
	protected String getContentType() {
		return "application/json; charset=ISO-8859-1";
	}

	protected final void writeJsonResponse(HashMap result, HttpServletResponse response) throws IOException {
		JSONObject json = JSONObject.fromObject(result);
		response.getOutputStream().write(json.toString().getBytes());
	}
	
	protected abstract ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
