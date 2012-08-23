package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.cache.CacheEntry;
import com.tdil.cache.CacheManager;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.dao.ProductCategoryDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ProductCategory;
import com.tdil.tuafesta.model.ProductCategoryExample;
import com.tdil.tuafesta.model.ProductCategoryExample.Criteria;

public class ProductCategoryUtils {

	public static String getPrefixFor(ProductCategoryTreeNode profesionalCategoryTreeNode) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < profesionalCategoryTreeNode.getLevel(); i++) {
			result.append("&nbsp;&nbsp;");
		}
		return result.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static List<ProductCategoryTreeNode> getTree(final boolean onlyActive)  {
		try {
			return (List<ProductCategoryTreeNode>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return getTreeInTransaction(onlyActive);
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<ProductCategoryTreeNode>();
		}
	}
	
	public static String getCategoryPath(int idCategory) throws SQLException {
		if (idCategory == 0) {
			return "";
		} else {
			ProductCategoryDAO productCategoryDAO = DAOManager.getProductCategoryDAO();
			ProductCategory productCategory = productCategoryDAO.selectProductCategoryByPrimaryKey(idCategory);
			if (productCategory.getParentId() != 0) {
				return getCategoryPath(productCategory.getParentId()) + " > " + productCategory.getName();
			} else {
				return productCategory.getName();
			}
		}
	}

	public static List<ProductCategoryTreeNode> getTreeInTransaction(boolean onlyActive) throws SQLException {
		Integer version = CacheRegionUtils.getVersionInTransaction(ProductCategory.class.getName());
		CacheEntry<List<ProductCategoryTreeNode>> cached = CacheManager.getCacheEntry(ProductCategory.class.getName(), "TREE-" + onlyActive, version);
		if (cached != null) {
			return cached.getValue();
		}
		ProductCategoryDAO profesionalCategoryDAO = DAOManager.getProductCategoryDAO();
		ProductCategoryExample example = new ProductCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(0);
		if (onlyActive) {
			criteria.andDeletedEqualTo(0);
		}
		example.setOrderByClause("name");
		
		List<ProductCategory> temp = profesionalCategoryDAO.selectProductCategoryByExample(example);
		ArrayList<ProductCategoryTreeNode> result = new ArrayList<ProductCategoryTreeNode>();
		for (ProductCategory profesionalCategory : temp) {
			ProductCategoryTreeNode newTree = new ProductCategoryTreeNode(profesionalCategory);
			result.add(getTreeFor(newTree, onlyActive));
		}
		
		CacheManager.put(ProductCategory.class.getName(), "TREE-" + onlyActive, result, version);
		return result;
	}

	private static ProductCategoryTreeNode getTreeFor(ProductCategoryTreeNode tree, boolean onlyActive) throws SQLException {
		ProductCategoryDAO profesionalCategoryDAO = DAOManager.getProductCategoryDAO();
		ProductCategoryExample example = new ProductCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(tree.getProductCategory().getId());
		if (onlyActive) {
			criteria.andDeletedEqualTo(0);
		}
		example.setOrderByClause("name");
		List<ProductCategory> temp = profesionalCategoryDAO.selectProductCategoryByExample(example);
		for (ProductCategory pro : temp) {
			ProductCategoryTreeNode newTree = new ProductCategoryTreeNode(pro);
			newTree.setLevel(tree.getLevel() + 1);
			tree.addChild(getTreeFor(newTree, onlyActive));
		}
		return tree;
	}

	public static List<Integer> selectChildsOf(List<ProductCategoryTreeNode> tree, int catId) {
		List<Integer> result = new ArrayList<Integer>();
		for (ProductCategoryTreeNode productCategoryTreeNode : tree) {
			productCategoryTreeNode.addChildsOf(result, catId);
		}
		return result;
	}
}
