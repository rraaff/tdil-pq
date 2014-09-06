package com.tdil.ljpeugeot.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ljpeugeot.struts.forms.prevent.SelectVehiclesForm;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.actions.AbstractAction;

public class EditVehiclePhonesAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SelectVehiclesForm aForm = (SelectVehiclesForm)form;
		try {
			String vehicleId = request.getParameter("vehicleId");
			aForm.selectVehicleForPhone((WebsiteUser)getLoggedUser(request), vehicleId);
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
		return LoggerProvider.getLogger(EditVehiclePhonesAction.class);
	}

}