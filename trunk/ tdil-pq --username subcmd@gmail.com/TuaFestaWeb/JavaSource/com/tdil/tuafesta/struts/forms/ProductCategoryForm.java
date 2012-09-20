package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ProductCategoryDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ProductCategory;
import com.tdil.tuafesta.model.ProductCategoryExample;
import com.tdil.tuafesta.utils.CacheRegionUtils;
import com.tdil.tuafesta.utils.ProductCategoryTreeNode;
import com.tdil.tuafesta.utils.ProductCategoryUtils;
import com.tdil.validations.FieldValidation;

public class ProductCategoryForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private List<ProductCategory> parentCategories = new ArrayList<ProductCategory>();
	
	private int objectId;
	private String name;
	private String description;
	private int parentId;
	private List<ProductCategoryTreeNode> allProductCategory;
	
	private static String name_key = "ProductCategory.name";
	private static String description_key = "ProductCategory.description";
	
	private static String parentId_key = "ProductCategory.parentId";
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
		ProductCategory professionalCategory = DAOManager.getProductCategoryDAO().selectProductCategoryByPrimaryKey(this.getObjectId());
		professionalCategory.setDeleted(professionalCategory.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getProductCategoryDAO().updateProductCategoryByPrimaryKeySelective(professionalCategory);
	}
	
	private void reloadList() throws SQLException {
		List<ProductCategoryTreeNode> list = ProductCategoryUtils.getTreeInTransaction(false);
		List<ProductCategoryTreeNode> flatten = ProductCategoryTreeNode.tree2list(list);
		setAllProductCategory(flatten);
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		ProductCategory systemProperty = DAOManager.getProductCategoryDAO().selectProductCategoryByPrimaryKey(id);
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
			ProductCategoryDAO profesionalCategoryDAO = DAOManager.getProductCategoryDAO();
			ProductCategory serviceCategory = profesionalCategoryDAO.selectProductCategoryByPrimaryKey(this.getParentId());
			while (serviceCategory != null) {
				if (serviceCategory.getId().equals(this.getObjectId())) {
					// error, arbol es un grafo
					validationError.setFieldError(parentId_key, PARENT_INVALID);
					return;
				}
				serviceCategory = profesionalCategoryDAO.selectProductCategoryByPrimaryKey(serviceCategory.getParentId());
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ProductCategoryDAO profesionalCategoryDAO = DAOManager.getProductCategoryDAO();
		if (this.getObjectId() == 0) {
			ProductCategory profesionalCategory = new ProductCategory();
			profesionalCategory.setName(this.getName());
			profesionalCategory.setDescription(this.getDescription());
			profesionalCategory.setParentId(this.getParentId());
			profesionalCategory.setIsother(0);
			profesionalCategory.setDeleted(0);
			profesionalCategoryDAO.insertProductCategory(profesionalCategory);
		} else {
			ProductCategory profesionalCategory = profesionalCategoryDAO.selectProductCategoryByPrimaryKey(this.getObjectId());
			profesionalCategory.setName(this.getName());
			profesionalCategory.setDescription(this.getDescription());
			profesionalCategory.setParentId(this.getParentId());
			profesionalCategoryDAO.updateProductCategoryByPrimaryKeySelective(profesionalCategory);
		}
		if (this.getParentId() != 0) {
			ProductCategoryExample otherexample = new ProductCategoryExample();
			otherexample.createCriteria().andParentIdEqualTo(this.getParentId()).andIsotherEqualTo(1);
			List<ProductCategory> pc = profesionalCategoryDAO.selectProductCategoryByExample(otherexample);
			if (pc.isEmpty()) {
				ProductCategory profesionalCategory = new ProductCategory();
				profesionalCategory.setName("Otros");
				profesionalCategory.setDescription("Otros");
				profesionalCategory.setParentId(this.getParentId());
				profesionalCategory.setIsother(1);
				profesionalCategory.setDeleted(0);
				profesionalCategoryDAO.insertProductCategory(profesionalCategory);
			}
		}
		CacheRegionUtils.incrementVersionInTransaction(ProductCategory.class.getName());
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

	public List<ProductCategoryTreeNode> getAllProductCategory() {
		return allProductCategory;
	}

	public void setAllProductCategory(List<ProductCategoryTreeNode> allProductCategory) {
		this.allProductCategory = allProductCategory;
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

	public List<ProductCategory> getParentCategories() {
		return parentCategories;
	}

	public void setParentCategories(List<ProductCategory> parentCategories) {
		this.parentCategories = parentCategories;
	}


	public static List<ProductCategoryTreeNode> getProductCategoryTree() {
		return ProductCategoryUtils.getTree(false);
	}
}
