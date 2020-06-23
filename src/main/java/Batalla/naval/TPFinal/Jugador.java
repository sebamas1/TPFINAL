package main.java.Batalla.naval.TPFinal;

public class Jugador {
	String nombre;
	int playerID;
	DisplayDelTablero display;

	public Jugador(String nombre, int playerID, DisplayDelTablero display) {
		this.nombre = nombre;
		this.playerID = playerID;
		this.display = display;
	}
}
