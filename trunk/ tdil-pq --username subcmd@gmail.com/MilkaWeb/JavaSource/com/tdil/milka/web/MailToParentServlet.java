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
		FlashListServletResult<MailToParentValueObject> data = MailToParentUtils.getMailToParent();
		List<MailToParentValueObject> answer = data.getList();
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
		for (MailToParentValueObject mailToParent : answer) {
			out.append("<item>");
			out.append("<id>").append(String.valueOf(mailToParent.getId())).append("</id>");
			out.append("<text>").append(CDATA_START_TAG);
			out.append(mailToParent.getName()).append(" - ");
			out.append(mailToParent.getTitle()).append(" - ");
			out.append(mailToParent.getDescription());
			out.append(CDATA_END_TAG).append("</text>");
			out.append("<thumb></thumb>");
			out.append("<view>").append(CDATA_START_TAG);
			out.append("./download.st?id=").append(String.valueOf(mailToParent.getIdApprovedData()));
			out.append("&type=PUBLIC&ext=").append(mailToParent.getExtApprovedData());
			out.append(CDATA_END_TAG).append("</view>");
			out.append("<link>").append(CDATA_START_TAG);
			out.append(mailToParent.getUrlLink() != null ? mailToParent.getUrlLink() : "");
			out.append(CDATA_END_TAG).append("</link>");
			out.append("<target>").append(CDATA_START_TAG);
			out.append(mailToParent.getUrlTarget());
			out.append(CDATA_END_TAG).append("</target>");
			out.append("<clickCounter>");
			out.append(String.valueOf(mailToParent.getIdClickCounter()));
			out.append("</clickCounter>");
			out.append("</item>");
			i = i + 1;
		}
		out.append("</items>");
		out.append("</config>");
		out.flush();
	}
}
