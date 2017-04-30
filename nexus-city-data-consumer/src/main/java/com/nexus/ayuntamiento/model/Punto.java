package com.nexus.ayuntamiento.model;

import java.util.ArrayList;

public class Punto {
	private double longitud;
	private String name;
	private double latitud;
	private ArrayList<String> tramos = new ArrayList<String>();
	private double diezExp6 = Math.pow(10,6);
	
	public Punto(double longitud, double latitud) {
		super();
		this.longitud = longitud;
		this.latitud = latitud;
	}

	public ArrayList<String> getTramos() {
		return tramos;
	}

	public void addTramo(String tramo) {
		this.tramos.add(tramo);
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public int getiLongitud() {
		double exp = longitud * diezExp6;

		return (int) exp;
	}

	public int getiLatitud() {
		double exp = latitud * diezExp6;

		return (int) exp;
	}
	
	public String toString(){
		return "Descargamos Longitud: " + getiLongitud() + " latitud: " + getiLatitud();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
