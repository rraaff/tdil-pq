package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Client;
import com.tdil.tuafesta.model.ClientExample;
import com.tdil.tuafesta.model.ClientStatus;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalExample;
import com.tdil.tuafesta.model.ProfesionalStatus;
import com.tdil.tuafesta.roles.ClientRole;
import com.tdil.tuafesta.roles.ProfesionalRole;
import com.tdil.users.User;
import com.tdil.utils.CryptoUtils;

public class WebsiteLoginForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		email = null;
		password = null;
	}
	
	public Object executeLogin() throws SQLException, ValidationException {
		if (StringUtils.isEmpty(this.getEmail())) {
			throw new ValidationException(new ValidationError("WebsiteLoginForm.GENERAL_ERROR"));
		}
		if (StringUtils.isEmpty(this.getPassword())) {
			throw new ValidationException(new ValidationError("WebsiteLoginForm.GENERAL_ERROR"));
		}
		ClientExample clientExample = new ClientExample();
		clientExample.createCriteria().andEmailEqualTo(this.getEmail());
		List<Client> clients = DAOManager.getClientDAO().selectClientByExample(clientExample);
		if (!clients.isEmpty()) {
			Client client = clients.get(0);
			if (!client.getPassword().equals(CryptoUtils.getHashedValue(this.getPassword()))) {
				throw new ValidationException(new ValidationError("WebsiteLoginForm.GENERAL_ERROR"));
			}
			// Si esta el email pendiente de validacion, informo, debera pedir otro email
			if (client.getStatus().equals(ClientStatus.EMAIL_VALIDATION_PENDING)) {
				throw new ValidationException(new ValidationError("WebsiteLoginForm.EMAIL_VALIDATION_PENDING"));
			}
			// levanto un error, que se comnunique por el formulario de contacto
			if (client.getStatus().equals(ClientStatus.BLOCKED)) {
				throw new ValidationException(new ValidationError("WebsiteLoginForm.BLOCKED"));
			}
			// Esta todo ok, aca lo logueo
			com.tdil.tuafesta.web.WebsiteUser user = new com.tdil.tuafesta.web.WebsiteUser(client);
			user.setId(client.getId());
			Set<String> roles = new HashSet<String>();
			roles.add(ClientRole.INSTANCE.getName());
			user.setRoles(roles);
			return user;
		} else {
			// si no lo encontre como cliente lo busco como profesional
			ProfesionalExample profesionalExample = new ProfesionalExample();
			profesionalExample.createCriteria().andEmailEqualTo(this.getEmail());
			List<Profesional> profesionals = DAOManager.getProfesionalDAO().selectProfesionalByExample(profesionalExample);
			if (profesionals.isEmpty()) {
				throw new ValidationException(new ValidationError("WebsiteLoginForm.GENERAL_ERROR"));
			}
			Profesional profesional = profesionals.get(0);
			if (!profesional.getPassword().equals(CryptoUtils.getHashedValue(this.getPassword()))) {
				throw new ValidationException(new ValidationError("WebsiteLoginForm.GENERAL_ERROR"));
			}
			// Si esta el email pendiente de validacion, informo, debera pedir otro email
			if (profesional.getStatus().equals(ProfesionalStatus.EMAIL_VALIDATION_PENDING)) {
				throw new ValidationException(new ValidationError("WebsiteLoginForm.EMAIL_VALIDATION_PENDING"));
			}
			// levanto un error, que se comnunique por el formulario de contacto
			if (profesional.getStatus().equals(ProfesionalStatus.BLOCKED)) {
				throw new ValidationException(new ValidationError("WebsiteLoginForm.BLOCKED"));
			}
			// Esta todo ok, aca lo logueo
			com.tdil.tuafesta.web.WebsiteUser user = new com.tdil.tuafesta.web.WebsiteUser(profesional);
			user.setId(profesional.getId());
			Set<String> roles = new HashSet<String>();
			roles.add(ProfesionalRole.INSTANCE.getName());
			user.setRoles(roles);
			return user;
		}
		
	}
	
}
