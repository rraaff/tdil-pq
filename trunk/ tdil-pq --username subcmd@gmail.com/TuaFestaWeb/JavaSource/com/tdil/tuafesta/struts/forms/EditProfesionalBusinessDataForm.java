package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo4;

public class EditProfesionalBusinessDataForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	
	private String businessname;
	private String cuit;
	private String iibb;
	
	private int geo2Id;
	private int geo3Id;
	private int geo4Id;
	
	private List<Geo2> level2;
	private List<Geo3> level3;
	private List<Geo4> level4;
	
	private UploadData logo;
	
	private String description;

	private String video1;
	private String video2;
	private String video3;
	private String video4;
	private String video5;
	
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
		return LoggerProvider.getLogger(EditProfesionalBusinessDataForm.class);
	}

}
