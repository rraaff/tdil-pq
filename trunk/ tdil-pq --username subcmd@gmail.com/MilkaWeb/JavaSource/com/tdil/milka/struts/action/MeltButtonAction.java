package com.tdil.milka.struts.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.tdil.milka.struts.forms.MeltButtonForm;
import com.tdil.struts.actions.AjaxTransactionalAction;
import com.tdil.struts.forms.TransactionalValidationForm;

public class MeltButtonAction extends AjaxTransactionalAction {

	@Override
	protected TransactionalValidationForm getAjaxForm(HttpServletRequest request, ActionForm form) {
		String buttonId =request.getParameter("buttonId");
		MeltButtonForm aForm = new MeltButtonForm();
		aForm.setButtonId(buttonId);
		return aForm;
	}
	
	@Override
	protected void addDataToResult(TransactionalValidationForm aForm, Map<String, Object> result) {
		MeltButtonForm meltButtonForm = (MeltButtonForm)aForm;
		result.put("quantity", meltButtonForm.getActualCount().toString());
	}
	
}
