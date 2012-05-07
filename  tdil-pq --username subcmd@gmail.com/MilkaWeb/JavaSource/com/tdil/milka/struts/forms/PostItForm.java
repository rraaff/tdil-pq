package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.dao.AuthorDAO;
import com.tdil.milka.dao.ClickCounterDAO;
import com.tdil.milka.dao.PostItDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.Author;
import com.tdil.milka.model.ClickCounter;
import com.tdil.milka.model.PostIt;
import com.tdil.milka.model.valueobjects.AuthorValueObject;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;

public class PostItForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private AuthorValueObject authorBean;
	private String text;
	
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

	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		PostItDAO postItDAO = DAOManager.getPostItDAO();
		AuthorDAO authorDAO = DAOManager.getAuthorDAO();
		ClickCounterDAO clickCounterDAO = DAOManager.getClickCounterDAO();
		ClickCounter clickCounter = new ClickCounter();
		clickCounter.setClicks(0);
		clickCounter.setDeleted(0);
		int clickCounterId = clickCounterDAO.insertClickCounter(clickCounter);
		Author author = getAuthorBean().asAuthor();
		int authorId = authorDAO.insertAuthor(author);
		PostIt postIt = new PostIt();
		postIt.setApproved(0);
		postIt.setCreationdate(new Date());
		postIt.setDescription(this.getText());
		postIt.setDeleted(0);
		postIt.setIdAuthor(authorId);
		postIt.setIdClickCounter(clickCounterId);
		postItDAO.insertPostIt(postIt);
	}	


	private static Logger getLog() {
		return LoggerProvider.getLogger(PostItForm.class);
	}
	public AuthorValueObject getAuthorBean() {
		if (authorBean == null) {
			authorBean = new AuthorValueObject();
		}
		return authorBean;
	}
	public void setAuthorBean(AuthorValueObject authorBean) {
		this.authorBean = authorBean;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
