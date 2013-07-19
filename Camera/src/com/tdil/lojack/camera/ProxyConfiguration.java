package com.tdil.lojack.camera;

public class ProxyConfiguration {

	private String server;
	private int port;
	
	public ProxyConfiguration(String server, int port) {
		super();
		this.server = server;
		this.port = port;
	}

	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	@Override
	public String toString() {
		return this.getServer() + ":" + this.getPort();
	}
}
