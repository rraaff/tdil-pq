package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.action.ActionMapping;

import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.BlobData;
import com.tdil.milka.model.Video;
import com.tdil.milka.model.VideoExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxFileUploadAction;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.struts.forms.UploadDataBean;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class VideoForm extends TransactionalValidationForm implements AjaxUploadHandlerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private List<VideoDataBean> positions = new ArrayList<VideoDataBean>();
	
	private static final String imageGallery_photo_key = "Video.photo";
	private static final int MAX_PHOTO_SIZE = 1000000;

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.positions = new ArrayList<VideoDataBean>();
	}
	
	public int getMaxImages() {
		return this.getPositions().size();
	}
	
	public void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError error,
			Map<String, Object> result) {
		FileItem uploaded = fileItems.get(AjaxFileUploadAction.UPLOAD_NAME);
		VideoDataBean imageGalleryPositionBean = new VideoDataBean();
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
	}


	@Override
	public void init() throws SQLException {
		VideoExample imageGalleryExample = new VideoExample();
		imageGalleryExample.setOrderByClause("orderNumber");
		List<Video> videos = DAOManager.getVideoDAO().selectVideoByExample(imageGalleryExample);
		for (Video image : videos) {
			this.getPositions().add(createPosition(image));
		}
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		
	}
	
	private VideoDataBean createPosition(Video video) throws SQLException {
		BlobData blobData = DAOManager.getBlobDataDAO().selectBlobDataByPrimaryKey(video.getFrontcoverId());
		VideoDataBean result = new VideoDataBean();
		result.setVideoId(video.getId());
		result.setUploadData(new UploadData(blobData.getFilename(), blobData.getContent(), false));
		return result;
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		if (this.getPositions().isEmpty()) {
			validationError.setFieldError(imageGallery_photo_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
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
		/*for (UploadDataBean imageGalleryPositionBean : this.getPositions()) {
			int blobId = BlobHelper.insertBlob(imageGalleryPositionBean.getUploadData());
			imageGalleryPositionBean.setBlobId(blobId);
			ImageInGallery imageInGallery = new ImageInGallery();
			imageInGallery.setIdGallery(galId);
			imageInGallery.setOrdernumber(index);
			imageInGallery.setImageId(blobId);
			imageInGallery.setImageext(imageGalleryPositionBean.getUploadData().getExtension());
			imageInGallery.setDeleted(0);
			imageInGalleryDAO.insertImageInGallery(imageInGallery);
			index = index + 1;
		}*/
	}

	
	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}

	public List<VideoDataBean> getPositions() {
		return positions;
	}

	public void setPositions(List<VideoDataBean> positions) {
		this.positions = positions;
	}
	
	public VideoDataBean getSelectedPosition(int index) {  
	      return (VideoDataBean)this.getPositions().get(index);  
	   }  
	   
	public void setSelectedPosition(int index, VideoDataBean countryVO) {  
		this.getPositions().set(index, countryVO);  
	 }

	public void movePositionUp(int index) {
		if (index > 0) {
			VideoDataBean prev = this.getPositions().get(index - 1);
			VideoDataBean act = this.getPositions().get(index);
			this.getPositions().set(index - 1, act);
			this.getPositions().set(index, prev);
		}
	}
	
	public void movePositionDown(int index) {
		if (index < this.getPositions().size() - 1) {
			VideoDataBean next = this.getPositions().get(index + 1);
			VideoDataBean act = this.getPositions().get(index);
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

	public void deleteImage(int id2) {
		UploadDataBean noteImageBean = this.getPositions().get(id2);
		this.getPositions().remove(noteImageBean);
	}

}
