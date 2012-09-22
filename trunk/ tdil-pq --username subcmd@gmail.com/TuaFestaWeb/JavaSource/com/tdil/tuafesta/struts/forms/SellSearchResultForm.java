package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.SellDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.valueobjects.CategoryValueObject;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.tuafesta.stats.StatisticType;
import com.tdil.tuafesta.stats.StatsManager;
import com.tdil.tuafesta.utils.CategoryTreeNode;
import com.tdil.tuafesta.utils.TreeCategoryUtils;

public class SellSearchResultForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private int id;
	private int objectId;
	
	private List<SellValueObject> searchResult;
	
	@Override
	public void reset() throws SQLException {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
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
	
	public void searchByGeoLevel4(int id) throws SQLException, ValidationException {
		StatsManager.addStat(StatisticType.GEO_LEVEL_SEARCH, id, 4, null);
		Geo4 geo4 = new Geo4();
		geo4.setId(id);
		setSearchResult(DAOManager.getSellDAO().selectSellsByGeo4(geo4));
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

	public List<SellValueObject> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<SellValueObject> searchResult) {
		this.searchResult = searchResult;
	}

	public void searchByCategory(int catType, int catId) throws SQLException {
		SellDAO sellDao = DAOManager.getSellDAO();
		if (catType == CategoryValueObject.PRODUCT) {
			StatsManager.addStat(StatisticType.PROD_CATEGORY_SEARCH, catId, null); // stat
			List<SellValueObject> sellValueObject = sellDao.selectProductSellsByCategory(catId);
			List<CategoryTreeNode> tree = TreeCategoryUtils.getTreeInTransaction(true, SellType.PRODUCT);
			List<Integer> catids = TreeCategoryUtils.selectChildsOf(tree, catId);
			if (!catids.isEmpty()) {
				sellValueObject.addAll(sellDao.selectProductSellsByCategories(catids));
			}
			setSearchResult(sellValueObject);
		} else {
			StatsManager.addStat(StatisticType.SERV_CATEGORY_SEARCH, catId, null); // stat
			List<SellValueObject> sellValueObject = sellDao.selectServiceSellsByCategory(catId);
			List<CategoryTreeNode> tree = TreeCategoryUtils.getTreeInTransaction(true, SellType.SERVICE);
			List<Integer> catids = TreeCategoryUtils.selectChildsOf(tree, catId);
			if (!catids.isEmpty()) {
				sellValueObject.addAll(sellDao.selectServiceSellsByCategories(catids));
			}
			setSearchResult(sellValueObject);
		}
		
	}


}
