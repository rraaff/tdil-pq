package com.tdil.ljpeugeot.struts.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.actions.AjaxAction;

public class SnoozeAdvicesAjaxAction extends AjaxAction {

	public static final String ADVICES_ALREADY_SHOWN = "ADVICES_ALREADY_SHOWN";
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(SnoozeAdvicesAjaxAction.class);
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			request.getSession().setAttribute(DismissAdvicesAjaxAction.ADVICES_ALREADY_SHOWN, Boolean.TRUE);
			result.put("result", "OK");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.put("result", "ERR");
		}
		writeJsonResponse(result, response);
		return null;
	}

}
