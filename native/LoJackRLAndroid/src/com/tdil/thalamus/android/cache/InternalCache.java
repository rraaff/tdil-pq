package com.tdil.thalamus.android.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;

public class InternalCache {

	public static void put(Context context, String fileName, String content) {
		File file;
		FileOutputStream outputStream;
		try {
		    file = new File(context.getFilesDir(), fileName);
		    if (file.exists()) {
		    	file.delete();
		    }
		    file = new File(context.getFilesDir(), fileName);
		    outputStream = new FileOutputStream(file);
		    outputStream.write(content.getBytes());
		    outputStream.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static String get(Context context, String fileName, int days) {
		BufferedReader input = null;
		File file = null;
		StringBuffer buffer = new StringBuffer();
		try {
		    file = new File(context.getFilesDir(), fileName); // Pass getFilesDir() and "MyFile" to read file
		    if (!file.exists()) {
		    	return null;
		    }
		    Date lastModDate = new Date(file.lastModified());
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(lastModDate);
		    cal.add(Calendar.DATE, days);
		    Calendar today = Calendar.getInstance();
		    if (today.after(cal)) {
		    	return null;
		    }
		    input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		    String line;
		    while ((line = input.readLine()) != null) {
		        buffer.append(line);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
		return buffer.toString();
	}
}
