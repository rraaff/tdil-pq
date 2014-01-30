package com.tdil.ljpeugeot.struts.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ljpeugeot.struts.forms.LoginForm;
import com.tdil.ljpeugeot.struts.forms.RegisterForm;
import com.tdil.ljpeugeot.utils.WebsiteUser;
import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.LoginResult;
import com.tdil.thalamus.client.facade.json.beans.TokenHolder;

public class LoginFacebookAction extends Action {

	public static final String redirect_uri = "/loginFB.do";

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String code = request.getParameter("code");
        if (!StringUtils.isEmpty(code)) {
        	Cookie twt = getTwitterCookie(request);
			Cookie etwt = getExtraTwitterCookie(request);
        	RegisterForm register = (RegisterForm)request.getSession().getAttribute("RegisterForm");
        	
			TokenHolder tokenHolder = new TokenHolder();
			org.apache.commons.httpclient.Cookie cookie = new org.apache.commons.httpclient.Cookie(ThalamusClient.getTHALAMUS_HOST(),
					"JSESSIONID", twt.getValue());
			cookie.setPath(ThalamusClient.getTHALAMUS_JSESSIONID_COOKIE_PATH());
			tokenHolder.addCookie(cookie);
			
			org.apache.commons.httpclient.Cookie ecookie = new org.apache.commons.httpclient.Cookie(ThalamusClient.getTHALAMUS_HOST(),
					"AWSELB", etwt.getValue());
			ecookie.setPath(ThalamusClient.getTHALAMUS_JSESSIONID_COOKIE_PATH());
			tokenHolder.addCookie(ecookie);
        	
        	LoginResult login = ThalamusClientFacade.signInFacebook(code, tokenHolder);
        	JSONObject json = (JSONObject)login.getResponse().getResult();
        	if (isNotLogged(json)) {
	        	register.setSocialConnections(getSocialConnections(json));
	        	setData(register, json);
	        	return mapping.findForward("register");
        	} else {
        		// marcar como logueado
        		WebsiteUser user = LoginForm.getUserLogged(login);
        		request.getSession().setAttribute("user", user);
        		return mapping.findForward("continue");
        	}
        } else { // si cancelo
        	return mapping.findForward("cancel");
        }
	}

	private Cookie getTwitterCookie(HttpServletRequest request) {
		for (Cookie co : request.getCookies()) {
			if (co.getName().equals("twt")) {
				return co;
			}
		}
		return null;
	}
	
	private Cookie getExtraTwitterCookie(HttpServletRequest request) {
		for (Cookie co : request.getCookies()) {
			if (co.getName().equals("etwt")) {
				return co;
			}
		}
		return null;
	}

	private void setData(RegisterForm register, JSONObject json) {
		JSONObject data = json.getJSONObject("data");
		JSONObject profile = data.getJSONObject("profile");
		if (hasData(profile, "firstname")) {
			register.setFirstName(profile.getString("firstname"));
		}
		if (hasData(profile, "lastname")) {
			register.setLastName(profile.getString("lastname"));
		}
		register.setFacebook(profile.getString("facebook"));
	}

	private boolean hasData(JSONObject profile, String string) {
		if (!profile.containsKey(string)) {
			return false;
		}
		return profile.get(string) != JSONNull.getInstance();
	}

	private JSONArray getSocialConnections(JSONObject json) {
		JSONObject social = (JSONObject)json.getJSONObject("data").getJSONArray("socialConnections").get(0);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("providerId", social.getString("providerId"));
		jsonObject.put("providerUserId", social.getString("providerUserId"));
		jsonObject.put("accessToken", social.getString("accessToken"));
		jsonArray.add(jsonObject);
		return jsonArray;
	}


	private boolean isNotLogged(JSONObject json) {
		JSONObject jsonObject = json.getJSONObject("link");
		String ref = jsonObject.getString("ref");
		return "SingUp".equals(ref);
	}

}
