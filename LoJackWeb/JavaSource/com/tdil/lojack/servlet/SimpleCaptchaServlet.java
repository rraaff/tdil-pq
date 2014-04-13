package com.tdil.lojack.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octo.captcha.service.CaptchaServiceException;

public class SimpleCaptchaServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5160423954179661853L;
	
	String sImgType = null;
	private int height = 0;
	private int width = 0;
	public static final String CAPTCHA_KEY = "captcha_key_name";

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		// For this servlet, supported image types are PNG and JPG.
		sImgType = servletConfig.getInitParameter("ImageType");
		sImgType = sImgType == null ? "png" : sImgType.trim().toLowerCase();
		if (!sImgType.equalsIgnoreCase("png") && !sImgType.equalsIgnoreCase("jpg") && !sImgType.equalsIgnoreCase("jpeg")) {
			sImgType = "png";
		}
		height = Integer.parseInt(getServletConfig().getInitParameter("height"));
		width = Integer.parseInt(getServletConfig().getInitParameter("width"));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedImage image = null;
		Graphics2D graphics2D = null;
		Hashtable<TextAttribute, Object> map = null;
		Random r = null;
		String token = null;
		String ch = null;
		Color c = null;
		GradientPaint gp = null;
		Font font = null;
		if (request.getQueryString() != null && request.getQueryString().indexOf("CSRF=") == -1) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "GET request should have no query string.");
			return;
		}
		try {
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			graphics2D = image.createGraphics();
			map = new Hashtable<TextAttribute, Object>();
			r = new Random();
			token = Long.toString(Math.abs(r.nextLong()), 36);
			ch = token.substring(0, 6);
			c = new Color(0.6662f, 0.4569f, 0.3232f);
			gp = new GradientPaint(30, 30, c, 15, 25, Color.white, true);
			graphics2D.setPaint(gp);
			font = new Font("Verdana", Font.CENTER_BASELINE, 26);
			graphics2D.setFont(font);
			graphics2D.drawString(ch, 2, 20);
			graphics2D.dispose();
			request.getSession().setAttribute(CAPTCHA_KEY, ch);
		} catch (CaptchaServiceException cse) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Problem generating captcha image.");
			return;
		} finally {
			// Set appropriate http headers.
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/" + (sImgType.equalsIgnoreCase("png") ? "png" : "jpeg"));
			OutputStream outputStream = response.getOutputStream();
			ImageIO.write(image, sImgType, outputStream);
			outputStream.close();
			image = null;
			graphics2D = null;
			map = null;
			r = null;
			token = null;
			ch = null;
			c = null;
			gp = null;
			font = null;
		}
	}

	public static boolean validateCaptchaWithSession(String paramValue, HttpSession session) {
		boolean bValidated = false;
		if (!paramValue.equalsIgnoreCase((String)session.getAttribute(CAPTCHA_KEY))) {
			bValidated = false;
		} else {
			bValidated = true;
		}
		return bValidated;
	}
}