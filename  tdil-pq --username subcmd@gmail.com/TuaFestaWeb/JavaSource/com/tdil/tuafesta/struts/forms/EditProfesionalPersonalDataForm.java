package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalChange;
import com.tdil.tuafesta.utils.SystemPropertiesKeys;
import com.tdil.tuafesta.web.SystemPropertyUtils;
import com.tdil.utils.DateUtils;
import com.tdil.utils.StringUtils;
import com.tdil.validations.FieldValidation;

public class EditProfesionalPersonalDataForm extends TransactionalValidationForm implements EditProfesionalDataForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	
	private String firstname;
	private String lastname;
	private String sex;
	private String birthdate;
	
	private String phoneAreaCode;
	private String phoneNumber;
	private String phoneExtension;
	private String phoneType;
	
	private String email;
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		objectId = id;
		Profesional profesional = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(id);
		ProfesionalChange profesionalChange = DAOManager.getProfesionalChangeDAO().selectProfesionalChangeByPrimaryKey(profesional.getIdProfesionalChange());
		setFirstname(com.tdil.utils.StringUtils.nvl(profesionalChange.getFirstname(), profesional.getFirstname()));
		setLastname(com.tdil.utils.StringUtils.nvl(profesionalChange.getLastname(), profesional.getLastname()));
		setSex(com.tdil.utils.StringUtils.nvl(profesionalChange.getSex(), profesional.getSex()));
		setBirthdate(com.tdil.utils.StringUtils.nvl(DateUtils.formatDateSp(profesionalChange.getBirthdate()), DateUtils.formatDateSp(profesional.getBirthdate())));
		setPhoneAreaCode(com.tdil.utils.StringUtils.nvl(profesionalChange.getPhoneareacode(), profesional.getPhoneareacode()));
		setPhoneNumber(com.tdil.utils.StringUtils.nvl(profesionalChange.getPhonenumber(), profesional.getPhonenumber()));
		setPhoneExtension(com.tdil.utils.StringUtils.nvl(profesionalChange.getPhoneextension(), profesional.getPhoneextension()));
		setPhoneType(com.tdil.utils.StringUtils.nvl(profesionalChange.getPhonetype(), profesional.getPhonetype()));
		setEmail(profesional.getEmail());
	}
	
	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getFirstname(), ProfesionalForm.firstname_key, 100, false, validationError);
		FieldValidation.validateText(this.getLastname(), ProfesionalForm.lastname_key, 100, false, validationError);
		FieldValidation.validateText(this.getSex(), ProfesionalForm.sex_key, 1, false, validationError);
		FieldValidation.validateText(this.getPhoneAreaCode(), ProfesionalForm.phoneareacode_key, 15, false, validationError);
		FieldValidation.validateText(this.getPhoneNumber(), ProfesionalForm.phonenumber_key, 15, false, validationError);
		FieldValidation.validateText(this.getPhoneExtension(), ProfesionalForm.phoneextension_key, 10, false, validationError);
		FieldValidation.validateText(this.getPhoneType(), ProfesionalForm.phonetype_key, 25, false, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		Profesional profesional = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(id);
		ProfesionalChange profesionalChange = DAOManager.getProfesionalChangeDAO().selectProfesionalChangeByPrimaryKey(profesional.getIdProfesionalChange());
		profesionalChange.setFirstname(com.tdil.utils.StringUtils.getDataForChange(this.getFirstname(), profesional.getFirstname()));
		profesionalChange.setLastname(com.tdil.utils.StringUtils.getDataForChange(this.getLastname(), profesional.getLastname()));
		profesionalChange.setSex(com.tdil.utils.StringUtils.getDataForChange(this.getSex(), profesional.getSex()));
		profesionalChange.setBirthdate(DateUtils.parseDateSp(com.tdil.utils.StringUtils.getDataForChange(this.getBirthdate(), DateUtils.formatDateSp(profesional.getBirthdate()))));
		profesionalChange.setPhoneareacode(com.tdil.utils.StringUtils.getDataForChange(this.getPhoneAreaCode(), profesional.getPhoneareacode()));
		profesionalChange.setPhonenumber(com.tdil.utils.StringUtils.getDataForChange(this.getPhoneNumber(), profesional.getPhonenumber()));
		profesionalChange.setPhoneextension(com.tdil.utils.StringUtils.getDataForChange(this.getPhoneExtension(), profesional.getPhoneextension()));
		profesionalChange.setPhonetype(com.tdil.utils.StringUtils.getDataForChange(this.getPhoneType(), profesional.getPhonetype()));
		DAOManager.getProfesionalChangeDAO().updateProfesionalChangeByPrimaryKey(profesionalChange);
		if (isAutoApprove()) {
			profesional.setFirstname(StringUtils.nvl(profesionalChange.getFirstname(), profesional.getFirstname()));
			profesional.setLastname(StringUtils.nvl(profesionalChange.getLastname(), profesional.getLastname()));
			profesional.setSex(StringUtils.nvl(profesionalChange.getSex(), profesional.getSex()));
			profesional.setBirthdate(StringUtils.nvl(profesionalChange.getBirthdate(), profesional.getBirthdate()));
			profesional.setPhoneareacode(StringUtils.nvl(profesionalChange.getPhoneareacode(), profesional.getPhoneareacode()));
			profesional.setPhonenumber(StringUtils.nvl(profesionalChange.getPhonenumber(), profesional.getPhonenumber()));
			profesional.setPhoneextension(StringUtils.nvl(profesionalChange.getPhoneextension(), profesional.getPhoneextension()));
			profesional.setPhonetype(StringUtils.nvl(profesionalChange.getPhonetype(), profesional.getPhonetype()));
			DAOManager.getProfesionalDAO().updateProfesionalByPrimaryKey(profesional);
			profesionalChange.setFirstname(null);
			profesionalChange.setLastname(null);
			profesionalChange.setSex(null);
			profesionalChange.setBirthdate(null);
			profesionalChange.setPhoneareacode(null);
			profesionalChange.setPhonenumber(null);
			profesionalChange.setPhoneextension(null);
			profesionalChange.setPhonetype(null);
			DAOManager.getProfesionalChangeDAO().updateProfesionalChangeByPrimaryKey(profesionalChange);
		}
	}


	protected static boolean isAutoApprove() {
		String value = SystemPropertyUtils.getSystemPropertValueInSameTransaction(SystemPropertiesKeys.AUTO_APPROVE_PROFESIONALS);
		return "TRUE".equals(value);
	}
	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(EditProfesionalPersonalDataForm.class);
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}
	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneExtension() {
		return phoneExtension;
	}
	public void setPhoneExtension(String phoneExtension) {
		this.phoneExtension = phoneExtension;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
