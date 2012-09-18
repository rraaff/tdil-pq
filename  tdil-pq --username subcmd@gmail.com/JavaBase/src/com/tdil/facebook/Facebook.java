package com.tdil.facebook;


public class Facebook {
    /// set this to the list of extended permissions you want
    public static final String email_perms = "email";

    public static String getLoginRedirectURL(String client_id, String redirect_uri, String perms) {
        return "https://graph.facebook.com/oauth/authorize?client_id=" + 
            client_id + "&display=page&redirect_uri=" + 
            redirect_uri+"&scope=" + perms;
    }

    public static String getAuthURL(String authCode, String client_id, String redirect_uri, String secret) {
        return "https://graph.facebook.com/oauth/access_token?client_id=" + 
            client_id+"&redirect_uri=" + 
            redirect_uri+"&client_secret="+secret+"&code="+authCode;
    }
}