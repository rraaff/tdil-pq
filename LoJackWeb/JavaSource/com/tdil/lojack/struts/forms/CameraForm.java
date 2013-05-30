package com.tdil.lojack.struts.forms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.camera.IPCamera;
import com.tdil.lojack.camera.PanasonicBLC131;
import com.tdil.lojack.camera.TPLinkSC4171G;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.Camera;
import com.tdil.lojack.utils.WebsiteUser;

public class CameraForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;

	private WebsiteUser user;
	private boolean useApplet = true;
	private String username;
	private String password;
	private String url;
	private String model;
	private String refreshInterval;

	private List<Camera> allCameras;

	private IPCamera camera;

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}

	public void reset() {
	}

	public void initWith(WebsiteUser user) {
		setUser(user);
		List<Camera> all = new ArrayList<Camera>();
		all.addAll(LoJackServicesConnector.getCameras(user));
		setAllCameras(all);
		if (all.size() == 1) {
			this.selectCamera(0);
		}
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}

	public boolean isUseApplet() {
		return useApplet;
	}

	public void setUseApplet(boolean useApplet) {
		this.useApplet = useApplet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(String refreshInterval) {
		this.refreshInterval = refreshInterval;
	}

	public IPCamera getCamera() {
		return camera;
	}

	private void setCamera(Camera camera2) {
		if (camera2 != null) {
			setUsername(camera2.getUsername());
			setPassword(camera2.getPassword());
			setUrl(camera2.getUrl());
			setModel(camera2.getModel());

			if (this.getModel().equals(PanasonicBLC131.PANASONIC_BLC131)) {
				setCamera(new PanasonicBLC131(this.getUrl(), this.getUsername(), this.getPassword()));
			}
			if (this.getModel().equals(TPLinkSC4171G.TP_LINK_SC4171G)) {
				setCamera(new TPLinkSC4171G(this.getUrl(), this.getUsername(), this.getPassword()));
			}
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCamera(IPCamera camera) {
		this.camera = camera;
	}

	public List<Camera> getAllCameras() {
		return allCameras;
	}

	public void setAllCameras(List<Camera> allCameras) {
		this.allCameras = allCameras;
	}

	public void selectCamera(Integer index) {
		setCamera(getAllCameras().get(index));
	}

}
