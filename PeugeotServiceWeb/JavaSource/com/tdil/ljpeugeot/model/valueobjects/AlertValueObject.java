package com.tdil.ljpeugeot.model.valueobjects;

import com.tdil.ljpeugeot.model.Alert;
import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.WebsiteUser;

public class AlertValueObject {

	private Alert alert;
	private WebsiteUser user;
	private ContactData contactData;
	
	public AlertValueObject(Alert alert, WebsiteUser user) {
		super();
		this.alert = alert;
		this.user = user;
	}
	
	public AlertValueObject(Alert alert, WebsiteUser user, ContactData contactData) {
		super();
		this.alert = alert;
		this.user = user;
		this.contactData = contactData;
	}

	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	public WebsiteUser getUser() {
		return user;
	}
	public void setUser(WebsiteUser user) {
		this.user = user;
	}

	public ContactData getContactData() {
		return contactData;
	}

	public void setContactData(ContactData contactData) {
		this.contactData = contactData;
	}
	
	
}
