package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Identificador extends JFrame {

  public JTextField nombre = new JTextField(15);
  private JLabel instruccion = new JLabel("Ingrese su nombre");
  private JButton botonIngresar = new JButton("Ok");

  public Identificador(ActionListener e) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(200, 130);
    botonIngresar.addActionListener(new IngresarListener(this));
    botonIngresar.addActionListener(e);
    
    JPanel identPanel = new JPanel();
    identPanel.add(instruccion);
    identPanel.add(nombre);
    identPanel.add(botonIngresar);
    
    this.add(identPanel);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  class IngresarListener implements ActionListener {
    private Identificador ident;

    public IngresarListener(Identificador ident) {
      this.ident = ident;
    }

    public void actionPerformed(ActionEvent e) {
      this.ident.dispose();
    }
  }
  


}