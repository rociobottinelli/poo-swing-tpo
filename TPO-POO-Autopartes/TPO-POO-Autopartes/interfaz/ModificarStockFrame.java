package interfaz;
import javax.swing.*;

import negocio.Administrador;

import java.awt.*;
import java.awt.event.*;

public class ModificarStockFrame extends JFrame {
	 private static final long serialVersionUID = 1L;
    private Administrador admin;

    public ModificarStockFrame(Administrador admin) {
        this.admin = admin;

        setTitle("Modificar Stock de Autoparte");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        // Componentes de la interfaz
        JPanel panel = new JPanel();
        JLabel labelCodigo = new JLabel("Código de la autoparte:");
        JTextField textFieldCodigo = new JTextField(10);
        JLabel labelOpcion = new JLabel("OPCIONES:");
        JRadioButton radioSumar = new JRadioButton("Sumar unidades al stock actual");
        JRadioButton radioReemplazar = new JRadioButton("Reemplazar el valor del stock");
        ButtonGroup grupoOpciones = new ButtonGroup();
        grupoOpciones.add(radioSumar);
        grupoOpciones.add(radioReemplazar);
        JButton botonModificar = new JButton("Modificar Stock");

        // Layout y agregado de componentes al panel
        panel.setLayout(new GridLayout(5, 1));
        panel.add(labelCodigo);
        panel.add(textFieldCodigo);
        panel.add(labelOpcion);
        panel.add(radioSumar);
        panel.add(radioReemplazar);
        panel.add(botonModificar);

        // Acción del botón Modificar Stock
        botonModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int codigo;
                try {
                    codigo = Integer.parseInt(textFieldCodigo.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ModificarStockFrame.this, "Ingrese un código válido.");
                    return;
                }

                if (!admin.existeAutoparte(codigo)) {
                    JOptionPane.showMessageDialog(ModificarStockFrame.this, "La autoparte con código " + codigo + " no existe.");
                    return;
                }

                int opcion = 0;
                if (radioSumar.isSelected()) {
                    opcion = 1;
                } else if (radioReemplazar.isSelected()) {
                    opcion = 2;
                } else {
                    JOptionPane.showMessageDialog(ModificarStockFrame.this, "Seleccione una opción.");
                    return;
                }

                ingresarCantidad(codigo, opcion);
            }
        });

        // Agregar panel al JFrame
        add(panel);

        setVisible(true);
    }

    private void ingresarCantidad(int codigo, int opcion) {
        try {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a modificar:"));
            admin.ModificarStock(codigo, cantidad, opcion); // Método del administrador para modificar el stock
            JOptionPane.showMessageDialog(this, "Stock modificado exitosamente.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor válido para la cantidad.");
        }
    }
}