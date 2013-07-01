package com.tdil.struts.forms;

import java.sql.SQLException;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;

public interface ApproveDisapproveForm {

	ValidationError validate();
	
	void basicValidate(ValidationError error);
	
	String getOperation();

	void initForApprove() throws SQLException;
	
	void initForReview() throws SQLException;
	
	void approve() throws SQLException, ValidationException;
	
	void disapprove() throws SQLException, ValidationException;

	void postApprove();

	void postDisapprove();

}
