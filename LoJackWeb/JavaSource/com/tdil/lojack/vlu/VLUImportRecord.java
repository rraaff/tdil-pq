package com.tdil.lojack.vlu;

public class VLUImportRecord {

	private String dni;
	private String domain;
	private String message;
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VLUImportRecord [");
		if (dni != null) {
			builder.append("dni=");
			builder.append(dni);
			builder.append(", ");
		}
		if (domain != null) {
			builder.append("domain=");
			builder.append(domain);
			builder.append(", ");
		}
		if (message != null) {
			builder.append("message=");
			builder.append(message);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
}
