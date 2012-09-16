package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.actions.AjaxFileUploadAction;
import com.tdil.struts.forms.AjaxUploadHandlerForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.tuafesta.dao.BlobDataDAO;
import com.tdil.tuafesta.dao.SellMediaDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.BlobData;
import com.tdil.tuafesta.model.SellMedia;
import com.tdil.tuafesta.model.SellMediaExample;
import com.tdil.tuafesta.struts.forms.beans.SellBean;
import com.tdil.tuafesta.utils.BlobHelper;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public abstract class EditProfesionalSellForm extends TransactionalValidationForm implements EditProfesionalDataForm, AjaxUploadHandlerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1351122768332927638L;
	private int id;
	private int objectId;
	
	protected SellBean edited;

	private UploadData image1;
	private UploadData image2;
	private UploadData image3;
	private UploadData image4;
	private UploadData image5;
	
	private String video1;
	private String video2;
	private String video3;
	private String video4;
	private String video5;
	
	private List<SellBean> sells = new ArrayList<SellBean>();

	
	public static String image_key = "EditProfesionalSellForm.logo";
	
	private static final int MAX_IMAGE_SIZE = 1000000;
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(EditProfesionalSellProductForm.class);
	}
	
	public void loadForEdit(int index) throws SQLException {
		edited = this.getSells().get(index);
		if (edited.getId() != 0) {
			// si ya estaba grabado
			if (!edited.isDataLoaded()) { // si no se cargaron los datos, los cargo de la base
				BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
				SellMediaDAO sellMediaDAO = DAOManager.getSellMediaDAO();
				SellMediaExample sellMediaExample = new SellMediaExample();
				sellMediaExample.createCriteria().andIdSellEqualTo(edited.getId()).andApprovedEqualTo(1); // TODO AUTO APP
				List<SellMedia> list = sellMediaDAO.selectSellMediaByExample(sellMediaExample);
				if (!list.isEmpty()) {
					SellMedia media = list.get(0);
					if (media.getIdBlobData1() != null && media.getIdBlobData1() != 0) {
						BlobData frontCover = blobDataDAO.selectBlobDataByPrimaryKey(media.getIdBlobData1());
						this.setImage1(new UploadData(frontCover.getFilename(), frontCover.getContent(), false));
						edited.setImage1(this.getImage1());
					}
					if (media.getIdBlobData2() != null && media.getIdBlobData2() != 0) {
						BlobData frontCover = blobDataDAO.selectBlobDataByPrimaryKey(media.getIdBlobData2());
						this.setImage2(new UploadData(frontCover.getFilename(), frontCover.getContent(), false));
						edited.setImage2(this.getImage2());
					}
					if (media.getIdBlobData3() != null && media.getIdBlobData3() != 0) {
						BlobData frontCover = blobDataDAO.selectBlobDataByPrimaryKey(media.getIdBlobData3());
						this.setImage3(new UploadData(frontCover.getFilename(), frontCover.getContent(), false));
						edited.setImage3(this.getImage3());
					}
					if (media.getIdBlobData4() != null && media.getIdBlobData4() != 0) {
						BlobData frontCover = blobDataDAO.selectBlobDataByPrimaryKey(media.getIdBlobData4());
						this.setImage4(new UploadData(frontCover.getFilename(), frontCover.getContent(), false));
						edited.setImage4(this.getImage4());
					}
					if (media.getIdBlobData5() != null && media.getIdBlobData5() != 0) {
						BlobData frontCover = blobDataDAO.selectBlobDataByPrimaryKey(media.getIdBlobData5());
						this.setImage5(new UploadData(frontCover.getFilename(), frontCover.getContent(), false));
						edited.setImage5(this.getImage5());
					}
				}
				edited.setDataLoaded(true);
			} else {
				this.setImage1(edited.getImage1());
				this.setImage2(edited.getImage2());
				this.setImage3(edited.getImage3());
				this.setImage4(edited.getImage4());
				this.setImage5(edited.getImage5());
			}
		} else {
			this.setImage1(edited.getImage1());
			this.setImage2(edited.getImage2());
			this.setImage3(edited.getImage3());
			this.setImage4(edited.getImage4());
			this.setImage5(edited.getImage5());
		}
		loadForEdit(edited);
	}
	
	protected abstract void loadForEdit(SellBean edited);

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.image1 = null;
		this.image2 = null;
		this.image3 = null;
		this.image4 = null;
		this.image5 = null;
		this.sells = new ArrayList<SellBean>();
	}
	
	public int getImageId0() {
		if (this.image1 == null) {
			return 0;
		} else {
			return 1;
		}
	}
	public int getImageId1() {
		if (this.image2 == null) {
			return 0;
		} else {
			return 1;
		}
	}
	public int getImageId2() {
		if (this.image3 == null) {
			return 0;
		} else {
			return 1;
		}
	}
	public int getImageId3() {
		if (this.image4 == null) {
			return 0;
		} else {
			return 1;
		}
	}
	public int getImageId4() {
		if (this.image5 == null) {
			return 0;
		} else {
			return 1;
		}
	}
	
	protected void createSellMedia(SellMediaDAO sellMediaDAO, int sellId, SellBean sell) throws SQLException {
		SellMedia sellMedia = new SellMedia();
		sellMedia.setIdSell(sellId);
		sellMedia.setDeleted(0);
		sellMedia.setApproved(1); // TODO AUTOAPP
		updateSellMedia(sellMedia, sell);
		sellMediaDAO.insertSellMedia(sellMedia);
	}
	
	protected void createOrUpdateSellMedia(SellMediaDAO sellMediaDAO, int sellId, SellBean sell) throws SQLException {
		SellMediaExample sellMediaExample = new SellMediaExample();
		sellMediaExample.createCriteria().andIdSellEqualTo(sellId).andApprovedEqualTo(1);
		List<SellMedia> sellMediaList = sellMediaDAO.selectSellMediaByExample(sellMediaExample);
		if (sellMediaList.isEmpty()) {
			SellMedia sellMedia = new SellMedia();
			sellMedia.setIdSell(sellId);
			sellMedia.setDeleted(0);
			sellMedia.setApproved(1); // TODO AUTOAPP
			updateSellMedia(sellMedia,sell);
			sellMediaDAO.insertSellMedia(sellMedia);
		} else {
			SellMedia sellMedia = sellMediaList.get(0);
			updateSellMedia(sellMedia,sell);
			sellMediaDAO.updateSellMediaByPrimaryKey(sellMedia);
		}
	}

	protected void deleteSellMediaFor(Integer id) throws SQLException {
		SellMediaExample sellMediaExample = new SellMediaExample();
		sellMediaExample.createCriteria().andIdSellEqualTo(id);
		SellMediaDAO sellMediaDAO = DAOManager.getSellMediaDAO();
		List<SellMedia> medias = sellMediaDAO.selectSellMediaByExample(sellMediaExample);
		for (SellMedia m : medias) {
			deleteBlobs(m, new SellBean()); // fuerzo el borrado
			sellMediaDAO.deleteSellMediaByPrimaryKey(m.getId());
		}
	}
	
	protected void updateSellMedia(SellMedia sellMedia, SellBean sell) throws SQLException {
		// TODO AUTOAPP
		deleteBlobs(sellMedia, sell);
		List<UploadData> toInsert = getImagesToInsert(sell);
		if (toInsert.size() > 0) {
			int id = BlobHelper.insertBlob(toInsert.get(0));
			sellMedia.setIdBlobData1(id);
			sellMedia.setExtBlobData1(toInsert.get(0).getExtension());
		} else {
			sellMedia.setIdBlobData1(0);
		}
		if (toInsert.size() > 1) {
			int id = BlobHelper.insertBlob(toInsert.get(1));
			sellMedia.setIdBlobData2(id);
			sellMedia.setExtBlobData2(toInsert.get(1).getExtension());
		} else {
			sellMedia.setIdBlobData2(0);
		}
		if (toInsert.size() > 2) {
			int id = BlobHelper.insertBlob(toInsert.get(2));
			sellMedia.setIdBlobData3(id);
			sellMedia.setExtBlobData3(toInsert.get(2).getExtension());
		} else {
			sellMedia.setIdBlobData3(0);
		}
		if (toInsert.size() > 3) {
			int id = BlobHelper.insertBlob(toInsert.get(3));
			sellMedia.setIdBlobData4(id);
			sellMedia.setExtBlobData4(toInsert.get(3).getExtension());
		} else {
			sellMedia.setIdBlobData4(0);
		}
		if (toInsert.size() > 4) {
			int id = BlobHelper.insertBlob(toInsert.get(4));
			sellMedia.setIdBlobData5(id);
			sellMedia.setExtBlobData5(toInsert.get(4).getExtension());
		} else {
			sellMedia.setIdBlobData5(0);
		}
	}

	protected void deleteBlobs(SellMedia sellMedia, SellBean sell) throws SQLException {
		if (shouldBeDeleted(sellMedia.getIdBlobData1(), sell.getImage1())) {
			BlobHelper.deleteBlob(sellMedia.getIdBlobData1());
		}
		if (shouldBeDeleted(sellMedia.getIdBlobData2(), sell.getImage2())) {
			BlobHelper.deleteBlob(sellMedia.getIdBlobData2());
		}
		if (shouldBeDeleted(sellMedia.getIdBlobData3(), sell.getImage3())) {
			BlobHelper.deleteBlob(sellMedia.getIdBlobData3());
		}
		if (shouldBeDeleted(sellMedia.getIdBlobData4(), sell.getImage4())) {
			BlobHelper.deleteBlob(sellMedia.getIdBlobData4());
		}
		if (shouldBeDeleted(sellMedia.getIdBlobData5(), sell.getImage5())) {
			BlobHelper.deleteBlob(sellMedia.getIdBlobData5());
		}
	}

	protected List<UploadData> getImagesToInsert(SellBean sell) {
		List<UploadData> result = new ArrayList<UploadData>();
		if (sell.getImage1() != null) {
			result.add(sell.getImage1());
		}
		if (sell.getImage2() != null) {
			result.add(sell.getImage2());
		}
		if (sell.getImage3() != null) {
			result.add(sell.getImage3());
		}
		if (sell.getImage4() != null) {
			result.add(sell.getImage4());
		}
		if (sell.getImage5() != null) {
			result.add(sell.getImage5());
		}
		return result;
	}

	protected boolean shouldBeDeleted(Integer idBlobData1, UploadData image1) {
		if (idBlobData1 == null) {
			return false;
		}
		if (idBlobData1 == 0) {
			return false;
		}
		if (image1 == null) {
			return true;
		}
		if (image1.isModified()) {
			return true;
		}
		return true; // por default se borra todo
	}
	
	public void handleAjaxFileUpload(Map<String, FileItem> fileItems, ValidationError validationError,
			Map<String, Object> result) {
		FileItem uploaded = fileItems.get(AjaxFileUploadAction.UPLOAD_NAME);
		UploadData uploadData = FieldValidation.validateFileItem(uploaded, image_key, true, validationError);
		if (uploadData != null) {
			long fileSize = uploaded.getSize();
			if (fileSize > MAX_IMAGE_SIZE) {
				validationError.setFieldError(image_key, ValidationErrors.TOO_BIG);
				return;
			}
			if ("upload0".equals(uploaded.getFieldName())) {
				setImage1(uploadData);
			}
			if ("upload1".equals(uploaded.getFieldName())) {
				setImage2(uploadData);
			}
			if ("upload2".equals(uploaded.getFieldName())) {
				setImage3(uploadData);
			}
			if ("upload3".equals(uploaded.getFieldName())) {
				setImage4(uploadData);
			}
			if ("upload4".equals(uploaded.getFieldName())) {
				setImage5(uploadData);
			}
		}
		result.put("result", "OK");
	}
	
	protected void clearMediaFields() {
		this.image1 = null;
		this.image2 = null;
		this.image3 = null;
		this.image4 = null;
		this.image5 = null;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void basicValidate(ValidationError validationError) {
	}

	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
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

	public EditProfesionalSellForm() {
		super();
	}

	public UploadData getImage1() {
		return image1;
	}

	public void setImage1(UploadData image1) {
		this.image1 = image1;
	}

	public UploadData getImage2() {
		return image2;
	}

	public void setImage2(UploadData image2) {
		this.image2 = image2;
	}

	public UploadData getImage3() {
		return image3;
	}

	public void setImage3(UploadData image3) {
		this.image3 = image3;
	}

	public UploadData getImage4() {
		return image4;
	}

	public void setImage4(UploadData image4) {
		this.image4 = image4;
	}

	public UploadData getImage5() {
		return image5;
	}

	public void setImage5(UploadData image5) {
		this.image5 = image5;
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

	public String getVideo1() {
		return video1;
	}

	public void setVideo1(String video1) {
		this.video1 = video1;
	}

	public String getVideo2() {
		return video2;
	}

	public void setVideo2(String video2) {
		this.video2 = video2;
	}

	public String getVideo3() {
		return video3;
	}

	public void setVideo3(String video3) {
		this.video3 = video3;
	}

	public String getVideo4() {
		return video4;
	}

	public void setVideo4(String video4) {
		this.video4 = video4;
	}

	public String getVideo5() {
		return video5;
	}

	public void setVideo5(String video5) {
		this.video5 = video5;
	}

	public SellBean getEdited() {
		return edited;
	}

	public void setEdited(SellBean edited) {
		this.edited = edited;
	}

}