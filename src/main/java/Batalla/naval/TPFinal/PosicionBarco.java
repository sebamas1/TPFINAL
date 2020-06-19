package main.java.Batalla.naval.TPFinal;

/** Guarda 4 ints que definen donde esta el barco.
 * La posicion de los barcos esta definida por donde empiezan
 * y donde terminan (son como una linea recta)
 * @author Juan Pablo
 *
 */
public class PosicionBarco {
  private int inicialX;
  private int inicialY;
  private int finalX;
  private int finalY;
  	
  /** Setea las coordenadas iniciales y finales.
   * 
   * @param xi x inicial
   * @param yi y inicial
   * @param xf x final
   * @param yf y final
   */
  public PosicionBarco(int xi, int yi, int xf, int yf) {
    this.inicialX = xi;
    this.inicialY = yi;
    this.finalX  = xf;
    this.finalY  = yf;
  }

  public int getInicialX() {
    return inicialX;
  }

  public void setInicialX(int inicialX) {
    this.inicialX = inicialX;
  }

  public int getInicialY() {
    return inicialY;
  }

  public void setInicialY(int inicialY) {
    this.inicialY = inicialY;
  }

  public int getFinalX() {
    return finalX;
  }

  public void setFinalX(int finalX) {
    this.finalX = finalX;
  }

  public int getFinalY() {
    return finalY;
  }

  public void setFinalY(int finalY) {
    this.finalY = finalY;
  }
  	
  
}