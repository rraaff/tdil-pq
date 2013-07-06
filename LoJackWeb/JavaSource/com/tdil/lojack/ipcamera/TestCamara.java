package com.tdil.lojack.ipcamera;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class TestCamara {

	  
	public static void main(String[] args) {
		HttpURLConnection conn;
		BufferedInputStream httpIn;
		URL url;
	    Base64Encoder base64 = new Base64Encoder();
	    
	    try {
	      url = new URL("http://demo-life.dyndns.org:8980/jpg/image.jpg");
	    }
	    catch (MalformedURLException e) {
	      System.err.println("Invalid URL");
	      return;
	    }
	    
	    try {
	    	System.out.println("test no proxy");
	      conn = (HttpURLConnection)url.openConnection();
	      conn.setRequestProperty("Authorization", "Basic " + base64.encode(("operator" + ":" + "operator1").getBytes()));
	      httpIn = new BufferedInputStream(conn.getInputStream(), 8192);
	      writeImage(httpIn, "no proxy");
	      
	      System.out.println("test with proxy " + args[0] + " port " + args[1]);
	      conn = (HttpURLConnection)url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(args[0], Integer.parseInt(args[1]))));
	      conn.setRequestProperty("Authorization", "Basic " + base64.encode(("operator" + ":" + "operator1").getBytes()));
	      httpIn = new BufferedInputStream(conn.getInputStream(), 8192);
	      writeImage(httpIn, "proxy-" + args[0] + "-" + args[1]);
	      
	    } catch (IOException e) {
	      System.err.println("Unable to connect: " + e.getMessage());
	      return;
	    }
	}

	private static void writeImage(BufferedInputStream httpIn, String testCase) {
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
					httpIn.close();
					httpIn = null;
				}
				prev = cur;
			}
			if (jpgOut == null) {
				return;
			}
			FileOutputStream fout = new FileOutputStream(testCase + "-" + System.currentTimeMillis() + ".jpg");
			fout.write(jpgOut.toByteArray());
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("I/O Error: " + e.getMessage());
			return;
		} 
	}
}
