package com.tdil.ljpeugeot.struts.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ljpeugeot.struts.forms.prevent.EditVehicleDataForm;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class SaveVehicleData extends AbstractAction {

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(SaveVehicleData.class);
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final EditVehicleDataForm aform = (EditVehicleDataForm)form;
		try {
			GenericTransactionExecutionService.getInstance().execute(new TransactionalAction() {
				@Override
				public void executeInTransaction() throws SQLException, ValidationException {
					aform.save();
				}
			});
			return mapping.findForward("continue");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return mapping.findForward("failure");
		}
	}
}
