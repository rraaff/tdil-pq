package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.WallWrittingDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.WallWritting;
import com.tdil.milka.utils.LinkHelper;
import com.tdil.milka.web.Experience;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.ApproveDisapproveForm;
import com.tdil.struts.forms.TransactionalValidationForm;

public class PapapediaAdministrationForm extends TransactionalValidationForm implements ApproveDisapproveForm, LinkAnchorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int objectId;
	private String originaltext;
	
	private String destinationType;
	private int destinationId;
	private String urlLink;
	private String urlTarget;
	
	private List<WallWritting> sourceList;
	
	
	@Override
	public void reset() throws SQLException {
		objectId = 0;
		originaltext = null;
	}
	
	public String getOriginType() {
		return Experience.PAPAPEDIA.name();
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
			this.urlLink = postIt.getUrlLink();
			this.urlTarget = postIt.getUrlTarget();
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
		setUrlLink(null);
		for (WallWritting mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(2);
			}
		}
	}
	
	public void approve() throws SQLException, ValidationException {
		WallWrittingDAO postItDAO = DAOManager.getWallWrittingDAO();
		WallWritting postIt = postItDAO.selectWallWrittingByPrimaryKey(this.getObjectId());
		// cambio el link
		if (!LinkHelper.areEquals(postIt.getUrlLink(), this.getUrlLink())) {
			LinkHelper.deleteActualLink(this.getOriginType(), postIt.getId());
			if (!StringUtils.isEmpty(this.getUrlLink())) {
				LinkHelper.createNewLink(this.getOriginType(), postIt.getId(), destinationType, destinationId);
			}
		}
		postIt.setApproved(1);
		postIt.setPublishdate(new Date());
		postIt.setUrlLink(this.getUrlLink());
		postIt.setUrlTarget(this.getUrlTarget());
		postItDAO.updateWallWrittingByPrimaryKey(postIt);
	}
	
	public void disapprove() throws SQLException, ValidationException {
		WallWrittingDAO postItDAO = DAOManager.getWallWrittingDAO();
		WallWritting postIt = postItDAO.selectWallWrittingByPrimaryKey(this.getObjectId());
		postIt.setApproved(2);
		postIt.setPublishdate(new Date());
		postIt.setUrlLink(null);
		postIt.setUrlTarget(this.getUrlTarget());
		postItDAO.updateWallWrittingByPrimaryKey(postIt);
		// cambio el link
		LinkHelper.deleteActualLink(this.getOriginType(), postIt.getId());
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
