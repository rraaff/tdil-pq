package com.tdil.ljpeugeot.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.Advice;
import com.tdil.ljpeugeot.model.AdviceExample;
import com.tdil.ljpeugeot.model.Alert;
import com.tdil.ljpeugeot.model.AlertExample;
import com.tdil.ljpeugeot.model.AlertStatus;
import com.tdil.ljpeugeot.model.City;
import com.tdil.ljpeugeot.model.CityExample;
import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.ContactDataExample;
import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.ModelExample;
import com.tdil.ljpeugeot.model.NativeApp;
import com.tdil.ljpeugeot.model.NativeAppExample;
import com.tdil.ljpeugeot.model.NotificationEmail;
import com.tdil.ljpeugeot.model.NotificationEmailExample;
import com.tdil.ljpeugeot.model.Service;
import com.tdil.ljpeugeot.model.ServiceExample;
import com.tdil.ljpeugeot.model.State;
import com.tdil.ljpeugeot.model.StateExample;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.model.VehicleExample;
import com.tdil.ljpeugeot.model.WebsiteUser;
import com.tdil.ljpeugeot.model.valueobjects.AdviceValueObject;
import com.tdil.ljpeugeot.model.valueobjects.AlertValueObject;
import com.tdil.ljpeugeot.model.valueobjects.VehicleValueObject;
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
	
	private static final class GetModel implements TransactionalActionWithResult<Model> {
		private int id;
		public GetModel(int id) {
			super();
			this.id = id;
		}
		public Model executeInTransaction() throws SQLException {
			return DAOManager.getModelDAO().selectModelByPrimaryKey(id);
		}
	}
	
	private static final class GetNativeApp implements TransactionalActionWithResult<NativeApp> {
		private String code;
		public GetNativeApp(String code) {
			super();
			this.code = code;
		}
		public NativeApp executeInTransaction() throws SQLException {
			NativeAppExample example = new NativeAppExample();
			example.createCriteria().andCodeEqualTo(this.code);
			List<NativeApp> nativeApps = DAOManager.getNativeAppDAO().selectNativeAppByExample(example);
			if (nativeApps.size() > 0) {
				return nativeApps.get(0);
			} else {
				return null;
			}
		}
	}
	
	private static final class UpdateNativeApp implements TransactionalAction {
		private String id;
		private String code;
		private String title;
		private String version;
		private String url;
		private String summary;

		public UpdateNativeApp(String id, String code, String title, String version, String url, String summary) {
			super();
			this.id = id;
			this.code = code;
			this.title = title;
			this.version = version;
			this.url = url;
			this.summary = summary;
		}

		public void executeInTransaction() throws SQLException {
			int id = Integer.valueOf(this.id);
			if (id == 0) {
				NativeApp app = new NativeApp();
				app.setCode(this.code);
				app.setTitle(this.title);
				app.setVersion(this.version);
				app.setUrl(this.url);
				app.setSummary(this.summary);
				app.setDeleted(0);
				DAOManager.getNativeAppDAO().insertNativeApp(app);
			} else {
				NativeApp app = DAOManager.getNativeAppDAO().selectNativeAppByPrimaryKey(id);
				app.setTitle(this.title);
				app.setVersion(this.version);
				app.setUrl(this.url);
				app.setSummary(this.summary);
				DAOManager.getNativeAppDAO().updateNativeAppByPrimaryKey(app);
			}
		}
	}
	
	private static final class ToggleDeleteNativeApp implements TransactionalAction {
		private String id;

		public ToggleDeleteNativeApp(String id) {
			super();
			this.id = id;
		}

		public void executeInTransaction() throws SQLException {
			NativeApp app = DAOManager.getNativeAppDAO().selectNativeAppByPrimaryKey(Integer.valueOf(this.id));
			app.setDeleted(app.getDeleted() == 1 ? 0 : 1);
			DAOManager.getNativeAppDAO().updateNativeAppByPrimaryKey(app);
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
	
	private static final class GetNativeApps implements TransactionalActionWithResult<List<NativeApp>> {
		public GetNativeApps() {
			super();
		}
		public List<NativeApp> executeInTransaction() throws SQLException {
			return DAOManager.getNativeAppDAO().selectNativeAppByExample(new NativeAppExample());
		}
	}
	
	private static final class GetAlerts implements TransactionalActionWithResult<List<Alert>> {
		private int idUser;
		public GetAlerts(int idUser) {
			super();
			this.idUser = idUser;
		}
		public List<Alert> executeInTransaction() throws SQLException {
			AlertExample vehicleExample = new AlertExample();
			vehicleExample.createCriteria().andIdWebsiteuserEqualTo(idUser);
			vehicleExample.setOrderByClause("id desc");
			return DAOManager.getAlertDAO().selectAlertByExample(vehicleExample);
		}
	}
	
	private static final class GetAlertsPending implements TransactionalActionWithResult<Alert> {
		private int idUser;
		public GetAlertsPending(int idUser) {
			super();
			this.idUser = idUser;
		}
		public Alert executeInTransaction() throws SQLException {
			AlertExample vehicleExample = new AlertExample();
			vehicleExample.createCriteria().andIdWebsiteuserEqualTo(idUser).andStatusEqualTo(AlertStatus.PENDING.code());
			vehicleExample.setOrderByClause("id desc");
			List<Alert> alerts = DAOManager.getAlertDAO().selectAlertByExample(vehicleExample);
			if (alerts.size() > 0) {
				return alerts.get(0);
			} else {
				return null;
			}
		}
	}
	
	private static final class GetAlertsPendingCC implements TransactionalActionWithResult<List<AlertValueObject>> {
		public GetAlertsPendingCC() {
			super();
		}
		public List<AlertValueObject> executeInTransaction() throws SQLException {
			AlertExample vehicleExample = new AlertExample();
			vehicleExample.createCriteria().andStatusEqualTo(AlertStatus.PENDING.code());
			vehicleExample.setOrderByClause("id desc");
			List<Alert> alerts = DAOManager.getAlertDAO().selectAlertByExample(vehicleExample);
			List<AlertValueObject> result = new ArrayList<AlertValueObject>();
			for (Alert alert : alerts) {
				result.add(new AlertValueObject(alert, DAOManager.getWebsiteUserDAO().selectWebsiteUserByPrimaryKey(alert.getIdWebsiteuser())));
			}
			return result;
		}
	}
	
	private static final class GetMyVehicles implements TransactionalActionWithResult<List<VehicleValueObject>> {
		private int idUser;
		public GetMyVehicles(int idUser) {
			super();
			this.idUser = idUser;
		}
		public List<VehicleValueObject> executeInTransaction() throws SQLException {
			VehicleExample vehicleExample = new VehicleExample();
			vehicleExample.createCriteria().andIdWebsiteuserEqualTo(idUser);
			List<Vehicle> vehicles = DAOManager.getVehicleDAO().selectVehicleByExample(vehicleExample);
			List<VehicleValueObject> result = new ArrayList<VehicleValueObject>();
			for (Vehicle vehicle : vehicles) {
				result.add(new VehicleValueObject(vehicle, getModel(vehicle.getIdModel())));
			}
			return result;
		}
	}
	
	private static final class GetAdvices implements TransactionalActionWithResult<List<AdviceValueObject>> {
		private int idUser;
		public GetAdvices(int idUser) {
			super();
			this.idUser = idUser;
		}
		public List<AdviceValueObject> executeInTransaction() throws SQLException {
			AdviceExample adviceExample = new AdviceExample();
			List<Vehicle> vehicles = getVehicles(idUser);
			if (vehicles.isEmpty()) {
				return new ArrayList<AdviceValueObject>();
			}
			List<Integer> vehiclesIds = new ArrayList<Integer>();
			Map<Integer, Vehicle> idsToVehicles = new HashMap<Integer, Vehicle>();
			for (Vehicle v : vehicles) {
				vehiclesIds.add(v.getId());
				idsToVehicles.put(v.getId(), v);
			}
			adviceExample.createCriteria().andIdVechicleIn(vehiclesIds).andIsreadEqualTo(0);
			List<Advice> advices = DAOManager.getAdviceDAO().selectAdviceByExample(adviceExample);
			
			List<AdviceValueObject> result = new ArrayList<AdviceValueObject>();
			for (Advice advice : advices) {
				result.add(new AdviceValueObject(advice, idsToVehicles.get(advice.getIdVechicle())));
			}
			return result;
		}
	}
	
	private static final class DismissAdvices implements TransactionalAction {
		private int idUser;
		private String idsAdvices;
		public DismissAdvices(String idsAdvices, int idUser) {
			super();
			this.idUser = idUser;
			this.idsAdvices = idsAdvices;
		}
		public void executeInTransaction() throws SQLException {
			List<Vehicle> vehicles = getVehicles(idUser);
			Set<Integer> vehiclesIds = new HashSet<Integer>();
			for (Vehicle v : vehicles) {
				vehiclesIds.add(v.getId());
			}
			String splitted[] = this.idsAdvices.split(",");
			for (String id : splitted) {
				Advice advice = DAOManager.getAdviceDAO().selectAdviceByPrimaryKey(Integer.valueOf(id));
				if (vehiclesIds.contains(advice.getIdVechicle())) {
					advice.setIsread(1);
					DAOManager.getAdviceDAO().updateAdviceByPrimaryKey(advice);
				}
			}
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
	
	private static final class AddAlert implements TransactionalAction {
		private int idUser;
		private String phone;
		private BigDecimal lat;
		private BigDecimal lon;
		
		public AddAlert(int idUser, String phone, BigDecimal lat, BigDecimal lon) {
			super();
			this.idUser = idUser;
			this.phone = phone;
			this.lat = lat;
			this.lon = lon;
		}

		public void executeInTransaction() throws SQLException {
			Alert alert = new Alert();
			alert.setCreationdate(new Date());
			alert.setDeleted(0);
			alert.setIdWebsiteuser(idUser);
			alert.setLat(lat);
			alert.setLon(lon);
			alert.setPhonenumber(phone);
			alert.setStatus(AlertStatus.PENDING.code());
			DAOManager.getAlertDAO().insertAlert(alert);
		}
	}
	
	private static final class UpdateAlert implements TransactionalActionWithResult<Boolean> {
		private int idUser;
		private String alertId;
		private BigDecimal lat;
		private BigDecimal lon;
		
		public UpdateAlert(int idUser, String alertId, BigDecimal lat, BigDecimal lon) {
			super();
			this.idUser = idUser;
			this.alertId = alertId;
			this.lat = lat;
			this.lon = lon;
		}

		public Boolean executeInTransaction() throws SQLException {
			Alert alert = DAOManager.getAlertDAO().selectAlertByPrimaryKey(Integer.valueOf(alertId));
			if (alert == null) {
				return false;
			}
			if (alert.getIdWebsiteuser() != this.idUser) {
				return false;
			}
			alert.setLat(lat);
			alert.setLon(lon);
			DAOManager.getAlertDAO().updateAlertByPrimaryKey(alert);
			return true;
		}
	}
	
	private static final class GetAlertInProgress implements TransactionalActionWithResult<AlertValueObject> {
		private int ccUserId;
		public GetAlertInProgress(int idccUserId) {
			super();
			this.ccUserId = idccUserId;
		}
		public AlertValueObject executeInTransaction() throws SQLException {
			AlertExample alertExample = new AlertExample();
			alertExample.createCriteria().andIdSystemuserEqualTo(this.ccUserId).andStatusEqualTo(AlertStatus.IN_PROGRESS.code());
			List<Alert> alerts = DAOManager.getAlertDAO().selectAlertByExample(alertExample);
			if (alerts.size() > 0) {
				Alert alert = alerts.get(0);
				WebsiteUser user = DAOManager.getWebsiteUserDAO().selectWebsiteUserByPrimaryKey(alert.getIdWebsiteuser());
				return new AlertValueObject(alert, user, getContactData(user.getId()));
			} else {
				return null;
			}
		}
	}
	
	private static final class TakeAlert implements TransactionalActionWithResult<Boolean> {
		private int alertId;
		private int ccUserId;
		public TakeAlert(int alertId, int idccUserId) {
			super();
			this.alertId = alertId;
			this.ccUserId = idccUserId;
		}
		public Boolean executeInTransaction() throws SQLException {
			Alert alert = new Alert();
			alert.setStatus(AlertStatus.IN_PROGRESS.code());
			alert.setIdSystemuser(this.ccUserId);
			AlertExample alertExample = new AlertExample();
			alertExample.createCriteria().andIdEqualTo(this.alertId).andStatusEqualTo(AlertStatus.PENDING.code());
			DAOManager.getAlertDAO().updateAlertByExampleSelective(alert, alertExample);
			return Boolean.TRUE;
		}
	}
	
	private static final class FinishAlertProgress implements TransactionalActionWithResult<Boolean> {
		private int alertId;
		public FinishAlertProgress(int alertId) {
			super();
			this.alertId = alertId;
		}
		public Boolean executeInTransaction() throws SQLException {
			Alert alert = DAOManager.getAlertDAO().selectAlertByPrimaryKey(this.alertId);
			alert.setStatus(AlertStatus.FINISHED.code());
			alert.setModificationdate(new Date());
			DAOManager.getAlertDAO().updateAlertByPrimaryKey(alert);
			return Boolean.TRUE;
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
	
	public static List<NativeApp> getNativeApps() {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetNativeApps());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<NativeApp>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<NativeApp>();
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
	
	public static NativeApp getNativeAppByCode(String code) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetNativeApp(code));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static void updateNativeApp(String idST, String code, String title, String version, String url, String summary) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new UpdateNativeApp(idST, code, title, version, url, summary));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		} 
	}
	
	public static void toggleDeleteNativeApp(String idST) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new ToggleDeleteNativeApp(idST));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
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
	
	public static List<AdviceValueObject> getAdvices(int idWebsiteUser) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetAdvices(idWebsiteUser));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<AdviceValueObject>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<AdviceValueObject>();
		} 
	}
	
	public static List<VehicleValueObject> getMyVehicles(int idWebsiteUser) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetMyVehicles(idWebsiteUser));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<VehicleValueObject>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<VehicleValueObject>();
		} 
	}
	
	public static boolean addAlert(int idWebsiteUser, String phone, BigDecimal lat, BigDecimal lon) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new AddAlert(idWebsiteUser, phone, lat, lon));
			return true;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} 
	}
	
	public static boolean updateAlert(int idWebsiteUser, String alertId, BigDecimal lat, BigDecimal lon) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new UpdateAlert(idWebsiteUser, alertId, lat, lon));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} 
	}
	
	public static List<Alert> getAlerts(int idWebsiteUser) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetAlerts(idWebsiteUser));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Alert>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Alert>();
		} 
	}
	
	public static List<AlertValueObject> getAlertsPending() {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetAlertsPendingCC());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<AlertValueObject>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<AlertValueObject>();
		} 
	}
	
	public static Alert getLastAlertPending(int idWebsiteUser) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetAlertsPending(idWebsiteUser));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static AlertValueObject getAlertInProgress(int idSystemUser) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetAlertInProgress(idSystemUser));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static AlertValueObject takeAlert(int idAlert, int idSystemUser) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new TakeAlert(idAlert, idSystemUser));
			return getAlertInProgress(idSystemUser);
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static void finishAlertProgress(int idAlert) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new FinishAlertProgress(idAlert));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
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
			if (idDealer == null) {
				return null;
			}
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
			if (idDealer == null) {
				return null;
			}
			return GenericTransactionExecutionService.getInstance().execute(new GetCity(idDealer));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static Model getModel(Integer idModel) {
		try {
			if (idModel == null) {
				return null;
			}
			return GenericTransactionExecutionService.getInstance().execute(new GetModel(idModel));
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

	public static void dismissAdvices(String idAdvices, Integer id) throws SQLException, ValidationException {
		GenericTransactionExecutionService.getInstance().execute(new DismissAdvices(idAdvices, id));
	}
	
}
