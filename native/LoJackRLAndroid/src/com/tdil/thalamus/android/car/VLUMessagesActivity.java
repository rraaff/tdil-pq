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
import android.widget.TextView;

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
	
	public static final String VLU_MESSAGES_LIST = "VLU_MESSAGES_LIST";
	private VLUMessagesCollection vluMessages;
	
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
		
		list = (ListView) findViewById(R.id.vluMessagesList);
		final TextView vluMessagesListLoading = (TextView)findViewById(R.id.vluMessagesListLoading);
		if (vluMessagesCount > 0) {
			vluMessages = (VLUMessagesCollection)extras.get(VLU_MESSAGES_LIST);
			CustomListViewValuesArr = new ArrayList<VLUDataDTO>(vluMessages.getVluData());
			Resources res = getResources();
			adapter = new VLULogListAdapter(VLUMessagesActivity.this,
					CustomListViewValuesArr, res);
			list.setAdapter(adapter);
		}
		new Thread() {
			
			public void run() {
				for (int i = 10; i <= 100; i+= 10) {
					VLUMessagesActivity.this.runOnUiThread(new UpdateProgressBarRunnable(vluMessagesListLoading, i));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				VLUMessagesActivity.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						loading.setVisibility(View.GONE);
						if (VLUMessagesActivity.this.vluMessagesCount > 0) {
							listContainer.setVisibility(View.VISIBLE);
						} else {
							empty.setVisibility(View.VISIBLE);
						}
					}
				});
			}
			
		}.start();
	}
	
	static final class UpdateProgressBarRunnable implements Runnable{
		private TextView vluMessagesListLoading;
		private int progress;
		
		public UpdateProgressBarRunnable(TextView vluMessagesListLoading, int progress) {
			super();
			this.vluMessagesListLoading = vluMessagesListLoading;
			this.progress = progress;
		}

		@Override
		public void run() {
			switch (progress) {
			case 10:
				vluMessagesListLoading.setBackgroundResource(R.drawable.fake_vlu_progressbar_010_per);
				break;
			case 20:
				vluMessagesListLoading.setBackgroundResource(R.drawable.fake_vlu_progressbar_020_per);
				break;
			case 30:
				vluMessagesListLoading.setBackgroundResource(R.drawable.fake_vlu_progressbar_030_per);
				break;
			case 40:
				vluMessagesListLoading.setBackgroundResource(R.drawable.fake_vlu_progressbar_040_per);
				break;
			case 50:
				vluMessagesListLoading.setBackgroundResource(R.drawable.fake_vlu_progressbar_050_per);
				break;
			case 60:
				vluMessagesListLoading.setBackgroundResource(R.drawable.fake_vlu_progressbar_060_per);
				break;
			case 70:
				vluMessagesListLoading.setBackgroundResource(R.drawable.fake_vlu_progressbar_070_per);
				break;
			case 80:
				vluMessagesListLoading.setBackgroundResource(R.drawable.fake_vlu_progressbar_080_per);
				break;
			case 90:
				vluMessagesListLoading.setBackgroundResource(R.drawable.fake_vlu_progressbar_090_per);
				break;
			case 100:
				vluMessagesListLoading.setBackgroundResource(R.drawable.fake_vlu_progressbar_100_per);
				break;
			default:
				break;
			}
			
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
