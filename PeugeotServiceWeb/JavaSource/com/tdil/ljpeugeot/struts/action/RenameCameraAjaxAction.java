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
import com.tdil.ljpeugeot.model.CameraConf;
import com.tdil.ljpeugeot.model.CameraConfExample;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxAction;

public class RenameCameraAjaxAction extends AjaxAction {

	public static final class RenameCameraAction implements TransactionalAction {
		private final String url;
		private final WebsiteUser sessionUser;
		private final String description;

		public RenameCameraAction(String url, WebsiteUser sessionUser, String description) {
			this.url = url;
			this.sessionUser = sessionUser;
			this.description = description;
		}

		@Override
		public void executeInTransaction() throws SQLException, ValidationException {
			CameraConfExample example = new CameraConfExample();
			example.createCriteria().andUrlEqualTo(url).andIdwebsiteuserEqualTo(sessionUser.getModelUser().getId());
			List<CameraConf> result = DAOManager.getCameraConfDAO().selectCameraConfByExample(example);
			if (result.isEmpty()) {
				// insert
				CameraConf alarmConf = new CameraConf();
				alarmConf.setUrl(url);
				alarmConf.setIdwebsiteuser(sessionUser.getModelUser().getId());
				alarmConf.setDescription(description);
				alarmConf.setDeleted(0);
				DAOManager.getCameraConfDAO().insertCameraConf(alarmConf);
			} else {
				// update
				CameraConf alarmConf = result.get(0);
				alarmConf.setDescription(description);
				DAOManager.getCameraConfDAO().updateCameraConfByPrimaryKeySelective(alarmConf);
			}
			
		}
	}

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(RenameCameraAjaxAction.class);
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final WebsiteUser sessionUser = (WebsiteUser)request.getSession().getAttribute("user");
		final String url = request.getParameter("url");
		final String description = request.getParameter("description");
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			TransactionProvider.executeInTransaction(new RenameCameraAction(url, sessionUser, description));
			result.put("result", "OK");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.put("result", "ERR");
		}
		writeJsonResponse(result, response);
		return null;
	}

}
