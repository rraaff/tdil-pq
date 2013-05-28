package com.tdil.lojack.utils;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.AsyncJobConstants.AsyncJobStatus;
import com.tdil.lojack.gis.model.Light;
import com.tdil.lojack.model.AsyncJob;
import com.tdil.struts.TransactionalActionWithResult;

public class AsyncJobUtils {

	private static Logger getLog() {
		return LoggerProvider.getLogger(AsyncJobUtils.class);
	}

	private static final class RegisterNewJob implements TransactionalActionWithResult {

		private AsyncJob asyncJob;

		public RegisterNewJob(int idwebsiteuser, int action, int jobId, int identidad, int idluz) {
			super();
			asyncJob = new AsyncJob();
			asyncJob.setIdwebsiteuser(idwebsiteuser);
			asyncJob.setAction(action);
			asyncJob.setIdjob(jobId);
			asyncJob.setIdentidad(identidad);
			asyncJob.setIdluz(idluz);
			asyncJob.setPostdate(new Date());
			asyncJob.setStatus(AsyncJobStatus.INITIAL);
			asyncJob.setDeleted(0);

		}

		public Object executeInTransaction() throws SQLException {
			return DAOManager.getAsyncJobDAO().insertAsyncJob(asyncJob);
		}
	}

	public static Integer registerNewJob(int idwebsiteuser, int action, int jobId, int identidad, int idluz) throws SQLException {
		return (Integer)new RegisterNewJob(idwebsiteuser, action, jobId, identidad, idluz).executeInTransaction();
	}

	public static boolean hasJobInProgress(Alarm alarm, WebsiteUser websiteUser) {
		return websiteUser.hasJobInProgress(alarm);
	}

	public static boolean hasJobInProgress(Light light, WebsiteUser websiteUser) {
		return websiteUser.hasJobInProgress(light);
	}
	
	public static boolean displayInactive(Alarm alarm, WebsiteUser websiteUser) {
		if (alarm.isInactive()) {
			return !hasJobInProgress(alarm, websiteUser);
		} else {
			return hasJobInProgress(alarm, websiteUser);
		}
	}

}
