package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.dao.RankingNoteCountryDAO;
import com.tdil.djmag.dao.RankingNoteDAO;
import com.tdil.djmag.dao.RankingPositionDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.RankingNote;
import com.tdil.djmag.model.RankingNoteCountry;
import com.tdil.djmag.model.RankingNoteCountryExample;
import com.tdil.djmag.model.RankingNoteExample;
import com.tdil.djmag.model.RankingPosition;
import com.tdil.djmag.model.RankingPositionExample;
import com.tdil.djmag.model.Video;
import com.tdil.djmag.model.VideoExample;
import com.tdil.djmag.web.beans.PublicHomeBean;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class RankingNoteForm extends TransactionalValidationForm implements ToggleDeletedFlagForm, AjaxUploadHandlerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String description;
	private List<RankingPosition> positions;
	
	private List<RankingNote> allRankings;
	private List<CountrySelectionVO> selectedCountries = new ArrayList<CountrySelectionVO>();
	private static List<Country> allCountries = new ArrayList<Country>();
	
	private static List<RankingNoteCountry> allRankingNoteCountries = new ArrayList<RankingNoteCountry>();
	
	private static final String description_key = "RankingNote.description";
	private static final String country_key = "RankingNote.country";
	private static final String position_key = "RankingNote.position";
	private static final String ranking_photo_key = "RankingNote.ranking_photo";
	private static final int POSITIONS = 100; 
	private static final int MAX_PHOTO_SIZE = 1000000;

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.description = null;
		this.positions = new ArrayList<RankingPosition>();
		this.resetSelectedCountries();
	}
	
	public void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError error,
			Map<String, Object> result) {
//		FileItem uploaded = fileItems.get(AjaxFileUploadAction.UPLOAD_NAME);
//		String index = uploaded.getFieldName();
//		index = index.substring(index.indexOf('_') + 1);
//		RankingPositionBean rankingPositionBean = this.getPositions().get(Integer.valueOf(index));
//		UploadData uploadData = FieldValidation.validateFileItem(uploaded, ranking_photo_key, true, error);
//		if (uploadData != null) {
//			long fileSize = uploaded.getSize();
//			if (fileSize > MAX_PHOTO_SIZE) {
//				error.setFieldError(ranking_photo_key, ValidationErrors.TOO_BIG);
//				rankingPositionBean.setUploadData(null);
//				return;
//			}
//			rankingPositionBean.setUploadData(uploadData);
//		}
//		result.put("result", "OK");
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
		RankingPositionDAO rankingPositionDAO = DAOManager.getRankingPositionDAO();
		RankingNote ranking = rankingDAO.selectRankingNoteByPrimaryKey(id);
		if (ranking != null) {
			this.objectId = id;
			this.description = ranking.getDescription();
			RankingPositionExample rankingPositionExample = new RankingPositionExample();
			rankingPositionExample.createCriteria().andIdRankingNoteEqualTo(id);
			rankingPositionExample.setOrderByClause("orderNumber");
			this.setPositions(rankingPositionDAO.selectRankingPositionByExampleWithBLOBs(rankingPositionExample));
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
		/*RankingNoteTextDAO rankingTextDAO = DAOManager.getRankingNoteTextDAO();
		RankingNoteTextExample rankingNoteTextExample = new RankingNoteTextExample();
		rankingNoteTextExample.createCriteria().andIdRankingNoteEqualTo(id);
		List<RankingNoteText> texts = rankingTextDAO.selectRankingNoteTextByExampleWithBLOBs(rankingNoteTextExample);
		for (RankingNoteText rankingNoteText : texts) {
			setPositionText(rankingNoteText);
		}*/
	}
	
	public static List<Country> getAllCountriesForRankingId(Integer sectionId) {
		List<Country> result = new ArrayList<Country>();
		for (RankingNoteCountry mi : getAllRankingNoteCountries()) {
			if (mi.getIdRankingNote().equals(sectionId)) {
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
				if (!duplicated.isEmpty() && !duplicated.get(0).getId().equals(countrySelectionVO.getOwnerId())) {
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
			rankingNote.setDescription(this.getDescription());
			rankingId = rankingNoteDAO.insertRankingNote(rankingNote);
			createEmptyPositions(rankingId);
		} else {
			RankingNote rankingNote = new RankingNote();
			rankingNote.setId(this.getObjectId());
			rankingNote.setDescription(this.getDescription());
			rankingNoteDAO.updateRankingNoteByPrimaryKeySelective(rankingNote);
			rankingId = this.getObjectId();
		}
		for (CountrySelectionVO countrySelectionVO : getSelectedCountries()) {
			if (this.mustBeSaved(countrySelectionVO)) {
				RankingNoteCountry rankingNoteCountry = new RankingNoteCountry();
				rankingNoteCountry.setId(countrySelectionVO.getOwnerId());
				rankingNoteCountry.setIdCountry(countrySelectionVO.getCountryId());
				rankingNoteCountry.setIdRankingNote(rankingId);
				rankingNoteCountry.setDeleted(0);
				if (countrySelectionVO.isSelected()) {
					if(countrySelectionVO.getOwnerId() != null && countrySelectionVO.getOwnerId() != 0) {
						rankingNoteCountryDAO.updateRankingNoteCountryByPrimaryKeySelective(rankingNoteCountry);
					} else {
						rankingNoteCountryDAO.insertRankingNoteCountry(rankingNoteCountry);
					}					
				} else {
					rankingNoteCountryDAO.deleteRankingNoteCountryByPrimaryKey(rankingNoteCountry.getId());
				}
			}
		}
	}
	
	private void createEmptyPositions(int id) throws SQLException {
		RankingPositionDAO rankingPositionDAO = DAOManager.getRankingPositionDAO();
		for (int i = 0; i < POSITIONS; i++) {
			RankingPosition rankingPosition = new RankingPosition();
			rankingPosition.setIdRankingNote(id);
			rankingPosition.setOrdernumber(i);
			rankingPosition.setDeleted(0);
			rankingPositionDAO.insertRankingPosition(rankingPosition);
		}
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

	public void movePositionUp(final int index) {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					if (index > 0) {
						RankingPositionDAO dao = DAOManager.getRankingPositionDAO();
						RankingPosition prev = RankingNoteForm.this.getPositions().get(index - 1);
						RankingPosition act = RankingNoteForm.this.getPositions().get(index);
						prev = dao.selectRankingPositionByPrimaryKey(prev.getId());
						act = dao.selectRankingPositionByPrimaryKey(act.getId());
						int prevOrder = prev.getOrdernumber();
						prev.setOrdernumber(act.getOrdernumber());
						act.setOrdernumber(prevOrder);
						RankingNoteForm.this.getPositions().set(index - 1, act);
						RankingNoteForm.this.getPositions().set(index, prev);
						dao.updateRankingPositionByPrimaryKeyWithoutBLOBs(prev);
						dao.updateRankingPositionByPrimaryKeyWithoutBLOBs(act);
					}
				}
			});
			this.initWith(this.getObjectId());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	public void movePositionDown(final int index) {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					if (index < RankingNoteForm.this.getPositions().size() - 1) {
						RankingPositionDAO dao = DAOManager.getRankingPositionDAO();
						RankingPosition next = RankingNoteForm.this.getPositions().get(index + 1);
						RankingPosition act = RankingNoteForm.this.getPositions().get(index);
						next = dao.selectRankingPositionByPrimaryKey(next.getId());
						act = dao.selectRankingPositionByPrimaryKey(act.getId());
						int nextOrder = next.getOrdernumber();
						next.setOrdernumber(act.getOrdernumber());
						act.setOrdernumber(nextOrder);
						RankingNoteForm.this.getPositions().set(index + 1, act);
						RankingNoteForm.this.getPositions().set(index, next);
						dao.updateRankingPositionByPrimaryKeyWithoutBLOBs(next);
						dao.updateRankingPositionByPrimaryKeyWithoutBLOBs(act);
					}
				}
			});
			this.initWith(this.getObjectId());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(RankingNoteForm.class);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setPositions(List<RankingPosition> positions) {
		this.positions = positions;
	}

	public List<RankingPosition> getPositions() {
		return positions;
	}


}
