package main.java.Batalla.naval.TPFinal;

import java.util.ArrayList;

public class Jugador {
	
	public static final int CANT_BARCOS = 4;
	private String nombre;
	private int playerID;
	private ArrayList<Barco> barcos;

	public Jugador(String nombre, int playerID) {
		
		this.barcos = new ArrayList<Barco>();
		BarcosFactory factory = new BarcosFactory();
		this.nombre = nombre;
		this.playerID = playerID;
		barcos.add(factory.createBarco("corbeta"));
		barcos.add(factory.createBarco("corbeta"));
		barcos.add(factory.createBarco("corbeta"));
		barcos.add(factory.createBarco("destructor"));
	}
	
	public ArrayList<Barco> getBarcos(){
		return this.barcos;
	}
}
