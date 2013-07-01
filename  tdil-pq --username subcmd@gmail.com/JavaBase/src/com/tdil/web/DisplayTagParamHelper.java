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
		StringBuilder result = new StringBuilder();
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
	
	public static String getFields(HttpServletRequest request) {
		StringBuilder result = new StringBuilder();
		if (!StringUtils.isEmpty(request.getParameter("page"))) {
			result.append("<input type=\"hidden\" name=\"page\" value=\"").append(request.getParameter("page")).append("\">");
		}
		if (!StringUtils.isEmpty(request.getParameter("sort"))) {
			result.append("<input type=\"hidden\" name=\"sort\" value=\"").append(request.getParameter("sort")).append("\">");
		}
		if (!StringUtils.isEmpty(request.getParameter("dir"))) {
			result.append("<input type=\"hidden\" name=\"dir\" value=\"").append(request.getParameter("dir")).append("\">");
		}
		return result.toString();
	}
}
