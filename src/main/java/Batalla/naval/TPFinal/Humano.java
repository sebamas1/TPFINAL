package main.java.Batalla.naval.TPFinal;

import java.util.ArrayList;

public class Humano implements Jugador {

  protected String nombre;
  protected int playerID;

  public Humano() {
    
  }
  
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getPlayerID() {
    return playerID;
  }

  public void setPlayerID(int playerID) {
    this.playerID = playerID;
  }

  public int getBarcosColocadosJugador() {
    return barcosColocadosJugador;
  }

  public void setBarcosColocadosJugador(int barcosColocadosJugador) {
    this.barcosColocadosJugador = barcosColocadosJugador;
  }


  public int barcosColocadosJugador = 0;

  /**
   * El jugador tiene los barcos, ya veremos bien que mas hacemos, puede cambiar
   * esto. le ponemos un nombre para mostrar en el display cuando gana player ID
   * porque hace mas facil todo trabajar con un enterito Jugador 0 - grilla de
   * arriba Jugador 1 - grilla de abajo
   * 
   * @param nombre   Nombre del jugador
   * @param playerID Identificacion para metodos del tablero
   */


  public Humano(String nombre, int playerID) {
    this.nombre = nombre;
    this.playerID = playerID;
  }

  
  public int[] realizarTurno() {
    return null;
  }
}
