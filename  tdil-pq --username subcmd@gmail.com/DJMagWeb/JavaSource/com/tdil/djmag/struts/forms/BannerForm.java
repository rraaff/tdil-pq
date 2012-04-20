package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.djmag.dao.BannerDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Banner;
import com.tdil.djmag.model.BannerExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class BannerForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String description;
	private String htmlContent;
	private boolean deleted;
	
	private List<Banner> allBanners;
	
	private static String description_key = "Banner.description";
	private static String htmlContent_key = "Banner.htmlContent";

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.description = null;
		this.htmlContent = null;
		this.deleted = false;
	}

	@Override
	public void init() throws SQLException {
		BannerExample bannerExample = new BannerExample();
		bannerExample.setOrderByClause("description");
		this.setAllBanners(DAOManager.getBannerDAO().selectBannerByExampleWithoutBLOBs(bannerExample));
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		BannerDAO bannerDAO = DAOManager.getBannerDAO();
		Banner banner = bannerDAO.selectBannerByPrimaryKey(id);
		if (banner != null) {
			this.objectId = id;
			this.description = banner.getDescription();
			this.htmlContent = banner.getHtmlcontent();
			this.deleted = banner.getDeleted() == 1;
		} 
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		BannerExample bannerExample = new BannerExample();
		bannerExample.setOrderByClause("description");
		this.setAllBanners(DAOManager.getBannerDAO().selectBannerByExampleWithoutBLOBs(bannerExample));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		BannerExample bannerExample = new BannerExample();
		bannerExample.createCriteria().andIdEqualTo(this.getObjectId());
		Banner banner = DAOManager.getBannerDAO().selectBannerByExampleWithoutBLOBs(bannerExample).get(0);
		banner.setDeleted(banner.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getBannerDAO().updateBannerByExampleWithoutBLOBs(banner, bannerExample);
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getDescription(), description_key, 250, validationError);
		FieldValidation.validateText(this.getHtmlContent(), htmlContent_key, ValidationErrors.TEXT_LENGTH, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		BannerDAO bannerDAO = DAOManager.getBannerDAO();
		{// Validate duplicated name
			BannerExample bannerExample = new BannerExample();
			bannerExample.createCriteria().andDescriptionEqualTo(this.getDescription());
			List<Banner> list = bannerDAO.selectBannerByExampleWithoutBLOBs(bannerExample);
			if (!list.isEmpty()) {
				Banner db = list.get(0);
				if (!db.getId().equals(this.getObjectId())) {
					validationError.setFieldError(description_key, ValidationErrors.DUPLICATED);
				}
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		BannerDAO bannerDAO = DAOManager.getBannerDAO();
		if (this.getObjectId() == 0) {
			Banner banner = new Banner();
			banner.setDescription(this.getDescription());
			banner.setHtmlcontent(this.getHtmlContent());
			banner.setDeleted(this.isDeleted() ? 1 : 0);
			bannerDAO.insertBanner(banner);
		} else {
			Banner banner = new Banner();
			banner.setId(this.getObjectId());
			banner.setDescription(this.getDescription());
			banner.setHtmlcontent(this.getHtmlContent());
			bannerDAO.updateBannerByPrimaryKeySelective(banner);
		}
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public List<Banner> getAllBanners() {
		return allBanners;
	}

	public void setAllBanners(List<Banner> allBanners) {
		this.allBanners = allBanners;
	}

}
