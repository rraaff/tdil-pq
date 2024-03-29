package com.tdil.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.tdil.cache.blob.BlobLocalData;
import com.tdil.cache.blob.BlobLocalDiskCache;
import com.tdil.log4j.LoggerProvider;


public class DownloadAttachmentController extends HttpServlet {

	private static final long serialVersionUID = -8356531321540585903L;
	
	private static final int DEFAULT_BUFFER_SIZE = 10240; // ..bytes = 10KB.
	private static final long DEFAULT_EXPIRE_TIME = 604800000L; // ..ms = 1
																// week.
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(DownloadAttachmentController.class);
	}
	
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// If-None-Match header should contain "*" or ETag. If so, then return 304.
		// Prepare some variables. The ETag is an unique identifier of the file.
		String type = req.getParameter("type");
		int id = Integer.parseInt(req.getParameter("id"));
		String ext = req.getParameter("ext");
		BlobLocalData blobLocalData = BlobLocalDiskCache.getBlob(type, id, 0, ext, null); // TODO usuario
		long length = blobLocalData.getFileSize();
		long lastModified = blobLocalData.lastModified();
		String eTag = type + "=" + id;
		String ifNoneMatch = req.getHeader("If-None-Match");
		if (ifNoneMatch != null && matches(ifNoneMatch, eTag)) {
			res.setHeader("ETag", eTag); // Required in 304.
			res.sendError(HttpServletResponse.SC_NOT_MODIFIED);
			return;
		}
		// If-Modified-Since header should be greater than LastModified. If so,
		// then return 304.
		// This header is ignored if any If-None-Match header is specified.
		long ifModifiedSince = req.getDateHeader("If-Modified-Since");
		if (ifNoneMatch == null && ifModifiedSince != -1 && ifModifiedSince + 1000 > lastModified) {
			res.setHeader("ETag", eTag); // Required in 304.
			res.sendError(HttpServletResponse.SC_NOT_MODIFIED);
			return;
		}
		res.setBufferSize(DEFAULT_BUFFER_SIZE);
		res.setHeader("Content-Length", String.valueOf(length));
		res.setHeader("ETag", eTag);
		res.setDateHeader("Last-Modified", lastModified);
		res.setDateHeader("Expires", System.currentTimeMillis() + DEFAULT_EXPIRE_TIME);
		// refactorizar a blobLocalData
		res.setContentType(blobLocalData.getMimeType());
		res.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("attachment-" + id + "." + ext, "UTF-8"));
		InputStream inputStream = null;
		try {
			inputStream = blobLocalData.getInputStream();
			IOUtils.copy(inputStream, res.getOutputStream());
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
	@Override public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	/**
	 * Returns true if the given accept header accepts the given value.
	 * 
	 * @param acceptHeader
	 *            The accept header.
	 * @param toAccept
	 *            The value to be accepted.
	 * @return True if the given accept header accepts the given value.
	 */
	private static boolean accepts(String acceptHeader, String toAccept) {
		String[] acceptValues = acceptHeader.split("\\s*(,|;)\\s*");
		Arrays.sort(acceptValues);
		return Arrays.binarySearch(acceptValues, toAccept) > -1
				|| Arrays.binarySearch(acceptValues, toAccept.replaceAll("/.*$", "/*")) > -1
				|| Arrays.binarySearch(acceptValues, "*/*") > -1;
	}

	/**
	 * Returns true if the given match header matches the given value.
	 * 
	 * @param matchHeader
	 *            The match header.
	 * @param toMatch
	 *            The value to be matched.
	 * @return True if the given match header matches the given value.
	 */
	private static boolean matches(String matchHeader, String toMatch) {
		String[] matchValues = matchHeader.split("\\s*,\\s*");
		Arrays.sort(matchValues);
		return Arrays.binarySearch(matchValues, toMatch) > -1 || Arrays.binarySearch(matchValues, "*") > -1;
	}
}
