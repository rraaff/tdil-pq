package com.tdil.lojack.struts.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.struts.forms.RegisterForm;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxAction;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.validations.ValidationErrors;

public class ThalamusRegisterAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RegisterForm aForm = (RegisterForm)form;
		return this.validateAndSave(aForm, request, mapping, response);
	}

	public ActionForward validateAndSave(final RegisterForm aForm, HttpServletRequest request, ActionMapping mapping, HttpServletResponse response) throws IOException {
		ValidationError error = aForm.validate();
		if(error.hasError()) {
			// return redirectToFailure(error, request, mapping);
			// TODO retornar los errores en formato ajax
		} else {
			try {
				WebsiteUser user = aForm.register();
				request.getSession().setAttribute("user", user);
				aForm.reset();
				aForm.init();
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("result", "OK");
				writeJsonResponse(result, response);
			} catch (ValidationException ex) {
				ValidationError error1 = ex.getError();
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("result", "ERR");
				if (!StringUtils.isEmpty(error1.getGeneralError())) {
					result.put("general", error1.getGeneralError());
				}
				for (Map.Entry<String, String> fieldErr : error1.getFieldErrors().entrySet()) {
					String keyToStore = fieldErr.getKey();
					if (keyToStore.equals("credential.principal")) {
						keyToStore = aForm.getFieldNameForPrincipal();
					}
					String err = ApplicationResources.getMessage(fieldErr.getValue());
					if (StringUtils.isEmpty(err)) {
						result.put(keyToStore, fieldErr.getValue());
					} else {
						result.put(keyToStore, err);
					}
				}
				writeJsonResponse(result, response);
				return null;
			} catch (Exception ex) {
				getLog().error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("result", "ERR");
				try {
					writeJsonResponse(result, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// todo agregar error
			}
		}
		return null;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ThalamusRegisterAction.class);
	}
}
