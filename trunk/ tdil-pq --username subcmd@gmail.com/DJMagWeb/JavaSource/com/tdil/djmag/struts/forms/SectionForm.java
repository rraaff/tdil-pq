package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.djmag.dao.MenuItemDAO;
import com.tdil.djmag.dao.SectionDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.MenuItem;
import com.tdil.djmag.model.MenuItemExample;
import com.tdil.djmag.model.MenuItemExample.Criteria;
import com.tdil.djmag.model.Section;
import com.tdil.djmag.model.SectionExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;

public class SectionForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	private String name;
	private boolean deleted;
	
	private List<Section> allSections;

	private List<CountrySelectionVO> selectedCountries = new ArrayList<CountrySelectionVO>();
	private List<Country> allCountries;

	@Override
	public void reset() throws SQLException {
		this.id = 0;
		this.name = null;
		this.deleted = false;
	}

	@Override
	public void init() throws SQLException {
		SectionExample sectionExample = new SectionExample();
		sectionExample.setOrderByClause("name");
		this.setAllSections(DAOManager.getSectionDAO().selectSectionByExample(sectionExample));
		
		CountryExample countryExample = new CountryExample();
		countryExample.setOrderByClause("name");
		this.setAllCountries(DAOManager.getCountryDAO().selectCountryByExample(countryExample));
		
		this.resetSelectedCountries();
	}
	
	private void resetSelectedCountries() {
		this.getSelectedCountries().clear();
		for (Country country : this.getAllCountries()) {
			this.getSelectedCountries().add(new CountrySelectionVO(country));
		}
		
	}

	@Override
	public void initWith(int id) throws SQLException {
		SectionDAO sectionDAO = DAOManager.getSectionDAO();
		Section section = sectionDAO.selectSectionByPrimaryKey(id);
		if (section != null) {
			this.id = id;
			this.name = section.getName();
			this.deleted = section.getDeleted() == 1;
		} 
		// reseteo los paises
		this.resetSelectedCountries();
		// seteto los que habia elegido
		MenuItemDAO menuItemDAO = DAOManager.getMenuItemDAO();
		MenuItemExample menuItemExample = new MenuItemExample();
		Criteria criteria = menuItemExample.createCriteria();
		criteria.andIdSectionEqualTo(this.getId());
		List<MenuItem> menuItems = menuItemDAO.selectMenuItemByExample(menuItemExample);
		for (MenuItem menuItem : menuItems) {
			this.setCountrySelected(menuItem);
		}
	}

	private void setCountrySelected(MenuItem menuItem) {
		for (CountrySelectionVO countrySelectionVO : this.getSelectedCountries()) {
			if (countrySelectionVO.getId().equals(menuItem.getIdCountry())) {
				if (menuItem.getDeleted() == 0) {
					countrySelectionVO.setSelected(true);
				}
				countrySelectionVO.setOwnerId(menuItem.getId());
			}
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
		SectionDAO sectionDAO = DAOManager.getSectionDAO();
		MenuItemDAO menuItemDAO = DAOManager.getMenuItemDAO();
		Integer sectionId;
		if (this.getId() == 0) {
			Section section = new Section();
			section.setName(this.getName());
			section.setDeleted(this.isDeleted() ? 1 : 0);
			sectionId = sectionDAO.insertSection(section);
		} else {
			Section section = new Section();
			section.setId(this.getId());
			section.setName(this.getName());
			section.setDeleted(this.isDeleted() ? 1 : 0);
			sectionDAO.updateSectionByPrimaryKey(section);
			sectionId = this.getId();
		}
		for (CountrySelectionVO countrySelectionVO : getSelectedCountries()) {
			if (this.mustBeSaved(countrySelectionVO)) {
				MenuItem menuItem = new MenuItem();
				menuItem.setId(countrySelectionVO.getOwnerId());
				menuItem.setDeleted(countrySelectionVO.isSelected() ? 0 : 1);
				menuItem.setIdSection(sectionId);
				menuItem.setIdCountry(countrySelectionVO.getId());
				// TODO arreglar
				menuItem.setName(this.getName());
				menuItem.setPosition(0);
				if(countrySelectionVO.getOwnerId() != null && countrySelectionVO.getOwnerId() != 0) {
					menuItemDAO.updateMenuItemByPrimaryKey(menuItem);
				} else {
					menuItemDAO.insertMenuItem(menuItem);
				}
			}
		}
	}

	private boolean mustBeSaved(CountrySelectionVO countrySelectionVO) {
		if(countrySelectionVO.getOwnerId() != null && countrySelectionVO.getOwnerId() != 0) {
			return true;
		}
		return countrySelectionVO.isSelected();
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<Section> getAllSections() {
		return allSections;
	}

	public void setAllSections(List<Section> allSections) {
		this.allSections = allSections;
	}

	public List<CountrySelectionVO> getSelectedCountries() {
		return selectedCountries;
	}

	public void setSelectedCountries(List<CountrySelectionVO> selectedCountries) {
		this.selectedCountries = selectedCountries;
	}
	
	public CountrySelectionVO getSelectedCountry(int index) {  
	      return (CountrySelectionVO)this.selectedCountries.get(index);  
	   }  
	   
	public void setSelectedCountry(int index, CountrySelectionVO countryVO) {  
		this.selectedCountries.set(index, countryVO);  
	 }  

	public List<Country> getAllCountries() {
		return allCountries;
	}

	public void setAllCountries(List<Country> allCountries) {
		this.allCountries = allCountries;
	}

}
