package com.tdil.thalamus.client.facade.json.beans;

import net.sf.json.JSONObject;

public class RequestResetPasswordBean {

	private String principal;

	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	public static void main(String[] args) {
		RequestResetPasswordBean requestResetPasswordBean = new RequestResetPasswordBean();
		requestResetPasswordBean.setPrincipal("user1@mail.com");
		
		JSONObject jsonObject = JSONObject.fromObject( requestResetPasswordBean );  
		System.out.println( jsonObject.toString(2) ); 
	}
}
