package com.tdil.tuafesta.struts.forms;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.actions.AjaxFileUploadAction;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.SearchForm;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.tuafesta.dao.PromotionPhotoDAO;
import com.tdil.tuafesta.dao.PromotionSellDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.BlobData;
import com.tdil.tuafesta.model.Promotion;
import com.tdil.tuafesta.model.PromotionExample;
import com.tdil.tuafesta.model.PromotionPhoto;
import com.tdil.tuafesta.model.PromotionPhotoExample;
import com.tdil.tuafesta.model.PromotionSell;
import com.tdil.tuafesta.model.PromotionSellExample;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.tuafesta.struts.forms.beans.BlobBean;
import com.tdil.tuafesta.struts.forms.beans.SellBean;
import com.tdil.tuafesta.utils.BlobHelper;
import com.tdil.utils.DateUtils;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class PromotionForm extends TransactionalValidationForm implements ToggleDeletedFlagForm, SearchForm, AjaxUploadHandlerForm {

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
	private String price;
	
	private List<BlobBean> photos = new ArrayList<BlobBean>();
	
	private List<SellBean> sells = new ArrayList<SellBean>();
	
	private SearchSellForm searchForm = new SearchSellForm();
	
	private List<Promotion> allPromotion;
	
	public static String startdate_key = "Promotion.startdate";
	public static String enddate_key = "Promotion.enddate";
	public static String name_key = "Promotion.name";
	public static String description_key = "Promotion.description";
	public static String photo_key = "Promotion.photo";
	public static String price_key = "Promotion.price";
	
	private static final int MAX_PHOTO_SIZE = 1000000;
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.name = null;
		this.description = null;
		this.price = null;
		this.startdate = null;
		this.enddate = null;
		this.sells = new ArrayList<SellBean>();
		this.photos = new ArrayList<BlobBean>();
		this.searchForm = new SearchSellForm();
	}
	
	public void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError error,
			Map<String, Object> result) {
		FileItem uploaded = fileItems.get(AjaxFileUploadAction.UPLOAD_NAME);
		UploadData uploadData = FieldValidation.validateFileItem(uploaded, photo_key, true, error);
		if (uploadData != null) {
			long fileSize = uploaded.getSize();
			if (fileSize > MAX_PHOTO_SIZE) {
				error.setFieldError(photo_key, ValidationErrors.TOO_BIG);
				return;
			}
			this.getPhotos().add(new BlobBean(uploadData));
		}
		result.put("result", "OK");
	}
	
	@Override
	public void search() throws ValidationException {
		this.searchForm.search();
	}
	
	public int getMaxImages() {
		return this.getPhotos().size();
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
		Promotion promotion = DAOManager.getPromotionDAO().selectPromotionByPrimaryKey(id);
		if (promotion != null) {
			this.objectId = id;
			this.name = promotion.getName();
			this.description= promotion.getDescription();
			setStartdate(DateUtils.formatDate(promotion.getStartdate()));
			setEnddate(DateUtils.formatDate(promotion.getEnddate()));
			this.setPrice(promotion.getPrice().toString());
			PromotionPhotoDAO photoDAO = DAOManager.getPromotionPhotoDAO();
			PromotionPhotoExample promotionPhotoExample = new PromotionPhotoExample();
			promotionPhotoExample.createCriteria().andIdPromotionEqualTo(id);
			promotionPhotoExample.setOrderByClause("orderNumber");
			List<PromotionPhoto> oldPhotos = photoDAO.selectPromotionPhotoByExample(promotionPhotoExample);
			for (PromotionPhoto photo : oldPhotos) {
				BlobData blobData = DAOManager.getBlobDataDAO().selectBlobDataByPrimaryKey(photo.getIdBlobData());
				this.getPhotos().add(new BlobBean(new UploadData(blobData.getFilename(), blobData.getContent(), false)));
			}
			PromotionSellExample promotionSellExample = new PromotionSellExample();
			promotionSellExample.createCriteria().andIdPromotionEqualTo(id);
			List<PromotionSell> promotionSells = DAOManager.getPromotionSellDAO().selectPromotionSellByExample(promotionSellExample);
			for (PromotionSell promotionSell : promotionSells) {
				Sell sell = DAOManager.getSellDAO().selectSellByPrimaryKey(promotionSell.getIdSell());
				if (sell.getType().equals(SellType.PRODUCT)) {
					this.getSells().add(new SellBean(DAOManager.getSellDAO().selectSellProductValueObject(sell.getId())));
				} else {
					this.getSells().add(new SellBean(DAOManager.getSellDAO().selectSellServiceValueObject(sell.getId())));
				}
			}
		} 
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getName(), name_key, 100, validationError);
		FieldValidation.validateText(this.getDescription(), description_key, 4000, validationError);
		FieldValidation.validateBigDecimal(this.getPrice(), price_key, 1, Integer.MAX_VALUE, validationError);
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
		if (this.getPhotos().isEmpty()) {
			validationError.setFieldError(photo_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		
	}
	
	public void addSell(int index) {
		try {
			SellValueObject sellValueObject = this.getSearchForm().getSearchResult().get(index);
			SellBean sellBean = new SellBean(sellValueObject);
			this.getSells().add(sellBean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removeSell(int index) {
		this.getSells().remove(index);
	}

	@Override
	public void save() throws SQLException, ValidationException {
		int id = this.getObjectId();
		if (this.getObjectId() == 0) {
			// inserto
			Promotion promotion = new Promotion();
			setData(promotion);
			promotion.setDeleted(0);
			id = DAOManager.getPromotionDAO().insertPromotion(promotion);
		} else {
			Promotion promotion = DAOManager.getPromotionDAO().selectPromotionByPrimaryKey(this.getObjectId());
			setData(promotion);
			DAOManager.getPromotionDAO().updatePromotionByPrimaryKey(promotion);
		}
		// borro todas las photos
		PromotionPhotoDAO photoDAO = DAOManager.getPromotionPhotoDAO();
		PromotionPhotoExample promotionPhotoExample = new PromotionPhotoExample();
		promotionPhotoExample.createCriteria().andIdPromotionEqualTo(id);
		List<PromotionPhoto> oldPhotos = photoDAO.selectPromotionPhotoByExample(promotionPhotoExample);
		for (PromotionPhoto photo : oldPhotos) {
			BlobHelper.deleteBlob(photo.getIdBlobData());
			photoDAO.deletePromotionPhotoByPrimaryKey(photo.getId());
		}
		// creo nuevas
		int index = 0;
		for (BlobBean newPhoto : this.getPhotos()) {
			int blobid = BlobHelper.insertBlob(newPhoto.getUploadData());
			PromotionPhoto promotionPhoto = new PromotionPhoto();
			promotionPhoto.setDeleted(0);
			promotionPhoto.setIdPromotion(id);
			promotionPhoto.setIdBlobData(blobid);
			promotionPhoto.setOrdernumber(index++);
			promotionPhoto.setExtBlobData(newPhoto.getUploadData().getExtension());
			photoDAO.insertPromotionPhoto(promotionPhoto);
		}
		// borro todos los productos 
		PromotionSellDAO promotionSellDAO = DAOManager.getPromotionSellDAO();
		PromotionSellExample promotionSellExample = new PromotionSellExample();
		promotionSellExample.createCriteria().andIdPromotionEqualTo(id);
		promotionSellDAO.deletePromotionSellByExample(promotionSellExample);
		// Agrego los que tengo en memoria
		for (SellBean sellBean : this.getSells()) {
			PromotionSell promotionSell = new PromotionSell();
			promotionSell.setIdPromotion(id);
			promotionSell.setIdSell(sellBean.getId());
			promotionSell.setDeleted(0);
			promotionSellDAO.insertPromotionSell(promotionSell);
		}
	}

	private void setData(Promotion promotion) {
		promotion.setName(this.getName());
		promotion.setDescription(this.getDescription());
		promotion.setStartdate(DateUtils.parseDate(this.getStartdate()));
		promotion.setEnddate(DateUtils.parseDate(this.getEnddate()));
		BigDecimal refPrice = new BigDecimal(this.getPrice());
		promotion.setPrice(refPrice);
	}
	
	public void moveImageUp(int index) {
		if (index > 0) {
			BlobBean prev = this.getPhotos().get(index - 1);
			BlobBean act = this.getPhotos().get(index);
			this.getPhotos().set(index - 1, act);
			this.getPhotos().set(index, prev);
		}
	}
	
	public void moveImageDown(int index) {
		if (index < this.getPhotos().size() - 1) {
			BlobBean next = this.getPhotos().get(index + 1);
			BlobBean act = this.getPhotos().get(index);
			this.getPhotos().set(index + 1, act);
			this.getPhotos().set(index, next);
		}
	}
	
	public void deleteImage(int id2) {
		BlobBean noteImageBean = this.getPhotos().get(id2);
		this.getPhotos().remove(noteImageBean);
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

	public List<SellBean> getSells() {
		return sells;
	}

	public void setSells(List<SellBean> sells) {
		this.sells = sells;
	}

	public SearchSellForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(SearchSellForm searchForm) {
		this.searchForm = searchForm;
	}

	public List<BlobBean> getPhotos() {
		return photos;
	}

	public void setPhotos(List<BlobBean> photos) {
		this.photos = photos;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
