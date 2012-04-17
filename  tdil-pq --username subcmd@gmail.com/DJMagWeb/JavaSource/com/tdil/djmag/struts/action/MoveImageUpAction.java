package com.tdil.djmag.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.struts.forms.NoteForm;
import com.tdil.struts.actions.AbstractAction;

public class MoveImageUpAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.valueOf((String)request.getParameter("id"));
		NoteForm noteForm = (NoteForm) form;
		noteForm.moveImageUp(id);
		return mapping.findForward("continue");
	}

}
