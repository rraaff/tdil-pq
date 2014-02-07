package com.tdil.ljpeugeot.roles;

import com.tdil.users.Role;
import com.tdil.users.User;

public class CallCenterRole extends Role {
	
	public static final CallCenterRole INSTANCE = new CallCenterRole();

	@Override
	public String getName() {
		return "CC";
	}

	@Override
	public boolean isValid(User user) {
		if (user == null) {
			return false;
		}
		return user.hasRole(this);
	}

}
