package com.tdil.struts.forms;

import com.tdil.struts.ValidationException;

public interface RefreshableForm {

	void refresh() throws ValidationException;
}
