package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.dao.TwitterFeedDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.RankingNoteCountry;
import com.tdil.djmag.model.TwitterFeed;
import com.tdil.djmag.model.TwitterFeedExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class TwitterFeedForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String htmlContent;
	private int countryId;
	
	private List<TwitterFeed> allTwitterFeeds;
	private List<CountrySelectionVO> selectedCountries = new ArrayList<CountrySelectionVO>();
	private static List<Country> allCountries = new ArrayList<Country>();
	
	private static String htmlContent_key = "TwitterFeed.htmlContent";
	private static String country_key = "TwitterFeed.country";

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.countryId = 0;
		this.htmlContent = null;
		this.resetSelectedCountries();
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		clearSelectedCountries();
	}

	private void clearSelectedCountries() {
		for (CountrySelectionVO countrySelectionVO : getSelectedCountries()) {
			countrySelectionVO.setSelected(false);
		}
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		TwitterFeedExample example = new TwitterFeedExample();
		this.setAllTwitterFeeds(DAOManager.getTwitterFeedDAO().selectTwitterFeedByExampleWithoutBLOBs(example));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		TwitterFeedExample twitterFeedExample = new TwitterFeedExample();
		twitterFeedExample.createCriteria().andIdEqualTo(this.getObjectId());
		TwitterFeed twitterFeed = DAOManager.getTwitterFeedDAO().selectTwitterFeedByExampleWithoutBLOBs(twitterFeedExample).get(0);
		twitterFeed.setDeleted(twitterFeed.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getTwitterFeedDAO().updateTwitterFeedByExampleWithoutBLOBs(twitterFeed, twitterFeedExample);
	}

	@Override
	public void init() throws SQLException {
		TwitterFeedExample example = new TwitterFeedExample();
		this.setAllTwitterFeeds(DAOManager.getTwitterFeedDAO().selectTwitterFeedByExampleWithoutBLOBs(example));
		
		CountryExample countryExample = new CountryExample();
		countryExample.setOrderByClause("name");
		setAllCountries(DAOManager.getCountryDAO().selectCountryByExample(countryExample));
		
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
		TwitterFeedDAO twitterFeedDAO = DAOManager.getTwitterFeedDAO();
		TwitterFeed twitterFeed = twitterFeedDAO.selectTwitterFeedByPrimaryKey(id);
		if (twitterFeed != null) {
			this.objectId = id;
			this.htmlContent = twitterFeed.getHtmlcontent();
		} 
		// reseteo los paises
		resetSelectedCountries();
	}
	
	public static Country getCountryForTwitterFeedId(Integer sectionId) {
		for (Country c : getAllCountries()) {
			if (c.getId().equals(sectionId)) {
				return c;
			}
		}
		return null;
	}
	
	public static Country getCountryWithId(Integer idCountry) {
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
		FieldValidation.validateText(this.getHtmlContent(), htmlContent_key, ValidationErrors.TEXT_LENGTH, validationError);
		FieldValidation.validateId(this.getCountryId(), country_key, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		TwitterFeedDAO twitterFeedDAO = DAOManager.getTwitterFeedDAO();
		{// Validate duplicated name
			TwitterFeedExample example = new TwitterFeedExample();
			example.createCriteria().andIdCountryEqualTo(this.getCountryId());
			List<TwitterFeed> list = twitterFeedDAO.selectTwitterFeedByExampleWithoutBLOBs(example);
			if (!list.isEmpty()) {
				TwitterFeed db = list.get(0);
				if (!db.getId().equals(this.getObjectId())) {
					validationError.setFieldError(country_key, ValidationErrors.DUPLICATED);
				}
			}
		}
		
	}

	@Override
	public void save() throws SQLException, ValidationException {
		TwitterFeedDAO twitterFeedDAO = DAOManager.getTwitterFeedDAO();
		if (this.getObjectId() == 0) {
			TwitterFeed twitterFeed = new TwitterFeed();
			twitterFeed.setIdCountry(this.getCountryId());
			twitterFeed.setHtmlcontent(this.getHtmlContent());
			twitterFeed.setDeleted(0);
			twitterFeedDAO.insertTwitterFeed(twitterFeed);
		} else {
			TwitterFeed twitterFeed = new TwitterFeed();
			twitterFeed.setId(this.getObjectId());
			twitterFeed.setIdCountry(this.getCountryId());
			twitterFeed.setHtmlcontent(this.getHtmlContent());
			twitterFeedDAO.updateTwitterFeedByPrimaryKeySelective(twitterFeed);
		}
		
	}
	
	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}

	public List<CountrySelectionVO> getSelectedCountries() {
		for (CountrySelectionVO csvo : selectedCountries) {
			if (csvo.getCountryId() == this.getCountryId()) {
				csvo.setSelected(true);
			}
		}
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

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public List<TwitterFeed> getAllTwitterFeeds() {
		return allTwitterFeeds;
	}

	public void setAllTwitterFeeds(List<TwitterFeed> allRankings) {
		this.allTwitterFeeds = allRankings;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

}
