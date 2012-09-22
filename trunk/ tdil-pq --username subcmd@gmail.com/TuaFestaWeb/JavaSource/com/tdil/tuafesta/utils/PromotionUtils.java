package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.dao.SellDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Promotion;
import com.tdil.tuafesta.model.PromotionPhotoExample;
import com.tdil.tuafesta.model.valueobjects.PromotionValueObject;

public class PromotionUtils {

	@SuppressWarnings("unchecked")
	public static List<PromotionValueObject> getActivePromotions()  {
		try {
			return (List<PromotionValueObject>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return DAOManager.getPromotionDAO().selectActivePromotions();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<PromotionValueObject>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<PromotionValueObject> getActivePromotionsWithSells()  {
		try {
			return (List<PromotionValueObject>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					List<PromotionValueObject> result = DAOManager.getPromotionDAO().selectActivePromotions();
					SellDAO sellDAO = DAOManager.getSellDAO();
					for (PromotionValueObject pvo : result) {
						pvo.setSells(sellDAO.selectSellsForPromotion(pvo.getId()));
					}
					return result;
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<PromotionValueObject>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static PromotionValueObject getActivePromotion(final int id)  {
		try {
			return (PromotionValueObject)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					Promotion promotion = DAOManager.getPromotionDAO().selectPromotionByPrimaryKey(id);
					PromotionValueObject promotionValueObject = new PromotionValueObject(promotion);
					SellDAO sellDAO = DAOManager.getSellDAO();
					promotionValueObject.setSells(sellDAO.selectSellsForPromotion(id));
					PromotionPhotoExample photoExample = new PromotionPhotoExample();
					photoExample.createCriteria().andIdPromotionEqualTo(id);
					photoExample.setOrderByClause("orderNumber");
					promotionValueObject.setAllImages(DAOManager.getPromotionPhotoDAO().selectPromotionPhotoByExample(photoExample));
					return promotionValueObject;
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
