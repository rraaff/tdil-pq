package com.tdil.thalamusweb.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.RegistrationParameters;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamusweb.struts.forms.beans.ComboBean;

public class RegisterForm extends AbstractForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String firstName;
	private String lastName;
	private String email;
	private String birthDate;
	private String street;
	private String city;
	private int countryId;
	private int stateId;
	
	private boolean activeConsumer;
	private int preferedBrand;
	private int alternativeBrandId;
	private int consumptionFrequency;
	
	private String postalCode;
	private String addressType;
	private String phoneNumber;
	private String phoneNumberType;
	private String password;
	
	private List<ComboBean> countries = new ArrayList<ComboBean>();
	private List<ComboBean> brands = new ArrayList<ComboBean>();
	private List<ComboBean> states = new ArrayList<ComboBean>();

	@Override
	public ValidationError validate() {
		// TODO Auto-generated method stub
		return new ValidationError();
	}
	
	public void init() {
		try {
			countries.clear();
			JSONArray result = (JSONArray)ThalamusClientFacade.getCountries();
			for (int i = 0; i < result.size(); i++) {
				JSONObject row = result.getJSONObject(i);
				countries.add(new ComboBean(row.getInt("id"), row.getString("name")));
			}
			brands.clear();
			JSONArray brandsResult = (JSONArray)ThalamusClientFacade.getBrands();
			for (int i = 0; i < brandsResult.size(); i++) {
				JSONObject row = brandsResult.getJSONObject(i);
				brands.add(new ComboBean(row.getInt("id"), row.getString("name")));
			}
			states.clear();
		} catch (HttpStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnauthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void reset() throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void save() throws SQLException, ValidationException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(RegistrationParameters.firstName,this.firstName);
		jsonObject.put(RegistrationParameters.lastName,this.lastName);
		jsonObject.put(RegistrationParameters.email,this.email);
		jsonObject.put(RegistrationParameters.birthDate,com.tdil.utils.DateUtils.parseDate(this.getBirthDate()).getTime());
		jsonObject.put(RegistrationParameters.street,this.street);
		jsonObject.put(RegistrationParameters.city,this.city);
		jsonObject.put(RegistrationParameters.countryId,this.countryId);
		jsonObject.put(RegistrationParameters.stateId,this.stateId);
		jsonObject.put(RegistrationParameters.postalCode,this.postalCode);
		jsonObject.put(RegistrationParameters.addressType,this.addressType);
		jsonObject.put(RegistrationParameters.phoneNumber,this.phoneNumber);
		jsonObject.put(RegistrationParameters.phoneNumberType,this.phoneNumberType);
		jsonObject.put(RegistrationParameters.password,this.password);
		
		jsonObject.put(RegistrationParameters.activeConsumer,this.activeConsumer);
		jsonObject.put(RegistrationParameters.preferedBrandId,this.preferedBrand);
		jsonObject.put(RegistrationParameters.alternativeBrandId,this.alternativeBrandId);
		jsonObject.put(RegistrationParameters.consumptionFrequency,this.consumptionFrequency);
		System.out.println(jsonObject);
		try {
			JSON response = ThalamusClientFacade.registerPersonAndConsumer(jsonObject);
			// if response is ok if ()
			
			
		} catch (HttpStatusException e) {
			throw new ValidationException(new ValidationError("RegisterForm.GENERAL_ERROR"));
		} catch (InvalidResponseException e) {
			throw new ValidationException(new ValidationError("RegisterForm.GENERAL_ERROR"));
		} catch (CommunicationException e) {
			throw new ValidationException(new ValidationError("RegisterForm.GENERAL_ERROR"));
		} catch (UnauthorizedException e) {
			throw new ValidationException(new ValidationError("RegisterForm.GENERAL_ERROR"));
		}
		
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public List<ComboBean> getCountries() {
		return countries;
	}

	public void setCountries(List<ComboBean> countries) {
		this.countries = countries;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumberType() {
		return phoneNumberType;
	}

	public void setPhoneNumberType(String phoneNumberType) {
		this.phoneNumberType = phoneNumberType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ComboBean> getStates() {
		if (this.getCountryId() == 0) {
			states = new ArrayList<ComboBean>();
		} else {
			if (states.isEmpty() || this.getCountryId() != states.get(0).getParent()) {
				try {
					states.clear();
					JSONArray brandsResult = (JSONArray)ThalamusClientFacade.getStates(this.getCountryId());
					for (int i = 0; i < brandsResult.size(); i++) {
						JSONObject row = brandsResult.getJSONObject(i);
						states.add(new ComboBean(row.getInt("id"), row.getString("name"), row.getInt("masterId")));
					}
				} catch (HttpStatusException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidResponseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CommunicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnauthorizedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return states;
	}

	public void setStates(List<ComboBean> states) {
		this.states = states;
	}

	public List<ComboBean> getBrands() {
		return brands;
	}

	public void setBrands(List<ComboBean> brands) {
		this.brands = brands;
	}

	public boolean isActiveConsumer() {
		return activeConsumer;
	}

	public void setActiveConsumer(boolean activeConsumer) {
		this.activeConsumer = activeConsumer;
	}

	public int getPreferedBrand() {
		return preferedBrand;
	}

	public void setPreferedBrand(int preferedBrand) {
		this.preferedBrand = preferedBrand;
	}

	public int getAlternativeBrandId() {
		return alternativeBrandId;
	}

	public void setAlternativeBrandId(int alternativeBrandId) {
		this.alternativeBrandId = alternativeBrandId;
	}

	public int getConsumptionFrequency() {
		return consumptionFrequency;
	}

	public void setConsumptionFrequency(int consumptionFrequency) {
		this.consumptionFrequency = consumptionFrequency;
	}
	
}
