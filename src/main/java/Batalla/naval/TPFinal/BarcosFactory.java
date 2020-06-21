package main.java.Batalla.naval.TPFinal;

public class BarcosFactory {
  /** Crea el barco que corresponda y lo retorna.
   * @param type que tipo de barco crear
   * @return el barco que creo
   */
  public Barco createBarco(String type) {
    Barco barco = null;

    if (type.equals("Corbeta")) {
      barco = new Corbeta();
    } else if (type.equals("Fragata")) {
      barco = new Fragata();
    } else if (type.equals("Destructor")) {
      barco = new Destructor();
    } else if (type.equals("Portaaviones")) {
      barco = new Portaaviones();
    }
    return barco;
  }
  
  /** Crea el barco.
   * @param type  que tipo de barco
   * @param xi    x inicial
   * @param yi    y inicial
   * @param xf    x final
   * @param yf    y final
   * @return
   */
  public Barco createBarco(String type, int xi, int yi, int xf, int yf) {
    Barco barco = null;

    if (type.equals("Corbeta")) {
      barco = new Corbeta();
    } else if (type.equals("Fragata")) {
      barco = new Fragata();
    } else if (type.equals("Destructor")) {
      barco = new Destructor();
    } else if (type.contentEquals("Portaaviones")) {
      barco = new Portaaviones();
    }
    barco.setPos(new PosicionBarco(xi, yi, xf, yf));
    return barco;
  }
}
