package com.tdil.lojack.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.model.NativeApp;
import com.tdil.lojack.services.LoJackService;
import com.tdil.web.NoCacheFilter;

public class GetNativeAppVersionServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		configureResponse(resp);
		String code = req.getParameter("code");
		NativeApp app = LoJackService.getNativeAppByCode(code);
		if (app != null) {
			resp.getOutputStream().write(app.getVersion().getBytes());
		} else {
			resp.getOutputStream().write("null".getBytes());
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(GetNativeAppVersionServlet.class);
	}

	private void configureResponse(HttpServletResponse resp) {
		resp.setContentType(this.getContentType());
		NoCacheFilter.setNoCache(resp);
	}
	protected String getContentType() {
		return "text/html; charset=ISO-8859-1";
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
