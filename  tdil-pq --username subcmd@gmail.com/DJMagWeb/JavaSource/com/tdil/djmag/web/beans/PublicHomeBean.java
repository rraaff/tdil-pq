package com.tdil.djmag.web.beans;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.djmag.dao.CountryDAO;
import com.tdil.djmag.dao.NoteDAO;
import com.tdil.djmag.dao.RankingNoteDAO;
import com.tdil.djmag.dao.SectionDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.Note;
import com.tdil.djmag.model.RankingNote;
import com.tdil.djmag.model.RankingPositions;
import com.tdil.djmag.model.Section;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.utils.XMLUtils;


public class PublicHomeBean  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7859928560990002269L;
	private Country country;
	private List<Section> sectionsForCountry;
	
	private RankingNote ranking;
	private RankingPositions rankingPositions;
	
	private List<Note> frontCoverNotes;
	
	private List<Note> agendaNotes;
	
	private List<Country> allCountries;
	
	public static final String PUBLIC_HOME_BEAN = "publicHomeBean";
	
	private static final int REDUCED_RANKING_SIZE = 10;
	private static final int MAX_AGENDA_NOTES_FOR_HOME = 5;
	
	public boolean hasCountrySelected() {
		return this.getCountry() != null;
	}
	
	public boolean hasRanking() {
		return this.getRanking() != null;
	}
	
	public boolean hasFrontCovers() {
		return this.getFrontCoverNotes() != null && !this.getFrontCoverNotes().isEmpty();
	}
	
	public boolean hasAgenda() {
		return this.getAgendaNotes() != null && !this.getAgendaNotes().isEmpty();
	}
	
	public void initCountries() {
		if (allCountries == null || allCountries.isEmpty()) {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						CountryDAO countryDAO = DAOManager.getCountryDAO();
						CountryExample countryExample = new CountryExample();
						countryExample.createCriteria().andDeletedEqualTo(0);
						countryExample.setOrderByClause("name");
						setAllCountries(countryDAO.selectCountryByExample(countryExample));
					}
				});
			} catch (Exception e) {
				getLog().error(e.getMessage(), e);
			}
		}
	}
	
	private void initHomeForCountry(final Country country) {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					SectionDAO sectionDAO = DAOManager.getSectionDAO();
					setSectionsForCountry(sectionDAO.selectActiveSectionsForCountry(country));
					
					RankingNoteDAO rankingNoteDAO = DAOManager.getRankingNoteDAO();
					List<RankingNote> rankingForCountry = rankingNoteDAO.selectActiveRankingForCountry(country);
					if (!rankingForCountry.isEmpty()) {
						setRanking(rankingForCountry.get(0));
						setRankingPositions((RankingPositions)XMLUtils.fromXML(getRanking().getPositions()));
					}
					
					NoteDAO noteDAO = DAOManager.getNoteDAO();
					setFrontCoverNotes(noteDAO.selectActiveFrontCoversNotesForCountry(country));
					
					setAgendaNotes(noteDAO.selectActiveAgendaNotesForCountry(country));
				}
			});
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	public List<String> getReducedRanking() {
		return this.getRankingPositions().getPositions().subList(0, REDUCED_RANKING_SIZE);
	}
	
	public List<Note> getReducedAgenda() {
		List<Note> result = new ArrayList<Note>();
		int size = 0;
		Iterator<Note> agendaIterator = this.getAgendaNotes().iterator();
		while (size < MAX_AGENDA_NOTES_FOR_HOME && agendaIterator.hasNext()) {
			result.add(agendaIterator.next());
			size = size + 1;
		}
		return result;
	}
	
	public String formatAgendaDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return simpleDateFormat.format(date);
	}
	
	public List<Country> getAllCountries() {
		return allCountries;
	}
	public void setAllCountries(List<Country> allCountries) {
		this.allCountries = allCountries;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(PublicHomeBean.class);
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public void setCountryById(int idcountry) {
		this.initCountries();
		for (Country c : getAllCountries()) {
			if (c.getId() == idcountry) {
				setCountry(c);
				initHomeForCountry(c);
				return;
			}
		}
	}

	public List<Section> getSectionsForCountry() {
		return sectionsForCountry;
	}

	public void setSectionsForCountry(List<Section> sectionsForCountry) {
		this.sectionsForCountry = sectionsForCountry;
	}

	public RankingNote getRanking() {
		return ranking;
	}

	public void setRanking(RankingNote ranking) {
		this.ranking = ranking;
	}

	public RankingPositions getRankingPositions() {
		return rankingPositions;
	}

	public void setRankingPositions(RankingPositions rankingPositions) {
		this.rankingPositions = rankingPositions;
	}

	public List<Note> getFrontCoverNotes() {
		return frontCoverNotes;
	}

	public void setFrontCoverNotes(List<Note> frontCoverNotes) {
		this.frontCoverNotes = frontCoverNotes;
	}

	public List<Note> getAgendaNotes() {
		return agendaNotes;
	}

	public void setAgendaNotes(List<Note> agendaNotes) {
		this.agendaNotes = agendaNotes;
	}
}
