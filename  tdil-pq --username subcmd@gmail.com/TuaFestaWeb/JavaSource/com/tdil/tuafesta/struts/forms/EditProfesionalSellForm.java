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
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.struts.forms.beans.SellBean;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public abstract class EditProfesionalSellForm extends TransactionalValidationForm implements EditProfesionalDataForm, AjaxUploadHandlerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1351122768332927638L;
	private int id;
	private int objectId;

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

}