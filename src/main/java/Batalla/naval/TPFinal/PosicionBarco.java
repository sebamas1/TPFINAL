package main.java.Batalla.naval.TPFinal;

/** Guarda 4 ints que definen donde esta el barco.
 * La posicion de los barcos esta definida por donde empiezan
 * y donde terminan (son como una linea recta)
 * @author Juan Pablo
 *
 */
public class PosicionBarco {
  private int inicialY;
  private int inicialX;
  private int finalY;
  private int finalX;
  	
  /** Setea las coordenadas iniciales y finales.
   * 
   * @param posicion en filas del barco
   * @param posicion en columnas del barco
   */
  public PosicionBarco(int filaI, int columnaI, int filaF, int columnaF) {
    this.inicialY = filaI;
    this.inicialX = columnaI;
    this.finalY  = filaF;
    this.finalX  = columnaF;
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