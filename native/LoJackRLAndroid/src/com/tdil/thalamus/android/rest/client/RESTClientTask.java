package com.tdil.thalamus.android.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

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
	private String url;
	private Map<String, String> urlParams;
	private String body;
	
	private static HttpClient httpClient = new DefaultHttpClient();
	
	public RESTClientTask(Context context, HttpMethod method, IRestClientObserver observer, String url, RestParams restParams,
			String body) {
		this.context = context;
		this.method = method;
		this.observer = observer;
		this.progressDialog = new ProgressDialog(context);
		this.url = url;
		this.urlParams = restParams == null? null : restParams.getParams();
		this.body = body;
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
		String url_select = RESTConstants.REST_URL + this.url;
		if (this.urlParams != null) {
			for (Map.Entry<String, String> replacements : this.urlParams.entrySet()) {
				url_select = url_select.replace(replacements.getKey(), replacements.getValue());
			}
		}
		ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

		try {
			// Set up HTTP post
			HttpRequestBase httpPost = this.method.create(url_select);
			if (this.body != null) {
				((HttpEntityEnclosingRequestBase)httpPost).setEntity(new ByteArrayEntity(
					    this.body.getBytes("UTF8")));
			}
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			// Read content & Log
			inputStream = httpEntity.getContent();
			
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
				JSONObject jObject;
				try {
					jObject = new JSONObject(result);
					System.out.println(jObject.toString(2));
					// if por ...
					return true;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			} catch (Exception e) {
				Log.e("StringBuilding & BufferedReader", "Error converting result "
						+ e.toString());
				return false;
			}
			
		} catch (UnsupportedEncodingException e1) {
			Log.e("UnsupportedEncodingException", e1.toString());
			e1.printStackTrace();
			return false;
		} catch (ClientProtocolException e2) {
			Log.e("ClientProtocolException", e2.toString());
			e2.printStackTrace();
			return false;
		} catch (IllegalStateException e3) {
			Log.e("IllegalStateException", e3.toString());
			e3.printStackTrace();
			return false;
		} catch (IOException e4) {
			Log.e("IOException", e4.toString());
			e4.printStackTrace();
			return false;
		}
		
	}

	@Override
	protected void onPostExecute(final Boolean success) {
		this.progressDialog.dismiss();
		if(success) {
			this.observer.sucess(this);
		} else {
			this.observer.error(this);
		}
	}

	@Override
	protected void onCancelled() {
		// TODO ver context.showProgress(false);
	}

	public String getResult() {
		return result;
	}
}
