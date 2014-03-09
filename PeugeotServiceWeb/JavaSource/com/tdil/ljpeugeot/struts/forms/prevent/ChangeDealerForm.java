package com.tdil.ljpeugeot.struts.forms.prevent;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.ljpeugeot.utils.WebsiteUserUtils;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.forms.TransactionalValidationForm;

public class ChangeDealerForm extends TransactionalValidationForm {

	/**
	 *
	 */
	private static final long serialVersionUID = -4103112336985471907L;

	private int idVehicle;
	private int idDealer;
	private String email;
	private WebsiteUser user;

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(ChangeDealerForm.class);
	
	@Override
	public void basicValidate(ValidationError validationError) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init() throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initWith(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reset() throws SQLException {
		this.idVehicle = 0;
		this.idDealer = 0;
	}
	
	@Override
	public void takeValuesFrom(HttpServletRequest request) {
//		this.user = (WebsiteUser)AbstractAction.getLoggedUser(request);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void save() throws SQLException {
		if (this.idVehicle == 0) {
			// cambio todos
			for(Vehicle v : PeugeotService.getVehicles(this.user.getModelUser().getId())) {
				v.setIdDealer(this.idDealer);
				DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(v);
			}
		} else {
			Vehicle vehicle = DAOManager.getVehicleDAO().selectVehicleByPrimaryKey(this.idVehicle);
			if (!vehicle.getIdWebsiteuser().equals(this.user.getModelUser().getId())) {
				throw new RuntimeException("invalid data");
			}
			vehicle.setIdDealer(this.idDealer);
			DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(vehicle);
		}
		com.tdil.ljpeugeot.model.WebsiteUser edit = DAOManager.getWebsiteUserDAO().selectWebsiteUserByPrimaryKey(this.user.getModelUser().getId());
		edit.setEmail(this.email);
		DAOManager.getWebsiteUserDAO().updateWebsiteUserByPrimaryKey(edit);
	}

	public int getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(int idVehicle) {
		this.idVehicle = idVehicle;
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
		this.email = WebsiteUserUtils.getWebSiteUserById(user.getModelUser().getId()).getEmail();
	}

	public int getIdDealer() {
		return idDealer;
	}

	public void setIdDealer(int idDealer) {
		this.idDealer = idDealer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
