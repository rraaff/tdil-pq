package com.tdil.ljpeugeot.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.ContactDataExample;
import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.ModelExample;
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
			ContactDataExample contactDataExample = new ContactDataExample();
			contactDataExample.createCriteria().andIdWebsiteuserEqualTo(this.id);
			List<ContactData> result = DAOManager.getContactDataDAO().selectContactDataByExample(contactDataExample);
			if (result.size() > 0) {
				return result.get(0);
			} else {
				return null;
			}
		}
	}
	
	private static final class GetVehicle implements TransactionalActionWithResult<Vehicle> {
		private int id;
		public GetVehicle(int id) {
			super();
			this.id = id;
		}
		public Vehicle executeInTransaction() throws SQLException {
			return DAOManager.getVehicleDAO().selectVehicleByPrimaryKey(this.id);
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
		public GetServices(int Vehicle) {
			super();
			this.idVehicle = Vehicle;
		}
		public List<Service> executeInTransaction() throws SQLException {
			ServiceExample serviceExample = new ServiceExample();
			serviceExample.createCriteria().andIdVehicleEqualTo(this.idVehicle);
			return DAOManager.getServiceDAO().selectServiceByExample(serviceExample);
		}
	}
	
	private static final class GetModels implements TransactionalActionWithResult<List<Model>> {
		public GetModels() {
			super();
		}
		public List<Model> executeInTransaction() throws SQLException {
			ModelExample vehicleExample = new ModelExample();
			return DAOManager.getModelDAO().selectModelByExample(vehicleExample);
		}
	}
	
	private static final class UpdateVehicle implements TransactionalAction {
		private Vehicle service;
		public UpdateVehicle(Vehicle service) {
			super();
			this.service = service;
		}
		public void executeInTransaction() throws SQLException {
			DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(this.service);
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
	
	private static final class InsertService implements TransactionalAction {
		private Service service;
		public InsertService(Service service) {
			super();
			this.service = service;
		}
		public void executeInTransaction() throws SQLException {
			DAOManager.getServiceDAO().insertService(this.service);
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
	
	public static List<Model> getModels() {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetModels());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Model>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Model>();
		} 
	}
	
	public static Vehicle getVehicle(int idUser) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetVehicle(idUser));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static boolean udpateVehicle(Vehicle contactData) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new UpdateVehicle(contactData));
			return true;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} 
	}
	
	public static boolean insertService(Service contactData) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new InsertService(contactData));
			return true;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return false;
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
	
	public static boolean updateService(Service service) {
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