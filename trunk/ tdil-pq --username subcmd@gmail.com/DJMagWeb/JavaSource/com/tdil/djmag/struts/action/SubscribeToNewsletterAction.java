package com.tdil.djmag.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.tdil.djmag.struts.forms.SubscribeToNewsletterForm;
import com.tdil.struts.actions.AjaxTransactionalAction;
import com.tdil.struts.forms.TransactionalValidationForm;

public class SubscribeToNewsletterAction extends AjaxTransactionalAction {

	@Override
	protected TransactionalValidationForm getAjaxForm(HttpServletRequest request, ActionForm form) {
		String email=request.getParameter("email");
		SubscribeToNewsletterForm aForm = new SubscribeToNewsletterForm();
		aForm.setEmail(email);
		return aForm;
	}
	
}
