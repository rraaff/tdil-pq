package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.tuafesta.dao.ProfesionalCategoryDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ProfesionalCategory;
import com.tdil.tuafesta.model.ProfesionalCategoryExample;

public class ProfesionalCategoryUtils {

	public static String getPrefixFor(ProfesionalCategoryTreeNode profesionalCategoryTreeNode) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < profesionalCategoryTreeNode.getLevel(); i++) {
			result.append("&nbsp;&nbsp;");
		}
		return result.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static List<ProfesionalCategoryTreeNode> getTree()  {
		try {
			return (List<ProfesionalCategoryTreeNode>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					return ProfesionalCategoryUtils.getTreeInTransaction();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<ProfesionalCategoryTreeNode>();
		}
	}

	public static List<ProfesionalCategoryTreeNode> getTreeInTransaction() throws SQLException {
		ProfesionalCategoryDAO profesionalCategoryDAO = DAOManager.getProfesionalCategoryDAO();
		ProfesionalCategoryExample example = new ProfesionalCategoryExample();
		example.createCriteria().andParentIdEqualTo(0);
		example.setOrderByClause("name");
		
		List<ProfesionalCategory> temp = profesionalCategoryDAO.selectProfesionalCategoryByExample(example);
		List<ProfesionalCategoryTreeNode> result = new ArrayList<ProfesionalCategoryTreeNode>();
		for (ProfesionalCategory profesionalCategory : temp) {
			ProfesionalCategoryTreeNode newTree = new ProfesionalCategoryTreeNode(profesionalCategory);
			result.add(getTreeFor(newTree));
		}
		return result;
	}

	private static ProfesionalCategoryTreeNode getTreeFor(ProfesionalCategoryTreeNode tree) throws SQLException {
		ProfesionalCategoryDAO profesionalCategoryDAO = DAOManager.getProfesionalCategoryDAO();
		ProfesionalCategoryExample example = new ProfesionalCategoryExample();
		example.createCriteria().andParentIdEqualTo(tree.getProfesionalCategory().getId());
		example.setOrderByClause("name");
		List<ProfesionalCategory> temp = profesionalCategoryDAO.selectProfesionalCategoryByExample(example);
		for (ProfesionalCategory pro : temp) {
			ProfesionalCategoryTreeNode newTree = new ProfesionalCategoryTreeNode(pro);
			newTree.setLevel(tree.getLevel() + 1);
			tree.addChild(getTreeFor(newTree));
		}
		return tree;
	}
}
