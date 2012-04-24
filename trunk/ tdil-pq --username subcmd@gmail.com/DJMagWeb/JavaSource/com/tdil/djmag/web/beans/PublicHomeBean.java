package com.tdil.djmag.web.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.djmag.dao.CountryDAO;
import com.tdil.djmag.dao.SectionDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.Section;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.SaveAction;


public class PublicHomeBean  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7859928560990002269L;
	private Country country;
	private List<Section> sectionsForCountry;
	
	private List<Country> allCountries;
	
	public static final String PUBLIC_HOME_BEAN = "publicHomeBean";
	
	public boolean hasCountrySelected() {
		return this.getCountry() != null;
	}
	
	public void initCountries() {
		if (allCountries == null || allCountries.isEmpty()) {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						CountryDAO countryDAO = DAOManager.getCountryDAO();
						CountryExample countryExample = new CountryExample();
						countryExample.createCriteria().andDeletedEqualTo(0);
						countryExample.setOrderByClause("name");
						setAllCountries(countryDAO.selectCountryByExample(countryExample));
					}
				});
			} catch (Exception e) {
				getLog().error(e.getMessage(), e);
			}
		}
	}
	
	private void initSectionsForCountry(final Country country) {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					SectionDAO sectionDAO = DAOManager.getSectionDAO();
					setSectionsForCountry(sectionDAO.selectActiveSectionsForCountry(country));
				}
			});
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	public List<Country> getAllCountries() {
		return allCountries;
	}
	public void setAllCountries(List<Country> allCountries) {
		this.allCountries = allCountries;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(PublicHomeBean.class);
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public void setCountryById(int idcountry) {
		this.initCountries();
		for (Country c : getAllCountries()) {
			if (c.getId() == idcountry) {
				setCountry(c);
				initSectionsForCountry(c);
				return;
			}
		}
	}

	public List<Section> getSectionsForCountry() {
		return sectionsForCountry;
	}

	public void setSectionsForCountry(List<Section> sectionsForCountry) {
		this.sectionsForCountry = sectionsForCountry;
	}
}
