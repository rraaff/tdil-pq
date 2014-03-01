package com.tdil.ljpeugeot.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdil.log4j.LoggerProvider;
import com.tdil.web.NoCacheFilter;

public class LogJSErrorServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(LogJSErrorServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NoCacheFilter.setNoCache(resp);
		String msg = req.getParameter("msg");
		String url = req.getParameter("url");
		String line = req.getParameter("line");
		LOG.error("JSERR msg " + msg + " ulr " + url + " line " + line);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
