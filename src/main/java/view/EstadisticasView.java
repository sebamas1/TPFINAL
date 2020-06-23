package view;

public class EstadisticasView implements Observer {

  private Display display;
  int disparosRealizados = 0;
  int barcosColocados = 0;
  int barcosDestruidosJug0 = 0;
  int barcosDestruidosJug1 = 0;

  /**
   * Crea los textos con las informaciones.
   * @param display frame sobre el que va a hacer las cosas.
   */
  public EstadisticasView(Display display) {
    this.display = display;
    display.mostrarString("Barcos colocados", 580, 420, 150, 50);
    display.mostrarString("Barcos colocados", 580, 70, 150, 50);
    display.mostrarString(Integer.toString(this.barcosColocados), 600, 110, 15, 30);
    display.mostrarString(Integer.toString(this.barcosColocados), 600, 460, 15, 30);

    display.mostrarString("Barcos enemigos destruidos", 380, 550, 180, 50);
    display.mostrarString("Barcos enemigos destruidos", 380, 200, 180, 50);
    display.mostrarString(Integer.toString(this.barcosDestruidosJug1), 400, 590, 180, 50);
    display.mostrarString(Integer.toString(this.barcosDestruidosJug0), 400, 240, 180, 50);

    display.mostrarString("Tiros Realizados", 380, 420, 180, 50);
    display.mostrarString("Tiros Realizados", 380, 70, 180, 50);
    display.mostrarString(Integer.toString(disparosRealizados), 400, 110, 15, 30);
    display.mostrarString(Integer.toString(disparosRealizados), 400, 460, 15, 30);
  }
  
  /**
   * Segun la accion por la que fue notificado y la id del jugador
   * actualiza sus informaciones.
   */
  public void update(int accion, int idJugador) {
    // TODO Auto-generated method stub
    
    switch (accion) {

      case Observer.COLOCA_BARCOS:
        this.barcosColocados++;
        display.mostrarString(Integer.toString(this.barcosColocados), 600, 110, 15, 30);
        display.mostrarString(Integer.toString(this.barcosColocados), 600, 460, 15, 30);
        break;
      case Observer.REALIZA_DISPARO:
        this.disparosRealizados++;
        display.mostrarString(Integer.toString(disparosRealizados), 400, 110, 15, 30);
        display.mostrarString(Integer.toString(disparosRealizados), 400, 460, 15, 30);
        break;

      case Observer.DESTRUYE_BARCO:
        if (idJugador == 0) {
          this.barcosDestruidosJug0++;
          display.mostrarString(Integer.toString(this.barcosDestruidosJug0), 400, 240, 180, 50);
        } else if (idJugador == 1) {
          this.barcosDestruidosJug1++;
          display.mostrarString(Integer.toString(this.barcosDestruidosJug1), 400, 590, 180, 50);
        }
        break;
      case Observer.REINICIA_JUEGO:
        this.disparosRealizados = 0;
        this.barcosDestruidosJug0 = 0;
        this.barcosDestruidosJug1 = 0;
        this.barcosColocados = 0;
        display.mostrarString(Integer.toString(this.barcosDestruidosJug1), 400, 590, 180, 50);
        display.mostrarString(Integer.toString(this.barcosDestruidosJug0), 400, 240, 180, 50);
        display.mostrarString(Integer.toString(this.disparosRealizados), 400, 110, 15, 30);
        display.mostrarString(Integer.toString(this.disparosRealizados), 400, 460, 15, 30);
        display.mostrarString(Integer.toString(this.barcosColocados), 600, 110, 15, 30);
        display.mostrarString(Integer.toString(this.barcosColocados), 600, 460, 15, 30);
        this.display.repaint();
        break;
      default:
        ;

    

    }
  }
  
}
