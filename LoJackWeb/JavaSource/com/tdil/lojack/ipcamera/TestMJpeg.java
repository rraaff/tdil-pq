package com.tdil.lojack.ipcamera;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class TestMJpeg {

	  
	public static void main(String[] args) {
		HttpURLConnection conn;
		BufferedInputStream httpIn;
		URL url;
	    Base64Encoder base64 = new Base64Encoder();
	    
	    try {
	      url = new URL("http://ljcam2.dyndns.org:8888/jpg/image.jpg");
	    }
	    catch (MalformedURLException e) {
	      System.err.println("Invalid URL");
	      return;
	    }
	    
	    try {
	      conn = (HttpURLConnection)url.openConnection();
	      conn.setRequestProperty("Authorization", "Basic " + base64.encode(("preisinger" + ":" + "lj2013").getBytes()));
	      httpIn = new BufferedInputStream(conn.getInputStream(), 8192);
	      int prev = 0;
	      int cur = 0;
	      OutputStream jpgOut = null;
	      byte curFrame[] = new byte[0];
	      try {
	        while (httpIn != null && (cur = httpIn.read()) >= 0) {
	          if (prev == 0xFF && cur == 0xD8) {
	            //jpgOut = new ByteArrayOutputStream(8192);
	        	  jpgOut = new FileOutputStream("/home/mgodoy/temp/" + System.currentTimeMillis() + ".jpg");
	            jpgOut.write((byte)prev);
	          }
	          if (jpgOut != null) {
	            jpgOut.write((byte)cur);
	          }
	          if (prev == 0xFF && cur == 0xD9) {
	            synchronized(curFrame) {
	              //curFrame = jpgOut.toByteArray();
	            }
	            //frameAvailable = true;
	            jpgOut.close();
	          }
	          prev = cur;
	        }
	      }
	      catch (IOException e) {
	        System.err.println("I/O Error: " + e.getMessage());
	      }
	    }
	    catch (IOException e) {
	      System.err.println("Unable to connect: " + e.getMessage());
	      return;
	    }
	}
}
