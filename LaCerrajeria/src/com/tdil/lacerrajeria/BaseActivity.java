package com.tdil.lacerrajeria;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class BaseActivity extends ActionBarActivity {

	private static Tracker tracker;

	public Tracker getTracker() {
	    if (tracker == null) {
	      GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
	      tracker = analytics.newTracker(R.xml.app_tracker);
	    }
	    return tracker;
	  }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new Thread() {
			public void run() {
				trackInAnalytics();
			};
		}.start();
	}

	private void trackInAnalytics() {
		Tracker t = getTracker();
		// Set screen name.
        t.setScreenName(this.getClass().getName());
        // Send a screen view.
        t.send(new HitBuilders.AppViewBuilder().build());
	}
}
