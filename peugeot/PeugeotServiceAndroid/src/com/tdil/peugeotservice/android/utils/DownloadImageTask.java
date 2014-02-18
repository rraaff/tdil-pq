package com.tdil.peugeotservice.android.utils;

import java.io.InputStream;
import java.util.Map;
import java.util.WeakHashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	
    ImageView bmImage;
    
    private static Map<String, Bitmap> cache = new WeakHashMap<String, Bitmap>();

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = cache.get(urldisplay);
        if (mIcon11 != null) {
        	return mIcon11;
        }
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
            in.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        cache.put(urldisplay, mIcon11);
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}