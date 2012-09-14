package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ServiceAreaDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ServiceArea;
import com.tdil.tuafesta.model.ServiceAreaExample;
import com.tdil.tuafesta.struts.forms.beans.ServiceAreaBean;

public class EditProfesionalServiceAreaForm extends TransactionalValidationForm implements EditProfesionalDataForm, ServiceAreaForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	private int objectId;

	// abm de areas de servicios
	private List<ServiceAreaBean> serviceAreas = new ArrayList<ServiceAreaBean>();
	private String level;
	private String geoLevelId;
	private String serviceAreaAutocompleter;
	private String serviceAreaSelectedText;
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.serviceAreas = new ArrayList<ServiceAreaBean>();
		this.level = null;
		this.geoLevelId = null;
		this.serviceAreaAutocompleter = null;
		this.serviceAreaSelectedText = null;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		this.reset();
		
		objectId = id;
		ServiceAreaExample serviceAreaExample = new ServiceAreaExample();
		serviceAreaExample.createCriteria().andIdProfesionalEqualTo(id);
		List<ServiceArea> list = DAOManager.getServiceAreaDAO().selectServiceAreaByExample(serviceAreaExample);
		for (ServiceArea sa : list) {
			serviceAreas.add(new ServiceAreaBean(sa));
		}
	}
	
	public boolean isServiceAreaSelected() {
		return !StringUtils.isEmpty(this.getGeoLevelId());
	}
	
	public void addServiceArea() {
		ServiceAreaBean serviceAreaBean = new ServiceAreaBean();
		serviceAreaBean.setLevel(Integer.valueOf(this.getLevel()));
		serviceAreaBean.setGeoLevelId(Integer.valueOf(this.getGeoLevelId()));
		serviceAreaBean.setServiceAreaText(this.getServiceAreaSelectedText());
		this.getServiceAreas().add(0, serviceAreaBean);
		cleanServiceAreaFields();
	}
	
	public void cleanServiceAreaFields() {
		this.setLevel(null);
		this.setGeoLevelId(null);
		this.setServiceAreaAutocompleter(null);
		this.setServiceAreaSelectedText(null);
	}

	public void removeServiceArea(String index) {
		if (StringUtils.isEmpty(index)) {
			return;
		}
		if (!StringUtils.isNumeric(index)) {
			return;
		}
		int indexInt = Integer.parseInt(index);
		if (indexInt < getServiceAreas().size()) {
			this.getServiceAreas().remove(indexInt);
		}
	}
	
	@Override
	public void basicValidate(ValidationError validationError) {

	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		Set<Integer> retained = new HashSet<Integer>();
		ServiceAreaDAO serviceAreaDAO = DAOManager.getServiceAreaDAO();
		for (ServiceAreaBean serviceAreaBean : this.getServiceAreas()) {
			retained.add(serviceAreaBean.getId());
		}
		// Borro los que estaban y ya no estan
		ServiceAreaExample serviceAreaExample = new ServiceAreaExample();
		serviceAreaExample.createCriteria().andIdProfesionalEqualTo(id);
		List<ServiceArea> list = serviceAreaDAO.selectServiceAreaByExample(serviceAreaExample);
		for (ServiceArea sa : list) {
			if (!retained.contains(sa.getId())) {
				serviceAreaDAO.deleteServiceAreaByPrimaryKey(sa.getId());
			}
		}
		for (ServiceAreaBean serviceAreaBean : this.getServiceAreas()) {
			if (serviceAreaBean.getId() == 0) {
				ServiceArea serviceArea = new ServiceArea();
				serviceArea.setDeleted(0);
				serviceArea.setApproved(1); // TODO AUTOAPP
				serviceArea.setIdProfesional(this.getId());
				serviceArea.setLevel(serviceAreaBean.getLevel());
				serviceArea.setIdGeolevel(serviceAreaBean.getGeoLevelId());
				serviceAreaDAO.insertServiceArea(serviceArea);
			}
		}
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(EditProfesionalServiceAreaForm.class);
	}
	public List<ServiceAreaBean> getServiceAreas() {
		int index = 0;
		for (ServiceAreaBean bean : serviceAreas) {
			bean.setIndex(index++);
		}
		return serviceAreas;
	}
	public void setServiceAreas(List<ServiceAreaBean> serviceAreas) {
		this.serviceAreas = serviceAreas;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getGeoLevelId() {
		return geoLevelId;
	}
	public void setGeoLevelId(String geoLevelId) {
		this.geoLevelId = geoLevelId;
	}
	public String getServiceAreaAutocompleter() {
		return serviceAreaAutocompleter;
	}
	public void setServiceAreaAutocompleter(String serviceAreaAutocompleter) {
		this.serviceAreaAutocompleter = serviceAreaAutocompleter;
	}
	public String getServiceAreaSelectedText() {
		return serviceAreaSelectedText;
	}
	public void setServiceAreaSelectedText(String serviceAreaSelectedText) {
		this.serviceAreaSelectedText = serviceAreaSelectedText;
	}

}
