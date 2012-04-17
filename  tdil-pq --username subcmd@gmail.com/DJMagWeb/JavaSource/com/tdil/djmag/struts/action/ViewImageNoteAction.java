package com.tdil.djmag.struts.action;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.struts.forms.NoteForm;
import com.tdil.djmag.struts.forms.NoteImageBean;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.actions.AbstractAction;

public class ViewImageNoteAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = Integer.valueOf((String)request.getParameter("id"));
		NoteForm noteForm = (NoteForm) form;
		NoteImageBean noteImageBean = noteForm.getNoteImage(id);
		String contentType = new MimetypesFileTypeMap().getContentType(noteImageBean.getUploadData().getFileName());
		response.setContentType(contentType);
		response.getOutputStream().write(noteImageBean.getUploadData().getData());
		return null;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ViewImageNoteAction.class);
	}

}
