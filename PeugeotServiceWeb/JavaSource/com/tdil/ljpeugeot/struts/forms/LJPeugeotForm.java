package com.tdil.ljpeugeot.struts.forms;

import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.struts.forms.AbstractForm;

public abstract class LJPeugeotForm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 222570557058629836L;
	
	private WebsiteUser sessionUser;

	public WebsiteUser getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(WebsiteUser sessionUser) {
		this.sessionUser = sessionUser;
	}
}
