package com.tdil.lojack.struts.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.AsyncJobResponse;
import com.tdil.lojack.gis.model.Light;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.actions.AjaxAction;

public class TunOnLightActionMobile extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
		final int idEntidad = Integer.valueOf(request.getParameter("idEntidad"));
		final int idLuz = Integer.valueOf(request.getParameter("idLuz"));
		HashMap<String, Object> result = new HashMap<String, Object>();
		Light light = new Light();
		light.setIdEntidad(idEntidad);
		light.setIdLuz(idLuz);
		if (!sessionUser.hasJobInProgress(light)) {
			AsyncJobResponse jobResponse = LoJackServicesConnector.activateLight(sessionUser, idEntidad, idLuz);
		}
		return mapping.findForward("continue");
	}

}
