package main.java.view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Identificador {
  private String id = "Jugador 1";
  private JTextField idField;
  private JLabel priceLabel;

  public Identificador() {
    idField = new JTextField(10);
    desplegarUserInt();
  }

  private void desplegarUserInt() {
    int selection = JOptionPane.showConfirmDialog(null, getPanel(), "Ingreso del ID : ",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE);

    // si el usuario ingreso el ID y presiono OK
    if (selection == JOptionPane.OK_OPTION) {
      String aux = String.valueOf(idField.getText());
      // System.out.println("aux es:" + aux + ".");
      if (aux != null && aux.length() > 0) {
        id = aux;
      }

      JOptionPane.showMessageDialog(null, "ID ingresado : " + (id), "ID :" + " ",
          JOptionPane.PLAIN_MESSAGE);
    } else if (selection == JOptionPane.CANCEL_OPTION) {     // si se apreto el boton cancelar
      JOptionPane.showMessageDialog(null, "El identificador fue seteado al ID por defecto", null,
          JOptionPane.PLAIN_MESSAGE);
    }
  }

  public String getID() {
    return id;
  }

  private JPanel getPanel() {
    JPanel basePanel = new JPanel();
    basePanel.setOpaque(true);
    basePanel.setBackground(Color.BLUE.darker());

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(3, 2, 5, 5));
    centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    centerPanel.setOpaque(true);
    centerPanel.setBackground(Color.WHITE);

    priceLabel = new JLabel("Ingrese su ID : ");

    centerPanel.add(priceLabel);
    centerPanel.add(idField);
    basePanel.add(centerPanel);

    return basePanel;
  }
}