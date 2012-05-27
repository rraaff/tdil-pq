package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.BlobDataDAO;
import com.tdil.milka.dao.EmailEndingsDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.EmailEndings;
import com.tdil.milka.model.valueobjects.EmailEndingsValueObject;
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
public class EmailEndingsAdministrationForm extends TransactionalValidationForm implements ApproveDisapproveForm , AjaxUploadHandlerForm {

	private int objectId;
	private boolean frontcover;
	private boolean showinhome;
	private int idBlobData;
	private String extBlobData;

	private String title;
	private String description;
	private String urlLink;
	private String urlTarget;
	
	private UploadData endingToApprove;
	
	private static final int MAX_REPLACEMENT_SIZE = 1000000; // 1mb
	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	private List<EmailEndingsValueObject> sourceList;

	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.frontcover = false;
		this.showinhome = false;
		this.idBlobData = 0;
		this.extBlobData = null;
		this.title = null;
		this.description = null;
		this.endingToApprove = null;
		this.urlLink = null;
		this.urlTarget = null;
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
		setSourceList(DAOManager.getEmailEndingsDAO().selectEmailEndingsToApproveWithAuthor());
	}
	
	public void initForReview() throws SQLException {
		setSourceList(DAOManager.getEmailEndingsDAO().selectEmailEndingsToReviewWithAuthor());
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
		EmailEndingsDAO emailEndingsDAO = DAOManager.getEmailEndingsDAO();
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		EmailEndings emailEndings = emailEndingsDAO.selectEmailEndingsByPrimaryKey(id);
		if (emailEndings != null) {
			this.objectId = id;
			this.frontcover = emailEndings.getFrontcover().equals(1);
			this.showinhome = emailEndings.getShowinhome().equals(1);
			this.idBlobData = emailEndings.getIdBlobData();
			this.extBlobData = emailEndings.getExtBlobData();
			this.title = emailEndings.getTitle();
			this.description = emailEndings.getDescription();
			this.urlLink = emailEndings.getUrlLink();
			this.urlTarget = emailEndings.getUrlTarget();
			if (emailEndings.getIdApprovedData() == null || emailEndings.getIdApprovedData() == 0) {
				com.tdil.milka.model.BlobData content = blobDataDAO.selectBlobDataByPrimaryKey(emailEndings.getIdBlobData());
				this.setEndingToApprove(new UploadData(content.getFilename(), content.getContent(), true));
			} else {
				com.tdil.milka.model.BlobData content = blobDataDAO.selectBlobDataByPrimaryKey(emailEndings.getIdApprovedData());
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
		for (EmailEndingsValueObject mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(1);
			}
		}
	}
	
	public void postDisapprove() {
		for (EmailEndingsValueObject mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(2);
			}
		}
	}
	
	public void approve() throws SQLException, ValidationException {
		EmailEndingsDAO emailEndingsDAO = DAOManager.getEmailEndingsDAO();
		EmailEndings emailEndings = emailEndingsDAO.selectEmailEndingsByPrimaryKey(this.getObjectId());
		emailEndings.setApproved(1);
		setData(emailEndings);
		emailEndings.setPublishdate(new Date());
		emailEndingsDAO.updateEmailEndingsByPrimaryKey(emailEndings);
	}
	
	private void setData(EmailEndings emailEndings) throws SQLException {
		emailEndings.setFrontcover(this.isFrontcover() ? 1 : 0);
		emailEndings.setShowinhome(this.isShowinhome() ? 1 : 0);
		emailEndings.setTitle(this.getTitle());
		emailEndings.setDescription(this.getDescription());
		emailEndings.setUrlLink(this.getUrlLink());
		emailEndings.setUrlTarget(this.getUrlTarget());
		if (BlobHelper.shouldDeleteBlob(this.getEndingToApprove())) {
			BlobHelper.deleteBlob(emailEndings.getIdApprovedData());
		}
		if (BlobHelper.shouldInsertBlob(this.getEndingToApprove())) {
			int blobId = BlobHelper.insertBlob(this.getEndingToApprove());
			emailEndings.setIdApprovedData(blobId);
			emailEndings.setExtApprovedData(this.getEndingToApprove().getExtension());
		}
	}
	public void disapprove() throws SQLException, ValidationException {
		EmailEndingsDAO emailEndingsDAO = DAOManager.getEmailEndingsDAO();
		EmailEndings emailEndings = emailEndingsDAO.selectEmailEndingsByPrimaryKey(this.getObjectId());
		emailEndings.setApproved(2);
		setData(emailEndings);
		emailEndingsDAO.updateEmailEndingsByPrimaryKey(emailEndings);
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(EmailEndingsAdministrationForm.class);
	}
	public List<EmailEndingsValueObject> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<EmailEndingsValueObject> approvalPending) {
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
	public UploadData getEndingToApprove() {
		return endingToApprove;
	}
	public void setEndingToApprove(UploadData endingToApprove) {
		this.endingToApprove = endingToApprove;
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

}
