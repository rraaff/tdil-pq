package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tdil.djmag.dao.MenuItemDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.MenuItem;
import com.tdil.djmag.model.MenuItemExample;
import com.tdil.djmag.model.MenuItemExample.Criteria;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.validations.FieldValidation;

public class ReorderMenuForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3196174331760319100L;
	private int countryId;
	private List<MenuItemPositionVO> menuItems = new ArrayList<MenuItemPositionVO>();
	
	private static String position_key = "MenuItem.position";

	@Override
	public void basicValidate(ValidationError validationError) {
		for (MenuItemPositionVO mi : getMenuItems()) {
			FieldValidation.validateNumber(mi.getPosition(), position_key, 0, 100, validationError);
			if (validationError.hasError()) {
				return;
			}
		}
	}

	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() throws SQLException {
		Collections.sort(this.getMenuItems(), new Comparator<MenuItemPositionVO>() {
			public int compare(MenuItemPositionVO o1, MenuItemPositionVO o2) {
				int result = Integer.valueOf(o1.getPosition()).compareTo(Integer.valueOf(o2.getPosition()));
				if (result != 0) {
					return result;
				}
				return o1.getMenuItemName().compareTo(o2.getMenuItemName());
			}
		});
	}

	@Override
	public void initWith(int id) throws SQLException {
		this.setCountryId(id);
		MenuItemDAO menuItemDAO = DAOManager.getMenuItemDAO();
		MenuItemExample menuItemExample = new MenuItemExample();
		Criteria criteria = menuItemExample.createCriteria();
		criteria.andIdCountryEqualTo(id);
		List<MenuItem> menuItems = menuItemDAO.selectMenuItemByExample(menuItemExample);
		this.getMenuItems().clear();
		for (MenuItem mi : menuItems) {
			this.getMenuItems().add(new MenuItemPositionVO(mi));
		}
		this.init();
	}

	@Override
	public void save() throws SQLException, ValidationException {
		MenuItemDAO menuItemDAO = DAOManager.getMenuItemDAO();
		for (MenuItemPositionVO menuItemPositionVO : this.getMenuItems()) {
			MenuItem menuItem = new MenuItem();
			menuItem.setId(menuItemPositionVO.getMenuItemId());
			menuItem.setPosition(Integer.valueOf(menuItemPositionVO.getPosition()));
			menuItemDAO.updateMenuItemByPrimaryKeySelective(menuItem);
		}
	}
	
	public MenuItemPositionVO getMenuItem(int index) {
		return this.getMenuItems().get(index);
	}
	
	public void setMenuItem(int index, MenuItemPositionVO menuItemPositionVO) {
		this.getMenuItems().set(index, menuItemPositionVO);
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public List<MenuItemPositionVO> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItemPositionVO> menuItems) {
		this.menuItems = menuItems;
	}

}
