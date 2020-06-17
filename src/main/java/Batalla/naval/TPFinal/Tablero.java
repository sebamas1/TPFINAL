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
		ArrayList<Barco> barcos = new ArrayList<Barco>();
    	Barco corbeta1 = new Corbeta();
		Barco corbeta2 = new Corbeta();
		Barco corbeta3 = new Corbeta();
		Barco fragata1 = new Fragata();
		Barco fragata2 = new Fragata();
		Barco destructor1 = new Destructor();
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
		return;
	}

}
