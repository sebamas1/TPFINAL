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

  /**
   * titulo. doc
   */

  public Tablero() {

    jugador0 = new Jugador("JP", 0);
    jugador1 = new Jugador("Seba", 1);
    grillaJugador0 = new int[FILAS][COLUMNAS];
    grillaJugador1 = new int[FILAS][COLUMNAS];
    observers = new HashSet<Observer>();
  }

  public void setGrillaJugador0(int[][] grillaJugador0) {
    this.grillaJugador0 = grillaJugador0;
  }

  /**
   * Crea la grilla donde se manjea la logica si no esta creada la crea.
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

  /**
   * Remueve observer.
   * 
   */

  public void detachObserver(Observer observer) {
    try {
      observers.remove(observer);
    } catch (NullPointerException e) {
      ;
    }
  }

  /**
   * Notifica observers.
   * 
   */

  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }

  /**
   * Decide que evento ejecutar y luego lo ejecuta, 
   * Hay que ver cuando queremos que haga el disparo especial
   * @param click El mouseEvent
   * @param i     En que fila esta la casilla
   * @param j     En que columna esta la casilla
   * @param id    En que grilla esta la casilla
   */
  public void dispararEventoEnGrilla(int click, int i, int j, int id) {
    if (turno < Jugador.CANT_BARCOS * 2) {
      deltaGrillaBehaviour = new ColocarBarcos(this);
      deltaGrillaBehaviour.realizarAccion(click, i, j, id);
    } else {
      if (this.getTurno() != 20) {
        deltaGrillaBehaviour = new RealizarDisparo(this);
      } else {
        deltaGrillaBehaviour = new RealizarDisparoEspecial(this);
      }
      deltaGrillaBehaviour.realizarAccion(click, i, j, id);

    }
    notifyObservers();
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
        // System.out.print(grillaJugador0[i][j] + " ");
      }
      // System.out.println();
    }
    System.out.println();
    System.out.println();
    for (int i = 0; i < FILAS; i++) {
      for (int j = 0; j < COLUMNAS; j++) {
        // System.out.print(grillaJugador1[i][j] + " ");
      }
      // System.out.println();
    }
  }

  /**
   * Le das la fila y columna de una casilla que tiene un barco y te devuelve el
   * barco que esta en esa casilla.
   * 
   * @param x fila
   * @param y columna
   * @return
   */
  public Barco encontrarBarco(int x, int y) {

    int xi;
    int xf;
    int yi;
    int yf;
    Jugador jugador = this.getTurno() % 2 == 0 ? this.getJugador1() : this.getJugador0();

    for (int i = 0; i < jugador.getBarcos().size(); i++) {
      xi = jugador.getBarcos().get(i).getPos().getInicialX();
      xf = jugador.getBarcos().get(i).getPos().getFinalX();
      yi = jugador.getBarcos().get(i).getPos().getInicialY();
      yf = jugador.getBarcos().get(i).getPos().getFinalY();
      boolean variaFilas = yi == yf ? true : false;
      for (int j = 0; j < jugador.getBarcos().get(i).getSize(); j++) {
        if (variaFilas) {
          if (xi + j == x && yi == y) {
            return jugador.getBarcos().get(i);
          }
        } else {
          if (xi == x && yi + j == y) {
            return jugador.getBarcos().get(i);
          }
        }
      }
    }
//    System.out.println("\n\n\n SE ROMPIO");
    return null;
  }

}
