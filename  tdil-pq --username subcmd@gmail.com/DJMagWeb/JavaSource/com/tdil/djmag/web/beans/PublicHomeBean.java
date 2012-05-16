package com.tdil.djmag.web.beans;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.djmag.dao.CountryDAO;
import com.tdil.djmag.dao.FooterDAO;
import com.tdil.djmag.dao.MagazineDAO;
import com.tdil.djmag.dao.NoteDAO;
import com.tdil.djmag.dao.RankingNoteDAO;
import com.tdil.djmag.dao.SectionDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.BannerInsertPoints;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.CountryExample;
import com.tdil.djmag.model.FacebookFeed;
import com.tdil.djmag.model.FacebookFeedExample;
import com.tdil.djmag.model.Footer;
import com.tdil.djmag.model.FooterExample;
import com.tdil.djmag.model.Magazine;
import com.tdil.djmag.model.MagazineExample;
import com.tdil.djmag.model.MenuItem;
import com.tdil.djmag.model.Note;
import com.tdil.djmag.model.NoteImage;
import com.tdil.djmag.model.NoteImageExample;
import com.tdil.djmag.model.RankingNote;
import com.tdil.djmag.model.RankingPosition;
import com.tdil.djmag.model.RankingPositions;
import com.tdil.djmag.model.Section;
import com.tdil.djmag.model.SectionType;
import com.tdil.djmag.model.TwitterFeed;
import com.tdil.djmag.model.TwitterFeedExample;
import com.tdil.djmag.model.Video;
import com.tdil.djmag.model.VideoExample;
import com.tdil.djmag.model.valueobjects.BannerValueObject;
import com.tdil.djmag.model.valueobjects.NoteValueObject;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.utils.DateUtils;
import com.tdil.utils.XMLUtils;


public class PublicHomeBean  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7859928560990002269L;
	
	private boolean initialized = false;
	private Country country;
	private List<Section> sectionsForCountry;
	
	private Section rankingSection;
	private Section videoSection;
	
	private RankingNote ranking;
	private RankingPositions rankingPositions;
	
	private Magazine magazine;
	
	private Footer footer;
	private Footer footerNote;
	private Footer footerRanking;
	
	private TwitterFeed twitterFeed;
	private FacebookFeed facebookFeed;
	
	private List<NoteValueObject> frontCoverNotes;
	private NoteValueObject lastNoteFirst;
	private NoteValueObject lastNoteSecond;
	private List<NoteValueObject> lastNotes;
	private List<NoteValueObject> agendaNotes;
	
	private List<NoteValueObject> allNotes;
	
	private Map<Section, List<NoteValueObject>> sectionsNotes;
	
	private BannerValueObject homeTop;
	private BannerValueObject homeRight;
	private BannerValueObject noteTop;
	private BannerValueObject noteRight;
	
	private List<Video> lastVideos;
	
	private List<Country> allCountries;
	
	public static final String PUBLIC_HOME_BEAN = "publicHomeBean";
	private static final int REDUCED_RANKING_SIZE = 10;
	private static final int MAX_AGENDA_NOTES_FOR_HOME = 4;
	private static final int MAX_LAST_NOTES_FOR_HOME = 3;
	private static final int MAX_VIDEOS_FOR_HOME = 3;
	private static final int MAX_NOTES_FOR_FOOTER = 7;

	public static final int SECTION_PAGE_SIZE = 5;
	public static final int SECTIONS_PAGES_SIDE = 5;
	public static final int MAX_SECTIONS_PAGES = 5;
	
	public static final int VIDEOS_PAGE_SIZE = 5;
	public static final int VIDEOS_PAGES_SIDE = 2;
	public static final int VIDEOS_MAX_SECTIONS_PAGES = 5;
	
	// 0 si no viene, sino lo que vino
	public static int parsePageParam(String pString) {
		if (StringUtils.isEmpty(pString)) {
			return 0;
		} else {
			if (StringUtils.isNumeric(pString)) {
				try {
					return Integer.parseInt(pString);
				} catch (NumberFormatException e) {
					return 0;
				}
			} else {
				return 0;
			}
		}
	}
	
	public boolean hasCountrySelected() {
		return this.getCountry() != null;
	}
	
	public boolean hasRanking() {
		return this.getRanking() != null;
	}
	
	public boolean hasMagazine() {
		return this.getMagazine() != null;
	}
	
	public boolean hasFooter() {
		return this.getFooter() != null;
	}
	
	public boolean hasFrontCovers() {
		return this.getFrontCoverNotes() != null && !this.getFrontCoverNotes().isEmpty();
	}
	
	public boolean hasLastNoteFirst() {
		return this.getLastNoteFirst() != null;
	}
	
	public boolean hasLastNoteSecond() {
		return this.getLastNoteSecond() != null;
	}
	
	public boolean hasLastNotes() {
		return this.getLastNotes() != null && this.getLastNotes().size() > 2;
	}
	
	public boolean hasAgenda() {
		return this.getAgendaNotes() != null && !this.getAgendaNotes().isEmpty();
	}
	
	public boolean hasTwitterFeed() {
		return this.getTwitterFeed() != null;
	}
	
	public boolean hasFacebookFeed() {
		return this.getFacebookFeed() != null;
	}
	
	public boolean hasVideos() {
		return this.getLastVideos() != null && !this.getLastVideos().isEmpty();
	}
	
	public boolean hasHomeTopBanner() {
		return this.getHomeTop() != null;
	}
	
	public boolean hasHomeRightBanner() {
		return this.getHomeRight() != null;
	}
	
	public boolean hasNoteTopBanner() {
		return this.getNoteTop() != null;
	}
	
	public boolean hasNoteRightBanner() {
		return this.getNoteRight() != null;
	}
	
	public Section getSectionForId(String sectionId) {
		if (StringUtils.isEmpty(sectionId)) {
			return null;
		}
		if (!StringUtils.isNumeric(sectionId)) {
			return null;
		}
		int id = Integer.parseInt(sectionId);
		for (Section s : getSectionsForCountry()) {
			if (s.getId().equals(id)) {
				return s;
			}
		}
		return null;
	}
	
	public String getExternalLink(String date, String webTitle) {
		NoteValueObject noteValueObject = getNoteByParams(this.getCountry().getIsoCode2(), date, webTitle);
		StringBuffer result = new StringBuffer();
		result.append("./notes/").append(this.getCountry().getIsoCode2()).append("/");
		result.append(formatDateForUrl(noteValueObject.getFromDate())).append("/");
		result.append(noteValueObject.getWebTitle()).append(".html");
		return result.toString();
	}
	
	public String getExternalLink(Section section) {
		StringBuffer result = new StringBuffer();
		result.append("./notes/").append(this.getCountry().getIsoCode2()).append("/");
		result.append(section.getId()).append("/");
		result.append(section.getName()).append(".html");
		return result.toString();
	}
	
	public String getExternalLink(NoteValueObject noteValueObject) {
		StringBuffer result = new StringBuffer();
		result.append("./notes/").append(this.getCountry().getIsoCode2()).append("/");
		result.append(formatDateForUrl(noteValueObject.getFromDate())).append("/");
		result.append(noteValueObject.getWebTitle()).append(".html");
		return result.toString();
	}
	
	public NoteValueObject getNoteByParams(final String isoCode2, final String datePart, final String webTitle) {
		NoteValueObject result = null;
		int countryId = 0;
		Iterator<Country> countryIterator = getAllCountries().iterator();
		while (countryId == 0 && countryIterator.hasNext()) {
			Country country = countryIterator.next();
			if (country.getIsoCode2().equals(isoCode2)) {
				countryId = country.getId();
			}
		}
		final Integer countryIdFound = countryId;
		Iterator<NoteValueObject> noteIterator = getAllNotes().iterator();
		while(result == null && noteIterator.hasNext()) {
			NoteValueObject iter = noteIterator.next();
			if (iter.getWebTitle().equals(webTitle)) {
				String date = formatDateForUrl(iter.getFromDate());
				if (date.equals(datePart)) {
					result = iter;
				}
			}
		}
		if (result == null) {
			final Date date = parseDateForUrl(datePart);
			try {
				List<NoteValueObject> list = (List<NoteValueObject>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
					public Object executeInTransaction() throws SQLException {
						return DAOManager.getNoteDAO().selectNoteByParams(countryIdFound, webTitle, date);
					}
				});
				if (list.size() > 0) {
					result = list.get(0);
				}
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			}
		}
		return result;
	}
	
	private String getPopulasNotesReplacement() {
		StringBuffer result = new StringBuffer();
		int index = 0;
		Iterator<NoteValueObject> frontCoverIter = this.getFrontCoverNotes().iterator();
		while (frontCoverIter.hasNext() && index < MAX_NOTES_FOR_FOOTER) {
			NoteValueObject nvo = frontCoverIter.next();
			result.append("<a href=\"").append(this.getExternalLink(nvo)).append("\">").append(nvo.getTitle()).append("</a>\n");
			index = index + 1;
		}
		if (index < MAX_NOTES_FOR_FOOTER) {
			Iterator<NoteValueObject> lastNewsIter = this.getLastNotes().iterator();
			while (lastNewsIter.hasNext() && index < MAX_NOTES_FOR_FOOTER) {
				NoteValueObject nvo = lastNewsIter.next();
				result.append("<a href=\"").append(this.getExternalLink(nvo)).append("\">").append(nvo.getTitle()).append("</a>\n");
				index = index + 1;
			}
		}
		return result.toString();
	}
	
	private String getPopulasNotesReplacementForNote() {
		StringBuffer result = new StringBuffer();
		int index = 0;
		Iterator<NoteValueObject> frontCoverIter = this.getFrontCoverNotes().iterator();
		while (frontCoverIter.hasNext() && index < MAX_NOTES_FOR_FOOTER) {
			NoteValueObject nvo = frontCoverIter.next();
			result.append("<a href=\"../../../").append(this.getExternalLink(nvo)).append("\">").append(nvo.getTitle()).append("</a>\n");
			index = index + 1;
		}
		if (index < MAX_NOTES_FOR_FOOTER) {
			Iterator<NoteValueObject> lastNewsIter = this.getLastNotes().iterator();
			while (lastNewsIter.hasNext() && index < MAX_NOTES_FOR_FOOTER) {
				NoteValueObject nvo = lastNewsIter.next();
				result.append("<a href=\"../../../").append(this.getExternalLink(nvo)).append("\">").append(nvo.getTitle()).append("</a>\n");
				index = index + 1;
			}
		}
		return result.toString();
	}
	
	private String getPopulasNotesReplacementForRanking() {
		StringBuffer result = new StringBuffer();
		int index = 0;
		Iterator<NoteValueObject> frontCoverIter = this.getFrontCoverNotes().iterator();
		while (frontCoverIter.hasNext() && index < MAX_NOTES_FOR_FOOTER) {
			NoteValueObject nvo = frontCoverIter.next();
			result.append("<a href=\"../../").append(this.getExternalLink(nvo)).append("\">").append(nvo.getTitle()).append("</a>\n");
			index = index + 1;
		}
		if (index < MAX_NOTES_FOR_FOOTER) {
			Iterator<NoteValueObject> lastNewsIter = this.getLastNotes().iterator();
			while (lastNewsIter.hasNext() && index < MAX_NOTES_FOR_FOOTER) {
				NoteValueObject nvo = lastNewsIter.next();
				result.append("<a href=\"../../").append(this.getExternalLink(nvo)).append("\">").append(nvo.getTitle()).append("</a>\n");
				index = index + 1;
			}
		}
		return result.toString();
	}
	
	private String getSectionsReplacementForNote() {
		StringBuffer result = new StringBuffer();
		for (Section section : getSectionsForCountry()) {
			if (SectionType.RANKING_100.equals(section.getSectiontype())) {
				result.append("<a href=\"../../../notes/").append(this.getCountry().getIsoCode2()).append("/viewRanking.html");
				result.append("\">").append(section.getName()).append("</a>\n");
			} else {
				if (SectionType.VIDEOS.equals(section.getSectiontype())) {
					result.append("<a href=\"../../../notes/").append(this.getCountry().getIsoCode2()).append("/viewVideos.html");
					result.append("\">").append(section.getName()).append("</a>\n");
				} else {
					result.append("<a href=\"../../../").append(this.getExternalLink(this.getFirstNoteForSection(section)));
					result.append("\">").append(section.getName()).append("</a>\n");
				}
			}
		}
		return result.toString();
	}
	
	private String getSectionsReplacementForRanking() {
		StringBuffer result = new StringBuffer();
		for (Section section : getSectionsForCountry()) {
			if (SectionType.RANKING_100.equals(section.getSectiontype())) {
				result.append("<a href=\"../../notes/").append(this.getCountry().getIsoCode2()).append("/viewRanking.html");
				result.append("\">").append(section.getName()).append("</a>\n");
			} else {
				if (SectionType.VIDEOS.equals(section.getSectiontype())) {
					result.append("<a href=\"../../notes/").append(this.getCountry().getIsoCode2()).append("/viewVideos.html");
					result.append("\">").append(section.getName()).append("</a>\n");
				} else {
					result.append("<a href=\"../../").append(this.getExternalLink(this.getFirstNoteForSection(section)));
					result.append("\">").append(section.getName()).append("</a>\n");
				}
			}
		}
		return result.toString();
	}
	
	private String getSectionsReplacement() {
		StringBuffer result = new StringBuffer();
		for (Section section : getSectionsForCountry()) {
			if (SectionType.RANKING_100.equals(section.getSectiontype())) {
				result.append("<a href=\"./notes/").append(this.getCountry().getIsoCode2()).append("/viewRanking.html");
				result.append("\">").append(section.getName()).append("</a>\n");
			} else {
				if (SectionType.VIDEOS.equals(section.getSectiontype())) {
					result.append("<a href=\"./notes/").append(this.getCountry().getIsoCode2()).append("/viewVideos.html");
					result.append("\">").append(section.getName()).append("</a>\n");
				} else {
					result.append("<a href=\"").append(this.getExternalLink(this.getFirstNoteForSection(section)));
					result.append("\">").append(section.getName()).append("</a>\n");
				}
			}
		}
		return result.toString();
	}
	
	@SuppressWarnings("unchecked")
	public List<Video> getAllVideosForCountry(int pageNumber, ArrayList<Integer> pages) {
		final Country country = this.getCountry();
		try {
			 List<Video> originalResult = (List<Video>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					VideoExample videoExample = new VideoExample();
					videoExample.createCriteria().andDeletedEqualTo(0).andIdCountryEqualTo(country.getId());
					videoExample.setOrderByClause("id desc");
					List<Video> result = DAOManager.getVideoDAO().selectVideoByExampleWithBLOBs(videoExample);
					return result;
				}
			});
			 List<Video> result;
			 int seekAt = pageNumber * VIDEOS_PAGE_SIZE;
			 if (originalResult.size() > seekAt) {
				 result = originalResult.subList(seekAt, originalResult.size());
			 } else {
				 result = new ArrayList<Video>();
			 }
			int min = pageNumber - VIDEOS_PAGES_SIDE;
			if (min < 0) {
				min = 0;
			}
			int sectionSize = originalResult.size();
			int pagesCount = 0;
			for (int i = min; (i * VIDEOS_PAGE_SIZE) < sectionSize && pagesCount < VIDEOS_MAX_SECTIONS_PAGES; i++) {
				pages.add(i);
				pagesCount = pagesCount + 1;
			}
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Video>();
		}
	}
	
	public static String getNoteContent(final NoteValueObject noteValueObject) {
		try {
			String content = (String)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					Note note = DAOManager.getNoteDAO().selectNoteByPrimaryKey(noteValueObject.getId());
					return note.getContent();
				}
			});
			return content;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return "";
		}
	}
	
	public NoteValueObject getFirstNoteForSection(Section section) {
		return this.getSectionsNotes().get(section).get(0);
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
	
	public void refreshHome() {
		initHomeForCountry(this.getCountry());
	}
	
	public void initHomeForCountry(final Country country) {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					PublicHomeBean.this.initialized = true;
					NoteDAO noteDAO = DAOManager.getNoteDAO();
					// cargo las secciones
					SectionDAO sectionDAO = DAOManager.getSectionDAO();
					setSectionsForCountry(sectionDAO.selectActiveSectionsForCountry(country));
					Map<Section, List<NoteValueObject>> sectionsGalleries = new HashMap<Section, List<NoteValueObject>>();
					List<Section> iterator = new ArrayList<Section>(getSectionsForCountry());
					for (Section section : iterator) {
						if (SectionType.NORMAL.equals(section.getSectiontype())) {
							MenuItem menuItem = new MenuItem();
							menuItem.setIdCountry(country.getId());
							menuItem.setIdSection(section.getId());
							List<NoteValueObject> notesForSection = noteDAO.selectActiveNotesForMenuItem(menuItem);
							if (notesForSection.isEmpty()) {
								getSectionsForCountry().remove(section);
							} else {
								sectionsGalleries.put(section, notesForSection);
							}
						} else {
							if (SectionType.RANKING_100.equals(section.getSectiontype())) {
								setRankingSection(section);
							} else {
								if (SectionType.VIDEOS.equals(section.getSectiontype())) {
									setVideoSection(section);
								}	
							}
						}
					}
					setSectionsNotes(sectionsGalleries);
					
					// cargo los banners
					List<BannerValueObject> banners = DAOManager.getBannerDAO().getActiveBannersForCountry(country);
					for (BannerValueObject ban : banners) {
						if (ban.getPosition().equals(BannerInsertPoints.HOME_TOP)) {
							setHomeTop(ban);
						}
						if (ban.getPosition().equals(BannerInsertPoints.HOME_RIGHT)) {
							setHomeRight(ban);
						}
						if (ban.getPosition().equals(BannerInsertPoints.NOTE_TOP)) {
							setNoteTop(ban);
						}
						if (ban.getPosition().equals(BannerInsertPoints.NOTE_RIGHT)) {
							setNoteRight(ban);
						}
					}
					
					// cargo el ranking
					RankingNoteDAO rankingNoteDAO = DAOManager.getRankingNoteDAO();
					List<RankingNote> rankingForCountry = rankingNoteDAO.selectActiveRankingForCountry(country);
					if (!rankingForCountry.isEmpty()) {
						setRanking(rankingForCountry.get(0));
						setRankingPositions((RankingPositions)XMLUtils.fromXML(getRanking().getPositions()));
					} else {
						setRanking(null);
						setRankingPositions(null);
					}
					// Cargo el magazine
					MagazineDAO magazineDAO = DAOManager.getMagazineDAO();
					MagazineExample magazineExample = new MagazineExample();
					magazineExample.setOrderByClause("publish_date");
					magazineExample.createCriteria().andDeletedEqualTo(0).andPublishDateGreaterThanOrEqualTo(DateUtils.date2FirstMomentOfMonth(new Date()));
					List<Magazine> listMagazine = magazineDAO.selectMagazineByExample(magazineExample);
					if (!listMagazine.isEmpty()) {
						setMagazine(listMagazine.get(0));
					} else {
						setMagazine(null);
					}
					
					// cargo las notas de tapa
					setFrontCoverNotes(noteDAO.selectActiveFrontCoversNotesForCountry(country));
					// cargo las ultimas notas de tapa
					setLastNotes(noteDAO.selectActiveLastNotesForCountry(country));
					if (getLastNotes().size() > 0) {
						setLastNoteFirst(getLastNotes().get(0));
					} else {
						setLastNoteFirst(null);
					}
					if (getLastNotes().size() > 1) {
						setLastNoteSecond(getLastNotes().get(1));
					} else {
						setLastNoteSecond(null);
					}
					// cargo la agenda
					setAgendaNotes(noteDAO.selectActiveAgendaNotesForCountry(country));
					
					// cargo las imagenes de cada nota, recolecto todas para armar un solo query, luego distribuyo
					List<NoteValueObject> allNotes = new ArrayList<NoteValueObject>();
					allNotes.addAll(getFrontCoverNotes());
					allNotes.addAll(getLastNotes());
					allNotes.addAll(getAgendaNotes());
					setAllNotes(allNotes);
					Set<Integer> notesIds = new HashSet<Integer>();
					for (NoteValueObject nvo : allNotes) {
						notesIds.add(nvo.getId());
					}
					if (!notesIds.isEmpty()) {
						List<Integer> param = new ArrayList<Integer>(notesIds);
						NoteImageExample noteImageExample = new NoteImageExample();
						noteImageExample.createCriteria().andIdNoteIn(param);
						noteImageExample.setOrderByClause("id_note, orderNumber");
						List<NoteImage> noteImages = DAOManager.getNoteImageDAO().selectNoteImageByExampleWithoutBLOBs(noteImageExample);
						for (NoteImage ni : noteImages) {
							for (NoteValueObject nvo : allNotes) {
								if (ni.getIdNote().equals(nvo.getId())) {
									nvo.addNoteImage(ni);
								}
							}
						}
					}
					
					// cargo el feed de twitter
					TwitterFeedExample twitterFeedExample = new TwitterFeedExample();
					twitterFeedExample.createCriteria().andIdCountryEqualTo(country.getId()).andDeletedEqualTo(0);
					List<TwitterFeed> twitterFeeds = DAOManager.getTwitterFeedDAO().selectTwitterFeedByExampleWithBLOBs(twitterFeedExample);
					if (!twitterFeeds.isEmpty()) {
						setTwitterFeed(twitterFeeds.get(0));
					} else {
						setTwitterFeed(null);
					}
					// cargo el feed de facebook
					FacebookFeedExample facebookFeedExample = new FacebookFeedExample();
					facebookFeedExample.createCriteria().andIdCountryEqualTo(country.getId()).andDeletedEqualTo(0);
					List<FacebookFeed> facebookFeeds = DAOManager.getFacebookFeedDAO().selectFacebookFeedByExampleWithBLOBs(facebookFeedExample);
					if (!facebookFeeds.isEmpty()) {
						setFacebookFeed(facebookFeeds.get(0));
					} else {
						setFacebookFeed(null);
					}
				
					setLastVideos(DAOManager.getVideoDAO().selectLastActiveVideosForCountry(country));
					
					// cargo el footer, en ultimo lugar porque el seteo hace el reemplazo en base a otros datos
					FooterDAO footerDAO = DAOManager.getFooterDAO();
					FooterExample footerExample = new FooterExample();
					footerExample.createCriteria().andDeletedEqualTo(0).andIdCountryEqualTo(country.getId());
					List<Footer> footers = footerDAO.selectFooterByExampleWithBLOBs(footerExample);
					if (!footers.isEmpty()) {
						setFooter(footers.get(0));
					} else {
						setFooter(null);
					}
				}
			});
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	public List<RankingPosition> getReducedRanking() {
		return this.getRankingPositions().getPositions().subList(0, REDUCED_RANKING_SIZE);
	}
	
	public List<NoteValueObject> getReducedAgenda() {
		List<NoteValueObject> result = new ArrayList<NoteValueObject>();
		int size = 0;
		Iterator<NoteValueObject> agendaIterator = this.getAgendaNotes().iterator();
		while (size < MAX_AGENDA_NOTES_FOR_HOME && agendaIterator.hasNext()) {
			result.add(agendaIterator.next());
			size = size + 1;
		}
		return result;
	}
	
	public List<NoteValueObject> getReducedLastNotes() {
		if (this.getLastNotes().size() > 2) {
			List<NoteValueObject> result = new ArrayList<NoteValueObject>();
			int size = 0;
			Iterator<NoteValueObject> lastNotesIterator = this.getLastNotes().subList(2, getLastNotes().size()).iterator();
			while (size < MAX_LAST_NOTES_FOR_HOME && lastNotesIterator.hasNext()) {
				result.add(lastNotesIterator.next());
				size = size + 1;
			}
			return result;
		} else {
			return new ArrayList<NoteValueObject>();
		}
	}
	
	public static String formatAgendaDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return simpleDateFormat.format(date);
	}
	
	public static String formatDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return simpleDateFormat.format(date);
	}
	
	public static String formatDateForUrl(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}
	
	public static Date parseDateForUrl(String date) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	
	public static String formatMagazineDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yyyy");
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
	
	public void setCountryByIsoCode2(String isoCode2) {
		this.initCountries();
		for (Country c : getAllCountries()) {
			if (c.getIsoCode2().equals(isoCode2)) {
				setCountry(c);
				initHomeForCountry(c);
				return;
			}
		}
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

	public List<NoteValueObject> getFrontCoverNotes() {
		return frontCoverNotes;
	}

	public void setFrontCoverNotes(List<NoteValueObject> frontCoverNotes) {
		this.frontCoverNotes = frontCoverNotes;
	}

	public List<NoteValueObject> getAgendaNotes() {
		return agendaNotes;
	}

	public void setAgendaNotes(List<NoteValueObject> agendaNotes) {
		this.agendaNotes = agendaNotes;
	}

	public TwitterFeed getTwitterFeed() {
		return twitterFeed;
	}

	public void setTwitterFeed(TwitterFeed twitterFeed) {
		this.twitterFeed = twitterFeed;
	}

	public FacebookFeed getFacebookFeed() {
		return facebookFeed;
	}

	public void setFacebookFeed(FacebookFeed facebookFeed) {
		this.facebookFeed = facebookFeed;
	}

	public List<NoteValueObject> getLastNotes() {
		return lastNotes;
	}
	
	public List<NoteValueObject> getLastNotesLinks() {
		List<NoteValueObject> result = new ArrayList<NoteValueObject>();
		if (this.getLastNotes() != null) {
			result.addAll(this.getLastNotes());
		}
		if (this.getLastNoteFirst() != null) {
			result.remove(this.getLastNoteFirst());
		}
		if (this.getLastNoteSecond() != null) {
			result.remove(this.getLastNoteSecond());
		}
		if (this.getReducedLastNotes() != null) {
			result.removeAll(this.getReducedLastNotes());
		}
		return result;
	}
	
	public List<NoteValueObject> getAgendaNotesLinks() {
		List<NoteValueObject> result = new ArrayList<NoteValueObject>();
		result.addAll(this.getAgendaNotes());
		result.removeAll(this.getReducedAgenda());
		return result;
	}

	public void setLastNotes(List<NoteValueObject> lastNotes) {
		this.lastNotes = lastNotes;
	}
	
	public Video getTopVideo() {
		return getLastVideos().get(0);
	}

	public List<Video> getLastVideos() {
		if (lastVideos.size() > MAX_VIDEOS_FOR_HOME) {
			return lastVideos.subList(0, MAX_VIDEOS_FOR_HOME);
		}
		return lastVideos;
	}

	public void setLastVideos(List<Video> lastVideos) {
		this.lastVideos = lastVideos;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public Footer getFooter() {
		return footer;
	}

	public void setFooter(Footer footer) {
		if (footer != null) {
			String html = footer.getHtmlcontent();
			String htmlNote = footer.getHtmlcontent();
			String htmlRanking = footer.getHtmlcontent();
			html = StringUtils.replace(html, "[SECCIONES]", this.getSectionsReplacement());
			htmlNote = StringUtils.replace(htmlNote, "[SECCIONES]", this.getSectionsReplacementForNote());
			htmlRanking = StringUtils.replace(htmlRanking, "[SECCIONES]", this.getSectionsReplacementForRanking());
			html = StringUtils.replace(html, "[NOTAS_POPULARES]", this.getPopulasNotesReplacement());
			htmlNote = StringUtils.replace(htmlNote, "[NOTAS_POPULARES]", this.getPopulasNotesReplacementForNote());
			htmlNote = StringUtils.replace(htmlNote, "src=\"images", "src=\"../../../images");
			
			htmlRanking = StringUtils.replace(htmlRanking, "[NOTAS_POPULARES]", this.getPopulasNotesReplacementForRanking());
			htmlRanking = StringUtils.replace(htmlRanking, "src=\"images", "src=\"../../images");
			footer.setHtmlcontent(html);
			Footer notefooter = new Footer();
			notefooter.setHtmlcontent(htmlNote);
			setFooterNote(notefooter);
			
			Footer rankingfooter = new Footer();
			rankingfooter.setHtmlcontent(htmlRanking);
			setFooterRanking(rankingfooter);
		}
		this.footer = footer;
	}

	public NoteValueObject getLastNoteFirst() {
		return lastNoteFirst;
	}

	public void setLastNoteFirst(NoteValueObject lastNoteFirst) {
		this.lastNoteFirst = lastNoteFirst;
	}

	public NoteValueObject getLastNoteSecond() {
		return lastNoteSecond;
	}

	public void setLastNoteSecond(NoteValueObject lastNoteSecond) {
		this.lastNoteSecond = lastNoteSecond;
	}

	public BannerValueObject getHomeTop() {
		return homeTop;
	}

	public void setHomeTop(BannerValueObject homeTop) {
		this.homeTop = homeTop;
	}

	public BannerValueObject getHomeRight() {
		return homeRight;
	}

	public void setHomeRight(BannerValueObject homeRight) {
		this.homeRight = homeRight;
	}

	public BannerValueObject getNoteTop() {
		return noteTop;
	}

	public void setNoteTop(BannerValueObject noteTop) {
		this.noteTop = noteTop;
	}

	public BannerValueObject getNoteRight() {
		return noteRight;
	}

	public void setNoteRight(BannerValueObject noteRight) {
		this.noteRight = noteRight;
	}

	public List<NoteValueObject> getAllNotes() {
		return allNotes;
	}

	public void setAllNotes(List<NoteValueObject> allNotes) {
		this.allNotes = allNotes;
	}

	public Map<Section, List<NoteValueObject>> getSectionsNotes() {
		return sectionsNotes;
	}
	
	public List<Integer> getPages(Section section, int pageNumber) {
		List<Integer> result = new ArrayList<Integer>();
		int min = pageNumber - SECTIONS_PAGES_SIDE;
		if (min < 0) {
			min = 0;
		}
		int sectionSize = getSectionsNotes().get(section).size();
		int pages = 0;
		for (int i = min; (i * SECTION_PAGE_SIZE) < sectionSize && pages < MAX_SECTIONS_PAGES; i++) {
			result.add(i);
			pages = pages + 1;
		}
		return result;
	}
	
	public List<NoteValueObject> getSectionsNotes(Section section, int pageNumber) {
		List<NoteValueObject> result;
		List<NoteValueObject> nvo = getSectionsNotes().get(section);
		int seekAt = pageNumber * SECTION_PAGE_SIZE;
		if (nvo.size() > seekAt) {
			result = nvo.subList(seekAt, nvo.size());
		} else {
			result = new ArrayList<NoteValueObject>();
		}
		final List<NoteValueObject> finalResult = result;
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					Set<Integer> notesIds = new HashSet<Integer>();
					for (NoteValueObject nvo : finalResult) {
						if (nvo.getNoteImages().isEmpty()) {
							notesIds.add(nvo.getId());
						}
					}
					if (!notesIds.isEmpty()) {
						List<Integer> param = new ArrayList<Integer>(notesIds);
						NoteImageExample noteImageExample = new NoteImageExample();
						noteImageExample.createCriteria().andIdNoteIn(param);
						noteImageExample.setOrderByClause("id_note, orderNumber");
						List<NoteImage> noteImages = DAOManager.getNoteImageDAO().selectNoteImageByExampleWithoutBLOBs(noteImageExample);
						for (NoteImage ni : noteImages) {
							for (NoteValueObject nvo : finalResult) {
								if (ni.getIdNote().equals(nvo.getId())) {
									nvo.addNoteImage(ni);
								}
							}
						}
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalResult;
	}

	public void setSectionsNotes(Map<Section, List<NoteValueObject>> sectionsNotes) {
		this.sectionsNotes = sectionsNotes;
	}

	public Section getRankingSection() {
		return rankingSection;
	}

	public void setRankingSection(Section rankingSection) {
		this.rankingSection = rankingSection;
	}

	public Section getVideoSection() {
		return videoSection;
	}

	public void setVideoSection(Section videoSection) {
		this.videoSection = videoSection;
	}

	public Footer getFooterNote() {
		return footerNote;
	}

	public void setFooterNote(Footer footerNote) {
		this.footerNote = footerNote;
	}

	public Footer getFooterRanking() {
		return footerRanking;
	}

	public void setFooterRanking(Footer footerRanking) {
		this.footerRanking = footerRanking;
	}
}
