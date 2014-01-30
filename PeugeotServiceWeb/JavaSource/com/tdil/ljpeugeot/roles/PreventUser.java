package com.tdil.ljpeugeot.roles;

import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.users.Role;
import com.tdil.users.User;

public class PreventUser extends Role {

	public static final PreventUser INSTANCE = new PreventUser();
	
	@Override
	public String getName() {
		return "PreventUser";
	}

	@Override
	public boolean isValid(User user) {
		if (user == null) {
			return false;
		}
		WebsiteUser casted = (WebsiteUser)user;
		return casted.isPreventUser();
	}

}
