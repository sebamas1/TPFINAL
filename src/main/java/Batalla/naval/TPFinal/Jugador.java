package main.java.Batalla.naval.TPFinal;


import java.util.ArrayList;

public class Jugador {
  
  public static final int CANT_BARCOS = 6;
  private String nombre;
  private int playerID;
  private ArrayList<Barco> barcos;
  public int barcosColocadosJugador = 0;

  /** El jugador tiene los barcos, ya veremos bien que mas hacemos, puede cambiar esto.
   * le ponemos un nombre para mostrar en el display cuando gana
   * player ID porque hace mas facil todo trabajar con un enterito
   * Jugador 0 -> grilla de arriba
   * Jugador 1 -> grilla de abajo
   * @param nombre Nombre del jugador
   * @param playerID Identificacion para metodos del tablero
   */
  public Jugador(String nombre, int playerID) {
    
    this.barcos = new ArrayList<Barco>();
    BarcosFactory factory = new BarcosFactory();
    this.nombre = nombre;
    this.playerID = playerID;
    barcos.add(factory.createBarco("Corbeta"));
    barcos.add(factory.createBarco("Corbeta"));
    barcos.add(factory.createBarco("Corbeta"));
    barcos.add(factory.createBarco("Fragata"));
    barcos.add(factory.createBarco("Fragata"));
    barcos.add(factory.createBarco("Destructor"));
  }
  
  public ArrayList<Barco> getBarcos(){
    return this.barcos;
  }
}
