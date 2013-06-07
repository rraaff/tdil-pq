package com.tdil.lojack.gis.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.Vlookup;

import com.tdil.log4j.LoggerProvider;

public class AsyncJobConstants {
	
	private static Map<String, Integer> mwToFrontStatusMap;
	private static Map<Integer, String> frontToMwStatusMap;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(AsyncJobConstants.class);
	
	static {
		mwToFrontStatusMap = new HashMap<String, Integer>();
		mwToFrontStatusMap.put(AsyncJobMWStatus.INCORRECTPARAMS, AsyncJobStatus.INCORRECTPARAMS);
		mwToFrontStatusMap.put(AsyncJobMWStatus.PENDING, AsyncJobStatus.PENDING);
		mwToFrontStatusMap.put(AsyncJobMWStatus.SENDEDTOGIS, AsyncJobStatus.SENDEDTOGIS);
		mwToFrontStatusMap.put(AsyncJobMWStatus.OK, AsyncJobStatus.OK);
		mwToFrontStatusMap.put(AsyncJobMWStatus.FAILED, AsyncJobStatus.FAILED);
		mwToFrontStatusMap.put(AsyncJobMWStatus.SENDMESSAGE, AsyncJobStatus.SENDMESSAGE);
		
		frontToMwStatusMap = new HashMap<Integer, String>();
		frontToMwStatusMap.put(AsyncJobStatus.INCORRECTPARAMS, AsyncJobMWStatus.INCORRECTPARAMS);
		frontToMwStatusMap.put(AsyncJobStatus.PENDING, AsyncJobMWStatus.PENDING);
		frontToMwStatusMap.put(AsyncJobStatus.SENDEDTOGIS, AsyncJobMWStatus.SENDEDTOGIS);
		frontToMwStatusMap.put(AsyncJobStatus.OK, AsyncJobMWStatus.OK);
		frontToMwStatusMap.put(AsyncJobStatus.FAILED, AsyncJobMWStatus.FAILED);
		frontToMwStatusMap.put(AsyncJobStatus.SENDMESSAGE, AsyncJobMWStatus.SENDMESSAGE);
	}

	public interface AsyncJobActions {
		// Acciones
		public static final int ACTIVATE_ALARM = 0;
		public static final int DEACTIVATE_ALARM = 1;
		public static final int PANIC_ALARM = 2;
		public static final int TURN_ON_LIGHT = 3;
		public static final int TURN_OFF_LIGHT = 4;
		public static final int ACTIVATE_RANDOM_LIGHT = 5;
		public static final int DEACTIVATE_RANDOM_LIGHT = 6;
	}
	
	public interface AsyncJobStatus {
		// Estados
		public static final int INITIAL = 100;
		public static final int JOB_TIMEOUT = 101;
		
		public static final int INCORRECTPARAMS = 0;
		public static final int PENDING = 1;
		public static final int SENDEDTOGIS = 2; 
		public static final int OK = 3;
		public static final int FAILED = 4; 
		public static final int SENDMESSAGE = 5; // que significa send message
	}
	
	public interface AsyncJobMWStatus {
		// Estados
		public static final String INCORRECTPARAMS = "INCORRECTPARAMS";
		public static final String PENDING = "PENDING";
		public static final String SENDEDTOGIS = "SENDEDTOGIS"; 
		public static final String OK = "OK";
		public static final String FAILED = "FAILED"; 
		public static final String SENDMESSAGE = "SENDMESSAGE"; 
	}
	
	
	public static int getStatusId(String status) {
		Integer value = mwToFrontStatusMap.get(status);
		if (value == null) {
			LOG.error("can not find status for " + (status == null ? "null" : status));
			return 0;
		}
		return value;
	}
	
	public static String getStatus(int statusId) {
		return frontToMwStatusMap.get(statusId);
	}
}
