package com.tdil.ljpeugeot.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ljpeugeot.services.EmailService;
import com.tdil.ljpeugeot.services.EmailService.GetSMTPProperties;
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
			
			Properties props = GenericTransactionExecutionService.getInstance().execute(new GetSMTPProperties());
			Map<String, String> replacements = new HashMap<String, String>();
			replacements.put(EmailService.DOMAIN_KEY, "CZP075");
			replacements.put(EmailService.FIRST_NAME_KEY, "Fernando");
			replacements.put(EmailService.LAST_NAME_KEY, "Valenzuela");
			replacements.put(EmailService.ACTUAL_KM_KEY, "49000");
			replacements.put(EmailService.LAST_SERVICE_KM_KEY, "40000");
			replacements.put(EmailService.NEXT_SERVICE_KM_KEY, "52000");
			replacements.put(EmailService.NEXT_SERVICE_DATE_KEY, "13-08-2015");
			
			replacements.put(EmailService.DEALER_NAME_KEY, "Galia Automotores S.A.");
			replacements.put(EmailService.DEALER_ADDRESS_KEY, "Calle 49 nro 123");
			replacements.put(EmailService.DEALER_PHONE_KEY, "0221 555-5555");
			replacements.put(EmailService.DEALER_EMAIL_KEY, "galia-lp@galia.com");
			
			List<String> sectionsToRemove = new ArrayList<String>();
			EmailService.sendEmail(from, to, subject, content, replacements, sectionsToRemove, props);
			
			sectionsToRemove.add(EmailService.DEALER_SECTION_KEY);
			EmailService.sendEmail(from, to, subject, content, replacements, sectionsToRemove, props);
			
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("result", "OK");
			writeJsonResponse(result, response);
			return null;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("result", "ERR");
			writeJsonResponse(result, response);
			return null;
		}
	}

		
}
