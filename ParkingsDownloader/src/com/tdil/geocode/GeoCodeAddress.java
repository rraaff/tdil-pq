package com.tdil.geocode;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;

public class GeoCodeAddress {

	public static void main(String[] args) {
		final Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress("Rivadavia 12456 Ciudadela").setLanguage("es").getGeocoderRequest();
		GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		System.out.println(geocoderResponse);
		for (int i = 0; i < geocoderResponse.getResults().size(); i++){
			GeocoderResult res = geocoderResponse.getResults().get(i);
			System.out.println(res.getFormattedAddress());
			System.out.println(res.getGeometry().getLocation().getLat() + " - " + res.getGeometry().getLocation().getLng());
		}
			
	}
}
