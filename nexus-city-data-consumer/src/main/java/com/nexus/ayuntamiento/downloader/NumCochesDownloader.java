package com.nexus.ayuntamiento.downloader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import com.nexus.ayuntamiento.model.Example;
import com.nexus.ayuntamiento.model.Feature;
import com.nexus.ayuntamiento.model.Punto;
import com.nexus.ayuntamiento.model.Variables;

public class NumCochesDownloader implements Callable {

	BufferedWriter writer = null;
	File logFile = null;
	
	@Override
	public Object onCall(MuleEventContext context) throws Exception {
		String toSlack = "";
		try {

			Variables variable = new Variables();
			variable.initializeCoordenadas();
			ArrayList<Punto> coordenadas = variable.getCoordenadas();
			
			String URL = "http://mapas.valencia.es/lanzadera/opendata/Tra-intensidad-trafico/JSON";

			Example BD = null;
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(
				    DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				BD = mapper.readValue(new URL(URL), Example.class);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			String output = "";
			int sum = 0;
			//create a temporary file
            String timeLog = "numero_coches";
            if(logFile == null){
            	 logFile = new File(timeLog + ".csv");
            }
           
            if (writer == null){
            	writer = new BufferedWriter(new FileWriter(logFile, true));
            }
            
            
            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());
			String hoy = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
			for (Iterator<Punto> iterator = coordenadas.iterator(); iterator.hasNext();) {
				Punto punto = (Punto) iterator.next();
				System.out.println(punto.toString());
				sum = 0;
				for (Iterator<String> iterator2 = punto.getTramos().iterator(); iterator2.hasNext();) {
					String tramo = (String) iterator2.next();
					int coches = 0;
					
					for (Iterator<Feature> iterator3 = BD.getFeatures().iterator(); iterator3.hasNext();) {
						Feature feature = (Feature) iterator3.next();
						if(tramo.trim().equals(feature.getProperties().getIdtramo().trim())){
							coches = Integer.parseInt(feature.getProperties().getLectura());
							System.out.println("\t Tramo: "+feature.getProperties().getIdtramo() + " Coches: "+ coches);
							break;
						}
						
						
					}
					sum += coches;
				}
				System.out.println("TOTAL PUNTO: "+ sum);
	           
	            output = punto.getName() + ", " + sum + ", " + hoy;
	            System.out.println(output + "\n");
	            toSlack += output + "\n";
	            writer.write(output+ "\n");
				
			}

		} catch (Exception e) {

			e.printStackTrace();
			return e.getMessage();
		}finally{
			// Close the writer regardless of what happens...
            writer.close();
		}

		return toSlack;

	}
}
