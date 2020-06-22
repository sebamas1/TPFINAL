package model;

import java.awt.event.MouseEvent;

import view.Display;

public class Controler {
  private Tablero tablero;
  private Display display;

  /**
   * El controler del sistema siguiendo MVC.
   * 
   * @param tablero El tablero que va a controlar
   */
  public Controler(Tablero tablero, Display display) {
    this.tablero = tablero;
    this.display = display;
    display.setControler(this);
    tablero.registerObserver(display);
    tablero.crearGrillas();

  }

  /**
   * Proximamente vamos a hacer que decida que metodos del tablero usar.
   * 
   * @param e  MouseEvent con data relevante
   * @param i  fila donde ocurrio el click
   * @param j  columna donde ocurrio el click
   * @param id id del jugador
   */
  public void notifyEvent(MouseEvent e, int i, int j, int id) {

    // hardcodeado para testear coloca barcos nada mas
    if (e.getButton() == 1 || e.getButton() == 3) {
      tablero.dispararEventoEnGrilla(e.getButton(), i, j, id);
    }
  }
}
