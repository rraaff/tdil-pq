package com.tdil.tuafesta.struts.forms;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.forms.SearchForm;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Client;

public class ClientAdministrationForm extends ActionForm implements SearchForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(ClientAdministrationForm.class);

	private int id;

	private int objectId;
	
	private int status = -1;
	private String name;
	private boolean tooMany;
	
	private List<Client> search = new ArrayList<Client>();

	public void initWith(int id) throws SQLException {
		
	}
	
	public void init() throws SQLException {
//		ProfesionalExample profesionalExample = new ProfesionalExample();
//		profesionalExample.setOrderByClause("email");
//		setSearch(DAOManager.getProfesionalDAO().selectProfesionalByExample(profesionalExample));
	}
	
	public void search() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (this.getStatus() != -1) {
				params.put("status", this.getStatus());
			}
			if (!StringUtils.isEmpty(this.getName())) {
				params.put("name", "%"+this.getName()+"%");
			}
			search = DAOManager.getClientDAO().selectClientForAdministration(params);
			if (search.size() > 100) {
				search = search.subList(0, 100);
				setTooMany(true);
			} else {
				setTooMany(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Client> getSearch() {
		return search;
	}

	public void setSearch(List<Client> search) {
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
