package com.tdil.milka.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.Video;
import com.tdil.milka.model.VideoExample;
import com.tdil.milka.struts.forms.PostItForm;
import com.tdil.struts.TransactionalActionWithResult;

public class VideoUtils {

	private static Logger getLog() {
		return LoggerProvider.getLogger(PostItForm.class);
	}
	
	private static final class GetVideos implements TransactionalActionWithResult {
		public Object executeInTransaction() throws SQLException {
			VideoExample videoExample = new VideoExample();
			videoExample.setOrderByClause("orderNumber");
			return DAOManager.getVideoDAO().selectVideoByExample(videoExample);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Video> getVideos() {
		try {
			List<Video> result = (List<Video>)TransactionProvider.executeInTransactionWithResult(new GetVideos());
			return result;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Video>();
		}
	}
}
