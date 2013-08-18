package com.tdil.thalamus.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.BeanMappingFunction;
import com.tdil.thalamus.android.gui.BeanMappingListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.DocumentTypeBean;
import com.tdil.thalamus.android.rest.model.DocumentTypeCollection;
import com.tdil.thalamus.android.rest.model.LoginResponse;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class HomeAlarmsActivity extends Activity {
	/**
	 * The default email to populate the email field with.
	 */

	private RESTClientTask mAuthTask = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home_alarms);

		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				 Gson gson = new Gson();
				 System.out.println(task.getResult());
				 /*DocumentTypeCollection col = gson.fromJson(task.getResult(), DocumentTypeCollection.class);
				 BeanMappingListAdapter<DocumentTypeBean> adapter = new BeanMappingListAdapter<DocumentTypeBean>(HomeAlarmsActivity.this,
					android.R.layout.simple_spinner_item, col.getList(), new BeanMappingFunction<DocumentTypeBean>() {
			 			public String key(DocumentTypeBean t) {return String.valueOf(t.getId());};
			 			@Override
			 			public String value(DocumentTypeBean t) {
			 				return t.getName();
			 			}
					});
					spinner.setAdapter(adapter);*/
			}
			@Override
			public void error(RESTClientTask task) {
				// TODO Auto-generated method stub
				
			}
		}, RESTConstants.ALARMS, null, null).execute((Void) null);
		

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
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_go_home:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
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
