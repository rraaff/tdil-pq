package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.dao.BannerPositionDAO;
import com.tdil.djmag.dao.VideoDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Banner;
import com.tdil.djmag.model.BannerExample;
import com.tdil.djmag.model.BannerInsertPoints;
import com.tdil.djmag.model.BannerPosition;
import com.tdil.djmag.model.BannerPositionExample;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.RankingNoteCountry;
import com.tdil.djmag.model.Video;
import com.tdil.djmag.model.VideoExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class BannerPositionForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private int bannerId;
	private int countryId;
	private String insertPoint;
	private boolean deleted;
	
	private List<BannerPosition> allBannerPositions;
	
	
	private List<BannerInsertPointSelectionVO> selectedPoints = new ArrayList<BannerInsertPointSelectionVO>();
	// Seleccion del pais
	private List<CountrySelectionVO> selectedCountries = new ArrayList<CountrySelectionVO>();
	private static List<Country> allCountries = new ArrayList<Country>();
	// Seleccion del banner
	private List<BannerSelectionVO> selectedBanners = new ArrayList<BannerSelectionVO>();
	private static List<Banner> allBanners = new ArrayList<Banner>();
	
	private static String bannerid_key = "BannerPosition.banner";
	private static String countryid_key = "BannerPosition.country";
	private static String insertpoint_key = "BannerPosition.insertPoint";

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.deleted = false;
		this.resetSelectedInsertPoints();
		this.resetSelectedBanners();
		this.resetSelectedCountries();
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.deleted = false;
		clearSelectedInsertPoints();
		clearSelectedBanners();
		clearSelectedCountries();
	}

	private void clearSelectedInsertPoints() {
		for (BannerInsertPointSelectionVO countrySelectionVO : getSelectedPoints()) {
			countrySelectionVO.setSelected(false);
		}
	}
	private void clearSelectedBanners() {
		for (BannerSelectionVO countrySelectionVO : getSelectedBanners()) {
			countrySelectionVO.setSelected(false);
		}
	}
	private void clearSelectedCountries() {
		for (CountrySelectionVO countrySelectionVO : getSelectedCountries()) {
			countrySelectionVO.setSelected(false);
		}
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		BannerPositionExample example = new BannerPositionExample();
		this.setAllBannerPositions(DAOManager.getBannerPositionDAO().selectBannerPositionByExample(example));
	}
	
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		BannerPositionExample bannerPositionExample = new BannerPositionExample();
		bannerPositionExample.createCriteria().andIdEqualTo(this.getObjectId());
		BannerPosition banner = DAOManager.getBannerPositionDAO().selectBannerPositionByExample(bannerPositionExample).get(0);
		banner.setDeleted(banner.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getBannerPositionDAO().updateBannerPositionByExample(banner, bannerPositionExample);
	}

	@Override
	public void init() throws SQLException {
		BannerPositionExample example = new BannerPositionExample();
		this.setAllBannerPositions(DAOManager.getBannerPositionDAO().selectBannerPositionByExample(example));
		
		CountryExample countryExample = new CountryExample();
		countryExample.setOrderByClause("name");
		setAllCountries(DAOManager.getCountryDAO().selectCountryByExample(countryExample));
		
		BannerExample bannerExample = new BannerExample();
		bannerExample.setOrderByClause("description");
		setAllBanners(DAOManager.getBannerDAO().selectBannerByExampleWithoutBLOBs(bannerExample));
		
		this.resetSelectedInsertPoints();
		this.resetSelectedBanners();
		this.resetSelectedCountries();
	}
	
	private void resetSelectedInsertPoints() {
		this.getSelectedPoints().clear();
		for (String point : getAllInsertPoints()) {
			this.getSelectedPoints().add(new BannerInsertPointSelectionVO(point));
		}
	}
	private void resetSelectedBanners() {
		this.getSelectedBanners().clear();
		for (Banner banner : getAllBanners()) {
			this.getSelectedBanners().add(new BannerSelectionVO(banner));
		}
	}
	private void resetSelectedCountries() {
		this.getSelectedCountries().clear();
		for (Country country : getAllCountries()) {
			this.getSelectedCountries().add(new CountrySelectionVO(country));
		}
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		BannerPositionDAO videoDAO = DAOManager.getBannerPositionDAO();
		BannerPosition bannerPosition = videoDAO.selectBannerPositionByPrimaryKey(id);
		if (bannerPosition != null) {
			this.objectId = id;
			this.setBannerId(bannerPosition.getIdBanner());
			this.countryId = bannerPosition.getIdCountry();
			this.setInsertPoint(bannerPosition.getPosition());
			this.deleted = bannerPosition.getDeleted() == 1;
		} 
		// reseteo los paises
		resetSelectedInsertPoints();
		resetSelectedBanners();
		resetSelectedInsertPoints();
	}
	
	public static Banner getBannerWithId(Integer id) {
		for (Banner c : getAllBanners()) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}
	
	public static Country getCountryWithId(Integer id) {
		for (Country c : getAllCountries()) {
			if (c.getId().equals(id)) {
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
		/*FieldValidation.validateText(this.getDescription(), description_key, 250, validationError);
		FieldValidation.validateText(this.getHtmlContent(), htmlContent_key, ValidationErrors.TEXT_LENGTH, validationError);
		FieldValidation.validateId(this.getCountryId(), country_key, validationError);*/
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
			
	}

	@Override
	public void save() throws SQLException, ValidationException {
		BannerPositionDAO bannerPositionDAO = DAOManager.getBannerPositionDAO();
		if (this.getObjectId() == 0) {
			BannerPosition bannerPosition = new BannerPosition();
			updateBannerPosition(bannerPosition);
			bannerPositionDAO.insertBannerPosition(bannerPosition);
		} else {
			BannerPosition bannerPosition = new BannerPosition();
			updateBannerPosition(bannerPosition);
			bannerPositionDAO.updateBannerPositionByPrimaryKeySelective(bannerPosition);
		}
		
	}

	private void updateBannerPosition(BannerPosition position) {
		position.setIdCountry(this.getCountryId());
		position.setIdBanner(this.getBannerId());
		position.setPosition(this.getInsertPoint());
		position.setDeleted(this.isDeleted() ? 1 : 0);
	}
	
	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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

	public static synchronized List<String> getAllInsertPoints() {
		List<String> result = new ArrayList<String>();
		result.add(BannerInsertPoints.HOME_TOP);
		result.add(BannerInsertPoints.NOTE_TOP);
		result.add(BannerInsertPoints.NOTE_RIGHT);
		return result;
	}

	public static synchronized void setAllCountries(List<Country> newCountries) {
		allCountries = newCountries;
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

	public List<BannerInsertPointSelectionVO> getSelectedPoints() {
		return selectedPoints;
	}

	public void setSelectedPoints(List<BannerInsertPointSelectionVO> selectedPoints) {
		this.selectedPoints = selectedPoints;
	}

	public static List<Banner> getAllBanners() {
		return allBanners;
	}

	public static void setAllBanners(List<Banner> allBanners) {
		BannerPositionForm.allBanners = allBanners;
	}

	public static List<Country> getAllCountries() {
		return allCountries;
	}

	public List<CountrySelectionVO> getSelectedCountries() {
		return selectedCountries;
	}

	public void setSelectedCountries(List<CountrySelectionVO> selectedCountries) {
		this.selectedCountries = selectedCountries;
	}

	public List<BannerSelectionVO> getSelectedBanners() {
		return selectedBanners;
	}

	public void setSelectedBanners(List<BannerSelectionVO> selectedBanners) {
		this.selectedBanners = selectedBanners;
	}

	public List<BannerPosition> getAllBannerPositions() {
		return allBannerPositions;
	}

	public void setAllBannerPositions(List<BannerPosition> allBannerPositions) {
		this.allBannerPositions = allBannerPositions;
	}

	public void setBannerId(int bannerId) {
		this.bannerId = bannerId;
	}

	public int getBannerId() {
		return bannerId;
	}

	public void setInsertPoint(String insertPoint) {
		this.insertPoint = insertPoint;
	}

	public String getInsertPoint() {
		return insertPoint;
	}
}
