package com.tdil.lojack.struts.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.AsyncJobResponse;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.actions.AjaxAction;

public class SendPanicAlarmAjaxAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
		final Integer idEntidad = Integer.valueOf(request.getParameter("idEntidad"));
		AsyncJobResponse jobResponse = LoJackServicesConnector.sendPanicSignal(sessionUser, idEntidad);
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(jobResponse.getJobId() != 0) {
			result.put("result", "OK");
			result.put("jobId", jobResponse.getJobId());
		} else {
			result.put("result", "ERR");
		}
		writeJsonResponse(result, response);
		return null;
	}

}
