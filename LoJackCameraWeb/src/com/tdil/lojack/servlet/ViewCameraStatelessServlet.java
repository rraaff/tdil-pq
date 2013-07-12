package com.tdil.lojack.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.tdil.lojack.camera.IPCamera;
import com.tdil.lojack.camera.PanasonicBLC131;
import com.tdil.lojack.camera.TPLinkSC4171G;
import com.tdil.web.NoCacheFilter;

public class ViewCameraStatelessServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;
	
//	private static Map<String, IPCamera> inProgress = new ConcurrentHashMap();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NoCacheFilter.setNoCache(resp);
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String url = req.getParameter("url");
		String model = req.getParameter("model");
//		IPCamera camara = inProgress.get(url);
//		if (camara != null) {
//			camara.cancelDownload();
//			inProgress.remove(url);
//		}
		try {
			IPCamera camera = null;
			if (model.equals(PanasonicBLC131.PANASONIC_BLC131)) {
				camera = new PanasonicBLC131(url, username, password);
			}
			if (model.equals(TPLinkSC4171G.TP_LINK_SC4171G)) {
				camera = new TPLinkSC4171G(url, username, password);
			}
//			inProgress.put(url, camera);
			InputStream inputStream = null;
			try {
				inputStream = camera.nextFrame();
				if (inputStream != null) {
					IOUtils.copy(inputStream, resp.getOutputStream());
				} else {
					resp.getOutputStream().write(ViewCameraServlet.noise);
				}
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		} finally {
//			inProgress.remove(url);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
