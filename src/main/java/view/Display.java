package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Controler;
import model.Evento;
import model.Tablero;

@SuppressWarnings("serial")
public class Display extends JFrame implements Observer {
  private static final ImageIcon agua = new ImageIcon("agua.png");
  private static final ImageIcon agua2 = new ImageIcon("agua2.png");
  private static final ImageIcon barco = new ImageIcon("barco.png");
  private static final ImageIcon explosion = new ImageIcon("explosion.png");
  private static final ImageIcon help = new ImageIcon("about.png");
  private static final ImageIcon nuclear = new ImageIcon("radiation.png");
  private final Tablero tablero;
  public static final Color BACKGROUND = new Color(168, 197, 255);
  public static final Color ROJO = new Color(230, 86, 45);
  public static final Color GRIS = new Color(130, 130, 130);
  public static final Color AZUL = new Color(0, 0, 255);
  public static final Color CELESTE = new Color(60, 220, 255);
  private static final int FILAS = 10;
  private static final int COLUMNAS = 10;
  public static final int SIZE_CASILLA = 30;
  public static final int WIDTH = 750;
  public static final int HEIGHT = 780;
  public static final int MARGEN_TOP_GRILLA1 = 50;
  public static final int MARGEN_LEFT_GRILLA1 = 10;
  public static final int MARGEN_TOP_GRILLA2 = 400;
  public static final int MARGEN_LEFT_GRILLA2 = 10;
  private Controler controler;
  private Casilla[][] casillas0;
  private final Casilla[][] casillas1;
  private JButton botonReinicio;
  private JButton botonHelp;
  private JButton botonNuclear;
  private boolean estanDisparando;

  /**
   * Crea la UI Dibuja el tablero creando 2 matrices de botones crea tambien el
   * tablero que va a manejar la logica y el controler del mvc Guarda referencia a
   * �ste y las matrices de botones.
   */
  public Display(final Tablero tablero) {
    this.tablero = tablero;
    this.setTitle("Batalla naval!");
    this.setSize(WIDTH, HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    casillas0 = new Casilla[FILAS][COLUMNAS];
    casillas1 = new Casilla[FILAS][COLUMNAS];
    this.crearGrilla();
    this.setLayout(null);
    this.crearBotonReinicio();
    this.crearBotonHelp();
    this.crearBotonNuclear();
    this.setVisible(false);
    this.estanDisparando = false;
  }

  /**
   * Crea al identificador y recibe el string ingresado para agregarlo a la UI
   * final.
   */
  public void mostrarString(final String nombre, final int x,
        final int y, final int largo, final int ancho) {
    final JPanel namePanel = new JPanel(new BorderLayout());
    final JLabel displayNombre = new JLabel(nombre);
    displayNombre.setFont(new Font("ROMAN_BASELINE", Font.BOLD, 14));
    namePanel.setBackground(Display.BACKGROUND);
    namePanel.add(displayNombre);
    namePanel.setBounds(x, y, largo, ancho);
    this.add(namePanel);
    this.validate();
  }

  public Tablero getTablero() {
    return this.tablero;
  }

  public Casilla[][] getCasillas0() {
    return casillas0;
  }

  public void setCasillas0(final Casilla[][] casillas0) {
    this.casillas0 = casillas0;
  }

  public void update(Evento e) {
    colorearGrilla();
    if (e.getEvento() == Evento.TERMINO_PARTIDA) {
      this.enableGrillas(false);
    } else if (e.getEvento() == Evento.REINICIA_JUEGO) {
      this.enableGrillas(true);
      this.estanDisparando = false;
      this.botonReinicio.setVisible(false);
    } else if (e.getEvento() == Evento.REALIZA_DISPARO && !this.estanDisparando) {
      this.botonReinicio.setVisible(true);
    }
  }

  public void setControler(final Controler controler) {
    this.controler = controler;
  }

  /**
   * Crea las grillas de botones en la UI.
   */
  public void crearGrilla() {
    for (int i = 0; i < COLUMNAS; i++) {
      for (int j = 0; j < FILAS; j++) {
        final Casilla casilla0 = new Casilla((MARGEN_LEFT_GRILLA1 + SIZE_CASILLA * i),
            (MARGEN_TOP_GRILLA1 + SIZE_CASILLA * j), 0, j, i);
        final Casilla casilla1 = new Casilla((MARGEN_LEFT_GRILLA2 + SIZE_CASILLA * i),
            (MARGEN_TOP_GRILLA2 + SIZE_CASILLA * j), 1, j, i);
        casilla1.setBackground(CELESTE);
        casillas1[j][i] = casilla1;
        this.add(casilla1, BorderLayout.SOUTH);
        casilla0.setBackground(CELESTE);
        casillas0[j][i] = casilla0;
        this.add(casilla0, BorderLayout.SOUTH);
      }
    }
  }

  /**
   * Colorea la grilla segun los valores del tablero.
   */

  public void colorearGrilla() {
    final int[][] tableroLogico0 = tablero.getGrillaJugador0();
    final int[][] tableroLogico1 = tablero.getGrillaJugador1();
    for (int i = 0; i < FILAS; i++) {
      for (int j = 0; j < COLUMNAS; j++) {

        switch (tableroLogico1[i][j]) {
           case Tablero.BARCO:
           casillas1[i][j].setBackground(GRIS);
           break;
          case Tablero.AGUA_MISS:
            casillas1[i][j].setBackground(AZUL);
            casillas1[i][j].setIcon(agua2);
            break;
          case Tablero.BARCO_HIT:
            casillas1[i][j].setBackground(ROJO);
            casillas1[i][j].setIcon(explosion);
            break;
          case Tablero.AGUA:
            casillas1[i][j].setBackground(CELESTE);
            casillas1[i][j].setIcon(agua);
            break;
          default:
          ;
        }

        switch (tableroLogico0[i][j]) {
          case Tablero.BARCO:
            casillas0[i][j].setBackground(GRIS);
            casillas0[i][j].setIcon(barco);
            break;
          case Tablero.AGUA_MISS:
            casillas0[i][j].setBackground(AZUL);
            casillas0[i][j].setIcon(agua2);
            break;
          case Tablero.BARCO_HIT:
            casillas0[i][j].setBackground(ROJO);
            casillas0[i][j].setIcon(explosion);
            break;
          case Tablero.AGUA:
            casillas0[i][j].setBackground(CELESTE);
            casillas0[i][j].setIcon(agua);
            break;
          default:
          ;
        }
      }
    }
  }

  public JButton getBotonReinicio() {
    return this.botonReinicio;
  }
  
  public JButton getBotonHelp() {
	return this.botonHelp;
  }
  
  public JButton getBotonNuclear() {
    return this.botonNuclear;
  }

  private void crearBotonReinicio() {
    this.botonReinicio = new JButton("Reiniciar");
    // this.botonReinicio.addActionListener(e);
    JPanel botonPanel = new JPanel();
    botonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
    botonPanel.setBounds(500, 340, 120, 80);
    botonPanel.add(botonReinicio);
    botonPanel.setBackground(Display.BACKGROUND);
    this.add(botonPanel);
    this.validate();
    this.botonReinicio.setVisible(false);
  }
  
  private void crearBotonHelp() {
	  this.botonHelp = new JButton(Display.help);
	  botonHelp.setPreferredSize(new Dimension(48, 48));
	  JPanel botonPanel = new JPanel();
	  botonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
	  botonPanel.setBounds(Display.WIDTH-150,Display.HEIGHT-160 , 100, 150);
	  botonPanel.add(botonHelp);
	  botonPanel.setBackground(Display.BACKGROUND);
	  this.add(botonPanel);
	  this.validate();
  }

  private void crearBotonNuclear() {
    this.botonNuclear = new JButton(Display.nuclear);
    botonNuclear.setPreferredSize(new Dimension(48, 48));
    JPanel botonPanel = new JPanel();
    botonPanel.setBounds(Display.WIDTH-440,Display.HEIGHT - 435 , 56, 56);
    botonPanel.add(botonNuclear);
    botonPanel.setBackground(Display.BACKGROUND);
    this.add(botonPanel);
    this.validate();
  }
  
  private void enableGrillas(boolean enable) {
    for (int i = 0; i < FILAS; i++) {
      for (int j = 0; j < COLUMNAS; j++){
        casillas0[i][j].setEnabled(enable);
        casillas1[i][j].setEnabled(enable);
      }
    }
  }

  public class Casilla extends JButton implements MouseListener {

    private int fila;
    private int columna;
    private int propietario;

    /**
     * Crea la casilla donde corresponde con sus campos como corresponden.
     * 
     * @param x       posicion x en la pantalla
     * @param y       posicion y en la pantalla
     * @param jugador -> 0 - grilla de arriba o 1 - de abajo
     * @param fil     para que la casilla sepa en donde esta en la grilla
     * @param col     para que la casilla sepa en que columna esta en la grilla
     */
    public Casilla(final int x, final int y, final int jugador, final int fil, final int col) {
      this.setPropietario(jugador);
      this.setBounds(x, y, SIZE_CASILLA, SIZE_CASILLA);
      this.setFila(fil);
      this.setColumna(col);
      this.addMouseListener(this);
    }

    public void setPropietario(final int a) {
      this.propietario = a;
    }

    public int getPropietario() {
      return this.propietario;
    }

    public void setFila(final int a) {
      this.fila = a;
    }

    public void setColumna(final int b) {
      this.columna = b;
    }

    public void mouseClicked(final MouseEvent e) {

    }

    public void mouseEntered(final MouseEvent e) {

    }

    public void mouseExited(final MouseEvent e) {

    }

    public void mousePressed(final MouseEvent e) {

    }

    /**
     * Cuando se suelta el mouse sobre un boton de la grilla, se notifica al
     * tablero.
     */
    public void mouseReleased(final MouseEvent e) {
      // Le avisa al controlador de que se solto el click, en que fila, columna y de
      // que grilla
      controler.notifyEvent(e, this.fila, this.columna, this.propietario);
    }
  }

}
