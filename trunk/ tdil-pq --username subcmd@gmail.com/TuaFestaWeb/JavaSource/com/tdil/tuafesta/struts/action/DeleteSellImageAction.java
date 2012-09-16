package com.tdil.tuafesta.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.tuafesta.struts.forms.EditProfesionalSellForm;

public class DeleteSellImageAction extends AbstractAction {
	
	private static final Logger LOG = LoggerProvider.getLogger(DeleteSellImageAction.class);

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		final EditProfesionalSellForm abstractForm = (EditProfesionalSellForm) form;
		final int index = Integer.parseInt(request.getParameter("index"));
		if (index == 1) {
			abstractForm.setImage1(null);
		}
		if (index == 2) {
			abstractForm.setImage2(null);
		}
		if (index == 3) {
			abstractForm.setImage3(null);
		}
		if (index == 4) {
			abstractForm.setImage4(null);
		}
		if (index == 5) {
			abstractForm.setImage5(null);
		}
		return mapping.findForward("continue");
	}

}
