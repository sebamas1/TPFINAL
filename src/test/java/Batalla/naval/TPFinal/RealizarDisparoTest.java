package test.java.Batalla.naval.TPFinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Batalla.naval.TPFinal.RealizarDisparo;
import main.java.Batalla.naval.TPFinal.Tablero;

class RealizarDisparoTest {
	
	Tablero tablero;
	RealizarDisparo disparo;

	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();
		tablero.crearGrilla();
		disparo = new RealizarDisparo(tablero);
	}

	@Test
	/**
	 * Testea si valida correctamente en el caso de que querer disparar en un turno que no es el propio
	 */
	void testEsValidoTurnos() {
		tablero.setTurno(1);
		assertFalse(disparo.esValido(0, 0, 0, 1));
		tablero.setTurno(0);
		assertTrue(disparo.esValido(0, 0, 0, 1));
	}
	
	@Test
	/**
	 * Testea si al repetir un disparo en una casilla, indica que es un disparo no valido
	 */
	void testEsValidoCasillaRepetida() {
		int[][] grilla = tablero.getGrillaJugador1();
		grilla[0][0] = tablero.AGUA_MISS;
		grilla[0][1] = tablero.BARCO_HIT;
		tablero.setGrillaJugador1(grilla);
		assertFalse(disparo.esValido(0, 0, 0, 1));
		assertFalse(disparo.esValido(0, 0, 1, 1));
		assertTrue(disparo.esValido(0, 1, 1, 1));
	}

	@Test
	/**
	 * Testea si al realizar una accion se pasa satisfactoriamente al turno siguiente y de que al realizar
	 * el disparo las casillas cambien de estado satisfactoriamente
	 */
	void testRealizarAccion() {
		tablero.setTurno(0);
		int[][] grilla = tablero.getGrillaJugador1();
		grilla[3][3] = tablero.BARCO;
		grilla[3][4] = tablero.AGUA;
		tablero.setGrillaJugador1(grilla);
		disparo.realizarAccion(0, 3, 3, 1);
		assertEquals(tablero.BARCO_HIT,(tablero.getGrillaJugador1())[3][3]);
		assertEquals(1,tablero.getTurno());
		tablero.setTurno(0);
		disparo.realizarAccion(0, 3, 4, 1);
		assertEquals(tablero.AGUA_MISS,(tablero.getGrillaJugador1())[3][4]);
	}

}
