package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import view.Display;
import view.EstadisticasView;
import view.Felicitaciones;
import view.Identificador;
import view.MensajeHelp;

public class Controler {
  private Tablero tablero;
  private Display display;
  private Identificador identificador;
  private EstadisticasView estadisticas;
  private Felicitaciones felicitaciones;

  /**
   * El controler del sistema siguiendo MVC.
   * 
   * @param tablero El tablero que va a controlar
   */
  public Controler(Tablero tablero, Display display) {
    this.tablero = tablero;
    this.display = display;
    estadisticas = new EstadisticasView(display);
    display.setControler(this);
    tablero.registerObserver(display);
    tablero.registerObserver(estadisticas);
    tablero.crearGrillas();
    identificador = new Identificador(new NameListener());

    felicitaciones = new Felicitaciones(tablero);
    tablero.registerObserver(felicitaciones);

    display.getBotonReinicio().addActionListener(new ReinicioListener());
    display.getBotonHelp().addActionListener(new HelpListener());


  }

  /**
   * Proximamente vamos a hacer que decida que metodos del tablero usar.
   * 
   * @param e  MouseEvent con data relevante
   * @param i  fila donde ocurrio el click
   * @param j  columna donde ocurrio el click
   * @param id id del jugador
   */
  public void notifyEvent(MouseEvent e, int i, int j, int id) {

    // hardcodeado para testear coloca barcos nada mas
    if (e.getButton() == 1 || e.getButton() == 3) {
      tablero.dispararEventoEnGrilla(e.getButton(), i, j, id);
    }
  }
  
  public void setIdentificador(Identificador identificador) {
    this.identificador = identificador;
  }
  
  private class NameListener implements ActionListener {
    
    public NameListener() {
      
    }
   
    public void actionPerformed(ActionEvent e) {
      display.getContentPane().setBackground(Display.BACKGROUND);
      String aux = identificador.nombre.getText().length() == 0 ? "Jugador 1"
          : identificador.nombre.getText();
      display.mostrarString(aux, 50, 10, 100, 40);
      display.mostrarString(tablero.getJugador1().getNombre()
          , 50, 350, 100, 40);
      display.setVisible(true);
      tablero.getJugador0().setNombre(aux);
    }

  }

  class ReinicioListener implements ActionListener {

    public ReinicioListener() {
    }
    
    public void actionPerformed(ActionEvent e) {
      tablero.resetearJuego();
    }
  }
  
  
  class HelpListener implements ActionListener{
	MensajeHelp mensajeHelp;
	public HelpListener() {
		mensajeHelp = new MensajeHelp();
	}

	public void actionPerformed(ActionEvent e) {
		mensajeHelp.showMensaje();
	}
  }
}
