package com.tdil.ljpeugeot.struts.forms.prevent;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.tdil.ljpeugeot.prevent.PreventConnector;
import com.tdil.ljpeugeot.prevent.XMLResponse;
import com.tdil.ljpeugeot.prevent.model.CircularSecureZone;
import com.tdil.ljpeugeot.prevent.model.CircularSecureZoneResponse;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.AbstractForm;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.users.User;

public class AddCircularSecureZoneForm extends AbstractForm {

	/**
	 *
	 */
	private static final long serialVersionUID = -4103112336985471907L;

	private WebsiteUser user;
	
	private String centerLat;
	private String centerLon;
	
	private String otherLat;
	private String otherLon;
	
	private String name;

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(AddCircularSecureZoneForm.class);
	
	@Override
	public void init() throws SQLException {
		
	}
	
	@Override
	public ValidationError validate() {
		return new ValidationError();
	}
	@Override
	public void reset() throws SQLException {
		
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		
	}
	
	@Override
	public void takeValuesFrom(HttpServletRequest request) {
		super.takeValuesFrom(request);
		this.setUser((WebsiteUser)request.getSession().getAttribute("user"));
	}
	
	public void save() throws SQLException, ValidationException {
		ValidationError validationError = new ValidationError();
		try {
			if(!basicsave()) {
				throw new ValidationException(validationError);
			}
		} catch (UnauthorizedException e) {
			this.getUser().reloginPrevent();
			try {
				if(!basicsave()) {
					throw new ValidationException(validationError);
				}
			} catch (HttpStatusException e1) {
				throw new ValidationException(new ValidationError("STATUS." + e1.getStatus()));
			} catch (InvalidResponseException e1) {
				throw new ValidationException(new ValidationError("InvalidResponseException"));
			} catch (CommunicationException e1) {
				throw new ValidationException(new ValidationError("CommunicationException"));
			} catch (UnauthorizedException e1) {
				throw new ValidationException(new ValidationError("UnauthorizedException"));
			}
		} catch (HttpStatusException e) {
			throw new ValidationException(new ValidationError("STATUS." + e.getStatus()));
		} catch (InvalidResponseException e) {
			throw new ValidationException(new ValidationError("InvalidResponseException"));
		} catch (CommunicationException e) {
			throw new ValidationException(new ValidationError("CommunicationException"));
		} 
	}

	private boolean basicsave() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		CircularSecureZone circularSecureZone = new CircularSecureZone();
		circularSecureZone.setLatitude(this.getCenterLat());
		circularSecureZone.setLongitude(this.getCenterLon());
		circularSecureZone.setDescription(this.getName());
		LatLng point1 = new LatLng(Double.parseDouble(this.getCenterLat()), Double.parseDouble(this.getCenterLon()));
		LatLng point2 = new LatLng(Double.parseDouble(this.getOtherLat()), Double.parseDouble(this.getOtherLon()));
		long distance = Math.round(LatLngTool.distance(point1, point2, LengthUnit.METER));
		circularSecureZone.setRadio(Long.toString(distance)); // Calcular el radio y mandarlo
		XMLResponse res = PreventConnector.addCircularSecureZone(this.getUser().getPreventLoginResponse(), circularSecureZone);
		CircularSecureZoneResponse resp = (CircularSecureZoneResponse)res.getResult();
		return !resp.hasError();
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}

	public String getCenterLat() {
		return centerLat;
	}

	public void setCenterLat(String centerLat) {
		this.centerLat = centerLat;
	}

	public String getCenterLon() {
		return centerLon;
	}

	public void setCenterLon(String centerLon) {
		this.centerLon = centerLon;
	}

	public String getOtherLat() {
		return otherLat;
	}

	public void setOtherLat(String otherLat) {
		this.otherLat = otherLat;
	}

	public String getOtherLon() {
		return otherLon;
	}

	public void setOtherLon(String otherLon) {
		this.otherLon = otherLon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
