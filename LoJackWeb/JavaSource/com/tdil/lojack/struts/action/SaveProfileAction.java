package com.tdil.lojack.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.lojack.utils.WebsiteUserUtils;
import com.tdil.struts.actions.SaveAction;

public class SaveProfileAction extends SaveAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward answer =  super.basicExecute(mapping, form, request, response);
		WebsiteUser user = (WebsiteUser)getLoggedUser(request);
		user.setModelUser(WebsiteUserUtils.getWebSiteUser(user.getLojackUserId()));
		return answer;
	}
}
