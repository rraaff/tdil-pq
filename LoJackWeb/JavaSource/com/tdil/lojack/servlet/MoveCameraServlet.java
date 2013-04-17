package com.tdil.lojack.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.struts.forms.CameraForm;

public class MoveCameraServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5611834065781809280L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// resp.setContentType(blobLocalData.getMimeType());
		// TODO enviar el content type.. jpg, segun la camara
		// TODO params de no cache
		CameraForm cameraForm = (CameraForm)req.getSession().getAttribute("CameraForm");
		String direction = req.getParameter("dir");
		if ("left".equals(direction)) {
			cameraForm.getCamera().left();
		}
		if ("right".equals(direction)) {
			cameraForm.getCamera().right();
		}
		if ("up".equals(direction)) {
			cameraForm.getCamera().up();
		}
		if ("down".equals(direction)) {
			cameraForm.getCamera().down();
		}
		// escribir datos, como para poder tomarlo via ajax, o nada..
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(MoveCameraServlet.class);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
