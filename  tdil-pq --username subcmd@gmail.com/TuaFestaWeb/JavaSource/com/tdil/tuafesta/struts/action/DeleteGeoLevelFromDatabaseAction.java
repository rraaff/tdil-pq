package com.tdil.tuafesta.struts.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.EditAction;
import com.tdil.tuafesta.struts.forms.GeoLevelForm;

public class DeleteGeoLevelFromDatabaseAction extends EditAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		final GeoLevelForm abstractForm = (GeoLevelForm) form;
//		final int levelId = Integer.parseInt(request.getParameter("level"));
//		final int userId = Integer.parseInt(request.getParameter("id"));
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
//				abstractForm.deleteGeoLevelFromDatabase(levelId, userId);
			}
		});

		return mapping.findForward("continue");
	}
	
}
