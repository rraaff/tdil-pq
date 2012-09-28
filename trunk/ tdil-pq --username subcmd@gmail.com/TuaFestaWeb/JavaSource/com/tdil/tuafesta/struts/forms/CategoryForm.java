package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.CategoryDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Category;
import com.tdil.tuafesta.model.CategoryExample;
import com.tdil.tuafesta.utils.CacheRegionUtils;
import com.tdil.tuafesta.utils.CategoryTreeNode;
import com.tdil.tuafesta.utils.TreeCategoryUtils;
import com.tdil.validations.FieldValidation;

public class CategoryForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int type;
	
	private List<Category> parentCategories = new ArrayList<Category>();
	
	private int objectId;
	private String name;
	private String description;
	private int parentId;
	
	private boolean showinhome;
	private String homeindex;
	private List<CategoryTreeNode> allCategory;
	
	public static String name_key = "Category.name";
	public static String description_key = "Category.description";
	public static String homeindex_key = "Category.homeindex";
	
	public static String parentId_key = "Category.parentId";
	private static String PARENT_INVALID = "TREE_IS_GRAPH";
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.name = null;
		this.parentId= 0;
		this.description = null;
		this.showinhome = false;
		this.homeindex = null;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.showinhome = false;
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
		Category professionalCategory = DAOManager.getCategoryDAO().selectCategoryByPrimaryKey(this.getObjectId());
		professionalCategory.setDeleted(professionalCategory.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getCategoryDAO().updateCategoryByPrimaryKeySelective(professionalCategory);
	}
	
	private void reloadList() throws SQLException {
		List<CategoryTreeNode> list = TreeCategoryUtils.getTreeInTransaction(false, this.getType());
		List<CategoryTreeNode> flatten = CategoryTreeNode.tree2list(list);
		setAllCategory(flatten);
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		Category systemProperty = DAOManager.getCategoryDAO().selectCategoryByPrimaryKey(id);
		if (systemProperty != null) {
			this.objectId = id;
			this.name = systemProperty.getName();
			this.description= systemProperty.getDescription();
			this.parentId = systemProperty.getParentId();
			this.homeindex = (systemProperty.getHomeindex() == null ? null : String.valueOf(systemProperty.getHomeindex()));
			this.showinhome = systemProperty.getShowinhome().equals(1);
		} 
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getName(), name_key, 100, validationError);
		FieldValidation.validateText(this.getDescription(), description_key, 4000, validationError);
		FieldValidation.validateNumber(this.getHomeindex(), homeindex_key, 1, 1000, false, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		if (this.getObjectId() != 0) {
			CategoryDAO profesionalCategoryDAO = DAOManager.getCategoryDAO();
			Category serviceCategory = profesionalCategoryDAO.selectCategoryByPrimaryKey(this.getParentId());
			while (serviceCategory != null) {
				if (serviceCategory.getId().equals(this.getObjectId())) {
					// error, arbol es un grafo
					validationError.setFieldError(parentId_key, PARENT_INVALID);
					return;
				}
				serviceCategory = profesionalCategoryDAO.selectCategoryByPrimaryKey(serviceCategory.getParentId());
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		CategoryDAO profesionalCategoryDAO = DAOManager.getCategoryDAO();
		if (this.getObjectId() == 0) {
			Category profesionalCategory = new Category();
			profesionalCategory.setName(this.getName());
			profesionalCategory.setDescription(this.getDescription());
			profesionalCategory.setParentId(this.getParentId());
			profesionalCategory.setIsother(0);
			profesionalCategory.setDeleted(0);
			profesionalCategory.setType(this.getType());
			if (StringUtils.isEmpty(this.getHomeindex())) {
				profesionalCategory.setHomeindex(null);
			} else {
				profesionalCategory.setHomeindex(Integer.valueOf(this.getHomeindex().trim()));
			}
			profesionalCategory.setShowinhome(this.isShowinhome() ? 1 : 0);
			profesionalCategoryDAO.insertCategory(profesionalCategory);
		} else {
			Category profesionalCategory = profesionalCategoryDAO.selectCategoryByPrimaryKey(this.getObjectId());
			profesionalCategory.setName(this.getName());
			profesionalCategory.setDescription(this.getDescription());
			profesionalCategory.setParentId(this.getParentId());
			if (StringUtils.isEmpty(this.getHomeindex())) {
				profesionalCategory.setHomeindex(null);
			} else {
				profesionalCategory.setHomeindex(Integer.valueOf(this.getHomeindex().trim()));
			}
			profesionalCategory.setShowinhome(this.isShowinhome() ? 1 : 0);
			profesionalCategoryDAO.updateCategoryByPrimaryKey(profesionalCategory);
		}
		if (this.getParentId() != 0) {
			CategoryExample otherexample = new CategoryExample();
			otherexample.createCriteria().andParentIdEqualTo(this.getParentId()).andIsotherEqualTo(1);
			List<Category> pc = profesionalCategoryDAO.selectCategoryByExample(otherexample);
			if (pc.isEmpty()) {
				Category profesionalCategory = new Category();
				profesionalCategory.setName("Otros");
				profesionalCategory.setDescription("Otros");
				profesionalCategory.setParentId(this.getParentId());
				profesionalCategory.setIsother(1);
				profesionalCategory.setShowinhome(0);
				profesionalCategory.setDeleted(0);
				profesionalCategory.setType(this.getType());
				profesionalCategoryDAO.insertCategory(profesionalCategory);
			}
		}
		CacheRegionUtils.incrementVersionInTransaction(Category.class.getName());
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

	public List<CategoryTreeNode> getAllCategory() {
		return allCategory;
	}

	public void setAllCategory(List<CategoryTreeNode> allCategory) {
		this.allCategory = allCategory;
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

	public List<Category> getParentCategories() {
		return parentCategories;
	}

	public void setParentCategories(List<Category> parentCategories) {
		this.parentCategories = parentCategories;
	}


	public List<CategoryTreeNode> getCategoryTree() {
		return TreeCategoryUtils.getTree(false, this.getType());
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isShowinhome() {
		return showinhome;
	}

	public void setShowinhome(boolean showinhome) {
		this.showinhome = showinhome;
	}

	public String getHomeindex() {
		return homeindex;
	}

	public void setHomeindex(String homeindex) {
		this.homeindex = homeindex;
	}
}
