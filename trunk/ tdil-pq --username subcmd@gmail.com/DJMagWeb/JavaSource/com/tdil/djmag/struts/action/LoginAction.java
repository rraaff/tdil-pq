package com.tdil.djmag.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.struts.forms.LoginForm;

public class LoginAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginForm login = (LoginForm) form;
		/*User user = User.getUserBy(login.getUsername(), login.getPassword());
		if (user != null) {
			request.getSession().setAttribute("user", user);
			return mapping.findForward("continue");			
		} else {
			request.setAttribute("error", "Nombre o contrase�a incorrectos");
			return mapping.findForward("failure");
		}*/
		return mapping.findForward("failure");
	}
}
