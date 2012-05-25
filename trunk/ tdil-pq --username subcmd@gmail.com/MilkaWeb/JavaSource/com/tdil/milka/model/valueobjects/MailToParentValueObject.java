package com.tdil.milka.model.valueobjects;

import com.tdil.milka.model.MailToParent;

public class MailToParentValueObject extends MailToParent {

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
	
	public String getStatusRB() {
		return StatusHelper.getStatusRB(this.getDeleted(), this.getApproved());
	}
	
	public String getCreationDateAsString() {
		return CreationDateHelper.getCreationDateAsString(this.getCreationdate());
	}
	
	public void setAuthorValueObject(AuthorValueObject authorValueObject) {
		this.authorValueObject = authorValueObject;
	}

	public String getName() {
		return authorValueObject.getName();
	}

	public void setName(String firstname) {
		authorValueObject.setName(firstname);
	}

	public String getEmail() {
		return authorValueObject.getEmail();
	}

	public void setEmail(String email) {
		authorValueObject.setEmail(email);
	}

}
