package com.tdil.tuafesta.struts.action;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.actions.AbstractAction;
import com.tdil.struts.forms.UploadData;
import com.tdil.tuafesta.struts.forms.PromotionForm;

public class ViewPromotionImageAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PromotionForm noteForm = (PromotionForm) form;
		int index = Integer.parseInt(request.getParameter("index"));
		UploadData uploadData = noteForm.getPhotos().get(index).getUploadData();
		String contentType = new MimetypesFileTypeMap().getContentType(uploadData.getFileName());
		response.setContentType(contentType);
		response.getOutputStream().write(uploadData.getData());
		return null;
	}

}
