package com.tdil.lojack.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.servlet.GetAlarmJobSatesServlet;

public class ProfilingFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();

		if (getLog().isDebugEnabled()) {
			long end = System.currentTimeMillis();
			// TODO loguear
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ProfilingFilter.class);
	}

}
