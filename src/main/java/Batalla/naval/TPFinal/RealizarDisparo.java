package main.java.Batalla.naval.TPFinal;

import java.awt.event.MouseEvent;

public class RealizarDisparo implements AccionBehavior {

  /**
   * Se fija que no se haya disparado en la grilla del jugador que hace el disparo
   * y que no se haya disparado antes en esa casilla.
   */
  public boolean esValido(Tablero tablero, MouseEvent e, int row, int column, int idCasilla) {

    if (tablero.getTurno() != idCasilla) {
      return false;
    }
    int [][] grilla = tablero.getGrilla0();
    //int[][] grilla = tablero.getTurno() == 0 ? tablero.getGrillaJugador1() : tablero.getGrilla0();
    if (grilla[row][column] == Tablero.AGUA_MISS || grilla[row][column] == Tablero.BARCO_HIT) {
      return false;
    } else {
      return true;
    }
  }
  
  /**
   * Se fija que no se haya disparado en esa casilla antes y realiza el disparo.
   */
  
  public void realizarAccion(Tablero tablero, MouseEvent e, int row, int column, int idCasilla) {

    if (!esValido(tablero, e, row, column, idCasilla)) {
      return;
    }
    //int[][] grilla = tablero.getTurno() == 0 ? tablero.getGrillaJugador1() : tablero.getGrilla0();
    int[][] grilla = tablero.getGrilla0();

    switch (grilla[row][column]) {

      case Tablero.AGUA:
        grilla[row][column] = Tablero.AGUA_MISS;
        break;

      case Tablero.BARCO:
        grilla[row][column] = Tablero.BARCO_HIT;
        break;

      default:
        ;
    }
    
    //tablero.setTurno(tablero.getTurno() + 1);
}

}
