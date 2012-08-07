package com.tdil.tuafesta.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tdil.tuafesta.model.ProductCategory;

public class ProductCategoryTreeNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -458918044276232438L;

	private ProductCategoryTreeNode parentNode;
	
	private int level = 0;
	private ProductCategory profesionalCategory;
	
	private List<ProductCategoryTreeNode> childs = new ArrayList<ProductCategoryTreeNode>();

	public ProductCategoryTreeNode(ProductCategory profesionalCategory) {
		super();
		this.profesionalCategory = profesionalCategory;
	}

	public ProductCategory getProductCategory() {
		return profesionalCategory;
	}

	public void setProductCategory(ProductCategory profesionalCategory) {
		this.profesionalCategory = profesionalCategory;
	}
	
	public void addChild(ProductCategoryTreeNode child) {
		this.getChilds().add(child);
		child.setParentNode(this);
	}

	private List<ProductCategoryTreeNode> getChilds() {
		return childs;
	}

	public ProductCategoryTreeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(ProductCategoryTreeNode parentNode) {
		this.parentNode = parentNode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public static List<ProductCategoryTreeNode> tree2list(List<ProductCategoryTreeNode> treelist) {
		List<ProductCategoryTreeNode> result = new ArrayList<ProductCategoryTreeNode>();
		tree2list(treelist, result);
		return result;
	}
	
	public static void tree2list(List<ProductCategoryTreeNode> treelist, List<ProductCategoryTreeNode> result) {
		for (ProductCategoryTreeNode pro : treelist) {
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
