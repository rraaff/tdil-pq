package com.tdil.lojack.gis.model;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

public class AsyncJobResponse {

	private static final String JOB_ID = "jobId";
	private int jobId = 0;
	private JSONObject jsonObject;
	
	public static AsyncJobResponse ERROR = new AsyncJobResponse();

	public AsyncJobResponse() {
		jsonObject = null;
	}

	public AsyncJobResponse(JSONObject jsonObject) {
		super();
		this.jsonObject = jsonObject;
		init();
	}

	private void init() {
		if (jsonObject != null) {
			if (jsonObject.containsKey(JOB_ID)) {
				if (jsonObject.get(JOB_ID) != JSONNull.getInstance()) {
					this.jobId = jsonObject.getInt(JOB_ID);
				}
			}
		} 
	}

	public int getJobId() {
		return jobId;
	}
	
}
