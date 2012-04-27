package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.tdil.djmag.dao.BlobDataDAO;
import com.tdil.djmag.dao.VideoDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.BlobData;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.RankingNoteCountry;
import com.tdil.djmag.model.Video;
import com.tdil.djmag.model.VideoExample;
import com.tdil.djmag.utils.BlobHelper;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class VideoForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String htmlContent;
	private String description;
	private boolean frontcover;
	private boolean popular;
	private UploadData frontCover;
	private FormFile frontCoverFormFile;
	private int countryId;
	
	private List<Video> allVideos;
	private List<CountrySelectionVO> selectedCountries = new ArrayList<CountrySelectionVO>();
	private static List<Country> allCountries = new ArrayList<Country>();
	
	private static String description_key = "Video.description";
	private static String htmlContent_key = "Video.htmlContent";
	private static String country_key = "Video.country";
	private static String front_cover_key = "Video.front_cover";
	
	private static final int MAX_FRONT_COVER_SIZE = 2000000;

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.countryId = 0;
		this.description = null;
		this.htmlContent = null;
		this.frontcover = false;
		this.popular = false;
		this.frontCover = null;
		this.resetSelectedCountries();
	}
	
	public boolean isCountrySelected(int id) {
		return id == this.getCountryId();
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.frontcover = false;
		this.popular = false;
		//clearSelectedCountries();
	}

	private void clearSelectedCountries() {
		for (CountrySelectionVO countrySelectionVO : getSelectedCountries()) {
			countrySelectionVO.setSelected(false);
		}
	}
	
	public boolean getHasFrontCover() {
		return this.getFrontCover() != null;
	}

	public void uploadFrontCover(ValidationError error) {
		FormFile formFile = this.getFrontCoverFormFile();
		UploadData uploadData = FieldValidation.validateFormFile(formFile, front_cover_key, true, error);
		if (uploadData != null) {
			int fileSize = formFile.getFileSize();
			if (fileSize > MAX_FRONT_COVER_SIZE) {
				error.setFieldError(front_cover_key, ValidationErrors.TOO_BIG);
				this.setFrontCover(null);
				return;
			}
			this.setFrontCover(uploadData);
		}
	}
	public void deleteFrontCover() {
		this.setFrontCover(null);			
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		VideoExample example = new VideoExample();
		example.setOrderByClause("description");
		this.setAllVideos(DAOManager.getVideoDAO().selectVideoByExampleWithoutBLOBs(example));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		VideoExample videoExample = new VideoExample();
		videoExample.createCriteria().andIdEqualTo(this.getObjectId());
		Video video = DAOManager.getVideoDAO().selectVideoByExampleWithoutBLOBs(videoExample).get(0);
		video.setDeleted(video.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getVideoDAO().updateVideoByExampleWithoutBLOBs(video, videoExample);
	}

	@Override
	public void init() throws SQLException {
		VideoExample example = new VideoExample();
		example.setOrderByClause("description");
		this.setAllVideos(DAOManager.getVideoDAO().selectVideoByExampleWithoutBLOBs(example));
		
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
		VideoDAO videoDAO = DAOManager.getVideoDAO();
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		Video video = videoDAO.selectVideoByPrimaryKey(id);
		if (video != null) {
			this.objectId = id;
			this.description = video.getDescription();
			this.htmlContent = video.getHtmlcontent();
			this.frontcover = video.getFrontcover() == 1;
			this.popular = video.getPopular() == 1;
			this.countryId = video.getIdCountry();
			if (!StringUtils.isEmpty(video.getFrontcoverext())) {
				BlobData content = blobDataDAO.selectBlobDataByPrimaryKey(video.getFrontcoverId());
				this.setFrontCover(new UploadData(video.getFrontcoverext(), content.getContent(), false));
			} else {
				this.setFrontCover(null);
			}
		} 
		
		// reseteo los paises
		resetSelectedCountries();
	}
	
	public static Country getCountryForVideoId(Integer sectionId) {
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
		FieldValidation.validateText(this.getDescription(), description_key, 250, validationError);
		FieldValidation.validateText(this.getHtmlContent(), htmlContent_key, ValidationErrors.TEXT_LENGTH, validationError);
		FieldValidation.validateId(this.getCountryId(), country_key, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
			
	}

	@Override
	public void save() throws SQLException, ValidationException {
		VideoDAO videoDAO = DAOManager.getVideoDAO();
		if (this.getObjectId() == 0) {
			Video video = new Video();
			updateVideo(video);
			if (this.getFrontCover() != null) {
				int blobId = BlobHelper.insertBlob(this.getFrontCover());
				video.setFrontcoverId(blobId);
				video.setFrontcoverext(this.getFrontCover().getExtension());
			}
			video.setDeleted(0);
			videoDAO.insertVideo(video);
		} else {
			Video video = videoDAO.selectVideoByPrimaryKey(this.getObjectId());
			updateVideo(video);
			if (BlobHelper.shouldDeleteBlob(this.getFrontCover())) {
				BlobHelper.deleteBlob(video.getFrontcoverId());
				video.setFrontcoverId(0);
				video.setFrontcoverext("");
			}
			if (this.getFrontCover() != null) {
				int blobId = BlobHelper.insertBlob(this.getFrontCover());
				video.setFrontcoverId(blobId);
				video.setFrontcoverext(this.getFrontCover().getExtension());
			}
			videoDAO.updateVideoByPrimaryKeySelective(video);
		}
		
	}

	private void updateVideo(Video video) {
		video.setIdCountry(this.getCountryId());
		video.setDescription(this.getDescription());
		video.setHtmlcontent(this.getHtmlContent());
		video.setFrontcover(this.isFrontcover() ? 1 : 0);
		video.setPopular(this.isPopular() ? 1 : 0);
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

	public List<Video> getAllVideos() {
		return allVideos;
	}

	public void setAllVideos(List<Video> allRankings) {
		this.allVideos = allRankings;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFrontcover() {
		return frontcover;
	}

	public void setFrontcover(boolean frontcover) {
		this.frontcover = frontcover;
	}

	public boolean isPopular() {
		return popular;
	}

	public void setPopular(boolean popular) {
		this.popular = popular;
	}

	public UploadData getFrontCover() {
		return frontCover;
	}

	public void setFrontCover(UploadData frontCover) {
		this.frontCover = frontCover;
	}

	public FormFile getFrontCoverFormFile() {
		return frontCoverFormFile;
	}

	public void setFrontCoverFormFile(FormFile frontCoverFormFile) {
		this.frontCoverFormFile = frontCoverFormFile;
	}

}
