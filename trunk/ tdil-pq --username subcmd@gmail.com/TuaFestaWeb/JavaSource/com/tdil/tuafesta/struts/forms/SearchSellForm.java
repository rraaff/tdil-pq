package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.SearchForm;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;

public class SearchSellForm extends ActionForm implements SearchForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5829022148346001648L;

	private int type = SellType.PRODUCT; // PRODUCT or SERVICE
	private String name;
	private String profesionalBusinessname;
	// TODO ver si hace falta la categoria
	private List<SellValueObject> searchResult = new ArrayList<SellValueObject>();
	
	@Override
	public void search() throws ValidationException {
		try {
			if (this.getType() == SellType.PRODUCT) { // Si es producto
				searchResult = DAOManager.getSellDAO().searchProductsSellsBy(this.getName(), this.getProfesionalBusinessname());
			} else { // Si es servicio
				searchResult = DAOManager.getSellDAO().searchServicesSellsBy(this.getName(), this.getProfesionalBusinessname());
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getProfesionalBusinessname() {
		return profesionalBusinessname;
	}


	public void setProfesionalBusinessname(String profesionalBusinessname) {
		this.profesionalBusinessname = profesionalBusinessname;
	}

	public List<SellValueObject> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<SellValueObject> searchResult) {
		this.searchResult = searchResult;
	}
}
