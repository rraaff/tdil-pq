package com.tdil.milka.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.tdil.milka.struts.forms.ContactForm;
import com.tdil.struts.actions.AjaxTransactionalAction;
import com.tdil.struts.forms.TransactionalValidationForm;

public class ContactAction extends AjaxTransactionalAction {

	@Override
	protected TransactionalValidationForm getAjaxForm(HttpServletRequest request, ActionForm form) {
		return (ContactForm)form;
	}
}
