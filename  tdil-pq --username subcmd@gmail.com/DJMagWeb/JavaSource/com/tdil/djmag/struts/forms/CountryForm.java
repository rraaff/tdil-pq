package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.djmag.dao.CountryDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;

public class CountryForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	private String name;
	private boolean deleted;
	
	private List<Country> allCountries;

	@Override
	public void reset() throws SQLException {
		this.id = 0;
		this.name = null;
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
			this.id = id;
			this.name = country.getName();
			this.deleted = country.getDeleted() == 1;
		} 
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void save() throws SQLException, ValidationException {
		CountryDAO countryDAO = DAOManager.getCountryDAO();
		if (this.getId() == 0) {
			Country country = new Country();
			country.setName(this.getName());
			country.setDeleted(this.isDeleted() ? 1 : 0);
			countryDAO.insertCountry(country);
		} else {
			Country country = new Country();
			country.setId(this.getId());
			country.setName(this.getName());
			country.setDeleted(this.isDeleted() ? 1 : 0);
			countryDAO.updateCountryByPrimaryKey(country);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
