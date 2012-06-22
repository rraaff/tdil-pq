package com.tdil.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * <%=DisplayTagParamHelper.getParams(request)%>
 * @author mgodoy
 *
 */

public class DisplayTagParamHelper {

	public static String getParams(HttpServletRequest request) {
		StringBuffer result = new StringBuffer();
		if (!StringUtils.isEmpty(request.getParameter("page"))) {
			result.append("&page=").append(request.getParameter("page"));
		}
		if (!StringUtils.isEmpty(request.getParameter("sort"))) {
			result.append("&sort=").append(request.getParameter("sort"));
		}
		if (!StringUtils.isEmpty(request.getParameter("dir"))) {
			result.append("&dir=").append(request.getParameter("dir"));
		}
		return result.toString();
	}
}
