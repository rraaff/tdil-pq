package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.dao.BannerPositionDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Banner;
import com.tdil.djmag.model.BannerExample;
import com.tdil.djmag.model.BannerInsertPoints;
import com.tdil.djmag.model.BannerPosition;
import com.tdil.djmag.model.BannerPositionExample;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.RankingNoteCountry;
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
		this.countryId = 0;
		this.bannerId = 0;
		this.insertPoint = null;
		this.resetSelectedInsertPoints();
		this.resetSelectedBanners();
		this.resetSelectedCountries();
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
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
	
	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateId(this.getCountryId(), countryid_key, validationError);
		FieldValidation.validateId(this.getBannerId(), bannerid_key, validationError);
		FieldValidation.validateText(this.getInsertPoint(), insertpoint_key, 100, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		BannerPositionExample bannerPositionExample = new BannerPositionExample();
		bannerPositionExample.createCriteria().andIdCountryEqualTo(this.getCountryId()).andPositionEqualTo(this.getInsertPoint());
		List<BannerPosition> result = DAOManager.getBannerPositionDAO().selectBannerPositionByExample(bannerPositionExample);
		if (!result.isEmpty()) {
			BannerPosition found = result.get(0);
			if (found.getId()!= this.getObjectId()) {
				validationError.setFieldError(countryid_key, ValidationErrors.DUPLICATED);
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		BannerPositionDAO bannerPositionDAO = DAOManager.getBannerPositionDAO();
		if (this.getObjectId() == 0) {
			BannerPosition bannerPosition = new BannerPosition();
			updateBannerPosition(bannerPosition);
			bannerPosition.setDeleted(0);
			bannerPositionDAO.insertBannerPosition(bannerPosition);
		} else {
			BannerPosition bannerPosition = new BannerPosition();
			bannerPosition.setId(this.getObjectId());
			updateBannerPosition(bannerPosition);
			bannerPositionDAO.updateBannerPositionByPrimaryKeySelective(bannerPosition);
		}
		
	}

	private void updateBannerPosition(BannerPosition position) {
		position.setIdCountry(this.getCountryId());
		position.setIdBanner(this.getBannerId());
		position.setPosition(this.getInsertPoint());
	}
	
	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
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
		result.add(BannerInsertPoints.HOME_RIGHT);
		result.add(BannerInsertPoints.HOME_RIGHT_AGENDA);
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
		for (BannerInsertPointSelectionVO bvo : selectedPoints) {
			if (bvo.getInsertPoint().equals(this.getInsertPoint())) {
				bvo.setSelected(true);
			}
		}
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

	public List<BannerSelectionVO> getSelectedBanners() {
		for (BannerSelectionVO bsvo : selectedBanners) {
			if (bsvo.getBannerId() == this.getBannerId()) {
				bsvo.setSelected(true);
			}
		}
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
