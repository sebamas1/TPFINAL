package test.java.Batalla.naval.TPFinal;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.AWTException;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import main.java.Batalla.naval.TPFinal.Display;
import main.java.Batalla.naval.TPFinal.Tablero;
import org.junit.jupiter.api.Test;

/**
 * Verifica que no se coloquen barcos en el tablero ni se coloree
 * la UI.
 * @author Juan Pablo
 *
 */
class MainTest {
  private Display display;

  @Test
  void test() throws AWTException {
    
    // Comentar este test antes de commitear a github
    //this.testUI();
    
    
  }
    
  void clickCasilla(Coordenada coord, boolean izq) {
    MouseEvent click;
    if (izq == true) {
      click = new MouseEvent(display, MouseEvent.MOUSE_RELEASED, 0,
          MouseEvent.BUTTON1_DOWN_MASK, 0, 0, 0, false, MouseEvent.BUTTON1);
    } else {
      click = new MouseEvent(display, MouseEvent.MOUSE_RELEASED, 0, 
          MouseEvent.BUTTON3_DOWN_MASK, 0, 0, 0, false, MouseEvent.BUTTON3);
    }
    display.getCasillas0()[coord.getFila()][coord.getColumna()].mouseReleased(click);
  }

  
  private void testUI() {
    display = new Display();
    Tablero tablero = display.getTablero();
    boolean izq = true;
    this.clickCasilla(new Coordenada(9, 9), izq);
    this.clickCasilla(new Coordenada(8, 8), izq);
    this.clickCasilla(new Coordenada(7, 8), izq);
    izq = false;
    this.clickCasilla(new Coordenada(0, 9), izq);
    this.clickCasilla(new Coordenada(1, 8), izq);
    this.clickCasilla(new Coordenada(1, 7), izq);
    ArrayList<Coordenada> listaChequearEmpty = new ArrayList<Coordenada>();
    listaChequearEmpty.add(new Coordenada(9, 9));
    listaChequearEmpty.add(new Coordenada(8, 7));
    listaChequearEmpty.add(new Coordenada(0, 9));
    listaChequearEmpty.add(new Coordenada(1, 7));
    ArrayList<Coordenada> listaChequearRojo = new ArrayList<Coordenada>();
    listaChequearRojo.add(new Coordenada(8, 8));
    listaChequearRojo.add(new Coordenada(9, 8));
    listaChequearRojo.add(new Coordenada(1, 8));
    listaChequearRojo.add(new Coordenada(1, 9));
   
    
    for (int i = 0; i < listaChequearEmpty.size(); i++) {
      Coordenada aux = listaChequearEmpty.get(i);
      int fila = aux.getFila();
      int col = aux.getColumna();
      if (tablero.getGrillaJugador0()[fila][col] != Tablero.AGUA 
          || display.getCasillas0()[fila][col].getBackground() == Display.ROJO) {
        fail("se marco/coloreo algo que no se tenia que marcar en el tablero/UI");
      }
    }
     
     
    for (int i = 0; i < listaChequearRojo.size(); i++) {
      Coordenada aux = listaChequearRojo.get(i);
      int fila = aux.getFila();  
      int col = aux.getColumna();
      if (tablero.getGrillaJugador0()[fila][col] != Tablero.BARCO
          || display.getCasillas0()[fila][col].getBackground() != Display.ROJO) {
        fail("no se marco/coloreo algo que se tenia que marcar en el tablero/UI");
      }
    }
  }
  
  
  private class Coordenada {
    private int fila;
    private int columna;
    
    Coordenada(int i, int j) {
      this.fila = i;
      this.columna = j;
    }
    
    public int getFila() {
      return fila;
    }
    
    public void setFila(int fila) {
      this.fila = fila;
    }
    
    public int getColumna() {
      return columna;
    }
    
    public void setColumna(int columna) {
      this.columna = columna;
    }
    
    
  }
}





