package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ProfesionalDAO;
import com.tdil.tuafesta.dao.WallWrittingDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.WallWritting;
import com.tdil.tuafesta.model.valueobjects.WallWrittingValueObject;
import com.tdil.tuafesta.stats.StatisticType;
import com.tdil.tuafesta.stats.StatsManager;
import com.tdil.tuafesta.utils.GeoLevelUtils;

public class ProfesionalProfileForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private int id;
	private int objectId;
	private Profesional profesional;
	
	private Calendar calendar;
	
	private WallCommentForm wallCommentForm = new WallCommentForm();
	
	private List<WallWrittingValueObject> wallWritting;
	
	@Override
	public void reset() throws SQLException {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		StatsManager.addStat(StatisticType.PROFESIONAL_VIEW, id, null);
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		setProfesional(profesionalDAO.selectProfesionalByPrimaryKey(id));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idWall", this.getProfesional().getIdWall());
		params.put("start", 0);
		params.put("limit", 11);
		wallWritting = DAOManager.getWallWrittingDAO().selectWallWindow(params);
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
		this.getWallCommentForm().basicValidate(validationError);
		return validationError;
	}

	public void addWallComment() throws SQLException {
		this.getWallCommentForm().addWallComment();
	}

	public List<WallWrittingValueObject> getWallWritting() {
		return wallWritting;
	}

	public Calendar getCalendar() {
		if (calendar == null) {
			calendar = Calendar.getInstance();
			calendar.set(Calendar.DATE, 1);
		}
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public void moveCurrentMonth() {
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
	}

	public void movePrevMonth() {
		getCalendar().add(Calendar.MONTH, -1);
	}
	
	public void moveNextMonth() {
		getCalendar().add(Calendar.MONTH, 1);
	}

}
