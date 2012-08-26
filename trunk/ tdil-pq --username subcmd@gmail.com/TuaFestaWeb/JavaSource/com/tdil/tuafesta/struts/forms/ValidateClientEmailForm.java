package com.tdil.tuafesta.struts.forms;


import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ClientDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Client;
import com.tdil.tuafesta.model.ClientStatus;

public class ValidateClientEmailForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(ValidateClientEmailForm.class);

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
		ClientDAO clientDAO = DAOManager.getClientDAO();
		Client client = clientDAO.selectClientByPrimaryKey(this.getObjectId());
		if (client != null && client.getVerifemail() != null) {
			if (client.getVerifemail().equals(this.getVerifemail())) {
				client.setEmailvalid(1);
				client.setVerifemail(null);
				client.setStatus(ClientStatus.APPROVED);
				clientDAO.updateClientByPrimaryKey(client);
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
