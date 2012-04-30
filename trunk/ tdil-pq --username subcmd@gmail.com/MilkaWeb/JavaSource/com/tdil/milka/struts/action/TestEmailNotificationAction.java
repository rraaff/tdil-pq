package com.tdil.milka.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.milka.struts.forms.NotificationEmailForm;
import com.tdil.struts.actions.AbstractAction;

public class TestEmailNotificationAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		NotificationEmailForm noteForm = (NotificationEmailForm) form;
		noteForm.testEmail();
		return mapping.findForward("continue");
	}

}
