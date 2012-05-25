package com.tdil.milka.model.valueobjects;

import java.io.Serializable;

import com.tdil.milka.model.Author;
import com.tdil.struts.ValidationError;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class AuthorValueObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4644170064163931602L;
	private boolean acceptPolitics = false;
	private String name;
	private String email;
	
	private static final String name_key = "Author.name";
	private static final String email_key = "Author.email";
	private static final String politics_key = "Author.politics";
	
	public void basicValidate(ValidationError error) {
		FieldValidation.validateText(this.getName(), name_key, 150, error);
		FieldValidation.validateEmail(this.getEmail(), email_key, error);
		if (!acceptPolitics) {
			error.setFieldError(politics_key, "MUST_ACCEPT_POLITICS");
		}
		
	}
	
	public boolean isAcceptPolitics() {
		return acceptPolitics;
	}
	public void setAcceptPolitics(boolean acceptPolitics) {
		this.acceptPolitics = acceptPolitics;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Author asAuthor() {
		Author result = new Author();
		result.setName(this.getName());
		result.setEmail(this.getEmail());
		result.setDeleted(0);
		return result;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
