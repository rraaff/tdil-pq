package com.tdil.tuafesta.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.tdil.struts.actions.AjaxTransactionalAction;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.struts.forms.ProfesionalForm;

public class AddProfesionalAction extends AjaxTransactionalAction {

	@Override
	protected TransactionalValidationForm getAjaxForm(HttpServletRequest request, ActionForm form) {
		return (ProfesionalForm)form;
	}
}
