package com.tdil.users;

import java.util.HashMap;
import java.util.Map;


public abstract class Role {

	private static Map<String, Role> roles = new HashMap<String, Role>();

	public static Role getRole(String rolename) {
		return roles.get(rolename);
	}
	
	public static void addRole(Role role) {
		roles.put(role.getName(), role);
	}
	
	public abstract String getName();
	
	public abstract boolean isValid(User user);
	
	public static final boolean isValid(User user, Role roles[]) {
		for (Role role : roles) {
			if (role.isValid(user)) {
				return true;
			}
		}
		return false;
	}
}
