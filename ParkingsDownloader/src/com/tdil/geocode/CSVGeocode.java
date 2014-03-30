package com.tdil.geocode;

import java.io.FileReader;
import java.io.IOException;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.tdil.lojack.vlu.VLUImportRecord;
import com.tdil.utils.SystemPropertyCache;

public class CSVGeocode {
	
	private static CellProcessor[] getProcessors() {

		final CellProcessor[] processors = new CellProcessor[] { 
				null, // dni
				null, // dominio
				null, // estado
				null, // dni
				null, // dominio
				null, // estado
				null, // dni
				null, // dominio
				null, // estado
				null, // dni
				null, // dominio
		};

		return processors;
	}

	public static void main(String[] args) throws IOException {
		final Geocoder geocoder = new Geocoder();
		CsvBeanReader beanReader = null;
		try {
			beanReader = new CsvBeanReader(new FileReader("/home/mgodoy/Documents/pois.csv"),CsvPreference.STANDARD_PREFERENCE);

			// the header elements are used to map the values to the bean (names
			// must match)
			final String[] header = beanReader.getHeader(true);
			final CellProcessor[] processors = getProcessors();

			PoiRecord importRecord;
			while ((importRecord = beanReader.read(PoiRecord.class, header, processors)) != null) {
				System.out.println("*****************************************");
				String toGeocode = importRecord.getAddres() + ", " + importRecord.getCity() + "," + importRecord.getState() + "," + "Argentina";
				System.out.println(toGeocode);
				GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(toGeocode).setLanguage("es").getGeocoderRequest();
				GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
				System.out.println("results: " +geocoderResponse.getResults().size() + " " + geocoderResponse);
				for (int i = 0; i < geocoderResponse.getResults().size(); i++){
					GeocoderResult res = geocoderResponse.getResults().get(i);
					System.out.println(res.getFormattedAddress());
					System.out.println(res.getGeometry().getLocation().getLat() + "," + res.getGeometry().getLocation().getLng());
				}
			}
			//changeStatus(imp.getId(), "FINISHED");
		} finally {
			if (beanReader != null) {
				beanReader.close();
			}
		}
	}
}
