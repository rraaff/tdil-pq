package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.LoginResponse;
import com.tdil.thalamus.android.rest.model.NotificationBean;
import com.tdil.thalamus.android.rest.model.NotificationBeanCollection;
import com.tdil.thalamus.android.utils.Login;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class NotificationsActivity extends LoJackLoggedActivity {
	

	public static final String NOTIFICATIONS = "NOTIFICATIONS";
	private ListView list;
	private TextView noNotifications;
	private boolean fromNotifications = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!Login.getLoggedUser(this).getLogged()) {
			Intent intent = new Intent(this.getBaseContext(), LoginActivity.class);
			this.startActivity(intent);
			this.finish();
		} else {
			setContentView(R.layout.activity_notifications);
			list = (ListView) findViewById(R.id.notificationsListView);
			noNotifications = (TextView)findViewById(R.id.noNotifications);
			LoginResponse login = Login.getLoggedUser(this);
			login.setMessagesUnread(false);
			Login.setLoggedUser(this, login);
			
			Bundle extras = getIntent().getExtras();
			NotificationBeanCollection collection = null;
			customizeActionBar(true);
			if (extras == null || !extras.containsKey(NOTIFICATIONS)) {
				fromNotifications = true;
				new RESTClientTaskOpt<NotificationBeanCollection>(this, HttpMethod.GET, getNotificationsObserver(this),
						RESTConstants.GET_NOTIFICATIONS, null, null, NotificationBeanCollection.class).executeSerial((Void) null);
			} else {
				collection = (NotificationBeanCollection)extras.get(NOTIFICATIONS);
				
				ArrayList<NotificationBean> CustomListViewValuesArr = new ArrayList<NotificationBean>(collection.getList());
				Resources res = getResources();
				NotificationsListAdapter adapter = new NotificationsListAdapter(this,
						CustomListViewValuesArr, res);
				list.setAdapter(adapter);
				if (collection.getList().size() == 0) {
					list.setVisibility(View.GONE);
					noNotifications.setVisibility(View.VISIBLE);
				} else {
					list.setVisibility(View.VISIBLE);
					noNotifications.setVisibility(View.GONE);
				}
			}
			
		}
	}
	
	public static IRestClientObserver getNotificationsObserver(final LoJackActivity activity) {
		return new LoJackRestClientObserver(activity) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				NotificationBeanCollection response = ((RESTClientTaskOpt<NotificationBeanCollection>) restClientTask).getCastedResult();
				ArrayList<NotificationBean> CustomListViewValuesArr = new ArrayList<NotificationBean>(response.getList());
				Resources res = activity.getResources();
				NotificationsListAdapter adapter = new NotificationsListAdapter(activity,
						CustomListViewValuesArr, res);
				NotificationsActivity notificationsActivity = (NotificationsActivity)activity;
				notificationsActivity.list.setAdapter(adapter);
				
				if (response.getList().size() == 0) {
					notificationsActivity.list.setVisibility(View.GONE);
					notificationsActivity.noNotifications.setVisibility(View.VISIBLE);
				} else {
					notificationsActivity.list.setVisibility(View.VISIBLE);
					notificationsActivity.noNotifications.setVisibility(View.GONE);
				}
			}
		};
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onBackPressed() {
		if (!fromNotifications) {
			super.onBackPressed();
		} else {
			Intent intent = new Intent(this.getBaseContext(), IndexActivity.class);
			this.startActivity(intent);
			this.finish();
		}
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
