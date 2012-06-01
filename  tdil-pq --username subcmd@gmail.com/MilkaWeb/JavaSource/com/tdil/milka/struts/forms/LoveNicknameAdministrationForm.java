package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.LoveNicknamesDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.LoveNicknames;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ApproveDisapproveForm;
import com.tdil.struts.forms.TransactionalValidationForm;

public class LoveNicknameAdministrationForm extends TransactionalValidationForm implements ApproveDisapproveForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int objectId;
	private String originaltext;
	private String sex;
	private String position;
	
	private List<LoveNicknames> sourceList;
	
	
	@Override
	public void reset() throws SQLException {
		objectId = 0;
		originaltext = null;
		sex = null;
		position = null;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}
	
	public void initForApprove() throws SQLException {
		setSourceList(DAOManager.getLoveNicknamesDAO().selectLoveNicknamesToApprove());
	}
	
	public void initForReview() throws SQLException {
		setSourceList(DAOManager.getLoveNicknamesDAO().selectLoveNicknamesToReview());
	}

	@Override
	public void initWith(int id) throws SQLException {
		LoveNicknamesDAO wallWrittingDAO = DAOManager.getLoveNicknamesDAO();
		LoveNicknames postIt = wallWrittingDAO.selectLoveNicknamesByPrimaryKey(id);
		if (postIt != null) {
			this.objectId = id;
			this.originaltext = postIt.getOriginaltext();
			this.sex = postIt.getSex();
			this.position = postIt.getPosition();
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
		for (LoveNicknames mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(1);
			}
		}
	}
	
	public void postDisapprove() {
		for (LoveNicknames mpvo : getSourceList()) {
			if (mpvo.getId().equals(this.getObjectId())) {
				mpvo.setApproved(2);
			}
		}
	}
	
	public void approve() throws SQLException, ValidationException {
		LoveNicknamesDAO postItDAO = DAOManager.getLoveNicknamesDAO();
		LoveNicknames postIt = postItDAO.selectLoveNicknamesByPrimaryKey(this.getObjectId());
		postIt.setApproved(1);
		postIt.setPublishdate(new Date());
		postItDAO.updateLoveNicknamesByPrimaryKey(postIt);
	}
	
	public void disapprove() throws SQLException, ValidationException {
		LoveNicknamesDAO postItDAO = DAOManager.getLoveNicknamesDAO();
		LoveNicknames postIt = postItDAO.selectLoveNicknamesByPrimaryKey(this.getObjectId());
		postIt.setApproved(2);
		postIt.setPublishdate(new Date());
		postItDAO.updateLoveNicknamesByPrimaryKey(postIt);
	}
	

	private static Logger getLog() {
		return LoggerProvider.getLogger(LoveNicknameAdministrationForm.class);
	}
	public List<LoveNicknames> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<LoveNicknames> approvalPending) {
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

}
