package com.tdil.lojack.web.jobs;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.tdil.lojack.gis.UpdateMiddlewareJobsThread;
import com.tdil.lojack.gis.model.AsyncJobConstants;
import com.tdil.lojack.gis.model.AsyncJobConstants.AsyncJobStatus;
import com.tdil.lojack.gis.model.JobStatus;
import com.tdil.lojack.model.AsyncJob;
import com.tdil.lojack.utils.WebsiteUser;

public class UserJobCollection implements Serializable {

	private static final long serialVersionUID = 7792011039877341570L;

	private int userId;
	private transient WeakReference<HttpSession> sessionref;
	private transient WeakReference<WebsiteUser> userref;
	private List<AsyncJob> jobs = new ArrayList<AsyncJob>();

	public UserJobCollection(HttpSession session, WebsiteUser user) {
		super();
		this.userId = user.getModelUser().getId();
		this.sessionref = new WeakReference<HttpSession>(session);
		this.userref = new WeakReference<WebsiteUser>(user);
	}

	public int getOldestJob() {
		for (AsyncJob asyncJob : jobs) {
			if (isInProgress(asyncJob)) {
				return asyncJob.getIdjob();
			}
		}
		return 0; // nada para consultar
	}

	public boolean isInProgress(AsyncJob asyncJob) {
		return asyncJob.getStatus().equals(AsyncJobStatus.INITIAL)
		|| asyncJob.getStatus().equals(AsyncJobStatus.SENDEDTOGIS)
		|| asyncJob.getStatus().equals(AsyncJobStatus.PENDING);
	}

	public List<AsyncJob> getJobs() {
		return jobs;
	}
	public WebsiteUser getUser() {
		return userref.get();
	}

	public void setJobs(List<AsyncJob> jobs) {
		this.jobs = jobs;
	}
	public boolean isExpired() {
		WebsiteUser user = userref.get();
		if (user == null) {
			return true;
		}
		if (!user.isLogged()) {
			return true;
		}
		HttpSession session = sessionref.get();
		if (session == null) {
			return true;
		}
		try {
			session.getAttribute("user");
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	public void addJob(AsyncJob job) {
		this.jobs.add(job);
	}

	public synchronized void updateStatus(Collection<JobStatus> result) {
		for (JobStatus jobStatus : result) {
			updateStatus(jobStatus);
		}
	}

	private void remove(Collection<AsyncJob> finished) {
		this.jobs.removeAll(finished);
	}

	private void updateStatus(JobStatus jobStatus) {
		for (AsyncJob asyncJob : jobs) {
			if (asyncJob.getIdjob().equals(jobStatus.getJobId())) {
				asyncJob.setStatus(AsyncJobConstants.getStatusId(jobStatus.getJobStatus()));
				return;
			}
		}
	}

	public int getUserId() {
		return userId;
	}

	public synchronized List<AsyncJob> getAndRemoveFinishedJobs() {
		List<AsyncJob> finished = new ArrayList<AsyncJob>();
		for (AsyncJob asyncJob : getJobs()) {
			if (!isInProgress(asyncJob) || isAssumedDead(asyncJob)) {
				finished.add(asyncJob);
			}
		}
		remove(finished);
		return finished;
	}

	private boolean isAssumedDead(AsyncJob asyncJob) {
		Calendar now = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		end.setTime(asyncJob.getPostdate());
		end.add(Calendar.MILLISECOND, UpdateMiddlewareJobsThread.getJobAbortTime());
		return end.before(now);
	}

	public synchronized AsyncJob getPendingJob(int idEntidad, int idLuz) {
		AsyncJob result = null;
		List<AsyncJob> deadJobs = new ArrayList<AsyncJob>();
		for (AsyncJob asyncJob : getJobs()) {
			if (isAssumedDead(asyncJob)) {
				deadJobs.add(asyncJob);
			} else {
				if (result == null) {
					if (asyncJob.getIdentidad().equals(idEntidad)) {
						if (asyncJob.getIdluz().equals(idLuz)) {
							if (isInProgress(asyncJob)) {
								result = asyncJob;
							}
						}
					}
				}
			}
		}
		return result;
	}


}
