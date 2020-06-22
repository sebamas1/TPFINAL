package test.java.unitest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import main.java.model.Barco;
import main.java.model.Jugador;
import main.java.model.PosicionBarco;
import main.java.model.Tablero;
import main.java.view.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TableroTest {

  Tablero tablero;

  @BeforeEach
  void setUp() throws Exception {
    tablero = new Tablero();
  }

  /**
   * Testea que las grillas no puedan ser creadas mas de una vez.
   * y que se inicialicen como AGUA
   */
  @Test
  void testCrearGrilla() {
    tablero.crearGrillas();
    int[][] grilla = tablero.getGrillaJugador1();
    for (int i = 0; i < grilla.length; i++) {
      for (int j = 0; j < grilla[i].length; j++) {
        assertEquals("Se creo la grilla incorrectamente", grilla[i][j], Tablero.AGUA);
      }
    }
    
    grilla[0][0] = Tablero.BARCO;
    tablero.setGrillaJugador1(grilla);
    tablero.crearGrillas();
    for (int i = 0; i < grilla.length; i++) {
      for (int j = 0; j < grilla[i].length; j++) {
        assertEquals("se creo 2 veces ", grilla[i][j], tablero.getGrillaJugador1()[i][j]);
      }
    }
  }

  @Test
  void testDispararEventoEnGrilla() {
    // fail("Not yet implemented");
  }

  @Test
  void testObserverInteraction() {

    Observer observer = null;
    tablero.registerObserver(observer);
    assertEquals("No registra el observer", tablero.getObservers().size(), 1);
    tablero.detachObserver(observer);
    assertEquals("No saca del hashset al observer", tablero.getObservers().size(), 0);
    
  }

  void testConstructorTablero() {
    
    assertTrue("No se inicializaron los turnos en 0", tablero.getTurno() == 0);
    assertTrue("No se creo al jugador0", tablero.getJugador0() != null);
    assertTrue("No se creo al jugador1", tablero.getJugador1() != null);
    assertEquals("No se crearon CANT_BARCOS para el jugador 0",
        tablero.getBarcosJug0().size(), Jugador.CANT_BARCOS);
    assertEquals("No se crearon CANT_BARCOS para el jugador 1",
        tablero.getBarcosJug1().size(), Jugador.CANT_BARCOS);
  }

  @Test
  void testEncontrarBarco() {
    Barco barco = tablero.getBarcosJug0().get(0);
    barco.setTipo("barcoTest");
    barco.setSize(4);
    barco.setPos(new PosicionBarco(0, 0, 0, 3));
    
    // Barcos posicionados en forma vertical
    tablero.setTurno(1);
    Barco resultado = tablero.encontrarBarco(0, 1);
    assertTrue((barco.getTipo()) == resultado.getTipo() 
        && (barco.getSize() == resultado.getSize()));
    
    tablero.setTurno(1);
    resultado = tablero.encontrarBarco(0, 2);
    assertTrue((barco.getTipo()) == resultado.getTipo() 
        && (barco.getSize() == resultado.getSize()));
    
    tablero.setTurno(1);
    resultado = tablero.encontrarBarco(0, 3);
    assertTrue((barco.getTipo()) == resultado.getTipo() 
        && (barco.getSize() == resultado.getSize()));
    
    // Ahora vertical
    
    barco = tablero.getBarcosJug1().get(0);
    barco.setTipo("barcoTest2");
    barco.setSize(4);
    barco.setPos(new PosicionBarco(6, 9, 9, 9));
    tablero.setTurno(2);
    resultado = tablero.encontrarBarco(6, 9);
    assertTrue((barco.getTipo()) == resultado.getTipo() 
        && (barco.getSize() == resultado.getSize()));
    
    tablero.setTurno(2);
    resultado = tablero.encontrarBarco(7, 9);
    assertTrue((barco.getTipo()) == resultado.getTipo() 
        && (barco.getSize() == resultado.getSize()));
    
    tablero.setTurno(2);
    resultado = tablero.encontrarBarco(9, 9);
    assertTrue((barco.getTipo()) == resultado.getTipo() 
        && (barco.getSize() == resultado.getSize()));
    
    

  }

  @Test
  void testTerminoPartida() {
    
    ArrayList<Barco> barcos0 = tablero.getBarcosJug0();
    ArrayList<Barco> barcos1 = tablero.getBarcosJug1();
    
    barcos0.get(0).setVida(1);
    barcos1.get(0).setVida(1);
    assertFalse("La partida no deberia terminar hasta quue mueran todos los barcos de un jugador",
        tablero.terminoPartida());
    
    for (int i = 0; i < Jugador.CANT_BARCOS; i++) {
      barcos0.get(i).setVida(0);
    }
    assertTrue("La partida deberia terminar cuando todos los barcos del jugador0 mueren",
        tablero.terminoPartida());
    
    barcos0.get(0).setVida(1);
    
    for (int i = 0; i < Jugador.CANT_BARCOS; i++) {
      barcos1.get(i).setVida(0);
    }
    assertTrue("La partida deberia terminar cuando todos los barcos del jugador1 mueren",
        tablero.terminoPartida());
    
    
    barcos0.get(0).setVida(0);
    assertTrue("La partida deberia terminar si mueren todos los barcos de los dos jugadores",
        tablero.terminoPartida());
    
  }

  @Test
  void testPerdio() {
    ArrayList<Barco> barcos = tablero.getBarcosJug0();
    barcos.get(0).setVida(1);
    assertFalse("No deberia perder si quedan barcos con vida", tablero.perdio(barcos));
    for (int i = 0; i < Jugador.CANT_BARCOS; i++) {
      barcos.get(i).setVida(0);
    }
    assertTrue("Deberia perder si todos sus barcos tienen vida 0", tablero.perdio(barcos));
  }
  
  @Test
  void testEncontrarGanador() {

    
    ArrayList<Barco> barcos0 = tablero.getBarcosJug0();
    ArrayList<Barco> barcos1 = tablero.getBarcosJug1();
    
    barcos0.get(0).setVida(1);
    barcos1.get(0).setVida(1);
    assertTrue("no deberia encontrar ganador porque todavia le quedan barcos a los dos jug", 
        tablero.encontrarGanador() == null);
    
    for (int i = 0; i < Jugador.CANT_BARCOS; i++) {
      barcos0.get(i).setVida(0);
    }
    assertTrue("Deberia ganar el jugador 1", tablero.encontrarGanador().getNombre() 
        == tablero.getJugador1().getNombre());
    
    barcos0.get(0).setVida(1);
    for (int i = 0; i < Jugador.CANT_BARCOS; i++) {
      barcos1.get(i).setVida(0);
    }
    
    assertTrue("Deberia ganar el jugador 0", tablero.encontrarGanador().getNombre() 
        == tablero.getJugador0().getNombre());
  }
}
