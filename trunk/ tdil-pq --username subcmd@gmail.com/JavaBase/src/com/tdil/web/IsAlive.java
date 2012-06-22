package com.tdil.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;

public class IsAlive extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static int appversion = -1;
	
	private static final Logger Log = LoggerProvider.getLogger(IsAlive.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doIsAlive(req, res);
	}

	private void doIsAlive(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if (appversion == -1) {
//			String st = getServletContext().getRealPath("/version.txt");
//			System.out.println(st);
		}
		int version = 0;
		try {
			version = (Integer)TransactionProvider.executeInTransactionWithResult(new TestDatabase());
		} catch (Exception e) {
			Log.error(e.getMessage(), e);
			version = -1;
		}
		if (version == -1) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		// TODO segun la version, 404 o como esta
		PrintWriter out = res.getWriter();
		out.println("ok");
		out.close();
	}
	
	
}
