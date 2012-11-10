package com.tdil.tuafesta.web;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.tuafesta.dao.SystemPropertyDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.NotificationEmail;
import com.tdil.tuafesta.model.NotificationEmailExample;
import com.tdil.tuafesta.model.SystemProperty;
import com.tdil.tuafesta.model.SystemPropertyExample;
import com.tdil.tuafesta.utils.SystemPropertiesKeys;

public class EmailUtils {


	public static final String SERVER_NAME_KEY = "SERVER_NAME";
	public static final String LINK_KEY = "[LINK]";
	public static final String PASSWORD_KEY = "[PASSWORD]";
	
	public static final String SELL_NAME_KEY = "[SELL_NAME]";
	public static final String MOTIVE_KEY = "[MOTIVE]";

	private static final Logger Log = LoggerProvider.getLogger(EmailUtils.class);
	
	public static final String PROFESIONAL_EMAIL_VERIFICATION = "verif.email.prof";
	public static final String CLIENT_EMAIL_VERIFICATION = "verif.email.client";
	
	public static final String PROFESIONAL_PASSWORD_RESET = "passreset.email.prof";
	public static final String CLIENT_PASSWORD_RESET = "passreset.email.client";
	
	public static final String DISAPPROVE_PERSONAL = "disapprove.personal";
	public static final String DISAPPROVE_BUSINESS = "disapprove.business";
	public static final String DISAPPROVE_SELL = "disapprove.sell";

	public static void sendEmail(String to, Map<String, String> replacements, String notificationtype) throws SQLException {
		try {

			SystemPropertyDAO systemPropertyDAO = DAOManager.getSystemPropertyDAO();

			SystemPropertyExample smtpExample = new SystemPropertyExample();
			smtpExample.createCriteria().andPropkeyLike("mail.smtp%").andDeletedEqualTo(0);
			List<SystemProperty> list = systemPropertyDAO.selectSystemPropertyByExample(smtpExample);
			Properties properties = new Properties();
			for (SystemProperty sp : list) {
				properties.put(sp.getPropkey(), sp.getPropvalue());
			}

			SystemPropertyExample serverExample = new SystemPropertyExample();
			serverExample.createCriteria().andPropkeyEqualTo(SystemPropertiesKeys.SERVER_NAME);
			SystemProperty server = systemPropertyDAO.selectSystemPropertyByExample(serverExample).get(0);

			NotificationEmailExample notificationEmailExample = new NotificationEmailExample();
			notificationEmailExample.createCriteria().andNotificationtypeEqualTo(notificationtype);
			NotificationEmail notificationEmail = DAOManager.getNotificationEmailDAO()
					.selectNotificationEmailByExampleWithBLOBs(notificationEmailExample).get(0);
			String content = notificationEmail.getContent();
			content = StringUtils.replace(content, SERVER_NAME_KEY, server.getPropvalue());
			
			for (Map.Entry<String, String> entry : replacements.entrySet()) {
				if (entry.getKey().equals(LINK_KEY)) {
					content = StringUtils.replace(content, entry.getKey(), server.getPropvalue() + entry.getValue());
				} else {
					content = StringUtils.replace(content, entry.getKey(), entry.getValue());
				}
			}
			com.tdil.utils.EmailUtils.sendEmail(content, to, notificationEmail.getFrom(), notificationEmail.getSubject(), properties);
		} catch (Exception e) {
			Log.error(e.getMessage(), e);
		}
	}

}
