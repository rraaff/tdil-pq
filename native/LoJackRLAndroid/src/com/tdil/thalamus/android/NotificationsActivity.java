package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.model.NotificationBean;
import com.tdil.thalamus.android.rest.model.NotificationBeanCollection;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class NotificationsActivity extends LoJackActivity {
	

	public static final String NOTIFICATIONS = "NOTIFICATIONS";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_notifications);
		customizeActionBar();
		
		Bundle extras = getIntent().getExtras();
		NotificationBeanCollection collection = (NotificationBeanCollection)extras.get(NOTIFICATIONS);
		
		final ListView list = (ListView) findViewById(R.id.notificationsListView);
		
		ArrayList<NotificationBean> CustomListViewValuesArr = new ArrayList<NotificationBean>(collection.getList());
		Resources res = getResources();
		NotificationsListAdapter adapter = new NotificationsListAdapter(this,
				CustomListViewValuesArr, res);
		list.setAdapter(adapter);

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuLogic.handleOnOptionsItemSelected(this, item);
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
