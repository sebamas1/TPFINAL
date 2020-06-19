package main.java.Batalla.naval.TPFinal;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Display extends JFrame implements Observer {
	private TableroPosta tablero;
	private static final Color ROJO = new Color(255,0,0);
	private int FILAS = 10;
	private int COLUMNAS = 10;
	private static final int SIZE_CASILLA = 45;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 1500;
	private static final int MARGEN_TOP_GRILLA1 = 10;
	private static final int MARGEN_LEFT_GRILLA1 = 10;
	private static final int MARGEN_TOP_GRILLA2 = 520;
	private static final int MARGEN_LEFT_GRILLA2 = 10;
	private final Controler controler;
	private Casilla[][] casillas0;
	private Casilla[][] casillas1;

	public Display() {
		this.setTitle("Batalla naval!");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setLayout(null);
		tablero = new TableroPosta();
		tablero.registerObserver(this);
		controler = new Controler(tablero);
		casillas0 = new Casilla[FILAS][COLUMNAS];
		casillas1 = new Casilla[FILAS][COLUMNAS];
		this.crearGrilla();
	}

	public void update() {
		colorearGrilla();
	}

	/**
	 * Crea las grillas de botones en la UI
	 */
	public void crearGrilla() {
		for (int i = 0; i < COLUMNAS; i++) {
			for (int j = 0; j < FILAS; j++) {
				Casilla casilla = new Casilla((MARGEN_LEFT_GRILLA1 + SIZE_CASILLA * i),
						(MARGEN_TOP_GRILLA1 + SIZE_CASILLA * j), 0, j, i);
				casillas0[j][i] = casilla;
				add(casilla);
			}
		}
		for (int i = 0; i < COLUMNAS; i++) {
			for (int j = 0; j < FILAS; j++) {
				Casilla casilla = new Casilla((MARGEN_LEFT_GRILLA2 + SIZE_CASILLA * i),
						(MARGEN_TOP_GRILLA2 + SIZE_CASILLA * j), 1, i, j);
				casillas1[j][i] = casilla;
				add(casilla);
			}
		}
	}
	
	/**
	 * Colorea la grilla segun los valores del tablero
	 */
	public void colorearGrilla() {
		int[][] tableroLogico0 = tablero.getGrilla0();
		for(int i=0; i< FILAS; i++) {
			for(int j=0; j<COLUMNAS; j++) {
				switch(tableroLogico0[i][j]) {
				case TableroPosta.BARCO:
					casillas0[i][j].setBackground(ROJO);
				}
			}
		}
	}
	

	
	@SuppressWarnings("serial")
	private class Casilla extends Button implements MouseListener {
		
		private int fila;
		private int columna;
		private int propietario;
		
		public Casilla(int x, int y, int jugador, int fil, int col) {
			this.setPropietario(jugador);
			this.setBounds(x, y, SIZE_CASILLA, SIZE_CASILLA);
			this.setFila(fil);
			this.setColumna(col);
			this.addMouseListener(this);
		}
		
		public void setPropietario(int a) {
			this.propietario = a;
		}
		
		public int getPropietario() {
			return this.propietario;
		}
		
		public void setFila(int a) {
			this.fila = a;
		}
		
		public void setColumna(int b) {
			this.columna = b;
		}

		public void mouseClicked(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {
			//Le avisa al controlador de que se solto el click, en que fila, columna y de que grilla
			controler.notifyEvent(e, this.fila, this.columna, this.propietario);
		}
	}
}