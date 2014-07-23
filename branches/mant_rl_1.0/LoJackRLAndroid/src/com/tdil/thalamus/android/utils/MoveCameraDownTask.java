package com.tdil.thalamus.android.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.tdil.thalamus.android.camera.IPCamera;

public class MoveCameraDownTask extends AsyncTask<Void, Void, Void> {
	
	private IPCamera camera;
    
    public MoveCameraDownTask(IPCamera camera) {
    	this.camera = camera;
    }

    protected Void doInBackground(Void ...a) {
        try {
        	camera.down();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Bitmap result) {
    }
}