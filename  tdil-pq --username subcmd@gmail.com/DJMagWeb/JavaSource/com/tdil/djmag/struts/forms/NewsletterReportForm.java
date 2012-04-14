package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.djmag.dao.NewsletterDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Newsletter;
import com.tdil.djmag.model.NewsletterExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.forms.ReportForm;

public class NewsletterReportForm extends ReportForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	

	@Override
	public void reset() throws SQLException {
	}

	@Override
	public void init() throws SQLException {
	}
	
	@Override
	public void initWith(int id) throws SQLException {
	}

	@Override
	public void basicValidate(ValidationError validationError) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public List<List<Object>> search() throws SQLException {
		List<List<Object>> full = new ArrayList<List<Object>>();
		NewsletterDAO newsletterDAO = DAOManager.getNewsletterDAO();
		List<Newsletter> subscription = newsletterDAO.selectNewsletterByExample(new NewsletterExample());
		for (Newsletter newsletter : subscription) {
			List<Object> result = new ArrayList<Object>();
			result.add(newsletter.getEmail());
			full.add(result);
		}
		return full;
	}
}
