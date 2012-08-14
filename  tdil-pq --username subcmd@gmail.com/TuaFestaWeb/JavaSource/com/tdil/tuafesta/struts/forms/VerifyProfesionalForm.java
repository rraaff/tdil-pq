package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.Date;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ProfesionalDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalStatus;

public class VerifyProfesionalForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private int id;
	private int objectId;
	private Profesional profesional;
	private String disapproveReason;
	
	@Override
	public void reset() throws SQLException {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		setProfesional(profesionalDAO.selectProfesionalByPrimaryKey(id));
	}

	@Override
	public void basicValidate(ValidationError validationError) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
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

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public ValidationError validateForDisapprove() {
		// TODO Auto-generated method stub
		return null;
	}

	public void disapprove() throws SQLException {
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		Profesional profesional = profesionalDAO.selectProfesionalByPrimaryKey(this.getProfesional().getId());
		profesional.setReviewdate(new Date());
		profesional.setDisapprovereason(this.getDisapproveReason());
		profesional.setStatus(ProfesionalStatus.DISAPPROVED);
		profesionalDAO.updateProfesionalByPrimaryKey(profesional);
	}

	public ValidationError validateForApprove() {
		// TODO Auto-generated method stub
		return null;
	}
	public void approve() throws SQLException {
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		Profesional profesional = profesionalDAO.selectProfesionalByPrimaryKey(this.getProfesional().getId());
		profesional.setReviewdate(new Date());
		profesional.setStatus(ProfesionalStatus.APPROVED);
		profesionalDAO.updateProfesionalByPrimaryKey(profesional);
		// TODO a esto le falta aprobar los datos puntuales, le falta el tema de los productos no rd etc etc
	}
	
	public String getDisapproveReason() {
		return disapproveReason;
	}

	public void setDisapproveReason(String disapproveReason) {
		this.disapproveReason = disapproveReason;
	}


}
