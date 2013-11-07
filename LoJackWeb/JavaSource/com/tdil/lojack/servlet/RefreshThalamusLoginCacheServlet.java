package com.tdil.lojack.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.thalamus.ThalamusLoginCache;
import com.tdil.lojack.utils.LoJackWebUtils;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.web.NoCacheFilter;

public class RefreshThalamusLoginCacheServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(RefreshThalamusLoginCacheServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NoCacheFilter.setNoCache(resp);
		WebsiteUser user = LoJackWebUtils.getLoggedUser(req);
		if (user == null) {
			if (LOG.isInfoEnabled()) {
				LOG.info("RefreshThalamusLoginCacheServlet: El usuario no esta logueado");
			}
			resp.getOutputStream().write("{\"result\": \"ERR\"}".getBytes());
		} else {
			if (LOG.isInfoEnabled()) {
				LOG.info("RefreshThalamusLoginCacheServlet: El usuario esta logueado");
			}
			ThalamusLoginCache.updateCache(user);
			resp.getOutputStream().write("{\"result\": \"OK\"}".getBytes());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
