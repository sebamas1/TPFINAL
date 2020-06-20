package main.java.Batalla.naval.TPFinal;

public interface AccionBehavior {

  public void realizarAccion(int click, int i, int j, int id);
  
  public boolean esValido(int click, int i, int j, int id); 
}
