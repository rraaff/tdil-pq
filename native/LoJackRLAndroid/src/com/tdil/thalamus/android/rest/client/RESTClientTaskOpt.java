package com.tdil.thalamus.android.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

public class RESTClientTaskOpt<T> extends RESTClientTask {

	private Class<T> classResult;
	private T castedResult;

	public RESTClientTaskOpt(Context context, HttpMethod method,
			IRestClientObserver observer, String url, RestParams restParams,
			String body, Class<T> classResult) {
		super(context, method, observer, url, restParams, body);
		this.classResult = classResult;
	}
	
	@Override
	protected Boolean doInBackground(Void... params) {
		if (incomplete) {
			return Boolean.FALSE;
		}
		Context context = contextRef.get();
		BufferedReader bReader = null;
		try {
			// Set up HTTP post
			HttpRequestBase httpPost = this.method.create(urlToExecute);
			if (this.body != null) {
				((HttpEntityEnclosingRequestBase)httpPost).setEntity(new ByteArrayEntity(
					    this.body.getBytes("UTF8")));
			}
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			// Read content & Log
			inputStream = httpEntity.getContent();
			statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode >= 300) {
				return false;
			} else {
				// Convert response to string using String Builder
				try {
					bReader = new BufferedReader(new InputStreamReader(
							inputStream), 8);
					StringBuilder sBuilder = new StringBuilder();
	
					String line = null;
					while ((line = bReader.readLine()) != null) {
						sBuilder.append(line + "\n");
					}
					result = sBuilder.toString();
					
					Gson gson = new Gson();
					// validar la respuesta
					castedResult = gson.fromJson(getResult(), classResult);
					return true;
				} catch (Exception e) {
					Log.e("StringBuilding & BufferedReader", "Error converting result "
							+ e.toString());
					return false;
				}
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
		} finally {
			if (bReader != null) {
				try {
					bReader.close();
				} catch (IOException e) {}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {}
			}
		}
		
	}

	public T getCastedResult() {
		return castedResult;
	}

	public void setCastedResult(T castedResult) {
		this.castedResult = castedResult;
	}
	
}
