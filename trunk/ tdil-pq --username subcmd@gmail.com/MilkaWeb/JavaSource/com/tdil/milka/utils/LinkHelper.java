package com.tdil.milka.utils;

import java.sql.SQLException;

import com.tdil.milka.dao.LinksDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.Links;
import com.tdil.milka.model.LinksExample;

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

}
