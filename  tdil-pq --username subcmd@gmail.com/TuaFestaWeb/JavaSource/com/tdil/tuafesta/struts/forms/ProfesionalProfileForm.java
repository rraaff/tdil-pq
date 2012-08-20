package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ProfesionalDAO;
import com.tdil.tuafesta.dao.WallWrittingDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.WallWritting;
import com.tdil.tuafesta.utils.GeoLevelUtils;

public class ProfesionalProfileForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private int id;
	private int objectId;
	private Profesional profesional;
	
	private WallCommentForm wallCommentForm = new WallCommentForm();
	
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
	
	public String getGeoLevelPath() {
		return GeoLevelUtils.getPath(profesional.getIdGeolevel());
	}
	
	public String getBirthDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(this.getProfesional().getBirthdate());
	}

	public WallCommentForm getWallCommentForm() {
		wallCommentForm.setWallId(this.getProfesional().getIdWall());
		return wallCommentForm;
	}

	public void setWallCommentForm(WallCommentForm wallCommentForm) {
		this.wallCommentForm = wallCommentForm;
	}

	public String getContent() {
		return wallCommentForm.getContent();
	}

	public void setContent(String content) {
		wallCommentForm.setContent(content);
	}

	public int getUserId() {
		return wallCommentForm.getUserId();
	}

	public void setUserId(int userId) {
		wallCommentForm.setUserId(userId);
	}

	public ValidationError validateWallComment() {
		ValidationError validationError = new ValidationError();
		// TODO Auto-generated method stub
		return validationError;
	}

	public void addWallComment() throws SQLException {
		this.getWallCommentForm().addWallComment();
	}

}
