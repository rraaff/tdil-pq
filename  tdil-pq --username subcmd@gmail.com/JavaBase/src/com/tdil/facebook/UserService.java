package com.tdil.facebook;

import java.net.URL;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

public class UserService {

    public static JSONObject authFacebookLogin(String accessToken, int expires) {
        try {
            String response = IOUtils.toString(new URL("https://graph.facebook.com/me?access_token=" + accessToken).openStream());
            JSONTokener tokener = new JSONTokener(response);
			JSONObject rawResponseMessage = (JSONObject)tokener.nextValue();
           return rawResponseMessage;
        } catch (Throwable ex) {
            throw new RuntimeException("failed login", ex);
        }
    }
}