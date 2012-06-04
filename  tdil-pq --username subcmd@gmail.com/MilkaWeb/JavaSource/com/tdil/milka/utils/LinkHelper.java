package com.tdil.milka.utils;

import java.sql.SQLException;
import java.util.List;

import com.tdil.milka.dao.LinksDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.EmailEndings;
import com.tdil.milka.model.Links;
import com.tdil.milka.model.LinksExample;
import com.tdil.milka.model.LoveNicknames;
import com.tdil.milka.model.MailToParent;
import com.tdil.milka.model.PostIt;
import com.tdil.milka.model.WallWritting;
import com.tdil.milka.web.Experience;

public class LinkHelper {

	public static boolean areEquals(String urlLink, String urlLink2) {
		if (urlLink == null && urlLink2 == null) {
			return true;
		}
		if (urlLink == null && urlLink2 != null) {
			return false;
		}
		if (urlLink != null && urlLink2 == null) {
			return false;
		}
		return urlLink.equals(urlLink2);
	}

	public static void deleteActualLink(String originType, Integer originId) throws SQLException {
		LinksDAO linksDAO = DAOManager.getLinksDAO();
		LinksExample linksExample = new LinksExample();
		linksExample.createCriteria().andOrigintypeEqualTo(originType).andOriginEqualTo(originId);
		linksDAO.deleteLinksByExample(linksExample);
	}

	public static void createNewLink(String originType, Integer originId, String destinationType, Integer destinationId) throws SQLException {
		Links links = new Links();
		links.setOrigintype(originType);
		links.setOrigin(originId);
		links.setDestinationtype(destinationType);
		links.setDestination(destinationId);
		links.setDeleted(0);
		DAOManager.getLinksDAO().insertLinks(links);
	}

	public static void deleteOriginsFor(String destinationType, Integer destinationId) throws SQLException {
		LinksExample linksExample = new LinksExample();
		linksExample.createCriteria().andDestinationtypeEqualTo(destinationType).andDestinationEqualTo(destinationId);
		List<Links> links = DAOManager.getLinksDAO().selectLinksByExample(linksExample);
		for (Links link : links) {
			deleteOrigin(link);
			DAOManager.getLinksDAO().deleteLinksByPrimaryKey(link.getId());
		}
	}

	private static void deleteOrigin(Links link) throws SQLException {
		if (Experience.FINALES_DE_EMAIL.name().equals(link.getOrigintype())) {
			EmailEndings emailEndings = DAOManager.getEmailEndingsDAO().selectEmailEndingsByPrimaryKey(link.getOrigin());
			emailEndings.setUrlLink(null);
			DAOManager.getEmailEndingsDAO().updateEmailEndingsByPrimaryKey(emailEndings);
		}
		if (Experience.CARTAS_DE_HIJOS_A_PADRES.name().equals(link.getOrigintype())) {
			MailToParent mailToParent = DAOManager.getMailToParentDAO().selectMailToParentByPrimaryKey(link.getOrigin());
			mailToParent.setUrlLink(null);
			DAOManager.getMailToParentDAO().updateMailToParentByPrimaryKey(mailToParent);
		}
		if (Experience.POST_ITS.name().equals(link.getOrigintype())) {
			PostIt postIt = DAOManager.getPostItDAO().selectPostItByPrimaryKey(link.getOrigin());
			postIt.setUrlLink(null);
			DAOManager.getPostItDAO().updatePostItByPrimaryKey(postIt);
		}
		if (Experience.PAPAPEDIA.name().equals(link.getOrigintype())) {
			WallWritting wallWritting = DAOManager.getWallWrittingDAO().selectWallWrittingByPrimaryKey(link.getOrigin());
			wallWritting.setUrlLink(null);
			DAOManager.getWallWrittingDAO().updateWallWrittingByPrimaryKey(wallWritting);
		}
		if (Experience.APODOS_DE_AMOR.name().equals(link.getOrigintype())) {
			LoveNicknames loveNicknames = DAOManager.getLoveNicknamesDAO().selectLoveNicknamesByPrimaryKey(link.getOrigin());
			loveNicknames.setUrlLink(null);
			DAOManager.getLoveNicknamesDAO().updateLoveNicknamesByPrimaryKey(loveNicknames);
		}
	}

}
