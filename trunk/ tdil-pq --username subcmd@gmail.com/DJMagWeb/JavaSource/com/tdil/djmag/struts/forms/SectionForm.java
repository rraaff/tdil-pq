package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.dao.MenuItemDAO;
import com.tdil.djmag.dao.SectionDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.MenuItem;
import com.tdil.djmag.model.MenuItemExample;
import com.tdil.djmag.model.SectionType;
import com.tdil.djmag.model.MenuItemExample.Criteria;
import com.tdil.djmag.model.Section;
import com.tdil.djmag.model.SectionExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.validations.FieldValidation;

public class SectionForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String name;
	
	private List<Section> allSections;

	private List<MenuItemSelectionVO> selectedCountries = new ArrayList<MenuItemSelectionVO>();
	
	private static List<Country> allCountries = new ArrayList<Country>();
	private static List<MenuItem> allMenuItems = new ArrayList<MenuItem>();
	
	private static String name_key = "Section.name";
	private static String name_duplicated_key = "DUPLICATED";

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.name = null;
		this.resetSelectedCountries();
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		clearSelectedCountries();
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		SectionExample sectionExample = new SectionExample();
		sectionExample.setOrderByClause("name");
		this.setAllSections(DAOManager.getSectionDAO().selectSectionByExample(sectionExample));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		Section section = DAOManager.getSectionDAO().selectSectionByPrimaryKey(this.getObjectId());
		section.setDeleted(section.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getSectionDAO().updateSectionByPrimaryKeySelective(section);
	}
	
	private void clearSelectedCountries() {
		for (MenuItemSelectionVO countrySelectionVO : getSelectedCountries()) {
			countrySelectionVO.setSelected(false);
		}
	}

	@Override
	public void init() throws SQLException {
		SectionExample sectionExample = new SectionExample();
		sectionExample.setOrderByClause("name");
		this.setAllSections(DAOManager.getSectionDAO().selectSectionByExample(sectionExample));
		
		CountryExample countryExample = new CountryExample();
		countryExample.setOrderByClause("name");
		setAllCountries(DAOManager.getCountryDAO().selectCountryByExample(countryExample));
		
		MenuItemExample menuItemExample = new MenuItemExample();
		setAllMenuItems(DAOManager.getMenuItemDAO().selectMenuItemByExample(menuItemExample));
		
		this.resetSelectedCountries();
	}
	
	private void resetSelectedCountries() {
		this.getSelectedCountries().clear();
		for (Country country : getAllCountries()) {
			this.getSelectedCountries().add(new MenuItemSelectionVO(country));
		}
		
	}

	@Override
	public void initWith(int id) throws SQLException {
		SectionDAO sectionDAO = DAOManager.getSectionDAO();
		Section section = sectionDAO.selectSectionByPrimaryKey(id);
		if (section != null) {
			this.objectId = id;
			this.name = section.getName();
		} 
		// reseteo los paises
		this.resetSelectedCountries();
		// seteto los que habia elegido
		MenuItemDAO menuItemDAO = DAOManager.getMenuItemDAO();
		MenuItemExample menuItemExample = new MenuItemExample();
		Criteria criteria = menuItemExample.createCriteria();
		criteria.andIdSectionEqualTo(this.getObjectId());
		List<MenuItem> menuItems = menuItemDAO.selectMenuItemByExample(menuItemExample);
		for (MenuItem menuItem : menuItems) {
			this.setCountrySelected(menuItem);
		}
	}
	
	public static List<Country> getAllCountriesForSectionId(Integer sectionId) {
		List<Country> result = new ArrayList<Country>();
		for (MenuItem mi : getAllMenuItems()) {
			if (mi.getIdSection().equals(sectionId) && mi.getDeleted() == 0) {
				result.add(getCountryWithId(mi.getIdCountry()));
			}
		}
		Collections.sort(result, new Comparator<Country>() {
			public int compare(Country arg0, Country arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
		return result;
	}

	private static Country getCountryWithId(Integer idCountry) {
		for (Country c : getAllCountries()) {
			if (c.getId().equals(idCountry)) {
				return c;
			}
		}
		return null;
	}

	private void setCountrySelected(MenuItem menuItem) {
		for (MenuItemSelectionVO countrySelectionVO : this.getSelectedCountries()) {
			if (countrySelectionVO.getCountryId().equals(menuItem.getIdCountry())) {
				if (menuItem.getDeleted() == 0) {
					countrySelectionVO.setSelected(true);
				}
				countrySelectionVO.setOwnerId(menuItem.getId());
				countrySelectionVO.setSectionName(menuItem.getName());
			}
		}
		
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getName(), name_key, 250, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		SectionDAO sectionDAO = DAOManager.getSectionDAO();
		SectionExample sectionExample = new SectionExample();
		com.tdil.djmag.model.SectionExample.Criteria criteria = sectionExample.createCriteria();
		criteria.andNameEqualTo(this.getName());
		List<Section> list = sectionDAO.selectSectionByExample(sectionExample);
		if (!list.isEmpty()) {
			Section db = list.get(0);
			if (!db.getId().equals(this.getObjectId())) {
				validationError.setFieldError(name_key, name_duplicated_key);
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		SectionDAO sectionDAO = DAOManager.getSectionDAO();
		MenuItemDAO menuItemDAO = DAOManager.getMenuItemDAO();
		Integer sectionId;
		if (this.getObjectId() == 0) {
			Section section = new Section();
			section.setName(this.getName());
			section.setSectiontype(SectionType.NORMAL);
			section.setDeleted(0);
			sectionId = sectionDAO.insertSection(section);
		} else {
			Section section = new Section();
			section.setId(this.getObjectId());
			section.setName(this.getName());
			sectionDAO.updateSectionByPrimaryKeySelective(section);
			sectionId = this.getObjectId();
		}
		for (MenuItemSelectionVO countrySelectionVO : getSelectedCountries()) {
			if (this.mustBeSaved(countrySelectionVO)) {
				MenuItem menuItem = new MenuItem();
				menuItem.setId(countrySelectionVO.getOwnerId());
				menuItem.setDeleted(countrySelectionVO.isSelected() ? 0 : 1);
				menuItem.setIdSection(sectionId);
				menuItem.setIdCountry(countrySelectionVO.getCountryId());
				menuItem.setName(StringUtils.isEmpty(countrySelectionVO.getSectionName()) ? this.getName() : countrySelectionVO.getSectionName());
				menuItem.setPosition(null);
				// TODO arreglar
				if(countrySelectionVO.getOwnerId() != null && countrySelectionVO.getOwnerId() != 0) {
					menuItemDAO.updateMenuItemByPrimaryKeySelective(menuItem);
				} else {
					menuItem.setPosition(0);
					menuItemDAO.insertMenuItem(menuItem);
				}
			}
		}
	}

	private boolean mustBeSaved(MenuItemSelectionVO countrySelectionVO) {
		if(countrySelectionVO.getOwnerId() != null && countrySelectionVO.getOwnerId() != 0) {
			return true;
		}
		return countrySelectionVO.isSelected();
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

	public List<Section> getAllSections() {
		return allSections;
	}

	public void setAllSections(List<Section> allSections) {
		this.allSections = allSections;
	}

	public List<MenuItemSelectionVO> getSelectedCountries() {
		return selectedCountries;
	}

	public void setSelectedCountries(List<MenuItemSelectionVO> selectedCountries) {
		this.selectedCountries = selectedCountries;
	}
	
	public MenuItemSelectionVO getSelectedCountry(int index) {  
	      return (MenuItemSelectionVO)this.selectedCountries.get(index);  
	   }  
	   
	public void setSelectedCountry(int index, MenuItemSelectionVO countryVO) {  
		this.selectedCountries.set(index, countryVO);  
	 }  

	// TODO Copia
	public static synchronized List<Country> getAllCountries() {
		return allCountries;
	}

	public static synchronized void setAllCountries(List<Country> newCountries) {
		allCountries = newCountries;
	}

	// TODO Copia
	public static synchronized List<MenuItem> getAllMenuItems() {
		return allMenuItems;
	}

	public static synchronized void setAllMenuItems(List<MenuItem> newItems) {
		allMenuItems = newItems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
