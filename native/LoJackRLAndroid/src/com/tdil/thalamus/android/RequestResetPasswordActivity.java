package com.tdil.thalamus.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.BeanMappingFunction;
import com.tdil.thalamus.android.gui.BeanMappingListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.DocumentTypeBean;
import com.tdil.thalamus.android.rest.model.DocumentTypeCollection;
import com.tdil.thalamus.android.rest.model.RESTResponse;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class RequestResetPasswordActivity extends LoJackActivity implements IRestClientObserver {

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
	private String mDocType;
	private String mDocNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request_reset);
		customizeActionBar();
		
		final Spinner spinner = (Spinner) findViewById(R.id.documentType);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				 DocumentTypeBean item = (DocumentTypeBean)arg0.getItemAtPosition(arg2);
				 RequestResetPasswordActivity.this.mDocType = String.valueOf(item.getId());
				
			}@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				 Gson gson = new Gson();
				 DocumentTypeCollection col = gson.fromJson(task.getResult(), DocumentTypeCollection.class);
				 BeanMappingListAdapter<DocumentTypeBean> adapter = new BeanMappingListAdapter<DocumentTypeBean>(RequestResetPasswordActivity.this,
					android.R.layout.simple_spinner_item, col.getList(), new BeanMappingFunction<DocumentTypeBean>() {
			 			public String key(DocumentTypeBean t) {return String.valueOf(t.getId());};
			 			@Override
			 			public String value(DocumentTypeBean t) {
			 				return t.getName();
			 			}
					});
					spinner.setAdapter(adapter);
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(RequestResetPasswordActivity.this);
			}
		}, RESTConstants.DOCUMENT_TYPES, null, null).execute((Void) null);
		
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
	
	@Override
	protected boolean mustUpdateMessages() {
		return false;
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
		mAuthTask = new RESTClientTask(this, HttpMethod.GET, this, RESTConstants.REQUEST_RESET_PASSWORD, new RestParams(RESTConstants.P_DOCUMENT_TYPE, mDocType).
				put(RESTConstants.P_DOCUMENT_NUMBER, mDocNumber), null);
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
            .setTitle("Contraseña")
            .setMessage("Te hemos enviado un E-Mail para que crees tu nueva clave. Si no lo recibiste, por favor revisá en correo no deseado.")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                 	   //this.
                    }
            }).show();
		} else {
			new AlertDialog.Builder(RequestResetPasswordActivity.this)
            .setIcon(R.drawable.ic_launcher)
            .setTitle("Contraseña")
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
