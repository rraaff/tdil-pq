package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tdil.djmag.dao.NewsletterDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Newsletter;
import com.tdil.djmag.model.NewsletterExample;
import com.tdil.djmag.model.NewsletterExample.Criteria;
import com.tdil.simon.actions.validations.FieldValidation;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;

public class SubscribeToNewsletterForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8206600031482405647L;

	private static String email_key = "SubscribeToNewsletterForm.email";
	private String email;

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateEmail(this.getEmail(), email_key, validationError);
	}

	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public void reset() throws SQLException {
		this.email = null;
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		NewsletterDAO newsletterDAO = DAOManager.getNewsletterDAO();
		NewsletterExample newsletterExample = new NewsletterExample();
		Criteria criteria = newsletterExample.createCriteria();
		criteria.andEmailEqualTo(this.getEmail());
		List<Newsletter> list = newsletterDAO.selectNewsletterByExample(newsletterExample);
		if (list.isEmpty()) {
			Newsletter newsletter = new Newsletter();
			newsletter.setEmail(this.getEmail());
			newsletter.setSubscriptiondate(new Date());
			newsletterDAO.insertNewsletter(newsletter);
		}
	}
	
	
}
