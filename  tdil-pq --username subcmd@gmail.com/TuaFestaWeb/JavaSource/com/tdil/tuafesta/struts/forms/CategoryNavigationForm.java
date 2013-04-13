package com.tdil.tuafesta.struts.forms;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.dao.SellDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Category;
import com.tdil.tuafesta.model.CategoryExample;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.tuafesta.utils.CategoryUtils;

public class CategoryNavigationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(CategoryNavigationForm.class);

	private int categoryId;
	
	private List<Category> path;
	private List<Category> subcategories;

	private List<SellValueObject> sells;

	public void initWith(int categoryId) throws SQLException {
		if (categoryId == -1) {
			path = new ArrayList<Category>();
			subcategories = CategoryUtils.getTopCategories();
			sells = new ArrayList<SellValueObject>();
		} else {
			path = CategoryUtils.getParentCategories(categoryId);
			subcategories = CategoryUtils.getCategories(categoryId);
			sells = new ArrayList<SellValueObject>();
			Category category = path.get(path.size() - 1);
			if (category.getType() == SellType.PRODUCT) {
				sells = getProductSells(categoryId);
			} else {
				sells = getServiceSells(categoryId);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<SellValueObject> getProductSells(final int categoryId)  {
		try {
			return (List<SellValueObject>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					SellDAO sellDao = DAOManager.getSellDAO();
					return sellDao.selectProductSellsByCategory(categoryId);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<SellValueObject>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<SellValueObject> getServiceSells(final int categoryId)  {
		try {
			return (List<SellValueObject>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					SellDAO sellDao = DAOManager.getSellDAO();
					return sellDao.selectServiceSellsByCategory(categoryId);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<SellValueObject>();
		}
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<Category> getPath() {
		return path;
	}

	public void setPath(List<Category> path) {
		this.path = path;
	}

	public List<Category> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Category> subcategories) {
		this.subcategories = subcategories;
	}

	public List<SellValueObject> getSells() {
		return sells;
	}

	public void setSells(List<SellValueObject> sells) {
		this.sells = sells;
	}
	

}
