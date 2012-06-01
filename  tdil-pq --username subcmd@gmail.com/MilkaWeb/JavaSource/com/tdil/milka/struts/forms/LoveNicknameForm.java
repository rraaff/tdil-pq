package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.ClickCounterDAO;
import com.tdil.milka.dao.LoveNicknamesDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.ClickCounter;
import com.tdil.milka.model.LoveNicknames;
import com.tdil.milka.model.WallFilter;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.validations.FieldValidation;

public class LoveNicknameForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private String text;
	private String sex;
	private String position;
	
	private static final String text_key = "LoveNicknameForm.text";
	private static final String position_key = "LoveNicknameForm.position";
	private static final String sex_key = "LoveNicknameForm.sex";
	
	@Override
	public void reset() throws SQLException {
		text = null;
		sex = null;
		position = null;
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
		FieldValidation.validateText(this.getText(), text_key, 150, error);
		FieldValidation.validateText(this.getPosition(), position_key, 25, error);
		FieldValidation.validateText(this.getSex(), sex_key, 1, error);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// todo validar que no este entre los ultimos posts???
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ClickCounterDAO clickCounterDAO = DAOManager.getClickCounterDAO();
		ClickCounter clickCounter = new ClickCounter();
		clickCounter.setClicks(0);
		clickCounter.setDeleted(0);
		int clickCounterId = clickCounterDAO.insertClickCounter(clickCounter);
		LoveNicknamesDAO loveNicknamesDAO = DAOManager.getLoveNicknamesDAO();
		LoveNicknames loveNicknames = new LoveNicknames();
		loveNicknames.setCreationdate(new Date());
		loveNicknames.setDeleted(0);
		loveNicknames.setIdClickCounter(clickCounterId);
		loveNicknames.setOriginaltext(this.getText());
		loveNicknames.setPosition(this.getPosition());
		loveNicknames.setSex(this.getSex());
		if (WallFilter.approves(this.getText())) {
			loveNicknames.setApproved(1);
			loveNicknames.setPublishdate(new Date());
		} else {
			loveNicknames.setApproved(0);
		}
		loveNicknamesDAO.insertLoveNicknames(loveNicknames);
	}	

	private static Logger getLog() {
		return LoggerProvider.getLogger(LoveNicknameForm.class);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
