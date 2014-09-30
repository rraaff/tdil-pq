package com.tdil.thalamus.android.car.parkedmode;

import java.util.List;

import org.apache.http.cookie.Cookie;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.ApplicationConfig;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.home.DemoActivityHome.GeoWebChromeClient;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.utils.Login;



public class ActivityParkedModeNotClient extends ActionBarActivity {

	private WebView webView;
	
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
			ActivityParkedModeNotClient.this.setProgress(newProgress * 100);
			super.onProgressChanged(view, newProgress);
		}
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_pm_not_client);

    	webView = (WebView) findViewById(R.id.webView);
		
		List<Cookie> cookies = RESTClientTask.httpClient.getCookieStore().getCookies();
		//setContentView(webView);
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
			webView.loadUrl(ApplicationConfig.URL_PARKED_MODE_NOT_CLIENT + "?apkToken=" + Login.getLoggedUser(this).getApkToken());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
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

}