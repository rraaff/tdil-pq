package com.tdil.milka.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.milka.struts.forms.VideoForm;
import com.tdil.struts.actions.AbstractAction;

public class DeleteVideoAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.valueOf((String)request.getParameter("index"));
		VideoForm noteForm = (VideoForm) form;
		noteForm.deleteImage(id);
		return mapping.findForward("continue");
	}

}
