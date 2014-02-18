package com.tdil.peugeotservice.android.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tdil.peugeotservice.android.rest.model.LoginResponse;

public class Login {

	 public static final String PREFS_NAME = "Login";
	 public static final String USER = "user";
	
	private static LoginResponse loggedUser;

	public static LoginResponse getLoggedUser(Context context) {
		if (loggedUser == null) {
			SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
			String jsonUser = sharedPref.getString(USER, USER);
			Gson gson = new Gson();
			loggedUser = gson.fromJson(jsonUser, LoginResponse.class);
		}
		return loggedUser;
	}

	public static void setLoggedUser(Context context, LoginResponse loggedUser) {
		Login.loggedUser = loggedUser;
		SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		Gson gson = new Gson();
		editor.putString(USER, gson.toJson(loggedUser));
		editor.commit();
	}
}
