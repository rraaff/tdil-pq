package com.tdil.djmag.struts.action;

import javax.servlet.http.HttpServletRequest;

import com.tdil.djmag.struts.forms.SubscribeToNewsletterForm;
import com.tdil.struts.actions.AjaxTransactionalAction;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.users.None;
import com.tdil.users.Role;

public class SubscribeToNewsletterAction extends AjaxTransactionalAction {

	private static final Role[] roles = new Role[] {None.INSTANCE};

	
	@Override
	public boolean userMustBeLogged() {
		return false;
	}
	
	@Override
	protected Role[] getAllowedRoles() {
		return roles;
	}
	
	@Override
	protected TransactionalValidationForm getAjaxForm(HttpServletRequest request) {
		String email=request.getParameter("email");
		SubscribeToNewsletterForm aForm = new SubscribeToNewsletterForm();
		aForm.setEmail(email);
		return aForm;
	}
	
}
