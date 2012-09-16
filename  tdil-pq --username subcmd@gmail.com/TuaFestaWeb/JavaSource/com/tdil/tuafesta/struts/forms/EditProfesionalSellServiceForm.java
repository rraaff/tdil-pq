package com.tdil.tuafesta.struts.forms;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.tdil.struts.ValidationException;
import com.tdil.tuafesta.dao.SellDAO;
import com.tdil.tuafesta.dao.SellMediaDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellExample;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.tuafesta.struts.forms.beans.SellBean;

public class EditProfesionalSellServiceForm extends EditProfesionalSellForm implements ProfesionalSellForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	// abm de servicios
	private String serviceId;
	private String serviceCategorySelected;
	private String serviceAutocompleter;
	private String serviceSelectedText;
	private String serviceReferenceprice;
	
	
	@Override
	protected void loadForEdit(SellBean edited) {
		this.serviceId = String.valueOf(edited.getProductId());
		this.serviceCategorySelected = edited.getCategoryText();
		this.serviceSelectedText = edited.getName();
		this.serviceReferenceprice = edited.getReferencePrice();
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		this.reset();
		this.setId(id);
		SellDAO sellDAO = DAOManager.getSellDAO();
		// obtengo las que tienen producto
		List<SellValueObject> sellVOs = sellDAO.selectServiceSellsByProfesional(id);
		for (SellValueObject sellValueObject : sellVOs) {
			this.getSells().add(new SellBean(sellValueObject));
		}
		// obtengo las que no tienen producto en rd
		SellExample sellExample = new SellExample();
		sellExample.createCriteria().andIdProfesionalEqualTo(id).andTypeEqualTo(SellType.SERVICE).andIdProdServEqualTo(0);
		List<Sell> sells = sellDAO.selectSellByExample(sellExample);
		for (Sell sell : sells) {
			this.getSells().add(new SellBean(sell));
		}
	}
	
	@Override
	public void reset() throws SQLException {
		super.reset();
		this.serviceId = null;
		this.serviceCategorySelected = null;
		this.serviceAutocompleter = null;
		this.serviceSelectedText = null;
		this.serviceReferenceprice = null;
	}
	
	@Override
	public void save() throws SQLException, ValidationException {
		SellDAO sellDAO = DAOManager.getSellDAO();
		SellMediaDAO sellMediaDAO = DAOManager.getSellMediaDAO();
		// calculo las que tenia
		Set<Integer> retained = new HashSet<Integer>();
		for (SellBean productBean : getSells()) {
			retained.add(productBean.getId());
		}
		// borro las que ya no estan
		SellExample sellExample = new SellExample();
		sellExample.createCriteria().andIdProfesionalEqualTo(this.getId()).andTypeEqualTo(SellType.SERVICE);
		List<Sell> sells = sellDAO.selectSellByExample(sellExample);
		for (Sell s : sells) {
			if (!retained.contains(s.getId())) {
				deleteSellMediaFor(s.getId());
				sellDAO.deleteSellByPrimaryKey(s.getId());
			}
		}
		for (SellBean productBean : getSells()) {
			if (productBean.getId() == 0) {
				// inserto las nuevas
				Sell sell = new Sell();
				sell.setIdProfesional(this.getId());
				sell.setType(productBean.getType());
				sell.setIdProdServ(productBean.getProductId());
				sell.setApproved(0);
				if (sell.getIdProdServ() == 0) {
					sell.setItem(productBean.getName());
				} else {
					// TODO AUTOAPP
					sell.setApproved(1);
				}
				// TODO ver tema de . , etc
				BigDecimal refPrice = new BigDecimal(productBean.getReferencePrice());
				sell.setReferenceprice(refPrice);
				sell.setDeleted(0);
				int sellId = sellDAO.insertSell(sell);
				createSellMedia(sellMediaDAO, sellId, productBean);
			} else {
				Sell sell = sellDAO.selectSellByPrimaryKey(productBean.getId());
				BigDecimal refPrice = new BigDecimal(productBean.getReferencePrice());
				sell.setReferenceprice(refPrice);
				// TODO esto deberia mandarlo a pending nuevamente
				sellDAO.updateSellByPrimaryKey(sell);
				createOrUpdateSellMedia(sellMediaDAO, productBean.getId(), productBean);
			}
		}
	}
	
	public void addProduct() {
		// TODO Auto-generated method stub
	}
	
	public boolean isServiceSelected() {
		return !StringUtils.isEmpty(this.getServiceId());
	}
	
	public void addService() {
		// TODO validaciones
		SellBean servicebean = null;
		if (edited != null) {
			servicebean = edited;
		} else {
			servicebean = new SellBean();
		}
		if (this.isServiceSelected()) {
			// serviceo elegido de la rd
			servicebean.setType(SellType.SERVICE);
			servicebean.setProductId(Integer.valueOf(this.getServiceId()));
			servicebean.setName(this.getServiceSelectedText());
			servicebean.setCategoryText(this.getServiceCategorySelected());
			servicebean.setReferencePrice(this.getServiceReferenceprice());
			servicebean.setMedia(this);
			if (edited == null) {
				this.getSells().add(0, servicebean);
			}
			cleanServiceFields();
		} else {
			// serviceo no rd
			servicebean.setType(SellType.SERVICE);
			servicebean.setProductId(0);
			servicebean.setName(this.getServiceAutocompleter());
			servicebean.setCategoryText("-");
			servicebean.setReferencePrice(this.getServiceReferenceprice());
			servicebean.setMedia(this);
			if (edited == null) {
				this.getSells().add(0, servicebean);
			}
			cleanServiceFields();
		}
		edited = null;
	}
	
	private void cleanServiceFields() {
		this.setServiceId(null);
		this.setServiceAutocompleter(null);
		this.setServiceSelectedText(null);
		this.setServiceCategorySelected(null);
		this.setServiceReferenceprice(null);
		clearMediaFields();
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceCategorySelected() {
		return serviceCategorySelected;
	}

	public void setServiceCategorySelected(String serviceCategorySelected) {
		this.serviceCategorySelected = serviceCategorySelected;
	}

	public String getServiceAutocompleter() {
		return serviceAutocompleter;
	}

	public void setServiceAutocompleter(String serviceAutocompleter) {
		this.serviceAutocompleter = serviceAutocompleter;
	}

	public String getServiceSelectedText() {
		return serviceSelectedText;
	}

	public void setServiceSelectedText(String serviceSelectedText) {
		this.serviceSelectedText = serviceSelectedText;
	}

	public String getServiceReferenceprice() {
		return serviceReferenceprice;
	}

	public void setServiceReferenceprice(String serviceReferenceprice) {
		this.serviceReferenceprice = serviceReferenceprice;
	}

}
