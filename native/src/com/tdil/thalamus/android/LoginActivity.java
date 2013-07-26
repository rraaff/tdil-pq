package com.tdil.thalamus.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tdil.lojack.rl.R;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {

	/*public static final String URL_WEBSITE = "http://192.168.0.109:8180/LoJackWeb/";
	public static final String URL_ANDROID_VERSION = URL_WEBSITE
			+ "android_version.txt";*/
	
	 public static final String URL_WEBSITE = "http://www.lojack-app.com.ar/";
	 public static final String URL_ANDROID_VERSION = URL_WEBSITE +
	 "android_version.txt";

	/**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 */
	private static final String[] DUMMY_CREDENTIALS = new String[] {
			"foo@example.com:hello", "bar@example.com:world" };

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private WebView webView1;

	/**
	 * WebChromeClient subclass handles UI-related calls Note: think chrome as
	 * in decoration, not the Chrome browser
	 */
	public class GeoWebChromeClient extends WebChromeClient {
		@Override
		public void onGeolocationPermissionsShowPrompt(String origin,
				GeolocationPermissions.Callback callback) {
			// Always grant permission since the app itself requires location
			// permission and the user has therefore already granted it
			callback.invoke(origin, true, false);
		}
	}

	@Override
	public void onBackPressed() {
		// Pop the browser back stack or exit the activity
		if (webView1.canGoBack()) {
			webView1.goBack();
		} else {
			super.onBackPressed();
		}
	}

	// To handle "Back" key press event for WebView to go back to previous
	// screen.

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView1.canGoBack()) {
			webView1.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		webView1 = (WebView) findViewById(R.id.webView1);
		WebSettings webSettings = webView1.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setGeolocationEnabled(true);

		webView1.setWebChromeClient(new GeoWebChromeClient());
		webView1.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// check if the url matched the url loaded via webview.loadUrl()
				if (checkMatchedLoadedURL(url)) {
					return false;
				} else {
					LoginActivity.this.startActivity(new Intent(
							Intent.ACTION_VIEW, Uri.parse(url)));
					return true;
				}
			}

			private boolean checkMatchedLoadedURL(String url) {
				return url.contains("www.lojack-app.com.ar");
			}
		});
		try {
			// webView1.loadUrl("http://ec2-54-234-200-17.compute-1.amazonaws.com:8080/TUAFESTA_WEB/");
			webView1.loadUrl(URL_WEBSITE);
			// webView1.loadUrl("http://mgodoy-Satellite-P755:8180/TuaFestaWeb/");
			// Set up the login form.
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_go_home:
			webView1.loadUrl(URL_WEBSITE);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
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
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			try {
				// Simulate network access.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return false;
			}

			for (String credential : DUMMY_CREDENTIALS) {
				String[] pieces = credential.split(":");
				if (pieces[0].equals(mEmail)) {
					// Account exists, return true if the password matches.
					return pieces[1].equals(mPassword);
				}
			}

			// TODO: register the new account here.
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}
