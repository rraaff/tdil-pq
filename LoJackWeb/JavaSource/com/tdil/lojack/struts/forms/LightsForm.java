package com.tdil.lojack.struts.forms;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.gis.GISConnector;
import com.tdil.lojack.gis.model.Light;
import com.tdil.lojack.utils.WebsiteUser;

public class LightsForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private Collection<Light> lights;
	
		
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}
	
	public void reset() {
	}

	public void initWith(WebsiteUser user) {
		setUser(user);
		setLights(GISConnector.getLights(String.valueOf(user.getId())));
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
