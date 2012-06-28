package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.ClickCounterDAO;
import com.tdil.milka.dao.LoveHateDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.ClickCounter;
import com.tdil.milka.model.LoveHate;
import com.tdil.milka.model.LoveHateExample;
import com.tdil.milka.model.WallFilter;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.validations.FieldValidation;

public class LoveHateForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private String text;
	private boolean love = true;
	
	private static final String text_key = "LoveHateForm.text";
	
	@Override
	public void reset() throws SQLException {
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
		FieldValidation.validateText(this.getText(), text_key, 150, error);
		//FieldValidation.validateText(this.getPosition(), position_key, 25, error);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// todo validar que no este entre los ultimos posts???
	}

	@Override
	public void save() throws SQLException, ValidationException {
		String text = StringUtils.capitalize(this.getText());
		LoveHateDAO loveHateDAO = DAOManager.getLoveHateDAO();
		LoveHateExample loveHateExample = new LoveHateExample();
		loveHateExample.createCriteria().andContentEqualTo(text).andLoveEqualTo(this.isLove() ? 1 : 0);
		List<LoveHate> result = loveHateDAO.selectLoveHateByExample(loveHateExample);
		if (result.isEmpty()) {
			ClickCounterDAO clickCounterDAO = DAOManager.getClickCounterDAO();
			ClickCounter clickCounter = new ClickCounter();
			clickCounter.setClicks(0);
			clickCounter.setDeleted(0);
			int clickCounterId = clickCounterDAO.insertClickCounter(clickCounter);
			LoveHate loveHate = new LoveHate();
			loveHate.setCreationdate(new Date());
			loveHate.setDeleted(0);
			loveHate.setIdClickCounter(clickCounterId);
			loveHate.setContent(text);
			loveHate.setLove(this.isLove() ? 1 : 0);
			if (WallFilter.approves(text)) {
				loveHate.setApproved(1);
				loveHate.setPublishdate(new Date());
			} else {
				loveHate.setApproved(0);
			}
			loveHate.setVotes(1);
			loveHateDAO.insertLoveHate(loveHate);
		} else {
			LoveHate loveHate = result.get(0);
			loveHate.setVotes(loveHate.getVotes() + 1);
			loveHateDAO.updateLoveHateByPrimaryKey(loveHate);
		}
	}	

	private static Logger getLog() {
		return LoggerProvider.getLogger(LoveHateForm.class);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLove() {
		return love;
	}
	public void setLove(boolean love) {
		this.love = love;
	}

}
