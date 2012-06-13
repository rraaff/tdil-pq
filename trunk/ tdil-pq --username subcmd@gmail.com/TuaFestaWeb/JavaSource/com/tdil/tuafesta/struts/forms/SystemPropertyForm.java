package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.SystemPropertyDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.SystemProperty;
import com.tdil.tuafesta.model.SystemPropertyExample;

public class SystemPropertyForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String propkey;
	private String propvalue;
	private String description;
	private List<SystemProperty> allSystemProperties;
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.propkey = null;
		this.propvalue = null;
		this.description = null;
	}

	@Override
	public void init() throws SQLException {
		reloadList();
	}

	private void reloadList() throws SQLException {
		SystemPropertyExample example = new SystemPropertyExample();
		example.setOrderByClause("propkey");
		this.setAllSystemProperties(DAOManager.getSystemPropertyDAO().selectSystemPropertyByExample(example));
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		SystemProperty systemProperty = DAOManager.getSystemPropertyDAO().selectSystemPropertyByPrimaryKey(id);
		if (systemProperty != null) {
			this.objectId = id;
			this.propkey = systemProperty.getPropkey();
			this.propvalue = systemProperty.getPropvalue();
			this.description = systemProperty.getDescription();
		} 
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		reloadList();
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		SystemProperty systemProperty = DAOManager.getSystemPropertyDAO().selectSystemPropertyByPrimaryKey(this.getObjectId());
		systemProperty.setDeleted(systemProperty.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getSystemPropertyDAO().updateSystemPropertyByPrimaryKey(systemProperty);
	}
	

	@Override
	public void basicValidate(ValidationError validationError) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		SystemPropertyDAO systemPropertyDAO = DAOManager.getSystemPropertyDAO();
		if (this.getObjectId() == 0) {
			/* nada que hacer, no hay altas de estas entidades */
		} else {
			SystemProperty systemProperty = new SystemProperty();
			systemProperty.setId(this.getObjectId());
			// Solo admito el cambio del value
			systemProperty.setPropvalue(this.getPropvalue());
			systemPropertyDAO.updateSystemPropertyByPrimaryKeySelective(systemProperty);
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

	public String getPropkey() {
		return propkey;
	}

	public void setPropkey(String propkey) {
		this.propkey = propkey;
	}

	public String getPropvalue() {
		return propvalue;
	}

	public void setPropvalue(String propvalue) {
		this.propvalue = propvalue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SystemProperty> getAllSystemProperties() {
		return allSystemProperties;
	}

	public void setAllSystemProperties(List<SystemProperty> allSystemProperties) {
		this.allSystemProperties = allSystemProperties;
	}

}
