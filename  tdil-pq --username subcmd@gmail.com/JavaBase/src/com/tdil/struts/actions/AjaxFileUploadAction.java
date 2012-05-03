package com.tdil.struts.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DefaultFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.ValidationError;
import com.tdil.struts.forms.AjaxUploadHandlerForm;


public class AjaxFileUploadAction extends AjaxAction {


	@SuppressWarnings("unchecked")
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// Create a factory for disk-based file items
		FileItemFactory itemFactory = new DefaultFileItemFactory();
		FileUpload fileUpload = new FileUpload(itemFactory);
		List<FileItem> files = fileUpload.parseRequest(request);
		Map<String, FileItem> parsed = new HashMap<String, FileItem>();
		for (FileItem fi : files) {
			parsed.put(fi.getFieldName(), fi);
		}
		// TODO manejo automatica del truncado y todo eso
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		AjaxUploadHandlerForm uploadForm = getUploadForm(request, form);
		ValidationError error = new ValidationError();
		uploadForm.handleAjaxFileUpload(parsed, error, result);
		if(error.hasError()) {
			this.writeJsonResponse(error.asJson(), response);
			return null;
		} else {
			this.writeJsonResponse(result, response);
			return null;
		}
	}

	protected AjaxUploadHandlerForm getUploadForm(HttpServletRequest request, ActionForm form) {
		return (AjaxUploadHandlerForm)form;
	}
}
