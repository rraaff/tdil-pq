package com.tdil.thalamus.android.car;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackActivity;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.UnCaughtException;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.VLUDataDTO;
import com.tdil.thalamus.android.rest.model.VLUMessagesCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class VLUMessagesActivity extends LoJackActivity {
	

	public static final String VLU_MESSAGES_COUNT = "VLU_MESSAGES_COUNT";
	private int vluMessagesCount = 0;
	
	ListView list;
	VLULogListAdapter adapter;
	public VLUMessagesActivity CustomListView = null;
	public ArrayList<VLUDataDTO> CustomListViewValuesArr = new ArrayList<VLUDataDTO>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));

		setContentView(R.layout.activity_vlu_messages);
		customizeActionBar();
		
		Bundle extras = getIntent().getExtras();
		vluMessagesCount = extras.getInt(VLU_MESSAGES_COUNT);
		
		final LinearLayout loading = (LinearLayout) findViewById(R.id.vluMessagesListLoadingContainer);
		final LinearLayout empty = (LinearLayout) findViewById(R.id.vluMessagesListEmptyContainer);
		final LinearLayout listContainer = (LinearLayout) findViewById(R.id.vluMessagesListContainer);
		
		if (vluMessagesCount > 0) {
			final Handler handler = new Handler(); 
	        Timer t = new Timer(); 
	        t.schedule(new TimerTask() { 
	                public void run() { 
	                    handler.post(new Runnable() { 
		                    public void run() { 
	
		                    	VLUMessagesActivity.this.runOnUiThread(new Runnable() {
		                    	    public void run() {
		                    	    	loading.setVisibility(View.GONE);
		                    	    	listContainer.setVisibility(View.VISIBLE);
		                    	    }
		                    	});
				             }
			            }); 
	                } 
			}, 2000); 
			list = (ListView) findViewById(R.id.vluMessagesList);
			new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
				@Override
				public void sucess(IRestClientTask task) {
					Gson gson = new Gson();
					
					VLUMessagesCollection col = gson.fromJson(task.getResult(),
							VLUMessagesCollection.class);
					CustomListViewValuesArr = new ArrayList<VLUDataDTO>(col.getVluData());
					Resources res = getResources();
					adapter = new VLULogListAdapter(VLUMessagesActivity.this,
							CustomListViewValuesArr, res);
					list.setAdapter(adapter);
				}
				
				@Override
				public void error(IRestClientTask task) {
					Messages.connectionErrorMessage(VLUMessagesActivity.this);
				}
			}, RESTConstants.VLU_MESSAGES, new RestParams(), null).execute((Void) null);
		} else {
			final Handler handler = new Handler(); 
	        Timer t = new Timer(); 
	        t.schedule(new TimerTask() { 
	                public void run() { 
	                    handler.post(new Runnable() { 
		                    public void run() { 
	
		                    	VLUMessagesActivity.this.runOnUiThread(new Runnable() {
		                    	    public void run() {
		                    	    	loading.setVisibility(View.GONE);
		                    	    	empty.setVisibility(View.VISIBLE);
		                    	    }
		                    	});
				             }
			            }); 
	                } 
			}, 2000); 
		}

		

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
