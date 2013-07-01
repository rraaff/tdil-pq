package com.tdil.lojack.prevent.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="LoginResponse")
public class LoginResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@XStreamAlias(value="Status")
	private String status;
	
	@XStreamAlias(value="UserToken")
	private String userToken;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginResponse [");
		if (userToken != null) {
			builder.append("userToken=");
			builder.append(userToken);
			builder.append(", ");
		}
		if (status != null) {
			builder.append("status=");
			builder.append(status);
		}
		builder.append(']');
		return builder.toString();
	}
	
	
}
