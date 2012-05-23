package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.BlobDataDAO;
import com.tdil.milka.dao.MilkaPhotoDAO;
import com.tdil.milka.dao.MilkaPhotoTagDAO;
import com.tdil.milka.dao.TagDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.MilkaPhoto;
import com.tdil.milka.model.MilkaPhotoTag;
import com.tdil.milka.model.MilkaPhotoTagExample;
import com.tdil.milka.model.Tag;
import com.tdil.milka.model.TagExample;
import com.tdil.milka.model.valueobjects.MilkaPhotoValueObject;
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

// TODO validaciones
public class MilkaPhotoAdministrationForm extends TransactionalValidationForm implements ApproveDisapproveForm, AjaxUploadHandlerForm {

	private int objectId;
	private boolean frontcover;
	private boolean showinhome;
	private int idBlobData;
	private String extBlobData;
	private String tags;
	
	private UploadData endingToApprove;
	
	private String originalTags;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	private List<MilkaPhotoValueObject> sourceList;

	private static final int MAX_REPLACEMENT_SIZE = 1000000; // 1mb
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.frontcover = false;
		this.showinhome = false;
		this.idBlobData = 0;
		this.extBlobData = null;
		this.tags = null;
		this.originalTags = null;
		this.endingToApprove = null;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.frontcover = false;
		this.showinhome = false; 
	}

	@Override
	public void init() throws SQLException {
	}
	
	public void initForApprove() throws SQLException {
		setSourceList(DAOManager.getMilkaPhotoDAO().selectMilkaPhotoToApproveWithAuthor());
	}
	
	public void initForReview() throws SQLException {
		setSourceList(DAOManager.getMilkaPhotoDAO().selectMilkaPhotoToReviewWithAuthor());
	}

	public void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError error,
			Map<String, Object> result) {
		FileItem uploaded = fileItems.get(AjaxFileUploadAction.UPLOAD_NAME);
		UploadData uploadData = FieldValidation.validateFileItem(uploaded, "ranking_photo_key", true, error);
		if (uploadData != null) {
			long fileSize = uploaded.getSize();
			if (fileSize > MAX_REPLACEMENT_SIZE) {
				error.setFieldError("ranking_photo_key", ValidationErrors.TOO_BIG);
				this.setEndingToApprove(null);
				return;
			}
			this.setEndingToApprove(uploadData);
		}
		result.put("result", "OK");
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		MilkaPhotoDAO milkaPhotoDAO = DAOManager.getMilkaPhotoDAO();
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		MilkaPhoto milkaPhoto = milkaPhotoDAO.selectMilkaPhotoByPrimaryKey(id);
		if (milkaPhoto != null) {
			this.objectId = id;
			this.frontcover = milkaPhoto.getFrontcover().equals(1);
			this.showinhome = milkaPhoto.getShowinhome().equals(1);
			this.idBlobData = milkaPhoto.getIdBlobData();
			this.extBlobData = milkaPhoto.getExtBlobData();
			this.tags = milkaPhoto.getTags();
			this.originalTags = this.tags;
			
			if (milkaPhoto.getIdApprovedData() == null || milkaPhoto.getIdApprovedData() == 0) {
				com.tdil.milka.model.BlobData content = blobDataDAO.selectBlobDataByPrimaryKey(milkaPhoto.getIdBlobData());
				this.setEndingToApprove(new UploadData(content.getFilename(), content.getContent(), true));
			} else {
				com.tdil.milka.model.BlobData content = blobDataDAO.selectBlobDataByPrimaryKey(milkaPhoto.getIdApprovedData());
				this.setEndingToApprove(new UploadData(content.getFilename(), content.getContent(), false));
			}
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
		for (MilkaPhotoValueObject mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(1);
			}
		}
		this.originalTags = this.getTags();
	}
	
	public void postDisapprove() {
		for (MilkaPhotoValueObject mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(2);
			}
		}
		this.originalTags = this.getTags();
	}
	
	public void approve() throws SQLException, ValidationException {
		MilkaPhotoDAO milkaPhotoDAO = DAOManager.getMilkaPhotoDAO();
		MilkaPhoto milkaPhoto = milkaPhotoDAO.selectMilkaPhotoByPrimaryKey(this.getObjectId());
		milkaPhoto.setApproved(1);
		setData(milkaPhoto);
		milkaPhoto.setPublishdate(new Date());
		milkaPhotoDAO.updateMilkaPhotoByPrimaryKey(milkaPhoto);
		updateTags(milkaPhoto);
	}
	
	private void setData(MilkaPhoto milkaPhoto) throws SQLException {
		milkaPhoto.setFrontcover(this.isFrontcover() ? 1 : 0);
		milkaPhoto.setShowinhome(this.isShowinhome() ? 1 : 0);
		milkaPhoto.setTags(this.getTags());
		if (BlobHelper.shouldDeleteBlob(this.getEndingToApprove())) {
			BlobHelper.deleteBlob(milkaPhoto.getIdApprovedData());
		}
		if (BlobHelper.shouldInsertBlob(this.getEndingToApprove())) {
			int blobId = BlobHelper.insertBlob(this.getEndingToApprove());
			milkaPhoto.setIdApprovedData(blobId);
			milkaPhoto.setExtApprovedData(this.getEndingToApprove().getExtension());
		}
	}
	public void disapprove() throws SQLException, ValidationException {
		MilkaPhotoDAO milkaPhotoDAO = DAOManager.getMilkaPhotoDAO();
		MilkaPhoto milkaPhoto = milkaPhotoDAO.selectMilkaPhotoByPrimaryKey(this.getObjectId());
		milkaPhoto.setApproved(2);
		setData(milkaPhoto);
		milkaPhotoDAO.updateMilkaPhotoByPrimaryKey(milkaPhoto);
		updateTags(milkaPhoto);
	}


	private void updateTags(MilkaPhoto milkaPhoto) throws SQLException {
		TagDAO tagDAO = DAOManager.getTagDAO();
		MilkaPhotoTagDAO photoTagDAO = DAOManager.getMilkaPhotoTagDAO();
		String newtags[] = StringUtils.split(this.getTags(), ',');
		String originaltags[] = StringUtils.split(this.originalTags, ',');
		
		Set<String> newTagSet = new HashSet<String>();
		Collections.addAll(newTagSet, newtags);
		
		Set<String> originalTagSet = new HashSet<String>();
		if (originaltags != null) {
			Collections.addAll(originalTagSet, originaltags);
		}
		for (String actualTag : newTagSet) {
			if (!StringUtils.isEmpty(actualTag)) {
				if (!originalTagSet.contains(actualTag)) {
					// si no lo tenia, lo tengo que agregar
					TagExample tagExample = new TagExample();
					tagExample.createCriteria().andDescriptionEqualTo(actualTag);
					List<Tag> tags = tagDAO.selectTagByExample(tagExample);
					if (tags.isEmpty()) {
						// si no existe el tag, lo creo
						Tag tag = new Tag();
						tag.setDescription(actualTag);
						tag.setDeleted(0);
						int tagId = tagDAO.insertTag(tag);
						MilkaPhotoTag milkaPhotoTag = new MilkaPhotoTag();
						milkaPhotoTag.setIdMilkaPhoto(milkaPhoto.getId());
						milkaPhotoTag.setIdTag(tagId);
						milkaPhotoTag.setDeleted(0);
						photoTagDAO.insertMilkaPhotoTag(milkaPhotoTag);
					} else {
						// si existia, lo engancho
						MilkaPhotoTag milkaPhotoTag = new MilkaPhotoTag();
						milkaPhotoTag.setIdMilkaPhoto(milkaPhoto.getId());
						milkaPhotoTag.setIdTag(tags.get(0).getId());
						milkaPhotoTag.setDeleted(0);
						photoTagDAO.insertMilkaPhotoTag(milkaPhotoTag);
					}
				}
			}
		}
		
		for (String oldTag : originalTagSet) {
			if(!StringUtils.isEmpty(oldTag)) {
				if (!newTagSet.contains(oldTag)) {
					// Si ya no tiene el tag, lo tengo que borrar
					MilkaPhotoTagExample example = new MilkaPhotoTagExample();
					TagExample tagExample = new TagExample();
					tagExample.createCriteria().andDescriptionEqualTo(oldTag);
					List<Tag> tags = tagDAO.selectTagByExample(tagExample);
					example.createCriteria().andIdMilkaPhotoEqualTo(milkaPhoto.getId()).andIdTagEqualTo(tags.get(0).getId());
					photoTagDAO.deleteMilkaPhotoTagByExample(example);
					//MilkaPhotoTag milkaPhotoTag = photoTagDAO.selectMilkaPhotoTagByExample(example).get(0);
					//photoTagDAO.deleteMilkaPhotoTagByPrimaryKey(milkaPhotoTag.getId());
				}
			}
		}
		
	}
	private static Logger getLog() {
		return LoggerProvider.getLogger(MilkaPhotoAdministrationForm.class);
	}
	public List<MilkaPhotoValueObject> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<MilkaPhotoValueObject> approvalPending) {
		this.sourceList = approvalPending;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public boolean isFrontcover() {
		return frontcover;
	}
	public void setFrontcover(boolean frontcover) {
		this.frontcover = frontcover;
	}
	public boolean isShowinhome() {
		return showinhome;
	}
	public void setShowinhome(boolean showinhome) {
		this.showinhome = showinhome;
	}
	public int getIdBlobData() {
		return idBlobData;
	}
	public void setIdBlobData(int idBlobData) {
		this.idBlobData = idBlobData;
	}
	public String getExtBlobData() {
		return extBlobData;
	}
	public void setExtBlobData(String extBlobData) {
		this.extBlobData = extBlobData;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public UploadData getEndingToApprove() {
		return endingToApprove;
	}
	public void setEndingToApprove(UploadData endingToApprove) {
		this.endingToApprove = endingToApprove;
	}

}
