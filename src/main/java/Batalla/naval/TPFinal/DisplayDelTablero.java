package main.java.Batalla.naval.TPFinal;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

import javax.swing.JFrame;

public class DisplayDelTablero implements Subject {
	private HashSet<Observer> observers;

	public DisplayDelTablero() {
		Grilla grilla = new Grilla();
		observers = new HashSet<Observer>();
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void detachObserver(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	private class Casilla extends Button implements MouseListener {
		private int propiedadCasilla;
		private boolean atacada = false;
		private boolean ocupado = false;

		public Casilla(int x, int y, int height, int width, int jugador) throws IllegalArgumentException {
			if (jugador == 0 || jugador == 1) {
				this.propiedadCasilla = jugador;
			} else
				throw new IllegalArgumentException("Ingresar jugador 0 o jugador 1");
			setBounds(x, y, height, width);
			addMouseListener(this);
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
			if (!ocupado) {
				setBackground(new Color(220, 0, 0));
			} else
				setBackground(new Color(0, 0, 220));

		}

		public boolean getOcupada() {
			return ocupado;
		}

		public boolean getAtacada() {
			return atacada;
		}

		public void setOcupada() {
			ocupado = true;
		}

		public void setAtacada() {
			atacada = true;
		}
	}

	private class Grilla extends JFrame {
		private final int FILAS = 10;
		private final int COLUMNAS = 10;
		private final int sizeCasilla = 45;
		private int WIDTH = 800;
		private int HEIGHT = 1500;

		public Grilla() {
			setTitle("Batalla naval!");
			setSize(WIDTH, HEIGHT);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			setLocationRelativeTo(null);
			setVisible(true);
			setLayout(null);
			for (int i = 0; i < COLUMNAS; i++) {
				for (int j = 0; j < FILAS; j++) {
					add(new Casilla((10 + sizeCasilla * i), (10 + sizeCasilla * j), sizeCasilla, sizeCasilla, 0));
				}
			}
			for (int i = 0; i < COLUMNAS; i++) {
				for (int j = 0; j < FILAS; j++) {
					add(new Casilla((10 + sizeCasilla * i), (520 + sizeCasilla * j), sizeCasilla, sizeCasilla, 1));
				}
			}

		}
	}
}