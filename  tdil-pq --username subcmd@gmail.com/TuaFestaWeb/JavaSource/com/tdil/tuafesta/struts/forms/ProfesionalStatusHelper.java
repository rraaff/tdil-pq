package com.tdil.tuafesta.struts.forms;

import com.tdil.struts.resources.ApplicationResources;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalStatus;

public class ProfesionalStatusHelper {

	public static final String EMAIL_NOT_VALIDATED = "emailNotValidated";
	public static final String VERIFICATION_PENDING = "verificationPending";
	
	public static final String APPROVED = "approved";
	
	public static final String DATA_NOT_COMPLETE = "dataNotComplete";
	public static final String DATA_MODIFIED = "dataModified";
	public static final String DATA_UNMODIFIED = "dataUnmodified";
	
	public static final String BLOCKED = "blocked";
	
	public static final String allStatus[] = {EMAIL_NOT_VALIDATED, VERIFICATION_PENDING, DATA_NOT_COMPLETE, DATA_MODIFIED, DATA_UNMODIFIED};
	
	public static String getStatusFor(Profesional profesional) {
		if (profesional.getStatus().equals(ProfesionalStatus.EMAIL_VALIDATION_PENDING)) {
			return ApplicationResources.getMessage(EMAIL_NOT_VALIDATED);
		} else {
			if (profesional.getStatus().equals(ProfesionalStatus.VERIFICATION_PENDING)) {
				return ApplicationResources.getMessage(VERIFICATION_PENDING);
			} else {
				if (profesional.getStatus().equals(ProfesionalStatus.BLOCKED)) {
					return ApplicationResources.getMessage(BLOCKED);
				} else {
					if (profesional.getStatus().equals(ProfesionalStatus.APPROVED)) {
						return ApplicationResources.getMessage(APPROVED); // TODO esto debe considerar si tiene los datos modificados o no
						// y usar diferente mensaje para esto
					} else {
						return ApplicationResources.getMessage(DATA_NOT_COMPLETE);
					}
				}
			}
		}
	}
}
