package com.tdil.tuafesta.struts.forms;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.valueobjects.HighlightedProfesionalValueObject;

public class HighlightedProfesionalForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(HighlightedProfesionalForm.class);

	private int id;

	private int objectId;
	private Profesional profesional;
	private String payment;
	private String fromDate;
	private String toDate;
	
	// search
	private String profesionalBusinessNameSearch;
	private String profesionalDateSearch;
	private List<HighlightedProfesionalValueObject> search = new ArrayList<HighlightedProfesionalValueObject>();

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {

	}

	@Override
	public void basicValidate(ValidationError validationError) {
		// TODO VALIDACIONES
	}

	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO VALIDACIONES
	}

	@Override
	public void save() throws SQLException, ValidationException {
		
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

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public String getProfesionalBusinessNameSearch() {
		return profesionalBusinessNameSearch;
	}

	public void setProfesionalBusinessNameSearch(String profesionalBusinessNameSearch) {
		this.profesionalBusinessNameSearch = profesionalBusinessNameSearch;
	}

	public String getProfesionalDateSearch() {
		return profesionalDateSearch;
	}

	public void setProfesionalDateSearch(String profesionalDateSearch) {
		this.profesionalDateSearch = profesionalDateSearch;
	}

	public List<HighlightedProfesionalValueObject> getSearch() {
		return search;
	}

	public void setSearch(List<HighlightedProfesionalValueObject> search) {
		this.search = search;
	}


}
