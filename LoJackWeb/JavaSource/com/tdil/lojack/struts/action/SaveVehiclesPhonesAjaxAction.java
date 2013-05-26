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
import com.tdil.lojack.struts.forms.prevent.SelectVehiclesForm;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxAction;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.validations.ValidationErrors;

public class SaveVehiclesPhonesAjaxAction extends AjaxAction {

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(SaveVehiclesPhonesAjaxAction.class);
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SelectVehiclesForm aForm = (SelectVehiclesForm)form;
		return this.validateAndSave(aForm, request, mapping, response);
	}

	public ActionForward validateAndSave(final SelectVehiclesForm aForm, HttpServletRequest request, ActionMapping mapping, HttpServletResponse response) throws IOException {
		try {
			aForm.savePhones();
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
				String err = ApplicationResources.getMessage(fieldErr.getValue());
				if (StringUtils.isEmpty(err)) {
					result.put(fieldErr.getKey(), fieldErr.getValue());
				} else {
					result.put(fieldErr.getKey(), err);
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
				LOG.error(e.getMessage(), e);
			}
			// todo agregar error
		}
		return null;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(SaveVehiclesPhonesAjaxAction.class);
	}
}
