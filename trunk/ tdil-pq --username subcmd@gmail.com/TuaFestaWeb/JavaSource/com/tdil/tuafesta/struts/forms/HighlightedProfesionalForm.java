package com.tdil.tuafesta.struts.forms;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.HighlightedProfesionalDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.HighlightedProfesional;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalExample;
import com.tdil.tuafesta.model.ProfesionalExample.Criteria;
import com.tdil.tuafesta.model.valueobjects.HighlightedProfesionalValueObject;
import com.tdil.tuafesta.utils.DateUtils;

public class HighlightedProfesionalForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(HighlightedProfesionalForm.class);

	private int id;

	private int objectId;

	// search profesional for add
	private String fromsearch = "0";
	private Profesional profesional;
	private String firstNameSearch;
	private String lastNameSearch;
	private String businessNameSearch;
	private List<Profesional> profesionalSearch = new ArrayList<Profesional>();

	private String payment = "0";
	private String fromDate;
	private String toDate;

	// search to edit
	private String profesionalFirstNameSearch;
	private String profesionalLastNameSearch;
	private String profesionalBusinessNameSearch;
	private String profesionalDateSearch;
	private List<HighlightedProfesionalValueObject> search = new ArrayList<HighlightedProfesionalValueObject>();

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.profesional = null;
		this.firstNameSearch = null;
		this.lastNameSearch = null;
		this.businessNameSearch = null;
		this.profesionalSearch = new ArrayList<Profesional>();
		this.payment = "0";
		this.fromDate = null;
		this.toDate = null;
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
		HighlightedProfesionalDAO highlightedProfesionalDAO = DAOManager.getHighlightedProfesionalDAO();
		HighlightedProfesional highlightedProfesional = new HighlightedProfesional();
		highlightedProfesional.setIdProfesional(this.getProfesional().getId());
		highlightedProfesional.setFromdate(DateUtils.parseDate(this.getFromDate()));
		highlightedProfesional.setTodate(DateUtils.parseDate(this.getToDate()));
		highlightedProfesional.setDeleted(0);
		BigDecimal payment = new BigDecimal(this.getPayment());
		highlightedProfesional.setPayment(payment);
		highlightedProfesionalDAO.insertHighlightedProfesional(highlightedProfesional);
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

	public String getFirstNameSearch() {
		return firstNameSearch;
	}

	public void setFirstNameSearch(String firstNameSearch) {
		this.firstNameSearch = firstNameSearch;
	}

	public String getLastNameSearch() {
		return lastNameSearch;
	}

	public void setLastNameSearch(String lastNameSearch) {
		this.lastNameSearch = lastNameSearch;
	}

	public String getBusinessNameSearch() {
		return businessNameSearch;
	}

	public void setBusinessNameSearch(String businessNameSearch) {
		this.businessNameSearch = businessNameSearch;
	}

	public void searchProfesionals() {
		try {
			ProfesionalExample profesionalExample = new ProfesionalExample();
			Criteria criteria = profesionalExample.createCriteria();
			if (StringUtils.isEmpty(this.firstNameSearch)) {
				criteria.andFirstnameLike("%" + this.firstNameSearch + "%");
			}
			if (StringUtils.isEmpty(this.lastNameSearch)) {
				criteria.andLastnameLike("%" + this.lastNameSearch + "%");
			}
			if (StringUtils.isEmpty(this.businessNameSearch)) {
				criteria.andBusinessnameLike("%" + this.businessNameSearch + "%");
			}
			this.setProfesionalSearch(DAOManager.getProfesionalDAO().selectProfesionalByExample(profesionalExample)); // TODO
																														// case
																														// insensitivity
		} catch (Exception e) {
			Log.error(e.getMessage(), e);
		}
	}
	
	public void searchHighlightedProfesionals() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isEmpty(this.profesionalFirstNameSearch)) {
				params.put("firstname", "%" + this.profesionalFirstNameSearch + "%");
			}
			if (StringUtils.isEmpty(this.profesionalLastNameSearch)) {
				params.put("lastname", "%" + this.profesionalLastNameSearch + "%");
			}
			if (StringUtils.isEmpty(this.profesionalBusinessNameSearch)) {
				params.put("businessname", "%" + this.profesionalBusinessNameSearch + "%");
			}
			Date datesearch = DateUtils.parseDate(this.profesionalDateSearch);
			if (datesearch != null) {
				params.put("dateactive", datesearch);
			}
			// TODO Hacer la busqueda en el DAO
																														// case
																														// insensitivity
		} catch (Exception e) {
			Log.error(e.getMessage(), e);
		}
	}

	public List<Profesional> getProfesionalSearch() {
		return profesionalSearch;
	}

	public void setProfesionalSearch(List<Profesional> profesionalSearch) {
		this.profesionalSearch = profesionalSearch;
	}

	public void selectProfesional(int profesionalId) throws SQLException {
		Profesional profesional = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(profesionalId);
		this.setProfesional(profesional);

	}

	public String getFromsearch() {
		return fromsearch;
	}

	public void setFromsearch(String fromsearch) {
		this.fromsearch = fromsearch;
	}

	public String getProfesionalFirstNameSearch() {
		return profesionalFirstNameSearch;
	}

	public void setProfesionalFirstNameSearch(String profesionalFirstNameSearch) {
		this.profesionalFirstNameSearch = profesionalFirstNameSearch;
	}

	public String getProfesionalLastNameSearch() {
		return profesionalLastNameSearch;
	}

	public void setProfesionalLastNameSearch(String profesionalLastNameSearch) {
		this.profesionalLastNameSearch = profesionalLastNameSearch;
	}
}
