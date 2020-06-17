package main.java.Batalla.naval.TPFinal;
import java.util.ArrayList;

public class Tablero implements Observer {
	DisplayDelTablero display;
	public Tablero(DisplayDelTablero display) {
		this.display = display;
		this.display.registerObserver(this);
		colocarBarcos();
	}

	public void update() {

	}
    public void colocarBarcos() {
    	BarcosFactory factory = new BarcosFactory();
		ArrayList<Barco> barcos = new ArrayList<Barco>();
    	Barco corbeta1 = factory.createBarco("corbeta");
		Barco corbeta2 = factory.createBarco("corbeta");
		Barco corbeta3 = factory.createBarco("corbeta");
		Barco fragata1 = factory.createBarco("fragata");
		Barco fragata2 = factory.createBarco("fragata");
		Barco destructor1 = factory.createBarco("destructor");
		
		barcos.add(corbeta1);
		barcos.add(corbeta2);
		barcos.add(corbeta3);
		barcos.add(fragata1);
		barcos.add(fragata2);
		barcos.add(destructor1);
		display.colocarBarcos(barcos, 0); //esto esta harcodeado, como se me borro lo soluciono rapido asi
    }
	public void checkDisparo(int a, int b) {
		// TODO implement here
	}


	public void getBarco(int a, int b) {
		// TODO implement here
	}

}
