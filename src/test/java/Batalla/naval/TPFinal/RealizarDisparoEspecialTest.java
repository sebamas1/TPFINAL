package test.java.Batalla.naval.TPFinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		int[][] grilla = tablero.getGrillaJugador1();
		prepararGrilla(grilla);
		tablero.setGrillaJugador1(grilla);
		try{
			disparo.realizarAccion(1, 0, 0, 1);
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		grilla = tablero.getGrillaJugador1();
		assertEquals(tablero.AGUA_MISS, grilla[0][0]);
		assertEquals(tablero.AGUA_MISS, grilla[0][1]);
		assertEquals(tablero.BARCO_HIT, grilla[1][0]);
		assertEquals(tablero.BARCO_HIT, grilla[1][1]);
	}
	
	private void prepararGrilla(int[][] grilla) {
		grilla[0][0] = tablero.AGUA;
		grilla[0][1] = tablero.AGUA;
		grilla[1][0] = tablero.BARCO;
		grilla[1][1] = tablero.BARCO;
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
