package com.tdil.thalamus.client.facade.json.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tdil.thalamus.client.facade.json.fields.PersonFieldNames;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PersonFields implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6029561665830265494L;
	private List<FieldDescription> profileFields = new ArrayList<FieldDescription>();
	private List<FieldDescription> consumerFields = new ArrayList<FieldDescription>();
	private List<FieldDescription> credentialFields = new ArrayList<FieldDescription>();
	
	private List<FieldDescription> dynamicFields = new ArrayList<FieldDescription>();

	public PersonFields(JSON json) {
		JSONObject jsonObject = (JSONObject)json;
		JSONArray profile = jsonObject.getJSONArray("profileFields");
		parseFields(profile, profileFields, dynamicFields);
		JSONArray consumer = jsonObject.getJSONArray("consumerFields");
		parseFields(consumer, consumerFields, dynamicFields);
		JSONArray credential = jsonObject.getJSONArray("credentialFields");
		parseFields(credential, credentialFields, dynamicFields);
		
		for (FieldDescription fd : this.getDynamicFields()) {
			if (FieldType.REFERENCE.equals(fd.getType())) {
				fd.searchOptions(this);
			}
		}
	}

	protected static void parseFields(JSONArray profile,
			List<FieldDescription> profileFields2, List<FieldDescription> dynamic) {
		for (Object obj : profile) {
			FieldDescription fieldDescription = new FieldDescription((JSONObject)obj);
			if (PersonFieldNames.ALL_FIELDS_SET.contains(fieldDescription.getName())) {
				profileFields2.add(fieldDescription);
			} else {
				if (FieldType.DOCUMENT.equals(fieldDescription.getType())) {
					for (FieldDescription subfield : fieldDescription.getSubfields()) {
						dynamic.add(subfield);
					}
				} else {
					dynamic.add(fieldDescription);
				}
			}
		}
	}

	public boolean isInUse(String field) {
		return isInUse(field, profileFields) || isInUse(field, consumerFields) || isInUse(field, credentialFields);
	}

	public boolean isRequired(String field) {
		return isRequired(field, profileFields) || isRequired(field, consumerFields) || isRequired(field, credentialFields);
	}

	private boolean isInUse(String field,
			List<FieldDescription> fields) {
		for (FieldDescription fd : fields) {
			if (fd.isInUse(field)) {
				return true;
			}
		}
		return false;
	}

	private boolean isRequired(String field,
			List<FieldDescription> fields) {
		for (FieldDescription fd : fields) {
			if (fd.isRequiredOrIsRequiredSubfield(field)) {
				return true;
			}
		}
		return false;
	}

	public List<FieldDescription> getProfileFields() {
		return profileFields;
	}
	public void setProfileFields(List<FieldDescription> profileFields) {
		this.profileFields = profileFields;
	}
	public List<FieldDescription> getConsumerFields() {
		return consumerFields;
	}
	public void setConsumerFields(List<FieldDescription> consumerFields) {
		this.consumerFields = consumerFields;
	}
	public List<FieldDescription> getCredentialFields() {
		return credentialFields;
	}
	public void setCredentialFields(List<FieldDescription> credentialFields) {
		this.credentialFields = credentialFields;
	}

	public FieldDescription getProfileField(String fieldName) {
		for (FieldDescription fd : this.getProfileFields()) {
			if (fd.getName().equals(fieldName)) {
				return fd;
			}
		}
		return null;
	}

	public boolean isInUse(String field, String subfield) {
		FieldDescription fd = this.getField(field);
		if (fd == null) {
			return false;
		}
		FieldDescription sfd = fd.getField(subfield);
		return sfd != null;
	}

	public boolean isRequired(String field, String subfield) {
		FieldDescription fd = this.getField(field);
		if (fd == null) {
			return false;
		}
		FieldDescription sfd = fd.getField(subfield);
		return sfd != null && sfd.isRequired();
	}

	public boolean isPrincipal(String field) {
		FieldDescription fd = this.getField(field);
		if (fd == null) {
			return false;
		}
		return fd.isPrincipal();
	}

	public boolean isEditable(String field) {
		FieldDescription fd = this.getField(field);
		if (fd == null) {
			return false;
		}
		return fd.isEditable();
	}

	public boolean isEditable(String field, String subfield) {
		FieldDescription fd = this.getField(field);
		if (fd == null) {
			return false;
		}
		FieldDescription sfd = fd.getField(subfield);
		if (sfd == null) {
			return false;
		}
		return sfd.isEditable();
	}

	private FieldDescription getField(String field) {
		for (FieldDescription fd : this.getProfileFields()) {
			if (fd.getName().equals(field)) {
				return fd;
			}
		}
		for (FieldDescription fd : this.getConsumerFields()) {
			if (fd.getName().equals(field)) {
				return fd;
			}
		}
		for (FieldDescription fd : this.getCredentialFields()) {
			if (fd.getName().equals(field)) {
				return fd;
			}
		}
		return null;
	}

	public List<FieldDescription> getDynamicFields() {
		return dynamicFields;
	}

	public void setDynamicFields(List<FieldDescription> dynamicFields) {
		this.dynamicFields = dynamicFields;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonFields [\nprofileFields=");
		builder.append(profileFields);
		builder.append(",\n consumerFields=");
		builder.append(consumerFields);
		builder.append(",\n credentialFields=");
		builder.append(credentialFields);
		builder.append(",\n dynamicFields=");
		builder.append(dynamicFields);
		builder.append(']');
		return builder.toString();
	}


}
