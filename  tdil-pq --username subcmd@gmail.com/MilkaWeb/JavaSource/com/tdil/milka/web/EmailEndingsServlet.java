package com.tdil.milka.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdil.milka.model.valueobjects.EmailEndingsValueObject;
import com.tdil.milka.model.valueobjects.MailToParentValueObject;

public class EmailEndingsServlet extends HttpServlet {

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
		FlashListServletResult<EmailEndingsValueObject> data = EmailEndingsUtils.getEmailEndings();
		List<EmailEndingsValueObject> answer = data.getList();
		resp.setHeader("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
		resp.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		resp.setDateHeader ("Expires", -1);
		resp.setContentType("text/xml");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		out.append("<config>");
		out.append(data.getRawInsert());
		int i = 0;
		out.append("<items>");
		for (EmailEndingsValueObject emailEndingsValueObject : answer) {
			out.append("<item>");
			out.append("<text>").append(CDATA_START_TAG);
			out.append(emailEndingsValueObject.getName()).append(" - ");
			out.append(emailEndingsValueObject.getTitle()).append(" - ");
			out.append(emailEndingsValueObject.getDescription());
			out.append(CDATA_END_TAG).append("</text>");
			out.append("<thumb></thumb>");
			out.append("<view>").append(CDATA_START_TAG);
			out.append("./download.st?id=").append(String.valueOf(emailEndingsValueObject.getIdApprovedData()));
			out.append("&type=PUBLIC&ext=").append(emailEndingsValueObject.getExtApprovedData());
			out.append(CDATA_END_TAG).append("</view>");
			out.append("<link>").append(CDATA_START_TAG);
			out.append(emailEndingsValueObject.getUrlLink());
			out.append(CDATA_END_TAG).append("</link>");
			out.append("<target>").append(CDATA_START_TAG);
			out.append(emailEndingsValueObject.getUrlTarget());
			out.append(CDATA_END_TAG).append("</target>");
			out.append("<clickCounter>");
			out.append(String.valueOf(emailEndingsValueObject.getIdClickCounter()));
			out.append("</clickCounter>");
			out.append("</item>");
			i = i + 1;
		}
		out.append("</items>");
		out.append("</config>");
		out.flush();
	}
}
