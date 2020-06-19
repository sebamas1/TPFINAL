package main.java.Batalla.naval.TPFinal;

public abstract class Barco {
  protected PosicionBarco pos;
  protected int size;
  protected int vida;
  protected int propietario;
  

  public Barco() {
  }

  public int getSize() {
    return size;
  }

  public int getVida() {
    return vida;
  }
  
  public int getPropietario() {
    return propietario;
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
  
  public void setProp(int id) {
    this.propietario = id;
  }
}
