package com.tdil.struts.pagination;

import java.util.Comparator;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilsComparator implements Comparator<Object> {

	private String propertyName;
	private boolean asc = true;

	public BeanUtilsComparator(String propertyName, boolean asc) {
		super();
		this.propertyName = propertyName;
		this.asc = asc;
	}

	public int compare(Object o1, Object o2) {
		String toCompare1 = "";
		String toCompare2 = "";
		try {
			if (o1 != null) {
				Object prop1 = BeanUtils.getProperty(o1, propertyName);
				if (prop1 != null) {
					toCompare1 = prop1.toString();
				}
			}
			if (o2 != null) {
				Object prop2 = BeanUtils.getProperty(o2, propertyName);
				if (prop2 != null) {
					toCompare2 = prop2.toString();
				}
			}
			if (asc) {
				return toCompare1.compareTo(toCompare2);
			} else { 
				return toCompare2.compareTo(toCompare1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
