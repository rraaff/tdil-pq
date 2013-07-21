package com.tdil.lojack.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.utils.ThalamusWebUtils;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.ThalamusResponse;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.json.beans.ResetPasswordBean;
import com.tdil.validations.FieldValidation;

public class ResetPasswordForm extends ThalamusForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String token;
	private int documentType = 1;
	private String username;
	private String password;
	private String retypePassword;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(ResetPasswordForm.class);
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	@Override
	public void basicValidate(ValidationError error) {
		FieldValidation.validateNumber(this.getUsername(), RegisterForm.document_key, 1, Integer.MAX_VALUE, error);
		FieldValidation.validateText(this.getPassword(), RegisterForm.password_key, 20, error);
		if (!org.apache.commons.lang.StringUtils.isEmpty(this.getPassword())) {
			if (!this.getPassword().equals(this.getRetypePassword())) {
				error.setFieldError(RegisterForm.password_key, RegisterForm.PASSWORDS_NOT_EQUAL);
			}
		}
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		username = null;
	}
	public WebsiteUser resetPassword() throws SQLException, ValidationException {
		if (StringUtils.isEmpty(this.getUsername())) {
			throw new ValidationException(new ValidationError("LoginForm.GENERAL_ERROR"));
		}
		try {
			ResetPasswordBean resetPasswordBean = new ResetPasswordBean();
			resetPasswordBean.setToken(this.getToken());
			resetPasswordBean.setPrincipal(this.getDocumentType() + ":" + this.getUsername());
			resetPasswordBean.setPassword(this.getPassword());
			ThalamusResponse response = ThalamusClientBeanFacade.resetPassword(resetPasswordBean);
			if (response.isBadRequest()) {
				ValidationError validationError = new ValidationError();
				ThalamusWebUtils.addErrorsTo(validationError, response);
				throw new ValidationException(validationError);
			} else {
				return LoginForm.login(this.getUsername(), this.getPassword(), "-3", ""); // TODO XXX
			}
		} catch (HttpStatusException e) {
			LOG.error(e.getMessage(), e);
		} catch (InvalidResponseException e) {
			LOG.error(e.getMessage(), e);
		} catch (CommunicationException e) {
			LOG.error(e.getMessage(), e);
		} catch (UnauthorizedException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void reset() {
		this.username = null;
		this.password = null;
		
	}
	public int getDocumentType() {
		return documentType;
	}
	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}
	public String getRetypePassword() {
		return retypePassword;
	}
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}
	
}
