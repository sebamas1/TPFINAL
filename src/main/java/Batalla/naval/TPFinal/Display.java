package main.java.Batalla.naval.TPFinal;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Display extends JFrame implements Observer {
  private Tablero tablero;
  public static final Color ROJO = new Color(255, 0, 0);
  public static final Color GRIS = new Color(130, 130, 130);
  public static final Color AZUL = new Color(0, 65, 106);
  public static final Color CELESTE = new Color(60, 220, 255);
  private static final int FILAS = 10;
  private static final int COLUMNAS = 10;
  public static final int SIZE_CASILLA = 30;
  public static final int WIDTH = 750;
  public static final int HEIGHT = 780;
  public static final int MARGEN_TOP_GRILLA1 = 10;
  public static final int MARGEN_LEFT_GRILLA1 = 10;
  public static final int MARGEN_TOP_GRILLA2 = 430;
  public static final int MARGEN_LEFT_GRILLA2 = 10;
  private final Controler controler;
  private Casilla[][] casillas0;
  private Casilla[][] casillas1;

  /** Crea la UI
   * Dibuja el tablero creando 2 matrices de botones
   * crea tambien el tablero que va a manejar la logica
   * y el controler del mvc
   * Guarda referencia a éste y las matrices de botones.
   */
  public Display() {
    this.setTitle("Batalla naval!");
    this.setSize(WIDTH, HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.setLayout(null);
    tablero = new Tablero();
    tablero.registerObserver(this);
    controler = new Controler(tablero);
    casillas0 = new Casilla[FILAS][COLUMNAS];
    casillas1 = new Casilla[FILAS][COLUMNAS];
    this.crearGrilla();
  }

  public Tablero getTablero() {
    return this.tablero;
  }
  
  
  public Casilla[][] getCasillas0() {
    return casillas0;
  }

  public void setCasillas0(Casilla[][] casillas0) {
    this.casillas0 = casillas0;
  }

  public void update() {
    colorearGrilla();
  }

  /** Crea las grillas de botones en la UI.
   */
  public void crearGrilla() {
    for (int i = 0; i < COLUMNAS; i++) {
      for (int j = 0; j < FILAS; j++) {
        Casilla casilla = new Casilla((MARGEN_LEFT_GRILLA1 + SIZE_CASILLA * i),
            (MARGEN_TOP_GRILLA1 + SIZE_CASILLA * j), 0, j, i);
        casilla.setBackground(CELESTE);
        casillas0[j][i] = casilla;
        add(casilla);
      }
    }
    for (int i = 0; i < COLUMNAS; i++) {
      for (int j = 0; j < FILAS; j++) {
        Casilla casilla = new Casilla((MARGEN_LEFT_GRILLA2 + SIZE_CASILLA * i),
            (MARGEN_TOP_GRILLA2 + SIZE_CASILLA * j), 1, j, i);
        casilla.setBackground(CELESTE);
        casillas1[j][i] = casilla;
        add(casilla);
      }
    }
  }
  
  /** Colorea la grilla segun los valores del tablero.
   */
  
  public void colorearGrilla() {
    int[][] tableroLogico0 = tablero.getGrilla0();
    int[][] tableroLogico1 = tablero.getGrillaJugador1();
    for (int i = 0; i < FILAS; i++) {
      for (int j = 0; j < COLUMNAS; j++) {
        
        switch (tableroLogico1[i][j]) {
          case Tablero.BARCO:
            casillas1[i][j].setBackground(GRIS);
            break;
          case Tablero.AGUA_MISS:
            casillas1[i][j].setBackground(AZUL);
            break;
          case Tablero.BARCO_HIT:
            casillas1[i][j].setBackground(ROJO);
            break;
          default:
            ;
        }
        
        switch (tableroLogico0[i][j]) {
          case Tablero.BARCO:
            casillas0[i][j].setBackground(GRIS);
            break;
          case Tablero.AGUA_MISS:
            casillas0[i][j].setBackground(AZUL);
            break;
          case Tablero.BARCO_HIT:
            casillas0[i][j].setBackground(ROJO);
            break;
          default:
            ;
        }
      }
    }
  }
  
  @SuppressWarnings("serial")
  public class Casilla extends Button implements MouseListener {
    
    private int fila;
    private int columna;
    private int propietario;
    
    /** Crea la casilla donde corresponde con sus campos como corresponden.
   * @param x posicion x en la pantalla
   * @param y posicion y en la pantalla
   * @param jugador -> 0 - grilla de arriba o 1 - de abajo
   * @param fil para que la casilla sepa en donde esta en la grilla 
   * @param col para que la casilla sepa en que columna esta en la grilla
 */
    public Casilla(int x, int y, int jugador, int fil, int col) {
      this.setPropietario(jugador);
      this.setBounds(x, y, SIZE_CASILLA, SIZE_CASILLA);
      this.setFila(fil);
      this.setColumna(col);
      this.addMouseListener(this);
    }
    
    public void setPropietario(int a) {
      this.propietario = a;
    }
    
    public int getPropietario() {
      return this.propietario;
    }
    
    public void setFila(int a) {
      this.fila = a;
    }
    
    public void setColumna(int b) {
      this.columna = b;
    }

    public void mouseClicked(MouseEvent e) {
      
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
      //Le avisa al controlador de que se solto el click, en que fila, columna y de que grilla
      controler.notifyEvent(e, this.fila, this.columna, this.propietario);
      System.out.println("Deberia ejecutarme");
    }
  }
}