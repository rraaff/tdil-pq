package com.tdil.struts.forms;

import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.tdil.struts.ValidationError;

public interface AjaxUploadHandlerForm {

	void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError validationError, Map<String, Object> result);
}
