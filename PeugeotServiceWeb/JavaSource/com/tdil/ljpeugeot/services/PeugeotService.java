package com.tdil.ljpeugeot.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.City;
import com.tdil.ljpeugeot.model.CityExample;
import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.ContactDataExample;
import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.ModelExample;
import com.tdil.ljpeugeot.model.NotificationEmail;
import com.tdil.ljpeugeot.model.NotificationEmailExample;
import com.tdil.ljpeugeot.model.Service;
import com.tdil.ljpeugeot.model.ServiceExample;
import com.tdil.ljpeugeot.model.State;
import com.tdil.ljpeugeot.model.StateExample;
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
	
	private static final class GetNotificationEmail implements TransactionalActionWithResult<NotificationEmail> {
		private int id;
		public GetNotificationEmail(int id) {
			super();
			this.id = id;
		}
		public NotificationEmail executeInTransaction() throws SQLException {
			return DAOManager.getNotificationEmailDAO().selectNotificationEmailByPrimaryKey(this.id);
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
	
	private static final class GetDealer implements TransactionalActionWithResult<Dealer> {
		private int id;
		public GetDealer(int id) {
			super();
			this.id = id;
		}
		public Dealer executeInTransaction() throws SQLException {
			return DAOManager.getDealerDAO().selectDealerByPrimaryKey(this.id);
		}
	}
	
	private static final class GetCity implements TransactionalActionWithResult<City> {
		private int id;
		public GetCity(int id) {
			super();
			this.id = id;
		}
		public City executeInTransaction() throws SQLException {
			return DAOManager.getCityDAO().selectCityByPrimaryKey(id);
		}
	}
	
	private static final class GetVehicleByUserAndDomain implements TransactionalActionWithResult<Vehicle> {
		private int idUser;
		private String domain;
		public GetVehicleByUserAndDomain(int idUser, String domain) {
			super();
			this.idUser = idUser;
			this.domain = domain;
		}
		public Vehicle executeInTransaction() throws SQLException {
			VehicleExample vehicleExample = new VehicleExample();
			vehicleExample.createCriteria().andIdWebsiteuserEqualTo(this.idUser).andDomainEqualTo(this.domain);
			List<Vehicle> result = DAOManager.getVehicleDAO().selectVehicleByExample(vehicleExample);
			if (result.size() > 0) {
				return result.get(0);
			} else {
				return null;
			}
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
	
	private static final class GetStates implements TransactionalActionWithResult<List<State>> {
		public GetStates() {
			super();
		}
		public List<State> executeInTransaction() throws SQLException {
			StateExample vehicleExample = new StateExample();
			vehicleExample.setOrderByClause("name");
			return DAOManager.getStateDAO().selectStateByExample(vehicleExample);
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
			serviceExample.setOrderByClause("km desc");
			return DAOManager.getServiceDAO().selectServiceByExample(serviceExample);
		}
	}
	
	private static final class GetCities implements TransactionalActionWithResult<List<City>> {
		private int idState;
		public GetCities(int Vehicle) {
			super();
			this.idState = Vehicle;
		}
		public List<City> executeInTransaction() throws SQLException {
			CityExample serviceExample = new CityExample();
			serviceExample.createCriteria().andIdStateEqualTo(this.idState);
			serviceExample.setOrderByClause("name");
			return DAOManager.getCityDAO().selectCityByExample(serviceExample);
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
	
	private static final class GetNotificationEmails implements TransactionalActionWithResult<List<NotificationEmail>> {
		public GetNotificationEmails() {
			super();
		}
		public List<NotificationEmail> executeInTransaction() throws SQLException {
			NotificationEmailExample vehicleExample = new NotificationEmailExample();
			return DAOManager.getNotificationEmailDAO().selectNotificationEmailByExample(vehicleExample);
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
	
	private static final class UpdateNotificationEmail implements TransactionalAction {
		private NotificationEmail service;
		public UpdateNotificationEmail(NotificationEmail service) {
			super();
			this.service = service;
		}
		public void executeInTransaction() throws SQLException {
			DAOManager.getNotificationEmailDAO().updateNotificationEmailByPrimaryKey(this.service);
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
	
	public static NotificationEmail getNotificationEmail(int id) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetNotificationEmail(id));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static List<NotificationEmail> getNotificationEmails() {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetNotificationEmails());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<NotificationEmail>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<NotificationEmail>();
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
	
	public static boolean udpateNotificationEmail(NotificationEmail contactData) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new UpdateNotificationEmail(contactData));
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
	
	public static Vehicle getVehicle(int idUser, String domain) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetVehicleByUserAndDomain(idUser, domain));
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
	
	public static List<City> getCities(int idState) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetCities(idState));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<City>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<City>();
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

	public static Dealer getDealer(Integer idDealer) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetDealer(idDealer));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static City getCity(Integer idDealer) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetCity(idDealer));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	
	public static List<State> getStates() {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetStates());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<State>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<State>();
		} 
	}
	
}
