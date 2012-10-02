package com.tdil.milka.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.tdil.cache.blob.BlobLocalDiskCache;

public class LoveWordCloudTagServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4570360669857999122L;
	public static Calendar lastGenerated = initCalendar();
	public static final int cachetimemillis = 1000 * 60 * 5;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}
	
	private static Calendar initCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MILLISECOND, cachetimemillis * -2);
		return cal;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

	private void doService(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		File file = new File(BlobLocalDiskCache.getDiskBlobLocation() + "/loveCloud.png");
		if (file == null || !file.exists() || isOutOfDate(file)) {
			LoveHateUtils.createLoveWordCloudTag();
		}
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(BlobLocalDiskCache.getDiskBlobLocation() + "/loveCloud.png");
			IOUtils.copy(inputStream, resp.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		
	}
	
	private synchronized boolean isOutOfDate(File file) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(lastGenerated.getTimeInMillis());
		cal.add(Calendar.MILLISECOND, cachetimemillis);
		if (cal.before(Calendar.getInstance())) {
			lastGenerated = Calendar.getInstance();
			return true;
		} else {
			return false;
		}
	}
}
