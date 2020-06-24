package model;

import view.Observer;

public class RealizarDisparoEspecial implements AccionBehavior {

  private Tablero tablero;
  private static final int LADO = 3;

  public RealizarDisparoEspecial(Tablero board) {
    this.tablero = board;
  }

  /**
   * Realiza un disparo cuadrado de LADO = nro impar.
   */
  public void realizarAccion(int click, int row, int column, int idCasilla) {
    // TODO Auto-generated method stub

    if (!esValido(click, row, column, idCasilla)) {
      return;
    }

    int varAux = LADO / 2;
    int[][] grilla = (tablero.getTurno() % 2 == 0) ? tablero.getGrillaJugador1() :
      tablero.getGrillaJugador0();
    for (int i = -varAux; i < varAux + 1; i++) {
      for (int j = -varAux; j < varAux + 1; j++) {
        try {
          switch (grilla[row + i][column + j]) {
            case Tablero.AGUA:
              grilla[row + i][column + j] = Tablero.AGUA_MISS;
              break;
            case Tablero.BARCO:
              grilla[row + i][column + j] = Tablero.BARCO_HIT;
              Barco barquito = tablero.encontrarBarco(row + i, column + j, idCasilla);
              barquito.reducirVida();
              if (barquito.getVida() == 0) {
                tablero.notifyObservers(new Evento(Evento.DESTRUYE_BARCO, tablero.getTurnoJugador()));
                System.out.println("Destruiste un/una " + barquito.getTipo() 
                    + " del jugador" + idCasilla);
              }
              break;
            default:
            ;
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          ;
        }

      }
    }
    tablero.notifyObservers(new Evento(Evento.REALIZA_DISPARO, idCasilla));
    int jugador = tablero.getTurno() % 2 == 0 ? 1 : 0;
    tablero.setMovimientoExitoso(true);
    tablero.setTurnoJugador(jugador);
    tablero.setTurno(tablero.getTurno() + 1);
  }

  /**
   * Se fija que no se haya disparado en la grilla del jugador que hace el disparo
   * y que no se haya disparado antes en esa casilla.
   */

  public boolean esValido(int click, int row, int column, int idCasilla) {

    int turnoJugador = tablero.getTurno() % 2;
    if (turnoJugador == idCasilla) {
      return false;
    }
    int[][] grilla = (turnoJugador == 0) ? tablero.getGrillaJugador1() : 
      tablero.getGrillaJugador0();
    if (grilla[row][column] == Tablero.AGUA_MISS || grilla[row][column] == Tablero.BARCO_HIT) {
      return false;
    } else {
      return true;
    }
  }
}
