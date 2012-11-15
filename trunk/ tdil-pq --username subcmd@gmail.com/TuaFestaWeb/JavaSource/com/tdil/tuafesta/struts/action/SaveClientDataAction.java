package com.tdil.tuafesta.struts.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.tdil.struts.actions.SaveAction;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.tuafesta.struts.forms.ClientHomeForm;

public class SaveClientDataAction extends SaveAction {

	@Override
	protected ActionForward getSucessForward(AbstractForm form, ActionMapping mapping) {
		ClientHomeForm aForm = (ClientHomeForm)form;
		ActionRedirect redirect = new ActionRedirect(mapping.findForward("continue"));
		redirect.addParameter("id", aForm.getClient().getId());
		return redirect;
	}
}
