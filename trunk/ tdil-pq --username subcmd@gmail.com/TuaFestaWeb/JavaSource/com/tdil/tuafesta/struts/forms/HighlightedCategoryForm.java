package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.HighlightedCategoryDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.HighlightedCategory;
import com.tdil.tuafesta.model.ProductCategory;
import com.tdil.tuafesta.model.ServiceCategory;
import com.tdil.tuafesta.model.valueobjects.CategoryValueObject;
import com.tdil.tuafesta.model.valueobjects.HighlightedCategoryValueObject;

public class HighlightedCategoryForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	
	private String categoryType;
	private int categoryId;
	private String categorySelectedText;
	private String categoryAutocompleter;
	
	private CategoryValueObject category;
	private String fromDate;
	private String toDate;
	private List<HighlightedCategoryValueObject> allHighlightedCategories;
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.category = null;
		this.fromDate = null;
		this.toDate = null;
		this.categoryType = "";
		this.categoryId = 0;
		this.categorySelectedText = "";
		this.categoryAutocompleter = "";
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
		this.setAllHighlightedCategories(DAOManager.getHighlightedCategoryDAO().selectAllHighlightedCategoriesValueObjects());
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		HighlightedCategoryDAO highlightedCategoryDAO = DAOManager.getHighlightedCategoryDAO();
		HighlightedCategory highlightedCategory = highlightedCategoryDAO.selectHighlightedCategoryByPrimaryKey(id);
		if (highlightedCategory != null) {
			this.objectId = id;
			this.fromDate = com.tdil.utils.DateUtils.formatDate(highlightedCategory.getFromdate());
			this.toDate = com.tdil.utils.DateUtils.formatDate(highlightedCategory.getTodate());
			if (highlightedCategory.getType().equals(0)) { // producto
				ProductCategory productCategory = DAOManager.getProductCategoryDAO().selectProductCategoryByPrimaryKey(highlightedCategory.getIdProdServCat());
				this.categoryId = productCategory.getId();
				this.categorySelectedText = productCategory.getName();
				this.categoryType = "p";
			} else { // servicio
				ServiceCategory serviceCategory = DAOManager.getServiceCategoryDAO().selectServiceCategoryByPrimaryKey(highlightedCategory.getIdProdServCat());
				this.categoryId = serviceCategory.getId();
				this.categorySelectedText = serviceCategory.getName();
				this.categoryType = "s";
			}
		}
	}
	
	public boolean isCategorySelected() {
		return this.getCategoryId() != 0;
	}
		
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		this.setAllHighlightedCategories(DAOManager.getHighlightedCategoryDAO().selectAllHighlightedCategoriesValueObjects());
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		HighlightedCategory highlightedCategory = DAOManager.getHighlightedCategoryDAO().selectHighlightedCategoryByPrimaryKey(this.getObjectId());
		highlightedCategory.setDeleted(highlightedCategory.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getHighlightedCategoryDAO().updateHighlightedCategoryByPrimaryKeySelective(highlightedCategory);
	}
	

	@Override
	public void basicValidate(ValidationError validationError) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		
	}

	@Override
	public void save() throws SQLException, ValidationException {
		HighlightedCategoryDAO highlightedCategoryDAO = DAOManager.getHighlightedCategoryDAO();
		if (this.getObjectId() == 0) {
			HighlightedCategory highlightedCategory = new HighlightedCategory();
			setData(highlightedCategory);
			highlightedCategory.setDeleted(0);
			highlightedCategoryDAO.insertHighlightedCategory(highlightedCategory);
		} else {
			HighlightedCategory highlightedCategory = new HighlightedCategory();
			highlightedCategory.setId(this.getObjectId());
			setData(highlightedCategory);
			highlightedCategoryDAO.updateHighlightedCategoryByPrimaryKeySelective(highlightedCategory);
		}
	}

	public void setData(HighlightedCategory highlightedCategory) {
		highlightedCategory.setIdProdServCat(this.getCategoryId());
		highlightedCategory.setType(this.getCategoryType().equals("p") ? 0 : 1);
		highlightedCategory.setFromdate(com.tdil.utils.DateUtils.parseDate(this.getFromDate()));
		highlightedCategory.setTodate(com.tdil.utils.DateUtils.parseDate(this.getToDate()));
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

	public CategoryValueObject getCategory() {
		return category;
	}

	public void setCategory(CategoryValueObject category) {
		this.category = category;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public List<HighlightedCategoryValueObject> getAllHighlightedCategories() {
		return allHighlightedCategories;
	}

	public void setAllHighlightedCategories(List<HighlightedCategoryValueObject> allHighlightedCategories) {
		this.allHighlightedCategories = allHighlightedCategories;
	}
	
	public static Logger getLog() {
		return LoggerProvider.getLogger(HighlightedCategoryForm.class);
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategorySelectedText() {
		return categorySelectedText;
	}

	public void setCategorySelectedText(String categorySelectedText) {
		this.categorySelectedText = categorySelectedText;
	}

	public String getCategoryAutocompleter() {
		return categoryAutocompleter;
	}

	public void setCategoryAutocompleter(String categoryAutocompleter) {
		this.categoryAutocompleter = categoryAutocompleter;
	}


}
