package com.tdil.users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7573967098098493707L;
	
	private Integer id;
	private Set<String> roles = new HashSet<String>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	public boolean hasRole(Role role) {
		return this.getRoles().contains(role.getName());
	}
	
}
