package com.tdil.ga;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tdil.web.ProxyConfiguration;

public class MeasurementProtocol {

	protected static String HTTP = "http://www.google-analytics.com/collect?";
	protected static String HTTPS = "https://ssl.google-analytics.com/collect?";
	
	private static boolean useHttps = false;
	
	private static ProxyConfiguration proxyConfiguration;
	
	private static ExecutorService executor = Executors.newFixedThreadPool(5);

	public static void track(PageView runnable) {
		executor.submit(runnable);
	}

	public static boolean useHttps() {
		return useHttps;
	}

	public static void setUseHttps(boolean useHttps) {
		MeasurementProtocol.useHttps = useHttps;
	}

	public static ProxyConfiguration getProxyConfiguration() {
		return proxyConfiguration;
	}

	public static void setProxyConfiguration(ProxyConfiguration proxyConfiguration) {
		MeasurementProtocol.proxyConfiguration = proxyConfiguration;
	}
}
