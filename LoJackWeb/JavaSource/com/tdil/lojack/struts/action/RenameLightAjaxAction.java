package com.tdil.lojack.struts.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.LightConf;
import com.tdil.lojack.model.LightConfExample;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxAction;

public class RenameLightAjaxAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
		String idEntidadIdLuz = request.getParameter("idEntidadIdLuz");
		String arr[] = idEntidadIdLuz.split("-");
		final int idEntidad = Integer.valueOf(arr[0]);
		final int idLuz = Integer.valueOf(arr[1]);
		final String description = request.getParameter("description");
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
						alarmConf.setDescription(description);
						alarmConf.setEmailnotification(0);
						alarmConf.setDeleted(0);
						DAOManager.getLightConfDAO().insertLightConf(alarmConf);
					} else {
						// update
						LightConf alarmConf = result.get(0);
						alarmConf.setDescription(description);
						DAOManager.getLightConfDAO().updateLightConfByPrimaryKeySelective(alarmConf);
					}
					
				}
			});
			result.put("result", "OK");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("result", "ERR");
		}
		writeJsonResponse(result, response);
		return null;
	}

}
