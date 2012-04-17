package com.tdil.djmag.dao;

import com.tdil.djmag.model.MenuItem;
import com.tdil.djmag.model.MenuItemExample;
import java.sql.SQLException;
import java.util.List;

public interface MenuItemDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Apr 17 07:34:30 ART 2012
	 */
	int countMenuItemByExample(MenuItemExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Apr 17 07:34:30 ART 2012
	 */
	int deleteMenuItemByExample(MenuItemExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Apr 17 07:34:30 ART 2012
	 */
	int deleteMenuItemByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Apr 17 07:34:30 ART 2012
	 */
	Integer insertMenuItem(MenuItem record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Apr 17 07:34:30 ART 2012
	 */
	Integer insertMenuItemSelective(MenuItem record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Apr 17 07:34:30 ART 2012
	 */
	List<MenuItem> selectMenuItemByExample(MenuItemExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Apr 17 07:34:30 ART 2012
	 */
	MenuItem selectMenuItemByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Apr 17 07:34:30 ART 2012
	 */
	int updateMenuItemByExampleSelective(MenuItem record, MenuItemExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Apr 17 07:34:30 ART 2012
	 */
	int updateMenuItemByExample(MenuItem record, MenuItemExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Apr 17 07:34:30 ART 2012
	 */
	int updateMenuItemByPrimaryKeySelective(MenuItem record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table MENUITEM
	 * @mbggenerated  Tue Apr 17 07:34:30 ART 2012
	 */
	int updateMenuItemByPrimaryKey(MenuItem record) throws SQLException;
}