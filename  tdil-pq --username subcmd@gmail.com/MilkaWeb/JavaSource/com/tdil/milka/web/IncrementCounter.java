package com.tdil.milka.web;

import java.sql.SQLException;

import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.ClickCounter;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;

class IncrementCounter implements TransactionalAction {
	
	private int id;
	
	public IncrementCounter(int id) {
		super();
		this.id = id;
	}

	public void executeInTransaction() throws SQLException, ValidationException {
		ClickCounter c = DAOManager.getClickCounterDAO().selectClickCounterByPrimaryKey(this.id);
		c.setClicks(c.getClicks() + 1);
		DAOManager.getClickCounterDAO().updateClickCounterByPrimaryKey(c);
	}
}