package view;

import javax.swing.JOptionPane;

public class MensajeHelp extends JOptionPane {
	
	String instrucciones;
	
	public MensajeHelp() {
		armarInstrucciones();
	}
	
	/**
	 * Metodo utilizado para mostrar el mensaje en pantalla.
	 */
	public void showMensaje() {
		this.showMessageDialog(null, instrucciones, "Ayuda", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Crea el string a mostrar en el mensaje de informacion
	 */
	private void armarInstrucciones() {
		instrucciones = "Instrucciones:\n"
				+ "\nEtapa de colocacion de barcos\n\n"
				+ "En la primer etapa del juego se disponen de seis barcos a colocar.\n"
				+ "Para esto se utiliza el click primario para ubicarlos orientados\n"
				+ "verticalmente hacia abajo, y el click secundario para ubicarlos\n"
				+ "de forma horizontal hacia la derecha.\n"
				+ "\nEtapa de juego 1vs1\n\n"
				+ "Se utiliza cualquier click para especificar la casilla a la cual se\n"
				+ "desea disparar,el objetivo del juego es hundir todos los barcos\n"
				+ "enemigos antes que el hunda tus propios barcos.\n"
				+ "\nEn caso de querer reiniciar la partida, solo presione el boton ubicado\n"
				+ "en la zona central de la pantalla";
	}

}
