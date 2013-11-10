package com.tdil.struts.forms;

import java.sql.SQLException;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;

public interface PhysicalDeleteForm {

	void initForDeleteWith(int userId) throws SQLException;

	void validateForToggleDeletedFlag(ValidationError validationError);

	void physicalDelete() throws SQLException, ValidationException;
	
	void resetAfterDelete() throws SQLException;

}
