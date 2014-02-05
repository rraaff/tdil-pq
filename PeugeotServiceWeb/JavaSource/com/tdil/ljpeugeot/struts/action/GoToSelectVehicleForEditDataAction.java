package com.tdil.ljpeugeot.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ljpeugeot.struts.forms.prevent.EditVehicleDataForm;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.actions.AbstractAction;

public class GoToSelectVehicleForEditDataAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EditVehicleDataForm aForm = (EditVehicleDataForm)form;
		try {
			aForm.reset();
			WebsiteUser user = (WebsiteUser)getLoggedUser(request);
			if (!user.isPreventLogged()) {
				user.reloginPrevent();
			}
			aForm.initWith(user);
			if (aForm.hasOnlyOne()) {
				return mapping.findForward("onlyOne");	
			} else {
				return mapping.findForward("continue");
			}
		} catch (Exception ex) {
			response.setStatus(500);
			getLog().error(ex.getMessage(), ex);
			/*ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
			return redirectToFailure(exError, request, mapping);*/
			return null;
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(GoToSelectVehicleForEditDataAction.class);
	}

}
