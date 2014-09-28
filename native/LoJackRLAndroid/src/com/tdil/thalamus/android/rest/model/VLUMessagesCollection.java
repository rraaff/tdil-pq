package com.tdil.thalamus.android.rest.model;

import java.io.Serializable;
import java.util.Collection;

public class VLUMessagesCollection implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1917940917655340346L;
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
