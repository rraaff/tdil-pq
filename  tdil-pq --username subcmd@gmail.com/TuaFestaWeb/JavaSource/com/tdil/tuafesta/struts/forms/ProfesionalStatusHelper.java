package com.tdil.tuafesta.struts.forms;

import com.tdil.struts.resources.ApplicationResources;
import com.tdil.tuafesta.model.Profesional;

public class ProfesionalStatusHelper {

	public static final String EMAIL_NOT_VALIDATED = "emailNotValidated";
	public static final String DATA_NOT_COMPLETE = "dataNotComplete";
	public static final String DATA_MODIFIED = "dataModified";
	public static final String DATA_UNMODIFIED = "dataUnmodified";
	
	public static final String allStatus[] = {EMAIL_NOT_VALIDATED, DATA_NOT_COMPLETE, DATA_MODIFIED, DATA_UNMODIFIED};
	
	public static String getStatusFor(Profesional profesional) {
		if (profesional.getEmailvalid().equals(0)) {
			return ApplicationResources.getMessage(EMAIL_NOT_VALIDATED);
		} else {
			return ApplicationResources.getMessage(DATA_NOT_COMPLETE);
		}
	}
}
