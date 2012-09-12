package com.tdil.tuafesta.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.actions.RefreshAction;
import com.tdil.tuafesta.struts.forms.ServiceAreaForm;

public class RemoveServiceAreaAction extends RefreshAction {

	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ServiceAreaForm profesionalForm = (ServiceAreaForm)form;
		String index = request.getParameter("index");
		profesionalForm.removeServiceArea(index);
		return mapping.findForward("continue");
	}
}
