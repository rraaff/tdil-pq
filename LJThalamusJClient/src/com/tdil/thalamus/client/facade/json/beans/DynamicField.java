package com.tdil.thalamus.client.facade.json.beans;

import java.util.List;

import net.sf.json.JSONObject;

public class DynamicField {

	private String value;
	private FieldDescription fieldDescription;
	
	public DynamicField() {
	}
	
	public DynamicField(FieldDescription fieldDescription) {
		super();
		this.fieldDescription = fieldDescription;
	}
	
	public String getDescription() {
		return this.getFieldDescription().getName();
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean getBooleanValue() {
		return Boolean.valueOf(this.value);
	}
	public void setBooleanValue(boolean value) {
		this.setValue(Boolean.toString(value));
	}
	
	public int getIntValue() {
		return Integer.valueOf(this.value);
	}
	public void setIntValue(int value) {
		this.setValue(Integer.toString(value));
	}
	
	public String getDateValue() {
		return this.value;
	}
	public void setDateValue(String value) {
		this.value = value;
	}
	
	public String getDecimalValue() {
		return this.value;
	}
	public void setDecimalValue(String value) {
		this.value = value;
	}
	
	
	public List<ReferenceOption> getOptions() {
		return this.getFieldDescription().getOptions();
	}
	
	public FieldDescription getFieldDescription() {
		return fieldDescription;
	}
	public void setFieldDescription(FieldDescription fieldDescription) {
		this.fieldDescription = fieldDescription;
	}
	
	private Object convert() throws ConversionException {
		return this.getFieldDescription().convert(this.getValue());
	}

	public Object getObjectValue() {
		try {
			// TODO Auto-generated method stub
			return this.convert();
		} catch (ConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void takeInitialValueFrom(JSONObject profile) {
		if (this.getFieldDescription().isSubfield()) {
			if (profile.containsKey(this.getFieldDescription().getParent().getName())) {
				JSONObject data = profile.getJSONObject(this.getFieldDescription().getParent().getName());
				if (data.containsKey(this.getFieldDescription().getName())) {
					Object value = data.get(this.getFieldDescription().getName());
					this.setValue(this.getFieldDescription().parseValue(value));
				}
			}
		} else {
			if (profile.containsKey(this.getFieldDescription().getName())) {
				Object value = profile.get(this.getFieldDescription().getName());
				this.setValue(this.getFieldDescription().parseValue(value));
			}
		}
	}
	
}
