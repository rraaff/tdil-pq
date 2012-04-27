package com.tdil.djmag.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.struts.forms.NoteForm;
import com.tdil.djmag.struts.forms.NoteImageBean;
import com.tdil.struts.actions.AbstractAction;

public class ViewImageNoteAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		NoteForm noteForm = (NoteForm) form;
		String contentType = null;
		byte data[];
		if ("cover".equals(type)) {
			contentType = noteForm.getFrontCoverImage().getContentType();
			data = noteForm.getFrontCoverImage().getData();
		} else {
			if ("agenda".equals(type)) {
				contentType = noteForm.getAgendaImage().getContentType();
				data = noteForm.getAgendaImage().getData();
			} else {
				if ("newsCover".equals(type)) {
					contentType = noteForm.getLastNewsCoverImage().getContentType();
					data = noteForm.getLastNewsCoverImage().getData();
				} else {
					if ("newsThumb".equals(type)) {
						contentType = noteForm.getLastNewsThumbImage().getContentType();
						data = noteForm.getLastNewsThumbImage().getData();
					} else {
						int id = Integer.valueOf((String)request.getParameter("id"));
						NoteImageBean noteImageBean = noteForm.getNoteImage(id);
						contentType = noteImageBean.getUploadData().getContentType();
						data = noteImageBean.getUploadData().getData();
					}
				}
			}
		}
		response.setContentType(contentType);
		response.getOutputStream().write(data);
		return null;
	}

}
