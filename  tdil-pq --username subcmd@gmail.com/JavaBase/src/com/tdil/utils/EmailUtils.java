package com.tdil.utils;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;


public class EmailUtils {
	
	private static final ExecutorService exec = Executors.newFixedThreadPool(10);

	public static void sendEmail(String email, String to, String from, String subject, String smtpServer, String smtpPort) throws MessagingException {
		SendMail sendMail = new SendMail(smtpServer, smtpPort);
		sendMail.sendCustomizedHtmlMail(from, to, subject, email);
	}
	
	public static void sendEmail(final String email, final String to, final String from, final String subject, final Properties properties) throws MessagingException {
		exec.execute(new Runnable() {
			@Override
			public void run() {
				try {
					SendMail sendMail = new SendMail(properties);
					sendMail.sendCustomizedHtmlMail(from, to, subject, email);
				} catch (MessagingException e) {
					getLog().error(e.getMessage(), e);
				}
			}
		});
	}
	
	public static Logger getLog() {
		return LoggerProvider.getLogger(EmailUtils.class);
	}
	
	/*
	public static void sendAdminEmailUserRequestPasswordReset(String fullName, String username) throws MessagingException {
		NotificationEmail notificationEmail = SystemConfig.getMailForPasswordReset();
		String body = notificationEmail.getEmailText();
		body = body.replace("{FULLNAME}", fullName);
		body = body.replace("{USERNAME}", username);
		body = body.replace("{SERVER}", SystemConfig.getServerUrl());
		new SendMail(SystemConfig.getMailServer()).sendCustomizedHtmlMail(notificationEmail.getEmailFrom(), notificationEmail.getEmailTo(), notificationEmail.getEmailText(), body);
	}

	public static void sendNewObservationEmail(final ObservationForm observationForm) {
		new Thread() {
			@Override
			public void run() {
				final NewObservationNotification notification = new NewObservationNotification();
				notification.setUser(observationForm.getUser());
				notification.setVersionId(observationForm.getVersionId());
				notification.setParagraphNumber(observationForm.getParagraphNumberForDiplay());
				try {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							notification.init();
							notification.setCreationDate(observationForm.getCreationDate());
						}
					});
					notification.notifyDelegates();
				} catch (SQLException e) {
					getLog().error(e.getMessage(), e);
				} catch (ValidationException e) {
					getLog().error(e.getMessage(), e);
				}
			}
		}.start();	
	}
	
	public static void sendNewConsolidatedVersionEmail(final SystemUser user, final CreateDocumentForm documentForm) {
		new Thread() {
			@Override
			public void run() {
				final NewVersionNotification notification = new NewVersionNotification();
				notification.setUser(user);
				notification.setVersionId(String.valueOf(documentForm.getVersionId()));
				try {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							notification.init();
						}
					});
					notification.notifyDelegates();
				} catch (SQLException e) {
					getLog().error(e.getMessage(), e);
				} catch (ValidationException e) {
					getLog().error(e.getMessage(), e);
				}
			}
		}.start();	
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(EmailUtils.class);
	}
	*/
}
