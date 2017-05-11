package com.nkang.kxmoment.util;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nkang.kxmoment.baseobject.GeoLocation;

public class GoogleLocationUtils {

	Logger log = Logger.getLogger(GoogleLocationUtils.class);
	private final String uRLString = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&";

	private static String localInd = "N";
	public static final String proxyInfo = "web-proxy.austin.hpecorp.net";

	public GoogleLocationUtils() {
		// TODO Auto-generated constructor stub
	}

	public GeoLocation geocodeByAddressNoSSL(String address) throws JSONException {
		GeoLocation loc = new GeoLocation();
		if (address == null || address.equals("")) {
			return null;
		}
		InputStream is = null;
		try {
			log.info("Start open url");
			String urlPath = uRLString + "&address="+ URLEncoder.encode(address, "UTF-8");;
			URL urlGet = new URL(urlPath);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // must be get request
			http.setRequestProperty("Content-Type","application/json");
			http.setDoOutput(true);
			http.setDoInput(true);
			if (localInd == "Y") {
				System.setProperty("http.proxyHost", proxyInfo);
				System.setProperty("http.proxyPort", "8080");
			}
			System.setProperty(
					"sun.net.client.defaultConnectTimeout",
					"60000");
			System.setProperty("sun.net.client.defaultReadTimeout",
					"60000");
			is = http.getInputStream();
			int size = is.available();
			log.debug(size);
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");

			JSONObject demoJson = new JSONObject(message);
			if (demoJson.has("results")) {
				JSONArray ResultJA = demoJson.getJSONArray("results");
				if (ResultJA.length() > 0) {
					JSONObject placeJO = ResultJA.getJSONObject(0);
					if (placeJO != null && placeJO.has("geometry")) { 
						JSONObject geoJO = placeJO.getJSONObject("geometry");
						if (geoJO != null && geoJO.has("location")) {
							JSONObject locationJO = geoJO.getJSONObject("location");
							String lng = locationJO.getString("lng");
							String lat = locationJO.getString("lat");
							loc.setLAT(lat);
							loc.setLNG(lng);
						}
					} else {
						log.debug("no geometry info");
					}
				} else {
					log.debug("no results 0");
				}
			} else {
				log.debug("no results");
			}

			is.close();
			log.info("End geocode");
			return loc;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			log.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		return null;
	}
}
