package model;

import java.util.Random;

public class AI extends Humano{
  
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
    coord = new int[3];
    //this.tablero = board;
    this.nombre = "R2-D2";
    this.playerID = 1;
  }
  
  /**
   * Devuelve las coordenadas para que el tablero ejecuta la accion que 
   * corresponda ah�.
   * @return 2 enteros
   */
  
  public int[] realizarTurno() {

    coord[0] = this.next();
    coord[1] = this.random.nextInt((9 - 0) + 1);
    coord[2] = this.random.nextInt((9 - 0) + 1);
    return coord;
  }
  
  private int next() {
    if (random.nextBoolean()) {
      return 1;
    } else {
      return 3;
    }
  }

  
  
}
