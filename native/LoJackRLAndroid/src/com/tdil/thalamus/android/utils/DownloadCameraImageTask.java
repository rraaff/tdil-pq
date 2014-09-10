package com.tdil.thalamus.android.utils;

import java.io.ByteArrayInputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.tdil.thalamus.android.deprecated.HomeCameraActivity;
import com.tdil.thalamus.android.home.ActivityHomeCamera;

public class DownloadCameraImageTask extends AsyncTask<Void, Void, Bitmap> {
	
	private ActivityHomeCamera activity;
	private String url;
    private ImageView bmImage;
    
    public DownloadCameraImageTask(ActivityHomeCamera activity, ImageView bmImage, String url) {
    	this.activity = activity;
    	this.url = url;
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(Void ...a) {
        Bitmap mIcon11 = null;
        try {
        	ByteArrayInputStream frame = activity.getCamera().nextFrame();
        	if (frame != null) {
        		mIcon11 = BitmapFactory.decodeStream(frame);
        		frame.close();
        	}
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
    	if (result != null) {
	    	if (!activity.isFinishing()) {
		    	if (result != null) {
		    		bmImage.setImageBitmap(result);
		    	}
		    	new DownloadCameraImageTask(activity, bmImage, url).execute();
	    	}
    	}
    }
}