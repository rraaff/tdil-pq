package com.tdil.lojack.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.actions.AbstractAction;
import com.tdil.struts.actions.AjaxAction;
import com.tdil.users.User;

public class CheckSessionAjaxAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	String contentType = this.getContentType();
	if (contentType != null) {
		response.setContentType(contentType);
	}
    response.setHeader("cache-control", "no-cache");
	User user = AbstractAction.getLoggedUser(request);
	Map<String, String> result = new HashMap<String, String>();
	if (user == null) {
		result.put("sessionExpired", "true");
	} else {
		result.put("sessionExpired", "false");
	}
	JSONObject json = JSONObject.fromObject(result);
	response.getOutputStream().write(json.toString().getBytes());
	return null;
}
}
