package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ProfesionalCategoryDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ProfesionalCategory;
import com.tdil.tuafesta.model.ProfesionalCategoryExample;

public class ProfesionalCategoryForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private List<ProfesionalCategory> parentCategories = new ArrayList<ProfesionalCategory>();
	
	private int objectId;
	private String name;
	private String description;
	private int parentId;
	private List<ProfesionalCategory> allProfesionalCategory;
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.name = null;
		this.parentId= 0;
		this.description = null;
	}

	@Override
	public void init() throws SQLException {
		reloadList();
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
		ProfesionalCategory professionalCategory = DAOManager.getProfesionalCategoryDAO().selectProfesionalCategoryByPrimaryKey(this.getObjectId());
		professionalCategory.setDeleted(professionalCategory.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getProfesionalCategoryDAO().updateProfesionalCategoryByPrimaryKeySelective(professionalCategory);
	}
	
	private void reloadList() throws SQLException {
		ProfesionalCategoryExample example = new ProfesionalCategoryExample();
		example.createCriteria().andParentIdEqualTo(0);
		example.setOrderByClause("name");
		this.setAllProfesionalCategory(DAOManager.getProfesionalCategoryDAO().selectProfesionalCategoryByExample(example));
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		ProfesionalCategory systemProperty = DAOManager.getProfesionalCategoryDAO().selectProfesionalCategoryByPrimaryKey(id);
		if (systemProperty != null) {
			this.objectId = id;
			this.name = systemProperty.getName();
			this.description= systemProperty.getDescription();
			this.parentId = systemProperty.getParentId();
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
		ProfesionalCategoryDAO profesionalCategoryDAO = DAOManager.getProfesionalCategoryDAO();
		if (this.getObjectId() == 0) {
			ProfesionalCategory profesionalCategory = new ProfesionalCategory();
			profesionalCategory.setName(this.getName());
			profesionalCategory.setDescription(this.getDescription());
			profesionalCategory.setParentId(this.getParentId());
			profesionalCategory.setDeleted(0);
			profesionalCategoryDAO.insertProfesionalCategory(profesionalCategory);
		} else {
			ProfesionalCategory profesionalCategory = new ProfesionalCategory();
			profesionalCategory.setName(this.getName());
			profesionalCategory.setDescription(this.getDescription());
			profesionalCategory.setParentId(this.getParentId());
			profesionalCategoryDAO.updateProfesionalCategoryByPrimaryKeySelective(profesionalCategory);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProfesionalCategory> getAllProfesionalCategory() {
		return allProfesionalCategory;
	}

	public void setAllProfesionalCategory(List<ProfesionalCategory> allProfesionalCategory) {
		this.allProfesionalCategory = allProfesionalCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<ProfesionalCategory> getParentCategories() {
		return parentCategories;
	}

	public void setParentCategories(List<ProfesionalCategory> parentCategories) {
		this.parentCategories = parentCategories;
	}


}
