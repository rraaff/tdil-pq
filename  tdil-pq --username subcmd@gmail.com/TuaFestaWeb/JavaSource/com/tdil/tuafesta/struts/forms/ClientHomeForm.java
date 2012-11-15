package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

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
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo2Example;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo3Example;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Geo4Example;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;
import com.tdil.utils.DateUtils;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class ClientHomeForm extends TransactionalValidationForm implements GeoLevelSelectionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	// para edicion
	private String firstname;
	private String lastname;
	private String sex;
	private String birthdate;
	private int geo2Id;
	private int geo3Id;
	private int geo4Id;
	private List<Geo2> level2;
	private List<Geo3> level3;
	private List<Geo4> level4;
	
	private Client client;
	private GeoLevelValueObject location;
	
	private static final Logger LOG = LoggerProvider.getLogger(ClientHomeForm.class);
	
	@Override
	public void reset() throws SQLException {
		this.firstname = null;
		this.lastname = null;
		this.sex = null;
		this.birthdate = null;
		this.geo2Id = 0;
		this.geo3Id = 0;
		this.geo4Id = 0;

		this.level2 = null;
		this.level3 = null;
		this.level4 = null;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
		
	}

	@Override
	public void initWith(int id) throws SQLException {
		Geo2DAO geo2dao = DAOManager.getGeo2DAO();
		Geo2Example geo2Example = new Geo2Example();
		geo2Example.setOrderByClause("nombre");
		setLevel2(geo2dao.selectGeo2ByExample(geo2Example));
		
		client = DAOManager.getClientDAO().selectClientByPrimaryKey(id);
		this.firstname = client.getFirstname();
		this.lastname = client.getLastname();
		this.sex = client.getSex();
		this.birthdate = DateUtils.formatDateSp(client.getBirthdate());
		if (client.getIdGeolevel() != null && client.getIdGeolevel() != 0) {
			GeoLevelValueObject geoLevelValueObject = DAOManager.getGeo4DAO().selectGeoLevelsByGeo4(client.getIdGeolevel());
			setGeo2Id(geoLevelValueObject.getGeo2id());
			setGeo3Id(geoLevelValueObject.getGeo3id());
			setGeo4Id(geoLevelValueObject.getGeo4id());
			location = DAOManager.getGeo4DAO().selectGeoLevelsByGeo4(client.getIdGeolevel());
		}
	}
	
	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getFirstname(), ClientForm.firstname_key, 100, validationError);
		FieldValidation.validateText(this.getLastname(), ClientForm.lastname_key, 100, validationError);
		FieldValidation.validateText(this.getSex(), ClientForm.sex_key, 1, validationError);

		Date birthDate = com.tdil.utils.DateUtils.parseDateSp(this.getBirthdate());
		if (birthDate == null) {
			validationError.setFieldError(ClientForm.birthdate_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ClientDAO clientDAO = DAOManager.getClientDAO();
		Client client = clientDAO.selectClientByPrimaryKey(this.getClient().getId());
		client.setFirstname(this.getFirstname());
		client.setLastname(this.getLastname());
		client.setIdGeolevel(this.getGeo4Id());
		client.setSex(this.getSex());
		client.setBirthdate(com.tdil.utils.DateUtils.parseDateSp(this.getBirthdate()));
		clientDAO.updateClientByPrimaryKey(client);
	}	

	private static Logger getLog() {
		return LoggerProvider.getLogger(ClientHomeForm.class);
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
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
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
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
						geo3Example.createCriteria().andGeo2IdEqualTo(ClientHomeForm.this.geo2Id);
						geo3Example.setOrderByClause("nombre");
						ClientHomeForm.this.setLevel3(geo3dao.selectGeo3ByExample(geo3Example));
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
						geo4Example.createCriteria().andGeo3IdEqualTo(ClientHomeForm.this.geo3Id);
						geo4Example.setOrderByClause("nombre");
						ClientHomeForm.this.setLevel4(geo4dao.selectGeo4ByExample(geo4Example));
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
	public GeoLevelValueObject getLocation() {
		return location;
	}
	public void setLocation(GeoLevelValueObject location) {
		this.location = location;
	}
	
}
