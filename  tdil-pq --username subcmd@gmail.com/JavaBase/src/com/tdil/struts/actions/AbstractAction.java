package com.tdil.struts.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.users.None;
import com.tdil.users.Role;
import com.tdil.users.User;

public abstract class AbstractAction extends Action {

	@Deprecated
	public static User getLoggedUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return null;
		}
		return user;
	}
	
	public static List<User> getLoggedUsers(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return new ArrayList<User>();
		}
		return Role.getUsers(request);
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!Role.isValid(request, getPermissions(mapping))) {
			getLog().fatal("Invalid action for " + this.getClass().getName());
			return mapping.findForward("invalidAction");
		}
		try {
			return this.basicExecute(mapping, form, request, response);
		} catch (RuntimeException e) {
			if (e instanceof UnAuthorizedAccessException) {
				UnAuthorizedAccessException ex = (UnAuthorizedAccessException)e;
				ex.setAction(this.getClass());
				getLog().error(ex.getMessage(), ex);
				return mapping.findForward("invalidAction");
			} else {
				getLog().error(e.getMessage(), e);
				return mapping.findForward("invalidAction");
			}
		}
	}
	
	public static Role[] getPermissions(ActionMapping mapping) {
		String parameter = mapping.getParameter();
		if (parameter == null || StringUtils.isEmpty(parameter)) {
			return None.INSTANCE_ARR;
		}
		if (parameter.indexOf(',') == -1) {
			return new Role[] {Role.getRole(parameter)};
		}
		StringTokenizer tokenizer = new StringTokenizer(mapping.getParameter(),",");
		List<String> roles = new ArrayList<String>();
		while (tokenizer.hasMoreElements()) {
			String role = tokenizer.nextToken();
			roles.add(role);
		}
		Role result[] = new Role[roles.size()];
		int i = 0;
		for (String role : roles) {
			result[i] = Role.getRole(role); 
		}
		return result;
	}
	
	protected abstract ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;

	protected ActionForward redirectToFailure(ValidationError error, HttpServletRequest request, ActionMapping mapping) {
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
	
	protected boolean isIndexedOperation(final HttpServletRequest request, String context, String key) {
		return isIndexedOperationByKey(request, context, key);
	}
	
	public static boolean isIndexedOperationByKey(final HttpServletRequest request, String context, String key) {
		String op = request.getParameter("indexOperation");
		return isIndexedOperationByKey(op, context, key);
	}

	public static boolean isIndexedOperationByKey(String op, String context, String key) {
		if (op == null || op.length() == 0) {
			return false;
		}
		return op.equals(context + "-" + key);
	}

	protected int getIndexClicked(final HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("indexClicked"));
	}

	protected String getParamClicked(final HttpServletRequest request) {
		return request.getParameter("indexClicked");
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(AbstractAction.class);
	}
}
