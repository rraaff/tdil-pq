package com.tdil.milka.model.valueobjects;

import java.text.SimpleDateFormat;

import com.tdil.milka.model.PostIt;
import com.tdil.struts.resources.ApplicationResources;

public class PostItValueObject extends PostIt {

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
		if (this.getDeleted().equals(1)) {
			return ApplicationResources.getMessage("data.status.deleted");
		} else {
			if (this.getApproved().equals(1)) {
				return ApplicationResources.getMessage("data.status.approved");
			} else {
				return ApplicationResources.getMessage("data.status.pending");
			}
		}
	}
	
	public String getCreationDateAsString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.getCreationdate());
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
