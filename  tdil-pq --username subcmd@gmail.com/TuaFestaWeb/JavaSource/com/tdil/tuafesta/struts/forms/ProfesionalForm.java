package com.tdil.tuafesta.struts.forms;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ProfesionalDAO;
import com.tdil.tuafesta.dao.WallDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.Wall;
import com.tdil.tuafesta.utils.SystemPropertiesKeys;
import com.tdil.tuafesta.web.EmailUtils;

public class ProfesionalForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(ProfesionalForm.class);

	private int id;

	private int objectId;
	private String firstname;
	private String lastname;
	private String sex;
	private String birthdate;
	private String phone;
	private String email;
	private String password;
	private String retypepassword;
	private String website;
	private String facebook;
	private String businesshours;
	private String description;

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.firstname = null;
		this.lastname = null;
		this.sex = null;
		this.birthdate = null;
		this.phone = null;
		this.email = null;
		this.password = null;
		this.retypepassword = null;
		this.website = null;
		this.facebook = null;
		this.businesshours = null;
		this.description = null;
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
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		WallDAO wallDAO = DAOManager.getWallDAO();
		Wall wall = new Wall();
		wall.setDescription("profesional");
		wall.setModerated(1);
		wall.setProfanityfilter(0);
		wall.setDeleted(0);
		int wallId = wallDAO.insertWall(wall);
		Profesional profesional = new Profesional();
		profesional.setFirstname(this.getFirstname());
		profesional.setLastname(this.getLastname());
		profesional.setSex(this.getSex());
		profesional.setBirthdate(parseDate(this.getBirthdate()));
		profesional.setPhone(this.getPhone());
		profesional.setEmail(this.getEmail());
		profesional.setVerifemail(RandomStringUtils.randomAlphanumeric(20));
		profesional.setPassword(this.getPassword());
		profesional.setWebsite(this.getWebsite());
		profesional.setFacebook(this.getFacebook());
		profesional.setBusinesshours(this.getBusinesshours());
		profesional.setDescription(this.getDescription());
		profesional.setIdWall(wallId);
		profesional.setEmailvalid(0);
		profesional.setApproved(0);
		profesional.setDatachanged(0);
		profesional.setDeleted(0);
		int id = profesionalDAO.insertProfesional(profesional);
		
		StringBuffer link = new StringBuffer();
		link.append("/validateProfesionalEmail.do?id=").append(id).append("&verifemail=").append(profesional.getVerifemail());
		
		/** Inicio del email */
		EmailUtils.sendEmail(this.getEmail(), link.toString(), EmailUtils.PROFESIONAL_EMAIL_VERIFICATION);
	}

	private Date parseDate(String fromDate2) {
		try {
			if (StringUtils.isEmpty(fromDate2)) {
				return null;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.parse(fromDate2);
		} catch (ParseException e) {
			Log.error(e.getMessage(), e);
			// throw new RuntimeException(e);
			return null;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetypepassword() {
		return retypepassword;
	}

	public void setRetypepassword(String retypepassword) {
		this.retypepassword = retypepassword;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getBusinesshours() {
		return businesshours;
	}

	public void setBusinesshours(String businesshours) {
		this.businesshours = businesshours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
