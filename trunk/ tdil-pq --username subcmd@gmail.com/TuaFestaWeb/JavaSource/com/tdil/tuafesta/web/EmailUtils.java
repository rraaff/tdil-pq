package com.tdil.tuafesta.web;

import java.sql.SQLException;
import java.util.List;
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

	private static final Logger Log = LoggerProvider.getLogger(EmailUtils.class);
	
	public static final String PROFESIONAL_EMAIL_VERIFICATION = "verif.email.prof";

	public static void sendEmail(String to, String lnk, String notificationtype) throws SQLException {
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
			content = StringUtils.replace(content, "SERVER_NAME", server.getPropvalue());
			
			if (lnk != null) {
				content = StringUtils.replace(content, "[LINK]", server.getPropvalue() + lnk);
			}

			com.tdil.utils.EmailUtils.sendEmail(content, to, notificationEmail.getFrom(), notificationEmail.getSubject(), properties);
		} catch (Exception e) {
			Log.error(e.getMessage(), e);
		}
	}

}
