package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.MenuItemDAO;
import com.tdil.djmag.model.MenuItem;
import com.tdil.djmag.model.MenuItemExample;
import java.sql.SQLException;
import java.util.List;

public class MenuItemDAOImpl implements MenuItemDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public MenuItemDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public int countMenuItemByExample(MenuItemExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("MENUITEM.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public int deleteMenuItemByExample(MenuItemExample example) throws SQLException {
		int rows = sqlMapClient.delete("MENUITEM.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public int deleteMenuItemByPrimaryKey(Integer id) throws SQLException {
		MenuItem _key = new MenuItem();
		_key.setId(id);
		int rows = sqlMapClient.delete("MENUITEM.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public Integer insertMenuItem(MenuItem record) throws SQLException {
		Object newKey = sqlMapClient.insert("MENUITEM.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public Integer insertMenuItemSelective(MenuItem record) throws SQLException {
		Object newKey = sqlMapClient.insert("MENUITEM.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<MenuItem> selectMenuItemByExample(MenuItemExample example) throws SQLException {
		List<MenuItem> list = sqlMapClient.queryForList("MENUITEM.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public MenuItem selectMenuItemByPrimaryKey(Integer id) throws SQLException {
		MenuItem _key = new MenuItem();
		_key.setId(id);
		MenuItem record = (MenuItem) sqlMapClient.queryForObject("MENUITEM.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public int updateMenuItemByExampleSelective(MenuItem record, MenuItemExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MENUITEM.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public int updateMenuItemByExample(MenuItem record, MenuItemExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("MENUITEM.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public int updateMenuItemByPrimaryKeySelective(MenuItem record) throws SQLException {
		int rows = sqlMapClient.update("MENUITEM.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	public int updateMenuItemByPrimaryKey(MenuItem record) throws SQLException {
		int rows = sqlMapClient.update("MENUITEM.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Jun 19 18:08:48 ART 2012
	 */
	protected static class UpdateByExampleParms extends MenuItemExample {
		private Object record;

		public UpdateByExampleParms(Object record, MenuItemExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}