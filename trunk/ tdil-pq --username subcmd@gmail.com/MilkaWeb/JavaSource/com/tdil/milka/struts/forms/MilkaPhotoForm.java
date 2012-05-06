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
import com.tdil.milka.dao.MilkaPhotoDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.Author;
import com.tdil.milka.model.ClickCounter;
import com.tdil.milka.model.MilkaPhoto;
import com.tdil.milka.utils.BlobHelper;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class MilkaPhotoForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private AuthorBean authorBean;
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
		FormFile formFile = this.getPhotoFormFile();
		UploadData uploadData = FieldValidation.validateFormFile(formFile, "", true, error);
		if (uploadData != null) {
			int fileSize = formFile.getFileSize();
			if (fileSize > 1000000) {
				error.setFieldError("", ValidationErrors.TOO_BIG);
				this.setPhoto(null);
				return;
			}
			this.setPhoto(uploadData);
		}
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		MilkaPhotoDAO milkaPhotoDAO = DAOManager.getMilkaPhotoDAO();
		AuthorDAO authorDAO = DAOManager.getAuthorDAO();
		ClickCounterDAO clickCounterDAO = DAOManager.getClickCounterDAO();
		ClickCounter clickCounter = new ClickCounter();
		clickCounter.setClicks(0);
		clickCounter.setDeleted(0);
		int clickCounterId = clickCounterDAO.insertClickCounter(clickCounter);
		Author author = getAuthorBean().asAuthor();
		int authorId = authorDAO.insertAuthor(author);
		int blobId = BlobHelper.insertBlob(this.getPhoto());
		
		MilkaPhoto milkaPhoto = new MilkaPhoto();
		milkaPhoto.setApproved(0);
		milkaPhoto.setCreationdate(new Date());
		milkaPhoto.setDeleted(0);
		milkaPhoto.setExtBlobData(this.getPhoto().getExtension());
		milkaPhoto.setFrontcover(0);
		milkaPhoto.setIdAuthor(authorId);
		milkaPhoto.setIdClickCounter(clickCounterId);
		milkaPhoto.setIdBlobData(blobId);
		milkaPhoto.setShowinhome(0);
		milkaPhotoDAO.insertMilkaPhoto(milkaPhoto);
	}	


	private static Logger getLog() {
		return LoggerProvider.getLogger(MilkaPhotoForm.class);
	}
	public AuthorBean getAuthorBean() {
		if (authorBean == null) {
			authorBean = new AuthorBean();
		}
		return authorBean;
	}
	public void setAuthorBean(AuthorBean authorBean) {
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
