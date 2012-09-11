package com.tdil.tuafesta.utils;

import org.apache.commons.lang.StringUtils;

import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalChange;

public class ProfesionalUtils {

	
	public static boolean mergePersonalData(Profesional profesional, ProfesionalChange profesionalChange) {
		boolean result = false;
		if (!StringUtils.isEmpty(profesionalChange.getFirstname())) {
			profesional.setFirstname(profesionalChange.getFirstname());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getLastname())) {
			profesional.setLastname(profesionalChange.getLastname());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getSex())) {
			profesional.setSex(profesionalChange.getSex());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhoneareacode())) {
			profesional.setPhoneareacode(profesionalChange.getPhoneareacode());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhonenumber())) {
			profesional.setPhonenumber(profesionalChange.getPhonenumber());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhoneextension())) {
			profesional.setPhoneextension(profesionalChange.getPhoneextension());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhonetype())) {
			profesional.setPhonetype(profesionalChange.getPhonetype());
			result = true;
		}
		return result;
	}
	
	public static boolean mergeBusinessData(Profesional profesional, ProfesionalChange profesionalChange) {
		boolean result = false;
		if (!StringUtils.isEmpty(profesionalChange.getBusinessname())) {
			profesional.setBusinessname(profesionalChange.getBusinessname());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getCuit())) {
			profesional.setCuit(profesionalChange.getCuit());
			result = true;
		}
		if (profesionalChange.getIdGeolevel() != null && profesionalChange.getIdGeolevel() != 0) {
			profesional.setIdGeolevel(profesionalChange.getIdGeolevel());
			result = true;
		}
		if (profesionalChange.getIdProfilePicture() != null && profesionalChange.getIdProfilePicture() != 0) {
			profesional.setIdProfilePicture(profesionalChange.getIdProfilePicture());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getDescription())) {
			profesional.setDescription(profesionalChange.getDescription());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo1())) {
			profesional.setVideo1(profesionalChange.getVideo1());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo2())) {
			profesional.setVideo2(profesionalChange.getVideo2());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo3())) {
			profesional.setVideo3(profesionalChange.getVideo3());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo4())) {
			profesional.setVideo4(profesionalChange.getVideo4());
			result = true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo5())) {
			profesional.setVideo5(profesionalChange.getVideo5());
			result = true;
		}
		
		return result;
	}
	
}
