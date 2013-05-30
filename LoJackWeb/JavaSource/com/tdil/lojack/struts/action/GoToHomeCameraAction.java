package com.tdil.lojack.struts.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.struts.forms.CameraForm;
import com.tdil.lojack.utils.LoJackWebUtils;
import com.tdil.lojack.utils.ThalamusWebUtils;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.validations.ValidationErrors;

public class GoToHomeCameraAction extends AbstractAction {
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CameraForm aForm = (CameraForm)form;
		try {
			aForm.reset();
			WebsiteUser user = (WebsiteUser)getLoggedUser(request);
			aForm.initWith(user);
			Cookie cameraCookie = ThalamusWebUtils.getCameraCookie(request);
			if (cameraCookie == null) {
				boolean isMobile = LoJackWebUtils.isMobile(request);
				if (isMobile) {
					aForm.setUseApplet(false);
				} else {
					aForm.setUseApplet(true);
				}
			} else {
				aForm.setUseApplet("applet".equals(cameraCookie.getValue()));
			}
			if (aForm.getAllCameras().size() == 1) {
				return mapping.findForward("viewcamera");
			} else {
				return mapping.findForward("continue");
			}
		} catch (Exception ex) {
			getLog().error(ex.getMessage(), ex);
			ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
			return redirectToFailure(exError, request, mapping);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(GoToHomeCameraAction.class);
	}
	
}
