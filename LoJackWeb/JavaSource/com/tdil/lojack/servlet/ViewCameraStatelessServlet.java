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

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import EDU.oswego.cs.dl.util.concurrent.ConcurrentHashMap;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.camera.IPCamera;
import com.tdil.lojack.camera.PanasonicBLC131;
import com.tdil.lojack.camera.TPLinkSC4171G;
import com.tdil.web.NoCacheFilter;

public class ViewCameraStatelessServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;
	
	private static Map<String, IPCamera> inProgress = new ConcurrentHashMap();
	
	public static byte[] noise;
	
	static {
		InputStream httpIn = null;
		try {
			ByteArrayOutputStream jpgOut = new ByteArrayOutputStream(8192);
			httpIn = new BufferedInputStream(ViewCameraStatelessServlet.class.getResourceAsStream("noise.jpg"), 8192);
			int cur = 0;
			while ((cur = httpIn.read()) >= 0) {
				if (jpgOut != null) {
					jpgOut.write((byte) cur);
				}
			}
			noise = jpgOut.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpIn != null) {
				try {
					httpIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NoCacheFilter.setNoCache(resp);
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String url = req.getParameter("url");
		String model = req.getParameter("model");
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
			InputStream inputStream = null;
			try {
				inputStream = camera.nextFrame();
				if (inputStream != null) {
					IOUtils.copy(inputStream, resp.getOutputStream());
				} else {
					if (noise != null) {
						resp.getOutputStream().write(noise);
					}
				}
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		} finally {
			inProgress.remove(url);
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
