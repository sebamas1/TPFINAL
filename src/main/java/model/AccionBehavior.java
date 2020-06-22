package main.java.model;

public interface AccionBehavior {

  public void realizarAccion(int click, int i, int j, int id);
  
  public boolean esValido(int click, int i, int j, int id); 
}
