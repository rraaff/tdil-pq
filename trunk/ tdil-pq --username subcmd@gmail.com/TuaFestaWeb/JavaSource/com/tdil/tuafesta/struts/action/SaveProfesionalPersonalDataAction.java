package com.tdil.tuafesta.struts.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.tdil.struts.actions.SaveAction;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.tuafesta.struts.forms.EditProfesionalPersonalDataForm;

public class SaveProfesionalPersonalDataAction extends SaveAction {

	@Override
	protected ActionForward getSucessForward(AbstractForm form, ActionMapping mapping) {
		EditProfesionalPersonalDataForm aForm = (EditProfesionalPersonalDataForm)form;
		ActionRedirect redirect = new ActionRedirect(mapping.findForward("continue"));
		redirect.addParameter("id", aForm.getId());
		return redirect;
	}
}
