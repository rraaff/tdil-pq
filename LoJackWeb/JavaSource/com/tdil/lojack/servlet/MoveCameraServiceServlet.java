package com.tdil.lojack.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.struts.forms.CameraForm;
import com.tdil.lojack.utils.LoJackWebUtils;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.web.NoCacheFilter;

public class MoveCameraServiceServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NoCacheFilter.setNoCache(resp);
		if (LoJackWebUtils.isHomeUserLogged(req)) {
			String cameraUrl = req.getParameter("cameraUrl");
			CameraForm cameraForm = (CameraForm)req.getSession().getAttribute(ViewCameraServiceServlet.CAMERA_SERVICE_FORM);
			if (cameraForm == null) {
				cameraForm = new CameraForm();
				WebsiteUser user = (WebsiteUser)req.getSession(false).getAttribute("user");
				cameraForm.initWith(user);
				req.getSession().setAttribute(ViewCameraServiceServlet.CAMERA_SERVICE_FORM, cameraForm);
			}
			String direction = req.getParameter("dir");
			if ("left".equals(direction)) {
				cameraForm.getCamera(cameraUrl).left();
			}
			if ("right".equals(direction)) {
				cameraForm.getCamera(cameraUrl).right();
			}
			if ("up".equals(direction)) {
				cameraForm.getCamera(cameraUrl).up();
			}
			if ("down".equals(direction)) {
				cameraForm.getCamera(cameraUrl).down();
			}
			// escribir datos, como para poder tomarlo via ajax, o nada..
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(MoveCameraServiceServlet.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
