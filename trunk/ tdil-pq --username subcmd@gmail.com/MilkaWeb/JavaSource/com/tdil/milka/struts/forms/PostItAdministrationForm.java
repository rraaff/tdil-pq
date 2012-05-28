package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.BlobDataDAO;
import com.tdil.milka.dao.PostItDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.Color;
import com.tdil.milka.model.PostIt;
import com.tdil.milka.model.valueobjects.PostItValueObject;
import com.tdil.milka.utils.BlobHelper;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxFileUploadAction;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.ApproveDisapproveForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class PostItAdministrationForm extends TransactionalValidationForm implements ApproveDisapproveForm, AjaxUploadHandlerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int objectId;
	private String originaltext;
	private String title;
	private String description;
	private String urlLink;
	private String urlTarget;
	private String color;
	
	private UploadData cover;
	private UploadData thumb;
	
	private List<String> allColors;
	
	private List<PostItValueObject> sourceList;
	
	private static final int MAX_THUMB_SIZE = 50000; // 50k
	private static final int MAX_COVER_SIZE = 100000; // 100k
	
	@Override
	public void reset() throws SQLException {
		objectId = 0;
		originaltext = null;
		title = null;
		description = null;
		urlLink = null;
		urlTarget = null;
		color = null;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}
	
	public void initForApprove() throws SQLException {
		setSourceList(DAOManager.getPostItDAO().selectPostItsToApproveWithAuthor());
	}
	
	public void initForReview() throws SQLException {
		setSourceList(DAOManager.getPostItDAO().selectPostItsToReviewWithAuthor());
	}

	@Override
	public void initWith(int id) throws SQLException {
		PostItDAO postItDAO = DAOManager.getPostItDAO();
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		PostIt postIt = postItDAO.selectPostItByPrimaryKey(id);
		if (postIt != null) {
			this.objectId = id;
			this.originaltext = postIt.getOriginaltext();
			this.title = postIt.getTitle();
			this.description = postIt.getDescription();
			this.urlLink = postIt.getUrlLink();
			this.urlTarget = postIt.getUrlTarget();
			this.color = postIt.getColor();
			if (!StringUtils.isEmpty(postIt.getExtImage())) {
				com.tdil.milka.model.BlobData content = blobDataDAO.selectBlobDataByPrimaryKey(postIt.getIdImage());
				this.setCover(new UploadData(content.getFilename(), content.getContent(), false));
			} else {
				this.setCover(null);
			}
			if (!StringUtils.isEmpty(postIt.getExtThum())) {
				com.tdil.milka.model.BlobData content = blobDataDAO.selectBlobDataByPrimaryKey(postIt.getIdThumb());
				this.setThumb(new UploadData(content.getFilename(), content.getContent(), false));
			} else {
				this.setThumb(null);
			}
		} 
	}

	public boolean getHasThumb() {
		return this.getThumb() != null;
	}
	
	public boolean getHasCover() {
		return this.getCover() != null;
	}
	
	public void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError error,
			Map<String, Object> result) {
		FileItem uploaded = fileItems.get(AjaxFileUploadAction.UPLOAD_NAME);
		String name = uploaded.getFieldName();
		if (name.startsWith("upload_thumb")) {
			UploadData uploadData = FieldValidation.validateFileItem(uploaded, "ranking_photo_key", true, error);
			if (uploadData != null) {
				long fileSize = uploaded.getSize();
				if (fileSize > MAX_THUMB_SIZE) {
					error.setFieldError("ranking_photo_key", ValidationErrors.TOO_BIG);
					this.setThumb(null);
					return;
				}
				this.setThumb(uploadData);
			}
			result.put("result", "OK");
		} else {
			UploadData uploadData = FieldValidation.validateFileItem(uploaded, "ranking_photo_key", true, error);
			if (uploadData != null) {
				long fileSize = uploaded.getSize();
				if (fileSize > MAX_COVER_SIZE) {
					error.setFieldError("ranking_photo_key", ValidationErrors.TOO_BIG);
					this.setCover(null);
					return;
				}
				this.setCover(uploadData);
			}
			result.put("result", "OK");
		}
	}
	
	@Override
	public void basicValidate(ValidationError error) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
	}	

	public void postApprove() {
		for (PostItValueObject mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(1);
			}
		}
	}
	
	public void postDisapprove() {
		for (PostItValueObject mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(2);
			}
		}
	}
	
	public void approve() throws SQLException, ValidationException {
		PostItDAO postItDAO = DAOManager.getPostItDAO();
		PostIt postIt = postItDAO.selectPostItByPrimaryKey(this.getObjectId());
		postIt.setApproved(1);
		setData(postIt);
		postIt.setPublishdate(new Date());
		postItDAO.updatePostItByPrimaryKey(postIt);
	}
	
	private void setData(PostIt postIt) throws SQLException {
		postIt.setTitle(this.getTitle());
		postIt.setDescription(this.getDescription());
		postIt.setColor(this.getColor());
		postIt.setUrlLink(this.getUrlLink());
		postIt.setUrlTarget(this.getUrlTarget());
		if (BlobHelper.shouldDeleteBlob(this.getCover())) {
			BlobHelper.deleteBlob(postIt.getIdImage());
			postIt.setIdImage(0);
			postIt.setExtImage("");
		}
		if (BlobHelper.shouldDeleteBlob(this.getThumb())) {
			BlobHelper.deleteBlob(postIt.getIdThumb());
			postIt.setIdThumb(0);
			postIt.setExtThum("");
		}
		if (BlobHelper.shouldInsertBlob(this.getCover())) {
			int blobId = BlobHelper.insertBlob(this.getCover());
			postIt.setIdImage(blobId);
			postIt.setExtImage(this.getCover().getExtension());
		}
		if (BlobHelper.shouldInsertBlob(this.getThumb())) {
			int blobId = BlobHelper.insertBlob(this.getThumb());
			postIt.setIdThumb(blobId);
			postIt.setExtThum(this.getThumb().getExtension());
		}
	}
	public void disapprove() throws SQLException, ValidationException {
		PostItDAO postItDAO = DAOManager.getPostItDAO();
		PostIt postIt = postItDAO.selectPostItByPrimaryKey(this.getObjectId());
		postIt.setApproved(2);
		setData(postIt);
		postIt.setPublishdate(new Date());
		postItDAO.updatePostItByPrimaryKey(postIt);
	}
	

	private static Logger getLog() {
		return LoggerProvider.getLogger(PostItAdministrationForm.class);
	}
	public List<PostItValueObject> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<PostItValueObject> approvalPending) {
		this.sourceList = approvalPending;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public String getOriginaltext() {
		return originaltext;
	}
	public void setOriginaltext(String originaltext) {
		this.originaltext = originaltext;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrlLink() {
		return urlLink;
	}
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
	public String getUrlTarget() {
		return urlTarget;
	}
	public void setUrlTarget(String urlTarget) {
		this.urlTarget = urlTarget;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public UploadData getCover() {
		return cover;
	}
	public void setCover(UploadData cover) {
		this.cover = cover;
	}
	public UploadData getThumb() {
		return thumb;
	}
	public void setThumb(UploadData thumb) {
		this.thumb = thumb;
	}
	public List<String> getAllColors() {
		if (allColors == null) {
			List<String> colors = new ArrayList<String>();
			colors.add(Color.AMARILLO);
			colors.add(Color.CELESTE);
			colors.add(Color.MAGENTA);
			allColors = colors;
		}
		return allColors;
	}
	public void setAllColors(List<String> allColors) {
		this.allColors = allColors;
	}

}
