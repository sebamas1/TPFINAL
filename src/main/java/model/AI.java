package model;

import java.util.Random;

public class AI extends Humano implements Jugador {
  
  //private Tablero tablero;
  public int[] coord;
  private Random random;

  /**
   * Se le pasa el estado del tablero (que contiene el turno) para que
   * pueda calcular su movimiento.
   * @param board El tablero sobre el que va a hacer movimientos
   */
  public AI(Tablero board) {
    random = new Random();
    coord = new int[2];
    //this.tablero = board;
    this.nombre = "AI";
    this.playerID = 1;
  }
  
  /**
   * Devuelve las coordenadas para que el tablero ejecuta la accion que 
   * corresponda ahí.
   * @return 2 enteros
   */
  
  public int[] realizarTurno() {

    coord[0] = this.random.nextInt((9 - 0) + 1) + 0;
    coord[1] = this.random.nextInt((9 - 0) + 1) + 0;
    return coord;
  }
  
  
  
}
