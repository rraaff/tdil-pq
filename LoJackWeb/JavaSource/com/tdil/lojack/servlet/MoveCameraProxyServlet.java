package com.tdil.lojack.servlet;

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
import com.tdil.web.NoCacheFilter;

public class MoveCameraProxyServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5611834065781809280L;
	
	private static Map<String, HttpMethodBase> inProgress = new ConcurrentHashMap();

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
			String mapKey = req.getSession().getId() + cameraForm.getUrl();
			HttpMethodBase load = inProgress.get(mapKey);
			if (load != null) {
				try {
					load.abort();
				} catch (Exception e) {
				}
				inProgress.remove(mapKey);
			}
			try {
				GetMethod httpMethod = new GetMethod(LoJackConfig.getCameraMobileExternalUrl() + "moveCameraStateless");
				inProgress.put(mapKey, httpMethod);
				httpMethod.setQueryString(new NameValuePair[] {new NameValuePair("username", cameraForm.getUsername()),
						new NameValuePair("password", cameraForm.getPassword()),
						new NameValuePair("url", cameraForm.getUrl()),
						new NameValuePair("model", cameraForm.getModel()),
						new NameValuePair("dir", req.getParameter("dir"))});
				HttpClient client = new HttpClient();
				configureTimeout(client);
				client.executeMethod(httpMethod);
				int statusCode = httpMethod.getStatusCode();
				InputStream in = httpMethod.getResponseBodyAsStream();
				IOUtils.copy(in, resp.getOutputStream());
				in.close();
			} finally {
				inProgress.remove(mapKey);
			}
		}
	}
	
	private static void configureTimeout(HttpClient client) {
		HttpConnectionManager connectionManager = client.getHttpConnectionManager();
		connectionManager.getParams().setSoTimeout(LoJackConfig.getCameraConnectTimeOut());
		boolean isHttps = LoJackConfig.getCameraMobileExternalUrl().toLowerCase().startsWith("https");
		if (isHttps && IPCamera.getProxyConfigurationHttps() != null) {
			client.getHostConfiguration().setProxy(IPCamera.getProxyConfigurationHttps().getServer(), IPCamera.getProxyConfigurationHttps().getPort());
		}
		if (!isHttps && IPCamera.getProxyConfiguration() != null) {
			client.getHostConfiguration().setProxy(IPCamera.getProxyConfiguration().getServer(), IPCamera.getProxyConfiguration().getPort());
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(MoveCameraProxyServlet.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
