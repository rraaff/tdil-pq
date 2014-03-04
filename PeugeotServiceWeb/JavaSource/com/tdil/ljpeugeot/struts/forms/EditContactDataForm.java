package com.tdil.ljpeugeot.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.ContactDataExample;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;

public class EditContactDataForm extends TransactionalValidationForm {

	private static final long serialVersionUID = 9184547736717742618L;
	private Integer userId;
	
	private ContactData contactData;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(EditContactDataForm.class);
	
	@Override
	public void basicValidate(ValidationError validationError) {
	}

	@Override
	public void validateInTransaction(ValidationError validationError)
			throws SQLException {
	}

	@Override
	public void reset() throws SQLException {
		this.userId = null;
		this.setContactData(new ContactData());
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		this.userId = id;
		ContactDataExample contactDataExample = new ContactDataExample();
		contactDataExample.createCriteria().andIdWebsiteuserEqualTo(id);
		List<ContactData> result = DAOManager.getContactDataDAO().selectContactDataByExample(contactDataExample);
		if (result.size() > 0) {
			this.setContactData(result.get(0));
		} else {
			this.setContactData(new ContactData());
			this.getContactData().setDeleted(0);
		}
	}
	
	@Override
	public void save() throws SQLException, ValidationException {
		if (this.getContactData().getId() != null) {
			DAOManager.getContactDataDAO().updateContactDataByPrimaryKey(this.getContactData());
		} else {
			this.getContactData().setIdWebsiteuser(this.getUserId());
			DAOManager.getContactDataDAO().insertContactData(this.getContactData());
		}
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private ContactData getContactData() {
		return contactData;
	}

	private void setContactData(ContactData contactData) {
		this.contactData = contactData;
	}



	public String getContact1name() {
		return contactData.getContact1name();
	}



	public void setContact1name(String contact1name) {
		contactData.setContact1name(contact1name);
	}



	public String getContact1relation() {
		return contactData.getContact1relation();
	}



	public void setContact1relation(String contact1relation) {
		contactData.setContact1relation(contact1relation);
	}



	public String getContact1phone() {
		return contactData.getContact1phone();
	}



	public void setContact1phone(String contact1phone) {
		contactData.setContact1phone(contact1phone);
	}



	public String getContact1secword() {
		return contactData.getContact1secword();
	}



	public void setContact1secword(String contact1secword) {
		contactData.setContact1secword(contact1secword);
	}



	public String getContact1healthi() {
		return contactData.getContact1healthi();
	}



	public void setContact1healthi(String contact1healthi) {
		contactData.setContact1healthi(contact1healthi);
	}



	public String getContact2name() {
		return contactData.getContact2name();
	}



	public void setContact2name(String contact2name) {
		contactData.setContact2name(contact2name);
	}



	public String getContact2relation() {
		return contactData.getContact2relation();
	}



	public void setContact2relation(String contact2relation) {
		contactData.setContact2relation(contact2relation);
	}



	public String getContact2phone() {
		return contactData.getContact2phone();
	}



	public void setContact2phone(String contact2phone) {
		contactData.setContact2phone(contact2phone);
	}



	public String getContact3name() {
		return contactData.getContact3name();
	}



	public void setContact3name(String contact3name) {
		contactData.setContact3name(contact3name);
	}



	public String getContact3relation() {
		return contactData.getContact3relation();
	}



	public void setContact3relation(String contact3relation) {
		contactData.setContact3relation(contact3relation);
	}



	public String getContact3phone() {
		return contactData.getContact3phone();
	}



	public void setContact3phone(String contact3phone) {
		contactData.setContact3phone(contact3phone);
	}

}
