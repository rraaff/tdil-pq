package com.tdil.ljpeugeot.struts.action;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.actions.AjaxAction;

public class AddAlertAjaxAction extends AjaxAction {

	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(AddAlertAjaxAction.class);
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
		final String lat = request.getParameter("lat");
		final String lon = request.getParameter("lon");
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			PeugeotService.addAlert(sessionUser.getModelUser().getId(), "", new BigDecimal(lat), new BigDecimal(lon));
			result.put("result", "OK");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.put("result", "ERR");
		}
		writeJsonResponse(result, response);
		return null;
	}

}
