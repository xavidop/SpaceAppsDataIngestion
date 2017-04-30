package com.nexus.ayuntamiento.downloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import com.nexus.ayuntamiento.model.AllData;

public class AyuntamientoDownloaderTwitter implements Callable {

	HttpURLConnection conn = null;

	@Override
	public Object onCall(MuleEventContext context) throws Exception {
		String output = "";
		HttpURLConnection conn = null;
		try {

			String URL = "http://mapas.valencia.es/lanzadera/gps/trafico/-2/-2";
			URL url = new URL(URL);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Basic amFpbWVsYWJvcmRhOmE4N1hEMEdr");

			AllData[] BD = null;
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String returned;
				System.out.println("Output from Server .... \n");
				while ((returned = br.readLine()) != null) {
					System.out.println(returned);
					BD = mapper.readValue(returned, AllData[].class);
					List<AllData> mcList = new ArrayList<AllData>(Arrays.asList(BD));

					for (Iterator<AllData> iterator = mcList.iterator(); iterator.hasNext();) {
						String mensaje = (String) iterator.next().getMensaje();
						output += mensaje + " ";
					}
				}

			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();
			return e.getMessage();
		} finally {
			conn.disconnect();

		}
		byte[] ptext = (output + " #SpaceApps #OpenData #traffic").getBytes(Charset.forName("ISO-8859-1"));
		String value = new String(ptext, Charset.forName("UTF-8"));
		System.out.println("TWITTEANDO: " + value);
		if(value.length() > 139){
			value = value.substring(0, 139);
		}
		
		return value;

	}
}
