package main.java.Batalla.naval.TPFinal;
import barcos.java.Batalla.naval.TPFinal.*;
public class Tablero implements Observer {

	public Tablero() {
		DisplayDelTablero tablero = new DisplayDelTablero();
		tablero.registerObserver(this);
		Barco corbeta1 = new Corbeta();
		Barco corbeta2 = new Corbeta();
		Barco corbeta3 = new Corbeta();
		Barco fragata1 = new Fragata();
		Barco fragata2 = new Fragata();
		Barco destructor1 = new Destructor();
	}

	public void update() {

	}
    
	public void checkDisparo(int a, int b) {
		// TODO implement here
	}

	/**
	 * @param int
	 * @param int
	 */
	public void getBarco(int a, int b) {
		// TODO implement here
	}

}
