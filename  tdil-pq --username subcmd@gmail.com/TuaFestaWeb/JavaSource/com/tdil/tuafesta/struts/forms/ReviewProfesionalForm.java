package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.tuafesta.dao.BlobDataDAO;
import com.tdil.tuafesta.dao.ProfesionalDAO;
import com.tdil.tuafesta.dao.SellDAO;
import com.tdil.tuafesta.dao.SellMediaDAO;
import com.tdil.tuafesta.dao.ServiceAreaDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.BlobData;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalChange;
import com.tdil.tuafesta.model.ProfesionalStatus;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellExample;
import com.tdil.tuafesta.model.SellMedia;
import com.tdil.tuafesta.model.SellMediaExample;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.ServiceArea;
import com.tdil.tuafesta.model.ServiceAreaExample;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.tuafesta.struts.forms.beans.PublicImageBlobBean;
import com.tdil.tuafesta.utils.ProfesionalUtils;
import com.tdil.tuafesta.web.EmailUtils;

import static com.tdil.tuafesta.struts.forms.EditProfesionalPersonalDataForm.approvePersonalData;
import static com.tdil.tuafesta.struts.forms.EditProfesionalBusinessDataForm.approveBusinessData;

public class ReviewProfesionalForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private int id;
	private int objectId;
	private boolean personalDataModified;
	private boolean businessDataModified;
	private boolean sellsModified;
	
	private Profesional profesional;
	private String disapproveReason;
	
	private GeoLevelValueObject location;
	private GeoLevelValueObject changeLocation;
	
	private int changeImageId;
	private UploadData changeLogo;

	private ProfesionalChange profesionalChange;
	
	private List<SellValueObject> sells = new ArrayList<SellValueObject>();
	
	private SellValueObject sellValueObject;
	private SellMedia sellMedia;
	
	private String disapproveMotive;
	
	private static final Logger LOG = LoggerProvider.getLogger(ReviewProfesionalForm.class);
	
	@Override
	public void reset() throws SQLException {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		
		setProfesional(profesionalDAO.selectProfesionalByPrimaryKey(id));
		
		profesionalChange = DAOManager.getProfesionalChangeDAO().selectProfesionalChangeByPrimaryKey(getProfesional().getIdProfesionalChange());
		personalDataModified = ProfesionalUtils.personalDataChanged(profesionalChange);
		businessDataModified = ProfesionalUtils.businessDataChanged(profesionalChange);
		SellExample sellExample = new SellExample();
		sellExample.createCriteria().andIdProfesionalEqualTo(id).andApprovedEqualTo(0);
		sellsModified = DAOManager.getSellDAO().countSellByExample(sellExample) > 0;
		
		location = DAOManager.getGeo4DAO().selectGeoLevelsByGeo4(profesional.getIdGeolevel());
		if (profesionalChange.getIdGeolevel() != null && profesionalChange.getIdGeolevel() != 0) {
			changeLocation = DAOManager.getGeo4DAO().selectGeoLevelsByGeo4(profesionalChange.getIdGeolevel());
		} else {
			changeLocation = null;
		}
		
		if (profesionalChange.getIdProfilePicture() != null && profesionalChange.getIdProfilePicture() != 0) {
			this.changeImageId = profesionalChange.getIdProfilePicture();
		} else {
			this.changeImageId = 0;
		}
		if (this.changeImageId != 0) {
			BlobData frontCover = blobDataDAO.selectBlobDataByPrimaryKey(this.changeImageId);
			this.setChangeLogo(new UploadData(frontCover.getFilename(), frontCover.getContent(), false));
		} else {
			this.setChangeLogo(null);
		}
		
		sells = DAOManager.getSellDAO().selectProductSellsByProfesional(id);
		sells.addAll(DAOManager.getSellDAO().selectServiceSellsByProfesional(id));
	}
	
	public List<PublicImageBlobBean> getMedia() {
		List<PublicImageBlobBean> result = new ArrayList<PublicImageBlobBean>();
		if (sellMedia != null) {
			if (sellMedia.getIdBlobData1() != null && sellMedia.getIdBlobData1() != 0) {
				result.add(new PublicImageBlobBean(sellMedia.getIdBlobData1(), sellMedia.getExtBlobData1()));
			}
			if (sellMedia.getIdBlobData2() != null && sellMedia.getIdBlobData2() != 0) {
				result.add(new PublicImageBlobBean(sellMedia.getIdBlobData2(), sellMedia.getExtBlobData2()));
			}
			if (sellMedia.getIdBlobData3() != null && sellMedia.getIdBlobData3() != 0) {
				result.add(new PublicImageBlobBean(sellMedia.getIdBlobData3(), sellMedia.getExtBlobData3()));
			}
			if (sellMedia.getIdBlobData4() != null && sellMedia.getIdBlobData4() != 0) {
				result.add(new PublicImageBlobBean(sellMedia.getIdBlobData4(), sellMedia.getExtBlobData4()));
			}
			if (sellMedia.getIdBlobData5() != null && sellMedia.getIdBlobData5() != 0) {
				result.add(new PublicImageBlobBean(sellMedia.getIdBlobData5(), sellMedia.getExtBlobData5()));
			}
			
		}
		return result;
		
	}
	
	public boolean hasMedia() {
		return sellMedia != null;
	}
	
	public void initWith(int type, int id) throws SQLException {
		if (type == SellType.PRODUCT) {
			sellValueObject = DAOManager.getSellDAO().selectSellProductValueObjectNoFilter(id);
		} else {
			if (type == SellType.SERVICE) {
				sellValueObject = DAOManager.getSellDAO().selectSellServiceValueObjectNoFilter(id);
			} else {
				Sell sell = DAOManager.getSellDAO().selectSellByPrimaryKey(id);
				if (sell.getType().equals(SellType.PRODUCT)) {
					sellValueObject = DAOManager.getSellDAO().selectSellProductValueObjectNoFilter(id);
				} else {
					sellValueObject = DAOManager.getSellDAO().selectSellServiceValueObjectNoFilter(id);
				}
			}
		}
		SellMediaDAO sellMediaDAO = DAOManager.getSellMediaDAO();
		SellMediaExample sellMediaExample = new SellMediaExample();
		sellMediaExample.createCriteria().andIdSellEqualTo(id).andApprovedEqualTo(1);
		List<SellMedia> result = sellMediaDAO.selectSellMediaByExample(sellMediaExample);
		if (!result.isEmpty()) {
			sellMedia = result.get(0);
		} else {
			sellMedia = null;
		}
	}

	@Override
	public void basicValidate(ValidationError validationError) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
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

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public ValidationError validateForDisapprove() {
		// TODO Auto-generated method stub
		return null;
	}

	public void disapprove() throws SQLException {
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		Profesional profesional = profesionalDAO.selectProfesionalByPrimaryKey(this.getProfesional().getId());
		profesional.setReviewdate(new Date());
		profesional.setDisapprovereason(this.getDisapproveReason());
		profesional.setStatus(ProfesionalStatus.DISAPPROVED);
		profesionalDAO.updateProfesionalByPrimaryKey(profesional);
	}

	public ValidationError validateForApprove() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void approve() throws SQLException {
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		Profesional profesional = profesionalDAO.selectProfesionalByPrimaryKey(this.getProfesional().getId());
		profesional.setReviewdate(new Date());
		profesional.setStatus(ProfesionalStatus.APPROVED);
		profesionalDAO.updateProfesionalByPrimaryKey(profesional);
		
	}
	
	public void approvePersonalDataChange() throws SQLException {
		approvePersonalData(profesional, profesionalChange);
	}
	
	public void approveBusinessDataChange() throws SQLException {
		approveBusinessData(profesional, profesionalChange);
	}
	
	public void approveSell() throws SQLException {
		Sell sell = DAOManager.getSellDAO().selectSellByPrimaryKey(this.getSellValueObject().getId());
		sell.setApproved(1);
		DAOManager.getSellDAO().updateSellByPrimaryKey(sell);
	}
	
	public String getDisapproveReason() {
		return disapproveReason;
	}

	public void setDisapproveReason(String disapproveReason) {
		this.disapproveReason = disapproveReason;
	}

	public void validateEmailManual() throws SQLException {
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		Profesional profesional = profesionalDAO.selectProfesionalByPrimaryKey(this.getProfesional().getId());
		profesional.setEmailvalid(1);
		profesional.setVerifemail(null);
		profesional.setStatus(ProfesionalStatus.VERIFICATION_PENDING);
		profesionalDAO.updateProfesionalByPrimaryKey(profesional);
	}

	public void blockProfesional() throws SQLException {
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		Profesional profesional = profesionalDAO.selectProfesionalByPrimaryKey(this.getProfesional().getId());
		profesional.setStatus(ProfesionalStatus.BLOCKED);
		profesionalDAO.updateProfesionalByPrimaryKey(profesional);
	}

	public void verify() throws SQLException {
		// TODO Auto-generated method stub
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		Profesional profesional = profesionalDAO.selectProfesionalByPrimaryKey(this.getProfesional().getId());
		profesional.setReviewdate(new Date());
		profesional.setStatus(ProfesionalStatus.APPROVED);
		profesionalDAO.updateProfesionalByPrimaryKey(profesional);
		// TODO a esto le falta aprobar los datos puntuales, le falta el tema de los productos no rd etc etc
		SellDAO sellDao = DAOManager.getSellDAO();
		SellExample sellExample = new SellExample();
		sellExample.createCriteria().andIdProfesionalEqualTo(this.getProfesional().getId());
		for (Sell sell : sellDao.selectSellByExample(sellExample)) {
			sell.setApproved(1);
			sellDao.updateSellByPrimaryKey(sell);
		}
		ServiceAreaDAO serviceAreaDAO = DAOManager.getServiceAreaDAO();
		ServiceAreaExample serviceAreaExample = new ServiceAreaExample();
		serviceAreaExample.createCriteria().andIdProfesionalEqualTo(this.getProfesional().getId());
		for (ServiceArea serviceArea : serviceAreaDAO.selectServiceAreaByExample(serviceAreaExample)) {
			serviceArea.setApproved(1);
			serviceAreaDAO.updateServiceAreaByPrimaryKey(serviceArea);
		}
	}

	public boolean isPersonalDataModified() {
		return personalDataModified;
	}

	public void setPersonalDataModified(boolean personalDataModified) {
		this.personalDataModified = personalDataModified;
	}

	public boolean isBusinessDataModified() {
		return businessDataModified;
	}

	public void setBusinessDataModified(boolean businessDataModified) {
		this.businessDataModified = businessDataModified;
	}

	public boolean isSellsModified() {
		return sellsModified;
	}
	
	public boolean isCurrentSellApproved() {
		return sellValueObject.getApproved() != null && sellValueObject.getApproved().equals(1);
	}

	public void setSellsModified(boolean sellsModifiedModified) {
		this.sellsModified = sellsModifiedModified;
	}

	public ProfesionalChange getProfesionalChange() {
		return profesionalChange;
	}

	public void setProfesionalChange(ProfesionalChange profesionalChange) {
		this.profesionalChange = profesionalChange;
	}

	public GeoLevelValueObject getLocation() {
		return location;
	}

	public void setLocation(GeoLevelValueObject location) {
		this.location = location;
	}

	public GeoLevelValueObject getChangeLocation() {
		return changeLocation;
	}

	public void setChangeLocation(GeoLevelValueObject changeLocation) {
		this.changeLocation = changeLocation;
	}

	public UploadData getChangeLogo() {
		return changeLogo;
	}

	public void setChangeLogo(UploadData changeLogo) {
		this.changeLogo = changeLogo;
	}

	public List<SellValueObject> getSells() {
		return sells;
	}

	public void setSells(List<SellValueObject> sells) {
		this.sells = sells;
	}

	public SellValueObject getSellValueObject() {
		return sellValueObject;
	}

	public void setSellValueObject(SellValueObject sellValueObject) {
		this.sellValueObject = sellValueObject;
	}

	public SellMedia getSellMedia() {
		return sellMedia;
	}

	public void setSellMedia(SellMedia sellMedia) {
		this.sellMedia = sellMedia;
	}

	public void disapproveBusinessDataChange() throws SQLException {
		profesionalChange.setBusinessname(null);
		profesionalChange.setCuit(null);
		profesionalChange.setIibb(null);
		profesionalChange.setDescription(null);
		profesionalChange.setFacebook(null);
		profesionalChange.setWebsite(null);
		profesionalChange.setBusinesshours(null);
		profesionalChange.setIdGeolevel(null);
		profesionalChange.setIdProfilePicture(null);
		profesionalChange.setExtProfilePicture(null);
		profesionalChange.setVideo1(null);
		profesionalChange.setVideo2(null);
		profesionalChange.setVideo3(null);
		profesionalChange.setVideo4(null);
		profesionalChange.setVideo5(null);
		Profesional prof = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(this.getProfesional().getId());
		prof.setDatachanged(ProfesionalUtils.dataChanged(profesionalChange) ? 1 : 0);
		DAOManager.getProfesionalDAO().updateProfesionalByPrimaryKey(prof);
		DAOManager.getProfesionalChangeDAO().updateProfesionalChangeByPrimaryKey(profesionalChange);
		try {
			/** Inicio del email */
			Map<String, String> params = new HashMap<String, String>();
			params.put(EmailUtils.MOTIVE_KEY, this.getDisapproveMotive());
			EmailUtils.sendEmail(this.getProfesional().getEmail(), params, EmailUtils.DISAPPROVE_BUSINESS);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void disapprovePersonalDataChange() throws SQLException {
		profesionalChange.setFirstname(null);
		profesionalChange.setLastname(null);
		profesionalChange.setSex(null);
		profesionalChange.setBirthdate(null);
		profesionalChange.setPhoneareacode(null);
		profesionalChange.setPhonenumber(null);
		profesionalChange.setPhoneextension(null);
		profesionalChange.setPhonetype(null);
		Profesional prof = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(this.getProfesional().getId());
		prof.setDatachanged(ProfesionalUtils.dataChanged(profesionalChange) ? 1 : 0);
		DAOManager.getProfesionalDAO().updateProfesionalByPrimaryKey(prof);
		DAOManager.getProfesionalChangeDAO().updateProfesionalChangeByPrimaryKey(profesionalChange);
		try {
			/** Inicio del email */
			Map<String, String> params = new HashMap<String, String>();
			params.put(EmailUtils.MOTIVE_KEY, this.getDisapproveMotive());
			EmailUtils.sendEmail(this.getProfesional().getEmail(), params, EmailUtils.DISAPPROVE_PERSONAL);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void disapproveSell() throws SQLException {
		DAOManager.getSellMediaDAO().deleteSellMediaByPrimaryKey(sellMedia.getId());
		DAOManager.getSellDAO().deleteSellByPrimaryKey(this.getSellValueObject().getId());
		if (sellMedia != null) {
			if (sellMedia.getIdBlobData1() != null && sellMedia.getIdBlobData1() != 0) {
				DAOManager.getBlobDataDAO().deleteBlobDataByPrimaryKey(sellMedia.getIdBlobData1());
			}
			if (sellMedia.getIdBlobData2() != null && sellMedia.getIdBlobData2() != 0) {
				DAOManager.getBlobDataDAO().deleteBlobDataByPrimaryKey(sellMedia.getIdBlobData2());
			}
			if (sellMedia.getIdBlobData3() != null && sellMedia.getIdBlobData3() != 0) {
				DAOManager.getBlobDataDAO().deleteBlobDataByPrimaryKey(sellMedia.getIdBlobData3());
			}
			if (sellMedia.getIdBlobData4() != null && sellMedia.getIdBlobData4() != 0) {
				DAOManager.getBlobDataDAO().deleteBlobDataByPrimaryKey(sellMedia.getIdBlobData4());
			}
			if (sellMedia.getIdBlobData5() != null && sellMedia.getIdBlobData5() != 0) {
				DAOManager.getBlobDataDAO().deleteBlobDataByPrimaryKey(sellMedia.getIdBlobData5());
			}
		}
		try {
			/** Inicio del email */
			Map<String, String> params = new HashMap<String, String>();
			params.put(EmailUtils.MOTIVE_KEY, this.getDisapproveMotive());
			params.put(EmailUtils.SELL_NAME_KEY, this.getSellValueObject().getName());
			EmailUtils.sendEmail(this.getProfesional().getEmail(), params, EmailUtils.DISAPPROVE_SELL);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public String getDisapproveMotive() {
		return disapproveMotive;
	}

	public void setDisapproveMotive(String disapproveMotive) {
		this.disapproveMotive = disapproveMotive;
	}


}
