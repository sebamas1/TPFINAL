package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class Identificador extends JFrame {

  private static final long serialVersionUID = 1L;
  public JTextField nombre = new JTextFieldLimit(15);
  private JLabel instruccion = new JLabel("Ingrese su nombre de usuario", SwingConstants.CENTER);
  private JButton botonIngresar = new JButton("Ok");

  /**
   * Crea la ventana que pregunta por el nombre.
   * 
   * @param e Action listener del controler.
   */
  public Identificador(ActionListener e) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    botonIngresar.addActionListener(new IngresarListener(this));
    botonIngresar.addActionListener(e);
    this.nombre.setToolTipText("No mas de 15 caracteres");
    JPanel identPanel = new JPanel();
    identPanel.add(instruccion);
    identPanel.add(nombre);
    identPanel.add(botonIngresar);
    identPanel.setLayout(new GridLayout(0, 1));
    identPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    this.add(identPanel);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.setResizable(false);
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

  
  class JTextFieldLimit extends JTextField {
    /**
     * Stackoverflow me dijo que asi limito los caracteres de una JLabel.
     */
    private static final long serialVersionUID = 1L;
    private int limit;

    public JTextFieldLimit(int limit) {
      super();
      this.limit = limit;
    }

    @Override
    protected Document createDefaultModel() {
      return new LimitDocument();
    }

    private class LimitDocument extends PlainDocument {

      /**
      * .
      */
      private static final long serialVersionUID = 1L;

      @Override
      public void insertString(int offset, String str, AttributeSet attr)
          throws BadLocationException {
        if (str == null) {
          return;
        }

        if ((getLength() + str.length()) <= limit) {
          super.insertString(offset, str, attr);
        }
      }

    }

  }

}