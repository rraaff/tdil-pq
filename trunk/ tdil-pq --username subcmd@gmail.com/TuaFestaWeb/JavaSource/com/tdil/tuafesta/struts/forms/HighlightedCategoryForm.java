package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.CategoryDAO;
import com.tdil.tuafesta.dao.Geo3DAO;
import com.tdil.tuafesta.dao.HighlightedCategoryDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Category;
import com.tdil.tuafesta.model.CategoryExample;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo3Example;
import com.tdil.tuafesta.model.HighlightedCategory;
import com.tdil.tuafesta.model.valueobjects.HighlightedCategoryValueObject;
import com.tdil.tuafesta.utils.CacheRegionUtils;
import com.tdil.tuafesta.utils.CategoryTreeNode;
import com.tdil.tuafesta.utils.TreeCategoryUtils;
import com.tdil.utils.DateUtils;
import com.tdil.validations.FieldValidation;

public class HighlightedCategoryForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	
	private int categoryId;
	private String fromDate;
	private String toDate;
	
	private List<HighlightedCategoryValueObject> allCategories = new ArrayList<HighlightedCategoryValueObject>();
	
	public static String name_key = "Category.name";
	public static String description_key = "Category.description";
	public static String homeindex_key = "Category.homeindex";
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.categoryId= 0;
		this.fromDate = null;
		this.toDate = null;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
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
		HighlightedCategory professionalCategory = DAOManager.getHighlightedCategoryDAO().selectHighlightedCategoryByPrimaryKey(this.getObjectId());
		professionalCategory.setDeleted(professionalCategory.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getHighlightedCategoryDAO().updateHighlightedCategoryByPrimaryKeySelective(professionalCategory);
	}
	
	private void reloadList() throws SQLException {
		setAllCategories(DAOManager.getHighlightedCategoryDAO().searchHighlightedCategories());
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		HighlightedCategory systemProperty = DAOManager.getHighlightedCategoryDAO().selectHighlightedCategoryByPrimaryKey(id);
		if (systemProperty != null) {
			this.objectId = id;
			this.categoryId = systemProperty.getIdCategory();
			this.fromDate= DateUtils.formatDate(systemProperty.getFromdate());
			this.toDate = DateUtils.formatDate(systemProperty.getTodate());
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
		HighlightedCategoryDAO highlightedCategoryDAO = DAOManager.getHighlightedCategoryDAO();
		if (this.getObjectId() == 0) {
			HighlightedCategory hc = new HighlightedCategory();
			hc.setIdCategory(this.getCategoryId());
			hc.setFromdate(com.tdil.utils.DateUtils.parseDate(this.getFromDate()));
			hc.setTodate(DateUtils.date2LastMomentOfDate(com.tdil.utils.DateUtils.parseDate(this.getToDate())));
			hc.setDeleted(0);
			highlightedCategoryDAO.insertHighlightedCategory(hc);
		} else {
			HighlightedCategory hc = highlightedCategoryDAO.selectHighlightedCategoryByPrimaryKey(this.getObjectId());
			hc.setIdCategory(this.getCategoryId());
			hc.setFromdate(com.tdil.utils.DateUtils.parseDate(this.getFromDate()));
			hc.setTodate(DateUtils.date2LastMomentOfDate(com.tdil.utils.DateUtils.parseDate(this.getToDate())));
			highlightedCategoryDAO.updateHighlightedCategoryByPrimaryKey(hc);
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public List<HighlightedCategoryValueObject> getAllCategories() {
		return allCategories;
	}

	public void setAllCategories(List<HighlightedCategoryValueObject> allCategories) {
		this.allCategories = allCategories;
	}

	@SuppressWarnings("unchecked")
	public static List<Category> getCategories()  {
		try {
			return (List<Category>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return DAOManager.getCategoryDAO().selectCategoryWithoutChilds();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Category>();
		}
	}
}
