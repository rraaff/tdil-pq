package com.tdil.tuafesta.struts.forms;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.Geo2DAO;
import com.tdil.tuafesta.dao.Geo3DAO;
import com.tdil.tuafesta.dao.Geo4DAO;
import com.tdil.tuafesta.dao.ProfesionalChangeDAO;
import com.tdil.tuafesta.dao.ProfesionalDAO;
import com.tdil.tuafesta.dao.SellDAO;
import com.tdil.tuafesta.dao.ServiceAreaDAO;
import com.tdil.tuafesta.dao.WallDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ClientExample;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo2Example;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo3Example;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Geo4Example;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.ProfesionalChange;
import com.tdil.tuafesta.model.ProfesionalExample;
import com.tdil.tuafesta.model.ProfesionalStatus;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.ServiceArea;
import com.tdil.tuafesta.model.Wall;
import com.tdil.tuafesta.stats.StatisticType;
import com.tdil.tuafesta.stats.StatsManager;
import com.tdil.tuafesta.struts.forms.beans.SellBean;
import com.tdil.tuafesta.struts.forms.beans.ServiceAreaBean;
import com.tdil.tuafesta.web.EmailUtils;
import com.tdil.utils.CryptoUtils;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class ProfesionalForm extends TransactionalValidationForm implements GeoLevelSelectionForm, ServiceAreaForm, ProfesionalSellForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;

	private static final Logger Log = LoggerProvider.getLogger(ProfesionalForm.class);

	private int id;
	private Profesional logged; // esta variable tiene el cliente luego de la ejecucion del save

	private int objectId;
	private boolean facebookRegister;
	private String facebookId;
	private String firstname;
	private String lastname;
	private String sex;
	private String birthdate;
	
	private String phoneAreaCode;
	private String phoneNumber;
	private String phoneExtension;
	private String phoneType;
	
	private String email;
	private String password;
	private String retypepassword;
	
	private String businessname;
	private String cuit;
	private String iibb;
	
	private int geo2Id;
	private int geo3Id;
	private int geo4Id;
	
	private List<Geo2> level2;
	private List<Geo3> level3;
	private List<Geo4> level4;
	
	private String website;
	private String facebook;
	private String businesshours;
	private String description;
	
	// abm de areas de servicios
	private List<ServiceAreaBean> serviceAreas = new ArrayList<ServiceAreaBean>();
	private String geoLevel4Id;
	private String serviceAreaAutocompleter;
	private String serviceAreaSelectedText;
	
	
	// abm de productos
	private List<SellBean> sells = new ArrayList<SellBean>();
	
	private String categoryId;
	private String categorySelected;
	private String sellName;
	private String sellDescription;
	private String referenceprice;
	
	
	protected static final int MIN_PASS_LENGTH = 4;
	
	public static final String firstname_key = "ProfesionalForm.firstname";
	public static final String lastname_key = "ProfesionalForm.lastname";
	public static final String sex_key = "ProfesionalForm.sex";
	public static final String birthdate_key = "ProfesionalForm.birthdate";
	public static final String phoneareacode_key = "ProfesionalForm.phoneAreaCode";
	public static final String phonenumber_key = "ProfesionalForm.phoneNumber";
	public static final String phoneextension_key = "ProfesionalForm.phoneExtension";
	public static final String phonetype_key = "ProfesionalForm.phoneType";
	
	public static final String email_key = "ProfesionalForm.email";
	public static final String password_key = "ProfesionalForm.password";
	public static final String retypepassword_key = "ProfesionalForm.retypepassword";
	
	public static final String businessname_key = "ProfesionalForm.businessname";
	public static final String cuit_key = "ProfesionalForm.cuit";
	public static final String iibb_key = "ProfesionalForm.iibb";
	public static final String sells_key = "ProfesionalForm.sells";
	
	public static final String geo2_key = "ProfesionalForm.geo2";
	public static final String geo3_key = "ProfesionalForm.geo3";
	public static final String geo4_key = "ProfesionalForm.geo4";
	
	public static final String website_key = "ProfesionalForm.website";
	public static final String facebook_key = "ProfesionalForm.facebook";
	public static final String businesshours_key = "ProfesionalForm.businesshours";
	public static final String description_key = "ProfesionalForm.description";
	
	public static final String video1_key = "ProfesionalForm.video1";
	public static final String video2_key = "ProfesionalForm.video2";
	public static final String video3_key = "ProfesionalForm.video3";
	public static final String video4_key = "ProfesionalForm.video4";
	public static final String video5_key = "ProfesionalForm.video5";
	
	
	private static final Logger LOG = LoggerProvider.getLogger(ProfesionalForm.class);

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.logged = null;
		this.firstname = null;
		this.lastname = null;
		this.sex = null;
		this.birthdate = null;
		this.phoneAreaCode = null;
		this.phoneNumber = null;
		this.phoneExtension = null;
		this.phoneType = null;
		this.email = null;
		this.password = null;
		this.retypepassword = null;
		
		this.businessname = null;
		this.cuit = null;
		this.iibb = null;
		
		this.website = null;
		this.facebook = null;
		this.businesshours = null;
		this.description = null;
		
		this.geo2Id = 0;
		this.geo3Id = 0;
		this.geo4Id = 0;
		this.sells = new ArrayList<SellBean>();
		
		this.categoryId = null;
		this.categorySelected = null;
		this.sellName= null;
		this.sellDescription= null;
		this.referenceprice = null;
	}

	@Override
	public void init() throws SQLException {
		Geo2DAO geo2dao = DAOManager.getGeo2DAO();
		Geo2Example geo2Example = new Geo2Example();
		geo2Example.setOrderByClause("nombre");
		setLevel2(geo2dao.selectGeo2ByExample(geo2Example));
	}

	@Override
	public void initWith(int id) throws SQLException {

	}


	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getFirstname(), firstname_key, 100, validationError);
		FieldValidation.validateText(this.getLastname(), lastname_key, 100, validationError);
		FieldValidation.validateText(this.getSex(), sex_key, 1, validationError);
		FieldValidation.validateText(this.getPhoneAreaCode(), phoneareacode_key, 15, false, validationError);
		FieldValidation.validateText(this.getPhoneNumber(), phonenumber_key, 15, false, validationError);
		FieldValidation.validateText(this.getPhoneExtension(), phoneextension_key, 10, false, validationError);
		FieldValidation.validateText(this.getPhoneType(), phonetype_key, 25, false, validationError);
		
		if (!this.isFacebookRegister()) {
			FieldValidation.validateText(this.getEmail(), email_key, 150, validationError);
			FieldValidation.validateText(this.getPassword(), password_key, 20, validationError);
		}
		
		FieldValidation.validateText(this.getBusinessname(), businessname_key, 400, validationError);
		FieldValidation.validateText(this.getCuit(), cuit_key, 400, validationError);
		FieldValidation.validateText(this.getIibb(), iibb_key, 400, validationError);
		
		FieldValidation.validateText(this.getWebsite(), website_key, 200, false, validationError);
		FieldValidation.validateText(this.getFacebook(), facebook_key, 200, false, validationError);
		FieldValidation.validateText(this.getBusinesshours(), businesshours_key, 4000, false, validationError);
		FieldValidation.validateText(this.getDescription(), description_key, 4000, false, validationError);
		Date birthDate = com.tdil.utils.DateUtils.parseDate(this.getBirthdate());
		if (birthDate == null) {
			validationError.setFieldError(birthdate_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
		FieldValidation.validateId(geo2Id, geo2_key, validationError);
		FieldValidation.validateId(geo3Id, geo3_key, validationError);
		FieldValidation.validateId(geo4Id, geo4_key, validationError);
		
		if (this.getSells().isEmpty()) {
			validationError.setFieldError(sells_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
		if (!this.isFacebookRegister()) {
			if (!validationError.hasFieldError(password_key)) {
				if (this.getPassword().length() < MIN_PASS_LENGTH) {
					validationError.setFieldError(password_key, "PASSWORD_TOO_SHORT");
				} else {
					if (!this.getPassword().equals(this.getRetypepassword())) {
						validationError.setFieldError(password_key, "RETYPE_NOT_EQUAL");
					}
				}
			}
		}
	}

	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		ClientExample clientExample = new ClientExample();
		clientExample.createCriteria().andEmailEqualTo(this.getEmail());
		int count = DAOManager.getClientDAO().countClientByExample(clientExample);
		if (count > 0) {
			validationError.setFieldError(email_key, ValidationErrors.DUPLICATED);
		} else {
			ProfesionalExample profesionalExample = new ProfesionalExample();
			profesionalExample.createCriteria().andEmailEqualTo(this.getEmail());
			count = DAOManager.getProfesionalDAO().countProfesionalByExample(profesionalExample);
			if (count > 0) {
				validationError.setFieldError(email_key, ValidationErrors.DUPLICATED);
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ProfesionalChangeDAO profesionalChangeDAO = DAOManager.getProfesionalChangeDAO();
		ProfesionalDAO profesionalDAO = DAOManager.getProfesionalDAO();
		SellDAO sellDAO = DAOManager.getSellDAO();
		WallDAO wallDAO = DAOManager.getWallDAO();
		ServiceAreaDAO serviceAreaDAO = DAOManager.getServiceAreaDAO();
		Wall wall = new Wall();
		wall.setDescription("profesional");
		wall.setModerated(1);
		wall.setProfanityfilter(0);
		wall.setDeleted(0);
		int wallId = wallDAO.insertWall(wall);
		
		ProfesionalChange profesionalChange = new ProfesionalChange();
		profesionalChange.setDeleted(0);
		int profesionalChangeId = profesionalChangeDAO.insertProfesionalChange(profesionalChange);
		
		Profesional profesional = new Profesional();
		profesional.setFirstname(this.getFirstname());
		profesional.setLastname(this.getLastname());
		profesional.setPhoneareacode(this.getPhoneAreaCode());
		profesional.setPhonenumber(this.getPhoneNumber());
		profesional.setPhoneextension(this.getPhoneExtension());
		profesional.setPhonetype(this.getPhoneType());
		profesional.setIdGeolevel(this.getGeo4Id());
		profesional.setSex(this.getSex());
		profesional.setBirthdate(com.tdil.utils.DateUtils.parseDate(this.getBirthdate()));
		
		profesional.setEmail(this.getEmail());
		
		if (!this.isFacebookRegister()) {
			profesional.setVerifemail(RandomStringUtils.randomAlphanumeric(20));
			profesional.setPassword(CryptoUtils.getHashedValue(this.getPassword()));
			profesional.setEmailvalid(0);
			profesional.setStatus(ProfesionalStatus.EMAIL_VALIDATION_PENDING);
		} else {
			profesional.setFacebookid(this.getFacebookId());
			profesional.setEmailvalid(1);
			profesional.setStatus(ProfesionalStatus.VERIFICATION_PENDING);
		}
		profesional.setBusinessname(this.getBusinessname());
		profesional.setCuit(this.getCuit());
		profesional.setIibb(this.getIibb());
		
		profesional.setWebsite(this.getWebsite());
		profesional.setFacebook(this.getFacebook());
		profesional.setBusinesshours(this.getBusinesshours());
		profesional.setDescription(this.getDescription());
		profesional.setIdWall(wallId);
		profesional.setIdProfesionalChange(profesionalChangeId);
		
		profesional.setDatachanged(0);
		profesional.setDeleted(0);
		int id = profesionalDAO.insertProfesional(profesional);
		
		for (SellBean productBean : getSells()) {
			Sell sell = new Sell();
			sell.setIdProfesional(id);
			sell.setType(productBean.getType());
//			sell.setIdProdServ(productBean.getProductId());
//			if (sell.getIdProdServ() == 0) {
//				sell.setItem(productBean.getName());
//			} // TODO esto cambia
			// TODO ver tema de . , etc
			BigDecimal refPrice = new BigDecimal(productBean.getReferencePrice());
			sell.setReferenceprice(refPrice);
			sell.setApproved(0);
			sell.setDeleted(0);
			sellDAO.insertSell(sell);
		}
		for (ServiceAreaBean serviceAreaBean : serviceAreas) {
			ServiceArea serviceArea = new ServiceArea();
			serviceArea.setIdGeolevel(serviceAreaBean.getGeoLevelId());
			serviceArea.setLevel(4);
			serviceArea.setIdProfesional(id);
			serviceArea.setApproved(0);
			serviceArea.setDeleted(0);
			serviceAreaDAO.insertServiceArea(serviceArea);
		}
		
		logged = profesionalDAO.selectProfesionalByPrimaryKey(id);
		
		if (!this.isFacebookRegister()) {
			StringBuffer link = new StringBuffer();
			link.append("/validateProfesionalEmail.do?id=").append(id).append("&verifemail=").append(profesional.getVerifemail());
			
			/** Inicio del email */
			Map<String, String> params = new HashMap<String, String>();
			params.put(EmailUtils.LINK_KEY, link.toString());
			EmailUtils.sendEmail(this.getEmail(), params, EmailUtils.PROFESIONAL_EMAIL_VERIFICATION);
		}
		
		StatsManager.addStat(StatisticType.PROFESIONAL_REGISTRATION, id, null);
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

	public String getEmail() {
		if (email != null) {
			email = email.toLowerCase();
		}
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

	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}

	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneExtension() {
		return phoneExtension;
	}

	public void setPhoneExtension(String phoneExtension) {
		this.phoneExtension = phoneExtension;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getBusinessname() {
		return businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getIibb() {
		return iibb;
	}

	public void setIibb(String iibb) {
		this.iibb = iibb;
	}

	public int getGeo2Id() {
		return geo2Id;
	}

	public void setGeo2Id(int geo2Id) {
		this.geo2Id = geo2Id;
	}

	public int getGeo3Id() {
		return geo3Id;
	}

	public void setGeo3Id(int geo3Id) {
		this.geo3Id = geo3Id;
	}

	public int getGeo4Id() {
		return geo4Id;
	}

	public void setGeo4Id(int geo4Id) {
		this.geo4Id = geo4Id;
	}

	public List<Geo2> getLevel2() {
		return level2;
	}

	public void setLevel2(List<Geo2> level2) {
		this.level2 = level2;
	}

	public List<Geo3> getLevel3() {
		if (geo2Id != 0) {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						Geo3DAO geo3dao = DAOManager.getGeo3DAO();
						Geo3Example geo3Example = new Geo3Example();
						geo3Example.createCriteria().andGeo2IdEqualTo(ProfesionalForm.this.geo2Id);
						geo3Example.setOrderByClause("nombre");
						ProfesionalForm.this.setLevel3(geo3dao.selectGeo3ByExample(geo3Example));
					}
				});
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		} else {
			setLevel3(new ArrayList<Geo3>());
		}
		return level3;
	}

	public void setLevel3(List<Geo3> level3) {
		this.level3 = level3;
	}

	public List<Geo4> getLevel4() {
		if (geo3Id != 0) {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						Geo4DAO geo4dao = DAOManager.getGeo4DAO();
						Geo4Example geo4Example = new Geo4Example();
						geo4Example.createCriteria().andGeo3IdEqualTo(ProfesionalForm.this.geo3Id);
						geo4Example.setOrderByClause("nombre");
						ProfesionalForm.this.setLevel4(geo4dao.selectGeo4ByExample(geo4Example));
					}
				});
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		} else {
			setLevel4(new ArrayList<Geo4>());
		}
		return level4;
	}

	public void setLevel4(List<Geo4> level4) {
		this.level4 = level4;
	}

	public String getReferenceprice() {
		return referenceprice;
	}

	public void setReferenceprice(String referenceprice) {
		this.referenceprice = referenceprice;
	}

	public void addProduct() {
		// TODO validaciones
		SellBean productbean = new SellBean();
		productbean.setType(SellType.PRODUCT);
		productbean.setCategoryId(Integer.valueOf(this.getCategoryId()));
		productbean.setName(this.getSellName());
		productbean.setDescription(this.getSellDescription());
		productbean.setCategoryText(this.getCategorySelected());
		productbean.setReferencePrice(this.getReferenceprice());
		this.getSells().add(0, productbean);
		cleanSellFields();
	}

	private void cleanSellFields() {
		this.setCategoryId(null);
		this.setCategorySelected(null);
		this.setSellName(null);
		this.setSellDescription(null);
		this.setReferenceprice(null);
	}
	
	public List<SellBean> getSells() {
		int index = 0;
		for (SellBean sellBean : sells) {
			sellBean.setIndex(index++);
		}
		return sells;
	}

	public void setSells(List<SellBean> sells) {
		this.sells = sells;
	}

	public void removeSell(String index) {
		if (StringUtils.isEmpty(index)) {
			return;
		}
		if (!StringUtils.isNumeric(index)) {
			return;
		}
		int indexInt = Integer.parseInt(index);
		if (indexInt < getSells().size()) {
			this.getSells().remove(indexInt);
		}
	}

	public List<ServiceAreaBean> getServiceAreas() {
		int index = 0;
		for (ServiceAreaBean bean : serviceAreas) {
			bean.setIndex(index++);
		}
		return serviceAreas;
	}

	public void setServiceAreas(List<ServiceAreaBean> serviceAreas) {
		this.serviceAreas = serviceAreas;
	}

	public String getGeoLevel4Id() {
		return geoLevel4Id;
	}

	public void setGeoLevel4Id(String geoLevel4Id) {
		this.geoLevel4Id = geoLevel4Id;
	}

	public String getServiceAreaAutocompleter() {
		return serviceAreaAutocompleter;
	}

	public void setServiceAreaAutocompleter(String serviceAreaAutocompleter) {
		this.serviceAreaAutocompleter = serviceAreaAutocompleter;
	}

	public String getServiceAreaSelectedText() {
		return serviceAreaSelectedText;
	}

	public void setServiceAreaSelectedText(String serviceAreaSelectedText) {
		this.serviceAreaSelectedText = serviceAreaSelectedText;
	}

	public void addServiceArea() {
		ServiceAreaBean serviceAreaBean = new ServiceAreaBean();
		serviceAreaBean.setGeoLevelId(Integer.valueOf(this.getGeoLevel4Id()));
		serviceAreaBean.setServiceAreaText(this.getServiceAreaSelectedText());
		this.getServiceAreas().add(0, serviceAreaBean);
		cleanServiceAreaFields();
	}
	
	public void cleanServiceAreaFields() {
		this.setGeoLevel4Id(null);
		this.setServiceAreaAutocompleter(null);
		this.setServiceAreaSelectedText(null);
	}

	public void removeServiceArea(String index) {
		if (StringUtils.isEmpty(index)) {
			return;
		}
		if (!StringUtils.isNumeric(index)) {
			return;
		}
		int indexInt = Integer.parseInt(index);
		if (indexInt < getServiceAreas().size()) {
			this.getServiceAreas().remove(indexInt);
		}
	}

	public void addService() {
		// TODO validaciones
		SellBean servicebean = new SellBean();
		servicebean.setType(SellType.SERVICE);
		servicebean.setCategoryId(Integer.valueOf(this.getCategoryId()));
		servicebean.setName(this.getSellName());
		servicebean.setDescription(this.getSellDescription());
		servicebean.setCategoryText(this.getCategorySelected());
		servicebean.setReferencePrice(this.getReferenceprice());
		this.getSells().add(0, servicebean);
		cleanSellFields();
	}

	public void takeFacebookData(JSONObject authFacebookLogin) {
		this.setFacebookRegister(true);
		this.setFacebookId(authFacebookLogin.getString("id"));
		this.setFirstname(authFacebookLogin.getString("first_name"));
		this.setLastname(authFacebookLogin.getString("last_name"));
		this.setEmail(authFacebookLogin.getString("email"));
	}
	
	public Object executeLoginFB() throws SQLException, ValidationException {
		ProfesionalExample clientExample = new ProfesionalExample();
		clientExample.createCriteria().andEmailEqualTo(this.getEmail());
		List<Profesional> clients = DAOManager.getProfesionalDAO().selectProfesionalByExample(clientExample);
		if (!clients.isEmpty()) {
			Profesional client = clients.get(0);
			// si no tiene el fbid, aprovecho y lo updateo
			return WebsiteLoginForm.getWebsiteUserFor(client);
		} else {
			return null;
		}
	}

	public boolean isFacebookRegister() {
		return facebookRegister;
	}

	public void setFacebookRegister(boolean facebookRegister) {
		this.facebookRegister = facebookRegister;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public Profesional getLogged() {
		return logged;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategorySelected() {
		return categorySelected;
	}

	public void setCategorySelected(String categorySelected) {
		this.categorySelected = categorySelected;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public String getSellDescription() {
		return sellDescription;
	}

	public void setSellDescription(String sellDescription) {
		this.sellDescription = sellDescription;
	}

}
