package com.tdil.milka.struts.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tdil.milka.struts.forms.MeltButtonForm;
import com.tdil.struts.actions.AjaxTransactionalAction;
import com.tdil.struts.forms.TransactionalValidationForm;

public class MeltButtonAction extends AjaxTransactionalAction {

	@Override
	protected TransactionalValidationForm getAjaxForm(HttpServletRequest request) {
		String buttonType =request.getParameter("buttonType");
		String buttonId =request.getParameter("buttonId");
		MeltButtonForm aForm = new MeltButtonForm();
		aForm.setButtonType(buttonType);
		aForm.setButtonId(buttonId);
		return aForm;
	}
	
	@Override
	protected void addDataToResult(TransactionalValidationForm aForm, Map<String, Object> result) {
		MeltButtonForm meltButtonForm = (MeltButtonForm)aForm;
		result.put("quantity", meltButtonForm.getActualCount().toString());
	}
	
}
