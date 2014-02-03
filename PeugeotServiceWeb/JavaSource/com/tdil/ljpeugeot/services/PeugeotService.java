package com.tdil.ljpeugeot.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.Service;
import com.tdil.ljpeugeot.model.ServiceExample;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.model.VehicleExample;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class PeugeotService {
	

	private static final class GetContactData implements TransactionalActionWithResult<ContactData> {
		private int id;
		public GetContactData(int id) {
			super();
			this.id = id;
		}
		public ContactData executeInTransaction() throws SQLException {
			return DAOManager.getContactDataDAO().selectContactDataByPrimaryKey(this.id);
		}
	}
	
	private static final class UpdateContactData implements TransactionalAction {
		private ContactData contactData;
		public UpdateContactData(ContactData contactData) {
			super();
			this.contactData = contactData;
		}
		public void executeInTransaction() throws SQLException {
			DAOManager.getContactDataDAO().updateContactDataByPrimaryKey(this.contactData);
		}
	}
	
	private static final class GetVehicles implements TransactionalActionWithResult<List<Vehicle>> {
		private int idUser;
		public GetVehicles(int idUser) {
			super();
			this.idUser = idUser;
		}
		public List<Vehicle> executeInTransaction() throws SQLException {
			VehicleExample vehicleExample = new VehicleExample();
			vehicleExample.createCriteria().andIdWebsiteuserEqualTo(idUser);
			return DAOManager.getVehicleDAO().selectVehicleByExample(vehicleExample);
		}
	}
	
	private static final class GetServices implements TransactionalActionWithResult<List<Service>> {
		private int idVehicle;
		public GetServices(int idVehicle) {
			super();
			this.idVehicle = idVehicle;
		}
		public List<Service> executeInTransaction() throws SQLException {
			ServiceExample vehicleExample = new ServiceExample();
			vehicleExample.createCriteria().andIdVechicleEqualTo(this.idVehicle);
			return DAOManager.getServiceDAO().selectServiceByExample(vehicleExample);
		}
	}
	
	private static final class DeleteService implements TransactionalAction {
		private int idService;
		public DeleteService(int idService) {
			super();
			this.idService = idService;
		}
		public void executeInTransaction() throws SQLException {
			DAOManager.getServiceDAO().deleteServiceByPrimaryKey(this.idService);
		}
	}
	
	private static final class UpdateService implements TransactionalAction {
		private Service service;
		public UpdateService(Service service) {
			super();
			this.service = service;
		}
		public void executeInTransaction() throws SQLException {
			DAOManager.getServiceDAO().updateServiceByPrimaryKey(this.service);
		}
	}
	
	public static ContactData getContactData(int idUser) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetContactData(idUser));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static boolean udpateContactData(ContactData contactData) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new UpdateContactData(contactData));
			return true;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} 
	}
	
	public static List<Vehicle> getVehicles(int idUser) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetVehicles(idUser));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Vehicle>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Vehicle>();
		} 
	}
	
	public static List<Service> getServices(int idVehicle) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetServices(idVehicle));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Service>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Service>();
		} 
	}
	
	public static boolean udpateService(Service service) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new UpdateService(service));
			return true;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} 
	}
	
	public static boolean deleteService(int idService) {
		try {
			 GenericTransactionExecutionService.getInstance().execute(new DeleteService(idService));
			 return true;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} 
	}
	
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(PeugeotService.class);
	}
	
}
