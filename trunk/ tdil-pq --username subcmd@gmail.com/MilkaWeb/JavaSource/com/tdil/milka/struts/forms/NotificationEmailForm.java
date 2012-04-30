package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.milka.dao.NotificationEmailDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.NotificationEmail;
import com.tdil.milka.model.NotificationEmailExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;

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
	
	private List<NotificationEmail> allNotificationEmails;
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.notificationtype = null;
		this.replacements = null;
		this.description = null;
		this.content = null;
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
		// TODO Auto-generated method stub
		// asas
		System.out.println("testing " + this.getEmail());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
