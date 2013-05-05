package com.tdil.lojack.web.jobs;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

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
			if (asyncJob.getStatus().equals(AsyncJobStatus.INITIAL)
					|| asyncJob.getStatus().equals(AsyncJobStatus.SENDEDTOGIS)
					|| asyncJob.getStatus().equals(AsyncJobStatus.PENDING)) {
				return asyncJob.getIdjob();
			}
		}
		return 0; // nada para consultar
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

	public void updateStatus(Collection<JobStatus> result) {
		 // TODO post ejecucion, marcar los timeout...
		for (JobStatus jobStatus : result) {
			updateStatus(jobStatus);
		}

	}

	private void updateStatus(JobStatus jobStatus) {
		for (AsyncJob asyncJob : jobs) {
			if (asyncJob.getIdjob().equals(jobStatus)) {
				asyncJob.setStatus(AsyncJobConstants.getStatusId(jobStatus.getJobStatus()));
				return;
			}
		}
	}

	public int getUserId() {
		return userId;
	}


}
