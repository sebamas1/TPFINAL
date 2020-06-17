package main.java.Batalla.naval.TPFinal;

public class Barco {
	private int posicionTop;
	private int posicionLeft;
	protected int size;
	protected int vida;

	public Barco() {
		
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

}