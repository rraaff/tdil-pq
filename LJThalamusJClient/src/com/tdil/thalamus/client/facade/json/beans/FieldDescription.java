package com.tdil.thalamus.client.facade.json.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.json.fields.PersonFieldNames;

import net.sf.json.JSONObject;


public class FieldDescription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9068847957799876134L;
	private String name;
	private String description;
	private FieldType type;
	private boolean principal;
	private boolean required;
	private boolean editable;
	private FieldDescription parent;
	private List<String> enumValues = new ArrayList<String>();
	private String refEntity;
	private List<FieldDescription> subfields = new ArrayList<FieldDescription>();
	
	private List<ReferenceOption> options = new ArrayList<ReferenceOption>();
	
	public FieldDescription(JSONObject obj) {
		this.setName(obj.getString("name"));
		this.setDescription(obj.getString("description"));
		this.setType(FieldType.valueOf(obj.getString("type")));
		this.setRequired(obj.getBoolean("required"));
		this.setEditable(obj.getBoolean("editable"));
		if (obj.has("principal")) {
			this.setPrincipal(obj.getBoolean("principal"));
		}
		this.getType().parseFields(this, obj);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public FieldType getType() {
		return type;
	}
	public void setType(FieldType type) {
		this.type = type;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public List<String> getEnumValues() {
		return enumValues;
	}
	public void setEnumValues(List<String> enumValues) {
		this.enumValues = enumValues;
	}
	public String getRefEntity() {
		return refEntity;
	}
	public void setRefEntity(String refEntity) {
		this.refEntity = refEntity;
	}
	public List<FieldDescription> getSubfields() {
		return subfields;
	}
	public void setSubfields(List<FieldDescription> subfields) {
		for (FieldDescription sub : subfields) {
			sub.setParent(this);
		}
		this.subfields = subfields;
	}
	@Override
	public String toString() {
		return "FieldDescription ["
				+ (name != null ? "name=" + name + ", " : "")
				+ (description != null ? "description=" + description + ", "
						: "") + (type != null ? "type=" + type + ", " : "")
				+ "required=" + required + ", editable=" + editable + ", " + ", principal=" + principal + ", "
				+ (enumValues != null ? "enumValues=" + enumValues + ", " : "")
				+ (refEntity != null ? "refEntity=" + refEntity + ", " : "")
				+ (subfields != null ? "subfields=" + subfields : "") + "]";
	}
	
	public boolean isInUse(String field) {
		if (this.getName().equals(field)) {
			return true;
		} else {
			for (FieldDescription fd : subfields) {
				if (fd.isInUse(field)) {
					return true;
				}
			}
			return false;
		}
	}
	
	public boolean isRequiredOrIsRequiredSubfield(String field) {
		if (this.getName().equals(field)) {
			return this.isRequired();
		} else {
			for (FieldDescription fd : subfields) {
				if (fd.isRequiredOrIsRequiredSubfield(field)) {
					return true;
				}
			}
			return false;
		}
	}
	
	public FieldDescription getField(String fieldName) {
		if (this.getName().equals(fieldName)) {
			return this;
		}
		for (FieldDescription fd : this.getSubfields()) {
			FieldDescription subfield = fd.getField(fieldName);
			if (subfield != null) {
				return subfield;
			}
		}
		return null;
	}
	public boolean isPrincipal() {
		return principal;
	}
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	public Object convert(String value) throws ConversionException {
		return this.getType().convert(this, value);
	}
	public List<ReferenceOption> getOptions() {
		return options;
	}
	public void setOptions(List<ReferenceOption> options) {
		this.options = options;
	}
	public void searchOptions(PersonFields personFields) {
		try {
			if (PersonFieldNames.COUNTRY_REF.equals(this.getRefEntity())) {
				Collection<CountryBean> countries = ThalamusClientBeanFacade.getCountries();
				for (CountryBean cb : countries) {
					this.getOptions().add(new ReferenceOption(cb.getId(), cb.getName()));
				}
			}
			if (PersonFieldNames.DOCUMENT_TYPE_REF.equals(this.getRefEntity())) {
				Collection<DocumentTypeBean> docs = ThalamusClientBeanFacade.getDocumentTypes();
				for (DocumentTypeBean cb : docs) {
					this.getOptions().add(new ReferenceOption(cb.getId(), cb.getName()));
				}
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
	public boolean isSubfield() {
		return this.getParent() != null;
	}
	public FieldDescription getParent() {
		return parent;
	}
	public void setParent(FieldDescription parent) {
		this.parent = parent;
	}
	public String parseValue(Object value) {
		return this.getType().parseValue(this, value);
	}
	
	
}
