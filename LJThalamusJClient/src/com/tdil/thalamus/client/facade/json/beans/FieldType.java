package com.tdil.thalamus.client.facade.json.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.utils.DateUtils;
import com.tdil.utils.StringUtils;

public enum FieldType {
	
	ADDRESS() {
		@Override
		public void parseFields(FieldDescription fieldDescription,
				JSONObject obj) {
			List<FieldDescription> subfields = new ArrayList<FieldDescription>();
			PersonFields.parseFields(obj.getJSONArray("subfields"), subfields, null);
			fieldDescription.setSubfields(subfields);
		}
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			throw new ConversionException();
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			return null;
		}
	},
	TEXT() {
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			return StringUtils.nullValueOf(value);
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			return (String)value;
		}
	},
	REFERENCE() {
		@Override
		public void parseFields(FieldDescription fieldDescription,
				JSONObject obj) {
			fieldDescription.setRefEntity(obj.getString("refEntity"));
		}
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			if(org.apache.commons.lang.StringUtils.isEmpty(value)) {
				return null;
			}
			return Integer.valueOf(value);
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			if (value == null) {
				return null;
			}
			return value.toString();
		}
	},
	ENUM() {
		@Override
		public void parseFields(FieldDescription fieldDescription,
				JSONObject obj) {
			List<String> enumValues = (List<String>)obj.get("enumValues");
			List<String> copy = new ArrayList<String>();
			copy.addAll(enumValues);
			fieldDescription.setEnumValues(copy);
		}
		
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			return StringUtils.nullValueOf(value);
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			if (value == null) {
				return null;
			}
			return value.toString();
		}
	},
	DATE() {
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			if (org.apache.commons.lang.StringUtils.isEmpty(value)) {
				return null;
			}
			return com.tdil.utils.DateUtils.parseDate(value).getTime();
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			if (value == null) {
				return null;
			}
			return DateUtils.formatDate(new Date((Long)value));
		}
	},
	EMAIL() {
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			return StringUtils.nullValueOf(value);
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			if (value == null) {
				return null;
			}
			return value.toString();
		}
	},
	PHONE_NUMBER() {
		@Override
		public void parseFields(FieldDescription fieldDescription,
				JSONObject obj) {
			List<FieldDescription> subfields = new ArrayList<FieldDescription>();
			PersonFields.parseFields(obj.getJSONArray("subfields"), subfields, null);
			fieldDescription.setSubfields(subfields);
		}
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			throw new ConversionException();
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			if (value == null) {
				return null;
			}
			return value.toString();
		}
	},
	BOOLEAN() {
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			if(org.apache.commons.lang.StringUtils.isEmpty(value)) {
				return null;
			}
			return Boolean.valueOf(value);
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			if (value == null) {
				return null;
			}
			return value.toString();
		}
	},
	DECIMAL() {
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			if (org.apache.commons.lang.StringUtils.isEmpty(value)) {
				return null;
			}
			try {
				return Float.parseFloat(value);
			} catch (NumberFormatException e) {
				throw new ConversionException();
			}
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			if (value == null) {
				return null;
			}
			return value.toString();
		}
	},
	DOCUMENT() {
		@Override
		public void parseFields(FieldDescription fieldDescription,
				JSONObject obj) {
			List<FieldDescription> subfields = new ArrayList<FieldDescription>();
			PersonFields.parseFields(obj.getJSONArray("subfields"), subfields, null);
			fieldDescription.setSubfields(subfields);
		}
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			return StringUtils.nullValueOf(value);
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			if (value == null) {
				return null;
			}
			return value.toString();
		}
	},
	PASSWORD() {
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			return value;
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			if (value == null) {
				return null;
			}
			return value.toString();
		}
	},
	LARGETEXT() {
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			return StringUtils.nullValueOf(value);
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			if (value == null) {
				return null;
			}
			return value.toString();
		}
	},
	NUMBER() {
		@Override
		public Object convert(FieldDescription fieldDescription, String value) throws ConversionException {
			if (org.apache.commons.lang.StringUtils.isEmpty(value)) {
				return null;
			}
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				throw new ConversionException();
			}
		}
		@Override
		public String parseValue(FieldDescription fieldDescription, Object value) {
			if (value == null) {
				return null;
			}
			return value.toString();
		}
	};

	public void parseFields(FieldDescription fieldDescription, JSONObject obj) {
		
	}

	public abstract Object convert(FieldDescription fieldDescription, String value) throws ConversionException;

	public abstract String parseValue(FieldDescription fieldDescription, Object value);
	
}
