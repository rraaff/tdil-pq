package com.tdil.tuafesta.struts.forms;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.AdvertisementDAO;
import com.tdil.tuafesta.dao.ProfesionalDAO;
import com.tdil.tuafesta.dao.SellDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.AdTarget;
import com.tdil.tuafesta.model.AdType;
import com.tdil.tuafesta.model.Advertisement;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellExample;
import com.tdil.tuafesta.model.valueobjects.AdvertisementValueObject;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.utils.DateUtils;

public class AdvertisementForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(AdvertisementForm.class);

	private int id;

	private int objectId;

	// search profesional for add
	private String profesionalNameSearch;
	private List<Profesional> profesionalSearch = new ArrayList<Profesional>();

	private boolean profesionalAd;
	private Profesional profesional;
	private List<Sell> sells = new ArrayList<Sell>();
	private Sell sell;
	private int adTarget = AdTarget.PROFESIONAL;
	private int type = AdType.NORMAL;
	private boolean paidup;
	private String price = "0";
	private String fromDate;
	private String toDate;

	// search to edit
	private String profesionalNameSearchAd;
	private String profesionalDateSearch;
	private List<AdvertisementValueObject> search = new ArrayList<AdvertisementValueObject>();

	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		Advertisement professionalAdvertisement = DAOManager.getAdvertisementDAO().selectAdvertisementByPrimaryKey(this.getObjectId());
		professionalAdvertisement.setDeleted(professionalAdvertisement.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getAdvertisementDAO().updateAdvertisementByPrimaryKeySelective(professionalAdvertisement);
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.profesionalAd = false;
		this.paidup = false;
	}
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.profesionalAd = false;
		this.profesional = null;
		this.adTarget = AdTarget.PROFESIONAL;
		this.sell = null;
		this.type = AdType.NORMAL;
		this.paidup = false;
		this.search = null;
		this.profesionalNameSearch = null;
		this.profesionalSearch = new ArrayList<Profesional>();
		
		this.price = "0";
		this.fromDate = null;
		this.toDate = null;
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		AdvertisementDAO adDao = DAOManager.getAdvertisementDAO();
		ProfesionalDAO profesionalDao = DAOManager.getProfesionalDAO();
		SellDAO sellDao = DAOManager.getSellDAO();
		Advertisement ad = adDao.selectAdvertisementByPrimaryKey(id);
		this.setProfesional(profesionalDao.selectProfesionalByPrimaryKey(ad.getIdProfesional()));
		int sellId = ad.getIdSell();
		if (sellId != 0) {
			this.setSell(sellDao.selectSellByPrimaryKey(sellId));
		}
		this.setType(ad.getType());
		this.setFromDate(DateUtils.formatDate(ad.getFromdate()));
		this.setToDate(DateUtils.formatDate(ad.getTodate()));
		this.setPaidup(ad.getPaidup().equals(1));
		this.setPrice(ad.getPrice().toString());
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		// TODO VALIDACIONES
	}

	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO VALIDACIONES
	}

	@Override
	public void save() throws SQLException, ValidationException {
		AdvertisementDAO adDao = DAOManager.getAdvertisementDAO();
		if (this.getObjectId() == 0) {
			Advertisement ad = new Advertisement();
			setData(ad);
			ad.setDeleted(0);
			adDao.insertAdvertisement(ad);
		} else {
			Advertisement ad = DAOManager.getAdvertisementDAO().selectAdvertisementByPrimaryKey(this.getObjectId());
			setData(ad);
			adDao.updateAdvertisementByPrimaryKey(ad);
		}
	}

	private void setData(Advertisement ad) {
		ad.setIdProfesional(this.getProfesional().getId());
		ad.setIdSell(this.getSell() == null ? 0 : this.getSell().getId());
		ad.setType(this.getType());
		ad.setFromdate(com.tdil.utils.DateUtils.parseDate(this.getFromDate()));
		ad.setTodate(com.tdil.utils.DateUtils.parseDate(this.getToDate()));
		ad.setPaidup(this.isPaidup() ? 1 : 0);
		BigDecimal price = new BigDecimal(this.getPrice());
		ad.setPrice(price);
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

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public String getProfesionalDateSearch() {
		return profesionalDateSearch;
	}

	public void setProfesionalDateSearch(String profesionalDateSearch) {
		this.profesionalDateSearch = profesionalDateSearch;
	}

	public void searchProfesionals() {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("name", "%" + this.getProfesionalNameSearch() + "%");
			this.setProfesionalSearch(DAOManager.getProfesionalDAO().selectProfesionalBy(params)); // TODO																														// insensitivity
		} catch (Exception e) {
			Log.error(e.getMessage(), e);
		}
	}
	
	public void searchHighlightedProfesionals() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isEmpty(this.getProfesionalNameSearchAd())) {
				params.put("name", "%" + this.getProfesionalNameSearchAd() + "%");
			}
			Date datesearch = com.tdil.utils.DateUtils.parseDate(this.profesionalDateSearch);
			if (datesearch != null) {
				params.put("dateactive", datesearch);
			}
			// TODO Hacer la busqueda en el DAO
																														// case
																														// insensitivity
		} catch (Exception e) {
			Log.error(e.getMessage(), e);
		}
	}

	public List<Profesional> getProfesionalSearch() {
		return profesionalSearch;
	}

	public void setProfesionalSearch(List<Profesional> profesionalSearch) {
		this.profesionalSearch = profesionalSearch;
	}

	public void selectProfesional(int profesionalId) throws SQLException {
		Profesional profesional = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(profesionalId);
		this.setProfesional(profesional);
		SellExample sellExample = new SellExample();
		sellExample.createCriteria().andApprovedEqualTo(1).andDeletedEqualTo(0).andIdProfesionalEqualTo(profesionalId);
		setSells(DAOManager.getSellDAO().selectSellByExample(sellExample));

	}
	
	public void selectSell(int sellId) throws SQLException {
		Sell sell = DAOManager.getSellDAO().selectSellByPrimaryKey(sellId);
		this.setSell(sell);
	}

	public boolean isProfesionalAd() {
		return profesionalAd;
	}

	public void setProfesionalAd(boolean profesionalAd) {
		this.profesionalAd = profesionalAd;
	}

	public Sell getSell() {
		return sell;
	}

	public void setSell(Sell sell) {
		this.sell = sell;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isPaidup() {
		return paidup;
	}

	public void setPaidup(boolean paidup) {
		this.paidup = paidup;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<AdvertisementValueObject> getSearch() {
		return search;
	}

	public void setSearch(List<AdvertisementValueObject> search) {
		this.search = search;
	}
	public String getProfesionalNameSearch() {
		return profesionalNameSearch;
	}
	public void setProfesionalNameSearch(String profesionalNameSearch) {
		this.profesionalNameSearch = profesionalNameSearch;
	}
	public String getProfesionalNameSearchAd() {
		return profesionalNameSearchAd;
	}
	public void setProfesionalNameSearchAd(String profesionalNameSearchAd) {
		this.profesionalNameSearchAd = profesionalNameSearchAd;
	}
	public int getAdTarget() {
		return adTarget;
	}
	public void setAdTarget(int adTarget) {
		this.adTarget = adTarget;
	}
	public List<Sell> getSells() {
		return sells;
	}
	public void setSells(List<Sell> sells) {
		this.sells = sells;
	}
}
