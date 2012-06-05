package com.tdil.milka.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tdil.milka.model.valueobjects.PostItValueObject;

public class PostItServlet extends HttpServlet {

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
		List<PostItValueObject> data = PostItUtils.getPostIts();
		resp.setHeader("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
		resp.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		resp.setDateHeader ("Expires", -1);
		resp.setContentType("text/xml");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		out.append("<data>");
		for (PostItValueObject postItValueObject : data) {
			out.append("<item color=\"").append(postItValueObject.getColor()).append("\">");
			out.append("<id>").append(String.valueOf(postItValueObject.getId())).append("</id>");
			out.append("<title>").append(CDATA_START_TAG);
			out.append(postItValueObject.getTitle() != null ? postItValueObject.getTitle() : "" );
			out.append(CDATA_END_TAG).append("</title>");
			out.append("<description>").append(CDATA_START_TAG);
			out.append(postItValueObject.getDescription() != null ? postItValueObject.getDescription() : "" );
			out.append(CDATA_END_TAG).append("</description>");
			out.append("<largeImagePath>");
			out.append(CDATA_START_TAG).append("./download.st?id=").append(String.valueOf(postItValueObject.getIdImage()));
			out.append("&type=PUBLIC&ext=").append(postItValueObject.getExtImage());
			out.append(CDATA_END_TAG).append("</largeImagePath>");
			out.append("<thumbImagePath>");
			out.append(CDATA_START_TAG).append("./download.st?id=").append(String.valueOf(postItValueObject.getIdThumb()));
			out.append("&type=PUBLIC&ext=").append(postItValueObject.getExtThum());
			out.append(CDATA_END_TAG).append("</thumbImagePath>");
			out.append("<destinationURL target=\"").append(postItValueObject.getUrlTarget()).append("\">");
			out.append(CDATA_START_TAG).append(postItValueObject.getUrlLink() != null ? postItValueObject.getUrlLink() : "").append(CDATA_END_TAG);
			out.append("</destinationURL>");
			out.append("</item>");
		}
		out.append("</data>");
		out.flush();
	}
}
