package com.tdil.tuafesta.struts.forms;


import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.log4j.LoggerProvider;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalExample;

public class ProfesionalAdministrationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(ProfesionalAdministrationForm.class);

	private int id;

	private int objectId;
	
	private String status;
	private String name;
	private boolean tooMany;
	
	private List<Profesional> search;

	public void initWith(int id) throws SQLException {
		
	}
	
	public void init() throws SQLException {
		ProfesionalExample profesionalExample = new ProfesionalExample();
		profesionalExample.setOrderByClause("email");
		setSearch(DAOManager.getProfesionalDAO().selectProfesionalByExample(profesionalExample));
	}
	
	public void search() {
		if (ProfesionalStatusHelper.EMAIL_NOT_VALIDATED.equals(this.getStatus())) {
			
		} else {
			if (ProfesionalStatusHelper.DATA_NOT_COMPLETE.equals(this.getStatus())) {
				
			}	
		}
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Profesional> getSearch() {
		return search;
	}

	public void setSearch(List<Profesional> search) {
		this.search = search;
	}

	public boolean isTooMany() {
		return tooMany;
	}

	public void setTooMany(boolean tooMany) {
		this.tooMany = tooMany;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
