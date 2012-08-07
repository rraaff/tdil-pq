package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ServiceCategoryDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ServiceCategory;
import com.tdil.tuafesta.utils.ServiceCategoryTreeNode;
import com.tdil.tuafesta.utils.ServiceCategoryUtils;
import com.tdil.validations.FieldValidation;

public class ServiceCategoryForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private List<ServiceCategory> parentCategories = new ArrayList<ServiceCategory>();
	
	private int objectId;
	private String name;
	private String description;
	private int parentId;
	private List<ServiceCategoryTreeNode> allServiceCategory;
	
	private static String name_key = "ServiceCategory.name";
	private static String description_key = "ServiceCategory.description";
	
	private static String parentId_key = "ServiceCategory.parentId";
	private static String PARENT_INVALID = "TREE_IS_GRAPH";
	
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
		ServiceCategory professionalCategory = DAOManager.getServiceCategoryDAO().selectServiceCategoryByPrimaryKey(this.getObjectId());
		professionalCategory.setDeleted(professionalCategory.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getServiceCategoryDAO().updateServiceCategoryByPrimaryKeySelective(professionalCategory);
	}
	
	private void reloadList() throws SQLException {
		List<ServiceCategoryTreeNode> list = ServiceCategoryUtils.getTreeInTransaction(false);
		List<ServiceCategoryTreeNode> flatten = ServiceCategoryTreeNode.tree2list(list);
		setAllServiceCategory(flatten);
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		ServiceCategory systemProperty = DAOManager.getServiceCategoryDAO().selectServiceCategoryByPrimaryKey(id);
		if (systemProperty != null) {
			this.objectId = id;
			this.name = systemProperty.getName();
			this.description= systemProperty.getDescription();
			this.parentId = systemProperty.getParentId();
		} 
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getName(), name_key, 100, validationError);
		FieldValidation.validateText(this.getDescription(), description_key, 4000, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		if (this.getObjectId() != 0) {
			ServiceCategoryDAO profesionalCategoryDAO = DAOManager.getServiceCategoryDAO();
			ServiceCategory serviceCategory = profesionalCategoryDAO.selectServiceCategoryByPrimaryKey(this.getParentId());
			while (serviceCategory != null) {
				if (serviceCategory.getId().equals(this.getObjectId())) {
					// error, arbol es un grafo
					validationError.setFieldError(parentId_key, PARENT_INVALID);
					return;
				}
				serviceCategory = profesionalCategoryDAO.selectServiceCategoryByPrimaryKey(serviceCategory.getParentId());
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ServiceCategoryDAO profesionalCategoryDAO = DAOManager.getServiceCategoryDAO();
		if (this.getObjectId() == 0) {
			ServiceCategory profesionalCategory = new ServiceCategory();
			profesionalCategory.setName(this.getName());
			profesionalCategory.setDescription(this.getDescription());
			profesionalCategory.setParentId(this.getParentId());
			profesionalCategory.setDeleted(0);
			profesionalCategoryDAO.insertServiceCategory(profesionalCategory);
		} else {
			ServiceCategory profesionalCategory = new ServiceCategory();
			profesionalCategory.setId(this.getObjectId());
			profesionalCategory.setName(this.getName());
			profesionalCategory.setDescription(this.getDescription());
			profesionalCategory.setParentId(this.getParentId());
			profesionalCategoryDAO.updateServiceCategoryByPrimaryKeySelective(profesionalCategory);
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

	public List<ServiceCategoryTreeNode> getAllServiceCategory() {
		return allServiceCategory;
	}

	public void setAllServiceCategory(List<ServiceCategoryTreeNode> allServiceCategory) {
		this.allServiceCategory = allServiceCategory;
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

	public List<ServiceCategory> getParentCategories() {
		return parentCategories;
	}

	public void setParentCategories(List<ServiceCategory> parentCategories) {
		this.parentCategories = parentCategories;
	}


	public static List<ServiceCategoryTreeNode> getServiceCategoryTree() {
		return ServiceCategoryUtils.getTree(false);
	}
}
