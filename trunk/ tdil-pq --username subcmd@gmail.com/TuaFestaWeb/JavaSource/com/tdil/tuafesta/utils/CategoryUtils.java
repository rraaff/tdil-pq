package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.valueobjects.CategoryValueObject;

public class CategoryUtils {

	@SuppressWarnings("unchecked")
	public static List<CategoryValueObject> getTopCategories()  {
		try {
			return (List<CategoryValueObject>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					List<CategoryValueObject> topCategories = DAOManager.getProductCategoryDAO().selectTopCategories();
					topCategories.addAll(DAOManager.getServiceCategoryDAO().selectTopCategories());
					Collections.sort(topCategories);
					return topCategories;
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<CategoryValueObject>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<CategoryValueObject> getAllCategories()  {
		try {
			return (List<CategoryValueObject>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					List<CategoryValueObject> allCategories = DAOManager.getProductCategoryDAO().selectAllCategories();
					allCategories.addAll(DAOManager.getServiceCategoryDAO().selectAllCategories());
					Collections.sort(allCategories);
					return allCategories;
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<CategoryValueObject>();
		}
	}
}
