package model;

import java.util.ArrayList;
import java.util.Random;

public class AI extends Humano {

  /*
   * SCORE_VACIAS_ALREDEDOR cuanto suma cada casilla de alrededor

   * (las 8 que lo rodean)
   * con estos parametros safa
   * SCORE_VACIAS_RECTAS cuanto suma tener casillas vacias seguidas en linea recta       4
   * hacia arriba/abajo/der/izq (util para q dispare sobre los costados)                 3
   * SCORE_BARCO_RECTO para que sume si la casilla esta a lado de un impacto            30
   * SCORE_BARCO_DIAGONAL para que sume si esta en diagonal a un impacto                10
   */
  private static final int SCORE_VACIAS_ALREDEDOR = 4;
  private static final int SCORE_VACIAS_RECTAS = 3;
  private static final int SCORE_BARCO_RECTO = 50;
  private static final int SCORE_BARCO_DIAGONAL = 4;
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
   * corresponda ahï¿½.
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
    int[][] grilla0 = tablero.getGrillaJugador0().clone();
    for (int i = 0; i < Tablero.FILAS; i++) {
      for (int j = 0; j < Tablero.COLUMNAS; j++) {
        grillaAnalizada[i][j] = analizarCasilla(i, j, grillaAnalizada);
      }
    }
    this.printMatriz(grillaAnalizada);
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
    //System.out.println("TURNO \n\n\n");
    //this.printMatriz(grillaAnalizada);
    int[] coord = { 1, 1, 1 };
    try {
      coord = listaCandidatos.get(random.nextInt(listaCandidatos.size()));
    } catch (IllegalArgumentException e) {
      System.out.println("LISTA VACIA EN IA disparo()");
    }
    return coord;

  }

  private int analizarCasilla(int fila, int columna, int[][] grilla) {
    int puntaje = 1;
    boolean bordeVertical = fila == 0 || fila == Tablero.FILAS - 1;
    boolean bordeLateral = columna == 0 || columna == Tablero.FILAS - 1;
    int[][] grilla0 = tablero.getGrillaJugador0();
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        try {
          if (grilla0[fila][columna] == Tablero.AGUA_MISS 
              || grilla0[fila][columna] == Tablero.BARCO_HIT) {
            return 0;
          }
          if (grilla0[fila + i][columna + j] == Tablero.AGUA 
              || grilla0[fila + i][columna + j] == Tablero.BARCO) {
            puntaje += tablero.getEnableNuclear() ? SCORE_VACIAS_ALREDEDOR * 2 
                : AI.SCORE_VACIAS_ALREDEDOR;
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          ;
        }
      }
    }
    
    if (bordeVertical || bordeLateral) {
      System.out.println(puntaje);
      puntaje = (int) Math.round(puntaje * 1.4 + 0.005 * tablero.getTurno());
    }
    puntaje += this.analizarProximidades(grilla0, fila, columna);
    puntaje += this.otroAnalisisMas(grilla0, fila, columna);
    return puntaje;
  }

  private int[] turnoRandom() {
    int[] coord = new int[3];
    coord[0] = this.next();
    coord[1] = random.nextInt(Tablero.FILAS);
    coord[2] = random.nextInt(Tablero.COLUMNAS);
    return coord;
  }

  private void printMatriz(int[][] grilla) {
    for (int i = 0; i < grilla.length; i++) {
      for (int j = 0; j < grilla[i].length; j++) {
        System.out.print(String.format("%03d", grilla[i][j]) + "  ");
        ;
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

  private int analizarProximidades(int[][] grilla0, int fila, int columna) {
    int puntajeAcum = 0;
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        boolean diagonal = j != 0 && i != 0;
        try {
          if (diagonal && grilla0[fila + i][columna + j] == Tablero.BARCO_HIT) {
            puntajeAcum += AI.SCORE_BARCO_DIAGONAL;
          } else if (!diagonal && grilla0[fila + i][columna + j] == Tablero.BARCO_HIT) {
            puntajeAcum += AI.SCORE_BARCO_RECTO;
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          ;
        }
      }
    }
    return puntajeAcum;
  }

  // Tiene en cuenta el tamaño del barco vivo mas chico y cuantas
  // casillas rectas vacias tiene
  private int otroAnalisisMas(int[][] grilla0, int fila, int columna) {
    int minimo = this.sizeMenorBarcoVivo();
    int puntaje = 0;
    boolean colisiona1 = false;
    boolean colisiona2 = false;
    boolean colisiona3 = false;
    boolean colisiona4 = false;
    for (int i = 1; i < minimo; i++) {
      try {
        boolean vacia1 = grilla0[fila + i][columna] == Tablero.AGUA 
            || grilla0[fila + i][columna] == Tablero.BARCO;
        if (vacia1 && !colisiona1) {
          puntaje += AI.SCORE_VACIAS_RECTAS; 
        } else if (!vacia1) {
          colisiona1 = true;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        ;
      }
      
      try {
        boolean vacia2 = grilla0[fila - i][columna] == Tablero.AGUA 
            || grilla0[fila - i][columna] == Tablero.BARCO;
        if (vacia2 && !colisiona2) {
          puntaje += AI.SCORE_VACIAS_RECTAS; 
        } else if (!vacia2) {
          colisiona2 = true;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        ;
      }
      
      
      try {
        boolean vacia3 = grilla0[fila][columna + i] == Tablero.AGUA 
            || grilla0[fila][columna + i] == Tablero.BARCO;
        if (vacia3 && !colisiona3) {
          puntaje += AI.SCORE_VACIAS_RECTAS; 
        } else if (!vacia3) {
          colisiona3 = true;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        ;
      }
      
      
      try {
        boolean vacia4 = grilla0[fila][columna - i] == Tablero.AGUA 
            || grilla0[fila][columna - i] == Tablero.BARCO;
        if (vacia4 && !colisiona4) {
          puntaje += AI.SCORE_VACIAS_RECTAS; 
        } else if (!vacia4) {
          colisiona4 = true;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        ;
      }
    }
    return puntaje;
  }

  private int sizeMenorBarcoVivo() {
    ArrayList<Barco> barcos = tablero.getBarcosJug0();
    int min = 10;
    for (int i = 0; i < barcos.size(); i++) {
      if (barcos.get(i).getVida() > 0 && barcos.get(i).getSize() < min) {
        min = barcos.get(i).getSize();
      }
    }
    return min;
  }
}
