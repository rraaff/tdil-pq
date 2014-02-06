package com.tdil.ljpeugeot.struts.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.ljpeugeot.model.SystemUser;
import com.tdil.ljpeugeot.services.CCServices;
import com.tdil.ljpeugeot.utils.LJPeugeotConfig;
import com.tdil.ljpeugeot.utils.SystemUserUtils;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;

public class LoginCCForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String username;
	private String password;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(LoginCCForm.class);

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		username = null;
		password = null;
	}

	public SystemUser executeLogin() throws ValidationException {
		if (StringUtils.isEmpty(this.getUsername())) {
			throw new ValidationException(new ValidationError("LoginForm.emptyusername"));
		}
		if (StringUtils.isEmpty(this.getPassword())) {
			throw new ValidationException(new ValidationError("LoginForm.emptypassword"));
		}
		if (LJPeugeotConfig.getFRONT_LOGIN_DELAY() != 0) {
			try {
				Thread.sleep(LJPeugeotConfig.getFRONT_LOGIN_DELAY());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		SystemUser user = SystemUserUtils.getSystemUser(this.username, this.password);
		if (user == null || user.getType() != 1) {
			throw new ValidationException(new ValidationError("LoginForm.invalid"));
		}
		return user;
		
	}


}
