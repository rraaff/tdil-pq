package com.tdil.milka.web;

import java.sql.SQLException;

import com.tdil.milka.daomanager.DAOManager;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;

class ParticipantCounter implements TransactionalAction {
	
	private int result;
	
	public ParticipantCounter() {
		super();
	}

	public void executeInTransaction() throws SQLException, ValidationException {
		result = DAOManager.getClickCounterDAO().sumRender();
	}

	public int getResult() {
		return result;
	}
}