package main.java.Batalla.naval.TPFinal;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ColocarBarcos implements AccionBehavior {
  
  
  
  /** Coloca el barco en la grilla, si es que es posible.
   * Cambia el valor de las celdas logicas
   * deja todo listo para que notifiquemos al display y coloree como corresponda
   * @param e El mouseEvent que tiene el boton se apreto, para saber si chequeo a la derecha o abajo
   * @param row   En que fila esta el boton 
   * @param column En que columna esta el boton
   * @param idCasilla En que grilla esta el boton
   */
  
  
  public void realizarAccion(Tablero tablero, MouseEvent e, int row, int column, int idCasilla) {
    
    int turnoJugador = tablero.getTurno() % 2;
    if (!esValido(tablero, e, row, column, idCasilla)) {
      return;
    }
    //int [][] grilla = tablero.getGrilla0();
    int [][] grilla = turnoJugador == 0 ? tablero.getGrilla0() : tablero.getGrillaJugador1();
    //ArrayList<Barco> barcosJug = tablero.getJugador0().getBarcos();
    ArrayList<Barco> barcosJug = turnoJugador % 2 == 0 ? tablero.getJugador0().getBarcos()
        : tablero.getJugador1().getBarcos();
    if (e.getButton() == 1) {
      for (int i = 0; i < barcosJug.get(0).getSize(); i++) {
        grilla[row + i][column] = Tablero.BARCO;
      }
    } else if (e.getButton() == 3) {
      for (int i = 0; i < barcosJug.get(0).getSize(); i++) {
        grilla[row][column + i] = Tablero.BARCO;
      }
    }
    tablero.setTurno(tablero.getTurno() + 1);
  }

  
  /** Chequea si es valido colocar el barco en esa posicion.
   * Chequea que no el barco entre en el espacio que hay hasta el borde de la grilla
   * Chequea que el movimiento sea hecho en la grilla del jugador
   * Chequea que no se puedan poner barcos que colisionarian con otros
   * @param e El mouseEvent que tiene el boton se apreto, para saber si chequeo a la derecha o abajo
   * @param row   En que fila esta el boton 
   * @param column En que columna esta el boton
   * @param idCasilla En que grilla esta el boton
   * @return boolean
   */
  
  public boolean esValido(Tablero tablero, MouseEvent e, int row, int column, int idCasilla) {
    
    int turnoJugador = tablero.getTurno() % 2;
    if (turnoJugador != idCasilla) {
      return false;
    }
    int [][] grilla = turnoJugador == 0 ? tablero.getGrilla0() : tablero.getGrillaJugador1();
    ArrayList<Barco> barcos = turnoJugador == 0 ? tablero.getJugador0().getBarcos() 
        : tablero.getJugador1().getBarcos();
    
    //si es click izquierdo chequeo a la derecha de la casilla
    //hardcodeado primer barco
    
    if (e.getButton() == 1) {
      for (int i = 0; i < barcos.get(0).getSize(); i++) {     
        try {
          if (grilla[row + i][column] == Tablero.BARCO) {
            return false;
          }
        } catch (ArrayIndexOutOfBoundsException exc) {
          return false;
        }
      }
    } else if (e.getButton() == 3) {
      for (int i = 0; i < barcos.get(0).getSize(); i++) {
        try {
          if (grilla[row][column + i] == Tablero.BARCO) {
            return false;
          }
        } catch (ArrayIndexOutOfBoundsException exc) {
          return false;
        }
      }
    }
    return true;
  }

}
