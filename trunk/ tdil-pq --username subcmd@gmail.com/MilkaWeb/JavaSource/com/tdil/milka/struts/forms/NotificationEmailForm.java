package com.tdil.milka.struts.forms;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.NotificationEmailDAO;
import com.tdil.milka.dao.SystemPropertyDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.NotificationEmail;
import com.tdil.milka.model.NotificationEmailExample;
import com.tdil.milka.model.SystemProperty;
import com.tdil.milka.model.SystemPropertyExample;
import com.tdil.milka.utils.SystemPropertiesKeys;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.utils.EmailUtils;

public class NotificationEmailForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	
	private String notificationtype;
	private String description;
	private String replacements;
	private String content;
	
	// Para el test del email, podriamos testear con reemplazos???
	private String email;
	private boolean emailTest;
	private boolean errorsSending;
	private String errorText;
	
	private List<NotificationEmail> allNotificationEmails;
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.notificationtype = null;
		this.replacements = null;
		this.description = null;
		this.content = null;
		this.errorsSending = false;
		this.errorText = null;
		this.emailTest = false;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.errorsSending = false;
		this.emailTest = false;
	}

	@Override
	public void init() throws SQLException {
		reloadList();
	}

	private void reloadList() throws SQLException {
		NotificationEmailExample example = new NotificationEmailExample();
		example.setOrderByClause("notificationType");
		this.setAllNotificationEmails(DAOManager.getNotificationEmailDAO().selectNotificationEmailByExampleWithoutBLOBs(example));
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		NotificationEmail notificationEmail = DAOManager.getNotificationEmailDAO().selectNotificationEmailByPrimaryKey(id);
		if (notificationEmail != null) {
			this.objectId = id;
			this.notificationtype = notificationEmail.getNotificationtype();
			this.replacements = notificationEmail.getReplacements();
			this.description = notificationEmail.getDescription();
			this.content = notificationEmail.getContent();
		} 
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		reloadList();
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		NotificationEmailExample example = new NotificationEmailExample();
		example.createCriteria().andIdEqualTo(this.getObjectId());
		NotificationEmail notificationEmail = DAOManager.getNotificationEmailDAO().selectNotificationEmailByExampleWithBLOBs(example).get(0);
		notificationEmail.setDeleted(notificationEmail.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getNotificationEmailDAO().updateNotificationEmailByPrimaryKeySelective(notificationEmail);
	}
	

	@Override
	public void basicValidate(ValidationError validationError) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		NotificationEmailDAO systemPropertyDAO = DAOManager.getNotificationEmailDAO();
		if (this.getObjectId() == 0) {
			/* nada que hacer, no hay altas de estas entidades */
		} else {
			NotificationEmail systemProperty = new NotificationEmail();
			systemProperty.setId(this.getObjectId());
			// Solo admito el cambio del value
			systemProperty.setContent(this.getContent());
			systemPropertyDAO.updateNotificationEmailByPrimaryKeySelective(systemProperty);
		}
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<NotificationEmail> getAllNotificationEmails() {
		return allNotificationEmails;
	}

	public void setAllNotificationEmails(List<NotificationEmail> allSystemProperties) {
		this.allNotificationEmails = allSystemProperties;
	}

	public String getNotificationtype() {
		return notificationtype;
	}

	public void setNotificationtype(String notificationtype) {
		this.notificationtype = notificationtype;
	}

	public String getReplacements() {
		return replacements;
	}

	public void setReplacements(String replacements) {
		this.replacements = replacements;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void testEmail() {
		this.setEmailTest(true);
		// TODO marcar y llenar errores...
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					SystemPropertyDAO systemPropertyDAO = DAOManager.getSystemPropertyDAO();
					SystemPropertyExample smtpExample = new SystemPropertyExample();
					smtpExample.createCriteria().andPropkeyEqualTo(SystemPropertiesKeys.SMTP_SERVER);
					SystemProperty smtpServer = systemPropertyDAO.selectSystemPropertyByExample(smtpExample).get(0);
					
					SystemPropertyExample portExample = new SystemPropertyExample();
					portExample.createCriteria().andPropkeyEqualTo(SystemPropertiesKeys.SMTP_PORT);
					SystemProperty smtpPort = systemPropertyDAO.selectSystemPropertyByExample(portExample).get(0);
					
					try {
						EmailUtils.sendEmail(NotificationEmailForm.this.getContent(), NotificationEmailForm.this.getEmail(), NotificationEmailForm.this.getEmail(), "Test email for type " + NotificationEmailForm.this.getNotificationtype(), smtpServer.getPropvalue(), smtpPort.getPropvalue());
					} catch (MessagingException e) {
						NotificationEmailForm.this.errorsSending = true;
						setErrorText(e);
						getLog().error(e.getMessage(), e);
					}
				}
			});
		} catch (Exception e) {
			this.errorsSending = true;
			setErrorText(e);
			getLog().error(e.getMessage(), e);
		} 
	}
	
	private void setErrorText(Throwable e) {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteOut);
		e.printStackTrace(printStream);
		setErrorText(byteOut.toString());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(NotificationEmailForm.class);
	}

	public boolean isErrorsSending() {
		return errorsSending;
	}

	public void setErrorsSending(boolean errorsSending) {
		this.errorsSending = errorsSending;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	public boolean isEmailTest() {
		return emailTest;
	}
	public void setEmailTest(boolean emailSent) {
		this.emailTest = emailSent;
	}

}
