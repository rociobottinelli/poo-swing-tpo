package interfaz;

import negocio.Administrador;
import negocio.Autoparte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarAutoparteFrame extends JFrame {
    private Administrador admin;

    private JTextField codigoField;
    private JTextField denominacionField;
    private JTextField descripcionField;
    private JTextField categoriaField;
    private JTextField marcaField;
    private JTextField modeloField;
    private JTextField precioField;
    private JTextField stockField;
    private JTextField stockMinimoField;
    private JTextField enlaceField;
    private JButton agregarButton;

    public AgregarAutoparteFrame(Administrador admin) {
        this.admin = admin;

        setTitle("Agregar Autoparte");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(11, 2));

        panel.add(new JLabel("Código:"));
        codigoField = new JTextField();
        panel.add(codigoField);

        panel.add(new JLabel("Denominación:"));
        denominacionField = new JTextField();
        panel.add(denominacionField);

        panel.add(new JLabel("Descripción:"));
        descripcionField = new JTextField();
        panel.add(descripcionField);

        panel.add(new JLabel("Categoría:"));
        categoriaField = new JTextField();
        panel.add(categoriaField);

        panel.add(new JLabel("Marca:"));
        marcaField = new JTextField();
        panel.add(marcaField);

        panel.add(new JLabel("Modelo:"));
        modeloField = new JTextField();
        panel.add(modeloField);

        panel.add(new JLabel("Precio:"));
        precioField = new JTextField();
        panel.add(precioField);

        panel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        panel.add(stockField);

        panel.add(new JLabel("Stock Mínimo:"));
        stockMinimoField = new JTextField();
        panel.add(stockMinimoField);

        panel.add(new JLabel("Enlace:"));
        enlaceField = new JTextField();
        panel.add(enlaceField);

        agregarButton = new JButton("Agregar Autoparte");
        panel.add(new JLabel()); // Empty label for alignment
        panel.add(agregarButton);

        add(panel);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAutoparte();
            }
        });

        setVisible(true);
    }

    private void agregarAutoparte() {
        try {
            int codigo = Integer.parseInt(codigoField.getText());
            while (admin.idAutoparteRepetido(codigo)) {
                JOptionPane.showMessageDialog(this, "Código repetido. Introduzca otro código.");
                return;
            }

            String denominacion = denominacionField.getText();
            String descripcion = descripcionField.getText();
            String categoria = categoriaField.getText();
            String marca = marcaField.getText();
            String modelo = modeloField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int stock = Integer.parseInt(stockField.getText());
            int stockMinimo = Integer.parseInt(stockMinimoField.getText());
            String enlace = enlaceField.getText();

            Autoparte autoparte = new Autoparte();
            autoparte.setCodigo(codigo);
            autoparte.setDenominacion(denominacion);
            autoparte.setDescripcion(descripcion);
            autoparte.setCategoria(categoria);
            autoparte.setMarca(marca);
            autoparte.setModelo(modelo);
            autoparte.setPrecio(precio);
            autoparte.setCantStock(stock);
            autoparte.setStockMinimo(stockMinimo);
            autoparte.setEnlace(enlace);

            admin.CargarAutoparte(autoparte);
            JOptionPane.showMessageDialog(this, "Autoparte cargada exitosamente!");

            dispose(); // Close the window after successful addition
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados. Por favor, verifique los campos numéricos.");
        }
    }
}