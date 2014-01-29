package com.tdil.lojack.struts.forms;

import java.util.List;

import net.sf.json.JSONArray;

import com.tdil.lojack.struts.forms.beans.OptIn;

public interface IPerson {

	public abstract int getDocumentType();

	public abstract String getDocument();

	public abstract String getPassword();

	public abstract String getFirstName();

	public abstract String getLastName();

	public abstract String getBirthDate();

	public abstract String getGender();

	public abstract String getEmail();

	public abstract String getPhoneIntCode();

	public abstract String getPhoneNumber();

	public abstract String getPhoneAreaCode();

	public abstract String getPhoneType();

	public abstract String getCountrySelected();

	public abstract int getCountryId();

	public abstract int getStateId();

	public abstract String getStreet1();

	public abstract String getStreet2();

	public abstract String getCity();

	public abstract String getPostalCode();

	public abstract String getAddressType();
	
	public JSONArray getSocialConnections();
	
	public List<OptIn> getOptIns();

}