package com.tdil.lojack.servlet;

import java.io.BufferedInputStream;
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
import com.tdil.lojack.struts.forms.CameraForm;
import com.tdil.lojack.utils.LoJackWebUtils;
import com.tdil.web.NoCacheFilter;

public class ViewCameraServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;
	
	public static byte[] noise;
	
	static {
		InputStream httpIn = null;
		try {
			ByteArrayOutputStream jpgOut = new ByteArrayOutputStream(8192);
			httpIn = new BufferedInputStream(ViewCameraServlet.class.getResourceAsStream("noise.jpg"), 8192);
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
		if (LoJackWebUtils.isHomeUserLogged(req)) {
			CameraForm cameraForm = (CameraForm)req.getSession().getAttribute("CameraForm");
			if (cameraForm == null) {
				return;
			}
			resp.setContentType(cameraForm.getCamera().getMimeType());
			InputStream inputStream = null;
			try {
				inputStream = cameraForm.getCamera().nextFrame();
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
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ViewCameraServlet.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
