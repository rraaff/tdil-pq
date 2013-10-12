package com.tdil.thalamus.android;

import java.util.ArrayList;

import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.AlarmCollection;
import com.tdil.thalamus.android.utils.Messages;

public class MenuLogic {

	public static boolean handleOnOptionsItemSelected(Activity activity,
			MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_logout:
			RESTClientTask.httpClient = new DefaultHttpClient();
			new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(RESTClientTask task) {
				}
				@Override
				public void error(RESTClientTask task) {
				}
			}, RESTConstants.LOGOUT, null, null).execute((Void) null);
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
