package com.tdil.milka.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdil.milka.model.valueobjects.MailToParentValueObject;

public class MailToParentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4570360669857999122L;
	public static final String CDATA_START_TAG = "<![CDATA[";
	public static final String CDATA_END_TAG = "]]>";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

	private void doService(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<MailToParentValueObject> answer = MailToParentUtils.getMailToParent();
		resp.setHeader("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
		resp.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		resp.setDateHeader ("Expires", -1);
		resp.setContentType("text/xml");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		out.append("<answer>");
		int i = 0;
		for (MailToParentValueObject mailToParent : answer) {
			out.append("<emailEnding>");
			out.append("<name>").append(CDATA_START_TAG);
			out.append(mailToParent.getName());
			out.append(CDATA_END_TAG).append("</name>");
			out.append("<title>").append(CDATA_START_TAG);
			out.append(mailToParent.getTitle());
			out.append(CDATA_END_TAG).append("</title>");
			out.append("<description>").append(CDATA_START_TAG);
			out.append(mailToParent.getDescription());
			out.append(CDATA_END_TAG).append("</description>");
			out.append("<url>").append(CDATA_START_TAG);
			out.append("./download.st?id=").append(String.valueOf(mailToParent.getIdApprovedData()));
			out.append("&type=PUBLIC&ext=").append(mailToParent.getExtApprovedData());
			out.append(CDATA_END_TAG).append("</url>");
			out.append("<clickCounter>");
			out.append(String.valueOf(mailToParent.getIdClickCounter()));
			out.append("</clickCounter>");
			out.append("</emailEnding>");
			i = i + 1;
		}
		out.append("</answer>");
		out.flush();
	}
}
