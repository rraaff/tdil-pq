package com.tdil.lojack.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServlet;

import com.tdil.lojack.camera.IPCamera;

public class ViewCameraServlet extends HttpServlet {

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

}
