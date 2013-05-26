package com.tdil.lojack.struts.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.AsyncJobResponse;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.actions.AjaxAction;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.json.beans.ValidatePasswordBean;
import com.tdil.thalamus.client.facade.json.beans.ValidatePasswordResultBean;

public class ActivateAlarmAjaxAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
		final Integer idEntidad = Integer.valueOf(request.getParameter("idEntidad"));
		final String password = request.getParameter("password");
		ValidatePasswordResultBean validatePasswordResultBean = ThalamusClientBeanFacade.validatePassword(sessionUser.getToken(), new ValidatePasswordBean(password));
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (!validatePasswordResultBean.isOk()) { // validar con thalamus
			result.put("result", "ERR_PASS");
		} else {
			Alarm alarm = new Alarm();
			alarm.setIdEntidad(idEntidad);
			if (sessionUser.hasJobInProgress(alarm)) {
				result.put("result", "HAS_JOB");
			} else {
				AsyncJobResponse jobResponse = LoJackServicesConnector.activateAlarm(sessionUser, idEntidad);
				if(jobResponse.getJobId() != 0) {
					result.put("result", "OK");
					result.put("jobId", jobResponse.getJobId());
				} else {
					result.put("result", "ERR");
				}
			}
		}
		writeJsonResponse(result, response);
		return null;
	}

}
