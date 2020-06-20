package main.java.Batalla.naval.TPFinal;

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
  public static final int AGUA_MISS = 3;
  private HashSet<Observer> observers;
  private boolean grillaCreada = false;
  private int turno = 0;
  private int nroTurno;
  private AccionBehavior deltaGrillaBehaviour;
  /** titulo.
   * doc
   */
  
  public Tablero() {
    
    jugador0 = new Jugador("JP", 0);
    jugador1 = new Jugador("Seba", 1);
    grillaJugador0 = new int[FILAS][COLUMNAS];
    grillaJugador1 = new int[FILAS][COLUMNAS];
    observers = new HashSet<Observer>();
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
  public void dispararEventoEnGrilla(int click, int i, int j, int id) {
    if(turno < (jugador0.getBarcos().size() + jugador0.getBarcos().size())){
      deltaGrillaBehaviour = new ColocarBarcos(this);
      if(turno % 2 == 0) {
        deltaGrillaBehaviour.realizarAccion(click, i, j, id);
      } else deltaGrillaBehaviour.realizarAccion(click, i, j, id);
      notifyObservers();
    } else {
      deltaGrillaBehaviour = new RealizarDisparo(this);
      notifyObservers();
    }
  }
  
  public int[][] getGrillaJugador0() {
    return this.grillaJugador0;
  }
  
  public int[][] getGrillaJugador1() {
    return grillaJugador1;
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

  public void printMatriz() {
    for (int i = 0; i < FILAS; i++) {
      for (int j = 0; j < COLUMNAS; j++) {
       // System.out.print(grillaJugador0[i][j] + "  ");
      }
     // System.out.println();
    }
    System.out.println();      System.out.println();
    for (int i = 0; i < FILAS; i++) {
      for (int j = 0; j < COLUMNAS; j++) {
       // System.out.print(grillaJugador1[i][j] + "  ");
      }
     // System.out.println();
    }
  }
  


}



