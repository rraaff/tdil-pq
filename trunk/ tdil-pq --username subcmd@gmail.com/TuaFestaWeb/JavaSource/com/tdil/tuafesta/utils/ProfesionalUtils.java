package com.tdil.tuafesta.utils;

import org.apache.commons.lang.StringUtils;

import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalChange;

public class ProfesionalUtils {

	
	public static boolean mergePersonalData(Profesional profesional, ProfesionalChange profesionalChange) {
		boolean result = false;
		if (!StringUtils.isEmpty(profesionalChange.getFirstname())) {
			profesional.setFirstname(profesionalChange.getFirstname());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getLastname())) {
			profesional.setLastname(profesionalChange.getLastname());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getSex())) {
			profesional.setSex(profesionalChange.getSex());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhoneareacode())) {
			profesional.setPhoneareacode(profesionalChange.getPhoneareacode());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhonenumber())) {
			profesional.setPhonenumber(profesionalChange.getPhonenumber());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhoneextension())) {
			profesional.setPhoneextension(profesionalChange.getPhoneextension());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhonetype())) {
			profesional.setPhonetype(profesionalChange.getPhonetype());
			return true;
		}
		return result;
	}
	
	public static boolean personalDataChanged(ProfesionalChange profesionalChange) {
		if (!StringUtils.isEmpty(profesionalChange.getFirstname())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getLastname())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getSex())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhoneareacode())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhonenumber())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhoneextension())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getPhonetype())) {
			return true;
		}
		return false;
	}
	
	public static boolean mergeBusinessData(Profesional profesional, ProfesionalChange profesionalChange) {
		boolean result = false;
		if (!StringUtils.isEmpty(profesionalChange.getBusinessname())) {
			profesional.setBusinessname(profesionalChange.getBusinessname());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getCuit())) {
			profesional.setCuit(profesionalChange.getCuit());
			return true;
		}
		if (profesionalChange.getIdGeolevel() != null && profesionalChange.getIdGeolevel() != 0) {
			profesional.setIdGeolevel(profesionalChange.getIdGeolevel());
			return true;
		}
		if (profesionalChange.getIdProfilePicture() != null && profesionalChange.getIdProfilePicture() != 0) {
			profesional.setIdProfilePicture(profesionalChange.getIdProfilePicture());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getDescription())) {
			profesional.setDescription(profesionalChange.getDescription());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo1())) {
			profesional.setVideo1(profesionalChange.getVideo1());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo2())) {
			profesional.setVideo2(profesionalChange.getVideo2());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo3())) {
			profesional.setVideo3(profesionalChange.getVideo3());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo4())) {
			profesional.setVideo4(profesionalChange.getVideo4());
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo5())) {
			profesional.setVideo5(profesionalChange.getVideo5());
			return true;
		}
		
		return result;
	}
	
	public static boolean businessDataChanged(ProfesionalChange profesionalChange) {
		if (!StringUtils.isEmpty(profesionalChange.getBusinessname())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getCuit())) {
			return true;
		}
		if (profesionalChange.getIdGeolevel() != null && profesionalChange.getIdGeolevel() != 0) {
			return true;
		}
		if (profesionalChange.getIdProfilePicture() != null && profesionalChange.getIdProfilePicture() != 0) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getDescription())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo1())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo2())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo3())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo4())) {
			return true;
		}
		if (!StringUtils.isEmpty(profesionalChange.getVideo5())) {
			return true;
		}
		
		return false;
	}
	
}
