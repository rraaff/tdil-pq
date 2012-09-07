package com.tdil.struts.forms;

import com.tdil.struts.ValidationException;

public interface RefreshableForm {

	public void refresh() throws ValidationException;
}
