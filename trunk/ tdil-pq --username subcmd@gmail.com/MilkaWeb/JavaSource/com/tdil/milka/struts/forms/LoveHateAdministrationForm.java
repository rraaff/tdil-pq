package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.LoveHateDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.LoveHate;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ApproveDisapproveForm;
import com.tdil.struts.forms.TransactionalValidationForm;

public class LoveHateAdministrationForm extends TransactionalValidationForm implements ApproveDisapproveForm  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int objectId;
	private String originaltext;
	private boolean love;
	
	private List<LoveHate> sourceList;
	
	
	@Override
	public void reset() throws SQLException {
		objectId = 0;
		originaltext = null;
		love = false;
	}
	

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		love = false;
	}

	@Override
	public void init() throws SQLException {
	}
	
	public void initForApprove() throws SQLException {
		setSourceList(DAOManager.getLoveHateDAO().selectLoveHateToApprove());
	}
	
	public void initForReview() throws SQLException {
		setSourceList(DAOManager.getLoveHateDAO().selectLoveHateToReview());
	}

	@Override
	public void initWith(int id) throws SQLException {
		LoveHateDAO wallWrittingDAO = DAOManager.getLoveHateDAO();
		LoveHate postIt = wallWrittingDAO.selectLoveHateByPrimaryKey(id);
		if (postIt != null) {
			this.objectId = id;
			this.originaltext = postIt.getContent();
			this.love = postIt.getLove().equals(1);
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
		for (LoveHate mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(1);
			}
		}
	}
	
	public void postDisapprove() {
		for (LoveHate mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(2);
			}
		}
	}
	
	public void approve() throws SQLException, ValidationException {
		LoveHateDAO postItDAO = DAOManager.getLoveHateDAO();
		LoveHate postIt = postItDAO.selectLoveHateByPrimaryKey(this.getObjectId());
		postIt.setApproved(1);
		postIt.setPublishdate(new Date());
		postItDAO.updateLoveHateByPrimaryKey(postIt);
	}
	
	public void disapprove() throws SQLException, ValidationException {
		LoveHateDAO postItDAO = DAOManager.getLoveHateDAO();
		LoveHate postIt = postItDAO.selectLoveHateByPrimaryKey(this.getObjectId());
		postIt.setApproved(2);
		postIt.setUrlLink(null);
		postIt.setPublishdate(new Date());
		postItDAO.updateLoveHateByPrimaryKey(postIt);
	}
	

	private static Logger getLog() {
		return LoggerProvider.getLogger(LoveHateAdministrationForm.class);
	}
	public List<LoveHate> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<LoveHate> approvalPending) {
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
	public boolean getLove() {
		return love;
	}
	public void setLove(boolean sex) {
		this.love = sex;
	}

}
