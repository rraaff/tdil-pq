package com.tdil.milka.model.valueobjects;

import java.io.Serializable;

import com.tdil.milka.model.Author;

public class AuthorValueObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4644170064163931602L;
	private boolean acceptPolitics = false;
	private String firstname;
	private String lastname;
	private String email;
	
	public boolean isAcceptPolitics() {
		return acceptPolitics;
	}
	public void setAcceptPolitics(boolean acceptPolitics) {
		this.acceptPolitics = acceptPolitics;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Author asAuthor() {
		Author result = new Author();
		result.setFirstname(this.getFirstname());
		result.setLastname(this.getLastname());
		result.setEmail(this.getEmail());
		result.setDeleted(0);
		return result;
	}

}
