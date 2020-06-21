package main.java.Batalla.naval.TPFinal;

import java.util.ArrayList;
import java.util.Random;

public class AI extends Humano implements Jugador {
  
  private Tablero tablero;
  public int[] coord;
  private Random r;

  /**
   * Se le pasa el estado del tablero (que contiene el turno) para que
   * pueda calcular su movimiento.
   * @param board El tablero sobre el que va a hacer movimientos
   */
  public AI(Tablero board) {
    r = new Random();
    coord = new int[2];
    this.tablero = board;
    this.barcos = new ArrayList<Barco>();
    BarcosFactory factory = new BarcosFactory();
    this.nombre = "AI";
    this.playerID = 1;
    barcos.add(factory.createBarco("Corbeta"));
    barcos.add(factory.createBarco("Corbeta"));
    barcos.add(factory.createBarco("Fragata"));
    barcos.add(factory.createBarco("Fragata"));
    barcos.add(factory.createBarco("Destructor"));
    barcos.add(factory.createBarco("Portaaviones"));
  }
  
  /**
   * Devuelve las coordenadas para que el tablero ejecuta la accion que 
   * corresponda ahí.
   * @return 2 enteros
   */
  
  public int[] realizarTurno() {

    coord[0] = this.r.nextInt((9 - 0) + 1) + 0;
    coord[1] = this.r.nextInt((9 - 0) + 1) + 0;
    return coord;
  }
  
  
  
}
