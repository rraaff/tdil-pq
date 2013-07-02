package com.tdil.lojack.camera;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

public class TPLinkSC4171G extends IPCamera {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6077871117739337461L;
	private static final String IMAGE = "/jpg/image.jpg";
	private static final String LEFT = "/cgi-bin/operator/ptzset?move=left";
	private static final String RIGHT = "/cgi-bin/operator/ptzset?move=right";
	private static final String UP = "/cgi-bin/operator/ptzset?move=up";
	private static final String DOWN = "/cgi-bin/operator/ptzset?move=down";
	public static final String TP_LINK_SC4171G = "1";

	public TPLinkSC4171G(String url, String username, String password) {
		super(url, username, password);
	}

	@Override
	public String getMimeType() {
		return "image/jpeg";
	}

	@Override
	public ByteArrayInputStream nextFrame() {
		HttpURLConnection conn = null;
		BufferedInputStream httpIn = null;
		URL url;
		ProxyConfiguration proxyConfiguration = null;
		try {
			if (getUrl().toLowerCase().startsWith("https")) {
				proxyConfiguration = getProxyConfigurationHttps();
			} else {
				proxyConfiguration = getProxyConfiguration();
			}
			url = new URL(this.getUrl() + IMAGE);
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL");
			return null;
		}
		try {
			if (proxyConfiguration != null) {
				conn = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyConfiguration.getServer(), proxyConfiguration.getPort())));
			} else {
				conn = (HttpURLConnection) url.openConnection();
			}
			configureTimeout(conn);
			conn.setRequestProperty("Authorization", this.getBasicAuth());
			httpIn = new BufferedInputStream(conn.getInputStream(), 8192);
			int prev = 0;
			int cur = 0;
			ByteArrayOutputStream jpgOut = null;
			try {
				while (httpIn != null && (cur = httpIn.read()) >= 0) {
					if (prev == 0xFF && cur == 0xD8) {
						jpgOut = new ByteArrayOutputStream(8192);
						jpgOut.write((byte) prev);
					}
					if (jpgOut != null) {
						jpgOut.write((byte) cur);
					}
					if (prev == 0xFF && cur == 0xD9) {
						jpgOut.close();
						httpIn = null;
					}
					prev = cur;
				}
				if (jpgOut == null) {
					return null;
				}
				return new ByteArrayInputStream(jpgOut.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("I/O Error: " + e.getMessage());
				return null;
			}
		} catch (IOException e) {
			System.err.println("Unable to connect: " + e.getMessage());
			return null;
		} finally {
			if (httpIn != null) {
				try {
					httpIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	@Override
	public void down() {
		readFully(this.getUrl() + DOWN);
	}

	@Override
	public void left() {
		readFully(this.getUrl() + LEFT);
	}

	@Override
	public void right() {
		readFully(this.getUrl() + RIGHT);
	}

	@Override
	public void up() {
		readFully(this.getUrl() + UP);
	}
}
