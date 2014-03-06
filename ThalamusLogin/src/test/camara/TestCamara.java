package test.camara;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class TestCamara {

	public static void main(String[] args) {
		HttpURLConnection conn;
		BufferedInputStream httpIn;
		URL url;
		Base64 base64 = new Base64();

		try {
			url = new URL("http://demo-life.dyndns.org:8980/jpg/image.jpg");
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL");
			return;
		}

		/*System.out.println("test no proxy");
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "Basic " + new String(base64.encode(("operator" + ":" + "operator1").getBytes())));
			httpIn = new BufferedInputStream(conn.getInputStream(), 8192);
			writeImage(httpIn, "no proxy");
		} catch (Exception e) {
			System.err.println("Unable to connect no proxy: " + e.getMessage());
		}*/

		System.out.println("test with proxy " + args[0] + " port " + args[1]);
		try {
			conn = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(args[0], Integer
					.parseInt(args[1]))));
			conn.setRequestProperty("Authorization", "Basic " + new String(base64.encode(("operator" + ":" + "operator1").getBytes())));
			httpIn = new BufferedInputStream(conn.getInputStream(), 8192);
			writeImage(httpIn, "proxy-" + args[0] + "-" + args[1]);
		} catch (Exception e) {
			System.err.println("Unable to connect no proxy: " + e.getMessage());
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
