package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.dao.BlobDataDAO;
import com.tdil.djmag.dao.RankingPositionDAO;
import com.tdil.djmag.dao.RankingPositionImageDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.BlobData;
import com.tdil.djmag.model.RankingPosition;
import com.tdil.djmag.model.RankingPositionImage;
import com.tdil.djmag.model.RankingPositionImageExample;
import com.tdil.djmag.utils.BlobHelper;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxFileUploadAction;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class RankingPositionForm extends TransactionalValidationForm implements AjaxUploadHandlerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private int rankingPos;
	private String title;
	private String summary;
	private Integer ordernumber;
	private UploadData frontCover;
	private String imageext;
	private Integer imageId;
	private String content;
	
	private List<RankingPositionBean> positions = new ArrayList<RankingPositionBean>();

	private static final String title_key = "RankingPosition.title";
	private static final String summary_key = "RankingPosition.summary";
	private static final String content_key = "RankingPosition.content";
	private static final String imageGallery_photo_key = "RankingPosition.photo";
	private static final int MAX_PHOTO_SIZE = 1000000;

	@Override
	public void init() throws SQLException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.title = null;
		this.summary = null;
		this.ordernumber= 0 ;
		this.frontCover = null;
		this.imageext= null ;
		this.imageId = 0;
		this.content = null;
		this.positions = new ArrayList<RankingPositionBean>();
	}
	
	public int getMaxImages() {
		return this.getPositions().size();
	}
	
	public void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError error,
			Map<String, Object> result) {
		if (this.getOperation().equals("cover")) {
			FileItem uploaded = fileItems.get(AjaxFileUploadAction.UPLOAD_NAME);
			UploadData uploadData = FieldValidation.validateFileItem(uploaded, imageGallery_photo_key, true, error);
			if (uploadData != null) {
				long fileSize = uploaded.getSize();
				if (fileSize > MAX_PHOTO_SIZE) {
					error.setFieldError(imageGallery_photo_key, ValidationErrors.TOO_BIG);
					return;
				}
				this.setFrontCover(uploadData);
			}
			result.put("result", "OK");
		} else {
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
	}
	
	public void deleteImage(int id2) {
		RankingPositionBean noteImageBean = this.getPositions().get(id2);
		this.getPositions().remove(noteImageBean);
	}
	
	public void moveImageUp(int index) {
		if (index > 0) {
			RankingPositionBean prev = this.getPositions().get(index - 1);
			RankingPositionBean act = this.getPositions().get(index);
			this.getPositions().set(index - 1, act);
			this.getPositions().set(index, prev);
		}
	}
	
	public void moveImageDown(int index) {
		if (index < this.getPositions().size() - 1) {
			RankingPositionBean next = this.getPositions().get(index + 1);
			RankingPositionBean act = this.getPositions().get(index);
			this.getPositions().set(index + 1, act);
			this.getPositions().set(index, next);
		}
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		this.getPositions().clear();
		RankingPositionDAO rankingDAO = DAOManager.getRankingPositionDAO();
		RankingPositionImageDAO rankingPositionImageDAO = DAOManager.getRankingPositionImageDAO();
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		RankingPosition position = rankingDAO.selectRankingPositionByPrimaryKey(id);
		if (position != null) {
			this.objectId = id;
			this.rankingPos = position.getOrdernumber() + 1;
			this.title = position.getTitle();
			this.summary = position.getSummary();
			this.ordernumber= position.getOrdernumber();
			this.imageext= position.getImageext();
			this.imageId = position.getImageId();
			if (this.imageId == null) {
				this.imageId = 0;
			}
			this.content = position.getContent();
			if (position.getImageId() != null && position.getImageId() != 0) {
				BlobData frontCover = blobDataDAO.selectBlobDataByPrimaryKey(position.getImageId());
				this.setFrontCover(new UploadData(frontCover.getFilename(), frontCover.getContent(), false));
			}
			RankingPositionImageExample rankingPositionImageExample = new RankingPositionImageExample();
			rankingPositionImageExample.createCriteria().andIdRankingPosEqualTo(id);
			rankingPositionImageExample.setOrderByClause("orderNumber");
			List<RankingPositionImage> images = rankingPositionImageDAO.selectRankingPositionImageByExample(rankingPositionImageExample);
			for (RankingPositionImage image : images) {
				this.getPositions().add(createPosition(image));
			}
		} 
	}

	private RankingPositionBean createPosition(RankingPositionImage image) throws SQLException {
		BlobData blobData = DAOManager.getBlobDataDAO().selectBlobDataByPrimaryKey(image.getImageId());
		RankingPositionBean result = new RankingPositionBean();
		result.setUploadData(new UploadData(blobData.getFilename(), blobData.getContent(), false));
		return result;
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getTitle(), title_key, 250, validationError);
		FieldValidation.validateText(this.getSummary(), summary_key, 4000, false, validationError);
		FieldValidation.validateText(this.getContent(), content_key, ValidationErrors.MEDIUMTEXT_LENGTH, false, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		
	}

	@Override
	public void save() throws SQLException, ValidationException {
		if (com.tdil.utils.StringUtils.equalsUnescaped(this.getOperation(), ApplicationResources.getMessage("cancel"))) {
			return;
		}
		RankingPositionDAO rankingNoteDAO = DAOManager.getRankingPositionDAO();
		RankingPosition rankingNote = rankingNoteDAO.selectRankingPositionByPrimaryKey(this.getObjectId());
		rankingNote.setId(this.getObjectId());
		rankingNote.setTitle(this.getTitle());
		rankingNote.setSummary(this.getSummary());
		rankingNote.setContent(this.getContent());
		if (BlobHelper.shouldDeleteBlob(this.getFrontCover())) {
			BlobHelper.deleteBlob(this.getImageId());
			rankingNote.setImageId(0);
			rankingNote.setImageext("");
		}
		if (BlobHelper.shouldInsertBlob(this.getFrontCover())) {
			int blobId = BlobHelper.insertBlob(this.getFrontCover());
			rankingNote.setImageId(blobId);
			rankingNote.setImageext(this.getFrontCover().getExtension());
		}
		
		// TODO todos los datos
		rankingNoteDAO.updateRankingPositionByPrimaryKeyWithBLOBs(rankingNote);
		updateImageGallery(this.getObjectId());
	}
	
	private void updateImageGallery(int rankingPositionId) throws SQLException {
		RankingPositionImageDAO imageInGalleryDAO = DAOManager.getRankingPositionImageDAO();
		int index = 0;
		RankingPositionImageExample imageInGalleryExample = new RankingPositionImageExample();
		imageInGalleryExample.createCriteria().andIdRankingPosEqualTo(rankingPositionId);
		List<RankingPositionImage> toDelete = imageInGalleryDAO.selectRankingPositionImageByExample(imageInGalleryExample);
		for (RankingPositionImage img : toDelete) {
			BlobHelper.deleteBlob(img.getImageId());
		}
		imageInGalleryDAO.deleteRankingPositionImageByExample(imageInGalleryExample);
		for (RankingPositionBean imageGalleryPositionBean : this.getPositions()) {
			int blobId = BlobHelper.insertBlob(imageGalleryPositionBean.getUploadData());
			imageGalleryPositionBean.setBlobId(blobId);
			RankingPositionImage imageInGallery = new RankingPositionImage();
			imageInGallery.setIdRankingPos(rankingPositionId);
			imageInGallery.setOrdernumber(index);
			imageInGallery.setImageId(blobId);
			imageInGallery.setImageext(imageGalleryPositionBean.getUploadData().getExtension());
			imageInGallery.setDeleted(0);
			imageInGalleryDAO.insertRankingPositionImage(imageInGallery);
			index = index + 1;
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

	public List<RankingPositionBean> getPositions() {
		return positions;
	}

	public void setPositions(List<RankingPositionBean> positions) {
		this.positions = positions;
	}

	public int getRankingPos() {
		return rankingPos;
	}

	public void setRankingPos(int rankingPos) {
		this.rankingPos = rankingPos;
	}

	public UploadData getPhoto(int pos) {
		return this.getPositions().get(pos).getUploadData();
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getImageext() {
		return imageext;
	}

	public void setImageext(String imageext) {
		this.imageext = imageext;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UploadData getFrontCover() {
		return frontCover;
	}

	public void setFrontCover(UploadData frontCover) {
		this.frontCover = frontCover;
	}

}
