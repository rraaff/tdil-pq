package com.tdil.milka.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.struts.forms.LoveHateForm;
import com.tdil.struts.actions.AjaxTransactionalAction;
import com.tdil.struts.forms.TransactionalValidationForm;

public class AddLoveAction extends AjaxTransactionalAction {

	private static Logger getLog() {
		return LoggerProvider.getLogger(AddLoveAction.class);
	}
	
	@Override
	protected TransactionalValidationForm getAjaxForm(HttpServletRequest request, ActionForm form) {
		LoveHateForm loveHateForm = (LoveHateForm)form;
		loveHateForm.setLove(true);
		return loveHateForm;
	}
}
