package com.tdil.thalamus.client.facade.json.fields;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface PersonFieldNames {

	public String document = "document";
	public String firstName = "firstname";
	public String lastName = "lastname";
	public String gender = "gender";
	public String principal = "principal";
	public String password = "password";
	
	public String birthDate = "birthday";
	
	public String phone = "phone";
	public String phoneNumber = "number";
	public String phoneAreaCode = "areaCode";
	public String phoneType = "type";
	public String phoneIntCode = "intCode";
	public String email = "email";
	
	public String address = "address";
	public String countryId = "countryId";
	public String street1 = "street1";
	public String street2 = "street2";
	public String postalCode = "postalCode";
	public String stateId = "stateId";
	public String addressType = "type";
	public String city = "city";

	public String activeConsumer = "activeConsumer";
	public String preferedBrand = "preferedBrandId";
	public String alternativeBrandId = "alternativeBrandId";
	public String consumtionFrequency = "consumptionFrecuency";
	
	public String COUNTRY_REF = "Country";
	public String DOCUMENT_TYPE_REF = "DocumentType";
	
	public static String all_fields[] = new String[]{document, firstName, lastName, gender, principal,
		password, birthDate, phone, phoneNumber, phoneAreaCode, phoneType, phoneIntCode, email,
		address, countryId, street1, street2, postalCode, stateId, addressType, city, activeConsumer, preferedBrand, alternativeBrandId, consumtionFrequency};
	
	public static Set<String> ALL_FIELDS_SET = new HashSet<String>(Arrays.asList(all_fields));
	
}
