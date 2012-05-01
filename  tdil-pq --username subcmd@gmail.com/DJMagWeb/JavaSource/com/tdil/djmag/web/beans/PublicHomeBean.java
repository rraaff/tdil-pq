package com.tdil.djmag.web.beans;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import com.tdil.djmag.model.Note;
import com.tdil.djmag.model.NoteImage;
import com.tdil.djmag.model.NoteImageExample;
import com.tdil.djmag.model.RankingNote;
import com.tdil.djmag.model.RankingPositions;
import com.tdil.djmag.model.Section;
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
	private Country country;
	private List<Section> sectionsForCountry;
	
	private RankingNote ranking;
	private RankingPositions rankingPositions;
	
	private Magazine magazine;
	
	private Footer footer;
	
	private TwitterFeed twitterFeed;
	private FacebookFeed facebookFeed;
	
	private List<NoteValueObject> frontCoverNotes;
	private NoteValueObject lastNoteFirst;
	private NoteValueObject lastNoteSecond;
	private List<NoteValueObject> lastNotes;
	private List<NoteValueObject> agendaNotes;
	
	private List<NoteValueObject> allNotes;
	
	private BannerValueObject homeTop;
	private BannerValueObject homeRight;
	private BannerValueObject noteTop;
	private BannerValueObject noteRight;
	
	private List<Video> lastVideos;
	
	private List<Country> allCountries;
	
	public static final String PUBLIC_HOME_BEAN = "publicHomeBean";
	private static final int REDUCED_RANKING_SIZE = 10;
	private static final int MAX_AGENDA_NOTES_FOR_HOME = 5;
	private static final int MAX_LAST_NOTES_FOR_HOME = 3;
	private static final int MAX_VIDEOS_FOR_HOME = 3;
	private static final int MAX_NOTES_FOR_FOOTER = 7;
	
	public static final String LIGTH_BOX_PARAMS = "?iframe=true&width=800&height=600";
	
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
	
	public String getExternalLink(String date, String webTitle) {
		NoteValueObject noteValueObject = getNoteByParams(this.getCountry().getIsoCode2(), date, webTitle);
		StringBuffer result = new StringBuffer();
		result.append("./notes/").append(this.getCountry().getIsoCode2()).append("/");
		result.append(formatDateForUrl(noteValueObject.getFromDate())).append("/");
		result.append(noteValueObject.getWebTitle()).append(".html");
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
		return result;
	}
	
	private String getPopulasNotesReplacement() {
		StringBuffer result = new StringBuffer();
		int index = 0;
		Iterator<NoteValueObject> frontCoverIter = this.getFrontCoverNotes().iterator();
		while (frontCoverIter.hasNext() && index < MAX_NOTES_FOR_FOOTER) {
			// TODO hacer link
			NoteValueObject nvo = frontCoverIter.next();
			result.append("<a href=\"#\">").append(nvo.getTitle()).append("</a>\n");
			index = index + 1;
		}
		if (index < MAX_NOTES_FOR_FOOTER) {
			Iterator<NoteValueObject> lastNewsIter = this.getLastNotes().iterator();
			while (lastNewsIter.hasNext() && index < MAX_NOTES_FOR_FOOTER) {
				// TODO hacer link
				NoteValueObject nvo = lastNewsIter.next();
				result.append("<a href=\"#\">").append(nvo.getTitle()).append("</a>\n");
				index = index + 1;
			}
		}
		return result.toString();
	}
	
	private String getSectionsReplacement() {
		StringBuffer result = new StringBuffer();
		// TODO , make link o algo asi
		for (Section section : getSectionsForCountry()) {
			result.append("<a href=\"#\">").append(section.getName()).append("</a>\n");
		}
		return result.toString();
	}
	
	@SuppressWarnings("unchecked")
	public List<Video> getAllVideosForCountry() {
		final Country country = this.getCountry();
		try {
			 List<Video> result = (List<Video>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					VideoExample videoExample = new VideoExample();
					videoExample.createCriteria().andDeletedEqualTo(0).andIdCountryEqualTo(country.getId());
					videoExample.setOrderByClause("id desc");
					List<Video> result = DAOManager.getVideoDAO().selectVideoByExampleWithBLOBs(videoExample);
					return result;
				}
			});
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
					// cargo las secciones
					SectionDAO sectionDAO = DAOManager.getSectionDAO();
					setSectionsForCountry(sectionDAO.selectActiveSectionsForCountry(country));
					
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
					NoteDAO noteDAO = DAOManager.getNoteDAO();
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
	
	public List<String> getReducedRanking() {
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
			html = StringUtils.replace(html, "[SECCIONES]", this.getSectionsReplacement());
			html = StringUtils.replace(html, "[NOTAS_POPULARES]", this.getPopulasNotesReplacement());
			footer.setHtmlcontent(html);
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
}
