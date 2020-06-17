package main.java.Batalla.naval.TPFinal;

abstract public class Barco {
	public PosicionBarco pos;
	public int tamaño;
	public int vida;
	

	public Barco() {
	}

	public int getSize() {
		return tamaño;
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

}
