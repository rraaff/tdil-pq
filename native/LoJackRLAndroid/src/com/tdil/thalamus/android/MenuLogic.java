package com.tdil.thalamus.android;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.home.DemoActivityHome;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.utils.Login;

public class MenuLogic {
	
	public static boolean handleOnOptionsItemSelected(final Activity activity,
			MenuItem item, boolean defaultIndex) {
		switch (item.getItemId()) {
		case R.id.menu_logout:
			new RESTClientTask(activity, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(IRestClientTask task) {
					Login.getLoggedUser(activity).setLogged(false);
					Login.save(activity);
					Intent intent = new Intent(activity, LoginActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.putExtra("EXIT", true);
					RESTClientTask.recreateClient();
			    	activity.startActivity(intent);
			    	activity.finish();
				}
				@Override
				public void error(IRestClientTask task) {
					Login.getLoggedUser(activity).setLogged(false);
					Login.save(activity);
					Intent intent = new Intent(activity, LoginActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.putExtra("EXIT", true);
			    	activity.startActivity(intent);
			    	activity.finish();
				}
			}, RESTConstants.LOGOUT, null, null).executeSerial((Void) null);

			return true;
		case R.id.menu_action_user: 
			return true;
		case R.id.menu_action_home: 
			HeaderLogic.handleHomeAccess(activity, true);
			return true;
		case R.id.menu_action_prevent:
			HeaderLogic.handlePreventAccess(activity);
			return true;
		case R.id.menu_action_pets:
			HeaderLogic.handlePetsAccess(activity);
			return true;
		case R.id.menu_action_parked_mode:
			HeaderLogic.handleParkedModeAccess(activity);
			return true;
		case R.id.menu_action_club_lj:
			HeaderLogic.handleClubLoJackAccess(activity);
			return true;
		case R.id.menu_action_parkings:
			HeaderLogic.handleParkingsAccess(activity);
			return true;
		case R.id.menu_totest:
			Intent intentDemos = new Intent(activity, DemoActivityHome.class);
			activity.startActivity(intentDemos);
			activity.finish();
			return true;
		case R.id.menu_totestCar:
			Intent intentDemosCar = new Intent(activity, DemoActivityCars.class);
			activity.startActivity(intentDemosCar);
			activity.finish();
			return true;
		case R.id.menu_update:
			activity.startActivity(new Intent(activity, UpdateActivity.class));
			return true;
		case R.id.menu_change_password:
			activity.startActivity(new Intent(activity, ChangePasswordActivity.class));
			return true;
		case R.id.menu_login:
			activity.finish();
			return true;
		case R.id.menu_back:
			activity.finish();
			return true;
		case R.id.menu_action_legales_not_logged:
			activity.startActivity(new Intent(activity, LegalesNotLoggedActivity.class));
			return true;
		case R.id.menu_action_legales:
			activity.startActivity(new Intent(activity, LegalesLoggedActivity.class));
			return true;
		//case R.id.secondaryMenu:
			//return true;
		case R.id.menu_register:
			Intent intent1 = new Intent(activity, RegisterActivity.class);
			activity.startActivity(intent1);
	    	activity.finish();
			return true;
		default:
			if (defaultIndex) {
				Intent intentIndex = new Intent(activity, IndexActivity.class);
				activity.startActivity(intentIndex);
		    	activity.finish();
				return true;
			} else {
				activity.finish();
				return false;
			}
		}
	}

}
