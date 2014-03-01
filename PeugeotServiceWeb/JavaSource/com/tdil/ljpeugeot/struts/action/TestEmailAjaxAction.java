package com.tdil.ljpeugeot.struts.action;

import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ljpeugeot.services.EmailService;
import com.tdil.ljpeugeot.services.EmailService.GetSMTPProperties;
import com.tdil.ljpeugeot.utils.LJPeugeotConfig;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.actions.AjaxAction;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class TestEmailAjaxAction extends AjaxAction {

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(TestEmailAjaxAction.class);
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String to = request.getParameter("to");
			String from = request.getParameter("from");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			content = StringUtils.replace(content, EmailService.SERVER_NAME_KEY, LJPeugeotConfig.getFRONT_SERVER());
			
			Properties props = GenericTransactionExecutionService.getInstance().execute(new GetSMTPProperties());
			com.tdil.utils.EmailUtils.sendEmail(content, to, from, subject, props);
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("result", "OK");
			writeJsonResponse(result, response);
			return null;
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("result", "ERR");
			writeJsonResponse(result, response);
			return null;
		}
	}

		
	private static Logger getLog() {
		return LoggerProvider.getLogger(SaveVehiclesPhonesAjaxAction.class);
	}
}
