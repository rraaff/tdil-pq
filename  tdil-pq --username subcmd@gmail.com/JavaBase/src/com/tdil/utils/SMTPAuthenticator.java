package com.tdil.utils;

import javax.mail.PasswordAuthentication;

class SMTPAuthenticator extends javax.mail.Authenticator {
	private String user;
	private String password;
	
	public SMTPAuthenticator(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, password);
	}
}