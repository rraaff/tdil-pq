package com.tdil.lojack.struts.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.lojack.utils.WebsiteUserUtils;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.struts.actions.SaveAction;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.validations.ValidationErrors;

public class SaveProfileActionMobile extends SaveAction {

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(SaveProfileActionMobile.class);
	
	public ActionForward validateAndSave(final AbstractForm form, HttpServletRequest request, ActionMapping mapping) {
		ValidationError error = form.validate();
		if(error.hasError()) {
			return redirectToFailure(error, request, mapping);
		} else {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						form.save();
					}
				});
				WebsiteUser user = (WebsiteUser)AbstractAction.getLoggedUser(request);
				user.setModelUser(WebsiteUserUtils.getWebSiteUser(user.getLojackUserId()));
			} catch (Exception ex) {
				LOG.error(ex.getMessage(), ex);
				ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
				return redirectToFailure(exError, request, mapping);
			}
		}
		return this.getSucessForward(form, mapping);
	}
}
