package com.tdil.thalamus.android.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.tdil.thalamus.android.rest.client.RESTClientTask;

public class MoveCameraTask extends AsyncTask<Void, Void, Void> {
	
	private String url;
	private String dir;
    
    public MoveCameraTask(String url, String dir) {
    	this.url = url;
    	this.dir = dir;
    }

    protected Void doInBackground(Void ...a) {
        try {
        	HttpGet get = new HttpGet(this.url + "&dir=" + dir);
        	HttpResponse httpResponse = RESTClientTask.httpClient.execute(get);
            InputStream in = httpResponse.getEntity().getContent();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(
					in, "iso-8859-1"), 8);
			StringBuilder sBuilder = new StringBuilder();
			String line = null;
			while ((line = bReader.readLine()) != null) {
				sBuilder.append(line + "\n");
			}
            in.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Bitmap result) {
    }
}