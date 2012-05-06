package com.tdil.milka.struts.forms;

import java.sql.SQLException;

import com.tdil.milka.dao.ClickCounterDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.ClickCounter;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;

public class MeltButtonForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8206600031482405647L;

	private String buttonId;
	
	private Integer actualCount = 0;

	@Override
	public void basicValidate(ValidationError validationError) {
	}

	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void reset() throws SQLException {
	}

	@Override
	public void init() throws SQLException {
	}
	@Override
	public void initWith(int id) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ClickCounterDAO clickCounterDAO = DAOManager.getClickCounterDAO();
		ClickCounter clickCounter = new ClickCounter();
		Integer id = Integer.valueOf(this.getButtonId());
		clickCounter.setId(id);
		clickCounterDAO.incrementCounter(clickCounter);
		clickCounter = clickCounterDAO.selectClickCounterByPrimaryKey(id);
		setActualCount(clickCounter.getClicks());
	}

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public Integer getActualCount() {
		return actualCount;
	}

	public void setActualCount(Integer actualCount) {
		this.actualCount = actualCount;
	}
	
	
}
