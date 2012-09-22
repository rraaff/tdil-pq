package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Category;
import com.tdil.tuafesta.model.CategoryExample;

public class CategoryUtils {

	@SuppressWarnings("unchecked")
	public static List<Category> getCategories(final int parent, final int type)  {
		try {
			return (List<Category>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					CategoryExample productCategoryExample = new CategoryExample();
					productCategoryExample.createCriteria().andParentIdEqualTo(parent).andTypeEqualTo(type).andDeletedEqualTo(0);
					productCategoryExample.setOrderByClause("isother, name");
					return DAOManager.getCategoryDAO().selectCategoryByExample(productCategoryExample);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Category>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Category> getTopCategories()  {
		try {
			return (List<Category>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					CategoryExample productCategoryExample = new CategoryExample();
					productCategoryExample.createCriteria().andParentIdEqualTo(0).andDeletedEqualTo(0);
					List<Category> topCategories = DAOManager.getCategoryDAO().selectCategoryByExample(productCategoryExample);
					return topCategories;
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Category>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Category> getAllCategories()  {
		try {
			return (List<Category>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					CategoryExample productCategoryExample = new CategoryExample();
					productCategoryExample.createCriteria().andDeletedEqualTo(0);
					productCategoryExample.setOrderByClause("name");
					List<Category> allCategories = DAOManager.getCategoryDAO().selectCategoryByExample(productCategoryExample);
					return allCategories;
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Category>();
		}
	}
}
