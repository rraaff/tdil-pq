package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.dao.SystemUserDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.SystemUser;
import com.tdil.djmag.model.SystemUserExample;
import com.tdil.djmag.model.SystemUserExample.Criteria;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.utils.CryptoUtils;

public class LoginForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
		username = null;
		password = null;
	}
	public Object executeLogin() throws SQLException, ValidationException {
		SystemUserDAO systemUserDAO = DAOManager.getSystemUserDAO();
		SystemUserExample systemUserExample = new SystemUserExample();
		Criteria criteria = systemUserExample.createCriteria();
		criteria.andUsernameEqualTo(this.getUsername());
		List<SystemUser> list = systemUserDAO.selectSystemUserByExample(systemUserExample);
		if (list.isEmpty()) {
			throw new ValidationException(new ValidationError("LoginForm.GENERAL_ERROR"));
		}
		SystemUser exists = list.get(0);
		if (!exists.getPassword().equals(CryptoUtils.getHashedValue(this.getPassword()))) {
			throw new ValidationException(new ValidationError("LoginForm.GENERAL_ERROR"));
		}
		return exists;
	}
	
}
