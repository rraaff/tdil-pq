package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.WallWrittingDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.WallWritting;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.ApproveDisapproveForm;
import com.tdil.struts.forms.TransactionalValidationForm;

public class PapapediaAdministrationForm extends TransactionalValidationForm implements ApproveDisapproveForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int objectId;
	private String originaltext;
	
	private List<WallWritting> sourceList;
	
	
	@Override
	public void reset() throws SQLException {
		objectId = 0;
		originaltext = null;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}
	
	public void initForApprove() throws SQLException {
		setSourceList(DAOManager.getWallWrittingDAO().selectPapapediaToApprove());
	}
	
	public void initForReview() throws SQLException {
		setSourceList(DAOManager.getWallWrittingDAO().selectPapapediaToReview());
	}

	@Override
	public void initWith(int id) throws SQLException {
		WallWrittingDAO wallWrittingDAO = DAOManager.getWallWrittingDAO();
		WallWritting postIt = wallWrittingDAO.selectWallWrittingByPrimaryKey(id);
		if (postIt != null) {
			this.objectId = id;
			this.originaltext = postIt.getOriginaltext();
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
		for (WallWritting mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(1);
			}
		}
	}
	
	public void postDisapprove() {
		for (WallWritting mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(2);
			}
		}
	}
	
	public void approve() throws SQLException, ValidationException {
		WallWrittingDAO postItDAO = DAOManager.getWallWrittingDAO();
		WallWritting postIt = postItDAO.selectWallWrittingByPrimaryKey(this.getObjectId());
		postIt.setApproved(1);
		postIt.setPublishdate(new Date());
		postItDAO.updateWallWrittingByPrimaryKey(postIt);
	}
	
	public void disapprove() throws SQLException, ValidationException {
		WallWrittingDAO postItDAO = DAOManager.getWallWrittingDAO();
		WallWritting postIt = postItDAO.selectWallWrittingByPrimaryKey(this.getObjectId());
		postIt.setApproved(2);
		postIt.setPublishdate(new Date());
		postItDAO.updateWallWrittingByPrimaryKey(postIt);
	}
	

	private static Logger getLog() {
		return LoggerProvider.getLogger(PapapediaAdministrationForm.class);
	}
	public List<WallWritting> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<WallWritting> approvalPending) {
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

}
