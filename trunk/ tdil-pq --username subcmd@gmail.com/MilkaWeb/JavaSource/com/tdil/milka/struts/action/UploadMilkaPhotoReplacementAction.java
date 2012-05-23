package com.tdil.milka.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.tdil.struts.actions.AjaxFileUploadAction;
import com.tdil.struts.forms.AjaxUploadHandlerForm;

public class UploadMilkaPhotoReplacementAction extends AjaxFileUploadAction {

	@Override
	protected AjaxUploadHandlerForm getUploadForm(HttpServletRequest request, ActionForm form) {
		return (AjaxUploadHandlerForm)request.getSession().getAttribute("MilkaPhotoAdministrationForm");
	}

}
