package com.tdil.lojack.struts.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.lojack.struts.forms.RegisterForm;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;

public class LoginTwitterAction extends Action {

	public static final String redirect_uri = "/loginTW.do"; 
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String code = request.getParameter("oauth_token");
        if (!StringUtils.isEmpty(code)) {
        	RegisterForm register = (RegisterForm)request.getSession().getAttribute("RegisterForm");
        	// TODO JSONObject json = (JSONObject)ThalamusClientFacade.signInTwitter(code);
        	/*if (isNotLogged(json)) {
	        	register.setSocialConnections(getSocialConnections(json));
	        	setData(register, json);
	        	System.out.println(json.toString(2));
	        	return mapping.findForward("register");
        	} else {
        		// marcar como logueado
        		return mapping.findForward("continue");
        	}*/
        	return mapping.findForward("cancel");
        } else { // si cancelo
        	return mapping.findForward("cancel");
        }
	}
	


	private void setData(RegisterForm register, JSONObject json) {
		JSONObject data = json.getJSONObject("data");
		JSONObject profile = data.getJSONObject("profile");
		register.setFirstName(profile.getString("firstname"));
		register.setLastName(profile.getString("lastname"));
		register.setFacebook(profile.getString("facebook"));
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


	private String readURL(URL url) throws IOException {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        InputStream is = url.openStream();
	        int r;
	        while ((r = is.read()) != -1) {
	            baos.write(r);
	        }
	        return new String(baos.toByteArray());
	    }
	
}
