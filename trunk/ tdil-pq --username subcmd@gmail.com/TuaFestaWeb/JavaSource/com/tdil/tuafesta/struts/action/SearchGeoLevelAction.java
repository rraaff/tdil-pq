package com.tdil.tuafesta.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.actions.AbstractAction;
import com.tdil.tuafesta.struts.forms.GeoLevelForm;

public class SearchGeoLevelAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		GeoLevelForm geoLevelForm = (GeoLevelForm)form;
		geoLevelForm.search();
		//TODO ejecutar la busqueda
		return mapping.findForward("continue");
	}

}
