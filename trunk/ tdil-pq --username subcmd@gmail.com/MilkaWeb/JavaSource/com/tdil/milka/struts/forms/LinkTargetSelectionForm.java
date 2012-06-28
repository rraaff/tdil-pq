package com.tdil.milka.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.valueobjects.ExperienceValueObject;
import com.tdil.milka.web.Experience;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;

public class LinkTargetSelectionForm extends ActionForm implements TransactionalAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -866984840047663023L;
	
	// used for searching
	private String type;
	private String description;
	private boolean withLinks;

	private LinkAnchorForm originForm;
	
	private List<ExperienceValueObject> sourceList = new ArrayList<ExperienceValueObject>();

	public LinkAnchorForm getOriginForm() {
		return originForm;
	}

	public void setOriginForm(LinkAnchorForm originForm) {
		this.originForm = originForm;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isWithLinks() {
		return withLinks;
	}

	public void setWithLinks(boolean withoutLinks) {
		this.withLinks = withoutLinks;
	}
	
	public void clearList() {
		this.setSourceList(new ArrayList<ExperienceValueObject>());
	}

	public List<ExperienceValueObject> getSourceList() {
		return sourceList;
	}

	public void setSourceList(List<ExperienceValueObject> sourceList) {
		this.sourceList = sourceList;
	}

	public void search() {
		try {
			TransactionProvider.executeInTransaction(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		List<ExperienceValueObject> list = new ArrayList<ExperienceValueObject>();
		if (Experience.FINALES_DE_EMAIL.name().equals(this.getType())) {
			list = DAOManager.getEmailEndingsDAO().search();
		}
		if (Experience.CARTAS_DE_HIJOS_A_PADRES.name().equals(this.getType())) {
			list = DAOManager.getMailToParentDAO().search();
		}
		if (Experience.POST_ITS.name().equals(this.getType())) {
			list = DAOManager.getPostItDAO().search();
		}
		if (Experience.PAPAPEDIA.name().equals(this.getType())) {
			list = DAOManager.getWallWrittingDAO().search();
		}
		if (Experience.APODOS_DE_AMOR.name().equals(this.getType())) {
			list = DAOManager.getLoveNicknamesDAO().search();
		}
		if (Experience.CARTAS_DE_PADRES_A_HIJOS.name().equals(this.getType())) {
			list = DAOManager.getMailToChildDAO().search();
		}
		if (StringUtils.isEmpty(this.getDescription())) {
			this.setSourceList(list);
		} else {
			List<ExperienceValueObject> result = new ArrayList<ExperienceValueObject>();
			for (ExperienceValueObject experienceValueObject : list) {
				if (experienceValueObject.getDescription() != null && experienceValueObject.getDescription().toUpperCase().contains(this.getDescription().toUpperCase())) {
					result.add(experienceValueObject);
				}
			}
			this.setSourceList(result);
		}		
	}

	public boolean canBeSelected(int id) {
		if (this.getOriginForm().getOriginType().equals(this.getType())) {
			return this.getOriginForm().getObjectId() != id;
		} else {
			return true;
		}
	}
	
	public void select(int id) {
		Experience experience = Experience.valueOf(Experience.class, this.getType());
		this.getOriginForm().setUrlLink( experience.getLink() + id);
		this.getOriginForm().setDestinationType(this.getType());
		this.getOriginForm().setDestinationId(id);
	}
}
