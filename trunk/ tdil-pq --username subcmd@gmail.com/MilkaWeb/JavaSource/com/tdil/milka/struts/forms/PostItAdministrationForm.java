package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.valueobjects.PostItValueObject;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;

public class PostItAdministrationForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	private List<PostItValueObject> approvalPending;

	
	@Override
	public void reset() throws SQLException {
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
		setApprovalPending(DAOManager.getPostItDAO().selectPostItsToApproveWithAuthor());
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
	}	


	private static Logger getLog() {
		return LoggerProvider.getLogger(PostItAdministrationForm.class);
	}
	public List<PostItValueObject> getApprovalPending() {
		return approvalPending;
	}
	public void setApprovalPending(List<PostItValueObject> approvalPending) {
		this.approvalPending = approvalPending;
	}

}
