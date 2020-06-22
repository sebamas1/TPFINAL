package main.java.model;


public interface Jugador {
  
  public static final int CANT_BARCOS = 6;

  public String getNombre();

  public void setNombre(String nombre);

  public int getPlayerID();

  public void setPlayerID(int playerID);

  public int getBarcosColocadosJugador();
  
  public void setBarcosColocadosJugador(int barcosColocadosJugador);
  
  public int[] realizarTurno();
  
}
