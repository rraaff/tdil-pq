package com.tdil.ljpeugeot.prevent.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="PolygonalSecureZoneResponse")
public class PolygonalSecureZoneResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8027637831225237698L;
	
	public static String OK = "0";
	public static String VALIDATION_ERROR = "1";
	public static String TOKEN_VALIDATION_ERROR = "2";
	public static String CONNECTION_ERROR = "3";
	public static String DATA_ERROR = "4";
	public static String TIMEOUT_ERROR = "5";
	public static String GENERAL_ERROR = "6";
	
	@XStreamAlias(value="secureZoneID")
	private String secureZoneID;
	@XStreamAlias(value="status")
	private String status;
	
	
	public String getSecureZoneID() {
		return secureZoneID;
	}
	public void setSecureZoneID(String secureZoneID) {
		this.secureZoneID = secureZoneID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
