package main.java.Batalla.naval.TPFinal;

public class Barco {
  private PosicionBarco pos;
  private String tipo;
  private int size;
  private int vida;
  

  public Barco() {
  }

  public Barco(int tamano, String type) {
    this.size = tamano;
    this.vida = tamano;
    this.tipo = type;
  }
  public int getSize() {
    return size;
  }

  public int getVida() {
    return vida;
  }
  
// // public int getPropietario() {
//    return propietario;
//  }

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
  
//  public void setProp(int id) {
//    this.propietario = id;
//  }
  
  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  
}
