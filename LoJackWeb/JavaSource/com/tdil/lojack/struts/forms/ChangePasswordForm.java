package com.tdil.lojack.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.utils.ThalamusWebUtils;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.ThalamusResponse;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class ChangePasswordForm extends AbstractForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;
	
	public static final String oldPassword_key = "oldPassword";
	public static final String newPassword_key = "newPassword";
	public static final String confirmNewPassword_key = "confirmPassword";
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		oldPassword = null;
		newPassword = null;
		confirmNewPassword = null;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String password) {
		this.oldPassword = password;
	}
	
	public void reset() {
		this.oldPassword = null;
		this.newPassword = null;
		this.confirmNewPassword = null;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String retypePassword) {
		this.confirmNewPassword = retypePassword;
	}
	@Override
	public void init() throws SQLException {
	}
	@Override
	public void initWith(int id) throws SQLException {
	}
	
	
	@Override
	public ValidationError validate() {
		return new ValidationError();
	}
	@Override
	public void save() throws SQLException, ValidationException {
		JSONObject general = getChangePasswordJSON();
		try {
			ThalamusResponse response = ThalamusClientFacade.changePassword(this.getUser().getToken(), general);
			if (response.isBadRequest()) {
				ValidationError validationError = new ValidationError();
				ThalamusWebUtils.addErrorsTo(validationError, response);
				throw new ValidationException(validationError);
			} 
		} catch (HttpStatusException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("ChangePasswordForm.HttpStatusException")));
		} catch (InvalidResponseException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("ChangePasswordForm.InvalidResponseException")));
		} catch (CommunicationException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("ChangePasswordForm.CommunicationException")));
		} catch (UnauthorizedException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("ChangePasswordForm.UnauthorizedException")));
		}
	}
	private JSONObject getChangePasswordJSON() {
		JSONObject result = new JSONObject();
		result.put("oldpassword", this.getOldPassword());
		result.put("newPassword", this.getNewPassword());
		result.put("confirmNewPassword", this.getConfirmNewPassword());
		return result;		
	}
	public WebsiteUser getUser() {
		return user;
	}
	public void setUser(WebsiteUser user) {
		this.user = user;
	}
	public void initWith(WebsiteUser user2) {
		this.reset();
		setUser(user2);
	}
	
}
