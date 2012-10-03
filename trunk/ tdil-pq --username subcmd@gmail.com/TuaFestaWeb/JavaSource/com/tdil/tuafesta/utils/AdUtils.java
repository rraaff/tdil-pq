package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.dao.AdvertisementDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Geo4Example;

public class AdUtils {

	@SuppressWarnings("unchecked")
	public static AdsForHome getAdsHome()  {
		try {
			return (AdsForHome)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					AdvertisementDAO adDao = DAOManager.getAdvertisementDAO();
					AdsForHome adsForHome = new AdsForHome();
					adsForHome.setNormal(adDao.selectActiveNormalAds());
					adsForHome.setExtraWithoutFilter(adDao.selectActiveExtraAds());
					adsForHome.setExtraByCategory(adDao.selectActiveExtraAdsByCategory());
					return adsForHome;
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new AdsForHome();
		}
	}
	

}
