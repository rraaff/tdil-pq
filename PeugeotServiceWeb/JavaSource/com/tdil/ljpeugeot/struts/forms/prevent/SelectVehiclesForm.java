package com.tdil.ljpeugeot.struts.forms.prevent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tdil.ljpeugeot.prevent.PreventConnector;
import com.tdil.ljpeugeot.prevent.XMLResponse;
import com.tdil.ljpeugeot.prevent.model.GetPMHistory;
import com.tdil.ljpeugeot.prevent.model.PhoneNumbers;
import com.tdil.ljpeugeot.prevent.model.SatellitePosition;
import com.tdil.ljpeugeot.prevent.model.SecureZoneResponse;
import com.tdil.ljpeugeot.prevent.model.SecureZones;
import com.tdil.ljpeugeot.prevent.model.SpeedLimitResponse;
import com.tdil.ljpeugeot.prevent.model.SpeedLimits;
import com.tdil.ljpeugeot.prevent.model.UpdatePhoneNumbers;
import com.tdil.ljpeugeot.prevent.model.Vehicle;
import com.tdil.ljpeugeot.struts.forms.beans.SecureZoneSelectionBean;
import com.tdil.ljpeugeot.struts.forms.beans.SpeedSelectionBean;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.utils.DateUtils;

public class SelectVehiclesForm extends VehiclesForm {

	/**
	 *
	 */
	private static final long serialVersionUID = 3752656266263380512L;

	private List<Vehicle> selectList;
	private List<SatellitePosition> selectedVehiclePosition;

	private String alertPhoneCode;
	private String alertPhone;
	private String crashPhoneCode;
	private String crashPhone;
	private String otherPhone;
	
	private SpeedSelectionBean speedSelectionBean;
	private SecureZoneSelectionBean secureZoneSelectionBean;
	
	private String historicPathLimit;
	private String dateStart;
	private String dateEnd;
	private GetPMHistory selectedVehicleHistoricPath;
	
	public static String FREE = "FREE";
	public static String TODAY = "TODAY";
	public static String YESTERDAY = "YESTERDAY";
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(SelectVehiclesForm.class);

	public void selectVehicleForMap(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		setUser(user);
		try {
			basicselectVehicleForMap(user, id);
		} catch (UnauthorizedException e) {
			user.reloginPrevent();
			basicselectVehicleForMap(user, id);
		}
	}
	
	public void loadAllVehiclesPositions(WebsiteUser user) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		List<SatellitePosition> allPos = new ArrayList<SatellitePosition>();
		for(Vehicle vehicle : this.getVehicles()) {
			allPos.add((SatellitePosition)PreventConnector.getVehicleSatPosition(user.getPreventLoginResponse(), vehicle).getResult());
		}
		selectedVehiclePosition = allPos;
	}
	
	private void basicselectVehicleForMap(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		for(Vehicle vehicle : this.getVehicles()) {
			if (vehicle.getId().equals(id)) {
				setSelected(vehicle);
				setSelectedVehiclePosition((SatellitePosition)PreventConnector.getVehicleSatPosition(user.getPreventLoginResponse(), vehicle).getResult());
				return;
			}
		}
	}
	
	public void selectVehicleForSpeed(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		setUser(user);
		try {
			basicselectVehicleForSpeed(user, id);
		} catch (UnauthorizedException e) {
			user.reloginPrevent();
			basicselectVehicleForPhone(user, id);
		}
	}
	
	public void selectVehicleForSecureZone(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		setUser(user);
		try {
			basicselectVehicleForSecureZone(user, id);
		} catch (UnauthorizedException e) {
			user.reloginPrevent();
			basicselectVehicleForPhone(user, id);
		}
	}

	public void selectVehicleForPhone(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		setUser(user);
		try {
			basicselectVehicleForPhone(user, id);
		} catch (UnauthorizedException e) {
			user.reloginPrevent();
			basicselectVehicleForPhone(user, id);
		}
	}
	
	private void basicselectVehicleForPhone(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		for(Vehicle vehicle : this.getVehicles()) {
			if (vehicle.getId().equals(id)) {
				setSelected(vehicle);
				PhoneNumbers pn = (PhoneNumbers)(PreventConnector.getVehiclePhones(user.getPreventLoginResponse(), vehicle).getResult());
				String alert = pn.getAlert();
				if (!StringUtils.isEmpty(alert) && alert.contains("-")) {
					String splitted[] = alert.split("-");
					if (splitted.length == 2) { 
						if (StringUtils.isEmpty(splitted[0])) {
							setAlertPhoneCode("54");
						} else {
							setAlertPhoneCode(splitted[0]);
						}
						setAlertPhone(splitted[1]);
					} else {
						setAlertPhoneCode("54");
						setAlertPhone("");
					}
				} else {
					setAlertPhoneCode("54");
					setAlertPhone("");
				}
				String crash = pn.getCrash();
				if (!StringUtils.isEmpty(crash) && crash.contains("-")) {
					String crashSplitted[] = crash.split("-");
					if (crashSplitted.length == 2) {
						if (StringUtils.isEmpty(crashSplitted[0])) {
							setCrashPhoneCode("54");
						} else {
							setCrashPhoneCode(crashSplitted[0]);
						}
						setCrashPhone(crashSplitted[1]);
					} else {
						setCrashPhoneCode("54");
						setCrashPhone("");
					}
				} else {
					setCrashPhoneCode("54");
					setCrashPhone("");
				}
				setOtherPhone(pn.getOther());
				return;
			}
		}
	}
	
	private void basicselectVehicleForSpeed(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		for(Vehicle vehicle : this.getVehicles()) {
			if (vehicle.getId().equals(id)) {
				setSelected(vehicle);
				SpeedLimits limits = (SpeedLimits)PreventConnector.getVehicleSpeedLimit(user.getPreventLoginResponse(), vehicle).getResult();
				setSpeedSelectionBean(new SpeedSelectionBean(vehicle, limits));
				return;
			}
		}
	}
	
	private void basicselectVehicleForSecureZone(WebsiteUser user, String id) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		for(Vehicle vehicle : this.getVehicles()) {
			if (vehicle.getId().equals(id)) {
				setSelected(vehicle);
				SecureZones limits = (SecureZones)PreventConnector.getVehicleSecureZones(user.getPreventLoginResponse(), vehicle).getResult();
				setSecureZoneSelectionBean(new SecureZoneSelectionBean(vehicle, limits));
				return;
			}
		}
	}

	public Vehicle getSelected() {
		return selectList.get(0);
	}
	public void setSelected(Vehicle selected) {
		List<Vehicle> list = new ArrayList<Vehicle>();
		list.add(selected);
		this.selectList = list;
	}
	public List<SatellitePosition> getSelectedVehiclePosition() {
		return selectedVehiclePosition;
	}
	public void setSelectedVehiclePosition(SatellitePosition selectedVehiclePosition) {
		List<SatellitePosition> list = new ArrayList<SatellitePosition>();
		list.add(selectedVehiclePosition);
		this.selectedVehiclePosition = list;
	}
	public void setSelectedVehiclePosition(List<SatellitePosition> selectedVehiclePosition) {
		this.selectedVehiclePosition = selectedVehiclePosition;
	}

	public String getAlertPhone() {
		return alertPhone;
	}

	public void setAlertPhone(String alertPhone) {
		this.alertPhone = alertPhone;
	}

	public String getCrashPhone() {
		return crashPhone;
	}

	public void setCrashPhone(String crashPhone) {
		this.crashPhone = crashPhone;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public boolean savePhones() throws ValidationException, HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		try {
			return this.basicsavePhones();
		} catch (UnauthorizedException e) {
			this.getUser().reloginPrevent();
			return this.basicsavePhones();
		}
	}
	
	public boolean saveSpeed() throws ValidationException, HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		try {
			return this.basicsaveSpeed();
		} catch (UnauthorizedException e) {
			this.getUser().reloginPrevent();
			return this.basicsaveSpeed();
		}
	}
	
	public boolean saveSecureZone() throws ValidationException, HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		try {
			return this.basicsaveSecureZone();
		} catch (UnauthorizedException e) {
			this.getUser().reloginPrevent();
			return this.basicsaveSecureZone();
		}
	}

	private boolean basicsavePhones() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		UpdatePhoneNumbers phoneNumbers = new UpdatePhoneNumbers();
		phoneNumbers.setAlert(this.getAlertPhoneCode() + "-" + this.getAlertPhone());
		phoneNumbers.setCrash(this.getCrashPhoneCode() + "-" +this.getCrashPhone());
		phoneNumbers.setOther("54-");
		//phoneNumbers.setOther(this.getOtherPhone());
		phoneNumbers.setVehicleID(this.getSelected().getId());
		phoneNumbers.setUserToken(this.getUser().getPreventLoginResponse().getUserToken());
		XMLResponse setSpeed = PreventConnector.setVehiclePhones(this.getUser().getPreventLoginResponse(), phoneNumbers);
		PhoneNumbers resp = (PhoneNumbers)setSpeed.getResult();
		if ("OK".equals(resp.getStatus())) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean basicsaveSpeed() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		XMLResponse setSpeed = PreventConnector.setVehicleSpeedLimit(getUser().getPreventLoginResponse(), speedSelectionBean.getVehicle(), speedSelectionBean.getSelectedSpeedLimit());
		SpeedLimitResponse resp = (SpeedLimitResponse)setSpeed.getResult();
		return "OK".equals(resp.getStatus());
	}
	
	private boolean basicsaveSecureZone() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		XMLResponse setSpeed = PreventConnector.setVehicleSecureZone(getUser().getPreventLoginResponse(), secureZoneSelectionBean.getVehicle(), secureZoneSelectionBean.getSelectedSecureZone());
		SecureZoneResponse resp = (SecureZoneResponse)setSpeed.getResult();
		return "OK".equals(resp.getStatus());
	}
	
	public void searchHistoricPath() throws ValidationException, HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String dateStartP = "";
		String dateEndP = "";
		if (TODAY.equals(this.getHistoricPathLimit())) {
			Date start = DateUtils.date2FirstMomentOfDate(cal.getTime());
			Date end = DateUtils.date2LastMomentOfDate(cal.getTime());
			dateStartP = dateFormat.format(start) + "000000";
			dateEndP = dateFormat.format(end) + "235959";
		}
		if (YESTERDAY.equals(this.getHistoricPathLimit())) {
			cal.add(Calendar.DATE, -1);
			Date start = DateUtils.date2FirstMomentOfDate(cal.getTime());
			Date end = DateUtils.date2LastMomentOfDate(cal.getTime());
			dateStartP = dateFormat.format(start) + "000000";
			dateEndP = dateFormat.format(end) + "235959";
		}
		if (FREE.equals(this.getHistoricPathLimit())) {
			dateStartP = dateFormat.format(DateUtils.parseDate(getDateStart())) + "000000";
			dateEndP = dateFormat.format(DateUtils.parseDate(getDateEnd())) + "235959";
		}
		try {
			this.basicsearchHistoricPath(dateStartP, dateEndP);
		} catch (UnauthorizedException e) {
			this.getUser().reloginPrevent();
			this.basicsearchHistoricPath(dateStartP, dateEndP);
		}
	}

	private void basicsearchHistoricPath(String dateStartP, String dateEndP) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		selectedVehicleHistoricPath = (GetPMHistory)(PreventConnector.getHistoricPath(getUser().getPreventLoginResponse(), getSelected(), dateStartP, dateEndP)).getResult();
	}

	public List<Vehicle> getSelectList() {
		return selectList;
	}

	public void setSelectList(List<Vehicle> selectList) {
		this.selectList = selectList;
	}

	public SpeedSelectionBean getSpeedSelectionBean() {
		return speedSelectionBean;
	}

	public void setSpeedSelectionBean(SpeedSelectionBean speedSelectionBean) {
		this.speedSelectionBean = speedSelectionBean;
	}

	public SecureZoneSelectionBean getSecureZoneSelectionBean() {
		return secureZoneSelectionBean;
	}

	public void setSecureZoneSelectionBean(SecureZoneSelectionBean secureZoneSelectionBean) {
		this.secureZoneSelectionBean = secureZoneSelectionBean;
	}

	public String getAlertPhoneCode() {
		return alertPhoneCode;
	}

	public void setAlertPhoneCode(String alertPhoneCode) {
		this.alertPhoneCode = alertPhoneCode;
	}

	public String getCrashPhoneCode() {
		return crashPhoneCode;
	}

	public void setCrashPhoneCode(String crashPhoneCode) {
		this.crashPhoneCode = crashPhoneCode;
	}

	public GetPMHistory getSelectedVehicleHistoricPath() {
		return selectedVehicleHistoricPath;
	}

	public void setSelectedVehicleHistoricPath(GetPMHistory selectedVehicleHistoricPath) {
		this.selectedVehicleHistoricPath = selectedVehicleHistoricPath;
	}
	
	public String getSelectedVehicleId() {
		return "2";
	}
	public void setSelectedVehicleId(String id) {
		for(Vehicle vehicle : this.getVehicles()) {
			if (vehicle.getId().equals(id)) {
				setSelected(vehicle);
				return;
			}
		}
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getHistoricPathLimit() {
		return historicPathLimit;
	}

	public void setHistoricPathLimit(String historicPathLimit) {
		this.historicPathLimit = historicPathLimit;
	}

}
