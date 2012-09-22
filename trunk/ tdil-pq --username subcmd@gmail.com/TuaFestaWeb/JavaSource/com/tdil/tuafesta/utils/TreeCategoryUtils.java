package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.cache.CacheEntry;
import com.tdil.cache.CacheManager;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.dao.CategoryDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Category;
import com.tdil.tuafesta.model.CategoryExample;
import com.tdil.tuafesta.model.CategoryExample.Criteria;

public class TreeCategoryUtils {

	public static String getPrefixFor(CategoryTreeNode profesionalCategoryTreeNode) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < profesionalCategoryTreeNode.getLevel(); i++) {
			result.append("&nbsp;&nbsp;");
		}
		return result.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static List<CategoryTreeNode> getTree(final boolean onlyActive, final int type)  {
		try {
			return (List<CategoryTreeNode>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return getTreeInTransaction(onlyActive, type);
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<CategoryTreeNode>();
		}
	}
	
	public static String getCategoryPath(int idCategory) throws SQLException {
		if (idCategory == 0) {
			return "";
		} else {
			CategoryDAO productCategoryDAO = DAOManager.getCategoryDAO();
			Category productCategory = productCategoryDAO.selectCategoryByPrimaryKey(idCategory);
			if (productCategory.getParentId() != 0) {
				return getCategoryPath(productCategory.getParentId()) + " > " + productCategory.getName();
			} else {
				return productCategory.getName();
			}
		}
	}

	public static List<CategoryTreeNode> getTreeInTransaction(boolean onlyActive, int type) throws SQLException {
		Integer version = CacheRegionUtils.getVersionInTransaction(Category.class.getName());
		CacheEntry<List<CategoryTreeNode>> cached = CacheManager.getCacheEntry(Category.class.getName(), "TREE-" + onlyActive + "-" + type, version);
		if (cached != null) {
			return cached.getValue();
		}
		CategoryDAO profesionalCategoryDAO = DAOManager.getCategoryDAO();
		CategoryExample example = new CategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(0).andTypeEqualTo(type);
		if (onlyActive) {
			criteria.andDeletedEqualTo(0);
		}
		example.setOrderByClause("name");
		
		List<Category> temp = profesionalCategoryDAO.selectCategoryByExample(example);
		ArrayList<CategoryTreeNode> result = new ArrayList<CategoryTreeNode>();
		for (Category profesionalCategory : temp) {
			CategoryTreeNode newTree = new CategoryTreeNode(profesionalCategory);
			result.add(getTreeFor(newTree, onlyActive, type));
		}
		
		CacheManager.put(Category.class.getName(), "TREE-" + onlyActive + "-" + type, result, version);
		return result;
	}

	private static CategoryTreeNode getTreeFor(CategoryTreeNode tree, boolean onlyActive, int type) throws SQLException {
		CategoryDAO profesionalCategoryDAO = DAOManager.getCategoryDAO();
		CategoryExample example = new CategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(tree.getCategory().getId()).andTypeEqualTo(type);
		if (onlyActive) {
			criteria.andDeletedEqualTo(0);
		}
		example.setOrderByClause("name");
		List<Category> temp = profesionalCategoryDAO.selectCategoryByExample(example);
		for (Category pro : temp) {
			CategoryTreeNode newTree = new CategoryTreeNode(pro);
			newTree.setLevel(tree.getLevel() + 1);
			tree.addChild(getTreeFor(newTree, onlyActive, type));
		}
		return tree;
	}

	public static List<Integer> selectChildsOf(List<CategoryTreeNode> tree, int catId) {
		List<Integer> result = new ArrayList<Integer>();
		for (CategoryTreeNode productCategoryTreeNode : tree) {
			productCategoryTreeNode.addChildsOf(result, catId);
		}
		return result;
	}
}
