package com.tdil.lojack.servlet;

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
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.model.PointOfInterest;
import com.tdil.lojack.utils.LoJackWebUtils;
import com.tdil.lojack.utils.ParkingUtils;

public class SearchParkingsServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		configureResponse(resp);
		if (LoJackWebUtils.isUserLogged(req)) {
			double lat = Double.valueOf(req.getParameter("lat"));
			double lon = Double.valueOf(req.getParameter("lon"));
			double rad = Double.valueOf(req.getParameter("rad"));
			CircularWindow rex = new CircularWindow(new LatLng(lat, lon), rad, LengthUnit.METER);
			List<PointOfInterest> pois = ParkingUtils.getParkings();
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			for (PointOfInterest poi : pois) {
				if (rex.contains(ParkingUtils.getLatLng(poi))) {
					result.add(getParkingJson(poi));
				}
			}
			// escribo el json..
			JSONArray jsonArray = JSONArray.fromObject(result);
			resp.getOutputStream().write(jsonArray.toString().getBytes());
		}
	}

	private Map<String, Object> getParkingJson(PointOfInterest poi) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", poi.getName());
		result.put("desc", poi.getDescription());
		result.put("lat", poi.getLat().doubleValue());
		result.put("lon", poi.getLon().doubleValue());
		return result;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(SearchParkingsServlet.class);
	}

	private void configureResponse(HttpServletResponse resp) {
		resp.setContentType(this.getContentType());
		resp.setHeader("cache-control", "no-cache");
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
