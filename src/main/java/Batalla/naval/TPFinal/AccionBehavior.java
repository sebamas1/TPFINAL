package main.java.Batalla.naval.TPFinal;

import java.awt.event.MouseEvent;

public interface AccionBehavior {

  public void realizarAccion(Tablero tablero, MouseEvent e, int i, int j, int id);
  
  public boolean esValido(Tablero tablero, MouseEvent e, int i, int j, int id); 
}
