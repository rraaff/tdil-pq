package com.tdil.lojack.pets;

import com.tdil.lojack.thalamus.ThalamusLoginCache;
import com.tdil.lojack.utils.WebsiteUser;

public class PetsConnector {

	private static String petsLoginUrl = "";
	private static String petsMobileLoginUrl = "";
	private static String petsToken;

	public static String getPetsLoginUrl(WebsiteUser websiteUser) {
		ThalamusLoginCache.updateCache(websiteUser);
		return petsLoginUrl;
	}

	public static void setPetsLoginUrl(String petsLoginUrl) {
		PetsConnector.petsLoginUrl = petsLoginUrl;
	}

	public static String getPetsToken() {
		return petsToken;
	}

	public static void setPetsToken(String petsToken) {
		PetsConnector.petsToken = petsToken;
	}

	public static String getPetsMobileLoginUrl(WebsiteUser websiteUser) {
		ThalamusLoginCache.updateCache(websiteUser);
		return petsMobileLoginUrl;
	}

	public static void setPetsMobileLoginUrl(String petsMobileLoginUrl) {
		PetsConnector.petsMobileLoginUrl = petsMobileLoginUrl;
	}
}
