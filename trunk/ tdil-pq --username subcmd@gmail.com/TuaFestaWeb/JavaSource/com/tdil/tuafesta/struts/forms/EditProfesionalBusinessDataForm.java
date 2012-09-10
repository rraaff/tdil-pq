package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxFileUploadAction;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.tuafesta.dao.BlobDataDAO;
import com.tdil.tuafesta.dao.Geo2DAO;
import com.tdil.tuafesta.dao.Geo3DAO;
import com.tdil.tuafesta.dao.Geo4DAO;
import com.tdil.tuafesta.dao.ProfileVideoDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.BlobData;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo2Example;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo3Example;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Geo4Example;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalChange;
import com.tdil.tuafesta.model.ProfileVideo;
import com.tdil.tuafesta.model.ProfileVideoExample;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;
import com.tdil.tuafesta.utils.BlobHelper;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class EditProfesionalBusinessDataForm extends TransactionalValidationForm implements GeoLevelSelectionForm, AjaxUploadHandlerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	
	private String businessname;
	private String cuit;
	private String iibb;
	
	private int geo2Id;
	private int geo3Id;
	private int geo4Id;
	
	private List<Geo2> level2;
	private List<Geo3> level3;
	private List<Geo4> level4;
	
	private int imageId;
	private UploadData logo;
	
	private String description;

	private String video1;
	private String video2;
	private String video3;
	private String video4;
	private String video5;
	
	public static String logo_key = "EditProfesionalBusinessDataForm.logo";
	
	private static final int MAX_LOGO_SIZE = 1000000;
	
	private static final Logger LOG = LoggerProvider.getLogger(EditProfesionalBusinessDataForm.class);
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.imageId = 0;
		this.logo = null;
		
		this.description = null;

		this.video1 = null;
		this.video2 = null;
		this.video3 = null;
		this.video4 = null;
		this.video5 = null;

	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}

	public void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError error,
			Map<String, Object> result) {
		FileItem uploaded = fileItems.get(AjaxFileUploadAction.UPLOAD_NAME);
		UploadData uploadData = FieldValidation.validateFileItem(uploaded, logo_key, true, error);
		if (uploadData != null) {
			long fileSize = uploaded.getSize();
			if (fileSize > MAX_LOGO_SIZE) {
				error.setFieldError(logo_key, ValidationErrors.TOO_BIG);
				return;
			}
			this.setLogo(uploadData);
		}
		result.put("result", "OK");
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		Geo2DAO geo2dao = DAOManager.getGeo2DAO();
		Geo2Example geo2Example = new Geo2Example();
		geo2Example.setOrderByClause("nombre");
		setLevel2(geo2dao.selectGeo2ByExample(geo2Example));
		
		Profesional profesional = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(id);
		ProfesionalChange profesionalChange = DAOManager.getProfesionalChangeDAO().selectProfesionalChangeByPrimaryKey(profesional.getIdProfesionalChange());
		setBusinessname(com.tdil.utils.StringUtils.nvl(profesionalChange.getBusinessname(), profesional.getBusinessname()));
		setCuit(com.tdil.utils.StringUtils.nvl(profesionalChange.getCuit(), profesional.getCuit()));
		setIibb(com.tdil.utils.StringUtils.nvl(profesionalChange.getIibb(), profesional.getIibb()));
		
		int idgeo4 = 0;
		if (profesionalChange.getIdGeolevel() != null && profesionalChange.getIdGeolevel() != 0) {
			idgeo4 = profesionalChange.getIdGeolevel();
		} else {
			idgeo4 = profesional.getIdGeolevel();
		}
		GeoLevelValueObject geoLevelValueObject = DAOManager.getGeo4DAO().selectGeoLevelsByGeo4(idgeo4);
		setGeo2Id(geoLevelValueObject.getGeo2id());
		setGeo3Id(geoLevelValueObject.getGeo3id());
		setGeo4Id(geoLevelValueObject.getGeo4id());
		
		// TODO logo
		if (profesionalChange.getIdProfilePicture() != null && profesionalChange.getIdProfilePicture() != 0) {
			this.imageId = profesionalChange.getIdProfilePicture();
		} else {
			if (profesional.getIdProfilePicture() != null && profesional.getIdProfilePicture() != 0) {
				this.imageId = profesional.getIdProfilePicture();
			}
		}
		if (this.imageId != 0) {
			BlobData frontCover = blobDataDAO.selectBlobDataByPrimaryKey(this.imageId);
			this.setLogo(new UploadData(frontCover.getFilename(), frontCover.getContent(), false));
		}
		
		setDescription(com.tdil.utils.StringUtils.nvl(profesionalChange.getDescription(), profesional.getDescription()));
		
		ProfileVideoExample approvedExample = new ProfileVideoExample();
		approvedExample.createCriteria().andIdProfesionalEqualTo(profesional.getId()).andApprovedEqualTo(1);
		approvedExample.setOrderByClause("orderNumber");
		List<ProfileVideo> approved = DAOManager.getProfileVideoDAO().selectProfileVideoByExample(approvedExample);
		
		ProfileVideoExample pendingExample = new ProfileVideoExample();
		pendingExample.createCriteria().andIdProfesionalEqualTo(profesional.getId()).andApprovedEqualTo(0);
		pendingExample.setOrderByClause("orderNumber");
		List<ProfileVideo> pending = DAOManager.getProfileVideoDAO().selectProfileVideoByExample(pendingExample);
		if (pending.isEmpty()) {
			if (approved.size() > 0) {
				setVideo1(approved.get(0).getUrl());
			}
			if (approved.size() > 1) {
				setVideo2(approved.get(1).getUrl());
			}
			if (approved.size() > 2) {
				setVideo3(approved.get(2).getUrl());
			}
			if (approved.size() > 3) {
				setVideo4(approved.get(3).getUrl());
			}
			if (approved.size() > 4) {
				setVideo5(approved.get(4).getUrl());
			}
		} else {
			if (pending.size() > 0) {
				setVideo1(pending.get(0).getUrl());
			}
			if (pending.size() > 1) {
				setVideo2(pending.get(1).getUrl());
			}
			if (pending.size() > 2) {
				setVideo3(pending.get(2).getUrl());
			}
			if (pending.size() > 3) {
				setVideo4(pending.get(3).getUrl());
			}
			if (approved.size() > 4) {
				setVideo5(pending.get(4).getUrl());
			}
		}
		
	}
	
	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getBusinessname(), ProfesionalForm.businessname_key, 400, false, validationError);
		FieldValidation.validateText(this.getCuit(), ProfesionalForm.cuit_key, 400, false, validationError);
		FieldValidation.validateText(this.getIibb(), ProfesionalForm.iibb_key, 400, false, validationError);
		
		FieldValidation.validateText(this.getDescription(), ProfesionalForm.description_key, 4000, false, validationError);
		FieldValidation.validateId(geo2Id, ProfesionalForm.geo2_key, validationError);
		FieldValidation.validateId(geo3Id, ProfesionalForm.geo3_key, validationError);
		FieldValidation.validateId(geo4Id, ProfesionalForm.geo4_key, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		Profesional profesional = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(id);
		ProfesionalChange profesionalChange = DAOManager.getProfesionalChangeDAO().selectProfesionalChangeByPrimaryKey(profesional.getIdProfesionalChange());
		
		profesionalChange.setBusinessname(com.tdil.utils.StringUtils.getDataForChange(this.getBusinessname(), profesional.getBusinessname()));
		profesionalChange.setCuit(com.tdil.utils.StringUtils.getDataForChange(this.getCuit(), profesional.getCuit()));
		profesionalChange.setIibb(com.tdil.utils.StringUtils.getDataForChange(this.getIibb(), profesional.getIibb()));
		profesionalChange.setDescription(com.tdil.utils.StringUtils.getDataForChange(this.getDescription(), profesional.getDescription()));
		profesionalChange.setIdGeolevel(com.tdil.utils.StringUtils.getDataForChange(this.getGeo4Id(), profesional.getIdGeolevel()));
		
		if (logo.isModified()) {
			if (imageId != 0) {
				BlobHelper.deleteBlob(imageId);
			}
			int id = BlobHelper.insertBlob(this.getLogo());
			profesionalChange.setIdProfilePicture(id);
		}
		DAOManager.getProfesionalChangeDAO().updateProfesionalChangeByPrimaryKey(profesionalChange);
		
		// Manejo de videos
		ProfileVideoExample approvedExample = new ProfileVideoExample();
		approvedExample.createCriteria().andIdProfesionalEqualTo(profesional.getId()).andApprovedEqualTo(1);
		approvedExample.setOrderByClause("orderNumber");
		
		ProfileVideoExample pendingExample = new ProfileVideoExample();
		pendingExample.createCriteria().andIdProfesionalEqualTo(profesional.getId()).andApprovedEqualTo(0);
		ProfileVideoDAO profileVideoDAO = DAOManager.getProfileVideoDAO();
		profileVideoDAO.deleteProfileVideoByExample(pendingExample);
		
		List<ProfileVideo> oldVideos = profileVideoDAO.selectProfileVideoByExample(approvedExample);
		List<String> old = new ArrayList<String>();
		for (ProfileVideo profileVideo : oldVideos) {
			old.add(profileVideo.getUrl());
		}
		boolean changed = false;
		boolean changedOrder = false;
		if (StringUtils.isEmpty(this.getVideo1()) || !old.contains(this.getVideo1())) {
			changed = true;
			if (old.size() <= 0 || !old.get(0).equals(this.getVideo1())) {
				changedOrder = true;
			}
		}
		if (StringUtils.isEmpty(this.getVideo2()) || !old.contains(this.getVideo2())) {
			changed = true;
			if (old.size() <= 1 || !old.get(1).equals(this.getVideo2())) {
				changedOrder = true;
			}
		}
		if (StringUtils.isEmpty(this.getVideo3()) || !old.contains(this.getVideo3())) {
			changed = true;
			if (old.size() <= 2 || !old.get(2).equals(this.getVideo3())) {
				changedOrder = true;
			}
		}
		if (StringUtils.isEmpty(this.getVideo4()) || !old.contains(this.getVideo4())) {
			changed = true;
			if (old.size() <= 3 || !old.get(3).equals(this.getVideo4())) {
				changedOrder = true;
			}
		}
		if (StringUtils.isEmpty(this.getVideo5()) || !old.contains(this.getVideo5())) {
			changed = true;
			if (old.size() <= 4 || !old.get(4).equals(this.getVideo5())) {
				changedOrder = true;
			}
		}
		List<String> newVideosUrls = new ArrayList<String>();
		if (!StringUtils.isEmpty(this.getVideo1())) {
			newVideosUrls.add(this.getVideo1());
		}
		if (!StringUtils.isEmpty(this.getVideo2())) {
			newVideosUrls.add(this.getVideo2());
		}
		if (!StringUtils.isEmpty(this.getVideo3())) {
			newVideosUrls.add(this.getVideo3());
		}
		if (!StringUtils.isEmpty(this.getVideo4())) {
			newVideosUrls.add(this.getVideo4());
		}
		if (!StringUtils.isEmpty(this.getVideo5())) {
			newVideosUrls.add(this.getVideo5());
		}
		if (changed) {
			int order = 0;
			for (String newUrl : newVideosUrls) {
				ProfileVideo profileVideo = new ProfileVideo();
				profileVideo.setApproved(0);
				profileVideo.setDeleted(0);
				profileVideo.setIdProfesional(profesional.getId());
				profileVideo.setOrdernumber(order);
				profileVideo.setUrl(newUrl);
				profileVideoDAO.insertProfileVideo(profileVideo);
				order = order + 1;
			}
		} else {
			// si cambio el orden, cambiarlos los persistidos y aprobados
			if (changedOrder) {
				profileVideoDAO.deleteProfileVideoByExample(approvedExample);
				int order = 0;
				for (String newUrl : newVideosUrls) {
					ProfileVideo profileVideo = new ProfileVideo();
					profileVideo.setApproved(1);
					profileVideo.setDeleted(0);
					profileVideo.setIdProfesional(profesional.getId());
					profileVideo.setOrdernumber(order);
					profileVideo.setUrl(newUrl);
					profileVideoDAO.insertProfileVideo(profileVideo);
					order = order + 1;
				}
			}
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

	private static Logger getLog() {
		return LoggerProvider.getLogger(EditProfesionalBusinessDataForm.class);
	}
	public String getBusinessname() {
		return businessname;
	}
	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getIibb() {
		return iibb;
	}
	public void setIibb(String iibb) {
		this.iibb = iibb;
	}
	public int getGeo2Id() {
		return geo2Id;
	}
	public void setGeo2Id(int geo2Id) {
		this.geo2Id = geo2Id;
	}
	public int getGeo3Id() {
		return geo3Id;
	}
	public void setGeo3Id(int geo3Id) {
		this.geo3Id = geo3Id;
	}
	public int getGeo4Id() {
		return geo4Id;
	}
	public void setGeo4Id(int geo4Id) {
		this.geo4Id = geo4Id;
	}
	public List<Geo2> getLevel2() {
		return level2;
	}
	public void setLevel2(List<Geo2> level2) {
		this.level2 = level2;
	}
	public List<Geo3> getLevel3() {
		if (geo2Id != 0) {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						Geo3DAO geo3dao = DAOManager.getGeo3DAO();
						Geo3Example geo3Example = new Geo3Example();
						geo3Example.createCriteria().andGeo2IdEqualTo(EditProfesionalBusinessDataForm.this.geo2Id);
						geo3Example.setOrderByClause("nombre");
						EditProfesionalBusinessDataForm.this.setLevel3(geo3dao.selectGeo3ByExample(geo3Example));
					}
				});
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		} else {
			setLevel3(new ArrayList<Geo3>());
		}
		return level3;
	}
	public void setLevel3(List<Geo3> level3) {
		this.level3 = level3;
	}
	public List<Geo4> getLevel4() {
		if (geo3Id != 0) {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						Geo4DAO geo4dao = DAOManager.getGeo4DAO();
						Geo4Example geo4Example = new Geo4Example();
						geo4Example.createCriteria().andGeo3IdEqualTo(EditProfesionalBusinessDataForm.this.geo3Id);
						geo4Example.setOrderByClause("nombre");
						EditProfesionalBusinessDataForm.this.setLevel4(geo4dao.selectGeo4ByExample(geo4Example));
					}
				});
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		} else {
			setLevel4(new ArrayList<Geo4>());
		}
		return level4;
	}
	public void setLevel4(List<Geo4> level4) {
		this.level4 = level4;
	}
	public UploadData getLogo() {
		return logo;
	}
	public void setLogo(UploadData logo) {
		this.logo = logo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVideo1() {
		return video1;
	}
	public void setVideo1(String video1) {
		this.video1 = video1;
	}
	public String getVideo2() {
		return video2;
	}
	public void setVideo2(String video2) {
		this.video2 = video2;
	}
	public String getVideo3() {
		return video3;
	}
	public void setVideo3(String video3) {
		this.video3 = video3;
	}
	public String getVideo4() {
		return video4;
	}
	public void setVideo4(String video4) {
		this.video4 = video4;
	}
	public String getVideo5() {
		return video5;
	}
	public void setVideo5(String video5) {
		this.video5 = video5;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

}
