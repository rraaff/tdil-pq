package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSON;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.dao.SystemPropertyDAO;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.SystemProperty;
import com.tdil.lojack.model.SystemPropertyExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.TokenHolder;
import com.tdil.thalamus.client.facade.json.fields.PersonFieldNames;
import com.tdil.utils.EmailUtils;
import com.tdil.validations.FieldValidation;

public class ContactForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private boolean isRegisteredUser;
	private String firstname;
	private String lastname;
	private String documentNumber;
	private String email;
	private String phone;
	private String content;
	
	public static final String firstname_key = "ContactForm.firstname";
	public static final String lastname_key = "ContactForm.lastname";
	public static final String document_key = "ContactForm.document";
	public static final String email_key = "ContactForm.email";
	public static final String phone_key = "ContactForm.phone";
	public static final String content_key = "ContactForm.content";
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(ContactForm.class);
	
	@Override
	public void reset() throws SQLException {
		isRegisteredUser = false;
		firstname = "";
		lastname = "";
		documentNumber = "";
		email = "";
		phone = "";
		content = "";
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
		FieldValidation.validateText(this.getFirstname(), firstname_key, 150, error);
		FieldValidation.validateText(this.getLastname(), lastname_key, 150, error);
		FieldValidation.validateNumber(this.getDocumentNumber(), document_key, 1, Integer.MAX_VALUE, error);
		FieldValidation.validateEmail(this.getEmail(), email_key, error);
		FieldValidation.validateText(this.getPhone(), phone_key, 20, error);
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
		destExample.createCriteria().andPropkeyEqualTo("contactform.email");
		SystemProperty emailDest = systemPropertyDAO.selectSystemPropertyByExample(destExample).get(0);
		
		StringBuffer mailBody = new StringBuffer();
		mailBody.append("Contacto del usuario, datos del usuario<br> ");
		mailBody.append("Usuario registrado: ").append(this.isRegisteredUser() ? "si" : "no").append("<br>");
		mailBody.append("Nombre: ").append(this.getFirstname()).append("<br>");
		mailBody.append("Apellido: ").append(this.getLastname()).append("<br>");
		mailBody.append("Dni: ").append(this.getDocumentNumber()).append("<br>");
		mailBody.append("Email: ").append(this.getEmail()).append("<br>");
		mailBody.append("Telefono: ").append(this.getPhone()).append("<br>");
		mailBody.append("Contenido: ").append(this.getContent()).append("<br>");
		try {
			EmailUtils.sendEmail(mailBody.toString(), emailDest.getPropvalue(), emailDest.getPropvalue(), "Contacto desde el site", properties);
		} catch (MessagingException e) {
			getLog().error(e.getMessage(), e);
			throw new ValidationException(new ValidationError("ContactForm.GENERAL_ERROR"));
		}
	}	

	private static Logger getLog() {
		return LoggerProvider.getLogger(ContactForm.class);
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean getRegisteredUser() {
		return isRegisteredUser;
	}
	
	public boolean isRegisteredUser() {
		return isRegisteredUser;
	}
	public void setRegisteredUser(boolean isRegisteredUser) {
		this.isRegisteredUser = isRegisteredUser;
	}
	public void initWith(TokenHolder tokenHolder) {
		try {
			JSON gral = ThalamusClientFacade.getPerson(tokenHolder);
			this.setRegisteredUser(true);
			JSONObject person = ((JSONObject)gral).getJSONObject("person");
			JSONObject profile = person.getJSONObject("profile");
			if (profile.containsKey("document") && profile.get("document") != JSONNull.getInstance()) {
				JSONObject document = profile.getJSONObject("document");
				this.setDocumentNumber(document.getString("number"));
			}
			
			if (profile.containsKey(PersonFieldNames.firstName) && profile.get(PersonFieldNames.firstName) != JSONNull.getInstance()) {
				this.setFirstname(profile.getString(PersonFieldNames.firstName));
			}
			if (profile.containsKey(PersonFieldNames.lastName) && profile.get(PersonFieldNames.lastName) != JSONNull.getInstance()) {
				this.setLastname(profile.getString(PersonFieldNames.lastName));
			}
			if (profile.containsKey(PersonFieldNames.email) && profile.get(PersonFieldNames.email) != JSONNull.getInstance()) {
				this.setEmail(profile.getString(PersonFieldNames.email));
			}
			String phoneSt = "";
			if (profile.containsKey(PersonFieldNames.phone)) {
				JSONObject phone = profile.getJSONObject(PersonFieldNames.phone);
				if (phone.containsKey(PersonFieldNames.phoneIntCode) && phone.get(PersonFieldNames.phoneIntCode) != JSONNull.getInstance()) {
					phoneSt = phoneSt + phone.getString(PersonFieldNames.phoneIntCode) + " ";
				}
				if (phone.containsKey(PersonFieldNames.phoneAreaCode) && phone.get(PersonFieldNames.phoneAreaCode) != JSONNull.getInstance()) {
					phoneSt = phoneSt + "(" + phone.getString(PersonFieldNames.phoneAreaCode) + ") ";
				}
				if (phone.containsKey(PersonFieldNames.phoneNumber) && phone.get(PersonFieldNames.phoneNumber) != JSONNull.getInstance()) {
					phoneSt = phoneSt + phone.getString(PersonFieldNames.phoneNumber);
				}
				if (phone.containsKey(PersonFieldNames.phoneType) && phone.get(PersonFieldNames.phoneType) != JSONNull.getInstance()) {
					phoneSt = phoneSt + " - " + phone.getString(PersonFieldNames.phoneType);
				}
			}
		} catch (HttpStatusException e) {
			LOG.error(e.getMessage(), e);
		} catch (InvalidResponseException e) {
			LOG.error(e.getMessage(), e);
		} catch (CommunicationException e) {
			LOG.error(e.getMessage(), e);
		} catch (UnauthorizedException e) {
			LOG.error(e.getMessage(), e);
		}
		
	}

}
