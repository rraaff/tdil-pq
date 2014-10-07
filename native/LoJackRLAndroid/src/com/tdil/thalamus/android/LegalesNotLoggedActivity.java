package com.tdil.thalamus.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
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
public class LegalesNotLoggedActivity extends LoJackNotLoggedActivity {

	// UI references.
	private WebView webView;

	/**
	 * The default email to populate the email field with.
	 */
	@Override
	public void onBackPressed() {
		// Pop the browser back stack or exit the activity
		if (webView.canGoBack()) {
			webView.goBack();
		} else {
			super.onBackPressed();
		}
	}
	
	@Override
	protected boolean mustUpdateMessages() {
		return false;
	}

	// To handle "Back" key press event for WebView to go back to previous
	// screen.

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

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			// TODO Auto-generated method stub
			LegalesNotLoggedActivity.this.setProgress(newProgress * 100);
			super.onProgressChanged(view, newProgress);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_legales_notlogged);
		customizeActionBar();

		webView = (WebView) findViewById(R.id.parkingsWebView);
		
//		setContentView(parkingsWebView);
		setProgressBarVisibility(true);
		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setGeolocationEnabled(true);
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setMessage("Cargando, por favor espere...");

		final Activity activity = this;
		webView.setWebChromeClient(new GeoWebChromeClient());
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				try {
					// check if the url matched the url loaded via webview.loadUrl()
					if (!pd.isShowing()) {
						pd.show();
					}
					webView.loadUrl(url);
					return false;
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				try {
					if (!pd.isShowing()) {
						pd.show();
					}
					super.onPageStarted(view, url, favicon);
				} catch (Exception e) {}
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				try {
					if (pd.isShowing()) {
						pd.dismiss();
					}
				} catch (Exception e) {}
			}

			private boolean checkMatchedLoadedURL(String url) {
				return url.contains("www.lojack-app.com.ar");
			}
		});
		try {
			webView.loadUrl(ApplicationConfig.URL_LEGALES_NOT_LOGGED);
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
