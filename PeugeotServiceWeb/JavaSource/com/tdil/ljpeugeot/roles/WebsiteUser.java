package com.tdil.ljpeugeot.roles;

import com.tdil.users.Role;
import com.tdil.users.User;

public class WebsiteUser extends Role {

	public static final WebsiteUser INSTANCE = new WebsiteUser();
	
	@Override
	public String getName() {
		return "WebsiteUser";
	}

	@Override
	public boolean isValid(User user) {
		if (user == null) {
			return false;
		}
		return user.hasRole(this);
	}

}
