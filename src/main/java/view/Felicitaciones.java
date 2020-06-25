package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Evento;
import model.Tablero;

public class Felicitaciones extends JFrame implements Observer {

  private static final long serialVersionUID = 1L;
  private JButton botonIngresar = new JButton("Ok");
  private JLabel mensaje;
  private Tablero tablero;

  public Felicitaciones(Tablero tablero) {
    this.tablero = tablero;
    this.setResizable(false);
  }

  /**
   * Crea la ventana felicitando al ganador.
   * 
   * @param nombre Nombre del ganador.
   */
  public void felicitar(String nombre) {
    this.getContentPane().removeAll();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400, 100);
    botonIngresar.addActionListener(new IngresarListener(this));
    if (tablero.encontrarGanador().getPlayerID() == 0) {
      mensaje = new JLabel("Felicitaciones " + nombre + " ganaste!");
    } else {
      mensaje = new JLabel("Mal ahi " + tablero.getJugador0().getNombre() 
          + " aprieta reiniciar para intentarlo de nuevo");
    }
    mensaje.setMinimumSize(new Dimension(40, 30));
    JPanel identPanel = new JPanel();
    identPanel.add(mensaje);
    identPanel.add(botonIngresar);
    this.add(identPanel);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  /**
   * Recibe updates del tablero.
   */
  public void update(Evento e) {
    if (e.getEvento() == Evento.TERMINO_PARTIDA) {
      felicitar(tablero.encontrarGanador().getNombre());
    }
  }

  class IngresarListener implements ActionListener {
    private Felicitaciones felic;

    public IngresarListener(Felicitaciones felic) {
      this.felic = felic;
    }

    public void actionPerformed(ActionEvent e) {
      this.felic.setVisible(false);
    }
  }

}
