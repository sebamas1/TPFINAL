package test.java.Batalla.naval.TPFinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Batalla.naval.TPFinal.ColocarBarcos;
import main.java.Batalla.naval.TPFinal.RealizarDisparoEspecial;
import main.java.Batalla.naval.TPFinal.Tablero;

class RealizarDisparoEspecialTest {
	
	Tablero tablero;
	RealizarDisparoEspecial disparo;

	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();
		tablero.crearGrilla();
		disparo = new RealizarDisparoEspecial(tablero);
	}

	@Test
	/**
	 * Testea si al relizar un disparo especial en una zona limite, esta no produce un comportamiento anomalo en el
	 * sistema, y a su vez, si las casillas afectadas por este cambiaron de estado satisfactoriamente.
	 */
	void testRealizarAccion() {
		int[][] grilla = tablero.getGrillaJugador0();
		prepararGrilla(grilla);
		tablero.printMatriz();
		tablero.setGrillaJugador0(grilla);
		try{
			disparo.realizarAccion(1, 0, 0, 0);
	    //tablero.printMatriz();
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		grilla = tablero.getGrillaJugador0();
		assertEquals(Tablero.AGUA_MISS, grilla[0][0]);
		assertEquals(Tablero.AGUA_MISS, grilla[0][1]);
		assertEquals(Tablero.BARCO_HIT, grilla[1][0]);
		assertEquals(Tablero.BARCO_HIT, grilla[1][1]);
	}
	
	private void prepararGrilla(int[][] grilla) {
    tablero.setTurno(0);
    ColocarBarcos colocarBarco = new ColocarBarcos(tablero);
    colocarBarco.realizarAccion(3, 1, 0, 0);
		grilla[0][0] = Tablero.AGUA;
		grilla[0][1] = Tablero.AGUA;
	}

	@Test
	/**
	 * Testea si al repetir un disparo en una casilla, indica que es un disparo no valido.
	 */
	void testEsValido() {
		int[][] grilla = tablero.getGrillaJugador1();
		grilla[0][0] = tablero.BARCO_HIT;
		grilla[0][1] = tablero.AGUA_MISS;
		tablero.setGrillaJugador1(grilla);
		assertFalse(disparo.esValido(1, 0, 0, 1));
		assertFalse(disparo.esValido(1, 0, 1, 1));
		assertTrue(disparo.esValido(1, 1, 0, 1));
		tablero.setTurno(1);
		assertFalse(disparo.esValido(1, 1, 0, 1));
	}

}
