package com.tdil.utils;


public class EmailUtils {

	/*
	 * TODO Ejemplo para los proyectos
	public static void sendPasswordEmail(String email, String fullName, String username, String password) throws MessagingException {
		NotificationEmail notificationEmail = SystemConfig.getMailForNewPassword();
		String body = notificationEmail.getEmailText();
		body = body.replace("{FULLNAME}", fullName);
		body = body.replace("{USERNAME}", username);
		body = body.replace("{PASSWORD}", password);
		body = body.replace("{SERVER}", SystemConfig.getServerUrl());
		new SendMail(SystemConfig.getMailServer()).sendCustomizedHtmlMail(notificationEmail.getEmailFrom(), email, notificationEmail.getEmailSubject(), body);
	}
	
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
