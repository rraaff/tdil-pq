package com.tdil.milka.web;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.apache.commons.lang.StringUtils;

import com.tdil.milka.dao.SystemPropertyDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.Author;
import com.tdil.milka.model.NotificationEmail;
import com.tdil.milka.model.NotificationEmailExample;
import com.tdil.milka.model.SystemProperty;
import com.tdil.milka.model.SystemPropertyExample;
import com.tdil.milka.struts.forms.MilkaPhotoAdministrationForm;
import com.tdil.milka.utils.SystemPropertiesKeys;

public class EmailUtils {
	
	public static final String tufotomilka = "tufotomilka";
	public static final String cartasdehijosapadres = "cartasdehijosapadres";
	public static final String finalesdeemail = "finalesdeemail";
	public static final String postits = "post-it";
	
	public static void sendContentApprovedEmail(int idauthor, String notificationtype, String experiencetype, String link) throws SQLException {
		SystemPropertyDAO systemPropertyDAO = DAOManager.getSystemPropertyDAO();
		
		SystemPropertyExample smtpExample = new SystemPropertyExample();
		smtpExample.createCriteria().andPropkeyEqualTo(SystemPropertiesKeys.SMTP_SERVER);
		SystemProperty smtpServer = systemPropertyDAO.selectSystemPropertyByExample(smtpExample).get(0);
		
		SystemPropertyExample portExample = new SystemPropertyExample();
		portExample.createCriteria().andPropkeyEqualTo(SystemPropertiesKeys.SMTP_PORT);
		SystemProperty smtpPort = systemPropertyDAO.selectSystemPropertyByExample(portExample).get(0);
		
		SystemPropertyExample fromExample = new SystemPropertyExample();
		fromExample.createCriteria().andPropkeyEqualTo(SystemPropertiesKeys.CONTENT_APPROVED_EMAIL_FROM);
		SystemProperty from = systemPropertyDAO.selectSystemPropertyByExample(fromExample).get(0);
		
		SystemPropertyExample subjectExample = new SystemPropertyExample();
		subjectExample.createCriteria().andPropkeyEqualTo(SystemPropertiesKeys.CONTENT_APPROVED_EMAIL_SUBJECT);
		SystemProperty subject = systemPropertyDAO.selectSystemPropertyByExample(subjectExample).get(0);
		
		String destLink = link;
		if (link == null) {
			SystemPropertyExample linkExample = new SystemPropertyExample();
			linkExample.createCriteria().andPropkeyEqualTo(experiencetype);
			SystemProperty linkSysProperty = systemPropertyDAO.selectSystemPropertyByExample(linkExample).get(0);
			if (linkSysProperty != null) {
				destLink = linkSysProperty.getPropvalue();
			}
			
		}
		
		SystemPropertyExample serverExample = new SystemPropertyExample();
		serverExample.createCriteria().andPropkeyEqualTo(SystemPropertiesKeys.SERVER_NAME);
		SystemProperty server = systemPropertyDAO.selectSystemPropertyByExample(serverExample).get(0);
		
		Author author = DAOManager.getAuthorDAO().selectAuthorByPrimaryKey(idauthor);
		NotificationEmailExample notificationEmailExample = new NotificationEmailExample();
		notificationEmailExample.createCriteria().andNotificationtypeEqualTo(notificationtype);
		NotificationEmail notificationEmail = DAOManager.getNotificationEmailDAO().selectNotificationEmailByExampleWithBLOBs(notificationEmailExample).get(0);
		String content = notificationEmail.getContent();
		content = StringUtils.replace(content, "AUTHOR_NAME", author.getName());
		content = StringUtils.replace(content, "EXPERIENCE_LINK", destLink);
		content = StringUtils.replace(content, "SERVER_NAME", server.getPropvalue());
		try {
			com.tdil.utils.EmailUtils.sendEmail(content, author.getEmail(), from.getPropvalue(), subject.getPropvalue(), smtpServer.getPropvalue(), smtpPort.getPropvalue());
		} catch (MessagingException e) {
			MilkaPhotoAdministrationForm.getLog().error(e.getMessage(), e);
		}
	}

}
