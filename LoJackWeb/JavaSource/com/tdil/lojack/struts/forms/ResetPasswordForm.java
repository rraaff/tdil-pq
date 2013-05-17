package com.tdil.lojack.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;

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

public class ResetPasswordForm extends ThalamusForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String token;
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	@Override
	public void basicValidate(ValidationError validationError) {
		// TODO Auto-generated method stub
		
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
			resetPasswordBean.setPrincipal("1:" + this.getUsername());
			resetPasswordBean.setPassword(this.getPassword());
			ThalamusResponse response = ThalamusClientBeanFacade.resetPassword(resetPasswordBean);
			if (response.isBadRequest()) {
				ValidationError validationError = new ValidationError();
				ThalamusWebUtils.addErrorsTo(validationError, response);
				throw new ValidationException(validationError);
			} else {
				return LoginForm.login(this.getUsername(), this.getPassword(), "-3", ""); // TODO XXX
			}
			// TODO ver como manejo los errores
		} catch (HttpStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnauthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
}
