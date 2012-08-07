package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.ProfesionalProductDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ProfesionalProduct;
import com.tdil.tuafesta.model.ProfesionalProductExample;
import com.tdil.tuafesta.model.ProfesionalProductExample.Criteria;
import com.tdil.tuafesta.utils.ServiceCategoryTreeNode;
import com.tdil.tuafesta.utils.ServiceCategoryUtils;
import com.tdil.validations.FieldValidation;

public class ProfesionalProductForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String name;
	private String description;
	private String averagePrice;
	private boolean approved = true;
	private int categoryId;
	private List<ProfesionalProduct> allServices;
	
	private static String name_key = "ProfesionalProduct.name";
	private static String name_duplicated_key = "DUPLICATED";
	private static String description_key = "ProfesionalProduct.description";

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.name = null;
		this.description = null;
		this.averagePrice = null;
		this.approved = true;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.approved = false;
	}

	@Override
	public void init() throws SQLException {
		ProfesionalProductExample serviceExample = new ProfesionalProductExample();
		serviceExample.createCriteria().andApprovedEqualTo(1);
		serviceExample.setOrderByClause("name");
		this.setAllServices(DAOManager.getProfesionalProductDAO().selectProfesionalProductByExample(serviceExample));
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		ProfesionalProductDAO serviceDAO = DAOManager.getProfesionalProductDAO();
		ProfesionalProduct service = serviceDAO.selectProfesionalProductByPrimaryKey(id);
		if (service != null) {
			this.objectId = id;
			this.name = service.getName();
			this.description = service.getDescription();
			this.categoryId = service.getIdCategory() != null ? service.getIdCategory() : 0;
			this.approved = service.getApproved().equals(1);
			if (service.getAveragePrice() != null) {
				this.averagePrice = String.valueOf(service.getAveragePrice());
			}
		} 
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		ProfesionalProductExample serviceExample = new ProfesionalProductExample();
		serviceExample.setOrderByClause("name");
		this.setAllServices(DAOManager.getProfesionalProductDAO().selectProfesionalProductByExample(serviceExample));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		ProfesionalProduct service = DAOManager.getProfesionalProductDAO().selectProfesionalProductByPrimaryKey(this.getObjectId());
		service.setDeleted(service.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getProfesionalProductDAO().updateProfesionalProductByPrimaryKeySelective(service);
	}
	

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getName(), name_key, 250, validationError);
		FieldValidation.validateText(this.getDescription(), description_key, 4000, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		ProfesionalProductDAO serviceDAO = DAOManager.getProfesionalProductDAO();
		{// Validate duplicated name
			ProfesionalProductExample serviceExample = new ProfesionalProductExample();
			Criteria criteria = serviceExample.createCriteria();
			criteria.andNameEqualTo(this.getName());
			List<ProfesionalProduct> list = serviceDAO.selectProfesionalProductByExample(serviceExample);
			if (!list.isEmpty()) {
				ProfesionalProduct db = list.get(0);
				if (!db.getId().equals(this.getObjectId())) {
					validationError.setFieldError(name_key, name_duplicated_key);
				}
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ProfesionalProductDAO serviceDAO = DAOManager.getProfesionalProductDAO();
		if (this.getObjectId() == 0) {
			ProfesionalProduct service = new ProfesionalProduct();
			setData(service);
			service.setDeleted(0);
			serviceDAO.insertProfesionalProduct(service);
		} else {
			ProfesionalProduct service = new ProfesionalProduct();
			service.setId(this.getObjectId());
			setData(service);
			serviceDAO.updateProfesionalProductByPrimaryKeySelective(service);
		}
	}

	public void setData(ProfesionalProduct service) {
		service.setName(this.getName());
		service.setDescription(this.getDescription());
		service.setIdCategory(this.getCategoryId());
		if (this.isApproved()) {
			service.setApproved(1);
		} else {
			service.setApproved(0);
		}
		if (StringUtils.isNumeric(this.getAveragePrice())) {
			service.setAveragePrice(Integer.valueOf(this.getAveragePrice()));
		} else {
			service.setAveragePrice(null);
		}
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProfesionalProduct> getAllServices() {
		return allServices;
	}

	public void setAllServices(List<ProfesionalProduct> allServices) {
		this.allServices = allServices;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(String averagePrice) {
		this.averagePrice = averagePrice;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public static List<ServiceCategoryTreeNode> getServiceCategoryTree() {
		return ServiceCategoryUtils.getTree(false);
	}
}
