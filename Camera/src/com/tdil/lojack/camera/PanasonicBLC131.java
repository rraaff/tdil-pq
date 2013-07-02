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

public class PanasonicBLC131 extends IPCamera {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7542741455885976217L;
	private static final String IMAGE = "/nphMotionJpeg?Resolution=320x240&Quality=Standard";
	private static final String LEFT = "/nphControlCamera?Direction=PanLeft";
	private static final String RIGHT = "/nphControlCamera?Direction=PanRight";
	private static final String UP = "/nphControlCamera?Direction=TiltUp";
	private static final String DOWN = "/nphControlCamera?Direction=TiltDown";
	public static final String PANASONIC_BLC131 = "2";

	public PanasonicBLC131(String url, String username, String password) {
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
			handleException(e);
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
