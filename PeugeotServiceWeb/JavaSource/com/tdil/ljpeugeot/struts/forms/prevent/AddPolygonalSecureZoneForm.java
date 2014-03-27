package com.tdil.ljpeugeot.struts.forms.prevent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.tdil.ljpeugeot.prevent.PreventConnector;
import com.tdil.ljpeugeot.prevent.model.CircularSecureZone;
import com.tdil.ljpeugeot.prevent.model.PolygonalPoint;
import com.tdil.ljpeugeot.prevent.model.PolygonalSecureZone;
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

public class AddPolygonalSecureZoneForm extends AbstractForm {

	/**
	 *
	 */
	private static final long serialVersionUID = -4103112336985471907L;

	private WebsiteUser user;
	
	private String points;
	
	private String name;

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(AddPolygonalSecureZoneForm.class);
	
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
		try {
			basicsave();
		} catch (UnauthorizedException e) {
			this.getUser().reloginPrevent();
			try {
				basicsave();
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
		boolean result = true;
		PolygonalSecureZone secureZone = new PolygonalSecureZone();
		secureZone.setDescription(this.name);
		String points[] = this.points.split(",");
		List<PolygonalPoint> ppoints = new ArrayList<PolygonalPoint>();
		for (int i = 0; i < points.length; i = i +2) {
			ppoints.add(new PolygonalPoint(points[i],points[i+1]));
		}
		secureZone.setPoints(ppoints);
		PreventConnector.addPolygonalSecureZone(this.getUser().getPreventLoginResponse(), secureZone);
		return result;
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

}
