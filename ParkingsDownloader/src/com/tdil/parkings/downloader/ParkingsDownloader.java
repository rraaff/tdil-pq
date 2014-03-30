package com.tdil.parkings.downloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSON;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;

import com.tdil.lojack.gis.JSONResponse;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.method.PostMethodCreator;
import com.tdil.thalamus.client.core.method.PostMethodCreatorWithParams;

public class ParkingsDownloader {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		// TODO bajo
		String response = null;
		for (int i = 1; i < 47; i++) {
			HttpClient client = new HttpClient();
			PostMethodCreatorWithParams postMethodCreatorWithParams = new PostMethodCreatorWithParams(new NameValuePair("barrio", String.valueOf(i)));
			postMethodCreatorWithParams.addParam(new NameValuePair("busque", "1"));
			HttpMethod httpMethod = postMethodCreatorWithParams.createMethod("http://miestacionamiento.com.ar/resultados.php");
			try {
				//httpMethod.setRequestHeader("Content-type", "application/json");
				client.executeMethod(httpMethod);
				int statusCode = httpMethod.getStatusCode();
				response = httpMethod.getResponseBodyAsString();
				if (statusCode != HttpStatus.SC_OK) {
					throw new HttpStatusException(statusCode, HttpStatus.getStatusText(statusCode));
				}
				//System.out.println(response);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// proceso y genero insert
			//String html = IOUtils.toString(new FileInputStream("/home/mgodoy/freelance/lojack/estacionamientos/estacionamientos.html"));

			String html = response;
			Pattern pattern = Pattern.compile("var marker([0-9]*) = new google.maps.Marker\\(\\{\\s*position: new google.maps.LatLng\\(([^,]*),([^\\)]*)",Pattern.DOTALL);


			Matcher matcher = pattern.matcher(html);
			while (matcher.find()) {
				StringBuilder insert = new StringBuilder();
				String index = matcher.group(1);
				String lat = matcher.group(2);
				String lon = matcher.group(3);
//				System.out.println(index);
//				System.out.println(lat);
//				System.out.println(lon);
				Pattern content = Pattern.compile("var contenido" + index + " = '[^']*<p class=\"tituloscentro\" >&quot;([^&]*)&quot;</p><p align=\"left\"><font face=\"Arial, Helvetica, sans-serif\" size=\"-1\">([^<]*)",Pattern.DOTALL);
				Matcher cmatcher = content.matcher(html);
				if (cmatcher.find()) {
					String title = cmatcher.group(1);
					if (title.length() == 0) {
						title = "Estacionamiento sin datos";
					}
					String desc = cmatcher.group(2);
					if (desc.length() == 0) {
						desc = "Estacionamiento sin datos";
					}
					if (lat.length() != 0) {
	//					System.out.println(cmatcher.group(1));
	//					System.out.println(cmatcher.group(2));
						insert.append("INSERT INTO POI (type, subtype, name, description, lat, lon) VALUES(1, 0, '"+title+"','"+desc+"',"+lat+","+lon+");");
						System.out.println(insert.toString());
					}
				}
			}
		}
	}

}
