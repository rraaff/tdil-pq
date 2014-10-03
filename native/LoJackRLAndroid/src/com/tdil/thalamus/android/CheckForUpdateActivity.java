package com.tdil.thalamus.android;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import com.tdil.lojack.rl.R;

public class CheckForUpdateActivity extends LoJackActivity {
	
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_for_update);
		mHandler = new Handler();
		checkUpdate.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_for_update, menu);
		return true;
	}
	
	@Override
	protected boolean mustUpdateMessages() {
		return false;
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
                URL updateURL = new URL(ApplicationConfig.URL_ANDROID_VERSION);                
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
                	StartHistory appStart = checkAppStart();
                	if (!appStart.isClient()) {
                		Intent intent = new Intent(CheckForUpdateActivity.this, FirstAccessActivity.class);
	                	startActivity(intent);
	                	finish();
                	} else {
	                	// activate activity
	                	Intent intent = new Intent(CheckForUpdateActivity.this, LoginActivity.class);
	                	intent.putExtra(LoginActivity.FROM_LAUNCH, LoginActivity.FROM_LAUNCH);
	                	startActivity(intent);
	                	finish();
                	}
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
               .setTitle("Atención")
               .setMessage("No se pudo establecer correctamente la conexión")
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
            .setMessage("Hay una actualización disponible.¿Desea actualizarla ahora?")
            .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        /* User clicked OK so do some stuff */
                        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pname:lojack.real.life"));
                    	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.tdil.lojack.rl"));
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
    
    public class StartHistory {
    	
    	private AppStart appStart;
    	private boolean client;
    	
		public StartHistory(AppStart appStart, boolean client) {
			super();
			this.appStart = appStart;
			this.client = client;
		}
		
		public AppStart getAppStart() {
			return appStart;
		}
		public void setAppStart(AppStart appStart) {
			this.appStart = appStart;
		}
		public boolean isClient() {
			return client;
		}
		public void setClient(boolean client) {
			this.client = client;
		}
    	
    	
    }
    
    /**
     * Distinguishes different kinds of app starts: <li>
     * <ul>
     * First start ever ({@link #FIRST_TIME})
     * </ul>
     * <ul>
     * First start in this version ({@link #FIRST_TIME_VERSION})
     * </ul>
     * <ul>
     * Normal app start ({@link #NORMAL})
     * </ul>
     * 
     * @author schnatterer
     * 
     */
    public enum AppStart {
        FIRST_TIME, FIRST_TIME_VERSION, NORMAL;
    }

    /**
     * The app version code (not the version name!) that was used on the last
     * start of the app.
     */
    private static final String LAST_APP_VERSION = "last_app_version";
    public static final String IS_CLIENT = "is_client";
    
    /**
     * Caches the result of {@link #checkAppStart()}. To allow idempotent method
     * calls.
     */
    private static AppStart appStart = null;

    /**
     * Finds out started for the first time (ever or in the current version).
     * 
     * @return the type of app start
     */
    public StartHistory checkAppStart() {
    	boolean client = false;
        if (appStart == null) {
            try {
            	final String PREFS_NAME = "RLPrefsFile";
            	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            	int currentVersionCode = getPackageManager().getPackageInfo(
    					"com.tdil.lojack.rl", 0).versionCode;
                int lastVersionCode = settings.getInt(
                        LAST_APP_VERSION, -1);
                // String versionName = pInfo.versionName;
                appStart = checkAppStart(currentVersionCode, lastVersionCode);
                client = settings.getBoolean(IS_CLIENT, false);
                // Update version in preferences
                settings.edit()
                        .putInt(LAST_APP_VERSION, currentVersionCode).commit();
            } catch (NameNotFoundException e) {
            	e.printStackTrace();
            	appStart = AppStart.NORMAL;
            }
        }
        return new StartHistory(appStart, client);
    }

    public AppStart checkAppStart(int currentVersionCode, int lastVersionCode) {
        if (lastVersionCode == -1) {
            return AppStart.FIRST_TIME;
        } else if (lastVersionCode < currentVersionCode) {
            return AppStart.FIRST_TIME_VERSION;
        } else if (lastVersionCode > currentVersionCode) {
//            Log.w(Constants.LOG, "Current version code (" + currentVersionCode
//                    + ") is less then the one recognized on last startup ("
//                    + lastVersionCode
//                    + "). Defenisvely assuming normal app start.");
            return AppStart.NORMAL;
        } else {
            return AppStart.NORMAL;
        }
    }
}
