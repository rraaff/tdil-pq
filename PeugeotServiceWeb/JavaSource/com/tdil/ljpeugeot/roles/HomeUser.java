package com.tdil.ljpeugeot.roles;

import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.users.Role;
import com.tdil.users.User;

public class HomeUser extends Role {

	public static final HomeUser INSTANCE = new HomeUser();
	
	@Override
	public String getName() {
		return "HomeUser";
	}

	@Override
	public boolean isValid(User user) {
		if (user == null) {
			return false;
		}
		WebsiteUser casted = (WebsiteUser)user;
		return casted.isHomeUser();
	}

}
