package main.java.Batalla.naval.TPFinal;

public class BarcosFactory {
	public Barco createBarco(String type) {
		Barco barco = null;

		if (type.equals("corbeta")) {
			barco = new Corbeta();
		} else if (type.equals("fragata")) {
			barco = new Fragata();
		} else if (type.equals("destructor")) {
			barco = new Destructor();
		} 
		return barco;
	}
	public Barco createBarco(String type, int xi, int yi, int xf, int yf) {
		Barco barco = null;

		if (type.equals("corbeta")) {
			barco = new Corbeta();
		} else if (type.equals("fragata")) {
			barco = new Fragata();
		} else if (type.equals("destructor")) {
			barco = new Destructor();
		} 
		barco.setPos(new PosicionBarco(xi,yi,xf,yf));
		return barco;
	}
}
