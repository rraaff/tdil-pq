package com.tdil.tuafesta.struts.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithValue;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.tuafesta.roles.ProfesionalRole;
import com.tdil.tuafesta.struts.forms.WebsiteLoginForm;
import com.tdil.users.User;

public class WebsiteLoginAction extends Action implements TransactionalActionWithValue {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WebsiteLoginForm login = (WebsiteLoginForm) form;
		try {
			User user = (User) TransactionProvider.executeInTransaction(this, login);
			request.getSession().setAttribute("user", user);
			if (user.hasRole(ProfesionalRole.INSTANCE)) {
				return mapping.findForward("successProfesional");
			} else {
				return mapping.findForward("successClient");
			}
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
			return mapping.findForward("failure");
		}
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		WebsiteLoginForm loginform = (WebsiteLoginForm) form;
		return loginform.executeLogin();
	}
}
