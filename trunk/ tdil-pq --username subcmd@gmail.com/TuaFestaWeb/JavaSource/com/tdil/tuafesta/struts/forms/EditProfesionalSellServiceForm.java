package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.tuafesta.struts.forms.beans.SellBean;

public class EditProfesionalSellServiceForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	
	private String productId;
	private String productCategorySelected;
	private String productAutocompleter;
	private String productSelectedText;
	private String referenceprice;
	
	private UploadData image1;
	private UploadData image2;
	private UploadData image3;
	private UploadData image4;
	private UploadData image5;
	
	// abm de productos
	private List<SellBean> sells = new ArrayList<SellBean>();
	
	
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
	}
	
	@Override
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

	private static Logger getLog() {
		return LoggerProvider.getLogger(EditProfesionalSellServiceForm.class);
	}

}
