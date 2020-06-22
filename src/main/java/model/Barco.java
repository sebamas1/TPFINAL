package main.java.model;

public class Barco {
  private PosicionBarco pos;
  private String tipo;
  private int size;
  private int vida;
  

  public Barco() {
  }
  
  /**
   * Setea el size y el tipo o nombre del barco.
   * @param tamano size a setear
   * @param type   nombre del barco
   */
  public Barco(int tamano, String type) {
    this.size = tamano;
    this.vida = tamano;
    this.tipo = type;
  }
  
  public void setSize(int size) {
    this.size = size;
  }
  
  public void setVida(int vida) {
    this.vida = vida;
  }
  
  public int getSize() {
    return size;
  }

  public int getVida() {
    return vida;
  }

  public void reducirVida() {
    this.vida = this.vida - 1;
  }
  
  public boolean estaVivo() {
    return vida > 0;
  }

  public PosicionBarco getPos() {
    return pos;
  }

  public void setPos(PosicionBarco pos) {
    this.pos = pos;
  }
  
  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  
}
