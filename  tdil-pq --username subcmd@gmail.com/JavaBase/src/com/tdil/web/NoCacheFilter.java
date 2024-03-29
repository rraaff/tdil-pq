package com.tdil.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/** checks whether user has logged in.  Session attribute logged indicates whether user has logged or not.
 If session attribute  logged is present, it means user has logged in otherwise send control to login.html.

 This filter must NOT be processed when login.jsp is called. So we check whether the requested URL is login.jsp.
 If so we do nothing in this filter.
 */

public class NoCacheFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (response instanceof HttpServletResponse) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			setNoCache(httpResponse);
		}
		chain.doFilter(request, response);
	}

	public static void setNoCache(HttpServletResponse httpResponse) {
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.setHeader("Pragma", "No-cache");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}
}
