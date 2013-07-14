package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.tdil.thalamus.client.facade.json.beans.PersonFields;
import com.tdil.thalamus.client.facade.json.beans.StateBean;
import com.tdil.thalamus.client.facade.json.beans.TokenHolder;
import com.tdil.thalamus.client.facade.json.beans.URLHolder;
import com.tdil.thalamus.client.facade.json.fields.PersonFieldNames;
import com.tdil.thalamus.client.utils.ThalamusUtils;
import com.tdil.utils.DateUtils;
import com.tdil.utils.StringUtils;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class RegisterForm extends AbstractForm implements RefreshableForm, IPerson {

	private static final long serialVersionUID = 7670249948557986182L;

	private boolean isMobile;
	private boolean isUpdate = false;

	private TokenHolder token;

	private int documentType = 1;
	private String document;
	private String password;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String gender; // Male Female
	private String email;

	private String day;
	private String month;
	private String year;

	private List<String> years;

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

	private List<OptIn> optIns = new ArrayList<OptIn>();

	private JSONArray socialConnections;

	private String facebook;
	private String twitter;
	private URLHolder facebookLoginUrl;

	private Collection<CountryBean> countries = new ArrayList<CountryBean>();
	private Collection<BrandBean> brands = new ArrayList<BrandBean>();
	private Collection<StateBean> states = new ArrayList<StateBean>();

	private PersonFields personFields;

	public static final String firstname_key = "RegisterForm.firstname";
	public static final String lastname_key = "RegisterForm.lastname";
	public static final String gender_key = "RegisterForm.gender";
	public static final String birthdate_key = "RegisterForm.birthdate";
	public static final String password_key = "RegisterForm.password";
	public static final String documenttype_key = "RegisterForm.documenttype";
	public static final String document_key = "RegisterForm.document";
	public static final String email_key = "RegisterForm.email";

	@Override
	public ValidationError validate() {
		ValidationError error = new ValidationError();
		FieldValidation.validateText(this.getFirstName(), firstname_key, 150, error);
		FieldValidation.validateText(this.getLastName(), lastname_key, 150, error);
		if (org.apache.commons.lang.StringUtils.isEmpty(gender)) {
			error.setFieldError(gender_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
		Date date = FieldValidation.validateDate(this.getBirthDate(), birthdate_key, "yyyy-MM-dd", true, error);
		if (!error.hasFieldError(birthdate_key)) {
			String formatted = DateUtils.formatDate(date);
			if (!formatted.equals(this.getBirthDate())) {
				error.setFieldError(birthdate_key, ValidationErrors.INVALID_DATE);
			}
		}
		if (!isUpdate) {
			FieldValidation.validateText(this.getPassword(), password_key, 20, error);
		}
		FieldValidation.validateId(this.getDocumentType(), documenttype_key, error);
		FieldValidation.validateNumber(this.getDocument(), document_key, 1, Integer.MAX_VALUE, error);
		FieldValidation.validateEmail(this.getEmail(), email_key, error);
		return error;
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
			if (countries.size() == 1) {
				CountryBean arg = this.countries.iterator().next();
				this.countryId = arg.getId();
				this.countrySelected = arg.getName();
				setStates(ThalamusClientBeanFacade.getStates(this.getCountryId()));
				this.buildOptIns();
			} else {
				states.clear();
			}
			brands = ThalamusClientBeanFacade.getBrands();
			this.personFields = ThalamusClientBeanFacade.getPersonFields();
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

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		for (OptIn optIn : this.getOptIns()) {
			optIn.setAccepted(false);
		}
	}

	@Override
	public void initWith(int id) throws SQLException {
	}

	@Override
	public void reset() throws SQLException {
		this.documentType = 1;
		this.document = null;
		this.firstName = null;
		this.lastName = null;
		this.email = null;
		this.birthDate = null;
		this.street1 = null;
		this.city = null;
		this.countryId = 0;
		this.stateId = 0;

		this.postalCode = null;
		this.addressType = null;
		this.phoneNumber = null;
		this.phoneType = null;
		this.password = null;
	}

	@Override
	public void save() throws SQLException, ValidationException {
	}

	public WebsiteUser register() throws ValidationException {
		JSONObject general = getPersonJSON(false, this);
		try {
			ThalamusResponse response = ThalamusClientFacade.registerPersonAndConsumer(general);
			if (response.isBadRequest()) {
				ValidationError validationError = new ValidationError();
				ThalamusWebUtils.addErrorsTo(validationError, response);
				throw new ValidationException(validationError);
			} else {
				LoginResult loginResult = ThalamusClientBeanFacade.login(new LoginBean(this.getPrincipal(), this.getPassword()));
				WebsiteUser user = new WebsiteUser(this.getFirstName() + " " + this.getLastName(), loginResult.getTokenHolder(), -3, "AA");
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

	public static JSONObject getPersonJSON(boolean update, IPerson iperson) {
		JSONObject general = new JSONObject();
		JSONObject profile = new JSONObject();
		JSONObject document = new JSONObject();
		if (!update) {
			if (iperson.getDocumentType() != 0) {
				document.put("type", iperson.getDocumentType());
			}
			document.put("number", StringUtils.nullValueOf(iperson.getDocument()));
			profile.put("document", document);
		}
		profile.put(PersonFieldNames.firstName, StringUtils.nullValueOf(iperson.getFirstName()));
		profile.put(PersonFieldNames.lastName, StringUtils.nullValueOf(iperson.getLastName()));
		profile.put(PersonFieldNames.gender, StringUtils.nullValueOf(iperson.getGender()));
		profile.put(PersonFieldNames.email, StringUtils.nullValueOf(iperson.getEmail()));
		Date birthDate = com.tdil.utils.DateUtils.parseDate(iperson.getBirthDate());
		if (birthDate != null) {
			profile.put(PersonFieldNames.birthDate, birthDate.getTime());
		} else {
			profile.put(PersonFieldNames.birthDate, null);
		}
		JSONObject address = new JSONObject();
		if (iperson.getCountryId() != 0) {
			address.put(PersonFieldNames.countryId, iperson.getCountryId());
		}
		address.put(PersonFieldNames.street2, StringUtils.nullValueOf(iperson.getStreet2()));
		address.put(PersonFieldNames.street1, StringUtils.nullValueOf(iperson.getStreet1()));
		address.put(PersonFieldNames.postalCode, StringUtils.nullValueOf(iperson.getPostalCode()));
		if (iperson.getStateId() != 0) {
			address.put(PersonFieldNames.stateId, iperson.getStateId());
		}
		address.put(PersonFieldNames.addressType, StringUtils.nullValueOf(iperson.getAddressType()));
		address.put(PersonFieldNames.city, StringUtils.nullValueOf(iperson.getCity()));
		profile.put(PersonFieldNames.address, address);
		JSONObject phone = new JSONObject();
		phone.put(PersonFieldNames.phoneNumber, StringUtils.nullValueOf(iperson.getPhoneNumber()));
		phone.put(PersonFieldNames.phoneAreaCode, StringUtils.nullValueOf(iperson.getPhoneAreaCode()));
		phone.put(PersonFieldNames.phoneType, StringUtils.nullValueOf(iperson.getPhoneType()));
		phone.put(PersonFieldNames.phoneIntCode, StringUtils.nullValueOf(iperson.getPhoneIntCode()));
		profile.put(PersonFieldNames.phone, phone);
		general.put("profile", profile);
		JSONObject credentials = new JSONObject();
		credentials.put(PersonFieldNames.principal, StringUtils.nullValueOf(iperson.getDocumentType() + ":" + iperson.getDocument()));
		credentials.put(PersonFieldNames.password, StringUtils.nullValueOf(iperson.getPassword()));
		general.put("credential", credentials);

		JSONArray optInsArray = new JSONArray();
		for (OptIn optIn : iperson.getOptIns()) {
			JSONObject op = new JSONObject();
			op.put("brandFamilyId", -1);
			op.put("channel", -1);
			op.put("accepted", optIn.isAccepted());
			optInsArray.add(op);
		}
		general.put("optIns", optInsArray);

		if (iperson.getSocialConnections() != null) {
			general.put("socialConnections", iperson.getSocialConnections());
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
		if (this.isMobile) {
			birthDate = this.getYear() + "-" + this.getMonth() + "-" + this.getDay();
		}
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
		/*
		 * if (states.isEmpty() || this.getCountryId() !=
		 * states.get(0).getParent()) { try { states.clear(); JSONArray
		 * brandsResult =
		 * (JSONArray)ThalamusClientFacade.getStates(this.getCountryId()); for
		 * (int i = 0; i < brandsResult.size(); i++) { JSONObject row =
		 * brandsResult.getJSONObject(i); states.add(new
		 * ComboBean(row.getInt("id"), row.getString("name"),
		 * row.getInt("masterId"))); } } catch (HttpStatusException e) { throw
		 * new ValidationException(new
		 * ValidationError("RegisterForm.HttpStatusException")); } catch
		 * (InvalidResponseException e) { throw new ValidationException(new
		 * ValidationError("RegisterForm.InvalidResponseException")); } catch
		 * (CommunicationException e) { throw new ValidationException(new
		 * ValidationError("RegisterForm.CommunicationException")); } catch
		 * (UnauthorizedException e) { throw new ValidationException(new
		 * ValidationError("RegisterForm.UnauthorizedException")); } }
		 */
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
		return this.getDocumentType() + ":" + this.getDocument();
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

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void takeInitialData(TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException,
			UnauthorizedException {
		this.setToken(tokenHolder);
		JSON gral = ThalamusClientFacade.getPerson(tokenHolder);
		JSONObject person = ((JSONObject) gral).getJSONObject("person");
		JSONObject profile = person.getJSONObject("profile");
		if (profile.containsKey("document") && profile.get("document") != JSONNull.getInstance()) {
			JSONObject document = profile.getJSONObject("document");
			this.setDocument(document.getString("number"));
		}

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
			Date date = new Date(profile.getLong(PersonFieldNames.birthDate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			setDay(String.valueOf(cal.get(Calendar.DATE)));
			setMonth(String.valueOf(cal.get(Calendar.MONTH) + 1));
			if (this.getMonth().length() == 1) {
				this.setMonth("0" + this.getMonth());
			}
			setYear(String.valueOf(cal.get(Calendar.YEAR)));
			this.setBirthDate(DateUtils.formatDate(date));
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
				CountryBean arg = this.countries.iterator().next();
				this.countrySelected = arg.getName();
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
		if (person.containsKey("optIns")) {
			JSONArray optIns = person.getJSONArray("optIns");
			Iterator iter = optIns.iterator();
			while (iter.hasNext()) {
				JSONObject optIn = (JSONObject) iter.next();
				int brandFamilyId = optIn.getInt("brandFamilyId");
				int channel = optIn.getInt("channel");
				boolean accepted = optIn.getBoolean("accepted");
				setOptIn(-1, -1, accepted);
			}
		}
	}

	public WebsiteUser update() throws ValidationException {
		JSONObject general = getPersonJSON(true, this);
		try {
			ThalamusResponse response = ThalamusClientFacade.updatePerson(this.getToken(), general);
			if (response.isBadRequest()) {
				ValidationError validationError = new ValidationError();
				ThalamusWebUtils.addErrorsTo(validationError, response);
				throw new ValidationException(validationError);
			} else {
				WebsiteUser user = new WebsiteUser(this.getFirstName() + " " + this.getLastName(), this.getToken(), -3, "AA");
				// user.setAppliedActivities(ThalamusUtils.getAppliedActivitiesFrom((JSONObject)response.getResult()));
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

	public void setTwitter(String string) {
		this.twitter = string;
	}

	public String getTwitter() {
		return twitter;
	}

	public String getFieldNameForPrincipal() {
		return "profile.document";
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String dniNumber) {
		this.document = dniNumber;
	}

	public String getCountrySelected() {
		return countrySelected;
	}

	public void setCountrySelected(String countrySelected) {
		this.countrySelected = countrySelected;
	}

	public int getDocumentType() {
		return documentType;
	}

	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<String> getYears() {
		if (years == null) {
			years = DateUtils.allYears();
		}
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public boolean isMobile() {
		return isMobile;
	}

	public void setMobile(boolean isMobile) {
		this.isMobile = isMobile;
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
		return this.getOptIns().get(index);
	}

	public void setOptIn(int index, OptIn countryVO) {
		this.getOptIns().set(index, countryVO);
	}

	private void setOptIn(int brandFamilyId, int channel, boolean accepted) {
		for (OptIn optIn : this.optIns) {
			// if (optIn.getBrandFamilyId() == brandFamilyId &&
			// optIn.getChannelId() == channel) {
			optIn.setAccepted(accepted);
			return;
			// }
		}
	}

	public List<OptIn> getOptIns() {
		return optIns;
	}

	public void setOptIns(List<OptIn> optIns) {
		this.optIns = optIns;
	}
}
