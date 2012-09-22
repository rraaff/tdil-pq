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

public class EditProfesionalSellProductForm extends EditProfesionalSellForm implements ProfesionalSellForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	private String productId;
	private String productCategorySelected;
	private String productAutocompleter;
	private String productSelectedText;
	private String referenceprice;
	
	@Override
	protected void loadForEdit(SellBean edited) {
		this.productId = String.valueOf(edited.getCategoryId());
		this.productCategorySelected = edited.getCategoryText();
		this.productSelectedText = edited.getName();
		this.referenceprice = edited.getReferencePrice();
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		this.reset();
		this.setId(id);
		SellDAO sellDAO = DAOManager.getSellDAO();
		// obtengo las que tienen producto
		List<SellValueObject> sellVOs = sellDAO.selectProductSellsByProfesional(id);
		for (SellValueObject sellValueObject : sellVOs) {
			this.getSells().add(new SellBean(sellValueObject));
		}
	}
	
	@Override
	public void reset() throws SQLException {
		super.reset();
		this.productId = null;
		this.productCategorySelected = null;
		this.productAutocompleter = null;
		this.productSelectedText = null;
		this.referenceprice = null;
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
		sellExample.createCriteria().andIdProfesionalEqualTo(this.getId()).andTypeEqualTo(SellType.PRODUCT);
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
				sell.setIdProdServ(productBean.getCategoryId());
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

	public boolean isProductSelected() {
		return !StringUtils.isEmpty(this.getProductId());
	}
	public void addService() {
		// TODO Auto-generated method stub
	}
	
	public void addProduct() {
		// TODO validaciones
		SellBean productbean = null;
		if (edited != null) {
			productbean = edited;
		} else {
			productbean = new SellBean();
		}
		if (this.isProductSelected()) {
			// producto elegido de la rd
			productbean.setType(SellType.PRODUCT);
			productbean.setCategoryId(Integer.valueOf(this.getProductId()));
			productbean.setName(this.getProductSelectedText());
			productbean.setCategoryText(this.getProductCategorySelected());
			productbean.setReferencePrice(this.getReferenceprice());
			productbean.setMedia(this);
			if (edited == null) {
				this.getSells().add(0, productbean);
			}
			cleanProductFields();
		} else {
			// producto no rd
			productbean.setType(SellType.PRODUCT);
			productbean.setCategoryId(0);
			productbean.setName(this.getProductAutocompleter());
			productbean.setCategoryText("-");
			productbean.setReferencePrice(this.getReferenceprice());
			productbean.setMedia(this);
			if (edited == null) {
				this.getSells().add(0, productbean);
			}
			cleanProductFields();
		}
		edited = null;
	}
	
	private void cleanProductFields() {
		this.setProductId(null);
		this.setProductAutocompleter(null);
		this.setProductSelectedText(null);
		this.setProductCategorySelected(null);
		this.setReferenceprice(null);
		clearMediaFields();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCategorySelected() {
		return productCategorySelected;
	}

	public void setProductCategorySelected(String productCategorySelected) {
		this.productCategorySelected = productCategorySelected;
	}

	public String getProductAutocompleter() {
		return productAutocompleter;
	}

	public void setProductAutocompleter(String productAutocompleter) {
		this.productAutocompleter = productAutocompleter;
	}

	public String getProductSelectedText() {
		return productSelectedText;
	}

	public void setProductSelectedText(String productSelectedText) {
		this.productSelectedText = productSelectedText;
	}

	public String getReferenceprice() {
		return referenceprice;
	}

	public void setReferenceprice(String referenceprice) {
		this.referenceprice = referenceprice;
	}
}
