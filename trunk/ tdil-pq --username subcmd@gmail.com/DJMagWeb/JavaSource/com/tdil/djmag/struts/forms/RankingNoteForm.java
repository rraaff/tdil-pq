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
import com.tdil.djmag.model.RankingPositions;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.utils.XMLUtils;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class RankingNoteForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String description;
	private List<RankingPositionBean> positions;
	
	private List<RankingNote> allRankings;
	private List<CountrySelectionVO> selectedCountries = new ArrayList<CountrySelectionVO>();
	private static List<Country> allCountries = new ArrayList<Country>();
	
	private static List<RankingNoteCountry> allRankingNoteCountries = new ArrayList<RankingNoteCountry>();
	
	private static final String description_key = "RankingNote.description";
	private static final String country_key = "RankingNote.country";
	private static final String position_key = "RankingNote.position";
	private static final int POSITIONS = 100; 

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.description = null;
		this.positions = createEmptyPositions();
		this.resetSelectedCountries();
	}
	
	private List<RankingPositionBean> createEmptyPositions() {
		List<RankingPositionBean> result = new ArrayList<RankingPositionBean>();
		for(int i = 0; i < POSITIONS; i++) {
			result.add(new RankingPositionBean(""));
		}
		return result;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		clearSelectedCountries();
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		RankingNoteExample rankingExample = new RankingNoteExample();
		rankingExample.setOrderByClause("description");
		this.setAllRankings(DAOManager.getRankingNoteDAO().selectRankingNoteByExampleWithoutBLOBs(rankingExample));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		RankingNoteExample example = new RankingNoteExample();
		example.createCriteria().andIdEqualTo(this.getObjectId());
		RankingNote note = DAOManager.getRankingNoteDAO().selectRankingNoteByExampleWithoutBLOBs(example).get(0);
		note.setDeleted(note.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getRankingNoteDAO().updateRankingNoteByExampleWithoutBLOBs(note, example);
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
		this.setAllRankings(DAOManager.getRankingNoteDAO().selectRankingNoteByExampleWithoutBLOBs(rankingExample));
		
		CountryExample countryExample = new CountryExample();
		countryExample.setOrderByClause("name");
		setAllCountries(DAOManager.getCountryDAO().selectCountryByExample(countryExample));
		
		RankingNoteCountryExample rankingNoteCountryExample = new RankingNoteCountryExample();
		setAllRankingNoteCountries(DAOManager.getRankingNoteCountryDAO().selectRankingNoteCountryByExample(rankingNoteCountryExample));
		
		this.resetSelectedCountries();
	}
	
	private void resetSelectedCountries() {
		this.getSelectedCountries().clear();
		for (Country country : getAllCountries()) {
			this.getSelectedCountries().add(new CountrySelectionVO(country));
		}
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		RankingNoteDAO rankingDAO = DAOManager.getRankingNoteDAO();
		RankingNote ranking = rankingDAO.selectRankingNoteByPrimaryKey(id);
		if (ranking != null) {
			this.objectId = id;
			this.description = ranking.getDescription();
			this.setRankingPositions((RankingPositions)XMLUtils.fromXML(ranking.getPositions()));
		} 
		// reseteo los paises
		resetSelectedCountries();
		// seteto los que habia elegido
		RankingNoteCountryDAO rankingNoteCountryDAO = DAOManager.getRankingNoteCountryDAO();
		RankingNoteCountryExample rankingNoteExample = new RankingNoteCountryExample();
		com.tdil.djmag.model.RankingNoteCountryExample.Criteria criteria = rankingNoteExample.createCriteria();
		criteria.andIdRankingNoteEqualTo(this.getObjectId());
		List<RankingNoteCountry> rankingNoteCountries = rankingNoteCountryDAO.selectRankingNoteCountryByExample(rankingNoteExample);
		for (RankingNoteCountry rankingNoteCountry : rankingNoteCountries) {
			this.setCountrySelected(rankingNoteCountry);
		}
	}
	
	private void setRankingPositions(RankingPositions fromXML) {
		this.getPositions().clear();
		for (String st : fromXML.getPositions()) {
			this.getPositions().add(new RankingPositionBean(st));
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
		FieldValidation.validateText(this.getDescription(), description_key, 250, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// validacion por unico, por unico ranking por seccion por pais
		for (CountrySelectionVO countrySelectionVO : this.getSelectedCountries()) {
			if (countrySelectionVO.isSelected()) {
				RankingNoteCountryExample rankingNoteCountryExample = new RankingNoteCountryExample();
				rankingNoteCountryExample.createCriteria().andIdCountryEqualTo(countrySelectionVO.getCountryId());
				List<RankingNoteCountry> duplicated = DAOManager.getRankingNoteCountryDAO().selectRankingNoteCountryByExample(rankingNoteCountryExample);
				if (!duplicated.isEmpty() && !duplicated.get(0).getId().equals(this.getId())) {
					validationError.setFieldError(country_key, ValidationErrors.DUPLICATED);
				}
			}
		}
		
	}

	@Override
	public void save() throws SQLException, ValidationException {
		RankingNoteDAO rankingNoteDAO = DAOManager.getRankingNoteDAO();
		RankingNoteCountryDAO rankingNoteCountryDAO = DAOManager.getRankingNoteCountryDAO();
		Integer rankingId;
		if (this.getObjectId() == 0) {
			RankingNote rankingNote = new RankingNote();
			rankingNote.setDeleted(0);
			updateRankingNote(rankingNote);
			rankingId = rankingNoteDAO.insertRankingNote(rankingNote);
		} else {
			RankingNote rankingNote = new RankingNote();
			rankingNote.setId(this.getObjectId());
			updateRankingNote(rankingNote);
			rankingNoteDAO.updateRankingNoteByPrimaryKeySelective(rankingNote);
			rankingId = this.getObjectId();
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

	private void updateRankingNote(RankingNote rankingNote) {
		rankingNote.setDescription(this.getDescription());
		RankingPositions rankingPositions = new RankingPositions();
		for (RankingPositionBean rankingPositionBean : this.getPositions()) {
			rankingPositions.getPositions().add(rankingPositionBean.getPosition());
		}
		rankingNote.setPositions(XMLUtils.asXML(rankingPositions));
	}
	
	private boolean mustBeSaved(CountrySelectionVO countrySelectionVO) {
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

	public List<CountrySelectionVO> getSelectedCountries() {
		return selectedCountries;
	}

	public void setSelectedCountries(List<CountrySelectionVO> selectedCountries) {
		this.selectedCountries = selectedCountries;
	}
	
	public CountrySelectionVO getSelectedCountry(int index) {
		if (this.selectedCountries.size() > index) {
			return (CountrySelectionVO)this.selectedCountries.get(index); 
		} else {
			return null;
		}
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

	public List<RankingPositionBean> getPositions() {
		return positions;
	}

	public void setPositions(List<RankingPositionBean> positions) {
		this.positions = positions;
	}
	
	public RankingPositionBean getSelectedPosition(int index) {  
	      return (RankingPositionBean)this.getPositions().get(index);  
	   }  
	   
	public void setSelectedPosition(int index, RankingPositionBean countryVO) {  
		this.getPositions().set(index, countryVO);  
	 }

	public void movePositionUp(int index) {
		if (index > 0) {
			RankingPositionBean prev = this.getPositions().get(index - 1);
			RankingPositionBean act = this.getPositions().get(index);
			this.getPositions().set(index - 1, act);
			this.getPositions().set(index, prev);
		}
	}
	
	public void movePositionDown(int index) {
		if (index < this.getPositions().size() - 1) {
			RankingPositionBean next = this.getPositions().get(index + 1);
			RankingPositionBean act = this.getPositions().get(index);
			this.getPositions().set(index + 1, act);
			this.getPositions().set(index, next);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
