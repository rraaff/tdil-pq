package com.tdil.ljpeugeot.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.dao.SystemPropertyDAO;
import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.NotificationEmail;
import com.tdil.ljpeugeot.model.NotificationEmailExample;
import com.tdil.ljpeugeot.model.SystemProperty;
import com.tdil.ljpeugeot.model.SystemPropertyExample;
import com.tdil.ljpeugeot.utils.LJPeugeotConfig;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class EmailService {

	public static final String FIRST_ADVICE = "first.advice";
	public static final String SECOND_ADVICE = "second.advice";
	public static final String THIRD_ADVICE = "third.advice";
	public static final String DEALER_ADVICE = "dealer.advice";
	
	public static final String SERVER_NAME_KEY = "[SERVER_NAME]";
	public static final String FIRST_NAME_KEY = "[FIRST_NAME]";
	public static final String LAST_NAME_KEY = "[LAST_NAME]";
	public static final String DOMAIN_KEY = "[DOMAIN]";
	public static final String ACTUAL_KM_KEY = "[ACTUAL_KM]";
	public static final String LAST_SERVICE_KM_KEY = "[LAST_SERVICE_KM]";
	public static final String NEXT_SERVICE_KM_KEY = "[NEXT_SERVICE_KM]";
	public static final String NEXT_SERVICE_DATE_KEY = "[NEXT_SERVICE_DATE]";
	
	public static final String DEALER_SECTION_KEY = "[DEALER_SECTION]";
	public static final String DEALER_NAME_KEY = "[DEALER_NAME]";
	public static final String DEALER_ADDRESS_KEY = "[DEALER_ADDRESS]";
	public static final String DEALER_PHONE_KEY = "[DEALER_PHONE]";
	public static final String DEALER_EMAIL_KEY = "[DEALER_EMAIL]";
	
	public static class GetSMTPProperties implements TransactionalActionWithResult<Properties> {
		@Override
		public Properties executeInTransaction() throws SQLException {
			SystemPropertyDAO systemPropertyDAO = DAOManager.getSystemPropertyDAO();
			SystemPropertyExample smtpExample = new SystemPropertyExample();
			smtpExample.createCriteria().andPropkeyLike("mail.smtp%").andDeletedEqualTo(0);
			List<SystemProperty> list = systemPropertyDAO.selectSystemPropertyByExample(smtpExample);
			Properties properties = new Properties();
			for (SystemProperty sp : list) {
				properties.put(sp.getPropkey(), sp.getPropvalue());
			}
			return properties;
		}
	}

	public static void sendEmail(String to, Map<String, String> replacements, List<String> sectionsToRemove, String notificationtype) throws SQLException {
		try {
			Properties props = GenericTransactionExecutionService.getInstance().execute(new GetSMTPProperties());
			
			NotificationEmailExample notificationEmailExample = new NotificationEmailExample();
			notificationEmailExample.createCriteria().andNotificationtypeEqualTo(notificationtype);
			NotificationEmail notificationEmail = DAOManager.getNotificationEmailDAO()
					.selectNotificationEmailByExample(notificationEmailExample).get(0);
			String from = notificationEmail.getFrom();
			String content = notificationEmail.getContent();
			String subject = notificationEmail.getSubject();
			
			sendEmail(from, to, subject, content, replacements, sectionsToRemove, props);
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}

	public static void sendEmail(String from, String to, String subject, String content, Map<String, String> replacements,
			List<String> sectionsToRemove, Properties props) throws MessagingException {
		content = StringUtils.replace(content, SERVER_NAME_KEY, LJPeugeotConfig.getFRONT_SERVER());
		
		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			content = StringUtils.replace(content, entry.getKey(), entry.getValue());
		}
		for (String section : sectionsToRemove) {
			int indexStart = content.indexOf("[" + section + "]");
			while(indexStart != -1) {
				String endSecton = "[/" + section + "]";
				int indexEnd = content.indexOf(endSecton);
				content = content.substring(0, indexStart) + content.substring(indexEnd + endSecton.length());
				indexStart = content.indexOf("[" + section + "]");
			}
		}
		Pattern pattern = Pattern.compile("\\[[^\\]]*\\]");
		Matcher matcher = pattern.matcher(content);
		if (matcher.find()) {
			getLog().error("No puede enviarse el email para " + to + " porque hay variables sin reemplazar " + content);
		} else {
			com.tdil.utils.EmailUtils.sendEmail(content, to, from, subject, props);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(EmailService.class);
	}
}
