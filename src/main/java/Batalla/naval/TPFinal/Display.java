package main.java.Batalla.naval.TPFinal;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Display extends JFrame implements Observer {
	private TableroPosta tablero;
	private int FILAS;
	private int COLUMNAS;
	private static final int SIZE_CASILLA = 45;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 1500;
	private static final int MARGEN_TOP_GRILLA1 = 10;
	private static final int MARGEN_LEFT_GRILLA1 = 10;
	private static final int MARGEN_TOP_GRILLA2 = 520;
	private static final int MARGEN_LEFT_GRILLA2 = 10;
	private final Controler controler;
	private static final Color ROJO = new Color(220, 0, 0);
	private Casilla[][] casillas0;
	private Casilla[][] casillas1;

	public Display() {
		setTitle("Batalla naval!");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(null);
		tablero = new TableroPosta();
		tablero.registerObserver(this);
		controler = new Controler(tablero);
		casillas0 = new Casilla[FILAS][COLUMNAS];
		casillas1 = new Casilla[FILAS][COLUMNAS];
		crearGrilla();
	}

	public void update() {
		COLUMNAS = tablero.getColumnas();
		FILAS = tablero.getFilas();
	}

	public void crearGrilla() {
		for (int i = 0; i < COLUMNAS; i++) {
			for (int j = 0; j < FILAS; j++) {
				Casilla casilla = new Casilla((MARGEN_LEFT_GRILLA1 + SIZE_CASILLA * i),
						(MARGEN_TOP_GRILLA1 + SIZE_CASILLA * j), 0);
				casillas0[j][i] = casilla;
				add(casilla);
			}
		}
		for (int i = 0; i < COLUMNAS; i++) {
			for (int j = 0; j < FILAS; j++) {
				Casilla casilla = new Casilla((MARGEN_LEFT_GRILLA2 + SIZE_CASILLA * i),
						(MARGEN_TOP_GRILLA2 + SIZE_CASILLA * j), 1);
				casillas0[j][i] = casilla;
				add(casilla);
			}
		}
	}

	@SuppressWarnings("serial")
	private class Casilla extends Button implements MouseListener {
		public Casilla(int x, int y, int jugador) {
			this.setBounds(x, y, SIZE_CASILLA, SIZE_CASILLA);
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
        controler.notifyEvent(e);
		}
	}
}