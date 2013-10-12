package com.gis.emulator;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAsyncJob extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -4822256555026476546L;
	public static AtomicInteger nextJobId = new AtomicInteger(0);
	
	public static int retries = 0;
	public static int maxretries = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int jobId = nextJobId.incrementAndGet();
		System.out.println("job id is " + jobId);
		retries = 0;
		resp.getOutputStream().write(("{\"result\": true,\"jobId\": "+jobId+"}").getBytes());
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
