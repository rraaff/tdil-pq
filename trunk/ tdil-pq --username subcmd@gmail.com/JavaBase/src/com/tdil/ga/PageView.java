package com.tdil.ga;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;

public class PageView implements Runnable {

	private String v = "1";
	private String tid = "UA-XXXX-Y";
	private String cid = "asdasdasd";
	private String uid = "as8eknlll";
	private String ua = "Opera/9.80 (Windows NT 6.0) Presto/2.12.388 Version/12.14";
	private String dp = "/a/a";
	private String dt = "Test de marcos";
	private String t = "pageview";
	
	private static final Logger logger = LoggerProvider.getLogger(PageView.class);
	
	public PageView(String tid, String cid, String uid, String dp, String dt, String ua) {
		super();
		this.tid = tid;
		this.cid = cid;
		this.uid = uid;
		this.dp = dp;
		this.dt = dt;
		this.ua = ua;
	}

	public String asPayload() {
		StringBuilder builder = new StringBuilder();
		builder.append("v=").append(v);
		builder.append("&tid=").append(tid);
		builder.append("&cid=").append(cid);
		builder.append("&uid=").append(uid);
		builder.append("&t=").append(t);
		builder.append("&dp=").append(dp);
		builder.append("&dt=").append(URLEncoder.encode(dt));
		return builder.toString();
	}
	
	@Override
	public void run() {
		BufferedReader in = null;
		InputStream conIn = null;
		DataOutputStream wr = null;
		OutputStream conOut = null;
		try {
			String url = null;
			String payload = asPayload();
			if (MeasurementProtocol.useHttps()) {
				url = MeasurementProtocol.HTTPS + payload;
			} else {
				url = MeasurementProtocol.HTTP + payload;
			}
			URL obj = new URL(url);
			URLConnection con = null;
			if (MeasurementProtocol.getProxyConfiguration() != null) {
				con = obj.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(MeasurementProtocol.getProxyConfiguration().getServer(), MeasurementProtocol.getProxyConfiguration().getPort())));
			} else {
				con = obj.openConnection();
			}
			con.setRequestProperty("User-Agent", ua);
			con.setDoOutput(true);
			conOut = con.getOutputStream();
			wr = new DataOutputStream(conOut);
			wr.writeBytes(this.asPayload());
			wr.flush();
			conIn = con.getInputStream();
			in = new BufferedReader(
			        new InputStreamReader(conIn));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {}
			}
			if (conIn != null) {
				try {
					conIn.close();
				} catch (IOException e) {}
			}
			if (wr != null) {
				try {
					wr.close();
				} catch (IOException e) {}
			}
			if (conOut != null) {
				try {
					conOut.close();
				} catch (IOException e) {}
			}
		}
 
	}
	
}
