package com.tdil.peugeotservice.android;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.RESTResponse;
import com.tdil.peugeotservice.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class RequestResetPasswordActivity extends ActionBarActivity implements IRestClientObserver {

	/*
	 * public static final String URL_WEBSITE = "http://www.lojack-app.com.ar/";
	 * public static final String URL_ANDROID_VERSION = URL_WEBSITE +
	 * "android_version.txt";
	 */

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	private RESTClientTask mAuthTask = null;
	// Values for email and password at the time of the login attempt.
	private String mDocNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_request_reset);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		/*List<String> list = new ArrayList<String>();
		list.add("DNI");
		list.add("LE");
		SpinAdapter adapter = new SpinAdapter(this,
				android.R.layout.simple_spinner_item, list);
		spinner.setAdapter(adapter);*/

		findViewById(R.id.resetPasswordButton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						resetPassword();
					}
				});
		
		findViewById(R.id.resetPasswordBack).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						RequestResetPasswordActivity.this.finish();
					}
				});
	}

	public void resetPassword() {
		if (mAuthTask != null) {
			return;
		}

		// Store values at the time of the login attempt.
		mDocNumber = ((EditText) findViewById(R.id.documentNumber)).getText().toString();

		boolean cancel = false;
		View focusView = null;
		showProgress(true);
		mAuthTask = new RESTClientTask(this, HttpMethod.GET, this, RESTConstants.REQUEST_RESET_PASSWORD, new RestParams(RESTConstants.P_DOCUMENT_TYPE, 1).
				put(RESTConstants.P_DOCUMENT_NUMBER, mDocNumber), null);
		mAuthTask.executeSerial((Void) null);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_reset_password, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuLogic.handleOnOptionsItemSelected(this, item);
	}

	@Override
	public void error(IRestClientTask task) {
		Messages.connectionErrorMessage(RequestResetPasswordActivity.this);
		this.mAuthTask = null;
	}
	@Override
	public void sucess(IRestClientTask task) {
		Gson gson = new Gson();
		RESTResponse resp = gson.fromJson(task.getResult(), RESTResponse.class);
		if (resp.getOk()) {
			new AlertDialog.Builder(RequestResetPasswordActivity.this)
            .setIcon(R.drawable.ic_launcher)
            .setTitle("Clave")
            .setMessage("Te hemos enviado un E-Mail para que crees tu nueva clave. Si no lo recibiste, por favor revis� en correo no deseado.")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                 	   //this.
                    }
            }).show();
		} else {
			new AlertDialog.Builder(RequestResetPasswordActivity.this)
            .setIcon(R.drawable.ic_launcher)
            .setTitle("Clave")
            .setMessage("Ha ocurrido un error, verifique los datos ingresados")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                 	   //this.
                    }
            }).show();
		}
		this.mAuthTask = null;
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
