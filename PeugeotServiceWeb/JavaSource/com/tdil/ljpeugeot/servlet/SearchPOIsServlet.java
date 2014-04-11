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

import com.tdil.ljpeugeot.model.PointOfInterest;
import com.tdil.ljpeugeot.utils.LJPeugeotWebUtils;
import com.tdil.ljpeugeot.utils.ParkingUtils;
import com.tdil.log4j.LoggerProvider;
import com.tdil.web.NoCacheFilter;

public class SearchPOIsServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		configureResponse(resp);
		if (LJPeugeotWebUtils.isUserLogged(req)) {
			String type = req.getParameter("poiType");
			List<PointOfInterest> pois = ParkingUtils.getPois(type);
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			for (PointOfInterest poi : pois) {
				result.add(getPoiJson(poi));
			}
			// escribo el json..
			JSONArray jsonArray = JSONArray.fromObject(result);
			resp.getOutputStream().write(jsonArray.toString().getBytes());
		}
	}

	private Map<String, Object> getPoiJson(PointOfInterest poi) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", poi.getName().replace('\'', ' '));
		result.put("desc", poi.getDescription().replace('\'', ' '));
		result.put("lat", poi.getLat().doubleValue());
		result.put("lon", poi.getLon().doubleValue());
		result.put("poiType", poi.getType());
		return result;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(SearchPOIsServlet.class);
	}

	private void configureResponse(HttpServletResponse resp) {
		resp.setContentType(this.getContentType());
		NoCacheFilter.setNoCache(resp);
	}
	protected String getContentType() {
		return "application/json; charset=ISO-8859-1";
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
