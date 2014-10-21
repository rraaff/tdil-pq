package com.tdil.lacerrajeria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

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
	
	@Override
	public final boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public final boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			Intent intent = new Intent(this.getBaseContext(), AboutActivity.class);
			this.startActivity(intent);
			return true;
		}
		if (id == R.id.action_services) {
			Intent intent = new Intent(this.getBaseContext(), ServicesActivity.class);
			this.startActivity(intent);
			return true;
		}
		if (id == R.id.action_location) {
			Intent intent = new Intent(this.getBaseContext(), LocationActivity.class);
			this.startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
