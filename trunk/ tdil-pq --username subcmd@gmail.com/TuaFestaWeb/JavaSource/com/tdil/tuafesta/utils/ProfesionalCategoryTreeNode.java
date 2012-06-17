package com.tdil.tuafesta.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tdil.tuafesta.model.ProfesionalCategory;

public class ProfesionalCategoryTreeNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -458918044276232438L;

	private ProfesionalCategoryTreeNode parentNode;
	
	private int level = 0;
	private ProfesionalCategory profesionalCategory;
	
	private List<ProfesionalCategoryTreeNode> childs = new ArrayList<ProfesionalCategoryTreeNode>();

	public ProfesionalCategoryTreeNode(ProfesionalCategory profesionalCategory) {
		super();
		this.profesionalCategory = profesionalCategory;
	}

	public ProfesionalCategory getProfesionalCategory() {
		return profesionalCategory;
	}

	public void setProfesionalCategory(ProfesionalCategory profesionalCategory) {
		this.profesionalCategory = profesionalCategory;
	}
	
	public void addChild(ProfesionalCategoryTreeNode child) {
		this.getChilds().add(child);
		child.setParentNode(this);
	}

	private List<ProfesionalCategoryTreeNode> getChilds() {
		return childs;
	}

	public ProfesionalCategoryTreeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(ProfesionalCategoryTreeNode parentNode) {
		this.parentNode = parentNode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public static List<ProfesionalCategoryTreeNode> tree2list(List<ProfesionalCategoryTreeNode> treelist) {
		List<ProfesionalCategoryTreeNode> result = new ArrayList<ProfesionalCategoryTreeNode>();
		tree2list(treelist, result);
		return result;
	}
	
	public static void tree2list(List<ProfesionalCategoryTreeNode> treelist, List<ProfesionalCategoryTreeNode> result) {
		for (ProfesionalCategoryTreeNode pro : treelist) {
			result.add(pro);
			tree2list(pro.getChilds(), result);
		}
	}

	public Integer getId() {
		return profesionalCategory.getId();
	}

	public Integer getDeleted() {
		return profesionalCategory.getDeleted();
	}

	public String getName() {
		return profesionalCategory.getName();
	}

	public String getDescription() {
		return profesionalCategory.getDescription();
	}
}
