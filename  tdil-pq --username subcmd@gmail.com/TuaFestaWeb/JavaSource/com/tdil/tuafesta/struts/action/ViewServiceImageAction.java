package com.tdil.tuafesta.struts.action;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.actions.AbstractAction;
import com.tdil.struts.forms.UploadData;
import com.tdil.tuafesta.struts.forms.EditProfesionalSellServiceForm;

public class ViewServiceImageAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EditProfesionalSellServiceForm noteForm = (EditProfesionalSellServiceForm) form;
		int index = Integer.parseInt(request.getParameter("index"));
		UploadData uploadData = null;
		if (index == 0) {
			uploadData = noteForm.getImage1();
		}
		if (index == 1) {
			uploadData = noteForm.getImage2();
		}
		if (index == 2) {
			uploadData = noteForm.getImage3();
		}
		if (index == 3) {
			uploadData = noteForm.getImage4();
		}
		if (index == 4) {
			uploadData = noteForm.getImage5();
		}
		String contentType = new MimetypesFileTypeMap().getContentType(uploadData.getFileName());
		response.setContentType(contentType);
		response.getOutputStream().write(uploadData.getData());
		return null;
	}

}
