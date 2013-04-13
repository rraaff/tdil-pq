package com.tdil.tuafesta.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.tuafesta.struts.forms.CategoryNavigationForm;

public class StartCategoryNavigationAction extends AbstractAction {
	
	private static final Logger LOG = LoggerProvider.getLogger(StartCategoryNavigationAction.class);

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		final CategoryNavigationForm abstractForm = (CategoryNavigationForm) form;
		abstractForm.initWith(-1);
		return mapping.findForward("continue");
	}

}
