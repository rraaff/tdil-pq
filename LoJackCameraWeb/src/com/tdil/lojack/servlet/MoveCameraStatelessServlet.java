package com.tdil.lojack.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdil.lojack.camera.IPCamera;
import com.tdil.web.NoCacheFilter;

public class MoveCameraStatelessServlet extends HttpServlet {

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
		String dir = req.getParameter("dir");
//		IPCamera camara = inProgress.get(url);
//		if (camara != null) {
//			camara.cancelDownload();
//			inProgress.remove(url);
//		}
		try {
			IPCamera camera = IPCamera.createIPCamera(model, url, username, password);
//			inProgress.put(url, camera);
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
//			inProgress.remove(url);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
