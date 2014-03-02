package com.tdil.ljpeugeot.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdil.log4j.LoggerProvider;
import com.tdil.web.NoCacheFilter;

public class LogJSErrorServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(LogJSErrorServlet.class);
	
	public static byte[] noise;

	static {
		InputStream httpIn = null;
		try {
			ByteArrayOutputStream jpgOut = new ByteArrayOutputStream(8192);
			httpIn = new BufferedInputStream(LogJSErrorServlet.class.getResourceAsStream("null.jpg"), 8192);
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
		resp.setContentType("image/jpeg");
		NoCacheFilter.setNoCache(resp);
		String msg = req.getParameter("msg");
		String url = req.getParameter("url");
		String line = req.getParameter("line");
		LOG.error("JSERR msg " + msg + " ulr " + url + " line " + line);
		resp.getOutputStream().write(LogJSErrorServlet.noise);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
