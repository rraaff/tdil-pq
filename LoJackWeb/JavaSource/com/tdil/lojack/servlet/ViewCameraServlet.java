package com.tdil.lojack.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.struts.forms.CameraForm;

public class ViewCameraServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5611834065781809280L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CameraForm cameraForm = (CameraForm)req.getSession().getAttribute("CameraForm");
		resp.setContentType(cameraForm.getCamera().getMimeType());
		InputStream inputStream = null;
		try {
			inputStream = cameraForm.getCamera().nextFrame();
			if (inputStream != null) {
				IOUtils.copy(inputStream, resp.getOutputStream());
			} else {
				// TODO enviar una imagen na ...
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
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
