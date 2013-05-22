package com.tdil.lojack.struts.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.struts.forms.EditProfileForm;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.lojack.utils.WebsiteUserUtils;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.struts.actions.AjaxAction;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.validations.ValidationErrors;

public class SaveProfileAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AbstractForm aForm = (AbstractForm)form;
		ActionForward answer = this.validateAndSave(aForm, request, mapping, response);
		WebsiteUser user = (WebsiteUser)AbstractAction.getLoggedUser(request);
		user.setModelUser(WebsiteUserUtils.getWebSiteUser(user.getLojackUserId()));
		return answer;
	}

	public ActionForward validateAndSave(final AbstractForm aForm, HttpServletRequest request, ActionMapping mapping, HttpServletResponse response) throws IOException {
		ValidationError error = aForm.validate();
		if(error.hasError()) {
			// return redirectToFailure(error, request, mapping);
			// TODO retornar los errores en formato ajax
		} else {
			try {
				aForm.save();
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("result", "OK");
				EditProfileForm editProfile = (EditProfileForm)aForm;
				result.put("idAvatar", editProfile.getIdAvatar());
				result.put("extAvatar", editProfile.getExtAvatar());
				writeJsonResponse(result, response);
			} catch (ValidationException ex) {
				ValidationError error1 = ex.getError();
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("result", "ERR");
				if (!StringUtils.isEmpty(error1.getGeneralError())) {
					result.put("general", error1.getGeneralError());
				}
				for (Map.Entry<String, String> fieldErr : error1.getFieldErrors().entrySet()) {
					String err = ApplicationResources.getMessage(fieldErr.getValue());
					if (StringUtils.isEmpty(err)) {
						result.put(fieldErr.getKey(), fieldErr.getValue());
					} else {
						result.put(fieldErr.getKey(), err);
					}
				}
				writeJsonResponse(result, response);
				return null;
			} catch (Exception ex) {
				getLog().error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				HashMap<String, Object> result = new HashMap<String, Object>();
				result.put("result", "ERR");
				try {
					writeJsonResponse(result, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// todo agregar error
			}
		}
		return null;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ThalamusSaveAjaxAction.class);
	}
}
