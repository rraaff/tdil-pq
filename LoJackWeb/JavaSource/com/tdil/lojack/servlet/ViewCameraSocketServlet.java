package com.tdil.lojack.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import EDU.oswego.cs.dl.util.concurrent.ConcurrentHashMap;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.camera.IPCamera;
import com.tdil.lojack.struts.forms.CameraForm;
import com.tdil.lojack.utils.LoJackConfig;
import com.tdil.lojack.utils.LoJackWebUtils;
import com.tdil.web.NoCacheFilter;

public class ViewCameraSocketServlet extends HttpServlet {

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
				
				// bajada via socket
				Socket requestSocket = null;
				if (LoJackConfig.getSOCKS_PROXY() != null) {
					Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(LoJackConfig.getSOCKS_PROXY().getServer(), LoJackConfig.getSOCKS_PROXY().getPort()));
					requestSocket = new Socket(proxy);
				    InetSocketAddress socketAddress = new InetSocketAddress(LoJackConfig.getCameraMobileSocket().getServer(), LoJackConfig.getCameraMobileSocket().getPort());
				    requestSocket.connect(socketAddress);
				}
				if (requestSocket == null) {
					requestSocket = new Socket(LoJackConfig.getCameraMobileSocket().getServer(), LoJackConfig.getCameraMobileSocket().getPort());
				}
				OutputStream out = requestSocket.getOutputStream();
				StringBuilder request = new StringBuilder();
				request.append(cameraForm.getUsername()).append(",");
				request.append(cameraForm.getPassword()).append(",");
				request.append(cameraForm.getUrl()).append(",");
				request.append(cameraForm.getModel()).append(",");
				request.append("img").append("\n");
				out.write(request.toString().getBytes());
				InputStream in = requestSocket.getInputStream();
				IOUtils.copy(in, resp.getOutputStream());
				in.close();
			} catch (Exception e) {
				resp.getOutputStream().write(ViewCameraServlet.noise);
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
		return LoggerProvider.getLogger(ViewCameraSocketServlet.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
