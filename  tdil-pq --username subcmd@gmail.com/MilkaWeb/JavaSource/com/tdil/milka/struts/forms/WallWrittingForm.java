package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.WallWrittingDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.Wall;
import com.tdil.milka.model.WallExample;
import com.tdil.milka.model.WallFilter;
import com.tdil.milka.model.WallWritting;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.validations.FieldValidation;

public class WallWrittingForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private String walltype;
	private String text;
	
	private static final String text_key = "WallWrittingForm.text";
	
	@Override
	public void reset() throws SQLException {
		walltype = null;
		text = null;
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
		FieldValidation.validateText(this.getText(), text_key, 4000, error);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// todo validar que no este entre los ultimos posts???
	}

	@Override
	public void save() throws SQLException, ValidationException {
		WallExample wallExample = new WallExample();
		wallExample.createCriteria().andDescriptionEqualTo(walltype);
		Wall wall = DAOManager.getWallDAO().selectWallByExample(wallExample).get(0);
		WallWrittingDAO wallWrittingDAO = DAOManager.getWallWrittingDAO();
		WallWritting wallWritting = new WallWritting();
		wallWritting.setIdWall(wall.getId());
		if (WallFilter.approves(this.getText())) {
			wallWritting.setApproved(1);
			wallWritting.setPublishdate(new Date());
		} else {
			wallWritting.setApproved(0);
		}
		wallWritting.setCreationdate(new Date());
		wallWritting.setOriginaltext(this.getText());
		wallWritting.setDeleted(0);
		//postIt.setIdClickCounter(clickCounterId);
		wallWrittingDAO.insertWallWritting(wallWritting);
	}	

	private static Logger getLog() {
		return LoggerProvider.getLogger(WallWrittingForm.class);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getWallType() {
		return walltype;
	}
	public void setWallType(String wallId) {
		this.walltype = wallId;
	}

}
