package com.tdil.milka.web;

import java.sql.SQLException;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.ClickCounter;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;

public class MeltButton implements TransactionalAction {

	private int id;
	private int quantity;
	
	public MeltButton(int id) {
		this.id = id;
	}
	
	
	//<div id="mb-2" buttonType="photomilka" buttonId="2" quantity="150"></div>
	public static String meltButton(ClickCounter c) {
		StringBuffer result = new StringBuffer();
		result.append("<div id=\"mb-").append(c.getId());
		result.append("\" buttonId=\"");
		result.append(c.getId()).append("\" quantity=\"").append(c.getClicks().toString()).append("\"></div>");
		return result.toString();
	}
	
	public static String meltButton(int id) {
		MeltButton meltButton = new MeltButton(id);
		try {
			TransactionProvider.executeInTransaction(meltButton);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer result = new StringBuffer();
		result.append("<div id=\"mb-").append(meltButton.id);
		result.append("\" buttonId=\"");
		result.append(meltButton.id).append("\" quantity=\"").append(meltButton.quantity).append("\"></div>");
		return result.toString();
	}
	
	public void executeInTransaction() throws SQLException {
		// TODO Auto-generated method stub
		ClickCounter c = DAOManager.getClickCounterDAO().selectClickCounterByPrimaryKey(this.id);
		this.quantity = c.getClicks();
	}
}
