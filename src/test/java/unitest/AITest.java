package unitest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.AI;
import model.Tablero;

class AITest {
	
	Tablero tablero;
	AI ai;

	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();
		ai = new AI(tablero);
	}

	@Test
	/**
	 * Testea si los valores utilizados para la seleccion de casilla de la AI son validos
	 */
	void testRealizarTurno() {
		int[] coordenadas = ai.realizarTurno();
		if(coordenadas[0] != 3) {
			if(coordenadas[0] != 1) fail("El disparo de la AI no simulo un click primario ni secundario");
		}
		assert (coordenadas[1] >= 0 && coordenadas [1] <= 10);
		assert (coordenadas[2] >= 0 && coordenadas [2] <= 10);
	}

}
