package com.tdil.lojack.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.camera.IPCamera;
import com.tdil.lojack.utils.CameraCache;
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
			
			byte[] image = CameraCache.getImage(url);
			if (image != null) {
				resp.getOutputStream().write(image);
			} else {
			
				IPCamera camera = IPCamera.createIPCamera(model, url, username, password);
	//			inProgress.put(url, camera);
				InputStream inputStream = null;
				try {
					inputStream = camera.nextFrame();
					if (inputStream != null) {
						ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
						IOUtils.copy(inputStream, byteArrayOutputStream);
						inputStream.close();
						byte[] cameraImage = byteArrayOutputStream.toByteArray();
						CameraCache.putImage(url, cameraImage);
						resp.getOutputStream().write(cameraImage);
					} else {
						resp.getOutputStream().write(ViewCameraServlet.noise);
					}
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
				}
			}
		} finally {
//			inProgress.remove(url);
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ViewCameraStatelessServlet.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
