package view;

import model.Evento;

public interface Observer {
  
  public static final int COLOCA_BARCOS = 10;
  public static final int REALIZA_DISPARO = 20;
  public static final int DESTRUYE_BARCO = 30;
  public static final int REINICIA_JUEGO = 40;
  public static final int TERMINO_PARTIDA = 50;
  
  public void update(Evento e);

}