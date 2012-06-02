package com.tdil.milka.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdil.milka.model.LoveNicknames;

public class LoveNicknamesServlet extends HttpServlet {

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
		List<LoveNicknames> data = LoveNicknamesUtils.getLoveNicknames();
		resp.setHeader("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
		resp.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		resp.setDateHeader ("Expires", -1);
		resp.setContentType("text/xml");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		out.append("<items>");
		for (LoveNicknames loveNicknames : data) {
			out.append("<item>");
			out.append("<text>").append(CDATA_START_TAG);
			out.append(loveNicknames.getOriginaltext());
			out.append(CDATA_END_TAG).append("</text>");
			out.append("<position>").append(loveNicknames.getPosition()).append("</position>");
			out.append("<sex>").append(loveNicknames.getSex()).append("</sex>");
			out.append("<link>").append(CDATA_START_TAG);
			out.append("");
			out.append(CDATA_END_TAG).append("</link>");
			out.append("<target>").append(CDATA_START_TAG);
			out.append("_self");
			out.append(CDATA_END_TAG).append("</target>");
			out.append("<clickCounter>");
			out.append(String.valueOf(loveNicknames.getIdClickCounter()));
			out.append("</clickCounter>");
			out.append("</item>");
		}
		out.append("</items>");
		out.flush();
	}
}
