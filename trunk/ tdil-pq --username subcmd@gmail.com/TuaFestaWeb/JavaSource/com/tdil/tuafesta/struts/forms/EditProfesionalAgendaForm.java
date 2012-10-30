package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalAgenda;
import com.tdil.tuafesta.model.ProfesionalAgendaExample;
import com.tdil.utils.DateUtils;

public class EditProfesionalAgendaForm extends TransactionalValidationForm implements EditProfesionalDataForm, ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	
	private int deleteId;
	
	private String eventdate;
	
	private List<ProfesionalAgenda> agendaList = new ArrayList<ProfesionalAgenda>();
	public static final String eventdate_key = "Agenda.eventdate";
	
	@Override
	public void reset() throws SQLException {
		reloadList();
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		reloadList();
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.deleteId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		DAOManager.getProfesionalAgendaDAO().deleteProfesionalAgendaByPrimaryKey(this.deleteId);
	}
	
	private void reloadList() throws SQLException {
		ProfesionalAgendaExample profesionalAgendaExample = new ProfesionalAgendaExample();
		profesionalAgendaExample.createCriteria().andIdProfesionalEqualTo(objectId);
		profesionalAgendaExample.setOrderByClause("date_ desc");
		setAgendaList(DAOManager.getProfesionalAgendaDAO().selectProfesionalAgendaByExample(profesionalAgendaExample));
	}

	@Override
	public void initWith(int id) throws SQLException {
		objectId = id;
		reloadList();
	}
	
	@Override
	public void basicValidate(ValidationError validationError) {
		
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ProfesionalAgenda profesionalAgenda = new ProfesionalAgenda();
		profesionalAgenda.setBusy(1);
		profesionalAgenda.setDeleted(0);
		profesionalAgenda.setIdProfesional(this.id);
		profesionalAgenda.setDate(DateUtils.parseDate(this.getEventdate()));
		DAOManager.getProfesionalAgendaDAO().insertProfesionalAgenda(profesionalAgenda);
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

	private static Logger getLog() {
		return LoggerProvider.getLogger(EditProfesionalAgendaForm.class);
	}
	public String getEventdate() {
		return eventdate;
	}
	public void setEventdate(String birthdate) {
		this.eventdate = birthdate;
	}
	public List<ProfesionalAgenda> getAgendaList() {
		return agendaList;
	}
	public void setAgendaList(List<ProfesionalAgenda> agendaList) {
		this.agendaList = agendaList;
	}

}
