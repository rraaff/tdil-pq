package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.dao.ServiceCategoryDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ServiceCategory;
import com.tdil.tuafesta.model.ServiceCategoryExample;
import com.tdil.tuafesta.model.ServiceCategoryExample.Criteria;

public class ServiceCategoryUtils {

	public static String getPrefixFor(ServiceCategoryTreeNode profesionalCategoryTreeNode) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < profesionalCategoryTreeNode.getLevel(); i++) {
			result.append("&nbsp;&nbsp;");
		}
		return result.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static List<ServiceCategoryTreeNode> getTree(final boolean onlyActive)  {
		try {
			return (List<ServiceCategoryTreeNode>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return ServiceCategoryUtils.getTreeInTransaction(onlyActive);
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<ServiceCategoryTreeNode>();
		}
	}
	
	public static String getCategoryPath(int idCategory) throws SQLException {
		if (idCategory == 0) {
			return "";
		} else {
			ServiceCategoryDAO productCategoryDAO = DAOManager.getServiceCategoryDAO();
			ServiceCategory productCategory = productCategoryDAO.selectServiceCategoryByPrimaryKey(idCategory);
			if (productCategory.getParentId() != 0) {
				return getCategoryPath(productCategory.getParentId()) + " > " + productCategory.getName();
			} else {
				return productCategory.getName();
			}
		}
	}

	public static List<ServiceCategoryTreeNode> getTreeInTransaction(boolean onlyActive) throws SQLException {
		ServiceCategoryDAO profesionalCategoryDAO = DAOManager.getServiceCategoryDAO();
		ServiceCategoryExample example = new ServiceCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(0);
		if (onlyActive) {
			criteria.andDeletedEqualTo(0);
		}
		example.setOrderByClause("name");
		
		List<ServiceCategory> temp = profesionalCategoryDAO.selectServiceCategoryByExample(example);
		List<ServiceCategoryTreeNode> result = new ArrayList<ServiceCategoryTreeNode>();
		for (ServiceCategory profesionalCategory : temp) {
			ServiceCategoryTreeNode newTree = new ServiceCategoryTreeNode(profesionalCategory);
			result.add(getTreeFor(newTree, onlyActive));
		}
		return result;
	}

	private static ServiceCategoryTreeNode getTreeFor(ServiceCategoryTreeNode tree, boolean onlyActive) throws SQLException {
		ServiceCategoryDAO profesionalCategoryDAO = DAOManager.getServiceCategoryDAO();
		ServiceCategoryExample example = new ServiceCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(tree.getServiceCategory().getId());
		if (onlyActive) {
			criteria.andDeletedEqualTo(0);
		}
		example.setOrderByClause("name");
		List<ServiceCategory> temp = profesionalCategoryDAO.selectServiceCategoryByExample(example);
		for (ServiceCategory pro : temp) {
			ServiceCategoryTreeNode newTree = new ServiceCategoryTreeNode(pro);
			newTree.setLevel(tree.getLevel() + 1);
			tree.addChild(getTreeFor(newTree, onlyActive));
		}
		return tree;
	}
}
