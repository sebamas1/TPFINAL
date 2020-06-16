package barcos.java.Batalla.naval.TPFinal;

public class Barco {
	private int posicionTop;
	private int posicionLeft;
	protected int tamaño;
	protected int vida;

	public Barco() {
		
	}

	public int getTamaño() {
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

}