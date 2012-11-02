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
import com.tdil.utils.CryptoUtils;
import com.tdil.validations.FieldValidation;

public class EditProfesionalPasswordDataForm extends TransactionalValidationForm implements EditProfesionalDataForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	
	private String password;
	private String retypepassword;
	
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
	}
	
	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getPassword(), ProfesionalForm.password_key, 20, false, validationError);
		
		if (!validationError.hasFieldError(ProfesionalForm.password_key)) {
			if (this.getPassword().length() != 0 || this.getRetypepassword().length() != 0) {
				if (this.getPassword().length() < ProfesionalForm.MIN_PASS_LENGTH) {
					validationError.setFieldError(ProfesionalForm.password_key, "PASSWORD_TOO_SHORT");
				} else {
					if (!this.getPassword().equals(this.getRetypepassword())) {
						validationError.setFieldError(ProfesionalForm.password_key, "RETYPE_NOT_EQUAL");
					}
				}
			}
		}
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		Profesional profesional = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(id);
		profesional.setPassword(CryptoUtils.getHashedValue(this.getPassword()));
		DAOManager.getProfesionalDAO().updateProfesionalByPrimaryKey(profesional);
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
		return LoggerProvider.getLogger(EditProfesionalPasswordDataForm.class);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRetypepassword() {
		return retypepassword;
	}
	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}

}
