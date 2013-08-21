package com.tdil.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
	
	@Deprecated
	public static final boolean isValid(User user, Role roles[]) {
		for (Role role : roles) {
			if (role.isValid(user)) {
				return true;
			}
		}
		return false;
	}
	
	public static final boolean isValid(HttpServletRequest request, Role roles[]) {
		for (Role role : roles) {
			User user = role.getUser(request);
			if (role.isValid(user)) {
				return true;
			}
		}
		return false;
	}

	protected User getUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}
		User user = (User)session.getAttribute("user");
		if (user == null) {
			return null;
		}
		return user;
	}

	public static List<User> getUsers(HttpServletRequest request) {
		List<User> result = new ArrayList<User>();
		for (Role role : roles.values()) {
			User user = role.getUser(request);
			if (user != null) {
				result.add(user);
			}
		}
		return result;
	}
}
