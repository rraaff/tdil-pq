package com.tdil.milka.model.valueobjects;

import com.tdil.struts.resources.ApplicationResources;

public class StatusHelper {

	public static String getStatusRB(int deleted, int status) {
		if (deleted == 1) {
			return ApplicationResources.getMessage("data.status.deleted");
		} else {
			if (status == 2) {
				return ApplicationResources.getMessage("data.status.disapproved");
			} else {
				if (status == 1) {
					return ApplicationResources.getMessage("data.status.approved");
				} else {
					return ApplicationResources.getMessage("data.status.pending");
				}
			}
		}
	}
}
