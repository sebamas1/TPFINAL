package main.java.Batalla.naval.TPFinal;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JFrame;

/**
 * Esta clase es el concrete subject del observer Tablero. El usuario interactua
 * con esta clase que controla toda la parte grafica de la aplicacion.
 */
public class DisplayDelTablero implements Subject {
	private int cont_aux = 0;
	private HashSet<Observer> observers;
	private Tablero tablero;
	private boolean debeColocar1 = false;
	private boolean debeColocar2 = false;
	private Grilla grilla;
	private ArrayList<Barco> barcosJugadores;

	public DisplayDelTablero() {
		grilla = new Grilla(this);
		barcosJugadores = null;
		observers = new HashSet<Observer>();
		tablero = new Tablero(this);
		Jugador usuario = new Jugador("Seba", 0, this);

	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void detachObserver(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	public void colocarBarcos(ArrayList<Barco> barcos, Integer ID) {
		barcosJugadores = barcos;
		for (int i = 0; i < barcos.size(); i++) {
				barcos.get(i).setProp(ID);
	    	}
		if (ID == 0) {
			debeColocar1 = true;
		} else if (ID == 1) {
			debeColocar2 = true;
		}
	}

	/**
	 * Esta clase crea casillas para luego ser colocadas en la grilla. Es del tipo
	 * Button, que puede manejar JFrame e implementa MouseListener para "escuchar"
	 * el mouse del usuario.
	 */
	@SuppressWarnings("serial")
	private class Casilla extends Button implements MouseListener {
		private int propiedadCasilla;
		private boolean atacada = false;
		private boolean ocupado = false;
		private int posicionX;
		private int posicionY;

		/**
		 * Rellenar descripcion de clase
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * 
		 * @param x       posicion en el eje x
		 * @param y       posicion en el eje y
		 * @param height  dimension de altura
		 * @param width   dimension de ancho
		 * @param jugador debe tomar valor 0 para el usuario o 1 para la IA
		 * @param display es la referencia al display del tablero, esta ahi para
		 *                comunicacion
		 */
		public Casilla(int x, int y, int height, int width, int jugador, DisplayDelTablero display)
				throws IllegalArgumentException {
			if (jugador == 0 || jugador == 1) {
				this.propiedadCasilla = jugador;
			} else
				throw new IllegalArgumentException("Ingresar jugador 0 o jugador 1");
			setBounds(x, y, height, width);
			addMouseListener(this);
		}

		public void mouseClicked(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {

		}

		public void setPosicion(int x, int y) {
			posicionX = x;
			posicionY = y;
		}

		/**
		 * Controla cuando se libera el dedo del mouse, y pinta la casilla con el color
		 * que corresponda.
		 * 
		 * @param e evento del mouse
		 */
		public void mouseReleased(MouseEvent e) {
		if(debeColocar1 && esValido(e)) {
        	try {
        		
        		ubicarBarco(e);
        	} catch(NullPointerException f) {
        		int color = 220;
        		Color defecto = getBackground();
        		for(int i = 0; i < color ; i++) {
        			setBackground(new Color(i, 0, 0));
        			try {
        				Thread.sleep(1);
        			}catch(InterruptedException j) {}
        		}
        		setBackground(defecto);
        	}
        }
		}
		/**
		 *  Si se hace click izuqierdo sobre un boton, ubica el barco verticalmente, con click derecho se ubica
		 *  horizontalmente.
		 *  Se controla que el usuario no pueda ingresar barcos en lugares incorrectos, aunque todavia se pueden
		 *  poner barcos en ambas grillas.
		 * @param e evento del mouse
		 * @throws NullPointerException
		 */
		private void ubicarBarco(MouseEvent e) throws NullPointerException{
			
			//Click izquierdo -> completa tantos cuadrados como size hacia abajo
			//Click derecho -> completa tantos cuadrados como size hacia la derecha
				if (e.getButton() == 1) {
					barcosJugadores.get(cont_aux).setPos(new PosicionBarco(posicionX, posicionY,
							posicionX, posicionY + grilla.sizeCasilla * barcosJugadores.get(cont_aux).getSize()));
					for (int i = 0; i < barcosJugadores.get(cont_aux).getSize(); i++) {
						Casilla casilla = grilla.casillas.get(posicionX + "" + (posicionY + (grilla.sizeCasilla * i)));
						casilla.setOcupada();
						casilla.setBackground(new Color(220, 0, 0));
					}
				} else if (e.getButton() == 3) {
					barcosJugadores.get(cont_aux).setPos(new PosicionBarco (posicionX, posicionY, 
							grilla.sizeCasilla*barcosJugadores.get(cont_aux).getSize(), posicionY));
					for (int i = 0; i < barcosJugadores.get(cont_aux).getSize(); i++) {
						Casilla casilla = grilla.casillas.get((posicionX + (grilla.sizeCasilla * i)) + "" + posicionY);
						casilla.setOcupada();
						casilla.setBackground(new Color(220, 0, 0));
					}
				}
				cont_aux = cont_aux + 1;
				setBackground(new Color(220, 0, 0));
				if (cont_aux >= Tablero.CANT_BARCOS) {
					debeColocar1 = false;
					/*for (int i = 0; i < barcosJugadores.size(); i++) {
						Barco temp = barcosJugadores.get(i);
						System.out.println(temp.getPos().getXi() + " " + temp.getPos().getYi() + "   "
								+ temp.getPos().getXf() + " " + temp.getPos().getYf());
			    	}*/
				}
			
		}
		//flag me dice si chequero a la derecha o abajo (click izq o derecho)
		
		public boolean esValido(MouseEvent e) {
			Casilla casilla = null;
			if(e.getButton() == 1) {
				for(int i=0; i< barcosJugadores.get(cont_aux).getSize(); i++) {
		        	try {
						casilla = grilla.casillas.get(posicionX + "" + (posicionY + (grilla.sizeCasilla * i)));
						if(casilla.getOcupada() == true) {
							return false;
						}
		        	} catch(NullPointerException f) {
		        		return false;
		        	}
				}
			}
			else if(e.getButton() == 3){
				for(int i=0; i< barcosJugadores.get(cont_aux).getSize(); i++) {
		        	try {
						casilla = grilla.casillas.get((posicionX + (grilla.sizeCasilla * i)) + "" + posicionY);
						if(casilla.getOcupada() == true) return false;
		        	} catch(NullPointerException f) {
		        		return false;
		        	}
				}
			}
			return true;
		}
		
		
		public boolean getOcupada() {
			return ocupado;
		}

		public boolean getAtacada() {
			return atacada;
		}

		public void setOcupada() {
			ocupado = true;
		}

		public void setAtacada() {
			atacada = true;
		}
	}

	/**
	 * Grilla del tablero
	 */
	@SuppressWarnings("serial")
	private class Grilla extends JFrame {
		private final int FILAS = 10;
		private final int COLUMNAS = 10;
		private final int sizeCasilla = 45;
		private int WIDTH = 800;
		private int HEIGHT = 1500;
		private HashMap<String, Casilla> casillas = new HashMap<String, Casilla>();

		/**
		 * El constructor crea una ventana de JFrame, luego crea la cantidad de casillas
		 * especificadas y se las pasa a la ventana de JFrame en la posicion
		 * correspondiente. Guarda las casillas en un HashMap y les da a las casillas su
		 * valor de posicion. Esto es para tener una referencia a todas las casillas.
		 */
		public Grilla(DisplayDelTablero display) {
			setTitle("Batalla naval!");
			setSize(WIDTH, HEIGHT);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			setLocationRelativeTo(null);
			setVisible(true);
			setLayout(null);
			for (int i = 0; i < COLUMNAS; i++) {
				for (int j = 0; j < FILAS; j++) {
					Casilla casilla = new Casilla((10 + sizeCasilla * i), (10 + sizeCasilla * j), sizeCasilla,
							sizeCasilla, 1, display);
					casilla.setPosicion(10 + sizeCasilla * i, 10 + sizeCasilla * j);
					String codigoCasilla = (10 + sizeCasilla * i) + "" + (10 + sizeCasilla * j);
					casillas.put(codigoCasilla, casilla);
					add(casilla);
				}
			}
			for (int i = 0; i < COLUMNAS; i++) {
				for (int j = 0; j < FILAS; j++) {
					Casilla casilla = new Casilla((10 + sizeCasilla * i), (520 + sizeCasilla * j), sizeCasilla,
							sizeCasilla, 0, display);
					casilla.setPosicion(10 + sizeCasilla * i, 520 + sizeCasilla * j);
					String codigoCasilla = (10 + sizeCasilla * i) + "" + (520 + sizeCasilla * j);
					casillas.put(codigoCasilla, casilla);
					add(casilla);
				}
			}

		}
	}
}