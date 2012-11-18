package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.SearchForm;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.tuafesta.struts.forms.beans.SearchSellBean;

public class OrganizeWizardForm extends ActionForm implements SearchForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5829022148346001648L;
	
	private List<SearchSellBean> searchSellBeans = new ArrayList<SearchSellBean>();
	
	private String product;
	private String maxPrice;
	
	private String geoLevelId;
	private String geoLevelSelectedText;
	private String level;
	private String geoLevel;
	
	private List<SellValueObject> searchResult;

	@Override
	public void search() throws ValidationException {
		try {
			searchResult = new ArrayList<SellValueObject>();
			for (SearchSellBean searchBean : searchSellBeans) {
				searchResult.addAll(DAOManager.getSellDAO().selectSellsByTextAndPrice(searchBean.getProduct(), searchBean.getMaxPrice()));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isGeoLevelSelected() {
		return this.getGeoLevelId() != null && this.getGeoLevelId().trim().length() > 0;
	}

	public List<SearchSellBean> getSearchSellBeans() {
		return searchSellBeans;
	}

	public void setSearchSellBeans(List<SearchSellBean> searchSellBeans) {
		this.searchSellBeans = searchSellBeans;
	}

	public String getGeoLevel() {
		return geoLevel;
	}

	public void setGeoLevel(String geoLevel) {
		this.geoLevel = geoLevel;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void addSearchSellBean() {
		// TODO validaciones
		SearchSellBean searchSellBean = new SearchSellBean();
		searchSellBean.setProduct(this.getProduct());
		searchSellBean.setMaxPrice(this.getMaxPrice());
		this.getSearchSellBeans().add(searchSellBean);
		
		this.setProduct(null);
		this.setMaxPrice(null);
	}

	public void reset() {
		this.setProduct(null);
		this.setMaxPrice(null);
		setSearchSellBeans(new ArrayList<SearchSellBean>());
	}

	public void removeSearchSellBean(String index) {
		if (StringUtils.isEmpty(index)) {
			return;
		}
		if (!StringUtils.isNumeric(index)) {
			return;
		}
		int indexInt = Integer.parseInt(index);
		if (indexInt < getSearchSellBeans().size()) {
			this.getSearchSellBeans().remove(indexInt);
		}
	}

	public List<SellValueObject> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<SellValueObject> searchResult) {
		this.searchResult = searchResult;
	}

	public String getGeoLevelId() {
		return geoLevelId;
	}

	public void setGeoLevelId(String geoLevelId) {
		this.geoLevelId = geoLevelId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getGeoLevelSelectedText() {
		return geoLevelSelectedText;
	}

	public void setGeoLevelSelectedText(String geoLevelSelectedText) {
		this.geoLevelSelectedText = geoLevelSelectedText;
	}

}
