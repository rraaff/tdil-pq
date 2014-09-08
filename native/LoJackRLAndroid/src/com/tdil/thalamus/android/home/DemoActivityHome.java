package com.tdil.thalamus.android.home;

import java.util.List;

import org.apache.http.cookie.Cookie;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
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
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.PreventActivity.GeoWebChromeClient;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.utils.Login;



public class DemoActivityHome extends LoJackWithProductMenuActivity {

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
			DemoActivityHome.this.setProgress(newProgress * 100);
			super.onProgressChanged(view, newProgress);
		}
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_lohome_main);
        HeaderLogic.installTabLogic(this);
        
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
			webView.loadUrl(ApplicationConfig.URL_HOME_NOT_CLIENT + "?apkToken=" + Login.getLoggedUser(this).getApkToken());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}