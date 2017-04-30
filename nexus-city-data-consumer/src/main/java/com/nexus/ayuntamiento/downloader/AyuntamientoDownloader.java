package com.nexus.ayuntamiento.downloader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import com.nexus.ayuntamiento.model.Punto;
import com.nexus.ayuntamiento.model.Variables;

public class AyuntamientoDownloader implements Callable {

	HttpURLConnection conn = null;
	@Override
	public Object onCall(MuleEventContext context) throws Exception {

		try {
			
			Variables variable = new Variables();
			variable.initializeCoordenadas();
			ArrayList<Punto> coordenadas = variable.getCoordenadas();
			
			for (Iterator<Punto> iterator = coordenadas.iterator(); iterator.hasNext();) {
				Punto punto = (Punto) iterator.next();
				
				System.out.println(punto.toString());
				String URL = "http://mapas.valencia.es/lanzadera/gps/trafico/" + punto.getiLatitud()+ "/" + punto.getiLongitud();
				System.out.println("Peticion al ayuntamiento Podemita: "+URL);
				URL url = new URL(URL);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Authorization", "Basic amFpbWVsYWJvcmRhOmE4N1hEMEdr");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			return e.getMessage();
		}finally{
			conn.disconnect();
			
		}

		return "";

	}
}
