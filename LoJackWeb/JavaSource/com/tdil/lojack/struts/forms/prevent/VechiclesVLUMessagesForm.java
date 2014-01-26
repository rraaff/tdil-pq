package com.tdil.lojack.struts.forms.prevent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tdil.lojack.model.VLUData;
import com.tdil.lojack.prevent.model.Vehicle;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.lojack.vlu.VLUUtils;
import com.tdil.lojack.vlu.model.VLUDataDTO;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class VechiclesVLUMessagesForm extends VehiclesForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 655195546310304497L;

	private List<VLUDataDTO> vlDataDTOs;

	public List<VLUDataDTO> getVlDataDTOs() {
		return vlDataDTOs;
	}

	public void setVlDataDTOs(List<VLUDataDTO> vlDataDTOs) {
		this.vlDataDTOs = vlDataDTOs;
	}
	
	@Override
	public void initWith(WebsiteUser user) throws CommunicationException, HttpStatusException, InvalidResponseException,
			UnauthorizedException {
		super.initWith(user);
		List<VLUData> vluDatas = VLUUtils.getVLUData(user.getDni());
		List<VLUDataDTO> vlDataDTOs = new ArrayList<VLUDataDTO>();
		Set<String> added = new HashSet<String>();
		for (VLUData data : vluDatas) {
			vlDataDTOs.add(new VLUDataDTO(data));
			added.add(data.getDomain());
		}
		for (Vehicle ve : getVehicles()) {
			if (!added.contains(ve.getDescription())) {
				VLUDataDTO vluDataDTO = new VLUDataDTO();
				vluDataDTO.setDni(user.getDni());
				vluDataDTO.setDomain(ve.getDescription());
				vlDataDTOs.add(vluDataDTO);
				added.add(ve.getDescription());
			}
		}
		setVlDataDTOs(vlDataDTOs);
	}
}
