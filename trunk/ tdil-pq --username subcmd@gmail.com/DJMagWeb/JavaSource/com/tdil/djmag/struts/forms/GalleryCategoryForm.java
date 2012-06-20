package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.dao.BlobDataDAO;
import com.tdil.djmag.dao.GalleryCategoryDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.BlobData;
import com.tdil.djmag.model.GalleryCategory;
import com.tdil.djmag.model.GalleryCategoryExample;
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

public class GalleryCategoryForm extends TransactionalValidationForm implements ToggleDeletedFlagForm, AjaxUploadHandlerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String title;
	private Integer imageId;
	private UploadData frontCover;
	
	private List<GalleryCategory> allCategories;

	private static String title_key = "GalleryCategory.name";
	private static String title_duplicated_key = "DUPLICATED";
	private static String cover_key = "GalleryCategory.frontCover";
	
	private static final int MAX_COVER_SIZE = 1000000;

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.title = null;
		this.frontCover = null;
		this.imageId = 0;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		GalleryCategoryExample galleryCategoryExample = new GalleryCategoryExample();
		galleryCategoryExample.setOrderByClause("title");
		this.setAllCategories(DAOManager.getGalleryCategoryDAO().selectGalleryCategoryByExample(galleryCategoryExample));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		GalleryCategory galleryCategory = DAOManager.getGalleryCategoryDAO().selectGalleryCategoryByPrimaryKey(this.getObjectId());
		galleryCategory.setDeleted(galleryCategory.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getGalleryCategoryDAO().updateGalleryCategoryByPrimaryKeySelective(galleryCategory);
	}
	
	public void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError error,
			Map<String, Object> result) {
		FileItem uploaded = fileItems.get(AjaxFileUploadAction.UPLOAD_NAME);
		UploadData uploadData = FieldValidation.validateFileItem(uploaded, cover_key, true, error);
		if (uploadData != null) {
			long fileSize = uploaded.getSize();
			if (fileSize > MAX_COVER_SIZE) {
				error.setFieldError(cover_key, ValidationErrors.TOO_BIG);
				return;
			}
			this.setFrontCover(uploadData);
		}
		result.put("result", "OK");
	}
	
	@Override
	public void init() throws SQLException {
		GalleryCategoryExample galleryCategoryExample = new GalleryCategoryExample();
		galleryCategoryExample.setOrderByClause("title");
		this.setAllCategories(DAOManager.getGalleryCategoryDAO().selectGalleryCategoryByExample(galleryCategoryExample));
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		GalleryCategoryDAO galleryCategoryDAO = DAOManager.getGalleryCategoryDAO();
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		GalleryCategory galleryCategory = galleryCategoryDAO.selectGalleryCategoryByPrimaryKey(id);
		if (galleryCategory != null) {
			this.objectId = id;
			this.title = galleryCategory.getTitle();
			this.imageId = galleryCategory.getImageId();
			if (this.imageId == null) {
				this.imageId = 0;
			}
			if (this.imageId != 0) {
				BlobData frontCover = blobDataDAO.selectBlobDataByPrimaryKey(this.imageId);
				this.setFrontCover(new UploadData(frontCover.getFilename(), frontCover.getContent(), false));
			}
		} 
	}
	
	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getTitle(), title_key, 250, validationError);
		if (this.getFrontCover() == null) {
			validationError.setFieldError(cover_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		
	}

	@Override
	public void save() throws SQLException, ValidationException {
		GalleryCategoryDAO galleryCategoryDAO = DAOManager.getGalleryCategoryDAO();
		if (this.getObjectId() == 0) {
			int frontCoverId = BlobHelper.insertBlob(this.getFrontCover());
			GalleryCategory galleryCategory = new GalleryCategory();
			galleryCategory.setImageId(frontCoverId);
			galleryCategory.setImageext(this.getFrontCover().getExtension());
			galleryCategory.setTitle(this.getTitle());
			galleryCategory.setDeleted(0);
			galleryCategoryDAO.insertGalleryCategory(galleryCategory);
		} else {
			GalleryCategory galleryCategory = galleryCategoryDAO.selectGalleryCategoryByPrimaryKey(this.getObjectId());
			galleryCategory.setId(this.getObjectId());
			if (BlobHelper.shouldDeleteBlob(this.getFrontCover())) {
				BlobHelper.deleteBlob(galleryCategory.getImageId());
				galleryCategory.setImageId(0);
				galleryCategory.setImageext("");
			}
			if (BlobHelper.shouldInsertBlob(this.getFrontCover())) {
				int blobId = BlobHelper.insertBlob(this.getFrontCover());
				galleryCategory.setImageId(blobId);
				galleryCategory.setImageext(this.getFrontCover().getExtension());
			}
			galleryCategory.setTitle(this.getTitle());
			galleryCategoryDAO.updateGalleryCategoryByPrimaryKeySelective(galleryCategory);
		}
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UploadData getFrontCover() {
		return frontCover;
	}

	public void setFrontCover(UploadData frontCover) {
		this.frontCover = frontCover;
	}

	public List<GalleryCategory> getAllCategories() {
		return allCategories;
	}

	public void setAllCategories(List<GalleryCategory> allCategories) {
		this.allCategories = allCategories;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

}
