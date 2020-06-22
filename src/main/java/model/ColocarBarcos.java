package main.java.model;

import java.util.ArrayList;

public class ColocarBarcos implements AccionBehavior {

  private Tablero tablero;
  private int nrobarco = 0;

  /**
   * Constructor.
   * @param tablero el tablero sobre el que colocara los barcos
   */
  public ColocarBarcos(Tablero tablero) {

    this.tablero = tablero;

  }

  /**
   * Coloca el barco en la grilla, si es que es posible. Cambia el valor de las
   * celdas logicas deja todo listo para que notifiquemos al display y coloree
   * como corresponda
   * 
   * @param click     El mouseEvent que tiene el boton se apreto, para saber si
   *                  chequeo a la derecha o abajo
   * @param row       En que fila esta el boton
   * @param column    En que columna esta el boton
   * @param idCasilla En que grilla esta el boton
   */

  public void realizarAccion(int click, int row, int column, int idCasilla) {

    int turnoJugador = tablero.getTurno() % 2;
    if (!esValido(click, row, column, idCasilla)) {
      return;
    }

    int[][] grilla = turnoJugador == 0 ? tablero.getGrillaJugador0() : tablero.getGrillaJugador1();
    ArrayList<Barco> barcosJug = turnoJugador % 2 == 0 ? tablero.getBarcosJug0() :
        tablero.getBarcosJug1();
    if (click == 1) {
      try {
        for (int i = 0; i < barcosJug.get(nrobarco).getSize(); i++) {
          grilla[row + i][column] = Tablero.BARCO;
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("No se deberian estar colocando barcos");
      }
      barcosJug.get(nrobarco).setPos(new PosicionBarco(row, column, 
          (row + barcosJug.get(nrobarco).getSize() - 1), column));
    } else if (click == 3) {
      try {
        for (int i = 0; i < barcosJug.get(nrobarco).getSize(); i++) {
          grilla[row][column + i] = Tablero.BARCO;
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("No se deberian estar colocando barcos");
      }
      barcosJug.get(nrobarco).setPos(new PosicionBarco(row, column, row, (
          column + barcosJug.get(nrobarco).getSize() - 1)));
    }
    tablero.setTurno(tablero.getTurno() + 1);
  }

  /**
   * Chequea si es valido colocar el barco en esa posicion. Chequea que no el
   * barco entre en el espacio que hay hasta el borde de la grilla Chequea que el
   * movimiento sea hecho en la grilla del jugador Chequea que no se puedan poner
   * barcos que colisionarian con otros
   * 
   * @param mouseClick El mouseEvent que tiene el boton se apreto, para saber si
   *                   chequeo a la derecha o abajo
   * @param row        En que fila esta el boton
   * @param column     En que columna esta el boton
   * @param idCasilla  En que grilla esta el boton
   * @return boolean
   */

  public boolean esValido(int mouseClick, int row, int column, int idCasilla) {

    int turnoJugador = tablero.getTurno() % 2;
    if (turnoJugador != idCasilla) {
      return false;
    }
    int[][] grilla = turnoJugador == 0 ? tablero.getGrillaJugador0() : tablero.getGrillaJugador1();
    ArrayList<Barco> barcos = turnoJugador == 0 ? tablero.getBarcosJug0() : tablero.getBarcosJug1();

    for (int i = 0; i < barcos.size(); i++) {
      if (barcos.get(i).getPos() == null) {
        nrobarco = i;
        break;
      }
    }

    if (mouseClick == 1) {
      for (int i = 0; i < barcos.get(nrobarco).getSize(); i++) {
        try {
          if (grilla[row + i][column] == Tablero.BARCO) {
            return false;
          }
        } catch (ArrayIndexOutOfBoundsException exc) {
          return false;
        }
      }
    } else if (mouseClick == 3) {
      for (int i = 0; i < barcos.get(nrobarco).getSize(); i++) {
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
