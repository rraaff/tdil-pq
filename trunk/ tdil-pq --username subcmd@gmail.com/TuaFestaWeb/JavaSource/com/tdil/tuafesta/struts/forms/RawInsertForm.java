package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.RawInsertDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.RawInsert;
import com.tdil.tuafesta.model.RawInsertExample;
import com.tdil.tuafesta.utils.Banner;
import com.tdil.tuafesta.utils.CacheRegionUtils;

public class RawInsertForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String inserttype;
	private String htmlcontent;
	private String description;
	private List<RawInsert> allRawInserts;
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.inserttype = null;
		this.htmlcontent = null;
		this.description = null;
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
		DAOManager.getRawInsertDAO().deleteRawInsertByPrimaryKey(this.getObjectId());
	}

	@Override
	public void init() throws SQLException {
		reloadList();
	}

	private void reloadList() throws SQLException {
		RawInsertExample example = new RawInsertExample();
		example.setOrderByClause("insertType");
		this.setAllRawInserts(DAOManager.getRawInsertDAO().selectRawInsertByExampleWithBLOBs(example));
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		RawInsert systemProperty = DAOManager.getRawInsertDAO().selectRawInsertByPrimaryKey(id);
		if (systemProperty != null) {
			this.objectId = id;
			this.inserttype = systemProperty.getInserttype();
			this.htmlcontent = systemProperty.getHtmlcontent();
			this.description = systemProperty.getDescription();
		} 
	}

	@Override
	public void basicValidate(ValidationError validationError) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		RawInsertDAO systemPropertyDAO = DAOManager.getRawInsertDAO();
		if (this.getObjectId() == 0) {
			RawInsert systemProperty = new RawInsert();
			systemProperty.setInserttype(this.getInserttype());
			systemProperty.setDescription(this.getDescription());
			systemProperty.setDeleted(0);
			systemProperty.setHtmlcontent(this.getHtmlcontent());
			systemPropertyDAO.insertRawInsert(systemProperty);
		} else {
			RawInsert systemProperty = new RawInsert();
			systemProperty.setId(this.getObjectId());
			systemProperty.setInserttype(this.getInserttype());
			systemProperty.setDescription(this.getDescription());
			systemProperty.setHtmlcontent(this.getHtmlcontent());
			systemPropertyDAO.updateRawInsertByPrimaryKeySelective(systemProperty);
		}
		CacheRegionUtils.incrementVersionInTransaction(Banner.class.getName());
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

	public String getInserttype() {
		return inserttype;
	}

	public void setInserttype(String propkey) {
		this.inserttype = propkey;
	}

	public String getHtmlcontent() {
		return htmlcontent;
	}

	public void setHtmlcontent(String propvalue) {
		this.htmlcontent = propvalue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RawInsert> getAllRawInserts() {
		return allRawInserts;
	}

	public void setAllRawInserts(List<RawInsert> allRawInserts) {
		this.allRawInserts = allRawInserts;
	}

}
