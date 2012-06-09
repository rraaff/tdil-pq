package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.dao.BlobDataDAO;
import com.tdil.djmag.dao.ImageGalleryDAO;
import com.tdil.djmag.dao.ImageInGalleryDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.BlobData;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.ImageGallery;
import com.tdil.djmag.model.ImageGalleryExample;
import com.tdil.djmag.model.ImageInGallery;
import com.tdil.djmag.model.ImageInGalleryExample;
import com.tdil.djmag.utils.BlobHelper;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxFileUploadAction;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class ImageGalleryForm extends TransactionalValidationForm implements ToggleDeletedFlagForm, AjaxUploadHandlerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private int countryId;
	private String title;
	private String description;
	
	private int coverPosition;
	
	private List<RankingPositionBean> positions = new ArrayList<RankingPositionBean>();
	
	private List<ImageGallery> allImageGalleries;
	private List<CountrySelectionVO> selectedCountries = new ArrayList<CountrySelectionVO>();
	private static List<Country> allCountries = new ArrayList<Country>();
	
	
	private static final String title_key = "ImageGallery.title";
	private static final String description_key = "ImageGallery.description";
	private static final String country_key = "ImageGallery.country";
	private static final String imageGallery_photo_key = "ImageGallery.photo";
	private static final int MAX_PHOTO_SIZE = 1000000;

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.title = null;
		this.description = null;
		this.positions = new ArrayList<RankingPositionBean>();
		this.resetSelectedCountries();
	}
	
	public int getMaxImages() {
		return this.getPositions().size();
	}
	
	public void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError error,
			Map<String, Object> result) {
		FileItem uploaded = fileItems.get(AjaxFileUploadAction.UPLOAD_NAME);
		RankingPositionBean imageGalleryPositionBean = new RankingPositionBean();
		UploadData uploadData = FieldValidation.validateFileItem(uploaded, imageGallery_photo_key, true, error);
		if (uploadData != null) {
			long fileSize = uploaded.getSize();
			if (fileSize > MAX_PHOTO_SIZE) {
				error.setFieldError(imageGallery_photo_key, ValidationErrors.TOO_BIG);
				return;
			}
			imageGalleryPositionBean.setUploadData(uploadData);
			this.getPositions().add(imageGalleryPositionBean);
		}
		result.put("result", "OK");
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		clearSelectedCountries();
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		ImageGalleryExample imageGalleryExample = new ImageGalleryExample();
		imageGalleryExample.setOrderByClause("title");
		this.setAllImageGalleries(DAOManager.getImageGalleryDAO().selectImageGalleryByExample(imageGalleryExample));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		ImageGalleryExample example = new ImageGalleryExample();
		example.createCriteria().andIdEqualTo(this.getObjectId());
		ImageGallery note = DAOManager.getImageGalleryDAO().selectImageGalleryByExample(example).get(0);
		note.setDeleted(note.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getImageGalleryDAO().updateImageGalleryByExample(note, example);
	}

	private void clearSelectedCountries() {
		for (CountrySelectionVO countrySelectionVO : getSelectedCountries()) {
			countrySelectionVO.setSelected(false);
		}
	}

	@Override
	public void init() throws SQLException {
		ImageGalleryExample imageGalleryExample = new ImageGalleryExample();
		imageGalleryExample.setOrderByClause("title");
		this.setAllImageGalleries(DAOManager.getImageGalleryDAO().selectImageGalleryByExample(imageGalleryExample));
		
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
		ImageGalleryDAO imageGalleryDAO = DAOManager.getImageGalleryDAO();
		ImageInGalleryDAO imageInGalleryDAO = DAOManager.getImageInGalleryDAO();
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		ImageGallery imageGallery = imageGalleryDAO.selectImageGalleryByPrimaryKey(id);
		if (imageGallery != null) {
			this.objectId = id;
			this.title = imageGallery.getTitle();
			this.description = imageGallery.getDescription();
//			this.setRankingPositions((RankingPositions)XMLUtils.fromXML(imageGallery.getPositions()));
		} 
		// reseteo los paises
		resetSelectedCountries();
		// seteto los que habia elegido
		this.setCountrySelected(imageGallery);
		this.getPositions().clear();
		ImageInGalleryExample imageInGalleryExample = new ImageInGalleryExample();
		imageInGalleryExample.createCriteria().andIdGalleryEqualTo(id);
		imageInGalleryExample.setOrderByClause("orderNumber");
		List<ImageInGallery> images = imageInGalleryDAO.selectImageInGalleryByExample(imageInGalleryExample);
		for (ImageInGallery image : images) {
			if (image.getImageId().equals(imageGallery.getImageId())) {
				setCoverPosition(image.getOrdernumber());
			}
			this.getPositions().add(createPositionForInGallery(image));
		}
		
	}
	
	private RankingPositionBean createPositionForInGallery(ImageInGallery image) throws SQLException {
		BlobData blobData = DAOManager.getBlobDataDAO().selectBlobDataByPrimaryKey(image.getImageId());
		RankingPositionBean result = new RankingPositionBean();
		result.setUploadData(new UploadData(blobData.getFilename(), blobData.getContent(), false));
		return result;
	}

	public static Country getCountryWithId(Integer idCountry) {
		for (Country c : getAllCountries()) {
			if (c.getId().equals(idCountry)) {
				return c;
			}
		}
		return null;
	}
	
	private void setCountrySelected(ImageGallery imageGallery) {
		for (CountrySelectionVO countrySelectionVO : this.getSelectedCountries()) {
			if (countrySelectionVO.getCountryId().equals(imageGallery.getIdCountry())) {
				if (imageGallery.getDeleted() == 0) {
					countrySelectionVO.setSelected(true);
				} else {
					countrySelectionVO.setSelected(false);
				}
				countrySelectionVO.setOwnerId(imageGallery.getId());
			}
		}
		
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getTitle(), title_key, 250, validationError);
		FieldValidation.validateText(this.getDescription(), description_key, 4000, validationError);
		if (this.getPositions().isEmpty()) {
			validationError.setFieldError(imageGallery_photo_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
		FieldValidation.validateId(this.getCountryId(), country_key, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// validacion por unico, por unico imageGallery por seccion por pais
		/*for (CountrySelectionVO countrySelectionVO : this.getSelectedCountries()) {
			if (countrySelectionVO.isSelected()) {
				ImageGalleryExample imageGalleryNoteCountryExample = new ImageGalleryExample();
				imageGalleryNoteCountryExample.createCriteria().andIdCountryEqualTo(countrySelectionVO.getCountryId());
				List<ImageGallery> duplicated = DAOManager.getImageGalleryDAO().selectImageGalleryByExample(imageGalleryNoteCountryExample);
				if (!duplicated.isEmpty() && !duplicated.get(0).getId().equals(countrySelectionVO.getOwnerId())) {
					validationError.setFieldError(country_key, ValidationErrors.DUPLICATED);
				}
			}
		}*/
		
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ImageGalleryDAO imageGalleryNoteDAO = DAOManager.getImageGalleryDAO();
		Integer imageGalleryId;
		if (this.getObjectId() == 0) {
			ImageGallery imageGalleryNote = new ImageGallery();
			imageGalleryNote.setDeleted(0);
			imageGalleryNote.setTitle(this.getTitle());
			imageGalleryNote.setDescription(this.getDescription());
			imageGalleryNote.setIdCountry(this.getCountryId());
			imageGalleryId = imageGalleryNoteDAO.insertImageGallery(imageGalleryNote);
			imageGalleryNote.setId(imageGalleryId);
			updateImageGallery(imageGalleryNote, imageGalleryId);
		} else {
			ImageGallery imageGalleryNote = new ImageGallery();
			imageGalleryNote.setId(this.getObjectId());
			imageGalleryNote.setDeleted(0);
			imageGalleryNote.setTitle(this.getTitle());
			imageGalleryNote.setDescription(this.getDescription());
			imageGalleryNote.setIdCountry(this.getCountryId());
			imageGalleryNoteDAO.updateImageGalleryByPrimaryKeySelective(imageGalleryNote);
			imageGalleryId = this.getObjectId();
			updateImageGallery(imageGalleryNote, imageGalleryId);
		}
		
	}

	private void updateImageGallery(ImageGallery imageGalleryNote, int galId) throws SQLException {
		ImageInGalleryDAO imageInGalleryDAO = DAOManager.getImageInGalleryDAO();
		int index = 0;
		ImageInGalleryExample imageInGalleryExample = new ImageInGalleryExample();
		imageInGalleryExample.createCriteria().andIdGalleryEqualTo(galId);
		List<ImageInGallery> toDelete = imageInGalleryDAO.selectImageInGalleryByExample(imageInGalleryExample);
		for (ImageInGallery img : toDelete) {
			BlobHelper.deleteBlob(img.getImageId());
		}
		imageInGalleryDAO.deleteImageInGalleryByExample(imageInGalleryExample);
		for (RankingPositionBean imageGalleryPositionBean : this.getPositions()) {
			int blobId = BlobHelper.insertBlob(imageGalleryPositionBean.getUploadData());
			imageGalleryPositionBean.setBlobId(blobId);
			ImageInGallery imageInGallery = new ImageInGallery();
			imageInGallery.setIdGallery(galId);
			imageInGallery.setOrdernumber(index);
			imageInGallery.setImageId(blobId);
			imageInGallery.setImageext(imageGalleryPositionBean.getUploadData().getExtension());
			imageInGallery.setDeleted(0);
			imageInGalleryDAO.insertImageInGallery(imageInGallery);
			if (index == this.getCoverPosition()) {
				imageGalleryNote.setImageId(blobId);
				imageGalleryNote.setImageext(imageGalleryPositionBean.getUploadData().getExtension());
				DAOManager.getImageGalleryDAO().updateImageGalleryByPrimaryKey(imageGalleryNote);
			}
			index = index + 1;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ImageGallery> getAllImageGalleries() {
		return allImageGalleries;
	}

	public void setAllImageGalleries(List<ImageGallery> allImageGalleries) {
		this.allImageGalleries = allImageGalleries;
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
			if (index == this.getCoverPosition()) {
				this.setCoverPosition(this.getCoverPosition() - 1);
			} else {
				if (index - 1 == this.getCoverPosition()) {
					this.setCoverPosition(this.getCoverPosition() + 1);
				}
			}
			RankingPositionBean prev = this.getPositions().get(index - 1);
			RankingPositionBean act = this.getPositions().get(index);
			this.getPositions().set(index - 1, act);
			this.getPositions().set(index, prev);
		}
	}
	
	public void movePositionDown(int index) {
		if (index < this.getPositions().size() - 1) {
			if (index == this.getCoverPosition()) {
				this.setCoverPosition(this.getCoverPosition() + 1);
			} else {
				if (index + 1 == this.getCoverPosition()) {
					this.setCoverPosition(this.getCoverPosition() - 1);
				}
			}
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

	public boolean hasRankingUploadData(int pos) {
		return this.getPositions().get(pos).getUploadData() != null;
	}
	
	public UploadData getRankingUploadData(int pos) {
		return this.getPositions().get(pos).getUploadData();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public void deleteImage(int id2) {
		if (id2 == this.getCoverPosition()) {
			this.setCoverPosition(0);
		}
		if (id2 < this.getCoverPosition()) {
			this.setCoverPosition(this.getCoverPosition() - 1);
		}
		RankingPositionBean noteImageBean = this.getPositions().get(id2);
		this.getPositions().remove(noteImageBean);
	}

	public int getCoverPosition() {
		return coverPosition;
	}

	public void setCoverPosition(int coverPosition) {
		this.coverPosition = coverPosition;
	}

}
