package com.tdil.ljpeugeot.struts.forms;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.struts.upload.FormFile;

import com.tdil.ljpeugeot.dao.BlobDataDAO;
import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.BlobData;
import com.tdil.ljpeugeot.model.WebsiteUser;
import com.tdil.ljpeugeot.model.WebsiteUserExample;
import com.tdil.ljpeugeot.utils.BlobHelper;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxFileUploadAction;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class EditProfileForm extends TransactionalValidationForm implements
		AjaxUploadHandlerForm {

	private static final long serialVersionUID = 9184547736717742618L;
	private String userId;
	private int imageId;
	private transient UploadData avatar;
	
	private boolean mobile;
	
	private transient FormFile formFile;
	
	private Integer idAvatar;
	private String extAvatar;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(EditProfileForm.class);
	
	public static final String avatar_key = "EditProfileForm.avatar";
	
	private static final int MAX_AVATAR_SIZE = 1000000;
	
	public void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError error,
			Map<String, Object> result) {
		FileItem uploaded = fileItems.get(AjaxFileUploadAction.UPLOAD_NAME);
		UploadData uploadData = FieldValidation.validateFileItemImage(uploaded, avatar_key, true, error);
		if (uploadData != null) {
			long fileSize = uploaded.getSize();
			if (fileSize > MAX_AVATAR_SIZE) {
				error.setFieldError(avatar_key, ValidationErrors.TOO_BIG);
				return;
			}
			this.setAvatar(uploadData);
		}
		result.put("result", "OK");
	}

	@Override
	public void basicValidate(ValidationError validationError) {
	}

	@Override
	public void validateInTransaction(ValidationError validationError)
			throws SQLException {
	}

	@Override
	public void reset() throws SQLException {
		this.userId = null;
		this.imageId = 0;
		this.avatar = null;
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
	}
	
	public void initWith(String userId) throws SQLException {
		this.userId = userId;
		WebsiteUserExample websiteUserExample = new WebsiteUserExample();
		websiteUserExample.createCriteria().andLojackuseridEqualTo(userId);
		List<WebsiteUser> result = DAOManager.getWebsiteUserDAO().selectWebsiteUserByExample(websiteUserExample);
		if (result != null && !result.isEmpty()) {
			WebsiteUser user = result.get(0);
			BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
			if (user.getIdAvatar() != null && user.getIdAvatar() != 0) {
				this.imageId = user.getIdAvatar();
			} 
			if (this.imageId != 0) {
				BlobData avatar = blobDataDAO.selectBlobDataByPrimaryKey(this.imageId);
				this.setAvatar(new UploadData(avatar.getFilename(), avatar.getContent(), false));
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		WebsiteUserExample websiteUserExample = new WebsiteUserExample();
		websiteUserExample.createCriteria().andLojackuseridEqualTo(userId);
		List<WebsiteUser> result = DAOManager.getWebsiteUserDAO().selectWebsiteUserByExample(websiteUserExample);
		if (isMobile()) {
			String fileName = formFile.getFileName();
			InputStream io = null;
			try {
				io = formFile.getInputStream();
				byte[] arr = IOUtils.toByteArray(io);
				this.avatar = new UploadData(fileName, arr, true);
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			} finally {
				if (io != null) {
					try {
						io.close();
					} catch (IOException e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
			
		}
		
		idAvatar = 0;
		extAvatar = null;
		if (avatar != null && avatar.isModified()) {
			if (imageId != 0) {
				BlobHelper.deleteBlob(imageId);
			}
			idAvatar = BlobHelper.insertBlob(this.getAvatar());
			extAvatar = avatar.getExtension();
		} else {
			WebsiteUser websiteUser = result.get(0);
			idAvatar = websiteUser.getIdAvatar();
			extAvatar = websiteUser.getExtAvatar();
		}
		
		if (result == null || result.isEmpty()) {
			WebsiteUser user = new WebsiteUser();
			user.setLojackuserid(userId);
			user.setIdAvatar(idAvatar);
			user.setExtAvatar(extAvatar);
			DAOManager.getWebsiteUserDAO().insertWebsiteUser(user);
		} else {
			WebsiteUser user = result.get(0);
			user.setLojackuserid(userId);
			user.setIdAvatar(idAvatar);
			user.setExtAvatar(extAvatar);
			DAOManager.getWebsiteUserDAO().updateWebsiteUserByPrimaryKey(user);
		}
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public UploadData getAvatar() {
		return avatar;
	}

	public void setAvatar(UploadData avatar) {
		this.avatar = avatar;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getIdAvatar() {
		return idAvatar;
	}

	public void setIdAvatar(int idAvatar) {
		this.idAvatar = idAvatar;
	}

	public String getExtAvatar() {
		return extAvatar;
	}

	public void setExtAvatar(String extAvatar) {
		this.extAvatar = extAvatar;
	}

	public FormFile getFormFile() {
		return formFile;
	}

	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

}