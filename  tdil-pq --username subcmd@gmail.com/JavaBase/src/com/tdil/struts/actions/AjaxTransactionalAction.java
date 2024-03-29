package com.tdil.struts.actions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithValue;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;


public abstract class AjaxTransactionalAction extends AjaxAction implements TransactionalActionWithValue {

	@SuppressWarnings("unchecked")
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		TransactionalValidationForm transactionalForm = getAjaxForm(request, form);
		
		ValidationError error = transactionalForm.validate();
		if(error.hasError()) {
			this.writeJsonResponse(error.asJson(), response);
			return null;
		} else {
			try {
				HashMap<String, Object> result = (HashMap<String, Object>)TransactionProvider.executeInTransaction(this, transactionalForm);
				this.writeJsonResponse(result, response);
				return null;
			} catch (ValidationException ex) {
				this.writeJsonResponse(error.asJson(), response);
				return null;
			}
		}
	}

	protected abstract TransactionalValidationForm getAjaxForm(HttpServletRequest request, ActionForm form);

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		Map<String, Object> result = new HashMap<String, Object>();
		TransactionalValidationForm aForm = (TransactionalValidationForm)form;
		aForm.save();
		result.put("result", "OK");
		addDataToResult(aForm, result);
		return result;
	}

	protected void addDataToResult(TransactionalValidationForm aForm, Map<String, Object> result) {
		// TODO Auto-generated method stub
	}

}
