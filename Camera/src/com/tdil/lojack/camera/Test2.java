package com.tdil.lojack.camera;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthPolicy;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

public class Test2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		Credentials creds = new UsernamePasswordCredentials("admin", "qw123456");
		client.getState().setCredentials(new AuthScope("demo-trendnet.dyndns.org", 8380, "TV-IP851WIC"), creds);
		GetMethod get = new GetMethod("http://demo-trendnet.dyndns.org:8380/image.jpg");
		get.setDoAuthentication(true);
		client.getParams().setAuthenticationPreemptive(true); // seems to be necessary in most cases
		client.getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, Collections.singleton(AuthPolicy.DIGEST));//need to register DIGEST scheme not the basic
		List authPrefs = new ArrayList(2);
		authPrefs.add(AuthPolicy.DIGEST);
		client.getParams().setParameter(AuthPolicy.AUTH_SCHEME_PRIORITY, authPrefs);
		client.executeMethod(get);
		InputStream result = get.getResponseBodyAsStream();
		copy(result, new File("/home/mgodoy/aaa1.jpg"));
	}

	private  static void copy(InputStream in, File file) {
	    try {
	        OutputStream out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0){
	            out.write(buf,0,len);
	        }
	        out.close();
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
