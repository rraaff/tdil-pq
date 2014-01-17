package com.tdil.thalamus.client.facade;

import java.io.Serializable;
import java.util.Collection;

import org.apache.commons.httpclient.HttpStatus;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.cache.CacheEntry;
import com.tdil.cache.TimeoutCacheManager;
import com.tdil.thalamus.client.cache.ThalamusCache;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.ThalamusClient;
import com.tdil.thalamus.client.core.ThalamusResponse;
import com.tdil.thalamus.client.core.ThalamusServices;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.json.beans.BrandBean;
import com.tdil.thalamus.client.facade.json.beans.BrandFamilyBean;
import com.tdil.thalamus.client.facade.json.beans.ChannelBean;
import com.tdil.thalamus.client.facade.json.beans.CountryBean;
import com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean;
import com.tdil.thalamus.client.facade.json.beans.LoginBean;
import com.tdil.thalamus.client.facade.json.beans.LoginResult;
import com.tdil.thalamus.client.facade.json.beans.URLHolder;
import com.tdil.thalamus.client.facade.json.beans.PersonFields;
import com.tdil.thalamus.client.facade.json.beans.PersonResult;
import com.tdil.thalamus.client.facade.json.beans.RequestResetPasswordBean;
import com.tdil.thalamus.client.facade.json.beans.RequestResetPasswordResult;
import com.tdil.thalamus.client.facade.json.beans.ResetPasswordBean;
import com.tdil.thalamus.client.facade.json.beans.StateBean;
import com.tdil.thalamus.client.facade.json.beans.TokenHolder;
import com.tdil.thalamus.client.facade.json.beans.ValidatePasswordBean;
import com.tdil.thalamus.client.facade.json.beans.ValidatePasswordResultBean;

public class ThalamusClientBeanFacade {

	public static PersonResult getPerson(TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		PersonResult result = new PersonResult((JSONObject)ThalamusClientFacade.getPerson(tokenHolder));
		return result;
	}
	
	public static RequestResetPasswordResult requestResetPassword(RequestResetPasswordBean bean) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		JSONObject json = (JSONObject)ThalamusClient.executePost(JSONObject.fromObject(bean), ThalamusServices.REQUEST_RESET_PASSWORD).getResult();
		return new RequestResetPasswordResult(json);
	}

	public static ThalamusResponse resetPassword(ResetPasswordBean resetPasswordBean) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClient.executePut(JSONObject.fromObject(resetPasswordBean), ThalamusServices.RESET_PASSWORD);
	}
	
	public static ValidatePasswordResultBean validatePassword(TokenHolder tokenHolder, ValidatePasswordBean validatePasswordBean) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		try {
			return new ValidatePasswordResultBean(ThalamusClient.executePut(tokenHolder, JSONObject.fromObject(validatePasswordBean), ThalamusServices.VALIDATE_PASSWORD));
		} catch (HttpStatusException e) {
			if (e.getStatus() == HttpStatus.SC_UNAUTHORIZED) {
				ValidatePasswordResultBean validatePasswordResultBean = new ValidatePasswordResultBean();
				validatePasswordResultBean.setUnauthorized(true);
				return validatePasswordResultBean;
			} else {
				throw e;
			}
		}
	}
	
	public static LoginResult login(LoginBean loginBean) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		return ThalamusClientFacade.login(JSONObject.fromObject(loginBean));
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<BrandBean> getBrands() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		CacheEntry<Collection<BrandBean>> cacheEntry = TimeoutCacheManager.getCacheEntry(ThalamusCache.CACHE_BRAND, ThalamusCache.ALL_RESULT);
		if (cacheEntry != null && cacheEntry.getValue() != null) {
			return cacheEntry.getValue();
		}
		JSONArray result = (JSONArray)ThalamusClient.executeGet(ThalamusServices.GET_BRANDS).getResult();
		Collection<BrandBean> resultObj = JSONArray.toCollection(result, BrandBean.class);
		TimeoutCacheManager.put(ThalamusCache.CACHE_BRAND, ThalamusCache.ALL_RESULT, (Serializable)resultObj);
		return resultObj;
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<BrandFamilyBean> getBrandFamilies() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		CacheEntry<Collection<BrandFamilyBean>> cacheEntry = TimeoutCacheManager.getCacheEntry(ThalamusCache.CACHE_BRAND_FAMILIES, ThalamusCache.ALL_RESULT);
		if (cacheEntry != null && cacheEntry.getValue() != null) {
			return cacheEntry.getValue();
		}
		JSONArray result = (JSONArray)ThalamusClient.executeGet(ThalamusServices.GET_BRANDSFAMILIES).getResult();
		Collection<BrandFamilyBean> resultObj = JSONArray.toCollection(result, BrandFamilyBean.class);
		TimeoutCacheManager.put(ThalamusCache.CACHE_BRAND_FAMILIES, ThalamusCache.ALL_RESULT, (Serializable)resultObj);
		return resultObj;
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<ChannelBean> getChannels() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		CacheEntry<Collection<ChannelBean>> cacheEntry = TimeoutCacheManager.getCacheEntry(ThalamusCache.CACHE_CHANNELS, ThalamusCache.ALL_RESULT);
		if (cacheEntry != null && cacheEntry.getValue() != null) {
			return cacheEntry.getValue();
		}
		JSONArray result = (JSONArray)ThalamusClient.executeGet(ThalamusServices.GET_CHANNELS).getResult();
		Collection<ChannelBean> resultObj = JSONArray.toCollection(result, ChannelBean.class);
		TimeoutCacheManager.put(ThalamusCache.CACHE_CHANNELS, ThalamusCache.ALL_RESULT, (Serializable)resultObj);
		return resultObj;
	}

	@SuppressWarnings("unchecked")
	public static Collection<CountryBean> getCountries() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		CacheEntry<Collection<CountryBean>> cacheEntry = TimeoutCacheManager.getCacheEntry(ThalamusCache.CACHE_COUNTRIES, ThalamusCache.ALL_RESULT);
		if (cacheEntry != null && cacheEntry.getValue() != null) {
			return cacheEntry.getValue();
		}
		JSONArray result = (JSONArray)ThalamusClient.executeGet(ThalamusServices.GET_COUNTRIES).getResult();
		Collection<CountryBean> resultObj = JSONArray.toCollection(result, CountryBean.class);
		TimeoutCacheManager.put(ThalamusCache.CACHE_COUNTRIES, ThalamusCache.ALL_RESULT, (Serializable)resultObj);
		return resultObj;
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<DocumentTypeBean> getDocumentTypes() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		CacheEntry<Collection<DocumentTypeBean>> cacheEntry = TimeoutCacheManager.getCacheEntry(ThalamusCache.CACHE_DOCUMENTTYPES, ThalamusCache.ALL_RESULT);
		if (cacheEntry != null && cacheEntry.getValue() != null) {
			return cacheEntry.getValue();
		}
		JSONArray result = (JSONArray)ThalamusClient.executeGet(ThalamusServices.GET_DOCUMENTTYPES).getResult();
		Collection<DocumentTypeBean> resultObj = JSONArray.toCollection(result, DocumentTypeBean.class);
		TimeoutCacheManager.put(ThalamusCache.CACHE_DOCUMENTTYPES, ThalamusCache.ALL_RESULT, (Serializable)resultObj);
		return resultObj;
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<StateBean> getStates(int country) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		JSONArray result = (JSONArray) ThalamusClient.executeGet(ThalamusServices.GET_STATES_START + country + ThalamusServices.GET_STATES_END).getResult();
		return JSONArray.toCollection(result, StateBean.class);
	}
	
	public static PersonFields getPersonFields() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		CacheEntry<PersonFields> cacheEntry = TimeoutCacheManager.getCacheEntry(ThalamusCache.CACHE_PERSONFIELDS, ThalamusCache.ALL_RESULT);
		if (cacheEntry != null && cacheEntry.getValue() != null) {
			return cacheEntry.getValue();
		}
		JSON json = ThalamusClient.executeGet(ThalamusServices.GET_PERSON_FIELDS).getResult();
		PersonFields resultObj = new PersonFields(json);
		TimeoutCacheManager.put(ThalamusCache.CACHE_PERSONFIELDS, ThalamusCache.ALL_RESULT, resultObj);
		return resultObj;
	}
	
	public static URLHolder getFacebookLogin() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		CacheEntry<URLHolder> cacheEntry = TimeoutCacheManager.getCacheEntry(ThalamusCache.CACHE_SIGN_IN_FACEBOOK, ThalamusCache.ALL_RESULT);
		if (cacheEntry != null && cacheEntry.getValue() != null) {
			return cacheEntry.getValue();
		}
		JSONObject json = (JSONObject)ThalamusClientFacade.signInFacebook();
		JSONObject link = json.getJSONObject("link");
		String href = link.getString("href");
		URLHolder loginUrl = new URLHolder(href);
		TimeoutCacheManager.put(ThalamusCache.CACHE_SIGN_IN_FACEBOOK, ThalamusCache.ALL_RESULT, loginUrl);
		return loginUrl;
	}
	
	public static URLHolder getFacebookAdd(TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		JSONObject json = (JSONObject)ThalamusClientFacade.addFacebook(tokenHolder);
		JSONObject link = json.getJSONObject("link");
		String href = link.getString("href");
		URLHolder loginUrl = new URLHolder(href);
		return loginUrl;
	}
	
	public static URLHolder getTwitterLogin() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		ThalamusResponse response = ThalamusClientFacade.signInTwitter();
		JSONObject link = ((JSONObject)response.getResult()).getJSONObject("link");
		String href = link.getString("href");
		URLHolder loginUrl = new URLHolder(href);
		loginUrl.addCookies(response.getTokenHolder().getCookies());
		return loginUrl;
	}
	
	public static URLHolder getTwitterAdd(TokenHolder tokenHolder) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		JSONObject json = (JSONObject)ThalamusClientFacade.addTwitter(tokenHolder);
		JSONObject link = json.getJSONObject("link");
		String href = link.getString("href");
		URLHolder loginUrl = new URLHolder(href);
		return loginUrl;
	}
}
