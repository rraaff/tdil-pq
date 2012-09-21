package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ProductCategory;
import com.tdil.tuafesta.model.ProductCategoryExample;
import com.tdil.tuafesta.model.valueobjects.PromotionValueObject;

public class PromotionUtils {

	@SuppressWarnings("unchecked")
	public static List<PromotionValueObject> getActivePromotions()  {
		try {
			return (List<PromotionValueObject>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return DAOManager.getPromotionDAO().selectActivePromotions();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<PromotionValueObject>();
		}
	}
}
