package com.tdil.djmag.struts.forms;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.dao.FooterDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.RankingNoteCountry;
import com.tdil.djmag.model.Footer;
import com.tdil.djmag.model.FooterExample;
import com.tdil.djmag.struts.action.UploadImageNoteAction;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.utils.SystemConfig;
import com.tdil.utils.SystemPropertyCache;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class FooterForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private String htmlContent;
	private int countryId;
	
	private List<Footer> allFooters;
	private List<CountrySelectionVO> selectedCountries = new ArrayList<CountrySelectionVO>();
	private static List<Country> allCountries = new ArrayList<Country>();
	
	private static String htmlContent_key = "Footer.htmlContent";
	private static String country_key = "Footer.country";

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.countryId = 0;
		this.htmlContent = null;
		this.resetSelectedCountries();
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	private void clearSelectedCountries() {
		for (CountrySelectionVO countrySelectionVO : getSelectedCountries()) {
			countrySelectionVO.setSelected(false);
		}
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		FooterExample example = new FooterExample();
		this.setAllFooters(DAOManager.getFooterDAO().selectFooterByExampleWithoutBLOBs(example));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		FooterExample videoExample = new FooterExample();
		videoExample.createCriteria().andIdEqualTo(this.getObjectId());
		Footer video = DAOManager.getFooterDAO().selectFooterByExampleWithoutBLOBs(videoExample).get(0);
		video.setDeleted(video.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getFooterDAO().updateFooterByExampleWithoutBLOBs(video, videoExample);
	}

	@Override
	public void init() throws SQLException {
		FooterExample example = new FooterExample();
		this.setAllFooters(DAOManager.getFooterDAO().selectFooterByExampleWithoutBLOBs(example));
		
		CountryExample countryExample = new CountryExample();
		countryExample.setOrderByClause("name");
		setAllCountries(DAOManager.getCountryDAO().selectCountryByExample(countryExample));
		
		this.resetSelectedCountries();
	}
	
	private void resetSelectedCountries() {
		this.getSelectedCountries().clear();
		for (Country country : getAllCountries()) {
			this.getSelectedCountries().add(new CountrySelectionVO(country));
		}
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		FooterDAO videoDAO = DAOManager.getFooterDAO();
		Footer video = videoDAO.selectFooterByPrimaryKey(id);
		if (video != null) {
			this.objectId = id;
			this.htmlContent = video.getHtmlcontent();
		} 
		// reseteo los paises
		resetSelectedCountries();
	}
	
	public static Country getCountryForFooterId(Integer sectionId) {
		for (Country c : getAllCountries()) {
			if (c.getId().equals(sectionId)) {
				return c;
			}
		}
		return null;
	}
	
	public static Country getCountryWithId(Integer idCountry) {
		for (Country c : getAllCountries()) {
			if (c.getId().equals(idCountry)) {
				return c;
			}
		}
		return null;
	}
	
	private void setCountrySelected(RankingNoteCountry ranking) {
		for (CountrySelectionVO countrySelectionVO : this.getSelectedCountries()) {
			if (countrySelectionVO.getCountryId().equals(ranking.getIdCountry())) {
				if (ranking.getDeleted() == 0) {
					countrySelectionVO.setSelected(true);
				} else {
					countrySelectionVO.setSelected(false);
				}
				countrySelectionVO.setOwnerId(ranking.getId());
			}
		}
		
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getHtmlContent(), htmlContent_key, ValidationErrors.TEXT_LENGTH, validationError);
		FieldValidation.validateId(this.getCountryId(), country_key, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
			
	}

	@Override
	public void save() throws SQLException, ValidationException {
		FooterDAO videoDAO = DAOManager.getFooterDAO();
		if (this.getObjectId() == 0) {
			Footer video = new Footer();
			updateFooter(video);
			video.setDeleted(0);
			videoDAO.insertFooter(video);
		} else {
			Footer video = new Footer();
			video.setId(this.getObjectId());
			updateFooter(video);
			videoDAO.updateFooterByPrimaryKeySelective(video);
		}
		
	}

	private void updateFooter(Footer video) {
		video.setIdCountry(this.getCountryId());
		video.setHtmlcontent(this.getHtmlContent());
	}
	
	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}

	public List<CountrySelectionVO> getSelectedCountries() {
		return selectedCountries;
	}

	public void setSelectedCountries(List<CountrySelectionVO> selectedCountries) {
		this.selectedCountries = selectedCountries;
	}
	
	public CountrySelectionVO getSelectedCountry(int index) {
		if (this.selectedCountries.size() > index) {
			return (CountrySelectionVO)this.selectedCountries.get(index); 
		} else {
			return null;
		}
	  }  
	   
	public void setSelectedCountry(int index, CountrySelectionVO countryVO) {  
		this.selectedCountries.set(index, countryVO);  
	 }  

	// TODO Copia
	public static synchronized List<Country> getAllCountries() {
		return allCountries;
	}

	public static synchronized void setAllCountries(List<Country> newCountries) {
		allCountries = newCountries;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public List<Footer> getAllFooters() {
		return allFooters;
	}

	public void setAllFooters(List<Footer> allRankings) {
		this.allFooters = allRankings;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public void restoreInitial() {
		InputStream io = null;
		ByteArrayOutputStream out = null;
		try {
			io = FooterForm.class.getResourceAsStream("original_footer.html");
			out = new ByteArrayOutputStream();
			IOUtils.copy(io,out);
			this.setHtmlContent(out.toString());
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
		} finally {
			if (io != null) {
				try {
					io.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(FooterForm.class);
	}
}
