package com.tdil.lojack.camera;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class CameraServer {

	public static Logger Log = Logger.getLogger(CameraServer.class);
	
	private static ServerSocket serverSocket;
	public static int connectTimeOut;
	public static int readTimeOut;
	public static Executor executor;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Params config.properties");
		Properties properties = new Properties();
		InputStream inStream = null;
		try {
			inStream = new FileInputStream(new File(args[0]));
			properties.load(inStream);
		} finally {
			try {
				inStream.close();
			} catch (IOException e) {}
		}
		
		DOMConfigurator.configureAndWatch(properties.getProperty("log4j.location"));
		executor = Executors.newCachedThreadPool();
		Integer port = Integer.parseInt(properties.getProperty("server.port"));
		connectTimeOut = Integer.parseInt(properties.getProperty("camera.connectTimeOut"));
		readTimeOut = Integer.parseInt(properties.getProperty("camera.readTimeOut"));
		
		Log.fatal("Camera server");
		Log.fatal("port: " + port);
		Log.fatal("connectTimeOut: " + connectTimeOut);
		Log.fatal("readTimeOut: " + readTimeOut);

		String cameraProxyHttp = properties.getProperty("camera.proxy.http");
		if (!StringUtils.isEmpty(cameraProxyHttp)) {
			String proxyConf[] = cameraProxyHttp.split(":");
			IPCamera.setProxyConfiguration(new ProxyConfiguration(proxyConf[0], Integer.valueOf(proxyConf[1])));
			Log.fatal("Http proxy is " + IPCamera.getProxyConfiguration());
		}
		String cameraProxyHttps = properties.getProperty("camera.proxy.http");
		if (!StringUtils.isEmpty(cameraProxyHttps)) {
			String proxyConf[] = cameraProxyHttps.split(":");
			IPCamera.setProxyConfigurationHttps(new ProxyConfiguration(proxyConf[0], Integer.valueOf(proxyConf[1])));
			Log.fatal("Https proxy is " + IPCamera.getProxyConfigurationHttps());
		}
		
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket s = serverSocket.accept();
				executor.execute(new HandleClientRunnable(s));
			}
		} catch (IOException e) {
			CameraServer.Log.error(e.getMessage(), e);
		}
	}

}
