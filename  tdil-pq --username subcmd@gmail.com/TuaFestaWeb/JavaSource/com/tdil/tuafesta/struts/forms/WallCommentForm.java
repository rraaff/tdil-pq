package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.WallWrittingDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.WallWritting;
import com.tdil.validations.FieldValidation;

public class WallCommentForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int userId;
	private int wallId;
	private String content;
	
	private static final String userId_key = "WallCommentForm.userId";
	private static final String content_key = "WallCommentForm.content";
	
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
//		FieldValidation.validateText(this.getName(), name_key, 150, error);
//		FieldValidation.validateEmail(this.getEmail(), email_key, error);
		FieldValidation.validateText(this.getContent(), content_key, 4000, error);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {

	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(WallCommentForm.class);
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getWallId() {
		return wallId;
	}
	public void setWallId(int wallId) {
		this.wallId = wallId;
	}
	public void addWallComment() throws SQLException {
		WallWrittingDAO dao = DAOManager.getWallWrittingDAO();
		WallWritting wallWritting = new WallWritting();
		wallWritting.setIdWall(this.getWallId());
		wallWritting.setIdAuthor(this.getUserId());
		wallWritting.setApproved(0);
		wallWritting.setOriginaltext(this.getContent());
		wallWritting.setCreationdate(new Date());
		wallWritting.setResponsePending(1);
		wallWritting.setDeleted(0);
		dao.insertWallWritting(wallWritting);
	}

}
