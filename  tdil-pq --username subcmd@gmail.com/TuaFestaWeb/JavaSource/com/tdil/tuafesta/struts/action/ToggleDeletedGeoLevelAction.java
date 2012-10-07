package com.tdil.tuafesta.struts.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.tuafesta.struts.forms.GeoLevelForm;

public class ToggleDeletedGeoLevelAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		final GeoLevelForm aForm = (GeoLevelForm) form;
		final int userId = Integer.parseInt(request.getParameter("id"));
		final int level = Integer.parseInt(request.getParameter("level"));
		aForm.initForDeleteWith(userId, level);
		ValidationError validationError = new ValidationError();
		aForm.validateForToggleDeletedFlag(validationError);
		if(!validationError.hasError()) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					aForm.toggleDeletedFlag();
				}
			});
			aForm.resetAfterDelete();
			return mapping.findForward("continue");
		}
		return mapping.findForward("failure");
	}

}
