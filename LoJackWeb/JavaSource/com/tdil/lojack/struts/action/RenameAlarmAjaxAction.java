package com.tdil.lojack.struts.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.actions.AjaxAction;

public class RenameAlarmAjaxAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
		final String alarmId = request.getParameter("alarmId");
		final String description = request.getParameter("description");
		boolean gisResult = LoJackServicesConnector.renameAlarm(sessionUser.getGuid(), alarmId, description);
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(gisResult) { // TODO ver si necesito manejar porque no lo hizo
			result.put("result", "OK");
		} else {
			result.put("result", "ERR");
		}
		writeJsonResponse(result, response);
		return null;
	}

}
