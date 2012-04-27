package com.tdil.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

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
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(DownloadAttachmentController.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.setHeader("Pragma", "no-cache");
		//res.setDateHeader("Expires", 0);
		String type = req.getParameter("type");
		int id = Integer.parseInt(req.getParameter("id"));
		String ext = req.getParameter("ext");
		BlobLocalData blobLocalData = BlobLocalDiskCache.getBlob(type, id, 0, ext, null); //TODO usuario
		if (blobLocalData != null) {
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
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
}
