package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.djmag.dao.CountryDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.CountryExample.Criteria;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.validations.FieldValidation;

public class CountryForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String name;
	private String iso_code_2;
	private boolean deleted;
	
	private List<Country> allCountries;
	
	private static String name_key = "Country.name";
	private static String name_duplicated_key = "DUPLICATED";
	private static String iso_code_2_key = "Country.iso_code_2";
	private static String iso_code_2_duplicated_key = "DUPLICATED";

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.name = null;
		this.iso_code_2 = null;
		this.deleted = false;
	}

	@Override
	public void init() throws SQLException {
		CountryExample countryExample = new CountryExample();
		countryExample.setOrderByClause("name");
		this.setAllCountries(DAOManager.getCountryDAO().selectCountryByExample(countryExample));
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		CountryDAO countryDAO = DAOManager.getCountryDAO();
		Country country = countryDAO.selectCountryByPrimaryKey(id);
		if (country != null) {
			this.objectId = id;
			this.name = country.getName();
			this.iso_code_2 = country.getIsoCode2();
			this.deleted = country.getDeleted() == 1;
		} 
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		CountryExample countryExample = new CountryExample();
		countryExample.setOrderByClause("name");
		this.setAllCountries(DAOManager.getCountryDAO().selectCountryByExample(countryExample));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		Country country = DAOManager.getCountryDAO().selectCountryByPrimaryKey(this.getId());
		country.setDeleted(country.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getCountryDAO().updateCountryByPrimaryKeySelective(country);
	}
	

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getName(), name_key, 250, validationError);
		FieldValidation.validateText(this.getIso_code_2(), iso_code_2_key, 2, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		CountryDAO countryDAO = DAOManager.getCountryDAO();
		{// Validate duplicated name
			CountryExample countryExample = new CountryExample();
			Criteria criteria = countryExample.createCriteria();
			criteria.andNameEqualTo(this.getName());
			List<Country> list = countryDAO.selectCountryByExample(countryExample);
			if (!list.isEmpty()) {
				Country db = list.get(0);
				if (!db.getId().equals(this.getObjectId())) {
					validationError.setFieldError(name_key, name_duplicated_key);
				}
			}
		}
		{// Validate duplicated isocode2
			CountryExample countryExample = new CountryExample();
			Criteria criteria = countryExample.createCriteria();
			criteria.andIsoCode2EqualTo(this.getIso_code_2());
			List<Country> list = countryDAO.selectCountryByExample(countryExample);
			if (!list.isEmpty()) {
				Country db = list.get(0);
				if (!db.getId().equals(this.getObjectId())) {
					validationError.setFieldError(iso_code_2_key, iso_code_2_duplicated_key);
				}
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		CountryDAO countryDAO = DAOManager.getCountryDAO();
		if (this.getObjectId() == 0) {
			Country country = new Country();
			country.setName(this.getName());
			country.setIsoCode2(this.getIso_code_2());
			country.setDeleted(this.isDeleted() ? 1 : 0);
			countryDAO.insertCountry(country);
		} else {
			Country country = new Country();
			country.setId(this.getObjectId());
			country.setName(this.getName());
			country.setIsoCode2(this.getIso_code_2());
			country.setDeleted(this.isDeleted() ? 1 : 0);
			countryDAO.updateCountryByPrimaryKey(country);
		}
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Country> getAllCountries() {
		return allCountries;
	}

	public void setAllCountries(List<Country> allCountries) {
		this.allCountries = allCountries;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getIso_code_2() {
		return iso_code_2;
	}

	public void setIso_code_2(String iso_code_2) {
		this.iso_code_2 = iso_code_2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
