package main.java.Batalla.naval.TPFinal;

public class PosicionBarco {
	private int x_inicio;
	private int y_inicio;
	private int x_final;
	private int y_final;
	
	public PosicionBarco(int xi, int yi, int xf, int yf){
		this.x_inicio = xi;
		this.y_inicio = yi;
		this.x_final  = xf;
		this.y_final  = yf;
	}
	
	public int getXi() {
		return x_inicio;
	}
	public int getXf() {
		return x_final;
	}
	public int getYi() {
		return y_inicio;
	}
	public int getYf() {
		return y_final;
	}
}