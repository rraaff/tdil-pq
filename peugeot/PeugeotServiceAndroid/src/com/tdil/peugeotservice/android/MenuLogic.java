package com.tdil.peugeotservice.android;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;

public class MenuLogic {
	
	public static boolean handleOnOptionsItemSelected(final Activity activity,
			MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_logout:
			new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(IRestClientTask task) {
					Intent intent = new Intent(activity, LoginActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.putExtra("EXIT", true);
					RESTClientTask.recreateClient();
			    	activity.startActivity(intent);
			    	activity.finish();
				}
				@Override
				public void error(IRestClientTask task) {
					Intent intent = new Intent(activity, LoginActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.putExtra("EXIT", true);
			    	activity.startActivity(intent);
			    	activity.finish();
				}
			}, RESTConstants.LOGOUT, null, null).executeSerial((Void) null);

			return true;
//		case R.id.menu_action_home: 
//			FooterLogic.handleHomeAccess(activity, true);
//			return true;
		case R.id.menu_action_prevent:
			FooterLogic.handlePreventAccess(activity);
			return true;
		case R.id.menu_action_parkings:
			FooterLogic.handleParkingsAccess(activity);
			return true;
		case R.id.menu_update:
			activity.startActivity(new Intent(activity, UpdateActivity.class));
			return true;
		case R.id.menu_change_password:
			activity.startActivity(new Intent(activity, ChangePasswordActivity.class));
			return true;
		case R.id.menu_action_services:
			activity.startActivity(new Intent(activity, ServicesDashboardActivity.class));
			return true;
		case R.id.menu_action_emergency:
			activity.startActivity(new Intent(activity, UpdateEmergencyConfigActivity.class));
			return true;
		case R.id.menu_login:
			activity.finish();
			return true;
		case R.id.menu_back:
			activity.finish();
			return true;
		case R.id.secondaryMenu:
			return true;
//		case R.id.menu_register:
//			Intent intent1 = new Intent(activity, RegisterActivity.class);
//			activity.startActivity(intent1);
//	    	activity.finish();
//			return true;
		default:
			activity.finish();
			return false;
		}
	}

}
