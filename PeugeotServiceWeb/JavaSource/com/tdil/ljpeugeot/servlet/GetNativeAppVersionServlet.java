package com.tdil.ljpeugeot.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.javadocmd.simplelatlng.window.CircularWindow;
import com.tdil.ljpeugeot.model.NativeApp;
import com.tdil.ljpeugeot.model.PointOfInterest;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.ljpeugeot.utils.LJPeugeotWebUtils;
import com.tdil.ljpeugeot.utils.ParkingUtils;
import com.tdil.log4j.LoggerProvider;
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
		NativeApp app = PeugeotService.getNativeAppByCode(code);
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
