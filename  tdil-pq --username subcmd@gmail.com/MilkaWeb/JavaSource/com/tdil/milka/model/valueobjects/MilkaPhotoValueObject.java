package com.tdil.milka.model.valueobjects;

import com.tdil.milka.model.MilkaPhoto;

public class MilkaPhotoValueObject extends MilkaPhoto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5159911708712426024L;

	private AuthorValueObject authorValueObject = new AuthorValueObject();

	public AuthorValueObject getAuthorValueObject() {
		if (authorValueObject == null) {
			authorValueObject = new AuthorValueObject();
		}
		return authorValueObject;
	}

	public void setAuthorValueObject(AuthorValueObject authorValueObject) {
		this.authorValueObject = authorValueObject;
	}

	public String getFirstname() {
		return authorValueObject.getFirstname();
	}

	public void setFirstname(String firstname) {
		authorValueObject.setFirstname(firstname);
	}

	public String getLastname() {
		return authorValueObject.getLastname();
	}

	public void setLastname(String lastname) {
		authorValueObject.setLastname(lastname);
	}

	public String getEmail() {
		return authorValueObject.getEmail();
	}

	public void setEmail(String email) {
		authorValueObject.setEmail(email);
	}

}
