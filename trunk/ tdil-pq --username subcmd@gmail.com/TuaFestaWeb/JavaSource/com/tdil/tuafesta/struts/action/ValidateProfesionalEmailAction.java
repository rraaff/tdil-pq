package com.tdil.tuafesta.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.actions.SaveAction;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.tuafesta.struts.forms.ValidateProfesionalEmailForm;

public class ValidateProfesionalEmailAction extends SaveAction {

	@Override
	public ActionForward validateAndSave(AbstractForm form, HttpServletRequest request, ActionMapping mapping) {
		final ValidateProfesionalEmailForm validateProfesionalEmailForm = (ValidateProfesionalEmailForm)form;
		validateProfesionalEmailForm.setObjectId(Integer.valueOf(request.getParameter("id")));
		validateProfesionalEmailForm.setVerifemail(request.getParameter("verifemail"));
		return super.validateAndSave(validateProfesionalEmailForm, request, mapping);
	}
	
}
