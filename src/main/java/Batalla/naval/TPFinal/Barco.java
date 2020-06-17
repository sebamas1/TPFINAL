package main.java.Batalla.naval.TPFinal;

abstract public class Barco {
	public PosicionBarco pos;
	public int size;
	public int vida;
	public int propietario;
	

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
	
	public void setProp(int ID) {
		this.propietario = ID;
	}
}
