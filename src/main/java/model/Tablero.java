package model;

import java.util.ArrayList;
import java.util.HashSet;
import view.Observer;

public class Tablero implements Subject {
  public static final int FILAS = 10;
  public static final int COLUMNAS = 10;
  private int[][] grillaJugador0;
  private int[][] grillaJugador1;
  private Jugador jugador0 = null;
  private Jugador jugador1 = null;
  private ArrayList<Barco> barcosJug0;
  private ArrayList<Barco> barcosJug1;
  public static final int BARCO = 0;
  public static final int AGUA = 1;
  public static final int BARCO_HIT = 2;
  public static final int AGUA_MISS = 3;
  private HashSet<Observer> observers;
  private boolean grillaCreada = false;
  private int turno;
  private AccionBehavior deltaGrillaBehaviour;
  private int turnoJugador;
  private boolean enableNuclear = false;

  /**
   * titulo. doc
   */

  public Tablero() {

    grillaJugador0 = new int[FILAS][COLUMNAS];
    grillaJugador1 = new int[FILAS][COLUMNAS];
    barcosJug0 = new ArrayList<Barco>();
    barcosJug1 = new ArrayList<Barco>();
    this.turno = 0;
    jugador0 = new Humano("Jugador humano", 0);
    jugador1 = new AI(this);
    observers = new HashSet<Observer>();
    for (int i = 0; i < 2; i++) {
      ArrayList<Barco> aux = i == 0 ? barcosJug0 : barcosJug1;
      aux.add(new Barco(2, "Corbeta"));
      //aux.add(new Barco(2, "Corbeta"));
      aux.add(new Barco(3, "Fragata"));
      aux.add(new Barco(3, "Fragata"));
      aux.add(new Barco(4, "Destructor"));
      aux.add(new Barco(5, "Portaaviones"));
    }
  }

  public void setGrillaJugador0(int[][] grillaJugador0) {
    this.grillaJugador0 = grillaJugador0;
  }

  /**
   * Crea la grilla donde se manjea la logica si no esta creada la crea.
   */
  public void crearGrillas() {

    if (!grillaCreada) {
      for (int i = 0; i < COLUMNAS; i++) {
        for (int j = 0; j < FILAS; j++) {
          grillaJugador0[j][i] = Tablero.AGUA;
          grillaJugador1[j][i] = Tablero.AGUA;
        }
      }
      grillaCreada = true;
      notifyObservers(new Evento(Evento.GRILLA_CREADA, 0));
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

  public void notifyObservers(Evento evento) {
    for (Observer observer : observers) {
      observer.update(evento);
    }
  }

  /**
   * Decide que evento ejecutar y luego lo ejecuta, Hay que ver cuando queremos
   * que haga el disparo especial.
   * 
   * @param click El mouseEvent
   * @param i     En que fila esta la casilla
   * @param j     En que columna esta la casilla
   * @param id    En que grilla esta la casilla
   */

  // gran parte de esto hay que meterlo en el controler
  public void dispararEventoEnGrilla(int click, int i, int j, int id) {

    this.turnoJugador = this.getTurno() % 2;
    if (turno < Humano.CANT_BARCOS * 2) {
      deltaGrillaBehaviour = new ColocarBarcos(this);
      deltaGrillaBehaviour.realizarAccion(click, i, j, id);
    } else {
      if (enableNuclear) {
        deltaGrillaBehaviour = new RealizarDisparoEspecial(this);
      } else {
        deltaGrillaBehaviour = new RealizarDisparo(this);
      }
      deltaGrillaBehaviour.realizarAccion(click, i, j, id);
    }

    if (terminoPartida()) {
      Jugador jugador = this.encontrarGanador();
      System.out.println("GANO: " + jugador.getNombre());
      notifyObservers(new Evento(Evento.TERMINO_PARTIDA, jugador.getPlayerID()));
      return;
    }

    this.turnoMaquina();

    if (terminoPartida()) {
      Jugador jugador = this.encontrarGanador();
      System.out.println("GANO: " + jugador.getNombre());
      notifyObservers(new Evento(Evento.TERMINO_PARTIDA, jugador.getPlayerID()));
      return;
    }
  }

  /**
   * Resetea las grillas y los arrays de barcos.
   */
  public void resetearJuego() {
    turno = 0;
    this.setEnableNuclear(false);
    for (int i = 0; i < COLUMNAS; i++) {
      for (int j = 0; j < FILAS; j++) {
        grillaJugador0[i][j] = Tablero.AGUA;
        grillaJugador1[i][j] = Tablero.AGUA;
      }
    }
    barcosJug0.clear();
    barcosJug1.clear();
    for (int i = 0; i < 2; i++) {
      ArrayList<Barco> aux = i == 0 ? barcosJug0 : barcosJug1;
      aux.add(new Barco(2, "Corbeta"));
      //aux.add(new Barco(2, "Corbeta"));
      aux.add(new Barco(3, "Fragata"));
      aux.add(new Barco(3, "Fragata"));
      aux.add(new Barco(4, "Destructor"));
      aux.add(new Barco(5, "Portaaviones"));
    }
    notifyObservers(new Evento(Evento.REINICIA_JUEGO, 0));
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

  public void setJugador0(Humano jugador0) {
    this.jugador0 = jugador0;
  }

  public Jugador getJugador1() {
    return jugador1;
  }

  public void setJugador1(Humano jugador1) {
    this.jugador1 = jugador1;
  }

  /**
   * para debuggear porque ver una matriz con el debugger es muy desagradable.
   */
  public void printMatriz() {
    for (int i = 0; i < FILAS; i++) {
      for (int j = 0; j < COLUMNAS; j++) {
        System.out.print(grillaJugador0[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
    System.out.println();
    for (int i = 0; i < FILAS; i++) {
      for (int j = 0; j < COLUMNAS; j++) {
        System.out.print(grillaJugador1[i][j] + " ");
      }
      System.out.println();
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
  public Barco encontrarBarco(int x, int y, int idCasilla) {

    int xi;
    int yi;
    int yf;
    ArrayList<Barco> barcosList = idCasilla == 0 ? barcosJug0 : barcosJug1;

    for (int i = 0; i < barcosList.size(); i++) {
      xi = barcosList.get(i).getPos().getInicialX();
      yi = barcosList.get(i).getPos().getInicialY();
      yf = barcosList.get(i).getPos().getFinalY();
      boolean variaFilas = yi == yf ? true : false;
      for (int j = 0; j < barcosList.get(i).getSize(); j++) {
        if (variaFilas) {
          if (xi + j == x && yi == y) {
            return barcosList.get(i);
          }
        } else {
          if (xi == x && yi + j == y) {
            return barcosList.get(i);
          }
        }
      }
    }
    System.out.println("no deberia ejecutarme nunca");
    return null;
  }

  public ArrayList<Barco> getBarcosJug0() {
    return barcosJug0;
  }

  public void setBarcosJug0(ArrayList<Barco> barcosJug0) {
    this.barcosJug0 = barcosJug0;
  }

  public ArrayList<Barco> getBarcosJug1() {
    return barcosJug1;
  }

  public void setBarcosJug1(ArrayList<Barco> barcosJug1) {
    this.barcosJug1 = barcosJug1;
  }

  /**
   * Chequea si termino la partida.
   * 
   * @return booleano true si termino.
   */

  public boolean terminoPartida() {
    boolean perdio0 = true;
    boolean perdio1 = true;
    for (int i = 0; i < Jugador.CANT_BARCOS; i++) {
      if (this.barcosJug0.get(i).getVida() != 0) {
        perdio0 = false;
      }
      if (this.barcosJug1.get(i).getVida() != 0) {
        perdio1 = false;
      }
    }
    return perdio0 || perdio1;
  }

  /**
   * Encuentra al ganador.
   * 
   * @return Devuelve al jugador ganador
   */
  public Jugador encontrarGanador() {

    boolean perdio0 = this.perdio(this.getBarcosJug0());
    boolean perdio1 = this.perdio(this.getBarcosJug1());
    if (perdio0 && !perdio1) {
      return jugador1;
    } else if (perdio1 && !perdio0) {
      return jugador0;
    } else {
      System.out.println("upa encontrarGanador()");
      return null;
    }
  }

  public HashSet<Observer> getObservers() {
    return observers;
  }

  public void setObservers(HashSet<Observer> observers) {

    this.observers = observers;
  }

  /**
   * Chequea si la lista de barcos es de un perdedor.
   * 
   * @param barcos Lista a chequear
   * @return true si es de un perdedor
   */
  public boolean perdio(ArrayList<Barco> barcos) {
    for (int i = 0; i < barcos.size(); i++) {
      if (barcos.get(i).getVida() > 0) {
        return false;
      }
    }
    return true;
  }

  public void setTurnoJugador(int jugador) {
    this.turnoJugador = jugador;
  }

  public int getTurnoJugador() {
    return this.turnoJugador;
  }

  private void turnoMaquina() {
    int aux;
    int[] coord;
    while (this.getTurno() % 2 == 1) {
      this.turnoJugador = 1;
      aux = turno < Humano.CANT_BARCOS * 2 ? jugador1.getPlayerID() : jugador0.getPlayerID();
      coord = jugador1.realizarTurno();
      if (deltaGrillaBehaviour.esValido(coord[0], coord[1], coord[2], aux)) {
        deltaGrillaBehaviour.realizarAccion(coord[0], coord[1], coord[2], aux);
      }
    }
  }

  public void setEnableNuclear(boolean enableNuclear) {
    this.enableNuclear = enableNuclear;
  }
  
  public boolean getEnableNuclear() {
    return this.enableNuclear;
  }
}
