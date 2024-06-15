package interfaz;

import javax.swing.*;

import negocio.Administrador;


import java.awt.event.*;

public class EliminarAutoparteFrame extends JFrame {
	 private static final long serialVersionUID = 1L;
    private Administrador admin;

    public EliminarAutoparteFrame(Administrador admin) {
        this.admin = admin;

        setTitle("Eliminar Autoparte");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        // Componentes de la interfaz
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Introduzca el código del autoparte:");
        JTextField textField = new JTextField(10);
        JButton botonEliminar = new JButton("Eliminar");

        // Agregar componentes al panel
        panel.add(label);
        panel.add(textField);
        panel.add(botonEliminar);

        // Agregar panel al JFrame
        add(panel);

        // Acción del botón Eliminar
        botonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarAutoparte(textField.getText());
            }
        });

        setVisible(true);
    }

    private void eliminarAutoparte(String codigoStr) {
        try {
            int codigo = Integer.parseInt(codigoStr);

            if (admin.existeAutoparte(codigo)) {
                admin.EliminarDelCatalogo(codigo); // Suponiendo que existe un método para eliminar
                JOptionPane.showMessageDialog(this, "Autoparte eliminada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "La autoparte con código " + codigo + " no existe.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ERROR: Ingrese solo números enteros.");
        }
    }
}