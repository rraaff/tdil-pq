package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.tdil.djmag.dao.MagazineDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Magazine;
import com.tdil.djmag.model.MagazineExample;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class MagazineForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	private static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private boolean deleted;
	
	private String description;
	private String publishDate;

	private UploadData frontCover;
	private UploadData magazineContent;
	private FormFile frontCoverFormFile;
	private FormFile magazineContentFormFile;
	
	private List<Magazine> allMagazines;
	
	private static String description_key = "Magazine.description";
	private static String publish_date_key = "Magazine.publish_date";
	private static String front_cover_key = "Magazine.front_cover";
	private static String magazine_key = "Magazine.magazine_content";
	
	private static final int MAX_FRONT_COVER_SIZE = 2000000;
	private static final int MAX_MAGAZINE_SIZE = 5000000;
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.description = null;
		this.publishDate = null;
		this.frontCover = null;
		this.magazineContent = null;
		this.deleted = false;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.deleted = false;
	}

	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		MagazineExample example = new MagazineExample();
		example.setOrderByClause("publish_date desc");
		this.setAllMagazines(DAOManager.getMagazineDAO().selectMagazineByExampleWithoutBLOBs(example));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		MagazineExample magazineExample = new MagazineExample();
		magazineExample.createCriteria().andIdEqualTo(this.getObjectId());
		Magazine magazine = DAOManager.getMagazineDAO().selectMagazineByExampleWithoutBLOBs(magazineExample).get(0);
		magazine.setDeleted(magazine.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getMagazineDAO().updateMagazineByExampleWithoutBLOBs(magazine, magazineExample);
	}

	@Override
	public void init() throws SQLException {
		MagazineExample example = new MagazineExample();
		example.setOrderByClause("publish_date desc");
		this.setAllMagazines(DAOManager.getMagazineDAO().selectMagazineByExampleWithoutBLOBs(example));
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		MagazineDAO magazineDAO = DAOManager.getMagazineDAO();
		Magazine magazine = magazineDAO.selectMagazineByPrimaryKey(id);
		if (magazine != null) {
			this.objectId = id;
			this.description = magazine.getDescription();
			this.publishDate = formatDate(magazine.getPublishDate());
			this.setFrontCover(new UploadData(magazine.getFrontcoverfilename(), magazine.getFrontcovercontent()));
			if (!StringUtils.isEmpty(magazine.getMagazinecontentfilename())) {
				this.setMagazineContent(new UploadData(magazine.getMagazinecontentfilename(), magazine.getMagazinecontent()));
			} else {
				this.setMagazineContent(null);
			}
			this.deleted = magazine.getDeleted() == 1;
		} 
	}
	
	public boolean getHasMagazineContent() {
		return this.getMagazineContent() != null;
	}
	
	public boolean getHasFrontCover() {
		return this.getFrontCover() != null;
	}

	public void uploadFrontCover(ValidationError error) {
		UploadData uploadData = FieldValidation.validateFormFile(this.getFrontCoverFormFile(), front_cover_key, true, error);
		if (uploadData != null) {
			int fileSize = this.getFrontCoverFormFile().getFileSize();
			if (fileSize > MAX_FRONT_COVER_SIZE) {
				error.setFieldError(front_cover_key, ValidationErrors.TOO_BIG);
				this.setFrontCover(null);
				return;
			}
			this.setFrontCover(uploadData);
		}
	}
	
	public void uploadMagazineContent(ValidationError error) {
		UploadData uploadData = FieldValidation.validateFormFile(this.getMagazineContentFormFile(), magazine_key, true, error);
		if (uploadData != null) {
			int fileSize = this.getMagazineContentFormFile().getFileSize();
			if (fileSize > MAX_MAGAZINE_SIZE) {
				error.setFieldError(magazine_key, ValidationErrors.TOO_BIG);
				this.setMagazineContent(null);
				return;
			}
			this.setMagazineContent(uploadData);			
		}
	}
	
	public void deleteFrontCover() {
		this.setFrontCover(null);			
	}
	
	public void deleteMagazineContent() {
		this.setMagazineContent(null);			
	}
	
	public static String formatDate(Date fromDate2) {
		if (fromDate2 == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM);
		return dateFormat.format(fromDate2);
	}
	
	private Date parseDate(String fromDate2) {
		try {
			if (StringUtils.isEmpty(fromDate2)) {
				return null;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY_MM);
			return dateFormat.parse(fromDate2);
		} catch (ParseException e) {
			getLog().error(e.getMessage(), e);
			//throw new RuntimeException(e);
			return null;
		}
	}
	
	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateDate(this.getPublishDate(), publish_date_key, DATE_FORMAT_YYYY_MM, true, validationError);
		FieldValidation.validateText(this.getDescription(), description_key, 4000, validationError);
		if (this.getFrontCover() == null) {
			validationError.setFieldError(front_cover_key, ValidationErrors.CANNOT_BE_EMPTY);
		}
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		MagazineDAO magazineDAO = DAOManager.getMagazineDAO();
		{// Validate duplicated name
			/*MagazineExample example = new MagazineExample();
			example.createCriteria().andIdCountryEqualTo(this.getCountryId());
			List<Magazine> list = magazineDAO.selectMagazineByExampleWithoutBLOBs(example);
			if (!list.isEmpty()) {
				Magazine db = list.get(0);
				if (!db.getId().equals(this.getObjectId())) {
					validationError.setFieldError(country_key, ValidationErrors.DUPLICATED);
				}
			}*/
		}
		
	}

	@Override
	public void save() throws SQLException, ValidationException {
		MagazineDAO magazineDAO = DAOManager.getMagazineDAO();
		if (this.getObjectId() == 0) {
			Magazine magazine = new Magazine();
			setData(magazine);
			magazine.setDeleted(this.isDeleted() ? 1 : 0);
			magazineDAO.insertMagazine(magazine);
		} else {
			Magazine magazine = new Magazine();
			magazine.setId(this.getObjectId());
			setData(magazine);
			magazineDAO.updateMagazineByPrimaryKeySelective(magazine);
		}
		
	}

	private void setData(Magazine magazine) {
		magazine.setDescription(this.getDescription());
		magazine.setPublishDate(parseDate(this.getPublishDate()));
		magazine.setFrontcoverfilename(this.getFrontCover().getFileName());
		magazine.setFrontcovercontent(this.getFrontCover().getData());
		if (this.getHasMagazineContent()) {
			magazine.setMagazinecontentfilename(this.getMagazineContent().getFileName());
			magazine.setMagazinecontent(this.getMagazineContent().getData());
		} else {
			magazine.setMagazinecontentfilename("");
			magazine.setMagazinecontent(null);
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

	public List<Magazine> getAllMagazines() {
		return allMagazines;
	}

	public void setAllMagazines(List<Magazine> allRankings) {
		this.allMagazines = allRankings;
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

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public UploadData getFrontCover() {
		return frontCover;
	}

	public void setFrontCover(UploadData frontCover) {
		this.frontCover = frontCover;
	}

	public UploadData getMagazineContent() {
		return magazineContent;
	}

	public void setMagazineContent(UploadData magazineContent) {
		this.magazineContent = magazineContent;
	}

	public FormFile getFrontCoverFormFile() {
		return frontCoverFormFile;
	}

	public void setFrontCoverFormFile(FormFile frontCoverFormFile) {
		this.frontCoverFormFile = frontCoverFormFile;
	}

	public FormFile getMagazineContentFormFile() {
		return magazineContentFormFile;
	}

	public void setMagazineContentFormFile(FormFile magazineContentFormFile) {
		this.magazineContentFormFile = magazineContentFormFile;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(MagazineForm.class);
	}
}
