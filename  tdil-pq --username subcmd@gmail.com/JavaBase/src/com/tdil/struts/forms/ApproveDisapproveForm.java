package com.tdil.struts.forms;

import java.sql.SQLException;

import com.tdil.struts.ValidationException;

public interface ApproveDisapproveForm {

	public String getOperation();

	void approve() throws SQLException, ValidationException;
	
	void disapprove() throws SQLException, ValidationException;

	public void postApprove();

	public void postDisapprove();

}
