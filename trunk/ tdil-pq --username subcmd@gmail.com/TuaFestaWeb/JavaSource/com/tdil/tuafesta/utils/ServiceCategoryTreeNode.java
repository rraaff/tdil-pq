package com.tdil.tuafesta.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tdil.tuafesta.model.ServiceCategory;

public class ServiceCategoryTreeNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -458918044276232438L;

	private ServiceCategoryTreeNode parentNode;
	
	private int level = 0;
	private ServiceCategory profesionalCategory;
	
	private List<ServiceCategoryTreeNode> childs = new ArrayList<ServiceCategoryTreeNode>();

	public ServiceCategoryTreeNode(ServiceCategory profesionalCategory) {
		super();
		this.profesionalCategory = profesionalCategory;
	}

	public ServiceCategory getServiceCategory() {
		return profesionalCategory;
	}

	public void setServiceCategory(ServiceCategory profesionalCategory) {
		this.profesionalCategory = profesionalCategory;
	}
	
	public void addChild(ServiceCategoryTreeNode child) {
		this.getChilds().add(child);
		child.setParentNode(this);
	}

	private List<ServiceCategoryTreeNode> getChilds() {
		return childs;
	}

	public ServiceCategoryTreeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(ServiceCategoryTreeNode parentNode) {
		this.parentNode = parentNode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public static List<ServiceCategoryTreeNode> tree2list(List<ServiceCategoryTreeNode> treelist) {
		List<ServiceCategoryTreeNode> result = new ArrayList<ServiceCategoryTreeNode>();
		tree2list(treelist, result);
		return result;
	}
	
	public static void tree2list(List<ServiceCategoryTreeNode> treelist, List<ServiceCategoryTreeNode> result) {
		for (ServiceCategoryTreeNode pro : treelist) {
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
