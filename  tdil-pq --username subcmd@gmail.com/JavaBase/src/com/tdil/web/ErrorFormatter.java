package com.tdil.web;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

import com.tdil.struts.resources.ApplicationResources;

public class ErrorFormatter {

	public static String getErrorsFrom(ServletRequest request) {
		StringBuffer result = new StringBuffer();
		Object o = request.getAttribute(org.apache.struts.Globals.MESSAGE_KEY);
		if (o != null) {
			org.apache.struts.action.ActionMessages ae = (org.apache.struts.action.ActionMessages) o;
			// Get the locale and message resources bundle
			// Loop thru all the labels in the ActionMessage's
			for (java.util.Iterator i = ae.properties(); i.hasNext();) {
				String property = (String) i.next();
				for (java.util.Iterator it = ae.get(property); it.hasNext();) {
					org.apache.struts.action.ActionMessage a = (org.apache.struts.action.ActionMessage) it.next();
					String key = a.getKey();
					// TODO result.append(ResourceBundleCache.get("mensajeDeError", key)).append("<br/>");
					result.append(key).append("<br/>");
				}
			}
		}
		return result.toString();
	}

	public static String getErrorFrom(ServletRequest request, String error) {
		StringBuffer result = new StringBuffer();
		Object o = request.getAttribute(org.apache.struts.Globals.ERROR_KEY);
		if (o != null) {
			org.apache.struts.action.ActionMessages ae = (org.apache.struts.action.ActionMessages) o;
			// Get the locale and message resources bundle
			// Loop thru all the labels in the ActionMessage's
			for (java.util.Iterator it = ae.get(error); it.hasNext();) {
				org.apache.struts.action.ActionMessage a = (org.apache.struts.action.ActionMessage) it.next();
				String key = a.getKey();
				String rb = ApplicationResources.getMessage(key);
				if (StringUtils.isEmpty(rb)) {
					return key;
				} else {
					return rb;
				}
			}
		}
		return "";
	}
	
	public static String getErrorFrom(ServletRequest request, String error, String start, String end) {
		StringBuffer result = new StringBuffer();
		Object o = request.getAttribute(org.apache.struts.Globals.ERROR_KEY);
		if (o != null) {
			org.apache.struts.action.ActionMessages ae = (org.apache.struts.action.ActionMessages) o;
			// Get the locale and message resources bundle
			// Loop thru all the labels in the ActionMessage's
			for (java.util.Iterator it = ae.get(error); it.hasNext();) {
				org.apache.struts.action.ActionMessage a = (org.apache.struts.action.ActionMessage) it.next();
				String key = a.getKey();
				result.append(start);
				String rb = ApplicationResources.getMessage(key);
				if (StringUtils.isEmpty(rb)) {
					result.append(key);
				} else {
					result.append(rb);
				}
				result.append(end);
			}
		}
		return result.toString();
	}
}
