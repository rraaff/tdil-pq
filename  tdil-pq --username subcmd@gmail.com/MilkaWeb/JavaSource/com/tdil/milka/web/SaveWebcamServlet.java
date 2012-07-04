package com.tdil.milka.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class SaveWebcamServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4570360669857999122L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET Save webcam");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST Save webcam");
		String author = req.getParameter("authorBean.name");
		String email = req.getParameter("authorBean.email");
		String description = req.getParameter("description");
		InputStream input = req.getInputStream();
		FileOutputStream fout = new FileOutputStream("/home/mgodoy/temp/sign.jpg");
//		try {
//			IOUtils.copy(input, fout);
//		} catch (Exception e) {
//			fout.close();
//			e.printStackTrace();
//		}
		resp.getOutputStream().write("OK".getBytes());
	}
}
