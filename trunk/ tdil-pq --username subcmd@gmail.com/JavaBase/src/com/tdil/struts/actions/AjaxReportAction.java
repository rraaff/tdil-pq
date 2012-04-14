package com.tdil.struts.actions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithValue;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ReportForm;
import com.tdil.struts.forms.TransactionalValidationForm;


public abstract class AjaxReportAction extends AjaxAction implements TransactionalActionWithValue {

	@SuppressWarnings("unchecked")
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		TransactionalValidationForm transactionalForm = getAjaxForm(request);
		
		ValidationError error = transactionalForm.validate();
		if(error.hasError()) {
			this.writeJsonResponse(error.asJson(), response);
			return null;
		} else {
			List<Object> report = (List<Object>)TransactionProvider.executeInTransaction(this, transactionalForm);
			HashMap<String, List<Object>> result = new HashMap<String, List<Object>>();
			result.put("aaData", report);
			this.writeJsonResponse(result, response);
			return null;
		}
	}

	protected abstract TransactionalValidationForm getAjaxForm(HttpServletRequest request);

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ReportForm reportForm = (ReportForm)form;
		return reportForm.search();
	}

}
