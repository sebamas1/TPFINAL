package test.java.Batalla.naval.TPFinal;
/**
 * 
 */


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.Batalla.naval.TPFinal.Tablero;

/**
 * @author augus
 *
 */
class TableroTest {
	
	Tablero tablero;
	int ultimaFila;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();
		tablero.crearGrilla();
		ultimaFila = tablero.getFilas()-1;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link main.java.Batalla.naval.TPFinal.Tablero#Tablero()}.
	 */
	@Test
	void testTablero() {
	//	fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.java.Batalla.naval.TPFinal.Tablero#crearGrilla()}.
	 */
	@Test
	void testCrearGrilla() {
		//fail("Not yet implemented");
	}
	
	
	/**
	 * Test method for {@link main.java.Batalla.naval.TPFinal.Tablero#esValido(int, int, int, int)}.
	 */
	@Test
	void testEsValido() {
		//fail("Not yet implemented");
	}

	/**
	 * Este metodo testea la correcta colocacion de barcos en Y
	 */
	@Test
	void testPositivoColocarBarcoY() { 
		//primero testeamos en la primer casilla con el barco alineado en y
		tablero.colocarBarco(1, 0, 0, 0);
		int[][] grilla = tablero.getGrilla0();
		assertEquals(0, grilla[0][0]);
		assertEquals(0, grilla[1][0]);
		assertEquals(1, grilla [1][1]);
	}
	
	/**
	 * Este metodo testea la correcta colocacion de barcos en Y
	 */
	@Test
	void testPositivoColocarBarcoX() {
		//testeamos en la utlima casilla de la primera fila con el barco alineado en x
		tablero.colocarBarco(3, ultimaFila, 0, 0);
		int[][] grilla = tablero.getGrilla0();
		assertEquals(0, grilla[ultimaFila][0]);
		assertEquals(0, grilla[ultimaFila][1]);
		assertEquals(1, grilla [ultimaFila-1][0]);
	}

}
