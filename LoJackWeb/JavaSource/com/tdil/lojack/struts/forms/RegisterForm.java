package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.struts.forms.beans.OptIn;
import com.tdil.lojack.utils.ThalamusWebUtils;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.struts.forms.RefreshableForm;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.ThalamusResponse;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.BrandBean;
import com.tdil.thalamus.client.facade.json.beans.BrandFamilyBean;
import com.tdil.thalamus.client.facade.json.beans.ChannelBean;
import com.tdil.thalamus.client.facade.json.beans.CountryBean;
import com.tdil.thalamus.client.facade.json.beans.FieldDescription;
import com.tdil.thalamus.client.facade.json.beans.LoginBean;
import com.tdil.thalamus.client.facade.json.beans.LoginResult;
import com.tdil.thalamus.client.facade.json.beans.URLHolder;
import com.tdil.thalamus.client.facade.json.beans.PersonFields;
import com.tdil.thalamus.client.facade.json.beans.StateBean;
import com.tdil.thalamus.client.facade.json.beans.TokenHolder;
import com.tdil.thalamus.client.facade.json.fields.PersonFieldNames;
import com.tdil.thalamus.client.utils.ThalamusUtils;
import com.tdil.utils.DateUtils;

public class RegisterForm extends AbstractForm implements RefreshableForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private boolean isUpdate = false;
	
	private TokenHolder token;
	
	private String firstName;
	private String lastName;
	private String gender; // Male Female
	
	private String password;

	private String birthDate;
	
	private String phoneNumber;
	private String phoneAreaCode;
	private String phoneType;
	private String phoneIntCode;
	
	private String email;
	
	private int countryId;
	private String street1;
	private String street2;
	private String postalCode;
	private int stateId;
	private String addressType;
	private String city;
	
	private boolean activeConsumer = true;
	private int preferedBrandId;
	private int alternativeBrandId;
	private int consumptionFrequency;
	
	private List<OptIn> optIns = new ArrayList<OptIn>();

	private JSONArray socialConnections;
	
	private String facebook;
	private URLHolder facebookLoginUrl;
	
	private Collection<CountryBean> countries = new ArrayList<CountryBean>();
	private Collection<BrandBean> brands = new ArrayList<BrandBean>();
	private Collection<StateBean> states = new ArrayList<StateBean>();
	
	private PersonFields personFields;

	@Override
	public ValidationError validate() {
		return new ValidationError();
	}
	@Override
	public void init() throws SQLException {
		
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(RegisterForm.class);
	}
	
	public boolean isInUse(String field) {
		return this.getPersonFields().isInUse(field);
	}
	
	public boolean isInUse(String field, String subfield) {
		return this.getPersonFields().isInUse(field, subfield);
	}
	
	public boolean isInUseAndEditable(String field) {
		return this.getPersonFields().isInUse(field) && (!this.isUpdate || this.getPersonFields().isEditable(field));
	}
	
	public boolean isInUseAndEditable(String field, String subfield) {
		return this.getPersonFields().isInUse(field, subfield) && (!this.isUpdate || this.getPersonFields().isEditable(field, subfield));
	}
	
	public boolean isRequired(String field) {
		return this.getPersonFields().isRequired(field);
	}
	
	public boolean isRequired(String field, String subfield) {
		return this.getPersonFields().isRequired(field, subfield);
	}
	
	public List<String> getPhoneTypes() {
		FieldDescription phone = this.getPersonFields().getProfileField(PersonFieldNames.phone);
		FieldDescription type = phone.getField(PersonFieldNames.phoneType);
		return type.getEnumValues();
	}
	
	public List<String> getAddressTypes() {
		FieldDescription phone = this.getPersonFields().getProfileField(PersonFieldNames.address);
		FieldDescription type = phone.getField(PersonFieldNames.addressType);
		return type.getEnumValues();
	}
	
	public void searchReferenceData() throws ValidationException {
		try {
			countries = ThalamusClientBeanFacade.getCountries();
			brands = ThalamusClientBeanFacade.getBrands();
			states.clear();
			this.personFields = ThalamusClientBeanFacade.getPersonFields();
			this.buildOptIns();
			this.setFacebookLoginUrl(ThalamusClientBeanFacade.getFacebookLogin());
			
		} catch (HttpStatusException e) {
			throw new ValidationException(new ValidationError("RegisterForm.HttpStatusException"));
		} catch (InvalidResponseException e) {
			throw new ValidationException(new ValidationError("RegisterForm.InvalidResponseException"));
		} catch (CommunicationException e) {
			throw new ValidationException(new ValidationError("RegisterForm.CommunicationException"));
		} catch (UnauthorizedException e) {
			throw new ValidationException(new ValidationError("RegisterForm.UnauthorizedException"));
		}
		
	}
	
	private void buildOptIns() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		this.getOptIns().clear();
		Collection<BrandFamilyBean> brandFamilies = ThalamusClientBeanFacade.getBrandFamilies();
		Collection<ChannelBean> channels = ThalamusClientBeanFacade.getChannels();
		for (BrandFamilyBean family : brandFamilies) {
			for (ChannelBean channel : channels) {
				this.getOptIns().add(new OptIn(family.getId(), family.getName(), channel.getId(), channel.getName()));
			}
		}
	}
	
	public OptIn getOptIn(int index) {  
	      return (OptIn)this.getOptIns().get(index);  
	   }  
	   
	public void setOptIn(int index, OptIn countryVO) {  
		this.getOptIns().set(index, countryVO);  
	 }
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.activeConsumer = true;
	}
	
	@Override
	public void initWith(int id) throws SQLException {
	}
	
	@Override
	public void reset() throws SQLException {
		this.firstName = null;
		this.lastName = null;
		this.email = null;
		this.birthDate = null;
		this.street1 = null;
		this.city = null;
		this.countryId = 0;
		this.stateId = 0;

		this.activeConsumer = false;
		this.preferedBrandId = 0;
		this.alternativeBrandId = 0;
		this.consumptionFrequency = 0;

		this.postalCode = null;
		this.addressType = null;
		this.phoneNumber = null;
		this.phoneType = null;
		this.password = null;
		
		this.optIns.clear();
	}
	
	@Override
	public void save() throws SQLException, ValidationException {
	}
	
	public WebsiteUser register() throws ValidationException {
		JSONObject general = getPersonJSON();
		try {
			ThalamusResponse response = ThalamusClientFacade.registerPersonAndConsumer(general);
			if (response.isBadRequest()) {
				ValidationError validationError = new ValidationError();
				ThalamusWebUtils.addErrorsTo(validationError, response);
				throw new ValidationException(validationError);
			} else {
				LoginResult loginResult = ThalamusClientBeanFacade.login(new LoginBean(this.getPrincipal(), this.getPassword()));
				WebsiteUser user = new WebsiteUser(this.getFirstName() + " " + this.getLastName(), loginResult.getTokenHolder(), "-3", "AA");
				// TODO XXX
				user.setAppliedActivities(ThalamusUtils.getAppliedActivitiesFrom(loginResult));
				return user;
			}
		} catch (HttpStatusException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("RegisterForm.HttpStatusException")));
		} catch (InvalidResponseException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("RegisterForm.InvalidResponseException")));
		} catch (CommunicationException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("RegisterForm.CommunicationException")));
		} catch (UnauthorizedException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("RegisterForm.UnauthorizedException")));
		}
	}

	private JSONObject getPersonJSON() {
		JSONObject general = new JSONObject();
		JSONObject profile = new JSONObject();
		if (isInUseAndEditable(PersonFieldNames.firstName)) {
			profile.put(PersonFieldNames.firstName, this.getFirstName());
		}
		if (isInUseAndEditable(PersonFieldNames.lastName)) {
			profile.put(PersonFieldNames.lastName, this.getLastName());
		}
		if (isInUseAndEditable(PersonFieldNames.gender)) {
			profile.put(PersonFieldNames.gender, this.getGender());
		}
		if (isInUseAndEditable(PersonFieldNames.email)) {
			profile.put(PersonFieldNames.email, this.getEmail());
		}
		if (isInUseAndEditable(PersonFieldNames.birthDate)) {
			Date birthDate = com.tdil.utils.DateUtils.parseDate(this.getBirthDate());
			if (birthDate != null) {
				profile.put(PersonFieldNames.birthDate,birthDate.getTime());
			} else {
				profile.put(PersonFieldNames.birthDate,null);
			}
		}
		if (isInUseAndEditable(PersonFieldNames.address)) {
			JSONObject address = new JSONObject();
			if (isInUseAndEditable(PersonFieldNames.address, PersonFieldNames.countryId)) {
				if (this.getCountryId() != 0) {
					address.put(PersonFieldNames.countryId, this.getCountryId());
				}
			}
			if (isInUseAndEditable(PersonFieldNames.address, PersonFieldNames.street2)) {
				address.put(PersonFieldNames.street2, this.getStreet2());
			}
			if (isInUseAndEditable(PersonFieldNames.address, PersonFieldNames.street1)) {
				address.put(PersonFieldNames.street1, this.getStreet1());
			}
			if (isInUseAndEditable(PersonFieldNames.address, PersonFieldNames.postalCode)) {
				address.put(PersonFieldNames.postalCode, this.getPostalCode());
			}
			if (isInUseAndEditable(PersonFieldNames.address, PersonFieldNames.stateId)) {
				if (this.getStateId() != 0) {
					address.put(PersonFieldNames.stateId, this.getStateId());
				}
			}
			if (isInUseAndEditable(PersonFieldNames.address, PersonFieldNames.addressType)) {
				address.put(PersonFieldNames.addressType, this.getAddressType());
			}
			if (isInUseAndEditable(PersonFieldNames.address, PersonFieldNames.city)) {
				address.put(PersonFieldNames.city, this.getCity());
			}
			profile.put(PersonFieldNames.address, address);
		}
		if (isInUseAndEditable(PersonFieldNames.phone)) {
			JSONObject phone = new JSONObject();
			if (isInUseAndEditable(PersonFieldNames.phone, PersonFieldNames.phoneNumber)) {
				phone.put(PersonFieldNames.phoneNumber, this.getPhoneNumber());
			}
			if (isInUseAndEditable(PersonFieldNames.phone, PersonFieldNames.phoneAreaCode)) {
				phone.put(PersonFieldNames.phoneAreaCode, this.getPhoneAreaCode());
			}
			if (isInUseAndEditable(PersonFieldNames.phone, PersonFieldNames.phoneType)) {
				phone.put(PersonFieldNames.phoneType, this.getPhoneType());
			}
			if (isInUseAndEditable(PersonFieldNames.phone, PersonFieldNames.phoneIntCode)) {
				phone.put(PersonFieldNames.phoneIntCode, this.getPhoneIntCode());
			}
			profile.put(PersonFieldNames.phone, phone);
		}
		general.put("profile", profile);
		JSONObject consumer = new JSONObject();
		if (isInUseAndEditable(PersonFieldNames.activeConsumer)) {
			consumer.put(PersonFieldNames.activeConsumer,this.activeConsumer);
		}
		if (isInUseAndEditable(PersonFieldNames.preferedBrand)) {
			if (this.getPreferedBrandId() != 0) {
				consumer.put(PersonFieldNames.preferedBrand,this.preferedBrandId);
			}
		}
		if (isInUseAndEditable(PersonFieldNames.alternativeBrandId)) {
			if (this.getAlternativeBrandId() != 0) {
				consumer.put(PersonFieldNames.alternativeBrandId,this.alternativeBrandId);
			}
		}
		if (isInUseAndEditable(PersonFieldNames.consumtionFrequency)) {
			consumer.put("consumptionFrecuency",this.consumptionFrequency);
		}
		general.put("consumer", consumer);
		
		JSONObject credentials = new JSONObject();
		credentials.put(PersonFieldNames.principal, this.getPrincipal());
		credentials.put(PersonFieldNames.password, this.getPassword());
		general.put("credential", credentials);

		JSONArray optInsArray = new JSONArray();
		for (OptIn optIn : this.getOptIns()) {
			JSONObject op = new JSONObject();
			op.put("brandFamilyId", optIn.getBrandFamilyId());
			op.put("channel", optIn.getChannelId());
			op.put("accepted", optIn.isAccepted());
			optInsArray.add(op);
		}
		general.put("optIns", optInsArray);
		if (socialConnections != null) {
			general.put("socialConnections", socialConnections);
		}
		return general;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
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

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street) {
		this.street1 = street;
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

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneNumberType) {
		this.phoneType = phoneNumberType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void refresh() throws ValidationException {
		/*if (states.isEmpty() || this.getCountryId() != states.get(0).getParent()) {
			try {
				states.clear();
				JSONArray brandsResult = (JSONArray)ThalamusClientFacade.getStates(this.getCountryId());
				for (int i = 0; i < brandsResult.size(); i++) {
					JSONObject row = brandsResult.getJSONObject(i);
					states.add(new ComboBean(row.getInt("id"), row.getString("name"), row.getInt("masterId")));
				}
			} catch (HttpStatusException e) {
				throw new ValidationException(new ValidationError("RegisterForm.HttpStatusException"));
			} catch (InvalidResponseException e) {
				throw new ValidationException(new ValidationError("RegisterForm.InvalidResponseException"));
			} catch (CommunicationException e) {
				throw new ValidationException(new ValidationError("RegisterForm.CommunicationException"));
			} catch (UnauthorizedException e) {
				throw new ValidationException(new ValidationError("RegisterForm.UnauthorizedException"));
			}
		}*/
	}

	public boolean isActiveConsumer() {
		return activeConsumer;
	}

	public void setActiveConsumer(boolean activeConsumer) {
		this.activeConsumer = activeConsumer;
	}

	public int getPreferedBrandId() {
		return preferedBrandId;
	}

	public void setPreferedBrandId(int preferedBrand) {
		this.preferedBrandId = preferedBrand;
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
	public PersonFields getPersonFields() {
		return personFields;
	}
	public void setPersonFields(PersonFields personFields) {
		this.personFields = personFields;
	}
	public Collection<CountryBean> getCountries() {
		return countries;
	}
	public void setCountries(Collection<CountryBean> countries) {
		this.countries = countries;
	}
	public Collection<BrandBean> getBrands() {
		return brands;
	}
	public void setBrands(Collection<BrandBean> brands) {
		this.brands = brands;
	}
	public Collection<StateBean> getStates() {
		return states;
	}
	public void setStates(Collection<StateBean> states) {
		this.states = states;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPrincipal() {
		FieldDescription fieldDescription = personFields.getProfileField(PersonFieldNames.email);
		if (fieldDescription.isPrincipal()) {
			return this.getEmail();
		}
		// TODO aca tener en cuenta el resto de los campos que pueden ser principal
		return null;
	}
	
	public boolean isPrincipal(String field) {
		return this.personFields.isPrincipal(field);
	}
	
	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}
	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}
	public String getPhoneIntCode() {
		return phoneIntCode;
	}
	public void setPhoneIntCode(String phoneIntCode) {
		this.phoneIntCode = phoneIntCode;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public List<OptIn> getOptIns() {
		return optIns;
	}
	public void setOptIns(List<OptIn> optIns) {
		this.optIns = optIns;
	}
	public boolean isUpdate() {
		return isUpdate;
	}
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public void takeInitialData(TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		this.setToken(tokenHolder);
		JSON gral = ThalamusClientFacade.getPerson(tokenHolder);
		JSONObject person = ((JSONObject)gral).getJSONObject("person");
		JSONObject profile = person.getJSONObject("profile");
		if (profile.containsKey(PersonFieldNames.firstName) && profile.get(PersonFieldNames.firstName) != JSONNull.getInstance()) {
			this.setFirstName(profile.getString(PersonFieldNames.firstName));
		}
		if (profile.containsKey(PersonFieldNames.lastName) && profile.get(PersonFieldNames.lastName) != JSONNull.getInstance()) {
			this.setLastName(profile.getString(PersonFieldNames.lastName));
		}
		if (profile.containsKey(PersonFieldNames.email) && profile.get(PersonFieldNames.email) != JSONNull.getInstance()) {
			this.setEmail(profile.getString(PersonFieldNames.email));
		}
		if (profile.containsKey(PersonFieldNames.gender) && profile.get(PersonFieldNames.gender) != JSONNull.getInstance()) {
			this.setGender(profile.getString(PersonFieldNames.gender));
		}
		if (profile.containsKey(PersonFieldNames.birthDate) && profile.get(PersonFieldNames.birthDate) != JSONNull.getInstance()) {
			this.setBirthDate(DateUtils.formatDate(new Date(profile.getLong(PersonFieldNames.birthDate))));
		}
		if (profile.containsKey(PersonFieldNames.phone)) {
			JSONObject phone = profile.getJSONObject(PersonFieldNames.phone);
			if (phone.containsKey(PersonFieldNames.phoneIntCode) && phone.get(PersonFieldNames.phoneIntCode) != JSONNull.getInstance()) {
				this.setPhoneIntCode(phone.getString(PersonFieldNames.phoneIntCode));
			}
			if (phone.containsKey(PersonFieldNames.phoneAreaCode) && phone.get(PersonFieldNames.phoneAreaCode) != JSONNull.getInstance()) {
				this.setPhoneAreaCode(phone.getString(PersonFieldNames.phoneAreaCode));
			}
			if (phone.containsKey(PersonFieldNames.phoneNumber) && phone.get(PersonFieldNames.phoneNumber) != JSONNull.getInstance()) {
				this.setPhoneNumber(phone.getString(PersonFieldNames.phoneNumber));
			}
			if (phone.containsKey(PersonFieldNames.phoneType) && phone.get(PersonFieldNames.phoneType) != JSONNull.getInstance()) {
				this.setPhoneType(phone.getString(PersonFieldNames.phoneType));
			}
		}
		if (profile.containsKey(PersonFieldNames.address)) {
			JSONObject address = profile.getJSONObject(PersonFieldNames.address);
			if (address.containsKey(PersonFieldNames.countryId) && address.get(PersonFieldNames.countryId) != JSONNull.getInstance()) {
				this.setCountryId(address.getInt(PersonFieldNames.countryId));
				setStates(ThalamusClientBeanFacade.getStates(this.getCountryId()));
			}
			if (address.containsKey(PersonFieldNames.stateId) && address.get(PersonFieldNames.stateId) != JSONNull.getInstance()) {
				this.setStateId(address.getInt(PersonFieldNames.stateId));
			}
			if (address.containsKey(PersonFieldNames.street1) && address.get(PersonFieldNames.street1) != JSONNull.getInstance()) {
				this.setStreet1(address.getString(PersonFieldNames.street1));
			}
			if (address.containsKey(PersonFieldNames.street2) && address.get(PersonFieldNames.street2) != JSONNull.getInstance()) {
				this.setStreet2(address.getString(PersonFieldNames.street2));
			}
			if (address.containsKey(PersonFieldNames.postalCode) && address.get(PersonFieldNames.postalCode) != JSONNull.getInstance()) {
				this.setPostalCode(address.getString(PersonFieldNames.postalCode));
			}
			if (address.containsKey(PersonFieldNames.addressType) && address.get(PersonFieldNames.addressType) != JSONNull.getInstance()) {
				this.setAddressType(address.getString(PersonFieldNames.addressType));
			}
			if (address.containsKey(PersonFieldNames.city) && address.get(PersonFieldNames.city) != JSONNull.getInstance()) {
				this.setCity(address.getString(PersonFieldNames.city));
			}
		}
		if (person.containsKey("consumer")) {
			JSONObject consumer = person.getJSONObject("consumer");
			if (consumer.containsKey(PersonFieldNames.activeConsumer) && consumer.get(PersonFieldNames.activeConsumer) != JSONNull.getInstance()) {
				this.setActiveConsumer(consumer.getBoolean(PersonFieldNames.activeConsumer));
			}
			if (consumer.containsKey("consumptionFrecuency")  && consumer.get("consumptionFrecuency") != JSONNull.getInstance()) {
				this.setConsumptionFrequency(consumer.getInt("consumptionFrecuency"));
			}
			if (consumer.containsKey(PersonFieldNames.preferedBrand) && consumer.get(PersonFieldNames.preferedBrand) != JSONNull.getInstance()) {
				this.setPreferedBrandId(consumer.getInt(PersonFieldNames.preferedBrand));
			}
			if (consumer.containsKey(PersonFieldNames.alternativeBrandId) && consumer.get(PersonFieldNames.alternativeBrandId) != JSONNull.getInstance()) {
				this.setAlternativeBrandId(consumer.getInt(PersonFieldNames.alternativeBrandId));
			}
		}
		if (person.containsKey("optIns")) {
			JSONArray optIns = person.getJSONArray("optIns");
			Iterator iter = optIns.iterator();
			while (iter.hasNext()) {
				JSONObject optIn = (JSONObject)iter.next();
				int brandFamilyId = optIn.getInt("brandFamilyId");
				int channel = optIn.getInt("channel");
				boolean accepted = optIn.getBoolean("accepted");
				setOptIn(brandFamilyId, channel, accepted);
			}
		}
	}
	private void setOptIn(int brandFamilyId, int channel, boolean accepted) {
		for (OptIn optIn : this.optIns) {
			if (optIn.getBrandFamilyId() == brandFamilyId && optIn.getChannelId() == channel) {
				optIn.setAccepted(accepted);
				return;
			}
		}
	}
	public WebsiteUser update() throws ValidationException {
		JSONObject general = getPersonJSON();
		try {
			ThalamusResponse response = ThalamusClientFacade.updatePerson(this.getToken(), general);
			if (response.isBadRequest()) {
				ValidationError validationError = new ValidationError();
				ThalamusWebUtils.addErrorsTo(validationError, response);
				throw new ValidationException(validationError);
			} else {
				WebsiteUser user = new WebsiteUser(this.getFirstName() + " " + this.getLastName(), this.getToken(), "-3", "");
				// TODO XXX
				//user.setAppliedActivities(ThalamusUtils.getAppliedActivitiesFrom((JSONObject)response.getResult()));
				return user;
			}
		} catch (HttpStatusException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("RegisterForm.HttpStatusException")));
		} catch (InvalidResponseException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("RegisterForm.InvalidResponseException")));
		} catch (CommunicationException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("RegisterForm.CommunicationException")));
		} catch (UnauthorizedException e) {
			throw new ValidationException(new ValidationError(ApplicationResources.getMessage("RegisterForm.UnauthorizedException")));
		}
	}
	public TokenHolder getToken() {
		return token;
	}
	public void setToken(TokenHolder token) {
		this.token = token;
	}
	public URLHolder getFacebookLoginUrl() {
		return facebookLoginUrl;
	}
	public void setFacebookLoginUrl(URLHolder facebookLoginUrl) {
		this.facebookLoginUrl = facebookLoginUrl;
	}
	public JSONArray getSocialConnections() {
		return socialConnections;
	}
	public void setSocialConnections(JSONArray socialConnections) {
		this.socialConnections = socialConnections;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	
}
