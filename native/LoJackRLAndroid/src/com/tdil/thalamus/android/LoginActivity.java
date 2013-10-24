package com.tdil.thalamus.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.BeanMappingFunction;
import com.tdil.thalamus.android.gui.BeanMappingListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.DocumentTypeBean;
import com.tdil.thalamus.android.rest.model.DocumentTypeCollection;
import com.tdil.thalamus.android.rest.model.LoginResponse;
import com.tdil.thalamus.android.utils.Login;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity implements IRestClientObserver {

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
	private String mPassword;
	private CheckBox remCheckBox;
	
	private DocumentTypeCollection col;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);
		
//		Editor e = this.getPreferences(Context.MODE_PRIVATE).edit();
//		e.clear();
//		e.commit();
		
		/* TODO borrar */
		String mDocNumber = this.getPreferences(Context.MODE_PRIVATE).getString("mDocNumber", "");
		String mPassword = this.getPreferences(Context.MODE_PRIVATE).getString("mPassword", "");
		
		final Spinner spinner = (Spinner) findViewById(R.id.documentType);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				 DocumentTypeBean item = (DocumentTypeBean)arg0.getItemAtPosition(arg2);
				 LoginActivity.this.mDocType = String.valueOf(item.getId());
				
			}@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				 Gson gson = new Gson();
				 col = gson.fromJson(task.getResult(), DocumentTypeCollection.class);
				 BeanMappingListAdapter<DocumentTypeBean> adapter = new BeanMappingListAdapter<DocumentTypeBean>(LoginActivity.this,
					android.R.layout.simple_spinner_item, col.getList(), new BeanMappingFunction<DocumentTypeBean>() {
			 			public String key(DocumentTypeBean t) {return String.valueOf(t.getId());};
			 			@Override
			 			public String value(DocumentTypeBean t) {
			 				return t.getName();
			 			}
					});
					spinner.setAdapter(adapter);
				
					String mDocTypeSt = LoginActivity.this.getPreferences(Context.MODE_PRIVATE).getString("mDocType", "-1");
					int mDocType = Integer.valueOf(mDocTypeSt);
					if(mDocType != -1) {
						int index = 0;
					 for (DocumentTypeBean bean : col.getList()) {
						 if (bean.getId() == mDocType) {
							 spinner.setSelection(index);
						 }
						 index = index + 1;
					 }
					}
			}
			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(LoginActivity.this);
			}
		}, RESTConstants.DOCUMENT_TYPES, null, null).execute((Void) null);
		
		/*List<String> list = new ArrayList<String>();
		list.add("DNI");
		list.add("LE");
		SpinAdapter adapter = new SpinAdapter(this,
				android.R.layout.simple_spinner_item, list);
		spinner.setAdapter(adapter);*/

		findViewById(R.id.loginButtons).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
		
		findViewById(R.id.requestResetPassword).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(LoginActivity.this, RequestResetPasswordActivity.class);
			        	startActivity(intent);
					}
				});
		remCheckBox = (CheckBox)findViewById(R.id.rememberPasswordCheckbox);
		
		findViewById(R.id.requestRegistration).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
			        	startActivity(intent);
					}
				});
		
		EditText docNumber = (EditText)findViewById(R.id.documentNumber);
		docNumber.setText(mDocNumber);
		EditText password = (EditText)findViewById(R.id.password);
		password.setText(mPassword);
		if (!isEmpty(mDocNumber) && !isEmpty(mPassword)) {
			remCheckBox.setChecked(true);
		}
	}

	private boolean isEmpty(String mDocNumber2) {
		if (mDocNumber2 == null) {
			return true;
		}
		return mDocNumber2.trim().length() == 0;
	}

	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Store values at the time of the login attempt.
		mDocNumber = ((EditText) findViewById(R.id.documentNumber)).getText().toString();
		mPassword = ((EditText) findViewById(R.id.password)).getText().toString();

		boolean cancel = false;
		View focusView = null;
		showProgress(true);
		mAuthTask = new RESTClientTask(this, HttpMethod.GET, this, RESTConstants.LOGIN, new RestParams(RESTConstants.P_DOCUMENT_TYPE, mDocType).
				put(RESTConstants.P_DOCUMENT_NUMBER, mDocNumber).put(RESTConstants.P_PASSWORD, mPassword), null);
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
		//getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public void error(RESTClientTask task) {
		Messages.connectionErrorMessage(LoginActivity.this);
		this.mAuthTask = null;
	}
	@Override
	public void sucess(RESTClientTask task) {
		Gson gson = new Gson();
		LoginResponse resp = gson.fromJson(task.getResult(), LoginResponse.class);
		if (resp.getLogged()) {
			Login.loggedUser = resp;
			
			if (LoginActivity.this.remCheckBox.isChecked()) {
				Editor e = this.getPreferences(Context.MODE_PRIVATE).edit();
				e.putString("mDocType", mDocType);
				e.putString("mDocNumber", mDocNumber);
				e.putString("mPassword", mPassword);
				e.commit();
			} else {
				Editor e = this.getPreferences(Context.MODE_PRIVATE).edit();
				e.putString("mDocType", "");
				e.putString("mDocNumber", "");
				e.putString("mPassword", "");
				e.commit();
			}
			
			Intent intent = new Intent(this, IndexActivity.class);
//			Intent intent = new Intent(this, HomeAlarmsActivity.class);
        	startActivity(intent);
        	finish();
		} else {
			Editor e = this.getPreferences(Context.MODE_PRIVATE).edit();
			e.clear();
			e.commit();
			
			new AlertDialog.Builder(LoginActivity.this)
			   .setIcon(R.drawable.ic_launcher)
			   .setTitle("Atención")
			   .setMessage("El usuario o contraseña son incorrectos")
			   .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int whichButton) {
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
