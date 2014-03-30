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
import com.tdil.ljpeugeot.model.POISubType;
import com.tdil.ljpeugeot.model.POIType;
import com.tdil.lojack.vlu.VLUImportRecord;
import com.tdil.utils.SystemPropertyCache;

public class CSVGeocodeToSQL {
	
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
				null, // dni
				null // dominio
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

			POIWithLatLon importRecord;
			while ((importRecord = beanReader.read(POIWithLatLon.class, header, processors)) != null) {
				if (!"XXX".equals(importRecord.getLat())) {
					StringBuilder insert = new StringBuilder();
					int type = POIType.getForDescription(importRecord.getType()).getCode();
					int subtype = POISubType.getForDescription(importRecord.getSubtype()).getCode();
					String desc = importRecord.getAddres() + "\n" + importRecord.getPhone() + "\n" + importRecord.getEmail();
					insert.append("INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(" + type+ ", "+subtype+", '"+importRecord.getName()+"','"+desc+"',"+importRecord.getLat()+","+importRecord.getLon()+");");
					System.out.println(insert.toString());
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
