package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.car.VLUMessagesActivity;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
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
			customizeActionBar();
			
			Bundle extras = getIntent().getExtras();
			NotificationBeanCollection collection = null;
			if (extras == null || !extras.containsKey(NOTIFICATIONS)) {
				new RESTClientTaskOpt<NotificationBeanCollection>(this, HttpMethod.GET, getNotificationsObserver(this),
						RESTConstants.GET_NOTIFICATIONS, null, null, NotificationBeanCollection.class).executeSerial((Void) null);
			} else {
				collection = (NotificationBeanCollection)extras.get(NOTIFICATIONS);
				
				ArrayList<NotificationBean> CustomListViewValuesArr = new ArrayList<NotificationBean>(collection.getList());
				Resources res = getResources();
				NotificationsListAdapter adapter = new NotificationsListAdapter(this,
						CustomListViewValuesArr, res);
				list.setAdapter(adapter);
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
				((NotificationsActivity)activity).list.setAdapter(adapter);
			}
		};
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
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
