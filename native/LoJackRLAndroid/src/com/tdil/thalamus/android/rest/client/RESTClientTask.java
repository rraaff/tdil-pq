package com.tdil.thalamus.android.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.tdil.thalamus.android.IRestClientObserver;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.util.Log;

public class RESTClientTask extends AsyncTask<Void, Void, Boolean> {

	private HttpMethod method;
	private Context context;
	private IRestClientObserver observer;
	private ProgressDialog progressDialog;
	private InputStream inputStream = null;
	private String result = "";
	
	private static HttpClient httpClient = new DefaultHttpClient();
	
	public RESTClientTask(Context context, HttpMethod method, IRestClientObserver observer) {
		this.context = context;
		this.method = method;
		this.observer = observer;
		this.progressDialog = new ProgressDialog(context);
	}

	protected void onPreExecute() {
		progressDialog.setMessage("Downloading your data...");
		progressDialog.show();
		progressDialog.setOnCancelListener(new OnCancelListener() {
			public void onCancel(DialogInterface arg0) {
				RESTClientTask.this.cancel(true);
			}
		});
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		// TODO: attempt authentication against a network service.

		String url_select = "http://192.168.0.107:8180/LoJackWeb/rest/users/login?documentType=1&documentNumber=12&password=12";

		ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

		try {
			// Set up HTTP post
			HttpRequestBase httpPost = this.method.create(url_select);
			// httpPost.setEntity(new UrlEncodedFormEntity(param));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			// Read content & Log
			inputStream = httpEntity.getContent();
		} catch (UnsupportedEncodingException e1) {
			Log.e("UnsupportedEncodingException", e1.toString());
			e1.printStackTrace();
		} catch (ClientProtocolException e2) {
			Log.e("ClientProtocolException", e2.toString());
			e2.printStackTrace();
		} catch (IllegalStateException e3) {
			Log.e("IllegalStateException", e3.toString());
			e3.printStackTrace();
		} catch (IOException e4) {
			Log.e("IOException", e4.toString());
			e4.printStackTrace();
		}
		// Convert response to string using String Builder
		try {
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					inputStream, "iso-8859-1"), 8);
			StringBuilder sBuilder = new StringBuilder();

			String line = null;
			while ((line = bReader.readLine()) != null) {
				sBuilder.append(line + "\n");
			}
			inputStream.close();
			result = sBuilder.toString();
		} catch (Exception e) {
			Log.e("StringBuilding & BufferedReader", "Error converting result "
					+ e.toString());
		}
		JSONObject jObject;
		try {
			jObject = new JSONObject(result);
			System.out.println(jObject.toString(2));
			// if por ...
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	protected void onPostExecute(final Boolean success) {
		this.progressDialog.dismiss();
		if(success) {
			this.observer.sucess();
		} else {
			this.observer.error();
		}
	}

	@Override
	protected void onCancelled() {
		// TODO ver context.showProgress(false);
	}
}
