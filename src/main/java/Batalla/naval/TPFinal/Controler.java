package main.java.Batalla.naval.TPFinal;

import java.awt.event.MouseEvent;

public class Controler {
	private TableroPosta tablero;

	public Controler(TableroPosta tablero) {
		this.tablero = tablero;
		tablero.crearGrilla();
	}
	public void notifyEvent(MouseEvent e) {
		
	}
}
