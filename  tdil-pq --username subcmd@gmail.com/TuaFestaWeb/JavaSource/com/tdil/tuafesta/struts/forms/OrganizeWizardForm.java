package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.SearchForm;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Category;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.tuafesta.struts.forms.beans.SearchSellBean;
import com.tdil.tuafesta.utils.CategoryTreeNode;
import com.tdil.tuafesta.utils.TreeCategoryUtils;

public class OrganizeWizardForm extends ActionForm implements SearchForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5829022148346001648L;
	
	private List<SearchSellBean> searchSellBeans = new ArrayList<SearchSellBean>();
	
	private String productId;
	private String product;
	private String productPath;
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

			List<SellSearchCategories> product = new ArrayList<OrganizeWizardForm.SellSearchCategories>();
			List<SellSearchCategories> services = new ArrayList<OrganizeWizardForm.SellSearchCategories>();
			fillSearchCategories(product, services);
			
			for (SellSearchCategories prodCat : product) {
				// TODO if por el ge level
				searchResult.addAll(DAOManager.getSellDAO().selectProductSellsByCategoriesAndPrice(prodCat.getCategoriesIds(), prodCat.getMaxPrice()));
			}
			
			if (StringUtils.isEmpty(this.getGeoLevelId())) {
				for (SellSearchCategories servCat : services) {
					searchResult.addAll(DAOManager.getSellDAO().selectServiceSellsByCategoriesAndPrice(servCat.getCategoriesIds(), servCat.getMaxPrice()));
				}
			} else {
				int geoLevelId = Integer.parseInt(this.getGeoLevelId());
				int levelInt = Integer.parseInt(this.getLevel());
				Map<String, Object> geosearch = new HashMap<String, Object>();
				if (levelInt == 4) {
					Geo4 geo4 = DAOManager.getGeo4DAO().selectGeo4ByPrimaryKey(geoLevelId);
					geosearch.put("level4", geo4.getId());
					geosearch.put("level3", geo4.getGeo3Id());
					Geo3 geo3 = DAOManager.getGeo3DAO().selectGeo3ByPrimaryKey(geo4.getGeo3Id());
					geosearch.put("level2", geo3.getGeo2Id());
				}
				if (levelInt == 3) {
					Geo3 geo3 = DAOManager.getGeo3DAO().selectGeo3ByPrimaryKey(geoLevelId);
					geosearch.put("level3", geo3.getId());
					geosearch.put("level2", geo3.getGeo2Id());
				}
				if (levelInt == 2) {
					geosearch.put("level2", geoLevelId);
				}
				for (SellSearchCategories servCat : services) {
					searchResult.addAll(DAOManager.getSellDAO().selectServicesSellsByCategoriesAndPriceAndGeoLevel(servCat.getCategoriesIds(), servCat.getMaxPrice(), geosearch));
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void fillSearchCategories(List<SellSearchCategories> product2, List<SellSearchCategories> services) throws NumberFormatException, SQLException {
		for (SearchSellBean searchBean : searchSellBeans) {
			
			Category category = DAOManager.getCategoryDAO().selectCategoryByPrimaryKey(Integer.valueOf(searchBean.getProductId()));
			if (category.getType().equals(SellType.PRODUCT)) {
				List<CategoryTreeNode> tree = TreeCategoryUtils.getTreeInTransaction(true, SellType.PRODUCT);
				List<Integer> catids = TreeCategoryUtils.selectChildsOf(tree, category.getId());
				catids.add(category.getId());
				product2.add(new SellSearchCategories(searchBean.getMaxPrice(), catids));
			} else {
				List<CategoryTreeNode> tree = TreeCategoryUtils.getTreeInTransaction(true, SellType.SERVICE);
				List<Integer> catids = TreeCategoryUtils.selectChildsOf(tree, category.getId());
				catids.add(category.getId());
				services.add(new SellSearchCategories(searchBean.getMaxPrice(), catids));
			}
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
		SearchSellBean searchSellBean = new SearchSellBean();
		searchSellBean.setProduct(this.getProductPath());
		searchSellBean.setProductId(this.getProduct());
		if (StringUtils.isNumeric(this.getMaxPrice())) {
			searchSellBean.setMaxPrice(this.getMaxPrice());
		} else {
			searchSellBean.setMaxPrice(null);
		}
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductPath() {
		return productPath;
	}

	public void setProductPath(String productPath) {
		this.productPath = productPath;
	}
	
	private class SellSearchCategories {
		
		private String maxPrice;
		private List<Integer> categoriesIds = new ArrayList<Integer>();
		
		public SellSearchCategories(String maxPrice, List<Integer> categoriesIds) {
			super();
			this.maxPrice = maxPrice;
			this.categoriesIds = categoriesIds;
		}

		public String getMaxPrice() {
			return maxPrice;
		}

		public List<Integer> getCategoriesIds() {
			return categoriesIds;
		}
		
		
		
	}

}
