package view;

import model.Evento;

public class EstadisticasView implements Observer {

  private Display display;
  int disparosRealizadosJug0 = 0;
  int disparosRealizadosJug1 = 0;
  int barcosColocadosJug0 = 0;
  int barcosColocadosJug1 = 0;
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
    display.mostrarString(Integer.toString(this.barcosColocadosJug0), 600, 110, 15, 30);
    display.mostrarString(Integer.toString(this.barcosColocadosJug1), 600, 460, 15, 30);

    display.mostrarString("Barcos enemigos destruidos", 380, 550, 250, 50);
    display.mostrarString("Barcos enemigos destruidos", 380, 200, 250, 50);
    display.mostrarString(Integer.toString(this.barcosDestruidosJug0), 400, 590, 80, 50);
    display.mostrarString(Integer.toString(this.barcosDestruidosJug1), 400, 240, 80, 50);

    display.mostrarString("Tiros Realizados", 380, 420, 180, 50);
    display.mostrarString("Tiros Realizados", 380, 70, 180, 50);
    display.mostrarString(Integer.toString(disparosRealizadosJug0), 400, 110, 80, 30);
    display.mostrarString(Integer.toString(disparosRealizadosJug1), 400, 460, 80, 30);
  }
  
  /**
   * Segun la accion por la que fue notificado y la id del jugador
   * actualiza sus informaciones.
   */
  public void update(Evento e) {
    // TODO Auto-generated method stub
    
    switch (e.getEvento()) {

      case Evento.COLOCA_BARCOS:
        if (e.getIdCasilla() == 0) {
          this.barcosColocadosJug0++;
          display.mostrarString(Integer.toString(this.barcosColocadosJug0), 600, 110, 80, 30);
        } else if (e.getIdCasilla() == 1) {
          this.barcosColocadosJug1++;
          display.mostrarString(Integer.toString(this.barcosColocadosJug1), 600, 460, 80, 30);
        }
        break;
      case Evento.REALIZA_DISPARO:
        if (e.getIdCasilla() == 0) {
          this.disparosRealizadosJug0++;
          display.mostrarString(Integer.toString(disparosRealizadosJug0), 400, 110, 80, 30);
        } else if (e.getIdCasilla() == 1) {
          this.disparosRealizadosJug1++;
          display.mostrarString(Integer.toString(disparosRealizadosJug1), 400, 460, 80, 30);
        }


        break;

      case Evento.DESTRUYE_BARCO:
        if (e.getIdCasilla() == 0) {
          this.barcosDestruidosJug0++;
          display.mostrarString(Integer.toString(this.barcosDestruidosJug0), 400, 240, 80, 50);
        } else if (e.getIdCasilla() == 1) {
          this.barcosDestruidosJug1++;
          display.mostrarString(Integer.toString(this.barcosDestruidosJug1), 400, 590, 80, 50);
        }
        break;
      case Evento.REINICIA_JUEGO:
        this.disparosRealizadosJug0 = 0;
        this.disparosRealizadosJug1 = 0;
        this.barcosDestruidosJug0 = 0;
        this.barcosDestruidosJug1 = 0;
        this.barcosColocadosJug0 = 0;
        this.barcosColocadosJug1 = 0;
        display.mostrarString(Integer.toString(this.barcosDestruidosJug1), 400, 590, 180, 50);
        display.mostrarString(Integer.toString(this.barcosDestruidosJug0), 400, 240, 180, 50);
        display.mostrarString(Integer.toString(this.disparosRealizadosJug0), 400, 110, 15, 30);
        display.mostrarString(Integer.toString(this.disparosRealizadosJug1), 400, 460, 15, 30);
        display.mostrarString(Integer.toString(this.barcosColocadosJug0), 600, 110, 15, 30);
        display.mostrarString(Integer.toString(this.barcosColocadosJug1), 600, 460, 15, 30);
        this.display.repaint();
        break;
      default:
        ;

    

    }
  }
  
}
