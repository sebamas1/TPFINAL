package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class AI extends Humano {

  // private Tablero tablero;

  private Tablero tablero;
  private Random random = new Random();

  /**
   * Se le pasa el estado del tablero (que contiene el turno) para que pueda
   * calcular su movimiento.
   * 
   * @param board El tablero sobre el que va a hacer movimientos
   */
  public AI(Tablero board) {
    this.tablero = board;
    this.nombre = "Terminator";
    this.playerID = 1;
  }

  /**
   * Devuelve las coordenadas para que el tablero ejecuta la accion que
   * corresponda ah�.
   * 
   * @return 2 enteros
   */

  public int[] realizarTurno() {

    if (tablero.getTurno() < Jugador.CANT_BARCOS * 2) {
      return this.turnoRandom();
    } else {
      return this.disparo();
    }
  }

  private int next() {
    if (random.nextBoolean()) {
      return 1;
    } else {
      return 3;
    }
  }

  private int[] disparo() {
    int[][] grillaAnalizada = new int[Tablero.FILAS][Tablero.COLUMNAS];
    int[][] grilla0 = tablero.getGrillaJugador0();
    this.copiarGrilla(grilla0, grillaAnalizada);
    for (int i = 0; i < Tablero.FILAS; i++) {
      for (int j = 0; j < Tablero.COLUMNAS; j++) {
        grillaAnalizada[i][j] = analizarCasilla(i, j, grillaAnalizada);
      }
    }
    ArrayList<int[]> listaCandidatos = new ArrayList<int[]>();
    int max = this.getMaxGrilla(grillaAnalizada);
    boolean apretado;
    for (int i = 0; i < Tablero.FILAS; i++) {
      for (int j = 0; j < Tablero.COLUMNAS; j++) {
        apretado = grilla0[i][j] == Tablero.AGUA_MISS || grilla0[i][j] == Tablero.BARCO_HIT;
        if (grillaAnalizada[i][j] == max && !apretado) {
          listaCandidatos.add(new int[] { this.next(), i, j });
        }
      }
    }
    this.printMatriz(grillaAnalizada);
    int[] coord = { 1, 1, 1 };
    try {
      coord = listaCandidatos.get(random.nextInt(listaCandidatos.size()));
    } catch (IllegalArgumentException e) {
      System.out.println(listaCandidatos.size());
      System.out.println("fuck");
    }
    return coord;

  }

  private int analizarCasilla(int fila, int columna, int[][] grilla) {
    int contadorCasillasVacias = -1;
    boolean apretado;
    int[][] grilla0 = tablero.getGrillaJugador0();
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        try {
          if (grilla0[fila][columna] == Tablero.AGUA_MISS || grilla0[fila][columna] == Tablero.BARCO_HIT) {
            return 0;
          }
          if (grilla0[fila + i][columna + j] == Tablero.AGUA || grilla0[fila + i][columna + j] == Tablero.BARCO) {
            contadorCasillasVacias++;
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          ;
        }
      }
    }
    return contadorCasillasVacias;
  }

  private void copiarGrilla(int[][] grillaCopiar, int[][] grillaCopiada) {
    for (int i = 0; i < grillaCopiar.length; i++) {
      for (int j = 0; j < grillaCopiar[i].length; j++) {
        grillaCopiada[i][j] = grillaCopiar[i][j];
      }
    }
  }

  private int[] turnoRandom() {
    int[] coord = new int[3];
    coord[0] = 1;
    coord[1] = random.nextInt((4 - 0) + 1);
    coord[2] = random.nextInt((4 - 0) + 1);
    tablero.printMatriz();
    return coord;
  }

  private void printMatriz(int[][] grilla) {
    for (int i = 0; i < grilla.length; i++) {
      for (int j = 0; j < grilla[i].length; j++) {
        System.out.print(grilla[i][j] + " ");
      }
      System.out.println();
    }
  }

  private int getMaxGrilla(int[][] grilla) {
    int max = 0;
    for (int i = 0; i < grilla.length; i++) {
      for (int j = 0; j < grilla[i].length; j++) {
        if (grilla[i][j] > max) {
          max = grilla[i][j];
        }
      }
    }
    return max;
  }
}
