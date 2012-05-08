package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.MilkaPhotoDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.MilkaPhoto;
import com.tdil.milka.model.valueobjects.MilkaPhotoValueObject;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ApproveDisapproveForm;
import com.tdil.struts.forms.TransactionalValidationForm;

public class MilkaPhotoAdministrationForm extends TransactionalValidationForm implements ApproveDisapproveForm {

	private int objectId;
	private boolean frontcover;
	private boolean showinhome;
	private int idBlobData;
	private String extBlobData;
	private String tags;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	private List<MilkaPhotoValueObject> approvalPending;

	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.frontcover = false;
		this.showinhome = false;
		this.idBlobData = 0;
		this.extBlobData = null;
		this.tags = null;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.frontcover = false;
		this.showinhome = false; 
	}

	@Override
	public void init() throws SQLException {
		setApprovalPending(DAOManager.getMilkaPhotoDAO().selectMilkaPhotoToApproveWithAuthor());
	}

	@Override
	public void initWith(int id) throws SQLException {
		MilkaPhotoDAO milkaPhotoDAO = DAOManager.getMilkaPhotoDAO();
		MilkaPhoto milkaPhoto = milkaPhotoDAO.selectMilkaPhotoByPrimaryKey(id);
		if (milkaPhoto != null) {
			this.objectId = id;
			this.frontcover = milkaPhoto.getFrontcover().equals(1);
			this.showinhome = milkaPhoto.getShowinhome().equals(1);
			this.idBlobData = milkaPhoto.getIdBlobData();
			this.extBlobData = milkaPhoto.getExtBlobData();
			this.tags = milkaPhoto.getTags();
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
		for (MilkaPhotoValueObject mpvo : getApprovalPending()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(1);
			}
		}
	}
	
	public void postDisapprove() {
		for (MilkaPhotoValueObject mpvo : getApprovalPending()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(2);
			}
		}
	}
	
	public void approve() throws SQLException, ValidationException {
		MilkaPhotoDAO milkaPhotoDAO = DAOManager.getMilkaPhotoDAO();
		MilkaPhoto milkaPhoto = milkaPhotoDAO.selectMilkaPhotoByPrimaryKey(this.getObjectId());
		milkaPhoto.setApproved(1);
		milkaPhoto.setPublishdate(new Date());
		milkaPhotoDAO.updateMilkaPhotoByPrimaryKey(milkaPhoto);
	}
	
	public void disapprove() throws SQLException, ValidationException {
		MilkaPhotoDAO milkaPhotoDAO = DAOManager.getMilkaPhotoDAO();
		MilkaPhoto milkaPhoto = milkaPhotoDAO.selectMilkaPhotoByPrimaryKey(this.getObjectId());
		milkaPhoto.setApproved(2);
		milkaPhotoDAO.updateMilkaPhotoByPrimaryKey(milkaPhoto);
	}


	private static Logger getLog() {
		return LoggerProvider.getLogger(MilkaPhotoAdministrationForm.class);
	}
	public List<MilkaPhotoValueObject> getApprovalPending() {
		return approvalPending;
	}
	public void setApprovalPending(List<MilkaPhotoValueObject> approvalPending) {
		this.approvalPending = approvalPending;
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

}
