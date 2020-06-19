package main.java.Batalla.naval.TPFinal;

import java.awt.event.MouseEvent;

public class Controler {
	private Tablero tablero;

	public Controler(Tablero tablero) {
		this.tablero = tablero;
		tablero.crearGrilla();
		
		
	}
	public void notifyEvent(MouseEvent e, int i, int j, int ID) {
		//hardcodeado para testear coloca barcos nada mas
		if(e.getButton() == 1 || e.getButton() == 3) {
			tablero.colocarBarco(e.getButton(), i, j, ID);
			tablero.notifyObservers();
		}
	}	
}
