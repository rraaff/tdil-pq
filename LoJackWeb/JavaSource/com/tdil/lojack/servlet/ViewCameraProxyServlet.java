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

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import EDU.oswego.cs.dl.util.concurrent.ConcurrentHashMap;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.camera.IPCamera;
import com.tdil.lojack.struts.forms.CameraForm;
import com.tdil.lojack.utils.LoJackConfig;
import com.tdil.lojack.utils.LoJackWebUtils;
import com.tdil.thalamus.client.core.ProxyConfiguration;
import com.tdil.web.NoCacheFilter;

public class ViewCameraProxyServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;
	
	public static byte[] noise;
	
	private static Map<String, HttpMethodBase> inProgress = new ConcurrentHashMap();
	
	private static int TIMEOUT = 2000;
	private static ProxyConfiguration PROXY;
	
	static {
		InputStream httpIn = null;
		try {
			ByteArrayOutputStream jpgOut = new ByteArrayOutputStream(8192);
			httpIn = new BufferedInputStream(ViewCameraProxyServlet.class.getResourceAsStream("noise.jpg"), 8192);
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
			HttpMethodBase load = inProgress.get(cameraForm.getUrl());
			if (load != null) {
				try {
					load.abort();
				} catch (Exception e) {
				}
				inProgress.remove(cameraForm.getUrl());
			}
			try {
				GetMethod httpMethod = new GetMethod(LoJackConfig.getCameraMobileExternalUrl());
				inProgress.put(cameraForm.getUrl(), httpMethod);
				httpMethod.setQueryString(new NameValuePair[] {new NameValuePair("username", cameraForm.getUsername()),
						new NameValuePair("password", cameraForm.getPassword()),
						new NameValuePair("url", cameraForm.getUrl()),
						new NameValuePair("model", cameraForm.getModel())});
				HttpClient client = new HttpClient();
				//configureTimeout(client);
				client.executeMethod(httpMethod);
				int statusCode = httpMethod.getStatusCode();
				InputStream in = httpMethod.getResponseBodyAsStream();
				IOUtils.copy(in, resp.getOutputStream());
				in.close();
			} finally {
				inProgress.remove(cameraForm.getUrl());
			}
		}
	}
	
	private static void configureTimeout(HttpClient client) {
		HttpConnectionManager connectionManager = client.getHttpConnectionManager();
		connectionManager.getParams().setSoTimeout(getTIMEOUT());
		if (getPROXY() != null) {
			client.getHostConfiguration().setProxy(getPROXY().getServer(), getPROXY().getPort());
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ViewCameraProxyServlet.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	public static int getTIMEOUT() {
		return TIMEOUT;
	}

	public static void setTIMEOUT(int tIMEOUT) {
		TIMEOUT = tIMEOUT;
	}

	public static ProxyConfiguration getPROXY() {
		return PROXY;
	}

	public static void setPROXY(ProxyConfiguration pROXY) {
		PROXY = pROXY;
	}
}
