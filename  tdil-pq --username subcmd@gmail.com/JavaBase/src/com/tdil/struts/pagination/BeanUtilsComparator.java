package com.tdil.struts.pagination;

import java.util.Comparator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.forms.TransactionalValidationForm;

public class BeanUtilsComparator extends CriterionComparator {

	public BeanUtilsComparator() {
		super();
	}
	
	public int compare(Object o1, Object o2) {
		String toCompare1 = "";
		String toCompare2 = "";
		try {
			if (o1 != null) {
				Object prop1 = BeanUtils.getProperty(o1, this.getCriterion());
				if (prop1 != null) {
					toCompare1 = prop1.toString();
				}
			}
			if (o2 != null) {
				Object prop2 = BeanUtils.getProperty(o2, this.getCriterion());
				if (prop2 != null) {
					toCompare2 = prop2.toString();
				}
			}
			if (this.isAsc()) {
				return toCompare1.compareTo(toCompare2);
			} else { 
				return toCompare2.compareTo(toCompare1);
			}
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			return 0;
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(BeanUtilsComparator.class);
	}

}
