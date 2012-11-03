package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ProfesionalDAO;
import com.tdil.tuafesta.dao.PromotionDAO;
import com.tdil.tuafesta.dao.PromotionSellDAO;
import com.tdil.tuafesta.dao.SellDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.Promotion;
import com.tdil.tuafesta.model.PromotionSell;
import com.tdil.tuafesta.model.PromotionSellExample;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.stats.StatisticType;
import com.tdil.tuafesta.stats.StatsManager;

public class PromotionContactForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private int id;
	private int objectId;
	private Promotion promotion;
	private List<Sell> sells = new ArrayList<Sell>();
	private List<Profesional> profesionals = new ArrayList<Profesional>();
	
	@Override
	public void reset() throws SQLException {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		sells.clear();
		profesionals.clear();
		Set<Integer> profs = new HashSet<Integer>();
		StatsManager.addStat(StatisticType.PROMOTION_CONTACT, id, null);
		PromotionDAO promotionDAO = DAOManager.getPromotionDAO();
		PromotionSellDAO promotionSellDAO = DAOManager.getPromotionSellDAO();
		SellDAO sellDAO = DAOManager.getSellDAO();
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		setPromotion(promotionDAO.selectPromotionByPrimaryKey(id));
		PromotionSellExample promotionSellExample = new PromotionSellExample();
		promotionSellExample.createCriteria().andIdPromotionEqualTo(id);
		List<PromotionSell> promotionSells = promotionSellDAO.selectPromotionSellByExample(promotionSellExample);
		for (PromotionSell pSell : promotionSells) {
			Sell sell = sellDAO.selectSellByPrimaryKey(pSell.getIdSell());
			this.sells.add(sell);
			if (!profs.contains(sell.getIdProfesional())) {
				this.profesionals.add(profesionalDAO.selectProfesionalByPrimaryKey(sell.getIdProfesional()));
			}
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

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	public List<Profesional> getUniqueProfesionals() {
		List<Profesional> result = new ArrayList<Profesional>();
		Set<Integer> added = new HashSet<Integer>();
		for (Profesional p : getProfesionals()) {
			if (!added.contains(p.getId())) {
				added.add(p.getId());
				result.add(p);
			}
		}
		return result;
	}

	public List<Profesional> getProfesionals() {
		return profesionals;
	}

	public void setProfesionals(List<Profesional> profesionals) {
		this.profesionals = profesionals;
	}

}
