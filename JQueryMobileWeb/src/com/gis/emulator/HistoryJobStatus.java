package com.gis.emulator;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HistoryJobStatus extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -4822256555026476546L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (CreateAsyncJob.retries >= CreateAsyncJob.maxretries) { 
			resp.getOutputStream().write(("[{\"jobStatus\": \"OK\",\"jobId\": "+CreateAsyncJob.nextJobId.get()+"}]").getBytes());
		} else {
			resp.getOutputStream().write(("[{}]").getBytes());
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
