package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.BlobDataDAO;
import com.tdil.milka.dao.MailToParentDAO;
import com.tdil.milka.dao.SystemPropertyDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.Author;
import com.tdil.milka.model.MailToParent;
import com.tdil.milka.model.NotificationEmail;
import com.tdil.milka.model.NotificationEmailExample;
import com.tdil.milka.model.SystemProperty;
import com.tdil.milka.model.SystemPropertyExample;
import com.tdil.milka.model.valueobjects.MailToParentValueObject;
import com.tdil.milka.utils.BlobHelper;
import com.tdil.milka.utils.LinkHelper;
import com.tdil.milka.utils.SystemPropertiesKeys;
import com.tdil.milka.web.Experience;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxFileUploadAction;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.ApproveDisapproveForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.utils.EmailUtils;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

// TODO validaciones
public class MailToParentAdministrationForm extends TransactionalValidationForm implements ApproveDisapproveForm , AjaxUploadHandlerForm, LinkAnchorForm {

	private int objectId;
	private boolean frontcover;
	private boolean showinhome;
	private int idBlobData;
	private String extBlobData;

	private String title;
	private String description;
	private String destinationType;
	private int destinationId;
	private String urlLink;
	private String urlTarget;
	
	private UploadData endingToApprove;
	
	private static final int MAX_REPLACEMENT_SIZE = 1000000; // 1mb
	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	private List<MailToParentValueObject> sourceList;

	
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
	
	public String getOriginType() {
		return Experience.CARTAS_DE_HIJOS_A_PADRES.name();
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
		setSourceList(DAOManager.getMailToParentDAO().selectMailToParentToApproveWithAuthor());
	}
	
	public void initForReview() throws SQLException {
		setSourceList(DAOManager.getMailToParentDAO().selectMailToParentToReviewWithAuthor());
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
		MailToParentDAO mailToParentDAO = DAOManager.getMailToParentDAO();
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		MailToParent mailToParent = mailToParentDAO.selectMailToParentByPrimaryKey(id);
		if (mailToParent != null) {
			this.objectId = id;
			this.frontcover = mailToParent.getFrontcover().equals(1);
			this.showinhome = mailToParent.getShowinhome().equals(1);
			this.idBlobData = mailToParent.getIdBlobData();
			this.extBlobData = mailToParent.getExtBlobData();
			this.title = mailToParent.getTitle();
			this.description = mailToParent.getDescription();
			this.urlLink = mailToParent.getUrlLink();
			this.urlTarget = mailToParent.getUrlTarget();
			if (mailToParent.getIdApprovedData() == null || mailToParent.getIdApprovedData() == 0) {
				com.tdil.milka.model.BlobData content = blobDataDAO.selectBlobDataByPrimaryKey(mailToParent.getIdBlobData());
				this.setEndingToApprove(new UploadData(content.getFilename(), content.getContent(), true));
			} else {
				com.tdil.milka.model.BlobData content = blobDataDAO.selectBlobDataByPrimaryKey(mailToParent.getIdApprovedData());
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
		for (MailToParentValueObject mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(1);
			}
		}
	}
	
	public void postDisapprove() {
		setUrlLink(null);
		for (MailToParentValueObject mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(2);
			}
		}
	}
	
	public void approve() throws SQLException, ValidationException {
		MailToParentDAO mailToParentDAO = DAOManager.getMailToParentDAO();
		MailToParent mailToParent = mailToParentDAO.selectMailToParentByPrimaryKey(this.getObjectId());
		mailToParent.setApproved(1);
		// cambio el link
		if (!LinkHelper.areEquals(mailToParent.getUrlLink(), this.getUrlLink())) {
			LinkHelper.deleteActualLink(this.getOriginType(), mailToParent.getId());
			if (!StringUtils.isEmpty(this.getUrlLink())) {
				LinkHelper.createNewLink(this.getOriginType(), mailToParent.getId(), destinationType, destinationId);
			}
		}
		setData(mailToParent);
		mailToParent.setPublishdate(new Date());
		mailToParentDAO.updateMailToParentByPrimaryKey(mailToParent);
		
		/** Inicio del email */
		com.tdil.milka.web.EmailUtils.sendContentApprovedEmail(mailToParent.getIdAuthor(), com.tdil.milka.web.EmailUtils.cartasdehijosapadres, SystemPropertiesKeys.CARTAS_DE_HIJOS_A_PADRES_URL, Experience.getLink(mailToParent));
	}
	
	private void setData(MailToParent mailToParent) throws SQLException {
		mailToParent.setFrontcover(this.isFrontcover() ? 1 : 0);
		mailToParent.setShowinhome(this.isShowinhome() ? 1 : 0);
		mailToParent.setTitle(this.getTitle());
		mailToParent.setDescription(this.getDescription());
		mailToParent.setUrlLink(this.getUrlLink());
		mailToParent.setUrlTarget(this.getUrlTarget());
		if (BlobHelper.shouldDeleteBlob(this.getEndingToApprove())) {
			BlobHelper.deleteBlob(mailToParent.getIdApprovedData());
		}
		if (BlobHelper.shouldInsertBlob(this.getEndingToApprove())) {
			int blobId = BlobHelper.insertBlob(this.getEndingToApprove());
			mailToParent.setIdApprovedData(blobId);
			mailToParent.setExtApprovedData(this.getEndingToApprove().getExtension());
		}
	}
	public void disapprove() throws SQLException, ValidationException {
		MailToParentDAO mailToParentDAO = DAOManager.getMailToParentDAO();
		MailToParent mailToParent = mailToParentDAO.selectMailToParentByPrimaryKey(this.getObjectId());
		mailToParent.setApproved(2);
		setData(mailToParent);
		mailToParent.setUrlLink(null);
		mailToParentDAO.updateMailToParentByPrimaryKey(mailToParent);
		// cambio el link
		LinkHelper.deleteActualLink(this.getOriginType(), mailToParent.getId());
		LinkHelper.deleteOriginsFor(this.getOriginType(), mailToParent.getId());
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(MailToParentAdministrationForm.class);
	}
	public List<MailToParentValueObject> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<MailToParentValueObject> approvalPending) {
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
	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
	public int getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

}
