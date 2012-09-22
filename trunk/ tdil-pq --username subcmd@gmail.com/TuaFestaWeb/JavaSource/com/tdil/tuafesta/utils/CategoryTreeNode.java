package com.tdil.tuafesta.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tdil.tuafesta.model.Category;

public class CategoryTreeNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -458918044276232438L;

	private CategoryTreeNode parentNode;
	
	private int level = 0;
	private Category profesionalCategory;
	
	private List<CategoryTreeNode> childs = new ArrayList<CategoryTreeNode>();

	public CategoryTreeNode(Category profesionalCategory) {
		super();
		this.profesionalCategory = profesionalCategory;
	}

	public Category getCategory() {
		return profesionalCategory;
	}

	public void setCategory(Category profesionalCategory) {
		this.profesionalCategory = profesionalCategory;
	}
	
	public void addChild(CategoryTreeNode child) {
		this.getChilds().add(child);
		child.setParentNode(this);
	}

	private List<CategoryTreeNode> getChilds() {
		return childs;
	}

	public CategoryTreeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(CategoryTreeNode parentNode) {
		this.parentNode = parentNode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public static List<CategoryTreeNode> tree2list(List<CategoryTreeNode> treelist) {
		List<CategoryTreeNode> result = new ArrayList<CategoryTreeNode>();
		tree2list(treelist, result);
		return result;
	}
	
	public static void tree2list(List<CategoryTreeNode> treelist, List<CategoryTreeNode> result) {
		for (CategoryTreeNode pro : treelist) {
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

	public void addChildsOf(List<Integer> result, int catId) {
		if (this.getId().equals(catId)) {
			for (CategoryTreeNode child : childs) {
				result.add(child.getId());
				child.addChilds(result);
			}
		} else {
			for (CategoryTreeNode child : childs) {
				child.addChildsOf(result, catId);
			}
		}
		
	}

	private void addChilds(List<Integer> result) {
		for (CategoryTreeNode child : childs) {
			result.add(child.getId());
			child.addChilds(result);
		}
	}
}
