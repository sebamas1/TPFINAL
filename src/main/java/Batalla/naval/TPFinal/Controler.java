package main.java.Batalla.naval.TPFinal;

import java.awt.event.MouseEvent;

public class Controler {
  private Tablero tablero;

  /** El controler del sistema siguiendo MVC.
   * @param tablero El tablero que va a controlar
   */
  public Controler(Tablero tablero) {
    this.tablero = tablero;
    tablero.crearGrilla();
    
    
  }
  
  /** Proximamente vamos a hacer que decida que metodos del tablero usar.
   * @param e MouseEvent con data relevante
   * @param i fila donde ocurrio el click
   * @param j columna donde ocurrio el click
   * @param id id del jugador 
   */
  public void notifyEvent(MouseEvent e, int i, int j, int id) {
    //hardcodeado para testear coloca barcos nada mas
    if (e.getButton() == 1 || e.getButton() == 3) {
      tablero.colocarBarco(e.getButton(), i, j, id);
      tablero.notifyObservers();
    }
  }  
}
