package com.tdil.thalamus.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.tdil.lojack.rl.R;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {

	public static final String URL_WEBSITE = "http://192.168.0.107:8180/LoJackWeb/";
	public static final String URL_ANDROID_VERSION = URL_WEBSITE
			+ "android_version.txt";

	/*
	 * public static final String URL_WEBSITE = "http://www.lojack-app.com.ar/";
	 * public static final String URL_ANDROID_VERSION = URL_WEBSITE +
	 * "android_version.txt";
	 */

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	private UserLoginTask mAuthTask = null;
	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		Spinner spinner = (Spinner) findViewById(R.id.documentType);
		List<String> list = new ArrayList<String>();
		list.add("DNI");
		list.add("LE");
		SpinAdapter adapter = new SpinAdapter(this,
				android.R.layout.simple_spinner_item, list);
		spinner.setAdapter(adapter);

		findViewById(R.id.button1).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}

	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Store values at the time of the login attempt.
		mEmail = "1:25270160";
		mPassword = "123456";

		boolean cancel = false;
		View focusView = null;
		showProgress(true);
		mAuthTask = new UserLoginTask();
		mAuthTask.execute((Void) null);
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

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog progressDialog = new ProgressDialog(
				LoginActivity.this);
		InputStream inputStream = null;
		String result = "";

		protected void onPreExecute() {
			progressDialog.setMessage("Downloading your data...");
			progressDialog.show();
			progressDialog.setOnCancelListener(new OnCancelListener() {
				public void onCancel(DialogInterface arg0) {
					UserLoginTask.this.cancel(true);
				}
			});
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			String url_select = "http://192.168.0.107:8180/LoJackWeb/rest/users/login?documentType=1&documentNumber=12&password=12";

			ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

			try {
				// Set up HTTP post

				// HttpClient is more then less deprecated. Need to change to
				// URLConnection
				HttpClient httpClient = new DefaultHttpClient();

				HttpGet httpPost = new HttpGet(url_select);
				//httpPost.setEntity(new UrlEncodedFormEntity(param));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();

				// Read content & Log
				inputStream = httpEntity.getContent();
			} catch (UnsupportedEncodingException e1) {
				Log.e("UnsupportedEncodingException", e1.toString());
				e1.printStackTrace();
			} catch (ClientProtocolException e2) {
				Log.e("ClientProtocolException", e2.toString());
				e2.printStackTrace();
			} catch (IllegalStateException e3) {
				Log.e("IllegalStateException", e3.toString());
				e3.printStackTrace();
			} catch (IOException e4) {
				Log.e("IOException", e4.toString());
				e4.printStackTrace();
			}
			// Convert response to string using String Builder
			try {
				BufferedReader bReader = new BufferedReader(
						new InputStreamReader(inputStream, "iso-8859-1"), 8);
				StringBuilder sBuilder = new StringBuilder();

				String line = null;
				while ((line = bReader.readLine()) != null) {
					sBuilder.append(line + "\n");
				}

				inputStream.close();
				result = sBuilder.toString();

			} catch (Exception e) {
				Log.e("StringBuilding & BufferedReader",
						"Error converting result " + e.toString());
			}

			// TODO: register the new account here.
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			try {
				JSONObject jObject = new JSONObject(result);
				System.out.println(jObject.toString(2));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.progressDialog.dismiss();
		}

		@Override
		protected void onCancelled() {
			showProgress(false);
		}
	}
}
