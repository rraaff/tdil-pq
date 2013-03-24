package com.tdil.lojack.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.struts.forms.ThalamusForm;
import com.tdil.struts.actions.AbstractAction;

public class ResetFormAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ThalamusForm aForm = (ThalamusForm)form;
		aForm.reset();
		return mapping.findForward("continue");
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ResetFormAction.class);
	}
}
