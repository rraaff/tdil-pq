package com.tdil.tuafesta.web;

import com.tdil.tuafesta.model.Client;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.users.User;

public class WebsiteUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6982086024158474048L;
	
	private Client client;
	private Profesional profesional;
	
	public WebsiteUser(Client client) {
		super();
		this.client = client;
	}

	public WebsiteUser(Profesional profesional) {
		super();
		this.profesional = profesional;
	}
	
	public String getName() {
		if (this.getClient() != null) {
			return this.getClient().getFirstname() + " " + this.getClient().getLastname();
		} else {
			return this.getProfesional().getFirstname() + " " + this.getProfesional().getLastname();
		}
	}
	
	public boolean isLogged() {
		return this.getClient() != null || this.getProfesional() != null;
	}
	
	public boolean isClient() {
		return this.getClient() != null;
	}
	
	public boolean isProfesional() {
		return this.getProfesional() != null;
	}

	public Client getClient() {
		return client;
	}

	public Profesional getProfesional() {
		return profesional;
	}

}
