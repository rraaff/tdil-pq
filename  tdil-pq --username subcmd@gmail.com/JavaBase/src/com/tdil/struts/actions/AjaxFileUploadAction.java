package com.tdil.struts.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.ValidationError;
import com.tdil.struts.forms.AjaxUploadHandlerForm;


public abstract class AjaxFileUploadAction extends AjaxAction {

	public static final String UPLOAD_NAME = "upload";
	
	@Override
	protected String getContentType() {
		return null;
	}
	
	@Override @SuppressWarnings("unchecked")
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		FileItemFactory factory = new DiskFileItemFactory();
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Parse the request
		List<FileItem> fitems = upload.parseRequest(request);
		Map<String, FileItem> parsed = new HashMap<String, FileItem>();
		for (FileItem fi : fitems) {
			if (fi.isFormField()) {
				parsed.put(fi.getFieldName(), fi);
			} else {
				parsed.put(getUploadFileName(), fi);
			}
		}
		// TODO manejo automatica del truncado y todo eso
		HashMap<String, Object> result = new HashMap<String, Object>();
		AjaxUploadHandlerForm uploadForm = getUploadForm(request, form);
		ValidationError error = new ValidationError();
		handleUpload(uploadForm, parsed, result, error);
		if(error.hasError()) {
			this.writeJsonResponse(error.asJson(), response);
			return null;
		} else {
			this.writeJsonResponse(result, response);
			return null;
		}
	}

	public void handleUpload(AjaxUploadHandlerForm uploadForm,
			Map<String, FileItem> parsed, HashMap<String, Object> result,
			ValidationError error) {
		uploadForm.handleAjaxFileUpload(parsed, error, result);
	}

	public String getUploadFileName() {
		return UPLOAD_NAME;
	}

	protected abstract AjaxUploadHandlerForm getUploadForm(HttpServletRequest request, ActionForm form);
}
