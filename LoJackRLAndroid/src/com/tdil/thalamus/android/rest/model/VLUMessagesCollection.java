package com.tdil.thalamus.android.rest.model;

import java.util.Collection;

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
