package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Geo4Example;

public class GeoLevelUtils {

	@SuppressWarnings("unchecked")
	public static List<Geo4> getActiveGeo4Levels()  {
		try {
			return (List<Geo4>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					Geo4Example geo4Example = new Geo4Example();
					geo4Example.createCriteria().andDeletedEqualTo(0);
					geo4Example.setOrderByClause("nombre");
					return DAOManager.getGeo4DAO().selectGeo4ByExample(geo4Example);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Geo4>();
		}
	}
	
}
