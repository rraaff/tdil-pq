package com.tdil.ljpeugeot.prevent.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.tdil.ljpeugeot.prevent.PreventXMLUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(value="GetPMHistory")
public class GetPMHistory implements Serializable {

	private static final long serialVersionUID = 8760240826905090226L;
	
	public static String OK = "0";
	public static String INVALID_ID_ERROR = "1";
	public static String TOKEN_VALIDATION_ERROR = "2";
	public static String CONNECTION_ERROR = "3";
	public static String DATA_ERROR = "4";
	public static String TIMEOUT_ERROR = "5";
	public static String GENERAL_ERROR = "6";
	
	@XStreamAlias(value="PMHistoryCollection")
	private List<PositionHistory> item;
	
	@XStreamAlias(value="status")
	private String status;

	public List<PositionHistory> getItem() {
		return item;
	}

	public void setItem(List<PositionHistory> positionHistory) {
		this.item = positionHistory;
	}

	public static void main(String[] args) throws IOException {
		Object o = PreventXMLUtils.fromXML(IOUtils.toString(GetPMHistory.class.getResourceAsStream("GetPMHistory.xml")));
		System.out.println(o);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
