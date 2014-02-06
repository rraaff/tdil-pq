package com.tdil.ljpeugeot.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.City;
import com.tdil.ljpeugeot.model.CityExample;
import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.DealerExample;
import com.tdil.ljpeugeot.model.State;
import com.tdil.ljpeugeot.model.StateExample;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class DealersService {
	

	private static final class GetStates implements TransactionalActionWithResult<Collection<State>> {
		public GetStates() {
			super();
		}
		public Collection<State> executeInTransaction() throws SQLException {
			StateExample stateExample = new StateExample();
			stateExample.setOrderByClause("name");
			return DAOManager.getStateDAO().selectStateByExample(stateExample);
		}
	}
	
	private static final class GetCities implements TransactionalActionWithResult<Collection<City>> {
		private int idState;
		public GetCities(int idState) {
			super();
			this.idState = idState;
		}
		public Collection<City> executeInTransaction() throws SQLException {
			CityExample stateExample = new CityExample();
			stateExample.createCriteria().andIdStateEqualTo(this.idState);
			stateExample.setOrderByClause("name");
			return DAOManager.getCityDAO().selectCityByExample(stateExample);
		}
	}
	
	private static final class GetDealers implements TransactionalActionWithResult<Collection<Dealer>> {
		private int idCity;
		public GetDealers(int idCity) {
			super();
			this.idCity = idCity;
		}
		public Collection<Dealer> executeInTransaction() throws SQLException {
			DealerExample dealerExample = new DealerExample();
			dealerExample.createCriteria().andIdCityEqualTo(this.idCity);
			dealerExample.setOrderByClause("name");
			return DAOManager.getDealerDAO().selectDealerByExample(dealerExample);
		}
	}
	
	public static Collection<State> getStates() {
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
	
	public static Collection<City> getCities(int idState) {
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
	
	public static Collection<Dealer> getDealers(int idCity) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetDealers(idCity));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Dealer>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Dealer>();
		} 
	}	
	
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(DealersService.class);
	}
	
}
