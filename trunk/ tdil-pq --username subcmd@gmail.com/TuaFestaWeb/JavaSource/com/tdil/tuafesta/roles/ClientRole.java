package com.tdil.tuafesta.roles;

import com.tdil.users.Role;
import com.tdil.users.User;

public class ClientRole extends Role {

	public static final ClientRole INSTANCE = new ClientRole();
	
	private ClientRole() {
		
	}
	
	@Override
	public String getName() {
		return "Client";
	}
	
	@Override
	public boolean isValid(User user) {
		if (user == null) {
			return false;
		}
		return user.hasRole(this);
	}

}
