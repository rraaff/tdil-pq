package com.tdil.tuafesta.struts.forms;


import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ProfesionalDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;

public class ValidateProfesionalEmailForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(ValidateProfesionalEmailForm.class);

	private int id;

	private int objectId;
	private String verifemail;

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {

	}

	@Override
	public void basicValidate(ValidationError validationError) {
		// TODO VALIDACIONES
	}

	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO VALIDACIONES
	}

	@Override
	public void save() throws SQLException, ValidationException {
		// TODO manejar los casos de error
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		Profesional profesional = profesionalDAO.selectProfesionalByPrimaryKey(this.getObjectId());
		if (profesional != null && profesional.getVerifemail() != null) {
			if (profesional.getVerifemail().equals(this.getVerifemail())) {
				profesional.setEmailvalid(1);
				profesional.setVerifemail(null);
				profesionalDAO.updateProfesionalByPrimaryKey(profesional);
			} else {
				throw new ValidationException(new ValidationError("not valid"));
			}
		} else {
			throw new ValidationException(new ValidationError("not valid"));
		}
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

	public String getVerifemail() {
		return verifemail;
	}

	public void setVerifemail(String verifemail) {
		this.verifemail = verifemail;
	}


}
