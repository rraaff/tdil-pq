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
import com.tdil.lojack.model.LightConf;
import com.tdil.lojack.model.LightConfExample;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxAction;

public class ActivateLightEmailNotificationAjaxAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
		final int idEntidad = Integer.valueOf(request.getParameter("idEntidad"));
		final int idLuz = Integer.valueOf(request.getParameter("idLuz"));
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				@Override
				public void executeInTransaction() throws SQLException, ValidationException {
					LightConfExample example = new LightConfExample();
					example.createCriteria().andIdentidadEqualTo(idEntidad).andIdluzEqualTo(idLuz).andIdwebsiteuserEqualTo(sessionUser.getModelUser().getId());
					List<LightConf> result = DAOManager.getLightConfDAO().selectLightConfByExample(example);
					if (result.isEmpty()) {
						// insert
						LightConf alarmConf = new LightConf();
						alarmConf.setIdentidad(idEntidad);
						alarmConf.setIdluz(idLuz);
						alarmConf.setIdwebsiteuser(sessionUser.getModelUser().getId());
						alarmConf.setEmailnotification(1);
						alarmConf.setDeleted(0);
						DAOManager.getLightConfDAO().insertLightConf(alarmConf);
					} else {
						// update
						LightConf alarmConf = result.get(0);
						alarmConf.setEmailnotification(1);
						DAOManager.getLightConfDAO().updateLightConfByPrimaryKeySelective(alarmConf);
					}
				}
			});
			result.put("result", "OK");
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
			result.put("result", "ERR");
		}
		writeJsonResponse(result, response);
		return null;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ActivateLightEmailNotificationAjaxAction.class);
	}
}
