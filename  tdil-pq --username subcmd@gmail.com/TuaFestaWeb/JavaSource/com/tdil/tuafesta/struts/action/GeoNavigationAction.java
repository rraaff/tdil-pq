package com.tdil.tuafesta.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.tuafesta.struts.forms.GeoLevelNavigationForm;

public class GeoNavigationAction extends AbstractAction {
	
	private static final Logger LOG = LoggerProvider.getLogger(GeoNavigationAction.class);

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String level = request.getParameter("level");
		final GeoLevelNavigationForm abstractForm = (GeoLevelNavigationForm) form;
		abstractForm.initWith(Integer.valueOf(id), Integer.valueOf(level));
		return mapping.findForward("continue");
	}

}
