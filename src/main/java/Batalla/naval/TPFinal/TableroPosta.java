package main.java.Batalla.naval.TPFinal;

import java.util.ArrayList;
import java.util.HashSet;

public class TableroPosta implements Subject {
	private final int FILAS = 10;
	private final int COLUMNAS = 10;
	private int[][] grillaJugador0;
	private int[][] grillaJugador1;
	private final int DEFAULT = 0;
	private final int AGUA = 1;
	private final int BARCOHIT = 2;
	private HashSet<Observer> observers;
	private boolean grillaCreada = false;

	public TableroPosta() {
		grillaJugador0 = new int[FILAS][COLUMNAS];
		grillaJugador1 = new int[FILAS][COLUMNAS];
		observers = new HashSet<Observer>();
	}

	public void crearGrilla() {

		if (!grillaCreada) {
			for (int i = 0; i < COLUMNAS; i++) {
				for (int j = 0; j < FILAS; j++) {
					grillaJugador0[j][i] = DEFAULT;
					grillaJugador1[j][i] = DEFAULT;
				}
			}
			grillaCreada = true;
			notifyObservers();
		}
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void detachObserver(Observer observer) {
		try {
			observers.remove(observer);
		} catch (NullPointerException e) {

		}
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	public int getFilas() {
		return FILAS;
	}

	public int getColumnas() {
		return COLUMNAS;
	}
}
