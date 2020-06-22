package model;

import view.Display;

public class Main {

  /**
   * inicia todo.
   * @param args no usamos los argumentos
   */
  public static void main(String[] args) {
    @SuppressWarnings("unused")
    Tablero tablero = new Tablero();
    Display display = new Display(tablero);
    Controler controler = new Controler(tablero, display);
    
  }

}
