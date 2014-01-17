package com.tdil.thalamus.android;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.TextView;

import com.tdil.lojack.rl.R;

public class CheckForUpdateActivity extends Activity {
	
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_for_update);
		mHandler = new Handler();
		try {
			int curVersion = getPackageManager().getPackageInfo("com.tdil.lojack.rl", 0).versionCode;
			String curVersionName = getPackageManager().getPackageInfo("com.tdil.lojack.rl", 0).versionName;
			((TextView)findViewById(R.id.appVersion)).setText(curVersion + "-" + curVersionName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkUpdate.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_for_update, menu);
		return true;
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
    	// TODO Auto-generated method stub
    	super.onConfigurationChanged(newConfig);
    }

	 /* This Thread checks for Updates in the Background */
    private Thread checkUpdate = new Thread() {
        public void run() {
            try {
                URL updateURL = new URL(LoginActivity.URL_ANDROID_VERSION);                
                URLConnection conn = updateURL.openConnection(); 
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                ByteArrayBuffer baf = new ByteArrayBuffer(50);
                
                int current = 0;
                while((current = bis.read()) != -1){
                     baf.append((byte)current);
                }

                /* Convert the Bytes read to a String. */
                final String s = new String(baf.toByteArray());         
                
                /* Get current Version Number */
                int curVersion = getPackageManager().getPackageInfo("com.tdil.lojack.rl", 0).versionCode;
                int newVersion = Integer.valueOf(s);
                
                /* Is a higher version than the current already out? */
                if (newVersion > curVersion) {
                    /* Post a Handler for the UI to pick up and open the Dialog */
                    mHandler.post(showUpdate);
                } else {
                	// activate activity
                	Intent intent = new Intent(CheckForUpdateActivity.this, LoginActivity.class);
                	startActivity(intent);
                	finish();
                }
            } catch (Exception e) {
            	e.printStackTrace();
            	 mHandler.post(showError);
            }
        }
    };
    
    /* This Runnable creates a Dialog and asks the user to open the Market */ 
    private Runnable showError = new Runnable(){
           public void run(){
        	   new AlertDialog.Builder(CheckForUpdateActivity.this)
               .setIcon(R.drawable.ic_launcher)
               .setTitle("Atenci�n")
               .setMessage("No se pudo establecer correctamente la conexi�n")
               .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int whichButton) {
                             CheckForUpdateActivity.this.finish();
                       }
               }).show();
           }
    };    

    /* This Runnable creates a Dialog and asks the user to open the Market */ 
    private Runnable showUpdate = new Runnable(){
           public void run(){
            new AlertDialog.Builder(CheckForUpdateActivity.this)
            .setIcon(R.drawable.ic_launcher)
            .setTitle("Actualizar")
            .setMessage("Hay una actualizaci�n disponible\\n\\n�Desea actualizarla ahora?")
            .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                            /* User clicked OK so do some stuff */
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pname:lojack.real.life"));
                            startActivity(intent);
                    }
            })
            .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                            /* User clicked Cancel */
                    	CheckForUpdateActivity.this.finish();
                    }
            })
            .show();
           }
    };    
}
