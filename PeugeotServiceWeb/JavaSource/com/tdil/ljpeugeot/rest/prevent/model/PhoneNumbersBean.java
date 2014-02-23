package com.tdil.ljpeugeot.rest.prevent.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.prevent.model.PhoneNumbers;
import com.tdil.ljpeugeot.prevent.model.UpdatePhoneNumbers;
import com.tdil.log4j.LoggerProvider;

@XmlRootElement
public class PhoneNumbersBean implements Serializable {

	private static final long serialVersionUID = 457280774604652610L;

	private String userToken;

	private String vehicleID;

	private String alert;

	private String crash;

	private String other;

	private String status;


	public PhoneNumbersBean() {
	}
	
	public PhoneNumbersBean(PhoneNumbers vehicle) {
		try {
			BeanUtils.copyProperties(this, vehicle);
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	public static UpdatePhoneNumbers asPhoneNumbers(PhoneNumbersBean personBean) {
		try {
			UpdatePhoneNumbers result = new UpdatePhoneNumbers();
			BeanUtils.copyProperties(result, personBean);
			return result;
		} catch (IllegalAccessException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (InvocationTargetException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PhoneNumbers [");
		if (userToken != null) {
			builder.append("userToken=");
			builder.append(userToken);
			builder.append(", ");
		}
		if (vehicleID != null) {
			builder.append("vehicleID=");
			builder.append(vehicleID);
			builder.append(", ");
		}
		if (alert != null) {
			builder.append("alerts=");
			builder.append(alert);
			builder.append(", ");
		}
		if (crash != null) {
			builder.append("crash=");
			builder.append(crash);
			builder.append(", ");
		}
		if (other != null) {
			builder.append("other=");
			builder.append(other);
		}
		builder.append(']');
		return builder.toString();
	}


	public String getUserToken() {
		return userToken;
	}


	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}


	public String getVehicleID() {
		return vehicleID;
	}


	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}


	public String getAlert() {
		return alert;
	}


	public void setAlert(String alert) {
		this.alert = alert;
	}


	public String getCrash() {
		return crash;
	}


	public void setCrash(String crash) {
		this.crash = crash;
	}


	public String getOther() {
		return other;
	}


	public void setOther(String other) {
		this.other = other;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(PhoneNumbersBean.class);
	}
}
