package com.tdil.lojack.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import EDU.oswego.cs.dl.util.concurrent.ConcurrentHashMap;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.camera.IPCamera;
import com.tdil.lojack.camera.PanasonicBLC131;
import com.tdil.lojack.camera.TPLinkSC4171G;
import com.tdil.web.NoCacheFilter;

public class MoveCameraStatelessServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;
	
	private static Map<String, IPCamera> inProgress = new ConcurrentHashMap();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NoCacheFilter.setNoCache(resp);
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String url = req.getParameter("url");
		String model = req.getParameter("model");
		String dir = req.getParameter("dir");
		IPCamera camara = inProgress.get(url);
		if (camara != null) {
			camara.cancelDownload();
			inProgress.remove(url);
		}
		try {
			IPCamera camera = null;
			if (model.equals(PanasonicBLC131.PANASONIC_BLC131)) {
				camera = new PanasonicBLC131(url, username, password);
			}
			if (model.equals(TPLinkSC4171G.TP_LINK_SC4171G)) {
				camera = new TPLinkSC4171G(url, username, password);
			}
			inProgress.put(url, camera);
			if ("left".equals(dir)) {
				camera.left();
			}
			if ("right".equals(dir)) {
				camera.right();
			}
			if ("up".equals(dir)) {
				camera.up();
			}
			if ("down".equals(dir)) {
				camera.down();
			}
		} finally {
			inProgress.remove(url);
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(MoveCameraStatelessServlet.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
