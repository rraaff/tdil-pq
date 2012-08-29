package com.tdil.tuafesta.struts.forms;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ClientDAO;
import com.tdil.tuafesta.dao.Geo2DAO;
import com.tdil.tuafesta.dao.Geo3DAO;
import com.tdil.tuafesta.dao.Geo4DAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Client;
import com.tdil.tuafesta.model.ClientExample;
import com.tdil.tuafesta.model.ClientStatus;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo2Example;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo3Example;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Geo4Example;
import com.tdil.tuafesta.model.ProfesionalExample;
import com.tdil.tuafesta.utils.DateUtils;
import com.tdil.tuafesta.web.EmailUtils;
import com.tdil.utils.CryptoUtils;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class ClientForm extends TransactionalValidationForm implements GeoLevelSelectionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(ClientForm.class);

	private int id;

	private int objectId;
	private String firstname;
	private String lastname;
	private String sex;
	private String birthdate;
	
	private String email;
	private String password;
	private String retypepassword;
	
	private int geo2Id;
	private int geo3Id;
	private int geo4Id;
	
	private List<Geo2> level2;
	private List<Geo3> level3;
	private List<Geo4> level4;
	
	
	private static final int MIN_PASS_LENGTH = 4;
	public static final String firstname_key = "ClientForm.firstname";
	public static final String lastname_key = "ClientForm.lastname";
	public static final String sex_key = "ClientForm.sex";
	public static final String birthdate_key = "ClientForm.birthdate";
	
	public static final String email_key = "ClientForm.email";
	public static final String password_key = "ClientForm.password";
	
	private static final Logger LOG = LoggerProvider.getLogger(ClientForm.class);

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.firstname = null;
		this.lastname = null;
		this.sex = null;
		this.birthdate = null;
		this.email = null;
		this.password = null;
		this.retypepassword = null;
		this.geo2Id = 0;
		this.geo3Id = 0;
		this.geo4Id = 0;
		
		this.level2 = null;
		this.level3 = null;
		this.level4 = null;
		
	}

	@Override
	public void init() throws SQLException {
		Geo2DAO geo2dao = DAOManager.getGeo2DAO();
		Geo2Example geo2Example = new Geo2Example();
		geo2Example.setOrderByClause("nombre");
		setLevel2(geo2dao.selectGeo2ByExample(geo2Example));
	}

	@Override
	public void initWith(int id) throws SQLException {

	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getFirstname(), firstname_key, 100, validationError);
		FieldValidation.validateText(this.getLastname(), lastname_key, 100, validationError);
		FieldValidation.validateText(this.getSex(), sex_key, 1, validationError);

		// TODO if por facebook
		FieldValidation.validateText(this.getEmail(), email_key, 150, validationError);
		FieldValidation.validateText(this.getPassword(), password_key, 20, validationError);
		
		Date birthDate = DateUtils.parseDate(this.getBirthdate());
		if (birthDate == null) {
			validationError.setFieldError(birthdate_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
		if (!validationError.hasFieldError(password_key)) {
			if (this.getPassword().length() < MIN_PASS_LENGTH) {
				validationError.setFieldError(password_key, "PASSWORD_TOO_SHORT");
			} else {
				if (!this.getPassword().equals(this.getRetypepassword())) {
					validationError.setFieldError(password_key, "RETYPE_NOT_EQUAL");
				}
			}
		}
		
	}

	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		ClientExample clientExample = new ClientExample();
		clientExample.createCriteria().andEmailEqualTo(this.getEmail());
		int count = DAOManager.getClientDAO().countClientByExample(clientExample);
		if (count > 0) {
			validationError.setFieldError(email_key, ValidationErrors.DUPLICATED);
		} else {
			ProfesionalExample profesionalExample = new ProfesionalExample();
			profesionalExample.createCriteria().andEmailEqualTo(this.getEmail());
			count = DAOManager.getProfesionalDAO().countProfesionalByExample(profesionalExample);
			if (count > 0) {
				validationError.setFieldError(email_key, ValidationErrors.DUPLICATED);
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ClientDAO clientDAO = DAOManager.getClientDAO();
		
		Client client = new Client();
		client.setFirstname(this.getFirstname());
		client.setLastname(this.getLastname());
		client.setIdGeolevel(this.getGeo4Id());
		client.setSex(this.getSex());
		client.setBirthdate(DateUtils.parseDate(this.getBirthdate()));
		
		client.setEmail(this.getEmail());
		client.setVerifemail(RandomStringUtils.randomAlphanumeric(20));
		
		// TODO if por facebook
		client.setPassword(CryptoUtils.getHashedValue(this.getPassword()));
		client.setEmailvalid(0);
		//
		
		client.setStatus(ClientStatus.EMAIL_VALIDATION_PENDING);
		client.setDeleted(0);
		int id = clientDAO.insertClient(client);
		
		StringBuffer link = new StringBuffer();
		link.append("/validateClientEmail.do?id=").append(id).append("&verifemail=").append(client.getVerifemail());
		
		/** Inicio del email */
		Map<String, String> params = new HashMap<String, String>();
		params.put(EmailUtils.LINK_KEY, link.toString());
		EmailUtils.sendEmail(this.getEmail(), params, EmailUtils.CLIENT_EMAIL_VERIFICATION);
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		if (email != null) {
			email = email.toLowerCase();
		}
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

	public String getRetypepassword() {
		return retypepassword;
	}

	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}

	public int getGeo2Id() {
		return geo2Id;
	}

	public void setGeo2Id(int geo2Id) {
		this.geo2Id = geo2Id;
	}

	public int getGeo3Id() {
		return geo3Id;
	}

	public void setGeo3Id(int geo3Id) {
		this.geo3Id = geo3Id;
	}

	public int getGeo4Id() {
		return geo4Id;
	}

	public void setGeo4Id(int geo4Id) {
		this.geo4Id = geo4Id;
	}

	public List<Geo2> getLevel2() {
		return level2;
	}

	public void setLevel2(List<Geo2> level2) {
		this.level2 = level2;
	}

	public List<Geo3> getLevel3() {
		if (geo2Id != 0) {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						Geo3DAO geo3dao = DAOManager.getGeo3DAO();
						Geo3Example geo3Example = new Geo3Example();
						geo3Example.createCriteria().andGeo2IdEqualTo(ClientForm.this.geo2Id);
						geo3Example.setOrderByClause("nombre");
						ClientForm.this.setLevel3(geo3dao.selectGeo3ByExample(geo3Example));
					}
				});
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		} else {
			setLevel3(new ArrayList<Geo3>());
		}
		return level3;
	}

	public void setLevel3(List<Geo3> level3) {
		this.level3 = level3;
	}

	public List<Geo4> getLevel4() {
		if (geo3Id != 0) {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						Geo4DAO geo4dao = DAOManager.getGeo4DAO();
						Geo4Example geo4Example = new Geo4Example();
						geo4Example.createCriteria().andGeo3IdEqualTo(ClientForm.this.geo3Id);
						geo4Example.setOrderByClause("nombre");
						ClientForm.this.setLevel4(geo4dao.selectGeo4ByExample(geo4Example));
					}
				});
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		} else {
			setLevel4(new ArrayList<Geo4>());
		}
		return level4;
	}

	public void setLevel4(List<Geo4> level4) {
		this.level4 = level4;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

}
