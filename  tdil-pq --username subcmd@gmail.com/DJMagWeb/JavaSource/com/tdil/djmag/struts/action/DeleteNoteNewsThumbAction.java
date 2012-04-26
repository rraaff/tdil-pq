package com.tdil.djmag.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.struts.forms.NoteForm;
import com.tdil.struts.actions.AbstractAction;

public class DeleteNoteNewsThumbAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		NoteForm noteForm = (NoteForm) form;
		noteForm.deleteNewsThumb();
		return mapping.findForward("continue");
	}

}
