package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalChange;
import com.tdil.tuafesta.model.WallWrittingExample;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.tuafesta.utils.ProfesionalUtils;

public class ProfesionalHomeForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private boolean personalDataChanged = false;
	private boolean businessDataChanged = false;
	private Profesional profesional;
	private GeoLevelValueObject location;
	private int wallModerationPendingCount;
	
	private List<SellValueObject> sells = new ArrayList<SellValueObject>();
	
	@Override
	public void reset() throws SQLException {
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		profesional = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(id);
		ProfesionalChange profesionalChange = DAOManager.getProfesionalChangeDAO().selectProfesionalChangeByPrimaryKey(profesional.getIdProfesionalChange());
		personalDataChanged = ProfesionalUtils.mergePersonalData(profesional, profesionalChange);
		businessDataChanged = ProfesionalUtils.mergeBusinessData(profesional, profesionalChange);
		
		location = DAOManager.getGeo4DAO().selectGeoLevelsByGeo4(profesional.getIdGeolevel());
		
		sells = DAOManager.getSellDAO().selectProductSellsByProfesional(id);
		sells.addAll(DAOManager.getSellDAO().selectServiceSellsByProfesional(id));
		
		WallWrittingExample wallWrittingExample = new WallWrittingExample();
		wallWrittingExample.createCriteria().andIdWallEqualTo(profesional.getIdWall()).andDeletedEqualTo(0).andResponsePendingEqualTo(1);
		setWallModerationPendingCount(DAOManager.getWallWrittingDAO().countWallWrittingByExample(wallWrittingExample));
	}
	
	@Override
	public void basicValidate(ValidationError error) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		
	}	

	private static Logger getLog() {
		return LoggerProvider.getLogger(ProfesionalHomeForm.class);
	}
	public Profesional getProfesional() {
		return profesional;
	}
	public GeoLevelValueObject getLocation() {
		return location;
	}
	public void setLocation(GeoLevelValueObject location) {
		this.location = location;
	}
	public boolean isPersonalDataChanged() {
		return personalDataChanged;
	}
	public void setPersonalDataChanged(boolean personalDataChanged) {
		this.personalDataChanged = personalDataChanged;
	}
	public boolean isBusinessDataChanged() {
		return businessDataChanged;
	}
	public void setBusinessDataChanged(boolean businessDataChanged) {
		this.businessDataChanged = businessDataChanged;
	}
	public List<SellValueObject> getSells() {
		return sells;
	}
	public void setSells(List<SellValueObject> sells) {
		this.sells = sells;
	}
	public int getWallModerationPendingCount() {
		return wallModerationPendingCount;
	}
	public void setWallModerationPendingCount(int wallModerationPendingCount) {
		this.wallModerationPendingCount = wallModerationPendingCount;
	}
	
}
