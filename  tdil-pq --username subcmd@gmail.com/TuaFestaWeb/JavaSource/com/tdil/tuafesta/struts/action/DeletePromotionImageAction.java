package com.tdil.tuafesta.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.actions.AbstractAction;
import com.tdil.tuafesta.struts.forms.PromotionForm;

public class DeletePromotionImageAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.valueOf((String)request.getParameter("index"));
		PromotionForm noteForm = (PromotionForm) form;
		noteForm.deleteImage(id);
		return mapping.findForward("continue");
	}

}
