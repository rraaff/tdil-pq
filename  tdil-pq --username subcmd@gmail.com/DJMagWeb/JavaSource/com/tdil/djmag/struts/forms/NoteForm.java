package com.tdil.djmag.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.tdil.djmag.dao.NoteCountryDAO;
import com.tdil.djmag.dao.NoteDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.Note;
import com.tdil.djmag.model.NoteCountry;
import com.tdil.djmag.model.NoteCountryExample;
import com.tdil.djmag.model.NoteExample;
import com.tdil.djmag.model.Section;
import com.tdil.djmag.model.SectionExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.struts.forms.UploadData;
import com.tdil.validations.FieldValidation;

public class NoteForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	private String title;
	private int sectionId;
	private boolean deleted;
	
	// TODO hacer esta parte
	private int noteImageIndex = 0;
	private FormFile noteImage;
	private List<NoteImageBean> images = new ArrayList<NoteImageBean>();
	
	private List<Note> allNotes;
	private List<SectionSelectionVO> allSections = new ArrayList<SectionSelectionVO>();
	private List<CountrySelectionVO> selectedCountries = new ArrayList<CountrySelectionVO>();
	private static List<Country> allCountries = new ArrayList<Country>();
	
	private static List<NoteCountry> allNoteCountries = new ArrayList<NoteCountry>();
	
	private static String title_key = "Note.title";
	private static String image_key = "Note.image";
	

	@Override
	public void reset() throws SQLException {
		this.id = 0;
		this.title = null;
		this.deleted = false;
		this.resetSelectedSections();
		this.resetSelectedCountries();
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.deleted = false;
		clearSelectedCountries();
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
		sectionExample.setOrderByClause("name");
		this.setAllSections(wrapSections(DAOManager.getSectionDAO().selectSectionByExample(sectionExample)));
		
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
			this.id = id;
			this.sectionId = ranking.getIdSection();
			this.title = ranking.getTitle();
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
		criteria.andIdNoteEqualTo(this.getId());
		List<NoteCountry> noteCountries = noteCountryDAO.selectNoteCountryByExample(noteExample);
		for (NoteCountry noteCountry : noteCountries) {
			this.setCountrySelected(noteCountry);
		}
	}
	
	public static List<Country> getAllCountriesForNoteId(Integer sectionId) {
		List<Country> result = new ArrayList<Country>();
		for (NoteCountry mi : getAllNoteCountries()) {
			if (mi.getIdCountry().equals(sectionId)) {
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
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// validacion por unico, por unico ranking por seccion por pais
	}

	@Override
	public void save() throws SQLException, ValidationException {
		NoteDAO noteDAO = DAOManager.getNoteDAO();
		NoteCountryDAO noteCountryDAO = DAOManager.getNoteCountryDAO();
		Integer noteId;
		if (this.getId() == 0) {
			Note note = new Note();
			note.setIdSection(this.getSectionId());
			note.setDeleted(0);
			updateNote(note);
			noteId = noteDAO.insertNote(note);
		} else {
			Note note = new Note();
			note.setId(this.getId());
			note.setIdSection(this.getSectionId());
			updateNote(note);
			noteDAO.updateNoteByPrimaryKeySelective(note);
			noteId = this.getId();
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
	}
	
	private boolean mustBeSaved(CountrySelectionVO countrySelectionVO) {
		if(countrySelectionVO.getOwnerId() != null && countrySelectionVO.getOwnerId() != 0) {
			return true;
		}
		return countrySelectionVO.isSelected();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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


}
