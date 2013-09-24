package com.tdil.lojack.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.Light;
import com.tdil.lojack.model.AsyncJob;
import com.tdil.lojack.model.PointOfInterest;
import com.tdil.lojack.utils.LoJackWebUtils;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.web.NoCacheFilter;

public class GetLightJobSatesServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		configureResponse(resp);
		if (LoJackWebUtils.isHomeUserLogged(req)) {
			WebsiteUser websiteUser = (WebsiteUser)req.getSession(false).getAttribute("user");
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			// busco los jobs que tenga en la session en estado final y los retorno,
			// si paso mas de x tiempo los asumo zoombies
			List<AsyncJob> finished = websiteUser.getAndRemoveFinishedJobs();
			if (!finished.isEmpty()) {
				Collection<Light> lights = LoJackServicesConnector.getLights(websiteUser);
				for (AsyncJob asyncJob : finished) {
					Light light = getLightFor(lights, asyncJob.getIdentidad(), asyncJob.getIdluz());
					Map<String, Object> job = new HashMap<String, Object>();
					job.put("idEntidad", asyncJob.getIdentidad());
					job.put("idLuz", asyncJob.getIdluz());
					job.put("status", light.getStatusDescription());
					job.put("unknown", light.isStatusUnknown());
					job.put("ran", light.isInRandomMode());
					job.put("on", light.isOn());
					result.add(job);
				}
			}
			// escribo el json..
			JSONArray jsonArray = JSONArray.fromObject(result);
			resp.getOutputStream().write(jsonArray.toString().getBytes());
		}
	}


	public static Light getLightFor(Collection<Light> lights, Integer identidad, Integer idLuz) {
		for (Light a : lights) {
			if (identidad.equals(a.getIdEntidad())) {
				if (idLuz.equals(a.getIdLuz())) {
					return a;
				}
			}
		}
		return null;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(GetLightJobSatesServlet.class);
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
