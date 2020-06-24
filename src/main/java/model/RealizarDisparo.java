package model;

import view.Observer;

public class RealizarDisparo implements AccionBehavior {
  private Tablero tablero;

 
  public RealizarDisparo(Tablero tablero) {
    this.tablero = tablero;
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
    int[][] grilla = (turnoJugador == 0) ? tablero.getGrillaJugador1() 
        : tablero.getGrillaJugador0();
    if (grilla[row][column] == Tablero.AGUA_MISS || grilla[row][column] == Tablero.BARCO_HIT) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Se fija que no se haya disparado en esa casilla antes y realiza el disparo.
   */

  public void realizarAccion(int click, int row, int column, int idCasilla) {

    if (!esValido(click, row, column, idCasilla)) {
      return;
    }
    int[][] grilla = (tablero.getTurno() % 2 == 0) ? tablero.getGrillaJugador1() 
        : tablero.getGrillaJugador0();
    switch (grilla[row][column]) {

      case Tablero.AGUA:
        grilla[row][column] = Tablero.AGUA_MISS;
        tablero.notifyObservers(new Evento(Evento.REALIZA_DISPARO, tablero.getTurnoJugador()));
        break;
  
      case Tablero.BARCO:
        grilla[row][column] = Tablero.BARCO_HIT;
        tablero.notifyObservers(new Evento(Evento.REALIZA_DISPARO, tablero.getTurnoJugador()));
        Barco barquito = tablero.encontrarBarco(row, column, idCasilla);
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
    tablero.setTurno(tablero.getTurno() + 1);
    tablero.setMovimientoExitoso(true);
  }

}
