package main.java.Batalla.naval.TPFinal;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;



public class TableroPosta implements Subject {
	private static final int FILAS = 10;
	private static final int COLUMNAS = 10;
	private int[][] grillaJugador0;
	private int[][] grillaJugador1;
	private Jugador jugador0;
	private Jugador jugador1;
	public static final int BARCO = 0;
	public static final int AGUA = 1;
	public static final int BARCO_HIT = 2;
	public static final int AGUA_MISS = 2;
	private HashSet<Observer> observers;
	private boolean grillaCreada = false;
	private int turno;
	private int nroTurno;

	public TableroPosta() {
		
		jugador0 = new Jugador("JP", 0);
		grillaJugador0 = new int[FILAS][COLUMNAS];
		grillaJugador1 = new int[FILAS][COLUMNAS];
		observers = new HashSet<Observer>();
		turno = 0;
	}

	public void crearGrilla() {

		if (!grillaCreada) {
			for (int i = 0; i < COLUMNAS; i++) {
				for (int j = 0; j < FILAS; j++) {
					grillaJugador0[j][i] = 1;
					grillaJugador1[j][i] = 1;
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
	
	public int[][] getGrilla0(){
		return this.grillaJugador0;
	}

	public int getFilas() {
		return FILAS;
	}

	public int getColumnas() {
		return COLUMNAS;
	}

	/**
	 * Chequea que no el barco entre en el espacio que hay hasta el borde de la grilla
	 * Chequea que el movimiento sea hecho en la grilla del jugador
	 * Chequea que no se puedan poner barcos que colisionarian con otros
	 * @param boton Que boton se apreto, para saber si chequeo a la derecha o abajo
	 * @param row   En que fila esta el boton 
	 * @param column En que columna esta el boton
	 * @param IDCasilla En que grilla esta el boton
	 * @return boolean
	 */
	public boolean esValido(int boton, int row, int column, int IDCasilla) {
		
		if(turno != IDCasilla) return false;
		int [][] grilla = turno == 0 ? grillaJugador0 : grillaJugador1;
		ArrayList<Barco> barcosJug = turno == 0? jugador0.getBarcos() : jugador1.getBarcos();
		
		//si es click izquierdo chequeo a la derecha de la casilla
		if(boton == 1) {
			for(int i=0; i < barcosJug.get(0).getSize() ; i++) {                //hardcpdeado chequea con el primer barco del array
				try {
					if(grilla[row+i][column] == BARCO) {
						return false;
					}
				}catch(ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
		}else if(boton == 3) {
			for(int i=0; i < barcosJug.get(0).getSize() ; i++) {
				try {
					if(grilla[row][column+i] == BARCO) {
						return false;
					}
				}catch(ArrayIndexOutOfBoundsException e){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Cambia el valor de las celdas logicas
	 * deja todo listo para que notifiquemos al display y coloree como corresponda
	 * @param boton Que boton se apreto, para saber si chequeo a la derecha o abajo
	 * @param row   En que fila esta el boton 
	 * @param column En que columna esta el boton
	 * @param IDCasilla En que grilla esta el boton
	 */
	public void colocarBarco(int boton, int row, int column, int IDCasilla) {
		
		if(!esValido(boton, row, column, IDCasilla)) {
			return;
		}
		int [][] grilla = turno == 0 ? grillaJugador0 : grillaJugador1;
		ArrayList<Barco> barcosJug = turno == 0? jugador0.getBarcos() : jugador1.getBarcos();
		if(boton == 1) {
			for(int i=0; i<barcosJug.get(0).getSize(); i++) {
				grilla[row+i][column] = BARCO;
			}
		}else if(boton == 3) {
			for(int i=0; i<barcosJug.get(0).getSize(); i++) {
				grilla[row][column+i] = BARCO;
			}
		}
	}

}



