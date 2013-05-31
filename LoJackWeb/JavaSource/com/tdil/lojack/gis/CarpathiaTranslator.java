package com.tdil.lojack.gis;

public class CarpathiaTranslator {

	private static String START = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\"><s:Body><";
	private static String MIDDLE1 = " xmlns=\"http://tempuri.org/\"><request>";
	private static String MIDDLE2 = "</request></";
	private static String END = "></s:Body></s:Envelope>";
	
	
	public static String prepareRequest(String method, String body) {
		return START + method + MIDDLE1 + body + MIDDLE2 + method + END;
	}
	
	public static String extractResponse(String method, String responseString) {
		String startTag = "<"+method+"Result>";
		String result = responseString.substring(responseString.indexOf(startTag) + startTag.length());
		result = result.substring(0, result.indexOf("</"+method+"Result>"));
		return result;
	}
}
