package com.nexus.ayuntamiento.model;

import java.util.ArrayList;

public class Variables {
	ArrayList<Punto> coordenadas = new ArrayList<Punto>();
	
	public void initializeCoordenadas(){
		//palacio de congresos
		Punto A = new Punto(39.497769, -0.4075594);
		A.setName("A");
		A.addTramo("A82");
		addCoordenadas(A);
		
		//media markt beniferri
		Punto B = new Punto(39.494713, -0.4046657);
		B.setName("B");
		B.addTramo("A60");
		B.addTramo("A377");
		addCoordenadas(B);
		
		/*//maestro rodrigo
		Punto C = new Punto(39.491701, -0.4102897);
		C.setName("C");
		C.addTramo("A82");
		C.addTramo("A219");
		addCoordenadas(C);*/
		
		Punto C = new Punto(39.4813262, -0.3534647);
		C.setName("C");
		C.addTramo("A296");
		C.addTramo("A165");
		C.addTramo("A3");
		C.addTramo("A287");
		addCoordenadas(C);
		
		//puente de aragon
		Punto D = new Punto(39.468758, -0.3661737);
		D.setName("D");
		D.addTramo("A278");
		D.addTramo("A121");
		D.addTramo("A136");
		D.addTramo("A266");
		addCoordenadas(D);
		
		//puente del angel custodio
		Punto E = new Punto(39.463095, -0.3629037);
		E.setName("E");
		E.addTramo("A136");
		E.addTramo("A280");
		E.addTramo("A188");
		E.addTramo("A15");
		addCoordenadas(E);
		
		/*//AV de giorgeta
		Punto F = new Punto(39.455425, -0.3828627);
		F.setName("F");
		F.addTramo("A373");
		F.addTramo("A372");
		F.addTramo("A408");
		addCoordenadas(F);*/
		
		//CV-5000
		Punto F = new Punto(39.453347, -0.3540817);
		F.setName("G");
		F.addTramo("B111");
		F.addTramo("A30");
		F.addTramo("A409");
		F.addTramo("A357");
		addCoordenadas(F);
		
		//V30
		Punto G = new Punto(39.438276, -0.3720757);
		G.setName("G");
		G.addTramo("A101");
		addCoordenadas(G);
		
		/*//V31
		Punto J = new Punto(39.432427, -0.3731837);
		J.setName("J");
		J.addTramo("A373");
		addCoordenadas(J);*/
		
	}
	
	public void addCoordenadas(Punto p) {
		coordenadas.add(p);
	}
	
	public ArrayList<Punto> getCoordenadas() {
		return coordenadas;
	}

	
}
