package test.java.Batalla.naval.TPFinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.Batalla.naval.TPFinal.Barco;
import main.java.Batalla.naval.TPFinal.BarcosFactory;

class BarcosFactoryTest {

	@Test
	void createBarcoTest() {
		BarcosFactory fabrica = new BarcosFactory();
		Barco barco = fabrica.createBarco("Corbeta");
		assertEquals(2,barco.getSize());
		barco = fabrica.createBarco("Fragata");
		assertEquals(3,barco.getSize());
		barco = fabrica.createBarco("Destructor");
		assertEquals(4,barco.getSize());
	}

}
