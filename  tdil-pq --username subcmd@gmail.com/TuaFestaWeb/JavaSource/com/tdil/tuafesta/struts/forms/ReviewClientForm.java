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

public class ReviewClientForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private int id;
	private int objectId;
	
	private Client client;
	
	private static final Logger LOG = LoggerProvider.getLogger(ReviewClientForm.class);
	
	@Override
	public void reset() throws SQLException {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		client = DAOManager.getClientDAO().selectClientByPrimaryKey(id);
	}
	
	public void basicValidate(ValidationError validationError) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
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

	public ValidationError validateForDisapprove() {
		// TODO Auto-generated method stub
		return null;
	}

	public ValidationError validateForApprove() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void approve() throws SQLException {
		ClientDAO profesionalDAO = DAOManager.getClientDAO();
		Client profesional = profesionalDAO.selectClientByPrimaryKey(this.getClient().getId());
		profesional.setStatus(ClientStatus.APPROVED);
		profesionalDAO.updateClientByPrimaryKey(profesional);
		
	}
	
	public void validateEmailManual() throws SQLException {
		ClientDAO profesionalDAO = DAOManager.getClientDAO();
		Client profesional = profesionalDAO.selectClientByPrimaryKey(this.getClient().getId());
		profesional.setEmailvalid(1);
		profesional.setVerifemail(null);
		profesional.setStatus(ClientStatus.APPROVED);
		profesionalDAO.updateClientByPrimaryKey(profesional);
	}

	public void blockClient() throws SQLException {
		ClientDAO profesionalDAO = DAOManager.getClientDAO();
		Client profesional = profesionalDAO.selectClientByPrimaryKey(this.getClient().getId());
		profesional.setStatus(ClientStatus.BLOCKED);
		profesionalDAO.updateClientByPrimaryKey(profesional);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
