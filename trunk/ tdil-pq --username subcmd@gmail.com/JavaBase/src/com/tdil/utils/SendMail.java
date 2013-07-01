package com.tdil.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;

/**
 * Insert the type's description here. Creation date: (4/9/2002 11:51:29 AM)
 * 
 */
public class SendMail {
	
	private static final InternetAddress[] EMPTY_ARRAY = new InternetAddress[0];
	// Represents the mail Session used to send mails
	private Session mailSession = null;
	private Properties properties;

	// private String mailSMTPHost = null;
	// public String mailSMTPPORT = "25";

	/**
	 * Constructor
	 * 
	 * @param mailSMTPHost
	 *            String that must contains the SMTP host address to send mails.
	 */
	public SendMail(String aMailSMTPHost) {
		super();

		Properties properties = new Properties();
		properties.put("mail.smtp.host", aMailSMTPHost);
		properties.put("mail.smtp.port", 25);
		setProperties(properties);
		this.initializeMailSession();
	}

	public SendMail(String aMailSMTPHost, String port) {
		super();
		Properties properties = new Properties();
		properties.put("mail.smtp.host", aMailSMTPHost);
		properties.put("mail.smtp.port", port);
		setProperties(properties);
		this.initializeMailSession();
	}

	public SendMail(Properties properties) {
		super();
		setProperties(properties);
		this.initializeMailSession();
	}

	/**
	 * Constructor
	 * 
	 * @param mailSMTPHost
	 *            String that must contains the SMTP host address to send mails.
	 */
	public SendMail(String aMailSMTPHost, boolean debug) {
		super();
		Properties properties = new Properties();
		properties.put("mail.smtp.host", aMailSMTPHost);
		properties.put("mail.smtp.port", 25);
		setProperties(properties);
		this.initializeMailSession(debug);
	}

	public SendMail(String aMailSMTPHost, String port, boolean debug) {
		super();
		Properties properties = new Properties();
		properties.put("mail.smtp.host", aMailSMTPHost);
		properties.put("mail.smtp.port", port);
		setProperties(properties);
		this.initializeMailSession(debug);
	}

	/**
	 * Converts an ArrayList that contains Internet Addresses (Emails, etc) to
	 * an InternetAddress Object Type Array.
	 * 
	 * @param anArrayList
	 *            java.util.ArrayList that contains a list of Internet Addresses
	 *            to COnvert
	 * @return javax.mail.internet.InternetAddress[]
	 */
	private InternetAddress[] convertArrayListToInternetAddressList(ArrayList<InternetAddress> anArrayList) {
		return anArrayList.toArray(EMPTY_ARRAY);
	}

	/**
	 * Answers a Mail Session
	 * 
	 * @return javax.mail.Session
	 */
	public javax.mail.Session getMailSession() {
		return mailSession;
	}

	/**
	 * Initialize and gets a Mail Session
	 */
	private void initializeMailSession() {
		this.initializeMailSession(true);
	}

	/**
	 * Initialize and gets a Mail Session
	 */
	private void initializeMailSession(boolean debug) {
		String useAuth = this.getProperties().getProperty("mail.smtp.auth");
		if ("true".equals(useAuth)) {
			String user = this.getProperties().getProperty("mail.smtp.user");
			String password = this.getProperties().getProperty("mail.smtp.password");
			this.setMailSession(Session.getInstance(this.getProperties(), new SMTPAuthenticator(user, password)));
			this.getMailSession().setDebug(debug);
		} else {
			this.setMailSession(Session.getInstance(this.getProperties(), null));
			this.getMailSession().setDebug(debug);
		}
	}

	/**
	 * Reads a Plain Text file and returns his content Creation date: (4/9/2002
	 * 6:15:57 PM)
	 * 
	 * @param fileName
	 *            String that contains the File Name to Read.
	 * @return java.lang.String
	 * @exception java.io.IOException
	 *                The exception description.
	 * @exception java.io.FileNotFoundException
	 *                The exception description.
	 */
	public String readPlainTextFile(String fileName) throws java.io.IOException, java.io.FileNotFoundException {
		FileInputStream fr = new FileInputStream(fileName);
		BufferedInputStream dis = new BufferedInputStream(fr);

		StringBuilder buffer = new StringBuilder();
		int a;
		while ((a = dis.read()) >= 0) {
			buffer.append((char) a);
		}
		dis.close();
		fr.close();
		return buffer.toString();
	}

	/**
	 * Sends mail using parameters options
	 * 
	 * @param from
	 *            String that contains the e-mail address from who sends the
	 *            e-mail.
	 * @param toList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the TO.
	 * @param ccList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the CC (Carbon Copies TO).
	 * @param bccList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the BCC (Blank Carbon Copies TO).
	 * @param subject
	 *            String that contains the e-mail subject.
	 * @param body
	 *            Body Text
	 * @param fileAttachmentList
	 *            ArrayList that contains a List of String with file names to
	 *            attach.
	 * @param contentAttachmentList
	 *            ArrayList that contains a Associations, where key represents
	 *            the Content ID, and Value is the file name to attach.
	 */
	public void sendCustomizedMail(String from, ArrayList<InternetAddress> toList, ArrayList<InternetAddress> ccList, ArrayList<InternetAddress> bccList, String subject,
			boolean isHTML, String bodyContent, ArrayList<String> fileAttachmentList)
			throws MessagingException {

		try {

			MimeBodyPart mimeBodyPart = new MimeBodyPart();

			if (isHTML)
				mimeBodyPart.setContent(bodyContent, "text/html;charset=" + System.getProperty("file.encoding"));
			else
				mimeBodyPart.setText(bodyContent);

			mimeBodyPart.setDisposition(MimeBodyPart.INLINE);

			MimeMultipart mimeMultiPart = new MimeMultipart((isHTML ? "related" : "mixed"));
			mimeMultiPart.addBodyPart(mimeBodyPart);

			sendMail(from, toList, ccList, bccList, subject, mimeMultiPart, fileAttachmentList);

		} catch (MessagingException mex) {
			throw mex;
		}
	}

	/**
	 * Sends mail using parameters options
	 * 
	 * @param from
	 *            String that contains the e-mail address from who sends the
	 *            e-mail.
	 * @param toList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the TO.
	 * @param ccList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the CC (Carbon Copies TO).
	 * @param bccList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the BCC (Blank Carbon Copies TO).
	 * @param subject
	 *            String that contains the e-mail subject.
	 * @param fileName
	 *            String that contains the name of the file that contains the
	 *            e-mail body.
	 * @param fileAttachmentList
	 *            ArrayList that contains a List of String with file names to
	 *            attach.
	 * @param contentAttachmentList
	 *            ArrayList that contains a Associations, where key represents
	 *            the Content ID, and Value is the file name to attach.
	 */
	public void sendCustomizedMailFromFile(String from, ArrayList<InternetAddress> toList, ArrayList<InternetAddress> ccList, ArrayList<InternetAddress> bccList,
			String subject, boolean isHTML, String fileName, ArrayList<String> fileAttachmentList) throws MessagingException, FileNotFoundException, IOException {

		String body = this.readPlainTextFile(fileName);
		sendCustomizedMail(from, toList, ccList, bccList, subject, isHTML, body, fileAttachmentList);
	}

	/**
	 * Sends a mail replacing the hooks with the associations values.
	 * 
	 * @param from
	 *            String that contains the e-mail address from who sends the
	 *            e-mail.
	 * @param to
	 *            String that contains the e-mail address to who wants the
	 *            sender to send the mail.
	 * @param subject
	 *            String that contains the e-mail subject.
	 * @param body
	 *            String that contains the e-mail body.
	 * @param associationsList
	 *            ArrayList that contains Association Objects of Strings
	 *            (Key/Value)
	 */
	public void sendCustomizedPlainTextMail(String from, String to, String subject, String body)
			throws MessagingException {

		ArrayList<InternetAddress> toList = new java.util.ArrayList<InternetAddress>();
		toList.add(new InternetAddress(to));

		this.sendCustomizedMail(from, toList, null, null, subject, false, body, null);
	}

	public void sendCustomizedHtmlMail(String from, String to, String subject, String body) throws MessagingException {

		ArrayList<InternetAddress> toList = new java.util.ArrayList<InternetAddress>();
		toList.add(new InternetAddress(to));

		this.sendCustomizedMail(from, toList, null, null, subject, true, body, null);
	}

	/**
	 * Sends mail using a file as body text
	 * 
	 * @param from
	 *            String that contains the e-mail address from who sends the
	 *            e-mail.
	 * @param to
	 *            String that contains the e-mail address to who wants the
	 *            sender to send the mail.
	 * @param subject
	 *            String that contains the e-mail subject.
	 * @param plainTextFile
	 *            String that contains the name of the file that contains the
	 *            e-mail body.
	 * @param associationsList
	 *            ArrayList that contains Association Objects of Strings
	 *            (Key/Value)
	 */
	public void sendCustomizedPlainTextMailFromFile(String from, String to, String subject, String fileName) throws FileNotFoundException, IOException, MessagingException {

		String body = this.readPlainTextFile(fileName);

		this.sendCustomizedPlainTextMail(from, to, subject, body);
	}

	/**
	 * Sends mail using parameters options
	 * 
	 * @param from
	 *            String that contains the e-mail address from who sends the
	 *            e-mail.
	 * @param toList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the TO.
	 * @param ccList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the CC (Carbon Copies TO).
	 * @param bccList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the BCC (Blank Carbon Copies TO).
	 * @param subject
	 *            String that contains the e-mail subject.
	 * @param mimeBodyPart
	 *            MimeBodyPart that contains the e-mail Body Part.
	 * @param fileAttachmentList
	 *            ArrayList that contains a List of String with file names to
	 *            attach.
	 */
	public void sendMail(String from, ArrayList<InternetAddress> toList, ArrayList<InternetAddress> ccList, ArrayList<InternetAddress> bccList, String subject,
			MimeBodyPart mimeBodyPart, ArrayList<String> fileAttachmentList) throws MessagingException {

		try {
			MimeMultipart mimeMultipart = new MimeMultipart();

			mimeMultipart.addBodyPart(mimeBodyPart);

			sendMail(from, toList, ccList, bccList, subject, mimeMultipart, fileAttachmentList);

		} catch (MessagingException mex) {
			throw mex;
		}
	}

	/**
	 * Sends mail using parameters options
	 * 
	 * @param from
	 *            String that contains the e-mail address from who sends the
	 *            e-mail.
	 * @param toList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the TO.
	 * @param ccList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the CC (Carbon Copies TO).
	 * @param bccList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the BCC (Blank Carbon Copies TO).
	 * @param subject
	 *            String that contains the e-mail subject.
	 * @param mimeMultiPart
	 *            MimeMultiPart that contains the Mime MultiPart Object for the
	 *            Mime Message.
	 */
	public void sendMail(String from, ArrayList<InternetAddress> toList, ArrayList<InternetAddress> ccList, ArrayList<InternetAddress> bccList, String subject,
			MimeMultipart mimeMultiPart) throws MessagingException {

		Session mailSession = this.getMailSession();

		try {
			StringBuilder mailCheckDetails = new StringBuilder(50);
			if (from == null) {
				mailCheckDetails.append("Sending mail with FROM null/n");
				from = "";
			}
			if (toList == null || toList.isEmpty()) {
				mailCheckDetails.append("Sending mail with TO null or empty/n");
				toList = new ArrayList<InternetAddress>();
			}
			if (subject == null) {
				mailCheckDetails.append("Sending mail with SUBJECT null/n");
				subject = "";
			}
			if (!StringUtils.isEmpty(mailCheckDetails.toString())) {
				mailCheckDetails.append("From: ");
				mailCheckDetails.append(from);
				mailCheckDetails.append(" To: ");
				mailCheckDetails.append(toList.toString());
				mailCheckDetails.append(" Subject: ");
				mailCheckDetails.append(subject);
				return;
			}

			InternetAddress fromIA = new InternetAddress(from);

			MimeMessage mimeMessage = new MimeMessage(mailSession);
			mimeMessage.setFrom(fromIA);

			mimeMessage.setRecipients(Message.RecipientType.TO, convertArrayListToInternetAddressList(toList));
			if (ccList != null && ccList.size() > 0)
				mimeMessage.setRecipients(Message.RecipientType.CC, convertArrayListToInternetAddressList(ccList));
			if (bccList != null && bccList.size() > 0)
				mimeMessage.setRecipients(Message.RecipientType.BCC, convertArrayListToInternetAddressList(bccList));

			// not using mimeMessage.setSubject(subject); because does not
			// accept international charactes
			mimeMessage.setSubject(subject, System.getProperty("file.encoding"));

			mimeMessage.setContent(mimeMultiPart);
			mimeMessage.setSentDate(new Date());
			Transport.send(mimeMessage);
		} catch (MessagingException mex) {
			throw mex;
		}
	}

	/**
	 * Sends mail using parameters options
	 * 
	 * @param from
	 *            String that contains the e-mail address from who sends the
	 *            e-mail.
	 * @param toList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the TO.
	 * @param ccList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the CC (Carbon Copies TO).
	 * @param bccList
	 *            ArrayList that contains a List of InternetAddress objects to
	 *            put in the BCC (Blank Carbon Copies TO).
	 * @param subject
	 *            String that contains the e-mail subject.
	 * @param mimeMultiPart
	 *            MimeMultiPart that contains the Mime MultiPart Object for the
	 *            Mime Message.
	 * @param fileAttachmentList
	 *            ArrayList that contains a List of String with file names to
	 *            attach.
	 * @param contentAttachmentList
	 *            ArrayList that contains a Associations, where key represents
	 *            the Content ID, and Value is the file name to attach.
	 */
	public void sendMail(String from, ArrayList<InternetAddress> toList, ArrayList<InternetAddress> ccList, ArrayList<InternetAddress> bccList, String subject,
			MimeMultipart mimeMultiPart, ArrayList<String> fileAttachmentList) throws MessagingException {

		try {
			// Adds File Attachments
			if (fileAttachmentList != null && fileAttachmentList.size() > 0)
				for (String fileName : fileAttachmentList) {
					FileDataSource fileDataSource = new FileDataSource(fileName);
					MimeBodyPart attachmentMimeBodyPart = new MimeBodyPart();
					attachmentMimeBodyPart.setDataHandler(new DataHandler(fileDataSource));
					attachmentMimeBodyPart.setFileName(fileDataSource.getName());
					attachmentMimeBodyPart.setDisposition(MimeBodyPart.ATTACHMENT);

					mimeMultiPart.addBodyPart(attachmentMimeBodyPart);
				}

			sendMail(from, toList, ccList, bccList, subject, mimeMultiPart);

		} catch (MessagingException mex) {
			throw mex;
		}
	}

	/**
	 * Sets the Mail Session
	 * 
	 * @param newMailSession
	 *            javax.mail.Session
	 */
	private void setMailSession(javax.mail.Session newMailSession) {
		mailSession = newMailSession;
	}

	private Properties getProperties() {
		return properties;
	}

	private void setProperties(Properties properties) {
		this.properties = properties;
	}

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("mail.smtp.user", "contacto@milka.com.ar");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		//SecurityManager security = System.getSecurityManager();

		try {
			Authenticator auth = new SMTPAuthenticator("contacto@milka.com.ar", "mil123ka");
			Session session = Session.getInstance(props, auth);
			// session.setDebug(true);

			MimeMessage msg = new MimeMessage(session);
			msg.setText("test");
			msg.setSubject("Test");
			msg.setFrom(new InternetAddress("contacto@milka.com.ar"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("subcmd@gmail.com"));
			Transport.send(msg);
		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}
}
