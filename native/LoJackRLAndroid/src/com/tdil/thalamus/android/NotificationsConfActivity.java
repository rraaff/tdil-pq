package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.LoginResponse;
import com.tdil.thalamus.android.rest.model.NotificationBean;
import com.tdil.thalamus.android.rest.model.NotificationBeanCollection;
import com.tdil.thalamus.android.rest.model.NotificationConfBean;
import com.tdil.thalamus.android.rest.model.RESTResponse;
import com.tdil.thalamus.android.rest.model.RegisterAndroidBean;
import com.tdil.thalamus.android.utils.Login;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class NotificationsConfActivity extends LoJackLoggedActivity {
	

	public static final String NOTIFICATIONS_CONF = "NOTIFICATIONS_CONF";
	private NotificationConfBean confBean;
	private CheckBox lojack;
	private CheckBox car;
	private CheckBox home;
	private CheckBox pet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications_conf);
		lojack = (CheckBox) findViewById(R.id.lojackConf);
		car = (CheckBox) findViewById(R.id.carConf);
		home = (CheckBox) findViewById(R.id.homeConf);
		pet = (CheckBox) findViewById(R.id.petConf);
			
		Bundle extras = getIntent().getExtras();
		customizeActionBar(true);
		confBean = (NotificationConfBean)extras.get(NOTIFICATIONS_CONF);
			
		lojack.setChecked(confBean.getLojack() == 1);
		car.setChecked(confBean.getCar() == 1);
		home.setChecked(confBean.getHome() == 1);
		pet.setChecked(confBean.getPet() == 1);
		
		findViewById(R.id.updateButton).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Gson gson = new Gson();
				String json = gson.toJson(new NotificationConfBean(lojack.isChecked() ? 1 : 0, home.isChecked() ? 1 : 0, car.isChecked() ? 1 : 0, pet.isChecked() ? 1 : 0));
				new RESTClientTaskOpt<RESTResponse>(NotificationsConfActivity.this, HttpMethod.POST, getPostSaveObserver((LoJackActivity)NotificationsConfActivity.this), 
						RESTConstants.POST_NOTIFICATION_CONF,null,json, RESTResponse.class, false, false).executeSerial((Void) null);
			}
		});
			
	}

	protected IRestClientObserver getPostSaveObserver(LoJackActivity loJackActivity) {
		return new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>)task).getCastedResult();
				if (response.getOk()) {
					new AlertDialog.Builder(NotificationsConfActivity.this)
		               .setIcon(R.drawable.ic_launcher)
		               .setTitle("Modificacion de datos")
		               .setMessage("Se han modificado los datos")
		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                       public void onClick(DialogInterface dialog, int whichButton) {
		                    	   NotificationsConfActivity.this.finish();
		                       }
		               }).show();
				} else {
					error(task);
				}
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessageAndFinish(NotificationsConfActivity.this);
			}
		};
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}


	@Override
	public final boolean onOptionsItemSelected(MenuItem item) {
		return MenuLogic.handleOnOptionsItemSelected(this, item, true);
	}
	
	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.

	}

}
