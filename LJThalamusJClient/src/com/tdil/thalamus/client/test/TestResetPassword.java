package com.tdil.thalamus.client.test;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.tdil.thalamus.client.core.ThalamusResponse;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.ThalamusClientFacade;
import com.tdil.thalamus.client.facade.json.beans.RequestResetPasswordBean;
import com.tdil.thalamus.client.facade.json.beans.RequestResetPasswordResult;
import com.tdil.thalamus.client.facade.json.beans.ResetPasswordBean;
import com.tdil.thalamus.client.facade.json.fields.RequestResetPasswordParameters;
import com.tdil.thalamus.client.facade.json.fields.ResetPasswordParameters;

public class TestResetPassword extends ThalamusTestCase {

	public void testRequestReset() throws Exception {
		// pido reseteo
		JSONObject requestReset = new JSONObject();
		requestReset.put(RequestResetPasswordParameters.principal, "Marcos739070166642@gmail.com");
		
		JSON result = ThalamusClientFacade.requestResetPassword(requestReset);
		assertTrue(result instanceof JSONObject);
		JSONObject json = (JSONObject)result;
		System.out.println(json.toString(2));
	
		JSONObject tokenDev = json.getJSONObject("token-development");
		
		// reseteo
		JSONObject reset = new JSONObject();
		reset.put(ResetPasswordParameters.token, tokenDev.getString("token"));
//		reset.put(ResetPasswordParameters.principal, tokenDev.getString("credentialId") + ":Marcos739070166642@gmail.com");
		reset.put(ResetPasswordParameters.principal, "Marcos739070166642@gmail.com");
		reset.put(ResetPasswordParameters.password, "4321");
	
		JSON resultReset = ThalamusClientFacade.resetPassword(reset);
		assertTrue(result instanceof JSONObject);
		json = (JSONObject)resultReset;
		System.out.println(json.toString(2));
		
		// me logueo con la clave cambiada
		JSON loginChanged = ThalamusClientFacade.login("Marcos739070166642@gmail.com", "4321");
		System.out.println(loginChanged.toString(2));
		
//		TODO ver la respuesta de Gabriel assertTrue(json.getBoolean("reseted"));
		
		// lo vuevo a hacer para dejar el password original
		requestReset = new JSONObject();
		requestReset.put(RequestResetPasswordParameters.principal, "Marcos739070166642@gmail.com");
		
		result = ThalamusClientFacade.requestResetPassword(requestReset);
		assertTrue(result instanceof JSONObject);
		json = (JSONObject)result;
		System.out.println(json.toString(2));
		
		tokenDev = json.getJSONObject("token-development");
		
		reset = new JSONObject();
		reset.put(ResetPasswordParameters.token, tokenDev.getString("token"));
//		reset.put(ResetPasswordParameters.principal, tokenDev.getString("credentialId") + ":Marcos739070166642@gmail.com");
		reset.put(ResetPasswordParameters.principal, "Marcos739070166642@gmail.com");
		reset.put(ResetPasswordParameters.password, "1234");
		
		resultReset = ThalamusClientFacade.resetPassword(reset);
		assertTrue(result instanceof JSONObject);
		json = (JSONObject)resultReset;
		System.out.println(json.toString(2));
//		TODO ver la respuesta de gabriel assertTrue(json.getBoolean("reseted"));
	}
	
	public void testRequestResetWithBean() throws Exception {
		// pido reseteo
		RequestResetPasswordBean requestResetPasswordBean = new RequestResetPasswordBean();
		requestResetPasswordBean.setPrincipal("Marcos739070166642@gmail.com");
		
		RequestResetPasswordResult resetResult = ThalamusClientBeanFacade.requestResetPassword(requestResetPasswordBean);
		
		// reseteo
		ResetPasswordBean resetPasswordBean = new ResetPasswordBean();
		resetPasswordBean.setToken(resetResult.getTokenDev());
		resetPasswordBean.setPrincipal("Marcos739070166642@gmail.com");
		resetPasswordBean.setPassword("4321");
	
		ThalamusResponse resultReset = ThalamusClientBeanFacade.resetPassword(resetPasswordBean);
		assertTrue(resultReset.getResult() instanceof JSONObject);
		JSON json = resultReset.getResult();
		System.out.println(json.toString(2));
		
		// me logueo con la clave cambiada
		JSON loginChanged = ThalamusClientFacade.login("Marcos739070166642@gmail.com", "4321");
		System.out.println(loginChanged.toString(2));
		
//		TODO ver la respuesta de Gabriel assertTrue(json.getBoolean("reseted"));
		
		// lo vuevo a hacer para dejar el password original
		RequestResetPasswordBean requestResetPasswordBean1 = new RequestResetPasswordBean();
		requestResetPasswordBean1.setPrincipal("Marcos739070166642@gmail.com");
		
		resetResult = ThalamusClientBeanFacade.requestResetPassword(requestResetPasswordBean1);
		resetPasswordBean = new ResetPasswordBean();
		resetPasswordBean.setToken(resetResult.getTokenDev());
		resetPasswordBean.setPrincipal("Marcos739070166642@gmail.com");
		resetPasswordBean.setPassword("1234");
		
		resultReset = ThalamusClientBeanFacade.resetPassword(resetPasswordBean);
//		TODO ver la respuesta de gabriel assertTrue(json.getBoolean("reseted"));
	}
}
