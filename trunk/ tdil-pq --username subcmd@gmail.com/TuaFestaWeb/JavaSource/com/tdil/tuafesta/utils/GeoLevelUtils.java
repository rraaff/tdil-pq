package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo2Example;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo3Example;
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
	public static List<Geo2> getActiveGeo2Levels()  {
		try {
			return (List<Geo2>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					Geo2Example geo2Example = new Geo2Example();
					geo2Example.createCriteria().andDeletedEqualTo(0);
					geo2Example.setOrderByClause("nombre");
					return DAOManager.getGeo2DAO().selectGeo2ByExample(geo2Example);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Geo2>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Geo3> getActiveGeo3Levels(final int g2)  {
		try {
			return (List<Geo3>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					Geo3Example geo3Example = new Geo3Example();
					geo3Example.createCriteria().andDeletedEqualTo(0).andGeo2IdEqualTo(g2);
					geo3Example.setOrderByClause("nombre");
					return DAOManager.getGeo3DAO().selectGeo3ByExample(geo3Example);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Geo3>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Geo4> getActiveGeo4Levels(final int g2)  {
		try {
			return (List<Geo4>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					Geo4Example geo4Example = new Geo4Example();
					geo4Example.createCriteria().andDeletedEqualTo(0).andGeo3IdEqualTo(g2);
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
	public static Geo2 getGeo2(final int id)  {
		try {
			return (Geo2)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return DAOManager.getGeo2DAO().selectGeo2ByPrimaryKey(id);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Geo3 getGeo3(final int id)  {
		try {
			return (Geo3)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return DAOManager.getGeo3DAO().selectGeo3ByPrimaryKey(id);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Geo4 getGeo4(final int id)  {
		try {
			return (Geo4)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return DAOManager.getGeo4DAO().selectGeo4ByPrimaryKey(id);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
