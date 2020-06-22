package model;

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
  
  /**
   * Setea la posicion.
   * @param filaI fila inicial
   * @param columnaI columna inicial
   * @param filaF fila final
   * @param columnaF columna final
   */
  public PosicionBarco(int filaI, int columnaI, int filaF, int columnaF) {
    this.inicialY = columnaI;
    this.inicialX = filaI;
    this.finalY  = columnaF;
    this.finalX  = filaF;
  }

  public int getInicialY() {
    return inicialY;
  }

  public void setInicialY(int inicialY) {
    this.inicialY = inicialY;
  }

  public int getInicialX() {
    return inicialX;
  }

  public void setInicialX(int inicialX) {
    this.inicialX = inicialX;
  }

  public int getFinalY() {
    return finalY;
  }

  public void setFinalY(int finalY) {
    this.finalY = finalY;
  }

  public int getFinalX() {
    return finalX;
  }

  public void setFinalX(int finalX) {
    this.finalX = finalX;
  }

  
}