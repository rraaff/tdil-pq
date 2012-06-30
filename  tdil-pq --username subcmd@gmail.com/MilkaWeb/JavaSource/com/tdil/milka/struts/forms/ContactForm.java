package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.SystemPropertyDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.SystemProperty;
import com.tdil.milka.model.SystemPropertyExample;
import com.tdil.milka.utils.SystemPropertiesKeys;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.utils.EmailUtils;
import com.tdil.validations.FieldValidation;

public class ContactForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private String name;
	private String email;
	private String content;
	
	private static final String name_key = "ContactForm.name";
	private static final String email_key = "ContactForm.email";
	private static final String content_key = "ContactForm.content";
	
	@Override
	public void reset() throws SQLException {
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
	}
	
	@Override
	public void basicValidate(ValidationError error) {
		FieldValidation.validateText(this.getName(), name_key, 150, error);
		FieldValidation.validateEmail(this.getEmail(), email_key, error);
		FieldValidation.validateText(this.getContent(), content_key, 4000, error);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		SystemPropertyDAO systemPropertyDAO = DAOManager.getSystemPropertyDAO();
		SystemPropertyExample smtpExample = new SystemPropertyExample();
		smtpExample.createCriteria().andPropkeyLike("mail.smtp%").andDeletedEqualTo(0);
		List<SystemProperty> list = systemPropertyDAO.selectSystemPropertyByExample(smtpExample);
		Properties properties = new Properties();
		for (SystemProperty sp : list) {
			properties.put(sp.getPropkey(), sp.getPropvalue());
		}
		
		SystemPropertyExample destExample = new SystemPropertyExample();
		destExample.createCriteria().andPropkeyEqualTo(SystemPropertiesKeys.CONTACT_FORM_EMAIL);
		SystemProperty emailDest = systemPropertyDAO.selectSystemPropertyByExample(destExample).get(0);
		
		StringBuffer mailBody = new StringBuffer();
		mailBody.append("Contacto del usuario: ").append(this.getName()).append("<br>");
		mailBody.append("Email: ").append(this.getEmail()).append("<br>");
		mailBody.append("Contenido: ").append(this.getContent()).append("<br>");
		try {
			EmailUtils.sendEmail(mailBody.toString(), emailDest.getPropvalue(), this.getEmail(), "Contacto desde el site", properties);
		} catch (MessagingException e) {
			getLog().error(e.getMessage(), e);
			throw new ValidationException(new ValidationError("ContactForm.GENERAL_ERROR"));
		}
	}	

	private static Logger getLog() {
		return LoggerProvider.getLogger(ContactForm.class);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
