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
import com.tdil.thalamus.android.header.logic.HomeHeaderLogic;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.utils.Login;

public class ActivityHomeIndex extends HomeActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_index);
        HeaderLogic.installTabLogic(this);
        HomeHeaderLogic.installHomeMenuLogic(this);

        View v = findViewById(R.id.goAlarmsImageView);
        v.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								HomeHeaderLogic.loadAlarms(ActivityHomeIndex.this);
							}
						});
						
		v = findViewById(R.id.goLightsImageView);
        v.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								HomeHeaderLogic.loadLights(ActivityHomeIndex.this);
							}
						});
		v = findViewById(R.id.goCamerasImageView);
        v.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								HomeHeaderLogic.loadCameras(ActivityHomeIndex.this);
							}
						});
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
	@Override
	public boolean isAlarmsTab() {
		return false;
	}
	@Override
	public boolean isCamerasTab() {
		return false;
	}
	@Override
	public boolean isLightsTab() {
		return false;
	}
}