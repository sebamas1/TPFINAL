package view;

public interface Observer {
  
  public static final int COLOCA_BARCOS = 10;
  public static final int REALIZA_DISPARO = 20;
  public static final int DESTRUYE_BARCO = 30;
  public static final int REINICIA_JUEGO = 40;
  
  public void update(int accion, int idJugador);

}