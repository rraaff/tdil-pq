package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.AuthorDAO;
import com.tdil.milka.dao.ClickCounterDAO;
import com.tdil.milka.dao.MailToParentDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.Author;
import com.tdil.milka.model.ClickCounter;
import com.tdil.milka.model.MailToParent;
import com.tdil.milka.model.valueobjects.AuthorValueObject;
import com.tdil.milka.utils.BlobHelper;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class MailToParentForm extends TransactionalValidationForm {

	private static final int MAX_PHOTO_SIZE = 1000000;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private AuthorValueObject authorBean;
	private FormFile photoFormFile;
	private UploadData photo;
	
	@Override
	public void reset() throws SQLException {
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
	}
	
	@Override
	public void basicValidate(ValidationError error) {
		getAuthorBean().basicValidate(error);
		FormFile formFile = this.getPhotoFormFile();
		UploadData uploadData = FieldValidation.validateFormFile(formFile, "", true, error);
		if (uploadData != null) {
			int fileSize = formFile.getFileSize();
			if (fileSize > MAX_PHOTO_SIZE) {
				error.setFieldError("MailToParentForm.photo", ValidationErrors.TOO_BIG);
				this.setPhoto(null);
				return;
			}
			this.setPhoto(uploadData);
		} else {
			error.setFieldError("MailToParentForm.photo", ValidationErrors.CANNOT_BE_EMPTY);
		}
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		MailToParentDAO mailsToParentsDao = DAOManager.getMailToParentDAO();
		AuthorDAO authorDAO = DAOManager.getAuthorDAO();
		ClickCounterDAO clickCounterDAO = DAOManager.getClickCounterDAO();
		ClickCounter clickCounter = new ClickCounter();
		clickCounter.setClicks(0);
		clickCounter.setDeleted(0);
		int clickCounterId = clickCounterDAO.insertClickCounter(clickCounter);
		Author author = getAuthorBean().asAuthor();
		int authorId = authorDAO.insertAuthor(author);
		int blobId = BlobHelper.insertBlob(this.getPhoto());
		
		MailToParent mailsToParents = new MailToParent();
		mailsToParents.setApproved(0);
		mailsToParents.setCreationdate(new Date());
		mailsToParents.setDeleted(0);
		mailsToParents.setExtBlobData(this.getPhoto().getExtension());
		mailsToParents.setFrontcover(0);
		mailsToParents.setIdAuthor(authorId);
		mailsToParents.setIdClickCounter(clickCounterId);
		mailsToParents.setIdBlobData(blobId);
		mailsToParents.setShowinhome(0);
		mailsToParentsDao.insertMailToParent(mailsToParents);
	}	


	private static Logger getLog() {
		return LoggerProvider.getLogger(MailToParentForm.class);
	}
	public AuthorValueObject getAuthorBean() {
		if (authorBean == null) {
			authorBean = new AuthorValueObject();
		}
		return authorBean;
	}
	public void setAuthorBean(AuthorValueObject authorBean) {
		this.authorBean = authorBean;
	}
	public UploadData getPhoto() {
		return photo;
	}
	public void setPhoto(UploadData photo) {
		this.photo = photo;
	}
	public FormFile getPhotoFormFile() {
		return photoFormFile;
	}
	public void setPhotoFormFile(FormFile photoFormFile) {
		this.photoFormFile = photoFormFile;
	}

}
