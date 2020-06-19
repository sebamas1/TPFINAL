package main.java.Batalla.naval.TPFinal;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;



public class Tablero implements Subject {
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

  /** titulo.
   * doc
   */
  
  public Tablero() {
    
    jugador0 = new Jugador("JP", 0);
    grillaJugador0 = new int[FILAS][COLUMNAS];
    grillaJugador1 = new int[FILAS][COLUMNAS];
    observers = new HashSet<Observer>();
    turno = 0;
  }

  /** Crea la grilla donde se manjea la logica
   *  si no esta creada la crea.
   */
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

  /** Remueve observer.
   * 
   */
  
  public void detachObserver(Observer observer) {
    try {
      observers.remove(observer);
    } catch (NullPointerException e) {
      ;
    }
  }

  /** Notifica observers.
   * 
   */
  
  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }
  
  public int[][] getGrilla0() {
    return this.grillaJugador0;
  }
  
  public int getTurno() {
    return turno;
  }

  public void setTurno(int turno) {
    this.turno = turno;
  }

  public int getFilas() {
    return FILAS;
  }

  public int getColumnas() {
    return COLUMNAS;
  }

  public int[][] getGrillaJugador1() {
    return grillaJugador1;
  }

  public void setGrillaJugador1(int[][] grillaJugador1) {

    this.grillaJugador1 = grillaJugador1;
  }
  

  public Jugador getJugador0() {
    return jugador0;
  }

  public void setJugador0(Jugador jugador0) {
    this.jugador0 = jugador0;
  }

  public Jugador getJugador1() {
    return jugador1;
  }

  public void setJugador1(Jugador jugador1) {
    this.jugador1 = jugador1;
  }

  


}



