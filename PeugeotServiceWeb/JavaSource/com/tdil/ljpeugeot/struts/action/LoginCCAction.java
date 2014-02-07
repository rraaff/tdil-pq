package com.tdil.ljpeugeot.struts.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.ljpeugeot.model.SystemUser;
import com.tdil.ljpeugeot.struts.forms.LoginCCForm;
import com.tdil.ljpeugeot.utils.CallCenterUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AbstractAction;

public class LoginCCAction extends AbstractAction  {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			LoginCCForm login = (LoginCCForm) form;
		try {
			SystemUser systemUser = login.executeLogin();
			HttpSession session = request.getSession();
			session.setAttribute("user", new CallCenterUser(systemUser));
			return mapping.findForward("continue");
		} catch (ValidationException e) {
			ValidationError error = e.getError();
			ActionMessages msg = error.asMessages();
			if (msg != null) {
				request.setAttribute("hasError", "true");
				addMessages(request, msg);
			}
			ActionMessages errors = error.asActionsErrors();
			if (errors != null) {
				addErrors(request, errors);
			}
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("result", "ERR");
			return mapping.findForward("failure");
		}
	}

}
