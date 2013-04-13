package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.dao.CategoryDAO;
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
	public static List<Category> getCategories(final int parent)  {
		try {
			return (List<Category>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					CategoryExample productCategoryExample = new CategoryExample();
					productCategoryExample.createCriteria().andParentIdEqualTo(parent).andDeletedEqualTo(0);
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
	public static List<Category> getParentCategories(final int id)  {
		try {
			return (List<Category>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					List<Category> result = new ArrayList<Category>();
					CategoryDAO categoryDAO = DAOManager.getCategoryDAO();
					Category category = categoryDAO.selectCategoryByPrimaryKey(id);
					result.add(category);
					while (category.getParentId() != 0) {
						category = categoryDAO.selectCategoryByPrimaryKey(category.getParentId());
						result.add(0, category);
					}
					return result;
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
	public static List<Category> getAllCategoriesForIndex()  {
		try {
			return (List<Category>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					CategoryExample productCategoryExample = new CategoryExample();
					productCategoryExample.createCriteria().andDeletedEqualTo(0).andShowinhomeEqualTo(1);
					productCategoryExample.setOrderByClause("ISNULL(homeIndex) ASC, homeIndex, name");
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
