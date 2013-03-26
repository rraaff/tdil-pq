package com.facebook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;

import com.facebook.util.GetTestUser;
import com.facebook.util.ListTestUsers;

public class FacebookTestData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4472604870712273805L;
	
	private String appName;
	private String appId;
	private String appSecret;
	private String accessToken;
	
	private Collection<FacebookTestUser> testUsers = new ArrayList<FacebookTestUser>();

	public void loadFromDisk() {
		ObjectInputStream objectInputStream = null;
		try {
			File file = new File("/home/mgodoy/temp/facebook.bin");
			if (file.exists()) {
				System.out.println("Database found...reading");
				objectInputStream = new ObjectInputStream(new FileInputStream(file));
				testUsers = (Collection<FacebookTestUser>)objectInputStream.readObject();
			} else {
				System.out.println("Database not found");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (objectInputStream != null) {
					objectInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveToDisk() {
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream("/home/mgodoy/temp/facebook.bin"));
			objectOutputStream.writeObject(testUsers);
			System.out.println("Database saved");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				objectOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void refreshTestUsers() throws HttpException, IOException {
		List<FacebookTestUser> oldtestUsers = new ArrayList<FacebookTestUser>();
		oldtestUsers.addAll(testUsers);
		testUsers.clear();
		
		try {
			JSONObject object = ListTestUsers.list(this.getAppId(), this.getAccessToken());
			JSONArray jsonArray = (JSONArray)object.getJSONArray("data");
			
			testUsers = (Collection<FacebookTestUser>)JSONArray.toCollection(jsonArray, FacebookTestUser.class);
			for (FacebookTestUser old : oldtestUsers) {
				setExtendedData(old);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadAll() throws HttpException, IOException {
		for (FacebookTestUser t : testUsers) {
			loadUser(t.getId());
		}
	}
	
	public void loadUser(String id) throws HttpException, IOException {
		JSONObject user = GetTestUser.get(id);
		setExtendedData(id, user);
	}
	
	private void setExtendedData(String id, JSONObject user) {
		for(FacebookTestUser act : testUsers) {
			if (act.getId().equals(id)) {
				act.setName(user.getString("name"));
				return;
			}
		}
	}

	private void setExtendedData(FacebookTestUser old) {
		for(FacebookTestUser act : testUsers) {
			if (act.getId().equals(old.getId())) {
				act.setName(old.getName());
				act.setEmail(old.getEmail());
				act.setPassword(old.getPassword());
				return;
			}
		}
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Collection<FacebookTestUser> getTestUsers() {
		return testUsers;
	}

	public void setTestUsers(List<FacebookTestUser> testUsers) {
		this.testUsers = testUsers;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
