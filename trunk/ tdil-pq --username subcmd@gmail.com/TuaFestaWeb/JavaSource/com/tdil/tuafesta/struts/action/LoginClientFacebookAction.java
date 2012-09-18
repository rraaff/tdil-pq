package com.tdil.tuafesta.struts.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.facebook.Facebook;
import com.tdil.facebook.UserService;
import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithValue;
import com.tdil.struts.ValidationException;
import com.tdil.tuafesta.struts.forms.ClientForm;
import com.tdil.tuafesta.utils.SystemPropertiesKeys;
import com.tdil.tuafesta.web.SystemPropertyUtils;
import com.tdil.users.User;

public class LoginClientFacebookAction extends Action implements TransactionalActionWithValue {

	public static final String redirect_uri = "/loginClientFB.do"; 
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String code = request.getParameter("code");
        if (!StringUtils.isEmpty(code)) {

        	String client_id = SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.FB_API_KEY);
        	String secret = SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.FB_SECRET);
        	String server = SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.SERVER_NAME);
        	
        	String authURL = Facebook.getAuthURL(code, client_id, server + redirect_uri, secret);
            URL url = new URL(authURL);
            try {
                String result = readURL(url);
                String accessToken = null;
                Integer expires = null;
                String[] pairs = result.split("&");
                for (String pair : pairs) {
                    String[] kv = pair.split("=");
                    if (kv.length != 2) {
                        throw new RuntimeException("Unexpected auth response");
                    } else {
                        if (kv[0].equals("access_token")) {
                            accessToken = kv[1];
                        }
                        if (kv[0].equals("expires")) {
                            expires = Integer.valueOf(kv[1]);
                        }
                    }
                }
                if (accessToken != null && expires != null) {
                	ClientForm clientForm = (ClientForm)form;
                	clientForm.takeFacebookData(UserService.authFacebookLogin(accessToken, expires));
                	try {
            			User user = (User) TransactionProvider.executeInTransaction(this, clientForm);
            			if (user != null) {
	            			request.getSession().setAttribute("user", user);
	            			return mapping.findForward("logged");
            			} else {
            				return mapping.findForward("completeRegistration");
            			}
            		} catch (ValidationException e) {
            			return mapping.findForward("completeRegistration");
            		}
                } else {
                    throw new RuntimeException("Access token and expires not found");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else { // si cancelo
        	return mapping.findForward("cancel");
        }
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
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ClientForm loginform = (ClientForm) form;
		loginform.init();
		return loginform.executeLoginFB();
	}
}
