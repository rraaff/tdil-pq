package com.tdil.tuafesta.roles;

import com.tdil.users.Role;
import com.tdil.users.User;

public class ProfesionalRole extends Role {

	public static final ProfesionalRole INSTANCE = new ProfesionalRole();
	
	private ProfesionalRole() {
		
	}
	
	@Override
	public String getName() {
		return "Profesional";
	}
	
	@Override
	public boolean isValid(User user) {
		if (user == null) {
			return false;
		}
		return user.hasRole(this);
	}

}
