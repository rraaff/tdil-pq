package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.model.WallWritting;

public class WallUtils {

	@SuppressWarnings("unchecked")
	public static List<WallWritting> getWallWritings()  {
		try {
			return (List<WallWritting>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
//					List<CategoryValueObject> topCategories = DAOManager.getProductCategoryDAO().selectTopCategories();
//					topCategories.addAll(DAOManager.getServiceCategoryDAO().selectTopCategories());
//					Collections.sort(topCategories);
//					return topCategories;
					return null; // TODO ver como va a ser esto, si va a tener paginacion etc etc
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<WallWritting>();
		}
	}
}
