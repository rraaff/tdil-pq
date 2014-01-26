package com.tdil.lojack.rest.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.tdil.lojack.vlu.model.VLUDataDTO;

@XmlRootElement
public class VLUMessagesCollection {
	
	private Collection<VLUDataDTO> vluData;
	
	public VLUMessagesCollection() {
	}
	
	public VLUMessagesCollection(Collection<VLUDataDTO> vluData) {
		super();
		this.vluData = vluData;
	}

	public Collection<VLUDataDTO> getVluData() {
		return vluData;
	}

	public void setVluData(Collection<VLUDataDTO> vluData) {
		this.vluData = vluData;
	}


}
