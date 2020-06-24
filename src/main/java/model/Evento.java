package model;

public class Evento {

  public static final int COLOCA_BARCOS = 10;
  public static final int REALIZA_DISPARO = 20;
  public static final int DESTRUYE_BARCO = 30;
  public static final int REINICIA_JUEGO = 40;
  public static final int TERMINO_PARTIDA = 50;
  public static final int GRILLA_CREADA = 60;
  
  private int evento;
  private int idCasilla;
  
  public Evento(int evento, int idCasilla) {
    this.evento = evento;
    this.idCasilla = idCasilla;
  }

  public int getEvento() {
    return evento;
  }

  public void setEvento(int evento) {
    this.evento = evento;
  }

  public int getIdCasilla() {
    return idCasilla;
  }

  public void setIdJugador(int idJugador) {
    this.idCasilla = idJugador;
  }
}
