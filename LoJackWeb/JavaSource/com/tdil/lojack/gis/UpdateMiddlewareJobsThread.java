package com.tdil.lojack.gis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.dao.AsyncJobDAO;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.gis.model.JobStatus;
import com.tdil.lojack.utils.SystemPropertyUtils;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.lojack.web.jobs.UserJobCollection;
import com.tdil.struts.TransactionalActionWithResult;

public class UpdateMiddlewareJobsThread extends Thread {

	private static int JobRefreshTime = 1000;
	private static int JobAbortTime = 10000;
	private static int JobClientRefreshTime = 1000;

	private List<UserJobCollection> jobsToUpdate = new Vector<UserJobCollection>();

	private static UpdateMiddlewareJobsThread updateMiddlewareJobsThread;

	public UpdateMiddlewareJobsThread() {
		super("UpdateMiddlewareJobsThread");
	}

	private static class UpdateMiddlewareJobsTransaction implements TransactionalActionWithResult {
		@Override
		public Object executeInTransaction() throws SQLException {
			AsyncJobDAO asyncJobDAO = DAOManager.getAsyncJobDAO();
			// Obtengo el job mas viejo sin terminar
			//int oldestJobId = asyncJobDAO.oldestMWJob();
			// Consulto con ese job


			// Por cada resultado si cambio lo actualizo

			// Para todos los no actualizados, si paso el tiempo de finalizacion los marco como TIMEOUT

			// retorno el tiempo de espera entre ejecuciones

			return 1000;
		}
	}

	public void addUserJobCollection(UserJobCollection userJobCollection) {
		if (getLog().isDebugEnabled()) {
			getLog().debug("Adding jobs for " + userJobCollection.getUserId());
		}
		this.jobsToUpdate.add(userJobCollection);
	}

	@Override
	public void run() {
		//UpdateMiddlewareJobsTransaction updateJobs = new UpdateMiddlewareJobsTransaction();
		while (true) {
			if (getLog().isDebugEnabled()) {
				getLog().debug("Updating jobs");
			}
			long waitMillis = getJobClientRefreshTime();
			List<UserJobCollection> toIterate = new ArrayList<UserJobCollection>();
			toIterate.addAll(jobsToUpdate);
			for (UserJobCollection userJobCollection : toIterate) {
				if (userJobCollection.isExpired()) {
					if (getLog().isDebugEnabled()) {
						getLog().debug("Removing jobs for " + userJobCollection.getUserId());
					}
					jobsToUpdate.remove(userJobCollection);
				} else {
					if (getLog().isDebugEnabled()) {
						getLog().debug("Updating jobs for " + userJobCollection.getUserId());
					}
					update(userJobCollection);
				}
			}
			/*
			try {
				waitMillis = (Long)TransactionProvider.executeInTransactionWithResult(updateJobs);
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			} */
			// Espero un cierto tiempo
			try {
				Thread.sleep(waitMillis);
			} catch (InterruptedException e) {
				getLog().error(e.getMessage(), e);
			}
		}
	}


	private void update(UserJobCollection userJobCollection) {
		WebsiteUser user = userJobCollection.getUser();
		int jobId = userJobCollection.getOldestJob();
		if (jobId != 0) { // si hay algo para consultar
			// consulto al middleware
			Collection<JobStatus> result = LoJackServicesConnector.getHistoryJobStatus(user, jobId);
			userJobCollection.updateStatus(result);
		}
	}


	private static Logger getLog() {
		return LoggerProvider.getLogger(UpdateMiddlewareJobsThread.class);
	}

	public static UpdateMiddlewareJobsThread getUpdateMiddlewareJobsThread() {
		return updateMiddlewareJobsThread;
	}

	public static void setUpdateMiddlewareJobsThread(UpdateMiddlewareJobsThread updateMiddlewareJobsThread) {
		UpdateMiddlewareJobsThread.updateMiddlewareJobsThread = updateMiddlewareJobsThread;
	}

	public static int getJobRefreshTime() {
		return JobRefreshTime;
	}

	public static void setJobRefreshTime(int jobRefreshTime) {
		JobRefreshTime = jobRefreshTime;
	}

	public static int getJobAbortTime() {
		return JobAbortTime;
	}

	public static void setJobAbortTime(int jobAbortTime) {
		JobAbortTime = jobAbortTime;
	}

	public static int getJobClientRefreshTime() {
		return JobClientRefreshTime;
	}

	public static void setJobClientRefreshTime(int jobClientRefreshTime) {
		JobClientRefreshTime = jobClientRefreshTime;
	}
}
