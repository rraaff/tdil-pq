package com.tdil.ljpeugeot.struts.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.AlarmConf;
import com.tdil.ljpeugeot.model.AlarmConfExample;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxAction;

public class RenameAlarmAjaxAction extends AjaxAction {

	public static final class RenameAlarmAction implements TransactionalAction {
		private final int idEntidad;
		private final WebsiteUser sessionUser;
		private final String description;

		public RenameAlarmAction(int idEntidad, WebsiteUser sessionUser, String description) {
			this.idEntidad = idEntidad;
			this.sessionUser = sessionUser;
			this.description = description;
		}

		@Override
		public void executeInTransaction() throws SQLException, ValidationException {
			AlarmConfExample example = new AlarmConfExample();
			example.createCriteria().andIdentidadEqualTo(idEntidad).andIdwebsiteuserEqualTo(sessionUser.getModelUser().getId());
			List<AlarmConf> result = DAOManager.getAlarmConfDAO().selectAlarmConfByExample(example);
			if (result.isEmpty()) {
				// insert
				AlarmConf alarmConf = new AlarmConf();
				alarmConf.setIdentidad(idEntidad);
				alarmConf.setIdwebsiteuser(sessionUser.getModelUser().getId());
				alarmConf.setDescription(description);
				alarmConf.setEmailnotification(0);
				alarmConf.setDeleted(0);
				DAOManager.getAlarmConfDAO().insertAlarmConf(alarmConf);
			} else {
				// update
				AlarmConf alarmConf = result.get(0);
				alarmConf.setDescription(description);
				DAOManager.getAlarmConfDAO().updateAlarmConfByPrimaryKeySelective(alarmConf);
			}
			
		}
	}

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(RenameAlarmAjaxAction.class);
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
		final int idEntidad = Integer.valueOf(request.getParameter("idEntidad"));
		final String description = request.getParameter("description");
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			TransactionProvider.executeInTransaction(new RenameAlarmAction(idEntidad, sessionUser, description));
			result.put("result", "OK");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.put("result", "ERR");
		}
		writeJsonResponse(result, response);
		return null;
	}

}
