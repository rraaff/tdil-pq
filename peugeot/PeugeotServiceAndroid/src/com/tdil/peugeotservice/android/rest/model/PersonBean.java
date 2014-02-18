package com.tdil.peugeotservice.android.rest.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class PersonBean {

	private int documentType = 1;
	private String document;
	private String password;
	private String firstName;
	private String lastName;
	private String birthDate; // format yyyy-MM-dd""
	private String gender; // Male Female
	private String email;
	
	// celular, opcional
	private String phoneIntCode = "54"; // Argentina, hardcodeado?
	private String phoneNumber;
	private String phoneAreaCode;
	private String phoneType = "cellphone"; // celular, hardcodeado
	
	private String countrySelected;
	private int countryId; // Argentina, hardcodeado
	private int stateId;
	private String street1; // opcional
	private String street2; // opcional
	private String city; // opcional
	private String postalCode; // opcional
	private String addressType;
	
	private boolean optIn;
	
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getDocumentType()
	 */
	public int getDocumentType() {
		return documentType;
	}
	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getDocument()
	 */
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getPassword()
	 */
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getLastName()
	 */
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getBirthDate()
	 */
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getGender()
	 */
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getEmail()
	 */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getPhoneIntCode()
	 */
	
	public String getPhoneIntCode() {
		return phoneIntCode;
	}
	public void setPhoneIntCode(String phoneIntCode) {
		this.phoneIntCode = phoneIntCode;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getPhoneNumber()
	 */
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getPhoneAreaCode()
	 */
	
	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}
	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getPhoneType()
	 */
	
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getCountrySelected()
	 */
	
	public String getCountrySelected() {
		return countrySelected;
	}
	public void setCountrySelected(String countrySelected) {
		this.countrySelected = countrySelected;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getCountryId()
	 */
	
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getStateId()
	 */
	
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getStreet1()
	 */
	
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getStreet2()
	 */
	
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getCity()
	 */
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getPostalCode()
	 */
	
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/* (non-Javadoc)
	 * @see com.tdil.lojack.rest.model.IPerson#getAddressType()
	 */
	
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public boolean isOptIn() {
		return optIn;
	}
	public void setOptIn(boolean optIn) {
		this.optIn = optIn;
	}
	
	
	public JSONArray getSocialConnections() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<OptIn> getOptIns() {
		OptIn optIn = new OptIn();
		optIn.setAccepted(this.isOptIn());
		List<OptIn> result = new ArrayList<OptIn>();
		result.add(optIn);
		return result;
	}
}
