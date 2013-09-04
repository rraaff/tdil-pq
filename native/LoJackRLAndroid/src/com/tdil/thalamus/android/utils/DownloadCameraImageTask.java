package com.tdil.thalamus.android.utils;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import com.tdil.thalamus.android.HomeCameraActivity;
import com.tdil.thalamus.android.rest.client.RESTClientTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DownloadCameraImageTask extends AsyncTask<Void, Void, Bitmap> {
	
	private HomeCameraActivity activity;
	private String url;
    private ImageView bmImage;
    
    public DownloadCameraImageTask(HomeCameraActivity activity, ImageView bmImage, String url) {
    	this.activity = activity;
    	this.url = url;
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(Void ...a) {
        Bitmap mIcon11 = null;
        try {
        	HttpGet get = new HttpGet(this.url);
        	HttpResponse httpResponse = RESTClientTask.httpClient.execute(get);
            InputStream in = httpResponse.getEntity().getContent();
            mIcon11 = BitmapFactory.decodeStream(in);
            in.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
    	if (!activity.isFinishing()) {
	    	if (result != null) {
	    		bmImage.setImageBitmap(result);
	    	}
	    	new DownloadCameraImageTask(activity, bmImage, url).execute();
    	}
    }
}