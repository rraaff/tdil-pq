package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.tdil.djmag.dao.NoteCountryDAO;
import com.tdil.djmag.dao.NoteDAO;
import com.tdil.djmag.dao.NoteImageDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.Note;
import com.tdil.djmag.model.NoteCountry;
import com.tdil.djmag.model.NoteCountryExample;
import com.tdil.djmag.model.NoteExample;
import com.tdil.djmag.model.NoteImage;
import com.tdil.djmag.model.NoteImageExample;
import com.tdil.djmag.model.RankingNote;
import com.tdil.djmag.model.RankingNoteExample;
import com.tdil.djmag.model.SectionType;
import com.tdil.djmag.model.NoteImageExample.Criteria;
import com.tdil.djmag.model.Section;
import com.tdil.djmag.model.SectionExample;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.validations.FieldValidation;
import com.tdil.validations.ValidationErrors;

public class NoteForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	// temporary
	private int id;
	
	private int objectId;
	private String title;
	private String webTitle;
	private String summary;
	private String content;
	private String fromDate;
	private String toDate;
	private boolean frontCover;
	private boolean popular;
	private boolean showInAgenda;
	private String agendaDate;
	
	private int sectionId;
	private boolean deleted;
	
	private int noteImageIndex = 0;
	private FormFile noteImage;
	private List<NoteImageBean> images = new ArrayList<NoteImageBean>();
	
	private List<Note> allNotes;
	private List<SectionSelectionVO> allSections = new ArrayList<SectionSelectionVO>();
	private List<CountrySelectionVO> selectedCountries = new ArrayList<CountrySelectionVO>();
	private static List<Section> allSections2 = new ArrayList<Section>();
	private static List<Country> allCountries = new ArrayList<Country>();
	
	private static List<NoteCountry> allNoteCountries = new ArrayList<NoteCountry>();
	
	private static String title_key = "Note.title";
	private static String webtitle_key = "Note.webtitle";
	private static String summary_key = "Note.summary";
	private static String content_key = "Note.content";
	private static String fromdate_key = "Note.fromdate";
	private static String todate_key = "Note.todate";
	private static String agendadate_key = "Note.agendadate";
	private static String image_key = "Note.image";
	private static String section_key = "Note.section";
	
	private static final int MAX_NOTE_IMAGE_SIZE = 1000000;
	

	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.title = "";
		this.webTitle = "";
		this.summary = "";
		this.content = "";
		this.fromDate = "";
		this.toDate = "";
		this.frontCover = false;
		this.popular = false;
		this.deleted = false;
		this.showInAgenda = false;
		this.agendaDate = "";
		this.resetSelectedSections();
		this.resetSelectedCountries();
		this.setImages(new ArrayList<NoteImageBean>());
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.deleted = false;
		this.frontCover = false;
		this.popular = false;
		this.showInAgenda = false;
		clearSelectedCountries();
	}
	
	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
		NoteExample rankingExample = new NoteExample();
		rankingExample.setOrderByClause("title");
		this.setAllNotes(DAOManager.getNoteDAO().selectNoteByExampleWithoutBLOBs(rankingExample));
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		NoteExample example = new NoteExample();
		example.createCriteria().andIdEqualTo(this.getObjectId());
		Note note = DAOManager.getNoteDAO().selectNoteByExampleWithoutBLOBs(example).get(0);
		note.setDeleted(note.getDeleted().equals(1) ? 0 : 1);
		DAOManager.getNoteDAO().updateNoteByExampleWithoutBLOBs(note, example);
	}

	private void clearSelectedCountries() {
		for (CountrySelectionVO countrySelectionVO : getSelectedCountries()) {
			countrySelectionVO.setSelected(false);
		}
	}

	@Override
	public void init() throws SQLException {
		NoteExample rankingExample = new NoteExample();
		rankingExample.setOrderByClause("title");
		this.setAllNotes(DAOManager.getNoteDAO().selectNoteByExampleWithoutBLOBs(rankingExample));
		
		SectionExample sectionExample = new SectionExample();
		sectionExample.createCriteria().andSectiontypeEqualTo(SectionType.NORMAL);
		sectionExample.setOrderByClause("name");
		allSections2 = DAOManager.getSectionDAO().selectSectionByExample(sectionExample);
		this.setAllSections(wrapSections(allSections2));
		
		CountryExample countryExample = new CountryExample();
		countryExample.setOrderByClause("name");
		setAllCountries(DAOManager.getCountryDAO().selectCountryByExample(countryExample));
		
		NoteCountryExample noteCountryExample = new NoteCountryExample();
		setAllNoteCountries(DAOManager.getNoteCountryDAO().selectNoteCountryByExample(noteCountryExample));
		
		this.resetSelectedSections();
		this.resetSelectedCountries();
	}
	
	private List<SectionSelectionVO> wrapSections(List<Section> selectSectionByExample) {
		List<SectionSelectionVO> result = new ArrayList<SectionSelectionVO>();
		for (Section section : selectSectionByExample) {
			result.add(new SectionSelectionVO(section));
		}
		return result;
	}

	private void resetSelectedCountries() {
		this.getSelectedCountries().clear();
		for (Country country : getAllCountries()) {
			this.getSelectedCountries().add(new CountrySelectionVO(country));
		}
	}
	
	private void resetSelectedSections() {
		for (SectionSelectionVO so : getAllSections()) {
			so.setSelected(false);
		}
		
	}

	@Override
	public void initWith(int id) throws SQLException {
		NoteDAO rankingDAO = DAOManager.getNoteDAO();
		Note ranking = rankingDAO.selectNoteByPrimaryKey(id);
		if (ranking != null) {
			this.objectId = id;
			this.sectionId = ranking.getIdSection();
			this.title = ranking.getTitle();
			this.webTitle = ranking.getWebTitle();
			this.summary = ranking.getSummary();
			this.content = ranking.getContent();
			this.fromDate = formatDate(ranking.getFromDate());
			this.toDate = formatDate(ranking.getToDate());
			this.frontCover = ranking.getFrontcover().equals(1);
			this.popular = ranking.getPopular().equals(1);
			this.showInAgenda = ranking.getShowinagenda().equals(1);
			this.agendaDate = formatDate(ranking.getAgendaDate());
			this.deleted = ranking.getDeleted() == 1;
		} 
		// reseto las secciones
		this.resetSelectedSections();
		for (SectionSelectionVO sectionSelectionVO : getAllSections()) {
			if (sectionSelectionVO.getSection().getId().equals(this.getSectionId())) {
				sectionSelectionVO.setSelected(true);
			}
		}
		// reseteo los paises
		resetSelectedCountries();
		// seteto los que habia elegido
		NoteCountryDAO noteCountryDAO = DAOManager.getNoteCountryDAO();
		NoteCountryExample noteExample = new NoteCountryExample();
		com.tdil.djmag.model.NoteCountryExample.Criteria criteria = noteExample.createCriteria();
		criteria.andIdNoteEqualTo(this.getObjectId());
		List<NoteCountry> noteCountries = noteCountryDAO.selectNoteCountryByExample(noteExample);
		for (NoteCountry noteCountry : noteCountries) {
			this.setCountrySelected(noteCountry);
		}
		// seteo las imagenes
		this.getImages().clear();
		NoteImageExample noteImageExample = new NoteImageExample();
		Criteria criteria2 = noteImageExample.createCriteria();
		criteria2.andIdNoteEqualTo(this.getObjectId());
		noteExample.setOrderByClause("orderNumber");
		List<NoteImage> noteImages = DAOManager.getNoteImageDAO().selectNoteImageByExampleWithBLOBs(noteImageExample);
		for (NoteImage noteImage : noteImages) {
			this.getImages().add(new NoteImageBean(noteImage, noteImageIndex++));
		}
	}
	
	private String formatDate(Date fromDate2) {
		if (fromDate2 == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(fromDate2);
	}
	
	private Date parseDate(String fromDate2) {
		try {
			if (StringUtils.isEmpty(fromDate2)) {
				return null;
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.parse(fromDate2);
		} catch (ParseException e) {
			getLog().error(e.getMessage(), e);
			//throw new RuntimeException(e);
			return null;
		}
	}

	public static List<Country> getAllCountriesForNoteId(Integer sectionId) {
		List<Country> result = new ArrayList<Country>();
		for (NoteCountry mi : getAllNoteCountries()) {
			if (mi.getIdNote().equals(sectionId)) {
				result.add(getCountryWithId(mi.getIdCountry()));
			}
		}
		Collections.sort(result, new Comparator<Country>() {
			public int compare(Country arg0, Country arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
		return result;
	}
	
	public static Section getSectionWithId(Integer idSection) {
		for (Section s : allSections2) {
			if (s.getId().equals(idSection)) {
				return s;
			}
		}
		return null;
	}
	
	private static Country getCountryWithId(Integer idCountry) {
		for (Country c : getAllCountries()) {
			if (c.getId().equals(idCountry)) {
				return c;
			}
		}
		return null;
	}
	
	private void setCountrySelected(NoteCountry ranking) {
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
		FieldValidation.validateText(this.getTitle(), title_key, 250, validationError);
		if (StringUtils.isEmpty(this.getWebTitle()) && !validationError.hasError()) {
			this.setWebTitle(com.tdil.utils.StringUtils.urlValid(this.getTitle()));
		} else {
			this.setWebTitle(com.tdil.utils.StringUtils.urlValid(this.getWebTitle()));
		}
		FieldValidation.validateText(this.getWebTitle(), webtitle_key, 250, false, validationError);
		FieldValidation.validateText(this.getSummary(), summary_key, 4000, false, validationError);
		FieldValidation.validateText(this.getContent(), content_key, 16000000, false, validationError);
		FieldValidation.validateId(this.getSectionId(), section_key, validationError);
		Date fromDate = parseDate(this.getFromDate());
		if (fromDate == null) {
			validationError.setFieldError(fromdate_key, ValidationErrors.CANNOT_BE_EMPTY);
		} else {
			Date toDate = parseDate(this.getToDate());
			if (toDate != null && toDate.before(fromDate)) {
				validationError.setFieldError(fromdate_key, "INVALID_ORDER");
			}
		}
		if (this.isShowInAgenda()) {
			Date agendaDate = parseDate(this.getAgendaDate());
			if (agendaDate == null) {
				validationError.setFieldError(agendadate_key, ValidationErrors.CANNOT_BE_EMPTY);
			}
		}
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// validacion por unico, por unico ranking por seccion por pais
	}

	@Override
	public void save() throws SQLException, ValidationException {
		NoteDAO noteDAO = DAOManager.getNoteDAO();
		NoteCountryDAO noteCountryDAO = DAOManager.getNoteCountryDAO();
		NoteImageDAO noteImageDAO = DAOManager.getNoteImageDAO();
		Integer noteId;
		if (this.getObjectId() == 0) {
			Note note = new Note();
			note.setIdSection(this.getSectionId());
			updateNote(note);
			noteId = noteDAO.insertNote(note);
		} else {
			Note note = new Note();
			note.setId(this.getObjectId());
			note.setIdSection(this.getSectionId());
			updateNote(note);
			noteDAO.updateNoteByPrimaryKeySelective(note);
			noteId = this.getObjectId();
		}
		// borro todas las imagenes anteriores
		NoteImageExample noteImageExample = new NoteImageExample();
		Criteria noCriteria = noteImageExample.createCriteria();
		noCriteria.andIdNoteEqualTo(noteId);
		noteImageDAO.deleteNoteImageByExample(noteImageExample);
		// Creo todas las imagenes
		int index = 0;
		for (NoteImageBean noteImageBean : this.getImages()) {
			NoteImage noteImage = new NoteImage();
			noteImage.setIdNote(noteId);
			noteImage.setFilename(noteImageBean.getUploadData().getFileName());
			noteImage.setNoteimage(noteImageBean.getUploadData().getData());
			noteImage.setOrdernumber(index++);
			noteImage.setDeleted(0);
			noteImageDAO.insertNoteImage(noteImage);
		}
		
		for (CountrySelectionVO countrySelectionVO : getSelectedCountries()) {
			if (this.mustBeSaved(countrySelectionVO)) {
				NoteCountry noteCountry = new NoteCountry();
				noteCountry.setId(countrySelectionVO.getOwnerId());
				noteCountry.setDeleted(countrySelectionVO.isSelected() ? 0 : 1);
				noteCountry.setIdCountry(countrySelectionVO.getCountryId());
				noteCountry.setIdNote(noteId);
				// TODO arreglar
				if(countrySelectionVO.getOwnerId() != null && countrySelectionVO.getOwnerId() != 0) {
					noteCountryDAO.updateNoteCountryByPrimaryKeySelective(noteCountry);
				} else {
					noteCountryDAO.insertNoteCountry(noteCountry);
				}
			}
		}
		
	}

	private void updateNote(Note note) {
		note.setTitle(this.getTitle());
		note.setWebTitle(this.getWebTitle()); // TODO URL escape...
		note.setSummary(this.getSummary());
		note.setContent(this.getContent());
		note.setFromDate(parseDate(this.getFromDate()));
		note.setToDate(parseDate(this.getToDate()));
		note.setFrontcover(this.isFrontCover() ? 1 : 0);
		note.setPopular(this.isPopular() ? 1 : 0);
		note.setShowinagenda(this.isShowInAgenda() ? 1 : 0);
		note.setAgendaDate(parseDate(this.getAgendaDate()));
		note.setDeleted(this.isDeleted() ? 1 : 0);
	}
	
	private boolean mustBeSaved(CountrySelectionVO countrySelectionVO) {
		if(countrySelectionVO.getOwnerId() != null && countrySelectionVO.getOwnerId() != 0) {
			return true;
		}
		return countrySelectionVO.isSelected();
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

	public List<SectionSelectionVO> getAllSections() {
		return allSections;
	}

	public void setAllSections(List<SectionSelectionVO> allSections) {
		this.allSections = allSections;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String description) {
		this.title = description;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public List<Note> getAllNotes() {
		return allNotes;
	}

	public void setAllNotes(List<Note> allNotes) {
		this.allNotes= allNotes;
	}

	public static List<NoteCountry> getAllNoteCountries() {
		return allNoteCountries;
	}

	public static void setAllNoteCountries(List<NoteCountry> allNoteCountries) {
		NoteForm.allNoteCountries = allNoteCountries;
	}

	public List<NoteImageBean> getImages() {
		return images;
	}

	public void setImages(List<NoteImageBean> images) {
		this.images = images;
	}

	public FormFile getNoteImage() {
		return noteImage;
	}

	public void setNoteImage(FormFile noteImage) {
		this.noteImage = noteImage;
	}
	
	public void uploadImage(ValidationError error) {
		UploadData uploadData = FieldValidation.validateFormFile(this.getNoteImage(), image_key, true, error);
		if (uploadData != null) {
			int fileSize = this.getNoteImage().getFileSize();
			if (fileSize > MAX_NOTE_IMAGE_SIZE) {
				error.setFieldError(image_key, ValidationErrors.TOO_BIG);
				return;
			}
			this.getImages().add(new NoteImageBean(uploadData, noteImageIndex++));			
		}
	}

	public NoteImageBean getNoteImage(int id2) {
		for (NoteImageBean noteImageBean : getImages()) {
			if (noteImageBean.getId() == id2) {
				return noteImageBean;
			}
		}
		return null;
	}

	public void moveImageUp(int id2) {
		NoteImageBean noteImageBean = getNoteImage(id2);
		int index = this.getImages().indexOf(noteImageBean);
		if (index > 0) {
			NoteImageBean toSwap = this.getImages().get(index - 1);
			this.getImages().set(index - 1, noteImageBean);
			this.getImages().set(index, toSwap);
		}
	}
	
	public void moveImageDown(int id2) {
		NoteImageBean noteImageBean = getNoteImage(id2);
		int index = this.getImages().indexOf(noteImageBean);
		if (index < this.getImages().size() - 1) {
			NoteImageBean toSwap = this.getImages().get(index + 1);
			this.getImages().set(index + 1, noteImageBean);
			this.getImages().set(index, toSwap);
		}
	}
	
	public void deleteImage(int id2) {
		NoteImageBean noteImageBean = getNoteImage(id2);
		this.getImages().remove(noteImageBean);
	}

	public String getWebTitle() {
		return webTitle;
	}

	public void setWebTitle(String webTitle) {
		this.webTitle = webTitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public boolean isFrontCover() {
		return frontCover;
	}

	public void setFrontCover(boolean frontCover) {
		this.frontCover = frontCover;
	}

	public boolean isPopular() {
		return popular;
	}

	public void setPopular(boolean leadingNote) {
		this.popular = leadingNote;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isShowInAgenda() {
		return showInAgenda;
	}

	public void setShowInAgenda(boolean showInAgenda) {
		this.showInAgenda = showInAgenda;
	}

	public String getAgendaDate() {
		return agendaDate;
	}

	public void setAgendaDate(String agendaDate) {
		this.agendaDate = agendaDate;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(NoteForm.class);
	}
}
