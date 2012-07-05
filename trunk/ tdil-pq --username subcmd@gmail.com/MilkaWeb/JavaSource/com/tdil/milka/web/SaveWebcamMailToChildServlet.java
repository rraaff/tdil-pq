package com.tdil.milka.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdil.milka.struts.forms.MailToChildForm;
import com.tdil.struts.forms.UploadData;

public class SaveWebcamMailToChildServlet extends HttpServlet {

	private static int MAX_FILE_SIZE = 1000000;
	
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
		MailToChildForm goodMorningForm = (MailToChildForm)req.getSession().getAttribute("MailToChildWCForm");
		if (goodMorningForm == null) {
			goodMorningForm = new MailToChildForm();
			req.getSession().setAttribute("MailToChildWCForm", goodMorningForm);
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = req.getInputStream();
		int read;
		int count = 0;
		while ((read = in.read()) != -1) {
			out.write(read); //copy streams
			count = count + 1;
			if (count > MAX_FILE_SIZE) {
				resp.getOutputStream().write("ERR".getBytes());
				return;
			}
		}	
		goodMorningForm.setPhoto(new UploadData("webcam.jpg", out.toByteArray(), true));
		resp.getOutputStream().write("OK".getBytes());
	}
}
