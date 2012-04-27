package com.tdil.djmag.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.struts.forms.VideoForm;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.validations.ValidationErrors;

public class UploadVideoFrontCoverAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		VideoForm videoForm = (VideoForm)form;
		ValidationError error = new ValidationError();
		try {
			videoForm.uploadFrontCover(error);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} 
		} catch (Exception ex) {
			getLog().error(ex.getMessage(), ex);
			ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
			return redirectToFailure(exError, request, mapping);
		}
		return mapping.findForward("continue");
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(UploadVideoFrontCoverAction.class);
	}

}
