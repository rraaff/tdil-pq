package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Client;
import com.tdil.tuafesta.model.ClientExample;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalExample;
import com.tdil.tuafesta.web.EmailUtils;
import com.tdil.utils.CryptoUtils;
import com.tdil.validations.FieldValidation;

public class PasswordResetForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private String email;
	
	private static final String email_key = "PasswordResetForm.email";
	
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
		FieldValidation.validateEmail(this.getEmail(), email_key, error);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		ClientExample clientExample = new ClientExample();
		clientExample.createCriteria().andEmailEqualTo(this.getEmail());
		int count = DAOManager.getClientDAO().countClientByExample(clientExample);
		if (count > 0) {
			return;
		}
		ProfesionalExample profesionalExample = new ProfesionalExample();
		profesionalExample.createCriteria().andEmailEqualTo(this.getEmail());
		count = DAOManager.getProfesionalDAO().countProfesionalByExample(profesionalExample);
		if (count == 0) {
			validationError.setFieldError(email_key, "EMAIL_NOT_FOUND");
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ClientExample clientExample = new ClientExample();
		clientExample.createCriteria().andEmailEqualTo(this.getEmail());
		List<Client> clients = DAOManager.getClientDAO().selectClientByExample(clientExample);
		if (!clients.isEmpty()) {
			String newPass = RandomStringUtils.randomAlphanumeric(4);
			Client client = clients.get(0);
			client.setPassword(CryptoUtils.getHashedValue(newPass));
			DAOManager.getClientDAO().updateClientByPrimaryKey(client);
			Map<String, String> params = new HashMap<String, String>();
			params.put(EmailUtils.PASSWORD_KEY, newPass);
			EmailUtils.sendEmail(this.getEmail(), params, EmailUtils.CLIENT_PASSWORD_RESET);
		} else {
			ProfesionalExample profesionalExample = new ProfesionalExample();
			profesionalExample.createCriteria().andEmailEqualTo(this.getEmail());
			List<Profesional> profesionals = DAOManager.getProfesionalDAO().selectProfesionalByExample(profesionalExample);
			if (!profesionals.isEmpty()) {
				String newPass = RandomStringUtils.randomAlphanumeric(4);
				Profesional profesional = profesionals.get(0);
				profesional.setPassword(CryptoUtils.getHashedValue(newPass));
				DAOManager.getProfesionalDAO().updateProfesionalByPrimaryKey(profesional);
				Map<String, String> params = new HashMap<String, String>();
				params.put(EmailUtils.PASSWORD_KEY, newPass);
				EmailUtils.sendEmail(this.getEmail(), params, EmailUtils.PROFESIONAL_PASSWORD_RESET);
			}
		}
	}	

	private static Logger getLog() {
		return LoggerProvider.getLogger(PasswordResetForm.class);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
