package com.tdil.milka.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.valueobjects.MilkaPhotoValueObject;
import com.tdil.milka.struts.forms.PostItForm;
import com.tdil.struts.TransactionalActionWithResult;

public class MilkaPhotoUtils {
	
	public static final int SHOW_LIMIT = 7;

	private static Logger getLog() {
		return LoggerProvider.getLogger(PostItForm.class);
	}
	
	private static final class GetMilkaPhoto implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			List<MilkaPhotoValueObject> photos = DAOManager.getMilkaPhotoDAO().selectMilkaPhotoToReviewWithAuthor();
			return photos;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<MilkaPhotoValueObject> getPhotos() {
		try {
			List<MilkaPhotoValueObject> result = (List<MilkaPhotoValueObject>)TransactionProvider.executeInTransactionWithResult(new GetMilkaPhoto());
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<MilkaPhotoValueObject>();
		}
	}
	
	public static List<MilkaPhotoValueObject> getDisplayPart(List<MilkaPhotoValueObject> photos) {
		int limit = Math.min(photos.size(), SHOW_LIMIT);
		return photos.subList(0, limit);
	}
	
	public static List<MilkaPhotoValueObject> getHiddenPart(List<MilkaPhotoValueObject> photos) {
		if (photos.size() > SHOW_LIMIT) {
			return photos.subList(SHOW_LIMIT, photos.size());
		} else {
			return new ArrayList<MilkaPhotoValueObject>();
		}
	}
}
