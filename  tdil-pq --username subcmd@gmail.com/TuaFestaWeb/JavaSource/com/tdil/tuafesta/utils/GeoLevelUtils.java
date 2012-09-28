package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Geo4Example;

public class GeoLevelUtils {

	@SuppressWarnings("unchecked")
	public static List<Geo4> getActiveGeo4LevelsForHome()  {
		try {
			return (List<Geo4>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					Geo4Example geo4Example = new Geo4Example();
					geo4Example.createCriteria().andDeletedEqualTo(0).andShowinhomeEqualTo(1).andAvailableforserviceEqualTo(1);
					geo4Example.setOrderByClause("ISNULL(homeIndex) ASC, homeIndex, nombre");
					return DAOManager.getGeo4DAO().selectGeo4ByExample(geo4Example);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Geo4>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Geo4> getActiveGeo4Levels()  {
		try {
			return (List<Geo4>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					Geo4Example geo4Example = new Geo4Example();
					geo4Example.createCriteria().andDeletedEqualTo(0).andAvailableforserviceEqualTo(1);
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
	
	@SuppressWarnings("unchecked")
	public static String getPath(final int geoId)  {
		if (geoId != 0) {
			try {
				return (String)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
					public Object executeInTransaction() throws SQLException {
						Geo4 geo4 = DAOManager.getGeo4DAO().selectGeo4ByPrimaryKey(geoId);
						Geo3 geo3 = DAOManager.getGeo3DAO().selectGeo3ByPrimaryKey(geo4.getGeo3Id());
						Geo2 geo2 = DAOManager.getGeo2DAO().selectGeo2ByPrimaryKey(geo3.getGeo2Id());
						return geo2.getNombre() + " > " + geo3.getNombre() + " > " + geo4.getNombre();
					}
				});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}
	
}
