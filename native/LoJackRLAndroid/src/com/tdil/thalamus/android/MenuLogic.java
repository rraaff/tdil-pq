package com.tdil.thalamus.android;

import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.client.RESTClientTask;

public class MenuLogic {

	public static boolean handleOnOptionsItemSelected(Activity activity,
			MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_logout:
			RESTClientTask.httpClient = new DefaultHttpClient();
			// TODO logout via rest para no dejar sessiones activas
			Intent intent = new Intent(activity, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("EXIT", true);
	    	activity.startActivity(intent);
	    	activity.finish();
			return true;
		default:
			return activity.onOptionsItemSelected(item);
		}
	}

}
