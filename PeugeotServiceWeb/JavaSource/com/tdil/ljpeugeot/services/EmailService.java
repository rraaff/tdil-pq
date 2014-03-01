package com.tdil.ljpeugeot.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
	
	public static final String SERVER_NAME_KEY = "[SERVER_NAME]";
	public static final String FIRST_NAME_KEY = "[FIRST_NAME]";
	public static final String LAST_NAME_KEY = "[LAST_NAME]";
	public static final String DOMAIN_KEY = "[DOMAIN]";
	public static final String KM_KEY = "[KM]";
	public static final String DEALER_KEY = "[DEALER]";
	
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

	public static void sendEmail(String to, Map<String, String> replacements, String notificationtype) throws SQLException {
		try {
			Properties props = GenericTransactionExecutionService.getInstance().execute(new GetSMTPProperties());
			
			NotificationEmailExample notificationEmailExample = new NotificationEmailExample();
			notificationEmailExample.createCriteria().andNotificationtypeEqualTo(notificationtype);
			NotificationEmail notificationEmail = DAOManager.getNotificationEmailDAO()
					.selectNotificationEmailByExample(notificationEmailExample).get(0);
			String content = notificationEmail.getContent();
			content = StringUtils.replace(content, SERVER_NAME_KEY, LJPeugeotConfig.getFRONT_SERVER());
			
			for (Map.Entry<String, String> entry : replacements.entrySet()) {
				content = StringUtils.replace(content, entry.getKey(), entry.getValue());
			}
			com.tdil.utils.EmailUtils.sendEmail(content, to, notificationEmail.getFrom(), notificationEmail.getSubject(), props);
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(EmailService.class);
	}
}
