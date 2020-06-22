package test.java.unitest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import main.java.model.AccionBehavior;
import main.java.model.ColocarBarcos;
import main.java.model.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ColocarBarcosTest {

  Tablero tablero;
  AccionBehavior colocarBarco;

  @BeforeEach
  void setUp() throws Exception {
    tablero = new Tablero();
    tablero.crearGrillas();
    colocarBarco = new ColocarBarcos(tablero);
  }
  
  /**
   * Chequea que solo sea valida la colocacion
   * de barcos en el turno que corresponde.
   */
  @Test
  void testEsValidoTurnos() {
    tablero.setTurno(1);
    assertTrue(colocarBarco.esValido(0, 0, 0, 1));
    tablero.setTurno(0);
    assertFalse(colocarBarco.esValido(0, 0, 0, 1));
  }
  
  @Test
  void testEsValidaCasilla() {
    tablero.setTurno(0);
    int[][] grilla0 = tablero.getGrillaJugador0();
    grilla0[0][0] = Tablero.AGUA;
    grilla0[2][2] = Tablero.BARCO;
    //vertical
    assertTrue(colocarBarco.esValido(1, 0, 0, 0));
    assertFalse(colocarBarco.esValido(1, 2, 2, 0));
    assertFalse(colocarBarco.esValido(1, 1, 2, 0));
    //horizontal
    assertTrue(colocarBarco.esValido(3, 0, 0, 0));
    assertFalse(colocarBarco.esValido(3, 2, 2, 0));
    assertFalse(colocarBarco.esValido(3, 2, 1, 0));
  }
  
  

  /**
   * Chequea que los barcos efectivamente se coloquen donde
   * se tienen que colocar.
   */
  @Test
  void ubicBarcosPositiva() {
    tablero.setTurno(0);
    //Coloca un barco verticalmente en la casilla[3][3] del jugador 0
    colocarBarco.realizarAccion(1, 3, 3, 0);
    //Coloca un barco horizontalmente en la casilla[3][3] del jugador 1
    colocarBarco.realizarAccion(3, 3, 3, 1);
    int[][] grilla0 = tablero.getGrillaJugador0();
    int[][] grilla1 = tablero.getGrillaJugador1();
    
    assertTrue(grilla0[3][3] == Tablero.BARCO);
    assertTrue(grilla0[4][3] == Tablero.BARCO);
    assertTrue(grilla1[3][3] == Tablero.BARCO);
    assertTrue(grilla1[3][4] == Tablero.BARCO);
  }
  
  /**
   * Chequea que los barcos efectivamente se coloquen donde
   * se tienen que colocar.
   */
  @Test
  void ubicBarcosNegativa() {
    tablero.setTurno(0);
    //Coloca un barco verticalmente en la casilla[3][3] del jugador 0
    colocarBarco.realizarAccion(1, 3, 3, 0);
    //Coloca un barco horizontalmente en la casilla[3][3] del jugador 1
    colocarBarco.realizarAccion(3, 3, 3, 1);
    int[][] grilla0 = tablero.getGrillaJugador0();
    int[][] grilla1 = tablero.getGrillaJugador1();
    for (int i = 0; i < grilla0.length; i++) {
      for (int j = 0; j < grilla0[i].length; j++) {
        if (! ((i == 3 && j == 3) || (i == 4 && j == 3))) {
          assertFalse(grilla0[i][j] == Tablero.BARCO);
        }
        if (! ((i == 3 && j == 3) || (i == 3 && j == 4))) {
          assertFalse(grilla1[i][j] == Tablero.BARCO);
        }
        
      }
    }
  }
}
