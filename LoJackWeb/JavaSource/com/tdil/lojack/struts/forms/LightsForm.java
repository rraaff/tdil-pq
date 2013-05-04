package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.Light;
import com.tdil.lojack.model.LightConf;
import com.tdil.lojack.model.LightConfExample;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.TransactionalActionWithResult;

public class LightsForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private Collection<Light> lights;
	
	private static final class GetLightConf implements TransactionalActionWithResult {
		private int userId;
		public GetLightConf(int userId) {
			super();
			this.userId = userId;
		}
		public Object executeInTransaction() throws SQLException {
			LightConfExample example = new LightConfExample();
			example.createCriteria().andIdwebsiteuserEqualTo(userId);
			return DAOManager.getLightConfDAO().selectLightConfByExample(example);
		}
	}
		
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}
	
	public void reset() {
	}

	public void initWith(WebsiteUser user) {
		setUser(user);
		setLights(LoJackServicesConnector.getLights(String.valueOf(user.getId())));
		try {
			List<LightConf> lightConf = (List<LightConf>)new GetLightConf(user.getModelUser().getId()).executeInTransaction();
			for (Light light : getLights()) {
				enhance(light, lightConf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void enhance(Light light, List<LightConf> lightConf) {
		for (LightConf lc : lightConf) {
			if (lc.getIdentidad().equals(light.getIdEntidad())) {
				if (lc.getIdluz().equals(light.getIdLuz())) {
					light.setEmailnotification(lc.getEmailnotification() == null ? false : (lc.getEmailnotification().equals(1)));
					light.setDescription(lc.getDescription());
					return;
				}
			}
		}
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}

	public Collection<Light> getLights() {
		return lights;
	}

	public void setLights(Collection<Light> lights) {
		this.lights = lights;
	}
	
}
