package com.tdil.milka.roles;

import com.tdil.users.Role;
import com.tdil.users.User;

public class Administrator extends Role {

	public static final Administrator INSTANCE = new Administrator();
	
	private Administrator() {
		
	}
	
	@Override
	public String getName() {
		return "Admin";
	}
	
	@Override
	public boolean isValid(User user) {
		if (user == null) {
			return false;
		}
		return user.hasRole(this);
	}

}
