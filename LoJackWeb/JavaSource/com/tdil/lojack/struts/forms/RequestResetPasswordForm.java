package com.tdil.lojack.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.json.beans.RequestResetPasswordBean;
import com.tdil.thalamus.client.facade.json.beans.RequestResetPasswordResult;

public class RequestResetPasswordForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private int documentType = 1;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		documentType = 1;
		username = null;
	}
	public int resetPassword() throws SQLException, ValidationException {
		if (StringUtils.isEmpty(this.getUsername())) {
			throw new ValidationException(new ValidationError("LoginForm.GENERAL_ERROR"));
		}
		try {
			RequestResetPasswordBean requestResetPasswordBean = new RequestResetPasswordBean();
			requestResetPasswordBean.setPrincipal(this.getDocumentType() + ":" + this.getUsername());
			
			RequestResetPasswordResult result = ThalamusClientBeanFacade.requestResetPassword(requestResetPasswordBean);
			String tokenDev = result.getTokenDev();
			if (tokenDev != null) {
				System.out.println(tokenDev);
			}
			return 201;
		} catch (HttpStatusException e) {
			return e.getStatus();
		} catch (InvalidResponseException e) {
			return -1;
		} catch (CommunicationException e) {
			return -1;
		} catch (UnauthorizedException e) {
			return -1;
		}
	}
	public int getDocumentType() {
		return documentType;
	}
	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}
	
}
