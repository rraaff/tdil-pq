package com.tdil.milka.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.tdil.milka.struts.forms.MailToChildForm;
import com.tdil.struts.actions.AjaxTransactionalAction;
import com.tdil.struts.forms.TransactionalValidationForm;

public class UploadMailToChildAction extends AjaxTransactionalAction {

	@Override
	protected TransactionalValidationForm getAjaxForm(HttpServletRequest request, ActionForm form) {
		return (MailToChildForm)form;
	}
}
