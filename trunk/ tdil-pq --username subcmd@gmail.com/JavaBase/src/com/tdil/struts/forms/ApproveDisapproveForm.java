package com.tdil.struts.forms;

import java.sql.SQLException;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;

public interface ApproveDisapproveForm {

	public ValidationError validate();
	
	public void basicValidate(ValidationError error);
	
	public String getOperation();

	void initForApprove() throws SQLException;
	
	void initForReview() throws SQLException;
	
	void approve() throws SQLException, ValidationException;
	
	void disapprove() throws SQLException, ValidationException;

	public void postApprove();

	public void postDisapprove();

}
