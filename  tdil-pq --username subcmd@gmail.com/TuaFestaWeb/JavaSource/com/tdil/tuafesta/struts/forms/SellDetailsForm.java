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
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;

public class SellDetailsForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private SellValueObject sellValueObject;
	
	@Override
	public void reset() throws SQLException {
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public void initWith(int type, int id) throws SQLException {
		if (type == SellType.PRODUCT) {
			sellValueObject = DAOManager.getSellDAO().selectSellProductValueObject(id);
		} else {
			sellValueObject = DAOManager.getSellDAO().selectSellServiceValueObject(id);
		}
	}
	
	@Override
	public void basicValidate(ValidationError error) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		
	}	

	private static Logger getLog() {
		return LoggerProvider.getLogger(SellDetailsForm.class);
	}
	public SellValueObject getSellValueObject() {
		return sellValueObject;
	}

	
}
