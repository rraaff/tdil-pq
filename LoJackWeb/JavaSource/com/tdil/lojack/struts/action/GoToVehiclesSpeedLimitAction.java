package com.tdil.lojack.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.struts.forms.prevent.VehiclesSpeedLimitForm;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.validations.ValidationErrors;

public class GoToVehiclesSpeedLimitAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		VehiclesSpeedLimitForm aForm = (VehiclesSpeedLimitForm)form;
		try {
			aForm.reset();
			WebsiteUser user = (WebsiteUser)getLoggedUser(request);
			aForm.initWith(user);
			return mapping.findForward("continue");
		} catch (Exception ex) {
			response.setStatus(500);
			getLog().error(ex.getMessage(), ex);
			/*ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
			return redirectToFailure(exError, request, mapping);*/
			return null;
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(GoToVehiclesSpeedLimitAction.class);
	}

}
