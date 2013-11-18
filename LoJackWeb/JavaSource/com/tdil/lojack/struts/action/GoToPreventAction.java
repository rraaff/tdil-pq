package com.tdil.lojack.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.rest.ApkLoginCache;
import com.tdil.lojack.struts.forms.prevent.SelectVehiclesForm;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.validations.ValidationErrors;

public class GoToPreventAction extends AbstractAction {
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			request.getSession().setAttribute("USING_APK", "true".equals(request.getParameter("USING_APK")));
			String apkToken = request.getParameter("apkToken");
			WebsiteUser user = ApkLoginCache.get(apkToken);
			int offset = Integer.valueOf(request.getParameter("timezone"));
			user.setTimezoneOffset(offset);
			if (user.isPreventLogged()) {
				firstLoad(user, (SelectVehiclesForm)form);
				return mapping.findForward("continue");
			} else {
				user.reloginPrevent();
				if (user.isPreventLogged()) {
					firstLoad(user, (SelectVehiclesForm)form);
					return mapping.findForward("continue");
				} else {
					return mapping.findForward("failure");
				}
			}
		} catch (Exception ex) {
			getLog().error(ex.getMessage(), ex);
			ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
			return redirectToFailure(exError, request, mapping);
		}
	}
	
	private void firstLoad(WebsiteUser user, SelectVehiclesForm form) throws CommunicationException, HttpStatusException, InvalidResponseException, UnauthorizedException {
		form.initWith(user);
		form.setSelectList(form.getVehicles());
		form.loadAllVehiclesPositions(user);
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(GoToPreventAction.class);
	}
	
}
