package test.java.Batalla.naval.TPFinal;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Batalla.naval.TPFinal.Tablero;

class TableroTest {

  Tablero tablero;

  @BeforeEach
  void setUp() throws Exception {
    tablero = new Tablero();
  }

  /**
   * Testea que las grillas no puedan ser creadas mas de una vez.
   */
  @Test
  void testCrearGrilla() {
    tablero.crearGrilla();
    int[][] grilla = tablero.getGrillaJugador1();
    grilla[0][0] = Tablero.BARCO;
    tablero.setGrillaJugador1(grilla);
    tablero.crearGrilla();
    assertEquals(grilla, tablero.getGrillaJugador1());
  }

  @Test
  void testDispararEventoEnGrilla() {
    // fail("Not yet implemented");
  }

}
