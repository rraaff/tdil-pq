package com.tdil.ljpeugeot.struts.forms.prevent;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.AdviceExample;
import com.tdil.ljpeugeot.model.Service;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.utils.DateUtils;

public class AddServiceForm extends TransactionalValidationForm {

	/**
	 *
	 */
	private static final long serialVersionUID = -4103112336985471907L;

	private int idVehicle;
	private String serviceKm;
	private String serviceDate;
	private WebsiteUser user;

	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(AddServiceForm.class);
	
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
		this.serviceKm = null;
		this.serviceDate = null;
	}
	
	@Override
	public void takeValuesFrom(HttpServletRequest request) {
		this.user = (WebsiteUser)AbstractAction.getLoggedUser(request);
		
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void save() throws SQLException {
		Service service = new Service();
		int serviceKm2 = Integer.parseInt(this.getServiceKm());
		service.setKm(serviceKm2);
		Date serviceDateObj = DateUtils.parseDateSp(this.getServiceDate());
		service.setServicedate(serviceDateObj);
		service.setDeleted(0);
		service.setIdVehicle(this.idVehicle);
		Vehicle vehicle = DAOManager.getVehicleDAO().selectVehicleByPrimaryKey(this.idVehicle);
		if (!vehicle.getIdWebsiteuser().equals(this.user.getModelUser().getId())) {
			throw new RuntimeException("invalid data");
		}
		if (vehicle.getLastservicekm() == null || serviceKm2 > vehicle.getLastservicekm()) {
			vehicle.setLastservicedate(serviceDateObj);
			vehicle.setLastservicekm(serviceKm2);
			vehicle.setNeedsadvice(0);
			vehicle.setNeedsadvice1(0);
			vehicle.setAdvice1sent(0);
			vehicle.setNeedsadvice1date(null);
			vehicle.setNeedsadvice2(0);
			vehicle.setAdvice2sent(0);
			vehicle.setNeedsadvice2date(null);
			vehicle.setNeedsadvice3(0);
			vehicle.setAdvice3sent(0);
			vehicle.setNeedsadvice3date(null);
			DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(vehicle);
		}
		DAOManager.getServiceDAO().insertService(service);
		AdviceExample adviceExample = new AdviceExample();
		adviceExample.createCriteria().andIdVechicleEqualTo(this.idVehicle);
		DAOManager.getAdviceDAO().deleteAdviceByExample(adviceExample);
	}

	public int getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(int idVehicle) {
		this.idVehicle = idVehicle;
	}

	public String getServiceKm() {
		return serviceKm;
	}

	public void setServiceKm(String serviceKm) {
		this.serviceKm = serviceKm;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}

}
