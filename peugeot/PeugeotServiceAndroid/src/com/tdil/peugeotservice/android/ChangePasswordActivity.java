package com.tdil.peugeotservice.android;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.ChangePasswordBean;
import com.tdil.peugeotservice.android.rest.model.LoginResponse;
import com.tdil.peugeotservice.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class ChangePasswordActivity extends PeugeotActivity implements
		IRestClientObserver, ValidationListener {

	/*
	 * public static final String URL_WEBSITE = "http://www.lojack-app.com.ar/";
	 * public static final String URL_ANDROID_VERSION = URL_WEBSITE +
	 * "android_version.txt";
	 */

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	private IRestClientTask mAuthTask = null;
	
	// Values for email and password at the time of the login attempt.
	
	@TextRule(order = 1, minLength = 4, maxLength = 10, message = "Ingrese la clave.")
	private TextView oldPassword;

	@TextRule(order = 2, minLength = 6, maxLength = 10, message = "Ingrese la clave.")
	@Password(order=3, message="clave")
	private TextView newPassword;

	@TextRule(order = 4, minLength = 6, maxLength = 10, message = "Ingrese la nueva clave.")
	@ConfirmPassword(order = 4, message= "Las claves no coinciden")
	private TextView retypePassword;
	private Validator validator;
	static final int DATE_DIALOG_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		
		validator = new Validator(this);
		validator.setValidationListener(this);

		setContentView(R.layout.activity_change_password);
		setTypeface(this, R.id.loadingInfoText);
		setTypeface(this, R.id.labelActualPass);
		setTypeface(this, R.id.oldPassword);
		setTypeface(this, R.id.labelNewPass);
		setTypeface(this, R.id.new_password);
		setTypeface(this, R.id.labelIdNumber);
		setTypeface(this, R.id.retype_new_password);
		setTypeface(this, R.id.updateButton);
		setTypeface(this, R.id.sendAlertButton);
		
		customizeActionBar();
		
		oldPassword = (EditText)findViewById(R.id.oldPassword);
		newPassword = (EditText)findViewById(R.id.new_password);
		retypePassword = (EditText)findViewById(R.id.retype_new_password);
		
		findViewById(R.id.updateButton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						validator.validate();
					}
				});
	}

	@Override
	public void preValidation() {
	}

	@Override
	public void onFailure(View failedView, Rule<?> failedRule) {
		String message = failedRule.getFailureMessage();
		if (failedView instanceof EditText) {
			failedView.requestFocus();
			((EditText) failedView).setError(message);
		} else {
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onSuccess() {
		changePassword();
	}

	@Override
	public void onValidationCancelled() {
	}

	protected void changePassword() {
		ChangePasswordBean changePasswordBean = new ChangePasswordBean();
		changePasswordBean.setOldpassword(oldPassword.getText().toString());
		changePasswordBean.setNewPassword(newPassword.getText().toString());
		changePasswordBean.setConfirmNewPassword(retypePassword.getText().toString());
		Gson gson = new Gson();
		String json = gson.toJson(changePasswordBean);
		new RESTClientTask(this, HttpMethod.POST, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				// TODO analizar la respuesta para mostrar un mensaje u otro

				new AlertDialog.Builder(ChangePasswordActivity.this)
						.setIcon(R.drawable.ic_launcher)
						.setTitle("Cambio de clave")
						.setMessage("Se ha cambiado la clave")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										ChangePasswordActivity.this.finish();
									}
								}).show();
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(ChangePasswordActivity.this);
			}
		}, RESTConstants.CHANGE_PASSWORD, new RestParams(), json)
				.executeSerial((Void) null);
	}


	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_full, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuLogic.handleOnOptionsItemSelected(this, item);
	}

	@Override
	public void error(IRestClientTask task) {
		Messages.connectionErrorMessage(ChangePasswordActivity.this);
		this.mAuthTask = null;
	}

	@Override
	public void sucess(IRestClientTask task) {
		Gson gson = new Gson();
		LoginResponse resp = gson.fromJson(task.getResult(),
				LoginResponse.class);
		if (resp.getLogged()) {
			Intent intent = new Intent(this, IndexActivity.class);
			startActivity(intent);
			finish();
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
