package com.tdil.ljpeugeot.utils;

import com.tdil.ljpeugeot.model.SystemUser;
import com.tdil.ljpeugeot.roles.CallCenterRole;
import com.tdil.users.User;

public class CallCenterUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6323985737282741615L;
	
	private SystemUser systemUser;

	public CallCenterUser(SystemUser systemUser) {
		super();
		setId(systemUser.getId());
		this.systemUser = systemUser;
		this.getRoles().add(CallCenterRole.INSTANCE.getName());
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

}
