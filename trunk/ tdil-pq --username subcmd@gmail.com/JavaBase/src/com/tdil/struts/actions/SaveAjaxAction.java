package com.tdil.struts.actions;

import java.io.IOException;
import java.sql.SQLException;
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
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;
import com.tdil.validations.ValidationErrors;

public class SaveAjaxAction extends AjaxAction {

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(SaveAjaxAction.class);
	
	private static final class ResetAndInit implements TransactionalAction {
		private final AbstractForm form;

		private ResetAndInit(AbstractForm form) {
			this.form = form;
		}

		public void executeInTransaction() throws SQLException, ValidationException {
			form.reset();
			form.init();
		}
	}
	private static final class Save implements TransactionalAction {
		private final AbstractForm form;

		private Save(AbstractForm form) {
			this.form = form;
		}

		public void executeInTransaction() throws SQLException, ValidationException {
			form.save();
		}
	}
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AbstractForm aForm = (AbstractForm)form;
		return this.validateAndSave(aForm, request, mapping, response);
	}

	public ActionForward validateAndSave(final AbstractForm aForm, HttpServletRequest request, ActionMapping mapping, HttpServletResponse response) throws IOException {
		try {
			HashMap<String, Object> result = new HashMap<String, Object>();
			aForm.takeValuesFrom(request);
			ValidationError error = aForm.validate();
			if(error.hasError()) {
				return addErrorsToResponse(response, error);
			} else {
				GenericTransactionExecutionService.getInstance().execute(new Save(aForm));
				GenericTransactionExecutionService.getInstance().execute(new ResetAndInit(aForm));
				result.put("result", "OK");
				writeJsonResponse(aForm, result, response);
			}
		} catch (ValidationException ex) {
			ValidationError error1 = ex.getError();
			return addErrorsToResponse(response, error1);
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
		}
		return null;
	}

	protected void writeJsonResponse(AbstractForm aForm, HashMap<String, Object> result, HttpServletResponse response) throws IOException {
		super.writeJsonResponse(result, response);
	}

	protected ActionForward addErrorsToResponse(HttpServletResponse response, ValidationError error1)
			throws IOException {
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
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(SaveAjaxAction.class);
	}
}
