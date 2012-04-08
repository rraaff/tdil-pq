package com.tdil.users;


public abstract class Role {

	public abstract boolean isValid(SystemUser user);
	
	public static final boolean isValid(SystemUser user, Role roles[]) {
		for (Role role : roles) {
			if (role.isValid(user)) {
				return true;
			}
		}
		return false;
	}
}
