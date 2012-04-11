package com.tdil.struts.actions;

import com.tdil.users.User;


public class UnAuthorizedAccessException extends RuntimeException {

	private static final long serialVersionUID = -3711192109876620191L;

	private User user;
	private Class action;

	public UnAuthorizedAccessException(User user) {
		super();
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Class getAction() {
		return action;
	}

	public void setAction(Class anAction) {
		this.action = anAction;
	}

}
