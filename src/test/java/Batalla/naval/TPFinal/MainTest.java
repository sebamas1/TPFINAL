package test.java.Batalla.naval.TPFinal;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.java.Batalla.naval.TPFinal.Display;
import main.java.Batalla.naval.TPFinal.Tablero;

class MainTest {
	private Robot bot;

	@Test
	void test() throws AWTException {
		
		Display display = new Display();
		bot = new Robot();
		Tablero tablero = display.getTablero();
		ArrayList<Coordenada> listaChequearEmpty = new ArrayList<Coordenada>();
		ArrayList<Coordenada> listaChequearRojo = new ArrayList<Coordenada>();
		boolean izq = true;
		

		this.clickCasilla(new Coordenada(9, 9), izq);
		this.clickCasilla(new Coordenada(8, 8), izq);
		this.clickCasilla(new Coordenada(7, 8), izq);
		izq = false;
		this.clickCasilla(new Coordenada(0, 9), izq);
		this.clickCasilla(new Coordenada(1, 8), izq);
		this.clickCasilla(new Coordenada(1, 7), izq);
		bot.delay(50);      							//sin esto no funca
		listaChequearEmpty.add(new Coordenada(9, 9));
		listaChequearEmpty.add(new Coordenada(8, 7));
		listaChequearEmpty.add(new Coordenada(0, 9));
		listaChequearEmpty.add(new Coordenada(1, 7));
		listaChequearRojo.add(new Coordenada(8, 8));
		listaChequearRojo.add(new Coordenada(9, 8));
		listaChequearRojo.add(new Coordenada(1, 8));
		listaChequearRojo.add(new Coordenada(1, 9));
		

		for(int i=0; i<listaChequearEmpty.size(); i++) {
			Coordenada aux = listaChequearEmpty.get(i);
			int fila = aux.getFila();
			int col = aux.getColumna();
			if(tablero.getGrilla0()[fila][col] != Tablero.AGUA 
					|| display.getCasillas0()[fila][col].getBackground() == Display.ROJO) {
				fail("se marco/coloreo algo que no se tenia que marcar en el tablero/UI");
			}
		}
		
		
		for(int i=0; i < listaChequearRojo.size(); i++) {
			Coordenada aux = listaChequearRojo.get(i);
			int fila = aux.getFila();  
			int col = aux.getColumna();
			if(tablero.getGrilla0()[fila][col] != Tablero.BARCO
					|| display.getCasillas0()[fila][col].getBackground() != Display.ROJO) {
				fail("no se marco/coloreo algo que se tenia que marcar en el tablero/UI");
			}
		}
		
		//fail("Not yet implemented");
	}
	
	@SuppressWarnings("deprecation")
	void clickCasilla(Coordenada coord, boolean izq) {
		int button = izq? InputEvent.BUTTON1_MASK : InputEvent.BUTTON3_MASK;
		bot.mouseMove(420 + Display.SIZE_CASILLA * coord.getColumna(), 70 + Display.SIZE_CASILLA * coord.getFila());
		bot.mousePress(button);
		bot.mouseRelease(button);
	}

	
	private class Coordenada{
		private int fila;
		private int columna;
		
		Coordenada(int i, int j){
			this.fila = i;
			this.columna = j;
		}
		
		public int getFila() {
			return fila;
		}
		public void setFila(int fila) {
			this.fila = fila;
		}
		public int getColumna() {
			return columna;
		}
		public void setColumna(int columna) {
			this.columna = columna;
		}
		
		
	}
}





