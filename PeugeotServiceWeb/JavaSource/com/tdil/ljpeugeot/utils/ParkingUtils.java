package com.tdil.ljpeugeot.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.javadocmd.simplelatlng.LatLng;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.PointOfInterest;
import com.tdil.ljpeugeot.model.PointOfInterestExample;
import com.tdil.ljpeugeot.poi.PointOfInterestConstants;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalActionWithResult;

public class ParkingUtils {

	private static final class GetParkings implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			PointOfInterestExample pointOfInterestExample = new PointOfInterestExample();
			pointOfInterestExample.createCriteria().andTypeEqualTo(PointOfInterestConstants.PARKINGS_TYPE).andSubtypeEqualTo(PointOfInterestConstants.PARKINGS_SUBTYPE);
			return DAOManager.getPointOfInterestDAO().selectPointOfInterestByExample(pointOfInterestExample);
		}
	}
	
	private static final class GetPois implements TransactionalActionWithResult {
		
		private String poiType;
		
		public GetPois(String poiType) {
			super();
			this.poiType = poiType;
		}

		public Object executeInTransaction() throws SQLException {
			PointOfInterestExample pointOfInterestExample = new PointOfInterestExample();
			pointOfInterestExample.createCriteria().andTypeEqualTo(Integer.valueOf(poiType));
			return DAOManager.getPointOfInterestDAO().selectPointOfInterestByExample(pointOfInterestExample);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<PointOfInterest> getParkings() {
		try {
			List<PointOfInterest> result = (List<PointOfInterest>)TransactionProvider.executeInTransactionWithResult(new GetParkings());
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<PointOfInterest>();
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ParkingUtils.class);
	}

	public static LatLng getLatLng(PointOfInterest poi) {
		return new LatLng(poi.getLat().doubleValue(), poi.getLon().doubleValue());
	}

	public static List<PointOfInterest> getPois(String type) {
		try {
			List<PointOfInterest> result = (List<PointOfInterest>)TransactionProvider.executeInTransactionWithResult(new GetPois(type));
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<PointOfInterest>();
		}
	}

}
