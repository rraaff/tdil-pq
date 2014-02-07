package com.tdil.ljpeugeot.struts.forms;

import org.apache.struts.action.ActionForm;

import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.WebsiteUser;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.ljpeugeot.utils.WebsiteUserUtils;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.SearchForm;

public class SearchUserForm extends ActionForm implements SearchForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5491928444450059587L;
	
	private String dni;
	private String email;
	private WebsiteUser user;
	private ContactData contactData;
	private boolean searchEmpty;

	@Override
	public void search() throws ValidationException {
		WebsiteUser websiteUser = WebsiteUserUtils.getWebSiteUserByDniAndEmail(this.dni, this.email);
		if (websiteUser != null) {
			searchEmpty = false;
			user = websiteUser;
			contactData = PeugeotService.getContactData(websiteUser.getId());
		} else {
			searchEmpty = true;
		}
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public boolean isSearchEmpty() {
		return searchEmpty;
	}

	public void setSearchEmpty(boolean searchEmpty) {
		this.searchEmpty = searchEmpty;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
