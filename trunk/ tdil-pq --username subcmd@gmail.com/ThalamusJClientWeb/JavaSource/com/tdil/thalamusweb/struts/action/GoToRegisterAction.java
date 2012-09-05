package com.tdil.thalamusweb.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.thalamusweb.struts.forms.RegisterForm;

public class GoToRegisterAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RegisterForm registerForm = (RegisterForm)form;
		registerForm.init();
		return mapping.findForward("continue");
	}
	
}
