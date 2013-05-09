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
import com.tdil.utils.encryption.DesEncrypter;

public class CameraConfigServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (LoJackWebUtils.isUserLogged(req)) {
			CameraForm cameraForm = (CameraForm)req.getSession().getAttribute("CameraForm");
			StringBuilder sb = new StringBuilder();
			sb.append(cameraForm.getUsername()).append(",");
			sb.append(cameraForm.getPassword()).append(",");
			sb.append(cameraForm.getUrl()).append(",");
			sb.append(cameraForm.getModel());
			try {
				DesEncrypter encrypter = new DesEncrypter("esta es la clave de la camara para lojack");
				resp.getOutputStream().write(encrypter.encrypt(sb.toString().getBytes()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(CameraConfigServlet.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
