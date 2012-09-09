package com.tdil.djmag.struts.action;

import javax.servlet.http.HttpServletRequest;

import com.tdil.djmag.struts.forms.NewsletterReportForm;
import com.tdil.struts.actions.AjaxReportAction;
import com.tdil.struts.forms.TransactionalValidationForm;

public class NewsletterReportAction extends AjaxReportAction {

	@Override
	protected TransactionalValidationForm getAjaxForm(HttpServletRequest request) {
		return new NewsletterReportForm();
	}
	
}
