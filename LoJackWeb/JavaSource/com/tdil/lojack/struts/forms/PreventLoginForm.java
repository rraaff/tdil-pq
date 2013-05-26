package com.tdil.lojack.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationException;

public class PreventLoginForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String password;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(PreventLoginForm.class);

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		password = null;
	}

	public Object executeLogin() throws SQLException, ValidationException {
		return null;
		/*try {
			if (LoJackConfig.getFRONT_LOGIN_DELAY() != 0) {
				try {
					Thread.sleep(LoJackConfig.getFRONT_LOGIN_DELAY());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return login(this.getDocumentType() + ":" + this.getUsername(), this.getPassword(), this.getTimezoneOffset(), this.getTimezoneName());
		} catch (HttpStatusException e) {
			if (e.getStatus() == HttpStatus.SC_UNAUTHORIZED) {
				throw new ValidationException(new ValidationError("LoginForm.HttpStatusException.401"));
			} else {
				throw new ValidationException(new ValidationError("LoginForm.HttpStatusException"));
			}
		} catch (InvalidResponseException e) {
			throw new ValidationException(new ValidationError("LoginForm.InvalidResponseException"));
		} catch (CommunicationException e) {
			throw new ValidationException(new ValidationError("LoginForm.CommunicationException"));
		} catch (UnauthorizedException e) {
			throw new ValidationException(new ValidationError("LoginForm.UnauthorizedException"));
		}*/
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
