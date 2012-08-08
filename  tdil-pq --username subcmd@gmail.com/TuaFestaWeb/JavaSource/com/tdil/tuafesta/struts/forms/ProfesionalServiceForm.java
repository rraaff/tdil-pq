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
import com.tdil.tuafesta.dao.ProfesionalServiceDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ProfesionalService;
import com.tdil.tuafesta.model.ProfesionalServiceExample;
import com.tdil.tuafesta.model.ProfesionalServiceExample.Criteria;
import com.tdil.tuafesta.utils.ServiceCategoryTreeNode;
import com.tdil.tuafesta.utils.ServiceCategoryUtils;
import com.tdil.validations.FieldValidation;

public class ProfesionalServiceForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

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
	private List<ProfesionalService> allServices;
	
	private static String name_key = "ProfesionalService.name";
	private static String name_duplicated_key = "DUPLICATED";
	private static String description_key = "ProfesionalService.description";

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
		ProfesionalServiceExample serviceExample = new ProfesionalServiceExample();
		serviceExample.createCriteria().andApprovedEqualTo(1);
		serviceExample.setOrderByClause("name");
		this.setAllServices(DAOManager.getProfesionalServiceDAO().selectProfesionalServiceByExample(serviceExample));
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		ProfesionalServiceDAO serviceDAO = DAOManager.getProfesionalServiceDAO();
		ProfesionalService service = serviceDAO.selectProfesionalServiceByPrimaryKey(id);
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
		ProfesionalServiceExample serviceExample = new ProfesionalServiceExample();
		serviceExample.setOrderByClause("name");
		this.setAllServices(DAOManager.getProfesionalServiceDAO().selectProfesionalServiceByExample(serviceExample));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		ProfesionalService service = DAOManager.getProfesionalServiceDAO().selectProfesionalServiceByPrimaryKey(this.getObjectId());
		service.setDeleted(service.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getProfesionalServiceDAO().updateProfesionalServiceByPrimaryKeySelective(service);
	}
	

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getName(), name_key, 250, validationError);
		FieldValidation.validateText(this.getDescription(), description_key, 4000, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		ProfesionalServiceDAO serviceDAO = DAOManager.getProfesionalServiceDAO();
		{// Validate duplicated name
			ProfesionalServiceExample serviceExample = new ProfesionalServiceExample();
			Criteria criteria = serviceExample.createCriteria();
			criteria.andNameEqualTo(this.getName());
			List<ProfesionalService> list = serviceDAO.selectProfesionalServiceByExample(serviceExample);
			if (!list.isEmpty()) {
				ProfesionalService db = list.get(0);
				if (!db.getId().equals(this.getObjectId())) {
					validationError.setFieldError(name_key, name_duplicated_key);
				}
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		ProfesionalServiceDAO serviceDAO = DAOManager.getProfesionalServiceDAO();
		if (this.getObjectId() == 0) {
			ProfesionalService service = new ProfesionalService();
			setData(service);
			service.setDeleted(0);
			serviceDAO.insertProfesionalService(service);
		} else {
			ProfesionalService service = new ProfesionalService();
			service.setId(this.getObjectId());
			setData(service);
			serviceDAO.updateProfesionalServiceByPrimaryKeySelective(service);
		}
	}

	public void setData(ProfesionalService service) {
		service.setName(this.getName());
		service.setDescription(this.getDescription());
		service.setIdCategory(this.getCategoryId());
		if (this.isApproved()) {
			service.setApproved(1);
		} else {
			service.setApproved(0);
		}
		if (StringUtils.isNumeric(this.getAveragePrice()) && !StringUtils.isEmpty(this.getAveragePrice())) {
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

	public List<ProfesionalService> getAllServices() {
		return allServices;
	}

	public void setAllServices(List<ProfesionalService> allServices) {
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
