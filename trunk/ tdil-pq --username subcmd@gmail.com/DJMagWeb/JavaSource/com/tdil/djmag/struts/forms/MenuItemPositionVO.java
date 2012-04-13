package com.tdil.djmag.struts.forms;

import java.io.Serializable;

import com.tdil.djmag.model.MenuItem;

public class MenuItemPositionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4656910179080344260L;
	private Integer menuItemId;
	private String menuItemName;
	private String position;
	
	public MenuItemPositionVO(MenuItem menuItem) {
		super();
		this.setMenuItemId(menuItem.getId());
		this.setMenuItemName(menuItem.getName());
		this.setPosition(String.valueOf(menuItem.getPosition()));
	}
	
	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String sectionName) {
		this.menuItemName = sectionName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(Integer menuItemId) {
		this.menuItemId = menuItemId;
	}
	
}
