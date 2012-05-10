package com.tdil.milka.struts.action;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.milka.struts.forms.PostItAdministrationForm;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.struts.forms.UploadData;

public class ViewPostItImageAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PostItAdministrationForm noteForm = (PostItAdministrationForm) form;
		UploadData uploadData = noteForm.getCover();
		String contentType = new MimetypesFileTypeMap().getContentType(uploadData.getFileName());
		response.setContentType(contentType);
		response.getOutputStream().write(uploadData.getData());
		return null;
	}

}
