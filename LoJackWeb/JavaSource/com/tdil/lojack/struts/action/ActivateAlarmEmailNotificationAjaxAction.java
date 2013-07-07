package com.tdil.lojack.struts.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.AlarmConf;
import com.tdil.lojack.model.AlarmConfExample;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxAction;

public class ActivateAlarmEmailNotificationAjaxAction extends AjaxAction {

	public static final class ActivateAlarmEmailNotification implements TransactionalAction {
		private final int idEntidad;
		private final WebsiteUser sessionUser;

		public ActivateAlarmEmailNotification(WebsiteUser sessionUser, int idEntidad) {
			this.idEntidad = idEntidad;
			this.sessionUser = sessionUser;
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
				alarmConf.setEmailnotification(1);
				alarmConf.setDeleted(0);
				DAOManager.getAlarmConfDAO().insertAlarmConf(alarmConf);
			} else {
				// update
				AlarmConf alarmConf = result.get(0);
				alarmConf.setEmailnotification(1);
				DAOManager.getAlarmConfDAO().updateAlarmConfByPrimaryKeySelective(alarmConf);
			}
		}
	}

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
		final int idEntidad = Integer.valueOf(request.getParameter("idEntidad"));
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			TransactionProvider.executeInTransaction(new ActivateAlarmEmailNotification(sessionUser, idEntidad));
			result.put("result", "OK");
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			result.put("result", "ERR");
		}
		writeJsonResponse(result, response);
		return null;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ActivateAlarmEmailNotificationAjaxAction.class);
	}
}
