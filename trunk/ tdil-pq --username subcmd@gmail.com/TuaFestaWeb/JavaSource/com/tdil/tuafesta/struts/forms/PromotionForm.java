package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Promotion;
import com.tdil.tuafesta.model.PromotionExample;
import com.tdil.utils.DateUtils;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class PromotionForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	
	private String startdate;
	private String enddate;
	
	private String name;
	private String description;
	private List<Promotion> allPromotion;
	
	public static String startdate_key = "Promotion.startdate";
	public static String enddate_key = "Promotion.enddate";
	public static String name_key = "Promotion.name";
	public static String description_key = "Promotion.description";
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.name = null;
		this.description = null;
		this.startdate = null;
		this.enddate = null;
	}

	@Override
	public void init() throws SQLException {
		reloadList();
	}

	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		reloadList();
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		Promotion professionalCategory = DAOManager.getPromotionDAO().selectPromotionByPrimaryKey(this.getObjectId());
		professionalCategory.setDeleted(professionalCategory.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getPromotionDAO().updatePromotionByPrimaryKeySelective(professionalCategory);
	}
	
	private void reloadList() throws SQLException {
		PromotionExample promotionExample = new PromotionExample();
		promotionExample.setOrderByClause("startDate DESC");
		setAllPromotion(DAOManager.getPromotionDAO().selectPromotionByExample(promotionExample));
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		Promotion systemProperty = DAOManager.getPromotionDAO().selectPromotionByPrimaryKey(id);
		if (systemProperty != null) {
			this.objectId = id;
			this.name = systemProperty.getName();
			this.description= systemProperty.getDescription();
			setStartdate(DateUtils.formatDate(systemProperty.getStartdate()));
			setEnddate(DateUtils.formatDate(systemProperty.getEnddate()));
		} 
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getName(), name_key, 100, validationError);
		FieldValidation.validateText(this.getDescription(), description_key, 4000, validationError);
		Date starDate = com.tdil.utils.DateUtils.parseDate(this.getStartdate());
		if (starDate == null) {
			validationError.setFieldError(startdate_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
		Date endDate = com.tdil.utils.DateUtils.parseDate(this.getEnddate());
		if (endDate == null) {
			validationError.setFieldError(enddate_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
		if (starDate != null && endDate != null) {
			if (starDate.after(endDate)) {
				validationError.setFieldError(startdate_key, "DATE_ORDER_INVALID");
			}
		}
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		
	}

	@Override
	public void save() throws SQLException, ValidationException {
		if (this.getObjectId() == 0) {
			// inserto
			Promotion promotion = new Promotion();
			setData(promotion);
			promotion.setDeleted(0);
			DAOManager.getPromotionDAO().insertPromotion(promotion);
		} else {
			Promotion promotion = DAOManager.getPromotionDAO().selectPromotionByPrimaryKey(this.getObjectId());
			setData(promotion);
			DAOManager.getPromotionDAO().updatePromotionByPrimaryKey(promotion);
		}
	}

	private void setData(Promotion promotion) {
		promotion.setName(this.getName());
		promotion.setDescription(this.getDescription());
		promotion.setStartdate(DateUtils.parseDate(this.getStartdate()));
		promotion.setEnddate(DateUtils.parseDate(this.getEnddate()));
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Promotion> getAllPromotion() {
		return allPromotion;
	}

	public void setAllPromotion(List<Promotion> allPromotion) {
		this.allPromotion = allPromotion;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

}
