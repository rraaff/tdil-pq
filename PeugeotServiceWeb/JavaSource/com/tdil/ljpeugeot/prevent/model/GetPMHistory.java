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
	
	@XStreamImplicit
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
