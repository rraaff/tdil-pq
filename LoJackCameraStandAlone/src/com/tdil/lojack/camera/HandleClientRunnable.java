package com.tdil.lojack.camera;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class HandleClientRunnable implements Runnable {

	private Socket clientConnection;
	private static final String IMG_COMMAND = "img";
	private static final String LEFT_COMMAND = "left";
	private static final String RIGHT_COMMAND = "right";
	private static final String UP_COMMAND = "up";
	private static final String DOWN_COMMAND = "down";
	
	public HandleClientRunnable(Socket clientConnection) {
		super();
		this.clientConnection = clientConnection;
	}

	@Override
	public void run() {
		handleRequest(clientConnection); // multiples threads
	}

	private static void handleRequest(Socket s) {
		try {
			execute(s);
			s.close();
		} catch (IOException e) {
			CameraServer.Log.error(e.getMessage(), e);
		}
	}
	
	private static void execute(Socket s) {
		if (CameraServer.Log.isInfoEnabled()) {
			CameraServer.Log.info("handling connection for " + s.getInetAddress());
		} 
		BufferedReader in = null;
		OutputStream out = null;
		ByteArrayInputStream byteArrayInputStream = null;
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String inputLine = in.readLine().trim();
			if (CameraServer.Log.isDebugEnabled()) {
				CameraServer.Log.debug("received " + inputLine);
			}
			String parameters[] = inputLine.split(",");
			String username = parameters[0];
			String password = parameters[1];
			String url = parameters[2];
			String model = parameters[3];
			String command = parameters[4];
			IPCamera camera = null;
			if (TPLinkSC4171G.TP_LINK_SC4171G.equals(model)) {
				camera = new TPLinkSC4171G(url, username, password);
			}
			if (PanasonicBLC131.PANASONIC_BLC131.equals(model)) {
				camera = new PanasonicBLC131(url, username, password);
			}
			camera.setConnectTimeOut(CameraServer.connectTimeOut);
			camera.setReadTimeOut(CameraServer.readTimeOut);
			out = s.getOutputStream();
			if (IMG_COMMAND.equals(command)) {
				byteArrayInputStream = camera.nextFrame();
				if (byteArrayInputStream != null) {
					IOUtils.copy(byteArrayInputStream, out);
					out.flush();
					out.close();
				}
			}
			if (CameraServer.Log.isInfoEnabled()) {
				CameraServer.Log.info(command + " command");
			}
			if (LEFT_COMMAND.equals(command)) {
				camera.left();
			}
			if (RIGHT_COMMAND.equals(command)) {
				camera.right();
			}
			if (UP_COMMAND.equals(command)) {
				camera.up();
			}
			if (DOWN_COMMAND.equals(command)) {
				camera.down();
			}
			if (CameraServer.Log.isInfoEnabled()) {
				CameraServer.Log.info("execution finished");
			}
		} catch (IOException ioe) {
			throw new RuntimeException();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {}
			try {
				if (byteArrayInputStream != null) {
					byteArrayInputStream.close();
				}
			} catch (IOException e) {}
		}
	}
}
