package com.tdil.lojack.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.PointOfInterest;
import com.tdil.lojack.model.PointOfInterestExample;
import com.tdil.lojack.poi.PointOfInterestConstants;
import com.tdil.struts.TransactionalActionWithResult;

public class ParkingUtils {

	private static final class GetParkings implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			PointOfInterestExample pointOfInterestExample = new PointOfInterestExample();
			pointOfInterestExample.createCriteria().andTypeEqualTo(PointOfInterestConstants.PARKINGS_TYPE).andSubtypeEqualTo(PointOfInterestConstants.PARKINGS_SUBTYPE);
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

}
