package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.dao.RankingNoteCountryDAO;
import com.tdil.djmag.dao.RankingNoteDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.RankingNote;
import com.tdil.djmag.model.RankingNoteCountry;
import com.tdil.djmag.model.RankingNoteCountryExample;
import com.tdil.djmag.model.RankingNoteExample;
import com.tdil.djmag.model.Section;
import com.tdil.djmag.model.SectionExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;

public class RankingNoteForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	private String description;
	private int sectionId;
	private String position1;
	private String position2;
	private String position3;
	private String position4;
	private String position5;
	private String position6;
	private String position7;
	private String position8;
	private String position9;
	private String position10;
	private boolean deleted;
	
	private List<RankingNote> allRankings;
	private List<SectionSelectionVO> allSections = new ArrayList<SectionSelectionVO>();
	
	

	private List<CountrySelectionVO> selectedCountries = new ArrayList<CountrySelectionVO>();
	private static List<Country> allCountries = new ArrayList<Country>();
	
	private static List<RankingNoteCountry> allRankingNoteCountries = new ArrayList<RankingNoteCountry>();
	
	private static String description_key = "RankingNote.description";
	private static String position1_key = "RankingNote.position1";

	@Override
	public void reset() throws SQLException {
		this.id = 0;
		this.description = null;
		this.deleted = false;
		this.resetSelectedSections();
		this.resetSelectedCountries();
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.deleted = false;
		clearSelectedCountries();
	}

	private void clearSelectedCountries() {
		for (CountrySelectionVO countrySelectionVO : getSelectedCountries()) {
			countrySelectionVO.setSelected(false);
		}
	}

	@Override
	public void init() throws SQLException {
		RankingNoteExample rankingExample = new RankingNoteExample();
		rankingExample.setOrderByClause("description");
		this.setAllRankings(DAOManager.getRankingNoteDAO().selectRankingNoteByExample(rankingExample));
		
		SectionExample sectionExample = new SectionExample();
		sectionExample.setOrderByClause("name");
		this.setAllSections(wrapSections(DAOManager.getSectionDAO().selectSectionByExample(sectionExample)));
		
		CountryExample countryExample = new CountryExample();
		countryExample.setOrderByClause("name");
		setAllCountries(DAOManager.getCountryDAO().selectCountryByExample(countryExample));
		
		RankingNoteCountryExample rankingNoteCountryExample = new RankingNoteCountryExample();
		setAllRankingNoteCountries(DAOManager.getRankingNoteCountryDAO().selectRankingNoteCountryByExample(rankingNoteCountryExample));
		
		this.resetSelectedSections();
		this.resetSelectedCountries();
	}
	
	private List<SectionSelectionVO> wrapSections(List<Section> selectSectionByExample) {
		List<SectionSelectionVO> result = new ArrayList<SectionSelectionVO>();
		for (Section section : selectSectionByExample) {
			result.add(new SectionSelectionVO(section));
		}
		return result;
	}

	private void resetSelectedCountries() {
		this.getSelectedCountries().clear();
		for (Country country : getAllCountries()) {
			this.getSelectedCountries().add(new CountrySelectionVO(country));
		}
	}
	
	private void resetSelectedSections() {
		for (SectionSelectionVO so : getAllSections()) {
			so.setSelected(false);
		}
		
	}

	@Override
	public void initWith(int id) throws SQLException {
		RankingNoteDAO rankingDAO = DAOManager.getRankingNoteDAO();
		RankingNote ranking = rankingDAO.selectRankingNoteByPrimaryKey(id);
		if (ranking != null) {
			this.id = id;
			this.sectionId = ranking.getIdSection();
			this.description = ranking.getDescription();
			this.deleted = ranking.getDeleted() == 1;
		} 
		// reseto las secciones
		this.resetSelectedSections();
		for (SectionSelectionVO sectionSelectionVO : getAllSections()) {
			if (sectionSelectionVO.getSection().getId().equals(this.getSectionId())) {
				sectionSelectionVO.setSelected(true);
			}
		}
		// reseteo los paises
		resetSelectedCountries();
		// seteto los que habia elegido
		RankingNoteCountryDAO rankingNoteCountryDAO = DAOManager.getRankingNoteCountryDAO();
		RankingNoteCountryExample rankingNoteExample = new RankingNoteCountryExample();
		com.tdil.djmag.model.RankingNoteCountryExample.Criteria criteria = rankingNoteExample.createCriteria();
		criteria.andIdRankingNoteEqualTo(this.getId());
		List<RankingNoteCountry> rankingNoteCountries = rankingNoteCountryDAO.selectRankingNoteCountryByExample(rankingNoteExample);
		for (RankingNoteCountry rankingNoteCountry : rankingNoteCountries) {
			this.setCountrySelected(rankingNoteCountry);
		}
	}
	
	public static List<Country> getAllCountriesForRankingId(Integer sectionId) {
		List<Country> result = new ArrayList<Country>();
		for (RankingNoteCountry mi : getAllRankingNoteCountries()) {
			if (mi.getIdCountry().equals(sectionId)) {
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
	
	private void setCountrySelected(RankingNoteCountry ranking) {
		for (CountrySelectionVO countrySelectionVO : this.getSelectedCountries()) {
			if (countrySelectionVO.getCountryId().equals(ranking.getIdCountry())) {
				if (ranking.getDeleted() == 0) {
					countrySelectionVO.setSelected(true);
				} else {
					countrySelectionVO.setSelected(false);
				}
				countrySelectionVO.setOwnerId(ranking.getId());
			}
		}
		
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		//FieldValidation.validateText(this.getDescri(), name_key, 250, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// validacion por unico, por unico ranking por seccion por pais
	}

	@Override
	public void save() throws SQLException, ValidationException {
		RankingNoteDAO rankingNoteDAO = DAOManager.getRankingNoteDAO();
		RankingNoteCountryDAO rankingNoteCountryDAO = DAOManager.getRankingNoteCountryDAO();
		Integer rankingId;
		if (this.getId() == 0) {
			RankingNote rankingNote = new RankingNote();
			rankingNote.setDescription(this.getDescription());
			rankingNote.setIdSection(this.getSectionId());
			rankingNote.setDeleted(0);
			rankingId = rankingNoteDAO.insertRankingNote(rankingNote);
		} else {
			RankingNote rankingNote = new RankingNote();
			rankingNote.setId(this.getId());
			rankingNote.setDescription(this.getDescription());
			rankingNote.setIdSection(this.getSectionId());
			rankingNoteDAO.updateRankingNoteByPrimaryKeySelective(rankingNote);
			rankingId = this.getId();
		}
		for (CountrySelectionVO countrySelectionVO : getSelectedCountries()) {
			if (this.mustBeSaved(countrySelectionVO)) {
				RankingNoteCountry rankingNoteCountry = new RankingNoteCountry();
				rankingNoteCountry.setId(countrySelectionVO.getOwnerId());
				rankingNoteCountry.setDeleted(countrySelectionVO.isSelected() ? 0 : 1);
				rankingNoteCountry.setIdCountry(countrySelectionVO.getCountryId());
				rankingNoteCountry.setIdRankingNote(rankingId);
				// TODO arreglar
				if(countrySelectionVO.getOwnerId() != null && countrySelectionVO.getOwnerId() != 0) {
					rankingNoteCountryDAO.updateRankingNoteCountryByPrimaryKeySelective(rankingNoteCountry);
				} else {
					rankingNoteCountryDAO.insertRankingNoteCountry(rankingNoteCountry);
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<SectionSelectionVO> getAllSections() {
		return allSections;
	}

	public void setAllSections(List<SectionSelectionVO> allSections) {
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

	// TODO Copia
	public static synchronized List<Country> getAllCountries() {
		return allCountries;
	}

	public static synchronized void setAllCountries(List<Country> newCountries) {
		allCountries = newCountries;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public List<RankingNote> getAllRankings() {
		return allRankings;
	}

	public void setAllRankings(List<RankingNote> allRankings) {
		this.allRankings = allRankings;
	}

	public static List<RankingNoteCountry> getAllRankingNoteCountries() {
		return allRankingNoteCountries;
	}

	public static void setAllRankingNoteCountries(List<RankingNoteCountry> allRankingNoteCountries) {
		RankingNoteForm.allRankingNoteCountries = allRankingNoteCountries;
	}


}
